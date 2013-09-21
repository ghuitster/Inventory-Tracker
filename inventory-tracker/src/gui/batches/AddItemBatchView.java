
package gui.batches;

import gui.common.DialogBox;
import gui.common.GridBagConstraintsExt;
import gui.inventory.ProductContainerData;
import gui.main.GUI;
import gui.product.AddProductView;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import common.util.DateUtils;

@SuppressWarnings("serial")
public class AddItemBatchView extends ItemBatchView implements
		IAddItemBatchView
{

	private JLabel entryDateLabel;
	private SpinnerModel entryDateSpinnerModel;
	private JSpinner.DateEditor entryDateSpinnerEditor;
	private JSpinner entryDateSpinner;
	private JLabel countLabel;
	private JTextField countField;

	public AddItemBatchView(GUI parent, DialogBox dialog,
			ProductContainerData target)
	{
		super(parent, dialog);

		construct();

		_controller = new AddItemBatchController(this, target);
	}

	@Override
	protected void barcodeChanged()
	{
		getController().barcodeChanged();
	}

	private void countChanged()
	{
		getController().countChanged();
	}

	@Override
	protected void createComponents()
	{
		super.createComponents();

		Date initDate = DateUtils.currentDate();
		Date latestDate = initDate;
		Date earliestDate = DateUtils.earliestDate();

		entryDateLabel = new JLabel("Entry Date:");

		entryDateSpinnerModel =
				new SpinnerDateModel(initDate, earliestDate, latestDate,
						Calendar.YEAR);
		entryDateSpinner = new JSpinner(entryDateSpinnerModel);
		entryDateSpinnerEditor =
				new JSpinner.DateEditor(entryDateSpinner, DateUtils.DATE_FORMAT);
		entryDateSpinner.setEditor(entryDateSpinnerEditor);
		entryDateSpinnerEditor.getTextField().getDocument()
				.addDocumentListener(new DocumentListener()
				{

					@Override
					public void changedUpdate(DocumentEvent e)
					{
						return;
					}

					@Override
					public void insertUpdate(DocumentEvent e)
					{
						processChange(e);
					}

					private void processChange(DocumentEvent e)
					{
						if(eventsAreDisabled())
						{
							return;
						}
						if(entryDateSpinnerEditor.getTextField().hasFocus())
						{
							entryDateChanged();
						}
					}

					@Override
					public void removeUpdate(DocumentEvent e)
					{
						processChange(e);
					}
				});
		// entryDateSpinner.addChangeListener(new ChangeListener() {
		// @Override
		// public void stateChanged(ChangeEvent e) {
		// if (eventsAreDisabled()) {
		// return;
		// }
		// entryDateChanged();
		// }
		// });

		countLabel = new JLabel("Count:");

		countField = new JTextField(5);
		countField.addKeyListener(new KeyListener()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				return;
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				if(eventsAreDisabled())
				{
					return;
				}
				countChanged();
			}

			@Override
			public void keyTyped(KeyEvent arg0)
			{
				return;
			}
		});
	}

	@Override
	public void displayAddProductView()
	{
		DialogBox dialogBox = new DialogBox(_parent, "Add Product");
		AddProductView dialogView =
				new AddProductView(_parent, dialogBox, getBarcode());
		dialogBox.display(dialogView, false);
	}

	// //////////////////////////
	// ItemBatchView Overrides
	// //////////////////////////

	@Override
	protected void done()
	{
		getController().done();
	}

	private void entryDateChanged()
	{
		getController().entryDateChanged();
	}

	@Override
	protected String getBarcodeLabel()
	{
		return "Product Barcode:";
	}

	@Override
	public IAddItemBatchController getController()
	{
		return (IAddItemBatchController) super.getController();
	}

	@Override
	public String getCount()
	{
		return countField.getText();
	}

	@Override
	public Date getEntryDate()
	{

		// return DateUtils.removeTimeFromDate((Date) entryDateSpinnerModel
		// .getValue());

		String entryDateText = entryDateSpinnerEditor.getTextField().getText();
		if(entryDateText == null)
		{
			return null;
		}
		try
		{
			return DateUtils.parseDate(entryDateText);
		}
		catch(ParseException e)
		{
			return null;
		}
	}

	@Override
	protected String getItemActionName()
	{
		return "Add Item";
	}

	@Override
	protected void itemAction()
	{
		getController().addItem();
	}

	@Override
	protected void layoutComponents()
	{
		batchPanel = new JPanel();
		batchPanel.setLayout(new GridBagLayout());

		GridBagConstraintsExt c = new GridBagConstraintsExt();
		c.ipadx = 2;
		c.ipady = 2;
		c.insets = new Insets(5, 5, 5, 5);

		c.place(0, 1, 1, 1);
		batchPanel.add(entryDateLabel, c);

		c.place(1, 1, 2, 1);
		batchPanel.add(entryDateSpinner, c);

		c.place(3, 1, 1, 1);
		batchPanel.add(countLabel, c);

		c.place(4, 1, 1, 1);
		batchPanel.add(countField, c);

		c.place(0, 0, 1, 1);
		batchPanel.add(barcodeLabel, c);

		c.place(1, 0, 2, 1);
		batchPanel.add(barcodeField, c);

		c.place(3, 0, 2, 1);
		batchPanel.add(scannerBox, c);

		c.place(1, 2, 1, 1);
		batchPanel.add(itemActionButton, c);

		c.place(2, 2, 1, 1);
		batchPanel.add(undoButton, c);

		c.place(3, 2, 1, 1);
		batchPanel.add(redoButton, c);

		c.place(4, 2, 1, 1);
		batchPanel.add(doneButton, c);

		setMaximumSize(batchPanel);

		productPanel = new JPanel();
		productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));

		productPanel.add(Box.createRigidArea(new Dimension(10, 10)));
		productPanel.add(batchPanel);
		productPanel.add(Box.createRigidArea(new Dimension(10, 10)));
		productPanel.add(productTableScrollPane);

		splitPane =
				new JSplitPane(JSplitPane.VERTICAL_SPLIT, productPanel,
						itemTableScrollPane);

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(splitPane);
	}

	@Override
	protected void redo()
	{
		getController().redo();
	}

	@Override
	protected void selectedItemChanged()
	{
		return;
	}

	// //////////////////////////
	// IAddItemsView overrides
	// //////////////////////////

	@Override
	protected void selectedProductChanged()
	{
		getController().selectedProductChanged();
	}

	@Override
	public void setCount(String value)
	{
		boolean disabledEvents = disableEvents();
		try
		{
			countField.setText(value);
		}
		finally
		{
			if(disabledEvents)
			{
				enableEvents();
			}
		}
	}

	@Override
	public void setEntryDate(Date value)
	{
		boolean disabledEvents = disableEvents();
		try
		{
			entryDateSpinnerModel.setValue(DateUtils.removeTimeFromDate(value));
		}
		finally
		{
			if(disabledEvents)
			{
				enableEvents();
			}
		}
	}

	@Override
	protected void undo()
	{
		getController().undo();
	}

	@Override
	protected void useScannerChanged()
	{
		getController().useScannerChanged();
	}

}
