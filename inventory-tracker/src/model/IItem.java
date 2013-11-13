
package model;

import java.util.Date;

public interface IItem extends IObservable, ITaggable, Comparable<IItem>
{

	/**
	 * @pre barcode != null
	 * @param barcode the Barcode to attempt to set
	 * @return whether or not the barcode can be set
	 */
	public abstract boolean ableToSetBarcode(IBarcode barcode);

	/**
	 * @pre container != null
	 * @param container the ProductContainer to attempt to set
	 * @return whether or not container can be set
	 */
	public abstract boolean ableToSetContainer(IStorageUnit container);

	/**
	 * @pre exitTime != null
	 * @param exitTime the exitTime Date to attempt to set
	 * @return whether or not we can set the exitTime
	 */
	public abstract boolean ableToSetExitTime(Date exitTime);

	/**
	 * @pre product != null
	 * @param product the Product to attempt to set
	 * @return whether or not the product can be set
	 */
	public abstract boolean ableToSetProduct(IProduct product);

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public abstract boolean equals(Object obj);

	/**
	 * @return the barcode
	 */
	public abstract IBarcode getBarcode();

	/**
	 * @return the container
	 */
	public abstract IProductContainer getContainer();

	/**
	 * @return the entryDate
	 */
	public abstract Date getEntryDate();

	/**
	 * @return the exitTime
	 */
	public abstract Date getExitTime();

	/**
	 * @return the expirationDate
	 */
	public abstract Date getExpirationDate();

	/**
	 * @return the product
	 */
	public abstract IProduct getProduct();

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public abstract int hashCode();

	/**
	 * @pre barcode != null
	 * @post barcode == passed in barcode
	 * @param barcode the barcode to set
	 */
	public abstract void setBarcode(IBarcode barcode);

	/**
	 * @pre container != null
	 * @post container == passed in container
	 * @param otherProductContainer the container to set
	 */
	abstract void setContainer(IProductContainer otherProductContainer);

	/**
	 * @pre entryDate must be a Date object after 1/1/2000
	 * @post entryDate is set
	 * @param entryDate the Date to set entryDate to
	 */
	public abstract void setEntryDate(Date entryDate);

	/**
	 * @pre exitTime != null
	 * @post exitTime == passed in exitTime
	 * @param exitTime the exitTime to set
	 */
	public abstract void setExitTime(Date exitTime);


	/**
	 * @pre product != null
	 * @post product == product passed in
	 * @param product the product to set
	 */
	public abstract void setProduct(IProduct product);

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public abstract String toString();

	void signalChanged();

}
