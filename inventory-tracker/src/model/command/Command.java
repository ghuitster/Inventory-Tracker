/**
 * 
 */

package model.command;

import java.util.List;

import model.IItem;
import model.IProduct;
import model.IProductContainer;

/**
 * An abstract public class to represent a Command
 * @author David
 */
public abstract class Command
{
	protected List<IItem> items;
	protected List<IProduct> products;
	protected IProductContainer target;
	/**
	 * Perform the action for this command object
	 */
	protected abstract void execute();

	/**
	 * Undo any action performed by execute
	 */
	protected abstract void undo();
}
