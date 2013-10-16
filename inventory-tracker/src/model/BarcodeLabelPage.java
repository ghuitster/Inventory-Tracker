
package model;

import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
// import com.itextpdf.text.Image;
// import com.itextpdf.text.Paragraph;
// import com.itextpdf.text.pdf.PdfWriter;
// import com.itextpdf.text.pdf.BarcodeEAN;
// import com.itextpdf.text.pdf.BarcodeEANSUPP;
import com.itextpdf.text.Rectangle;

/**
 * class for printing out batches of Barcode labels for newly added Items.
 * @author Michael
 * 
 */
public class BarcodeLabelPage implements IBarcodeLabelPage
{
	// Variables
	private final ArrayList<Barcode> barcodes = null;
	private final Document document = null;
	private static String RESULT = null;
	private final int pageCount = 0;
	private final int BARCODES_PER_PAGE = 12;

	/**
	 * @pre barcodes must contain only valid Barcode objects
	 * @pre pageSize must be a valid pagesize for printing
	 * @param barcodes the list of Barcode objects to create labels for
	 * @param pageSize the size of the page to generate the Barcode labels on
	 */
	public BarcodeLabelPage(ArrayList<Barcode> barcodes, Rectangle pageSize)
	{

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
