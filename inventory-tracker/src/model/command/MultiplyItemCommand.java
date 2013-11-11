package model.command;

import java.util.List;
import model.IItem;

public abstract class MultiplyItemCommand extends Command
{
	protected List<IItem> items;

	@Override
	protected abstract void execute();

	@Override
	protected abstract void undo();

}
