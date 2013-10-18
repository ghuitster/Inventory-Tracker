
package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * class for printing out batches of Barcode labels for newly added Items.
 * @author Michael
 * 
 */
public class BarcodeLabelPage implements IBarcodeLabelPage
{
	// Variables
	private ArrayList<Item> items = null;
	private Document document = null;
	private String result = "Labels" + File.separator + "labels-";
	private int pageCount = 0;
	private final int BARCODES_PER_PAGE = 72;

	/**
	 * @pre items must contain only valid Item objects
	 * @pre pageSize must be a valid page size for printing
	 * @param items the list of Item objects to create barcode labels for
	 */
	public BarcodeLabelPage(ArrayList<Item> items)
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
		String timeStamp =
				new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar
						.getInstance().getTime());
		this.result = this.result + timeStamp + ".pdf";
		FileOutputStream pdfos = new FileOutputStream(this.result);

		PdfWriter writer = PdfWriter.getInstance(this.document, pdfos);
		writer.setPdfVersion(PdfWriter.VERSION_1_7);

		this.document.open();

		// Set up Barcode Label Font stuff
		float fontSize = 7.0f;
		BaseFont basefont = BaseFont.createFont("Helvetica", "winansi", false);
		Font font = new Font(basefont, 7F);

		// step 4
		PdfContentByte cb = writer.getDirectContent();

		BarcodeEAN codeEAN = new BarcodeEAN();
		codeEAN.setCodeType(com.itextpdf.text.pdf.Barcode.UPCA);
		codeEAN.setFont(basefont);
		codeEAN.setSize(fontSize);
		codeEAN.setBaseline(fontSize);
		codeEAN.setBarHeight(fontSize * 3.0f);
		codeEAN.setCode("000000000000");

		Image codeImage = codeEAN.createImageWithBarcode(cb, null, null);
		float codeWidth = codeImage.getWidth();
		float float3 = 5.0f;
		float labelWidth = codeWidth + 10.0f; // float f2 = image.getWidth(); //
												// float f3 = 5F; // float f4 =
												// f2 + 10F;
		int columns =
				(int) (this.document.getPageSize().getWidth() / labelWidth);
		PdfPTable pdfTable = new PdfPTable(columns);
		pdfTable.setTotalWidth(columns * labelWidth);
		pdfTable.setLockedWidth(true);
		PdfPCell pdfTableCell = pdfTable.getDefaultCell();
		pdfTableCell.setBorder(0);
		pdfTableCell.setPadding(5.0f);

		for(int index = 0; index < this.items.size(); index++)
		{
			Item currentItem = this.items.get(index);
		}

		/* Stuff from code */
		/*
		 * for(Iterator iterator = list.iterator(); iterator.hasNext();) { en
		 * en1 = (en) iterator.next(); Iterator iterator1 = ((List)
		 * map.get(en1)).iterator(); while(iterator1.hasNext()) { ed ed1 = (ed)
		 * iterator1.next(); int j = 1; int k = 0; while(k < j) {
		 * barcodeean.setCode(ed1.d()); Image image1 =
		 * barcodeean.createImageWithBarcode(pdfcontentbyte, null, null); String
		 * s = ed1.c().d(); do { if(s.length() <= 0) break; Chunk chunk = new
		 * Chunk(s, font); if(chunk.getWidthPoint() < image1.getWidth()) break;
		 * s = s.substring(0, s.length() - 1); } while(true); String s1 =
		 * t.b(ed1.e()); if(ed1.i() != null) { s1 = (new
		 * StringBuilder()).append(s1) .append(" exp ").toString(); s1 = (new
		 * StringBuilder()).append(s1) .append(t.b(ed1.i())).toString(); }
		 * Paragraph paragraph = new Paragraph((new StringBuilder()).append(s)
		 * .append("\n").append(s1).toString(), font);
		 * paragraph.setLeading(0.0F, 1.1F); PdfPCell pdfpcell1 = new
		 * PdfPCell(); pdfpcell1.addElement(paragraph);
		 * image1.setSpacingBefore(2.0F); pdfpcell1.addElement(image1);
		 * pdfpcell1.setBorder(0); pdfpcell1.setPadding(5F);
		 * pdfptable.addCell(pdfpcell1); k++; } } }
		 * 
		 * pdfptable.completeRow(); document.add(pdfptable); document.close();
		 * 
		 * /* End stuff from code
		 */

		// Create a Barcode label from an item
		// Item currentItem = this.items.get(0);
		// String itemDescription =
		// currentItem.getProduct().getDescription().getDescription()
		// .substring(0, 19); // Limit Item/Product Description to
		// // first 18 characters
		//
		// document.add(new Paragraph(currentItem.getProduct().getDescription()
		// .getDescription().substring(0, 19)));
		// BarcodeEAN codeEAN = new BarcodeEAN();
		codeEAN.setCodeType(com.itextpdf.text.pdf.Barcode.UPCA);
		codeEAN.setCode(this.items.get(0).getBarcode().getNumber());
		codeEAN.setFont(basefont);

		Image image1 = codeEAN.createImageWithBarcode(cb, null, null);

		document.add(new Paragraph("default:"));
		document.add(codeEAN.createImageWithBarcode(cb, null, null));
		codeEAN.setGuardBars(false);
		document.add(new Paragraph("without guard bars:"));
		document.add(codeEAN.createImageWithBarcode(cb, null, null));
		codeEAN.setBaseline(-1f);
		codeEAN.setGuardBars(true);
		document.add(new Paragraph("text above:"));
		document.add(codeEAN.createImageWithBarcode(cb, null, null));
		codeEAN.setBaseline(codeEAN.getSize());

		codeEAN.setCodeType(com.itextpdf.text.pdf.Barcode.UPCA);
		codeEAN.setCode("785342304749");
		document.add(codeEAN.createImageWithBarcode(cb, null, null));

	}

}
