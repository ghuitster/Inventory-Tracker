
package gui.product;

import gui.common.ButtonBankListener;
import gui.common.ButtonBankPanel;
import gui.common.DialogBox;
import gui.common.DialogView;
import gui.common.GridBagConstraintsExt;
import gui.common.SizeUnits;
import gui.main.GUI;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public abstract class ProductView extends DialogView
{

	private JPanel _valuesPanel;
	private JLabel _barcodeLabel;
	private JTextField _barcodeField;
	private JLabel _descriptionLabel;
	private JTextField _descriptionField;
	private JLabel _sizeLabel;
	private JTextField _sizeField;
	private JComboBox _sizeBox;
	private JLabel _supplyLabel;
	private JTextField _supplyField;
	private JLabel _supplyCountLabel;
	private JLabel _shelfLifeLabel;
	private JTextField _shelfLifeField;
	private JLabel _shelfLifeMonthsLabel;
	private ButtonBankPanel _buttonsPanel;
	protected JButton _okButton;

	public ProductView(GUI parent, DialogBox dialog)
	{
		super(parent, dialog);
	}

	protected abstract void cancel();

	private void createButtonsPanel()
	{
		_buttonsPanel =
				new ButtonBankPanel(new String[] {"OK", "Cancel"},
						new ButtonBankListener()
						{
							@Override
							public void buttonPressed(int index, String label)
							{
								switch(index)
								{
								case 0:
									ok();
									_dialog.dispose();
									break;
								case 1:
									cancel();
									_dialog.dispose();
									break;
								default:
									assert false;
									break;
								}
							}
						});

		_okButton = _buttonsPanel.getButtons()[0];
		_dialog.getRootPane().setDefaultButton(_okButton);
	}

	@Override
	protected void createComponents()
	{
		createValuesPanel();
		createButtonsPanel();
	}

	private void createValuesPanel()
	{
		KeyListener keyListener = new KeyListener()
		{

			@Override
			public void keyPressed(KeyEvent e)
			{
				return;
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				valuesChanged();
			}

			@Override
			public void keyTyped(KeyEvent arg0)
			{
				return;
			}
		};

		_valuesPanel = new JPanel();

		_barcodeLabel = new JLabel("Product Barcode:");

		_barcodeField = new JTextField(15);
		_barcodeField.addKeyListener(keyListener);

		_descriptionLabel = new JLabel("Description:");

		_descriptionField = new JTextField(30);
		_descriptionField.addKeyListener(keyListener);

		_sizeLabel = new JLabel("Size:");

		_sizeField = new JTextField(8);
		_sizeField.addKeyListener(keyListener);

		_sizeBox = new JComboBox();
		_sizeBox.addItem(SizeUnits.Count);
		_sizeBox.addItem(SizeUnits.Pounds);
		_sizeBox.addItem(SizeUnits.Ounces);
		_sizeBox.addItem(SizeUnits.Grams);
		_sizeBox.addItem(SizeUnits.Kilograms);
		_sizeBox.addItem(SizeUnits.Gallons);
		_sizeBox.addItem(SizeUnits.Quarts);
		_sizeBox.addItem(SizeUnits.Pints);
		_sizeBox.addItem(SizeUnits.FluidOunces);
		_sizeBox.addItem(SizeUnits.Liters);
		_sizeBox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if(eventsAreDisabled())
				{
					return;
				}
				valuesChanged();
			}
		});

		_supplyLabel = new JLabel("3 Month Supply:");

		_supplyField = new JTextField(8);
		_supplyField.addKeyListener(keyListener);

		_supplyCountLabel = new JLabel("count");

		_shelfLifeLabel = new JLabel("Shelf Life:");

		_shelfLifeField = new JTextField(8);
		_shelfLifeField.addKeyListener(keyListener);

		_shelfLifeMonthsLabel = new JLabel("months");
	}

	public void enableBarcode(boolean value)
	{
		_barcodeField.setEnabled(value);
	}

	public void enableDescription(boolean value)
	{
		_descriptionField.setEnabled(value);
	}

	public void enableOK(boolean value)
	{
		_okButton.setEnabled(value);
	}

	public void enableShelfLife(boolean value)
	{
		_shelfLifeField.setEnabled(value);
	}

	public void enableSizeUnit(boolean value)
	{
		_sizeBox.setEnabled(value);
	}

	public void enableSizeValue(boolean value)
	{
		_sizeField.setEnabled(value);
	}

	public void enableSupply(boolean value)
	{
		_supplyField.setEnabled(value);
	}

	public String getBarcode()
	{
		return _barcodeField.getText();
	}

	public String getDescription()
	{
		return _descriptionField.getText();
	}

	public String getShelfLife()
	{
		return _shelfLifeField.getText();
	}

	public SizeUnits getSizeUnit()
	{
		return (SizeUnits) _sizeBox.getSelectedItem();
	}

	public String getSizeValue()
	{
		return _sizeField.getText();
	}

	public String getSupply()
	{
		return _supplyField.getText();
	}

	@Override
	protected void layoutComponents()
	{
		layoutValuesPanel();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(_valuesPanel);
		add(Box.createHorizontalStrut(5));
		add(_buttonsPanel);
	}

	private void layoutValuesPanel()
	{
		_valuesPanel.setLayout(new GridBagLayout());

		GridBagConstraintsExt c = new GridBagConstraintsExt();
		c.ipadx = 2;
		c.ipady = 2;
		c.insets = new Insets(5, 5, 5, 5);

		c.place(0, 0, 1, 1);
		_valuesPanel.add(_barcodeLabel, c);

		c.place(1, 0, 1, 1);
		_valuesPanel.add(_barcodeField, c);

		c.place(0, 1, 1, 1);
		_valuesPanel.add(_descriptionLabel, c);

		c.place(1, 1, 3, 1);
		_valuesPanel.add(_descriptionField, c);

		c.place(0, 2, 1, 1);
		_valuesPanel.add(_sizeLabel, c);

		c.place(1, 2, 1, 1);
		_valuesPanel.add(_sizeField, c);

		c.place(2, 2, 2, 1);
		_valuesPanel.add(_sizeBox, c);

		c.place(0, 3, 1, 1);
		_valuesPanel.add(_shelfLifeLabel, c);

		c.place(1, 3, 1, 1);
		_valuesPanel.add(_shelfLifeField, c);

		c.place(2, 3, 1, 1);
		_valuesPanel.add(_shelfLifeMonthsLabel, c);

		c.place(0, 4, 1, 1);
		_valuesPanel.add(_supplyLabel, c);

		c.place(1, 4, 1, 1);
		_valuesPanel.add(_supplyField, c);

		c.place(2, 4, 1, 1);
		_valuesPanel.add(_supplyCountLabel, c);
	}

	protected abstract void ok();

	public void setBarcode(String value)
	{
		boolean disabledEvents = disableEvents();
		try
		{
			_barcodeField.setText(value);
		}
		finally
		{
			if(disabledEvents)
			{
				enableEvents();
			}
		}
	}

	public void setDescription(String value)
	{
		boolean disabledEvents = disableEvents();
		try
		{
			_descriptionField.setText(value);
		}
		finally
		{
			if(disabledEvents)
			{
				enableEvents();
			}
		}
	}

	public void setShelfLife(String value)
	{
		boolean disabledEvents = disableEvents();
		try
		{
			_shelfLifeField.setText(value);
		}
		finally
		{
			if(disabledEvents)
			{
				enableEvents();
			}
		}
	}

	public void setSizeUnit(SizeUnits value)
	{
		boolean disabledEvents = disableEvents();
		try
		{
			_sizeBox.setSelectedItem(value);
		}
		finally
		{
			if(disabledEvents)
			{
				enableEvents();
			}
		}
	}

	public void setSizeValue(String value)
	{
		boolean disabledEvents = disableEvents();
		try
		{
			_sizeField.setText(value);
		}
		finally
		{
			if(disabledEvents)
			{
				enableEvents();
			}
		}
	}

	public void setSupply(String value)
	{
		boolean disabledEvents = disableEvents();
		try
		{
			_supplyField.setText(value);
		}
		finally
		{
			if(disabledEvents)
			{
				enableEvents();
			}
		}
	}

	protected abstract void valuesChanged();

}
