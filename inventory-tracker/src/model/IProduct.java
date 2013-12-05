
package model;

import java.util.Date;
import java.util.Set;

public interface IProduct extends ITaggable, IObservable, Comparable<IProduct>
{
	/**
	 * @pre container != null
	 * @param container the container to attempt to add
	 * @return whether the container can be added or not
	 */
	public abstract boolean ableToAddContainer(IStorageUnit container);

	/**
	 * @pre productContainer must != null
	 * @param productContainer the container to attempt to remove
	 */
	public abstract boolean ableToRemoveContainer(
			IProductContainer productContainer);

	/**
	 * @pre barcode != null
	 * @param barcode the Barcode to attempt to set
	 * @return if the Barcode can be set or not
	 */
	public abstract boolean ableToSetBarcode(IBarcode barcode);

	/**
	 * @pre description != null
	 * @param description the description to attempt to set
	 * @return if the description can be set or not
	 */
	public abstract boolean ableToSetDescription(String description);

	/**
	 * @pre shelfLife must be > 0
	 * @param shelfLife the shelfLife to attempt to set
	 * @return if the shelflife can be set or not
	 */
	public abstract boolean ableToSetShelfLife(int shelfLife);

	/**
	 * @pre size != null
	 * @pre size number component must not be <= 0
	 * @param size the size to attempt to set
	 * @return whether the size can be set or not
	 */
	public abstract boolean ableToSetSize(Amount size);

	/**
	 * @pre threeMonthSupply != null
	 * @param threeMonthSupply the threeMonthSupply to attempt to set
	 * @return whether the threeMonthSupply could be set or not
	 */
	public abstract boolean ableToSetThreeMonthSupply(
			CountThreeMonthSupply threeMonthSupply);

	/**
	 * @pre container != null
	 * @pre this Product must not exist in another product container in this
	 *      storage unit
	 * @post container == passed in container
	 * @param productContainer the container to add
	 */
	public abstract void addContainer(IProductContainer productContainer);

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public abstract boolean equals(Object obj);

	/**
	 * @return the barcode
	 */
	public abstract IBarcode getBarcode();

	/**
	 * @return the containers set
	 */
	public abstract Set<IProductContainer> getContainers();

	/**
	 * @return the creationDate
	 */
	public abstract Date getCreationDate();

	/**
	 * @return the description
	 */
	public abstract IProductDescription getDescription();

	/**
	 * A unique ID to represent this product
	 * @return
	 */
	public abstract int getId();

	public abstract int getID();

	/**
	 * @return the shelfLife
	 */
	public abstract int getShelfLife();

	/**
	 * @return the size
	 */
	public abstract Amount getSize();

	/**
	 * @return the threeMonthSupply
	 */
	public abstract CountThreeMonthSupply getThreeMonthSupply();

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public abstract int hashCode();

	/**
	 * @pre productContainer != null
	 * @pre productContainer must exist in the set of containers for this
	 *      product
	 * @post productContainer no longer exists in the set of containers for this
	 *       product
	 * @param productContainer the container to remove
	 */
	public abstract void removeContainer(IProductContainer productContainer);

	/**
	 * @pre barcode != null
	 * @post barcode == passed in barcode
	 * @param barcode the Barcode to set
	 */
	public abstract void setBarcode(IBarcode barcode);

	/**
	 * @pre creationDate != null
	 * @pre creationDate == earliest entryDate of items of this product
	 * @post creationData == passed in creationDate
	 * @param creationDate the creationDate to set
	 */
	public abstract void setCreationDate(Date creationDate);

	/**
	 * @pre != null
	 * @pre description must be valid
	 * @param description the description to set
	 */
	public abstract void setDescription(IProductDescription description);

	public abstract void setID(int id);

	/**
	 * @pre shelfLife != null
	 * @pre shelfLife must be non negative
	 * @post shelfLife == passed in shelfLife
	 * @param shelfLife the shelfLife to set
	 */
	public abstract void setShelfLife(int shelfLife);

	/**
	 * @pre size number component must be > 0
	 * @pre size != null
	 * @post size == passed in size
	 * @param size the size to set
	 */
	public abstract void setSize(Amount size);

	/**
	 * @pre threeMonthSupply != null
	 * @pre threeMonthSupply number component must be > 0
	 * @post threeMonthSupply == passed in threeMonthSupply
	 * @param threeMonthSupply the threeMonthSupply to set
	 */
	public abstract void setThreeMonthSupply(
			CountThreeMonthSupply threeMonthSupply);

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public abstract String toString();
}
