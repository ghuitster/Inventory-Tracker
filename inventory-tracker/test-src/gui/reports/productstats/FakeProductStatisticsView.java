
package gui.reports.productstats;

import gui.common.DialogBox;
import gui.common.DialogView;
import gui.common.FileFormat;
import gui.main.GUI;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class FakeProductStatisticsView extends DialogView implements
		IProductStatsReportView
{
	private JComboBox _formatBox;
	private JTextField _monthsField;
	protected JButton _okButton;

	public FakeProductStatisticsView(GUI parent, DialogBox dialog)
	{
		super(parent, dialog);
		_controller = new ProductStatsReportController(this);
		_formatBox = new JComboBox();
		_formatBox.addItem(FileFormat.PDF);
		_formatBox.addItem(FileFormat.HTML);
		_monthsField = new JTextField(4);
		_okButton = new JButton();
	}

	@Override
	public void enableFormat(boolean value)
	{
		_formatBox.setEnabled(value);
	}

	@Override
	public void enableMonths(boolean value)
	{
		_monthsField.setEnabled(value);
	}

	@Override
	public void enableOK(boolean value)
	{
		if(_okButton == null)
			_okButton = new JButton();

		_okButton.setEnabled(value);
	}

	public boolean getOKEnabledStatus()
	{
		return _okButton.isEnabled();
	}

	@Override
	public FileFormat getFormat()
	{
		return (FileFormat) _formatBox.getSelectedItem();
	}

	@Override
	public String getMonths()
	{
		return _monthsField.getText();
	}

	@Override
	public void setFormat(FileFormat value)
	{
		if(_formatBox == null)
		{
			_formatBox = new JComboBox();
			_formatBox.addItem(FileFormat.PDF);
			_formatBox.addItem(FileFormat.HTML);
		}

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

	@Override
	public void setMonths(String value)
	{
		if(_monthsField == null)
			_monthsField = new JTextField(4);

		_monthsField.setText(value);
	}
}
