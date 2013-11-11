package model.command;

import model.IItem;

public abstract class SingleItemCommand extends Command
{
	protected IItem item;
	
	@Override
	protected abstract void execute();

	@Override
	protected abstract void undo();

}
