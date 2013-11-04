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
public interface ICommand
{
	List<IItem> items = null;
	List<IProduct> products = null;

	/**
	 * Perform the action for this command object
	 */
	public void execute();

	/**
	 * Undo any action performed by execute
	 */
	public void undo();
}
