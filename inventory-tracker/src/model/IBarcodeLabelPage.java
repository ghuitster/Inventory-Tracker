
package model;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

public interface IBarcodeLabelPage
{

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
	public abstract void createPDF(String filename) throws IOException,
			DocumentException;

}
