
package gui.storageunit;

import gui.common.ButtonBankListener;
import gui.common.ButtonBankPanel;
import gui.common.DialogBox;
import gui.common.DialogView;
import gui.common.GridBagConstraintsExt;
import gui.main.GUI;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public abstract class StorageUnitView extends DialogView
{

	private JPanel _addPanel;
	private JLabel _nameLabel;
	private JTextField _nameField;
	private ButtonBankPanel _buttonsPanel;
	protected JButton _okButton;

	public StorageUnitView(GUI parent, DialogBox dialog)
	{
		super(parent, dialog);
	}

	protected abstract void cancel();

	private void createAddPanel()
	{
		_addPanel = new JPanel();

		_nameLabel = new JLabel("Storage Unit Name:");

		_nameField = new JTextField(20);
		_nameField.addKeyListener(new KeyListener()
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

	public void enableStorageUnitName(boolean value)
	{
		_nameField.setEnabled(value);
	}

	public String getStorageUnitName()
	{
		return _nameField.getText();
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

	public void setStorageUnitName(String value)
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

	protected abstract void valuesChanged();

}
