
package gui.productgroup;

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
public abstract class ProductGroupView extends DialogView
{

	private JPanel _addPanel;
	private JLabel _nameLabel;
	private JTextField _nameField;
	private JLabel _supplyLabel;
	private JTextField _supplyField;
	private JComboBox _supplyBox;
	private ButtonBankPanel _buttonsPanel;
	protected JButton _okButton;

	public ProductGroupView(GUI parent, DialogBox dialog)
	{
		super(parent, dialog);
	}

	protected abstract void cancel();

	private void createAddPanel()
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

		_addPanel = new JPanel();

		_nameLabel = new JLabel("Product Group Name:");

		_nameField = new JTextField(20);
		_nameField.addKeyListener(keyListener);

		_supplyLabel = new JLabel("3 Month Supply:");

		_supplyField = new JTextField(8);
		_supplyField.addKeyListener(keyListener);

		_supplyBox = new JComboBox();
		_supplyBox.addItem(SizeUnits.Count);
		_supplyBox.addItem(SizeUnits.Pounds);
		_supplyBox.addItem(SizeUnits.Ounces);
		_supplyBox.addItem(SizeUnits.Grams);
		_supplyBox.addItem(SizeUnits.Kilograms);
		_supplyBox.addItem(SizeUnits.Gallons);
		_supplyBox.addItem(SizeUnits.Quarts);
		_supplyBox.addItem(SizeUnits.Pints);
		_supplyBox.addItem(SizeUnits.FluidOunces);
		_supplyBox.addItem(SizeUnits.Liters);
		_supplyBox.addActionListener(new ActionListener()
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
	}

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
		createAddPanel();
		createButtonsPanel();
	}

	public void enableOK(boolean value)
	{
		_okButton.setEnabled(value);
	}

	public void enableProductGroupName(boolean value)
	{
		_nameField.setEnabled(value);
	}

	public void enableSupplyUnit(boolean value)
	{
		_supplyBox.setEnabled(value);
	}

	public void enableSupplyValue(boolean value)
	{
		_supplyField.setEnabled(value);
	}

	public String getProductGroupName()
	{
		return _nameField.getText();
	}

	public SizeUnits getSupplyUnit()
	{
		return (SizeUnits) _supplyBox.getSelectedItem();
	}

	public String getSupplyValue()
	{
		return _supplyField.getText();
	}

	private void layoutAddPanel()
	{
		_addPanel.setLayout(new GridBagLayout());

		GridBagConstraintsExt c = new GridBagConstraintsExt();
		c.ipadx = 2;
		c.ipady = 2;
		c.insets = new Insets(5, 5, 5, 5);

		c.place(0, 0, 1, 1);
		_addPanel.add(_nameLabel, c);

		c.place(1, 0, 3, 1);
		_addPanel.add(_nameField, c);

		c.place(0, 1, 1, 1);
		_addPanel.add(_supplyLabel, c);

		c.place(1, 1, 1, 1);
		_addPanel.add(_supplyField, c);

		c.place(2, 1, 2, 1);
		_addPanel.add(_supplyBox, c);
	}

	@Override
	protected void layoutComponents()
	{
		layoutAddPanel();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(_addPanel);
		add(Box.createHorizontalStrut(5));
		add(_buttonsPanel);
	}

	protected abstract void ok();

	public void setProductGroupName(String value)
	{
		boolean disabledEvents = disableEvents();
		try
		{
			_nameField.setText(value);
		}
		finally
		{
			if(disabledEvents)
			{
				enableEvents();
			}
		}
	}

	public void setSupplyUnit(SizeUnits value)
	{
		boolean disabledEvents = disableEvents();
		try
		{
			_supplyBox.setSelectedItem(value);
		}
		finally
		{
			if(disabledEvents)
			{
				enableEvents();
			}
		}
	}

	public void setSupplyValue(String value)
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
