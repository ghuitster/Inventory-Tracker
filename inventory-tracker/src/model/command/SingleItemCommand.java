
package model.command;

import model.IItem;
import model.IProductContainer;

public abstract class SingleItemCommand extends Command
{
	protected IItem item;

	@Override
	protected abstract void execute();

	public SingleItemCommand(IProductContainer target, IItem item)
	{
		super(target);
		this.item = item;
	}

	@Override
	protected abstract void undo();

}
