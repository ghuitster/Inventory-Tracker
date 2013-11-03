/**
 * 
 */

package model.command;

/**
 * An Interface to represent a Command
 * @author David
 */
public interface Command
{
	/**
	 * Perform the action for this command object
	 */
	public void execute();

	/**
	 * Undo any action performed by execute
	 */
	public void undo();
}
