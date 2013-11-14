/**
 * 
 */

package model.report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author Michael
 * 
 */
public class PDFReportBuilder implements IReportBuilder
{
	// Variables
	private final String document = null;
	private float cellWidth = 0.0f;
	private Document pdfDocument = null;
	private final float BAR_HEIGHT_MULTIPLE = 3.0f;
	private final float LABEL_WIDTH_PADDING = 10.0f;
	private final float TOP_BOTTOM_MARGINS = 36f;
	private final float LEFT_RIGHT_MARGINS = 24f;
	private final float fontSize = 7.0f;
	private final float cellPadding = 5.0f;
	private final int cellBorder = 1;
	private BaseFont basefont;
	private Font font;
	private String path = null;
	private PdfWriter writer = null;
	private PdfPTable table = null;
	private FileOutputStream pdfos = null;

	// Constructor
	/**
	 * A class to create a PDF version of a report and save it as a PDF file.
	 */
	public PDFReportBuilder()
	{
		this.path = "";
		this.pdfDocument = new Document(PageSize.LETTER_LANDSCAPE.rotate());
		// // step 1
		// Document document = new Document(PageSize.A4.rotate());
		// // step 2
		// PdfWriter.getInstance(document, new FileOutputStream(filename));
		// // step 3
		// document.open();
		// // step 4
		// List<Date> days = PojoFactory.getDays(connection);
		// for (Date day : days) {
		// document.add(getTable(connection, day));
		// document.newPage();
		// }
		// // step 5
		// document.close();
		// // close the database connection
		// connection.close();
	}

	// Methods

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.report.IReportBuilder#setPath(java.lang.String)
	 */
	@Override
	public void setPath(String path)
	{
		// Set the path and get the document, writer, and file output stream
		// ready to build the document.
		this.path = path;

		// Create the label directory and filename for this particular batch of
		// labels.
		File directory =
				new File(path.substring(0, path.lastIndexOf(File.separator)));
		directory.mkdirs();
		File file = new File(path);
		if(!file.exists())
		{
			try
			{
				file.createNewFile();
			}
			catch(IOException ie)
			{
				ie.printStackTrace();
			}
		}

		// Create the filestream so we can write to the file.
		// Get the PdfWriter ready to write to the filestream and get the
		// document open
		try
		{
			this.pdfos = new FileOutputStream(this.path);
			this.writer = PdfWriter.getInstance(this.pdfDocument, pdfos);
			writer.setPdfVersion(PdfWriter.VERSION_1_7);
			this.pdfDocument.open();

			this.pdfos = new FileOutputStream(this.path);
			this.writer = PdfWriter.getInstance(this.pdfDocument, this.pdfos);
			this.writer.setPdfVersion(PdfWriter.VERSION_1_7);
			this.pdfDocument.open();
		}
		catch(DocumentException | FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.report.IReportBuilder#buildHead(java.lang.String)
	 */
	@Override
	public void buildHead(String title)
	{
		// this.document +=
		// "<!DOCTYPE html><html><head><title>" + title
		// + "</title></head><body><br><h1 align=\"center\"><b>"
		// + title + "</b></h1>";

		Font titleFont = FontFactory.getFont("Helvetica", 24, Font.BOLD);
		Paragraph titleParagraph = new Paragraph(title, titleFont);
		titleParagraph.setAlignment(Paragraph.ALIGN_CENTER);
		titleParagraph.setSpacingAfter(10);
		try
		{
			this.pdfDocument.add(titleParagraph);
		}
		catch(DocumentException de)
		{
			de.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.report.IReportBuilder#addSectionHeader(java.lang.String)
	 */
	@Override
	public void addSectionHeader(String title)
	{
		// this.document += "<br><h2>" + title + "</h2>";
		Font sectionFont = FontFactory.getFont("Helvetica", 18, Font.BOLD);
		Paragraph sectionParagraph = new Paragraph(title, sectionFont);
		sectionParagraph.setAlignment(Paragraph.ALIGN_LEFT);
		sectionParagraph.setSpacingAfter(5);
		try
		{
			this.pdfDocument.add(sectionParagraph);
		}
		catch(DocumentException de)
		{
			de.printStackTrace();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.report.IReportBuilder#addText(java.lang.String)
	 */
	@Override
	public void addText(String text)
	{
		// this.document += "<br><p>" + text + "</p>";
		Font textFont = FontFactory.getFont("Helvetica", 12, Font.NORMAL);
		Paragraph titleParagraph = new Paragraph(text, textFont);
		titleParagraph.setAlignment(Paragraph.ALIGN_LEFT);
		titleParagraph.setSpacingAfter(5);
		try
		{
			this.pdfDocument.add(titleParagraph);
		}
		catch(DocumentException de)
		{
			de.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.report.IReportBuilder#startTable(java.lang.String[])
	 */
	@Override
	public void startTable(String[] columnHeaders)
	{
		this.cellWidth =
				(this.pdfDocument.getPageSize().getWidth() - this.LEFT_RIGHT_MARGINS * 2)
						/ columnHeaders.length;

		this.table = new PdfPTable(columnHeaders.length);

		this.table.setTotalWidth(cellWidth * columnHeaders.length);
		this.table.setLockedWidth(true);
		PdfPCell cell = this.table.getDefaultCell();
		cell.setBorder(this.cellBorder);
		cell.setPadding(this.cellPadding);
		cell.setMinimumHeight(13f);
		Font cellFont = FontFactory.getFont("Helvetica", 12, Font.BOLD);

		for(String header: columnHeaders)
		{
			Paragraph cellParagraph = new Paragraph(header, cellFont);
			cellParagraph.setAlignment(Paragraph.ALIGN_LEFT);
			cell = new PdfPCell(cellParagraph);
			this.table.addCell(cell);
		}

		this.table.completeRow();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.report.IReportBuilder#addTableRow(java.lang.String[])
	 */
	@Override
	public void addTableRow(String[] cells)
	{
		PdfPCell cell = this.table.getDefaultCell();
		cell.setBorder(this.cellBorder);
		cell.setPadding(this.cellPadding);
		cell.setMinimumHeight(12f);
		Font cellFont = FontFactory.getFont("Helvetica", 12, Font.NORMAL);

		for(String text: cells)
		{
			Paragraph cellParagraph = new Paragraph(text, cellFont);
			cellParagraph.setAlignment(Paragraph.ALIGN_LEFT);
			cell = new PdfPCell(cellParagraph);
			//
			// cell.addElement(cellParagraph);
			this.table.addCell(cell);
		}

		this.table.completeRow();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.report.IReportBuilder#addBulletedList(java.lang.String[])
	 */
	@Override
	public void addBulletedList(String[] items)
	{
		// this.document += "<br><ul>";
		// for(String item: items)
		// {
		// this.document +=
		// "<li>" + StringEscapeUtils.escapeHtml4(item) + "</li>";
		// }
		// this.document += "</ul>";

		List unorderedList = new List(List.UNORDERED);
		unorderedList.setListSymbol("•");

		for(String item: items)
		{
			unorderedList.add(new ListItem(item));
		}

		try
		{
			this.pdfDocument.add(unorderedList);
		}
		catch(DocumentException de)
		{
			de.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.report.IReportBuilder#finishTable()
	 */
	@Override
	public void finishTable()
	{
		// this.document += "</tbody><tfoot></tfoot></table>";

		this.table.setSpacingAfter(10f);
		try
		{
			this.pdfDocument.add(this.table);
		}
		catch(DocumentException de)
		{
			de.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.report.IReportBuilder#finishAndSave(java.lang.String)
	 */
	@Override
	public void finishAndSave()
	{
		// this.document += "</body></html>";
		// OutputStream file = null;
		//
		// try
		// {
		// File directory =
		// new File(this.path.substring(0,
		// this.path.lastIndexOf(File.separator)));
		// directory.mkdirs();
		// file = new FileOutputStream(new File(this.path));
		// pdfDocument = new Document(PageSize.LETTER_LANDSCAPE);
		// PdfWriter.getInstance(pdfDocument, file);
		// pdfDocument.open();
		// HTMLWorker htmlWorker = new HTMLWorker(pdfDocument);
		// htmlWorker.parse(new StringReader(this.document));
		// pdfDocument.close();
		// file.close();
		// java.awt.Desktop.getDesktop().open(new File(this.path));
		// }
		// catch(Exception e)
		// {
		// e.printStackTrace();
		// }

		this.pdfDocument.close();
		try
		{
			this.pdfos.close();
			java.awt.Desktop.getDesktop().open(new File(this.path));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

}
