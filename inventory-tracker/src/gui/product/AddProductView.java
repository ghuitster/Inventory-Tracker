
package gui.product;

import gui.common.DialogBox;
import gui.main.GUI;

@SuppressWarnings("serial")
public class AddProductView extends ProductView implements IAddProductView
{

	public AddProductView(GUI parent, DialogBox dialog, String barcode)
	{
		super(parent, dialog);

		construct();

		_controller = new AddProductController(this, barcode);
	}

	@Override
	protected void cancel()
	{
		return;
	}

	@Override
	public IAddProductController getController()
	{
		return (IAddProductController) super.getController();
	}

	@Override
	protected void ok()
	{
		getController().addProduct();
	}

	@Override
	protected void valuesChanged()
	{
		getController().valuesChanged();
	}

}
