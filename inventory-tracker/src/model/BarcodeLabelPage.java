
package model;

import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
// import com.itextpdf.text.Image;
// import com.itextpdf.text.Paragraph;
// import com.itextpdf.text.pdf.PdfWriter;
// import com.itextpdf.text.pdf.BarcodeEAN;
// import com.itextpdf.text.pdf.BarcodeEANSUPP;

/**
 * class for printing out batches of Barcode labels for newly added Items.
 * @author Michael
 * 
 */
public class BarcodeLabelPage implements IBarcodeLabelPage
{
	// Variables
	private final ArrayList<Barcode> barcodes = null;
	private Document document = null;
	private final String RESULT = null;
	private final int pageCount = 0;
	private final int BARCODES_PER_PAGE = 12;

	/**
	 * @pre barcodes must contain only valid Barcode objects
	 * @pre pageSize must be a valid pagesize for printing
	 * @param barcodes the list of Barcode objects to create labels for
	 */
	public BarcodeLabelPage(ArrayList<Barcode> barcodes)
	{
		// Set Document object to have American Letter standard pagesize with
		// 0.5" left/right, and 1.0" top/bottom margins
		this.document = new Document(PageSize.LETTER, 36f, 36f, 72f, 72f);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IBarcodeLabelPage#createPDF(java.lang.String)
	 */
	@Override
	public void createPDF(String filename) throws IOException,
			DocumentException
	{

	}

}
