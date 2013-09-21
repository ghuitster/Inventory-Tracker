
package gui.storageunit;

import gui.common.DialogBox;
import gui.inventory.ProductContainerData;
import gui.main.GUI;

@SuppressWarnings("serial")
public class EditStorageUnitView extends StorageUnitView implements
		IEditStorageUnitView
{

	public EditStorageUnitView(GUI parent, DialogBox dialog,
			ProductContainerData target)
	{
		super(parent, dialog);

		construct();

		_controller = new EditStorageUnitController(this, target);
	}

	@Override
	protected void cancel()
	{
		return;
	}

	@Override
	public IEditStorageUnitController getController()
	{
		return (IEditStorageUnitController) super.getController();
	}

	@Override
	protected void ok()
	{
		getController().editStorageUnit();
	}

	@Override
	protected void valuesChanged()
	{
		getController().valuesChanged();
	}

}
