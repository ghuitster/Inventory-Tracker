
package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

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
	private int pageCount = 0;
	private final int BARCODES_PER_PAGE = 72;
	private final float BAR_HEIGHT_MULTIPLE = 3.0f;
	private final float LABEL_WIDTH_PADDING = 10.0f;

	/**
	 * @pre items must contain only valid Item objects
	 * @pre pageSize must be a valid page size for printing
	 * @param items the list of Item objects to create barcode labels for
	 */
	public BarcodeLabelPage(List<IItem> items)
	{
		// Set Document object to have American Letter standard pagesize with
		// 0.5" left/right, and 1.0" top/bottom margins
		this.document = new Document(PageSize.LETTER, 36f, 36f, 72f, 72f);
		this.items = items;

		// Set how many pages we are going to need to print all of the barcodes
		this.pageCount = this.items.size() / this.BARCODES_PER_PAGE;
		if(this.items.size() % this.BARCODES_PER_PAGE != 0)
		{
			this.pageCount += 1;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IBarcodeLabelPage#createPDF(java.lang.String)
	 */
	@Override
	public void createPDF() throws IOException, DocumentException
	{
		// Set up Barcode Label variables
		float fontSize = 7.0f;
		float labelPadding = 5.0f;
		float codeWidth = 0.0f;
		float labelWidth = 0.0f;

		int numColumns = 0;
		int labelBorder = 0;

		Image codeImage = null;
		BarcodeEAN codeEAN = null;
		PdfWriter writer = null;

		BaseFont basefont = BaseFont.createFont("Helvetica", "winansi", false);
		Font font = new Font(basefont, 7F);

		String timeStamp =
				new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar
						.getInstance().getTime());
		this.filename = this.filename + timeStamp + ".pdf";
		File dir =
				new File(this.filename.substring(0,
						this.filename.lastIndexOf(File.separator)));
		dir.mkdirs();
		File temp =
				new File(dir, this.filename.substring(this.filename
						.lastIndexOf(File.separator) + 1));
		if(!temp.exists())
		{
			temp.createNewFile();
		}
		FileOutputStream pdfos = null;
		try
		{
			pdfos = new FileOutputStream(this.filename);
		}
		catch(FileNotFoundException fnfe)
		{
			fnfe.printStackTrace();
		}

		// Get the document open, and the PdfWriter ready to write to the
		// filestream
		writer = PdfWriter.getInstance(this.document, pdfos);
		writer.setPdfVersion(PdfWriter.VERSION_1_7);
		this.document.open();

		// Let's write stuff
		PdfContentByte cb = writer.getDirectContent();

		// Create a test label to work out columns per page
		codeEAN = new BarcodeEAN();
		codeEAN.setCodeType(Barcode.UPCA);
		codeEAN.setFont(basefont);
		codeEAN.setSize(fontSize);
		codeEAN.setBaseline(fontSize);
		codeEAN.setBarHeight(fontSize * this.BAR_HEIGHT_MULTIPLE);
		codeEAN.setCode("555555555555"); // Use fives to give uniform character
											// spacing

		codeImage = codeEAN.createImageWithBarcode(cb, null, null);
		codeWidth = codeImage.getWidth();;
		labelWidth = codeWidth + this.LABEL_WIDTH_PADDING;
		numColumns =
				(int) (this.document.getPageSize().getWidth() / labelWidth);

		// Now we create the Table based on the number of columns we've
		// calculated.
		PdfPTable pdfTable = new PdfPTable(numColumns);
		pdfTable.setTotalWidth(numColumns * labelWidth);
		pdfTable.setLockedWidth(true);
		PdfPCell pdfTableCell = pdfTable.getDefaultCell();
		pdfTableCell.setBorder(labelBorder);
		pdfTableCell.setPadding(labelPadding);

		// Now let's go through and create our labels
		for(int index = 0; index < this.items.size(); index++)
		{
			// Variables we'll need for each Label

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
			// Image currentCodeImage =
			codeImage = codeEAN.createImageWithBarcode(cb, null, null);
			String labelDescription = description.getDescription();

			if(labelDescription.length() > 0)
			{
				Chunk chunk = new Chunk(labelDescription, font);
				while(chunk.getWidthPoint() > codeImage.getWidth())
				{
					labelDescription =
							labelDescription.substring(0,
									labelDescription.length() - 1);
					chunk = new Chunk(labelDescription, font);
				}
			}
			Paragraph labelParagraph =
					new Paragraph(labelDescription + "\n" + dateString, font);
			labelParagraph.setLeading(0.0f, 1.1f);
			pdfTableCell = new PdfPCell();
			pdfTableCell.addElement(labelParagraph);
			codeImage.setSpacingBefore(2.0f);
			pdfTableCell.addElement(codeImage);
			pdfTableCell.setBorder(0);
			pdfTableCell.setPadding(labelPadding);
			pdfTable.addCell(pdfTableCell);
		}

		pdfTable.completeRow();
		document.add(pdfTable);
		document.close();
		pdfos.close();

		java.awt.Desktop.getDesktop().open(new File(this.filename));
	}
}
