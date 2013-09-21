
package gui.item;

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
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import common.util.DateUtils;

@SuppressWarnings("serial")
public abstract class ItemView extends DialogView
{

	private JPanel _valuesPanel;
	private JLabel _descriptionLabel;
	private JTextField _descriptionField;
	private JLabel _barcodeLabel;
	private JTextField _barcodeField;
	private JLabel _entryDateLabel;
	private SpinnerModel _entryDateSpinnerModel;
	private JSpinner.DateEditor _entryDateSpinnerEditor;
	private JSpinner _entryDateSpinner;
	private ButtonBankPanel _buttonsPanel;
	protected JButton _okButton;

	public ItemView(GUI parent, DialogBox dialog)
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

		Date initDate = DateUtils.currentDate();
		Date latestDate = initDate;
		Date earliestDate = DateUtils.earliestDate();

		_valuesPanel = new JPanel();

		_descriptionLabel = new JLabel("Description:");

		_descriptionField = new JTextField(30);
		_descriptionField.addKeyListener(keyListener);

		_barcodeLabel = new JLabel("Item Barcode:");

		_barcodeField = new JTextField(18);
		_barcodeField.addKeyListener(keyListener);

		_entryDateLabel = new JLabel("Entry Date:");

		_entryDateSpinnerModel =
				new SpinnerDateModel(initDate, earliestDate, latestDate,
						Calendar.YEAR);
		_entryDateSpinner = new JSpinner(_entryDateSpinnerModel);
		_entryDateSpinnerEditor =
				new JSpinner.DateEditor(_entryDateSpinner,
						DateUtils.DATE_FORMAT);
		_entryDateSpinner.setEditor(_entryDateSpinnerEditor);
		_entryDateSpinnerEditor.getTextField().getDocument()
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
						if(_entryDateSpinnerEditor.getTextField().hasFocus())
						{
							valuesChanged();
						}
					}

					@Override
					public void removeUpdate(DocumentEvent e)
					{
						processChange(e);
					}
				});
		// _entryDateSpinner.addChangeListener(new ChangeListener() {
		// @Override
		// public void stateChanged(ChangeEvent arg0) {
		// if (eventsAreDisabled()) {
		// return;
		// }
		// valuesChanged();
		// }
		// });
	}

	public void enableBarcode(boolean value)
	{
		_barcodeField.setEnabled(value);
	}

	public void enableDescription(boolean value)
	{
		_descriptionField.setEnabled(value);
	}

	public void enableEntryDate(boolean value)
	{
		_entryDateSpinner.setEnabled(value);
	}

	public void enableOK(boolean value)
	{
		_okButton.setEnabled(value);
	}

	public String getBarcode()
	{
		return _barcodeField.getText();
	}

	public String getDescription()
	{
		return _descriptionField.getText();
	}

	public Date getEntryDate()
	{

		// return (Date)_entryDateSpinnerModel.getValue();

		String entryDateText = _entryDateSpinnerEditor.getTextField().getText();
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
		_valuesPanel.add(_descriptionLabel, c);

		c.place(1, 0, 3, 1);
		_valuesPanel.add(_descriptionField, c);

		c.place(0, 1, 1, 1);
		_valuesPanel.add(_barcodeLabel, c);

		c.place(1, 1, 1, 1);
		_valuesPanel.add(_barcodeField, c);

		c.place(0, 2, 1, 1);
		_valuesPanel.add(_entryDateLabel, c);

		c.place(1, 2, 1, 1);
		_valuesPanel.add(_entryDateSpinner, c);
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

	public void setEntryDate(Date value)
	{
		boolean disabledEvents = disableEvents();
		try
		{
			_entryDateSpinnerModel.setValue(value);
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
