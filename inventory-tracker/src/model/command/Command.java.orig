/**
 * 
 */

package model.command;

import java.util.List;

import model.IItem;
import model.IProduct;

/**
 * An Interface to represent a Command
 * @author David
 */
public abstract class Command
{
	protected List<IItem> items;
	protected List<IProduct> products;

	/**
	 * Perform the action for this command object
	 */
	protected abstract void execute();

	/**
	 * Undo any action performed by execute
	 */
	protected abstract void undo();
}
