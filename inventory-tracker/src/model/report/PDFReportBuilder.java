/**
 * 
 */

package model.report;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.lang3.StringEscapeUtils;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

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
		this.document = StringEscapeUtils.escapeHtml4(this.document);
		OutputStream file = null;

		try
		{
			file = new FileOutputStream(new File(path));
			Document pdfDocument = new Document();
			PdfWriter writer = PdfWriter.getInstance(pdfDocument, file);
			pdfDocument.open();
			InputStream is = new ByteArrayInputStream(this.document.getBytes());
			XMLWorkerHelper.getInstance().parseXHtml(writer, pdfDocument, is);
			pdfDocument.close();
			file.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
