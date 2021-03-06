
package model.command;

import model.IItem;
import model.IProductContainer;

public abstract class SingleItemCommand extends Command
{
	protected IItem item;

	public SingleItemCommand(IProductContainer target, IItem item)
	{
		super(target);
		this.item = item;
	}

	@Override
	public abstract void execute();

	@Override
	public abstract void undo();

}
