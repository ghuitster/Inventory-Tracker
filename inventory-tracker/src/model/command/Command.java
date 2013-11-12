/**
 * 
 */

package model.command;

import model.IProductContainer;

/**
 * An abstract public class to represent a Command
 * @author David
 */
public abstract class Command
{
	public Command(IProductContainer target)
	{
		super();
		this.target = target;
	}

	protected IProductContainer target;

	/**
	 * Perform the action for this command object
	 */
	public abstract void execute();

	/**
	 * Undo any action performed by execute
	 */
	public abstract void undo();
}
