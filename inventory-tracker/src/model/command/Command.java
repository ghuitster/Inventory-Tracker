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
	protected IProductContainer target;

	public Command(IProductContainer target)
	{
		super();
		this.target = target;
	}

	/**
	 * Perform the action for this command object
	 */
	public abstract void execute();

	/**
	 * Undo any action performed by execute
	 */
	public abstract void undo();
}
