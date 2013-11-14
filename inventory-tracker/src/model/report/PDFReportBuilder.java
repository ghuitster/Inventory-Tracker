/**
 * 
 */

package model.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author Michael
 * 
 */
public class PDFReportBuilder extends HTMLReportBuilder
{

	/**
	 * A class to create a PDF version of a report and save it as a PDF file.
	 */
	public PDFReportBuilder()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.report.IReportBuilder#finishAndSave(java.lang.String)
	 */
	@Override
	public void finishAndSave(String path)
	{
		this.document += "</body></html>";
		OutputStream file = null;

		try
		{
			File directory =
					new File(
							path.substring(0, path.lastIndexOf(File.separator)));
			directory.mkdirs();
			file = new FileOutputStream(new File(path));
			Document pdfDocument = new Document(PageSize.LETTER_LANDSCAPE);
			PdfWriter.getInstance(pdfDocument, file);
			pdfDocument.open();
			HTMLWorker htmlWorker = new HTMLWorker(pdfDocument);
			htmlWorker.parse(new StringReader(this.document));
			pdfDocument.close();
			file.close();
			java.awt.Desktop.getDesktop().open(new File(path));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
