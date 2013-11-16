
package model.report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import model.IBarcode;
import model.IBarcodeLabelPage;
import model.IItem;
import model.IProductDescription;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import common.util.DateUtils;

/**
 * class for printing out batches of Barcode labels for newly added Items.
 * @author Michael
 * 
 */
public class BarcodeLabelPage implements IBarcodeLabelPage
{
	// Variables
	private List<IItem> items = null;
	private Document document = null;
	private String filename = "Labels" + File.separator + "labels-";
	private final float BAR_HEIGHT_MULTIPLE = 3.0f;
	private final float LABEL_WIDTH_PADDING = 10.0f;
	private final float TOP_BOTTOM_MARGINS = 36f;
	private final float LEFT_RIGHT_MARGINS = 24f;
	private final float fontSize = 7.0f;
	private final float labelPadding = 5.0f;
	private final int labelBorder = 0;
	private BaseFont basefont;
	private Font font;

	/**
	 * @pre items must contain only valid Item objects
	 * @pre pageSize must be a valid page size for printing
	 * @param items the list of Item objects to create barcode labels for
	 */
	public BarcodeLabelPage(List<IItem> items)
	{
		// Set Document object to have American Letter standard pagesize with
		// 24pt left/right, and 36pt top/bottom margins
		this.document =
				new Document(PageSize.LETTER, LEFT_RIGHT_MARGINS,
						LEFT_RIGHT_MARGINS, TOP_BOTTOM_MARGINS,
						TOP_BOTTOM_MARGINS);
		this.items = items;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IBarcodeLabelPage#createPDF(java.lang.String)
	 */
	@Override
	public void createPDF() throws IOException, DocumentException
	{
		float codeWidth = 0.0f;
		float labelWidth = 0.0f;

		int numColumns = 0;
		Image codeImage = null;
		BarcodeEAN codeEAN = null;
		PdfWriter writer = null;
		File file = null;
		File directory = null;
		FileOutputStream pdfos = null;
		PdfContentByte cb = null;
		PdfPTable pdfTable = null;
		PdfPCell pdfTableCell = null;

		this.basefont = BaseFont.createFont("Helvetica", "winansi", false);
		this.font = new Font(this.basefont, this.fontSize);

		String timeStamp =
				new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar
						.getInstance().getTime());
		this.filename = this.filename + timeStamp + ".pdf";

		// Create the label directory and filename for this particular batch of
		// labels.
		directory =
				new File(this.filename.substring(0,
						this.filename.lastIndexOf(File.separator)));
		directory.mkdirs();
		file =
				new File(directory, this.filename.substring(this.filename
						.lastIndexOf(File.separator) + 1));
		if(!file.exists())
		{
			file.createNewFile();
		}

		// Create the filestream so we can write to the file.
		try
		{
			pdfos = new FileOutputStream(this.filename);
		}
		catch(FileNotFoundException fnfe)
		{
			fnfe.printStackTrace();
		}

		// Get the PdfWriter ready to write to the filestream and get the
		// document open
		writer = PdfWriter.getInstance(this.document, pdfos);
		writer.setPdfVersion(PdfWriter.VERSION_1_7);
		this.document.open();

		// Let's write stuff
		cb = writer.getDirectContent();

		// Create a test label to work out columns per page
		codeEAN = new BarcodeEAN();
		codeEAN.setCodeType(Barcode.UPCA);
		codeEAN.setSize(this.fontSize);
		codeEAN.setBaseline(this.fontSize);
		codeEAN.setBarHeight(this.fontSize * this.BAR_HEIGHT_MULTIPLE);
		codeEAN.setFont(this.basefont);
		codeEAN.setCode("555555555555"); // Use fives to give uniform character
											// spacing
		codeImage = codeEAN.createImageWithBarcode(cb, null, null);
		codeWidth = codeImage.getWidth();;
		labelWidth = codeWidth + this.LABEL_WIDTH_PADDING;
		numColumns =
				(int) (this.document.getPageSize().getWidth() / labelWidth);

		// Now we create the Table based on the number of columns we've
		// calculated, and a cell to populate the table with
		pdfTable = new PdfPTable(numColumns);
		pdfTable.setTotalWidth(numColumns * labelWidth);
		pdfTable.setLockedWidth(true);
		pdfTableCell = pdfTable.getDefaultCell();
		pdfTableCell.setBorder(this.labelBorder);
		pdfTableCell.setPadding(this.labelPadding);

		// Now let's go through and create our labels
		for(int index = 0; index < this.items.size(); index++)
		{
			// Variables we'll need for each Label, if they weren't created
			// outside the
			// loop
			String labelDescription = "";
			Paragraph labelParagraph = null;
			Chunk chunk = null;
			float fixedLeading = 0.0f;
			float multipliedLeading = 1.25f;
			float beforeCodeSpacing = 2.0f;

			// Get Item data
			IItem currentItem = this.items.get(index);
			IBarcode barcode = currentItem.getBarcode();
			IProductDescription description =
					currentItem.getProduct().getDescription();

			String dateString =
					DateUtils.formatShortDate(currentItem.getEntryDate());
			if(currentItem.getExpirationDate() != null)
			{
				dateString +=
						" exp "
								+ DateUtils.formatShortDate(currentItem
										.getExpirationDate());
			}

			codeEAN.setCode(barcode.getNumber());
			codeImage = codeEAN.createImageWithBarcode(cb, null, null);
			labelDescription = description.getDescription();

			if(labelDescription.length() > 0)
			{
				chunk = new Chunk(labelDescription, this.font);
				while(chunk.getWidthPoint() > codeImage.getWidth())
				{
					labelDescription =
							labelDescription.substring(0,
									labelDescription.length() - 1);
					chunk = new Chunk(labelDescription, this.font);
				}
			}
			labelParagraph =
					new Paragraph(labelDescription + "\n" + dateString,
							this.font);
			labelParagraph.setLeading(fixedLeading, multipliedLeading);
			pdfTableCell = new PdfPCell();
			pdfTableCell.addElement(labelParagraph);
			codeImage.setSpacingBefore(beforeCodeSpacing);
			pdfTableCell.addElement(codeImage);
			pdfTableCell.setBorder(0);
			pdfTableCell.setPadding(this.labelPadding);
			pdfTable.addCell(pdfTableCell);
		}

		pdfTable.completeRow();
		this.document.add(pdfTable);
		this.document.close();
		pdfos.close();
		java.awt.Desktop.getDesktop().open(new File(this.filename));
	}
}
