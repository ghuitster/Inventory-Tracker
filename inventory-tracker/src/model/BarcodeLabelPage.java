
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

public class BarcodeLabelPage
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

	/**
	 * Creates a PDF document containing Barcode labels (UPC-A) generated from
	 * the Barcode objects passed in.
	 * @pre this.barcodes must not be null;
	 * @pre this.document must not be null;
	 * @pre this.pageCount must != 0;
	 * @post this.RESULT represents a location to the Valid PDF we created.
	 * @param filename the path to the new PDF document
	 * @throws IOException
	 * @throws DocumentException
	 */
	public void createPDF(String filename) throws IOException,
			DocumentException
	{

	}

}
