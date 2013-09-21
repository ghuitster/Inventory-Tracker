
package gui.reports.expired;

import gui.common.ButtonBankListener;
import gui.common.ButtonBankPanel;
import gui.common.DialogBox;
import gui.common.DialogView;
import gui.common.FileFormat;
import gui.common.GridBagConstraintsExt;
import gui.main.GUI;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ExpiredReportView extends DialogView implements IExpiredReportView
{

	private JPanel _valuesPanel;
	private JLabel _formatLabel;
	private JComboBox _formatBox;
	private ButtonBankPanel _buttonsPanel;
	protected JButton _okButton;

	public ExpiredReportView(GUI parent, DialogBox dialog)
	{
		super(parent, dialog);

		construct();

		_controller = new ExpiredReportController(this);
	}

	private void cancel()
	{
		return;
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
		createValuesPanel();
		createButtonsPanel();
	}

	private void createValuesPanel()
	{
		_valuesPanel = new JPanel();

		_formatLabel = new JLabel("Format:");

		_formatBox = new JComboBox();
		_formatBox.addItem(FileFormat.PDF);
		_formatBox.addItem(FileFormat.HTML);
		_formatBox.addActionListener(new ActionListener()
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

	@Override
	public void enableFormat(boolean value)
	{
		_formatBox.setEnabled(value);
	}

	@Override
	public void enableOK(boolean value)
	{
		_okButton.setEnabled(value);
	}

	@Override
	public IExpiredReportController getController()
	{
		return (IExpiredReportController) super.getController();
	}

	@Override
	public FileFormat getFormat()
	{
		return (FileFormat) _formatBox.getSelectedItem();
	}

	@Override
	protected void layoutComponents()
	{
		layoutValuesPanel();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createVerticalStrut(15));
		add(_valuesPanel);
		add(Box.createVerticalStrut(15));
		add(_buttonsPanel);
		add(Box.createVerticalStrut(15));
	}

	private void layoutValuesPanel()
	{
		_valuesPanel.setLayout(new GridBagLayout());

		GridBagConstraintsExt c = new GridBagConstraintsExt();
		c.ipadx = 2;
		c.ipady = 2;
		c.insets = new Insets(5, 5, 5, 5);

		c.place(0, 0, 1, 1);
		_valuesPanel.add(Box.createHorizontalStrut(20), c);

		c.place(1, 0, 1, 1);
		_valuesPanel.add(_formatLabel, c);

		c.place(2, 0, 3, 1);
		_valuesPanel.add(_formatBox, c);

		c.place(5, 0, 1, 1);
		_valuesPanel.add(Box.createHorizontalStrut(20), c);
	}

	private void ok()
	{
		getController().display();
	}

	@Override
	public void setFormat(FileFormat value)
	{
		boolean disabledEvents = disableEvents();
		try
		{
			_formatBox.setSelectedItem(value);
		}
		finally
		{
			if(disabledEvents)
			{
				enableEvents();
			}
		}
	}

	private void valuesChanged()
	{
		getController().valuesChanged();
	}

}
