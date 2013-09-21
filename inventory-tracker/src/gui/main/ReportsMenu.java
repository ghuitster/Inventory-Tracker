
package gui.main;

import gui.common.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

@SuppressWarnings("serial")
public class ReportsMenu extends JMenu
{

	private IMainController _controller;
	private JMenuItem _expiredMenuItem;
	private JMenuItem _supplyMenuItem;
	private JMenuItem _removedMenuItem;
	private JMenuItem _productMenuItem;
	private JMenuItem _noticesMenuItem;

	public ReportsMenu(GUI parent)
	{
		super("Reports");

		this.addMenuListener(new MenuListener()
		{
			@Override
			public void menuCanceled(MenuEvent e)
			{
				return;
			}

			@Override
			public void menuDeselected(MenuEvent e)
			{
				return;
			}

			@Override
			public void menuSelected(MenuEvent e)
			{
				enableMenuItems();
			}
		});

		ActionListener actionListener = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if(evt.getSource() == _expiredMenuItem)
				{
					printExpiredReport();
				}
				else if(evt.getSource() == _supplyMenuItem)
				{
					printSupplyReport();
				}
				else if(evt.getSource() == _removedMenuItem)
				{
					printRemovedReport();
				}
				else if(evt.getSource() == _productMenuItem)
				{
					printProductReport();
				}
				else if(evt.getSource() == _noticesMenuItem)
				{
					printNoticesReport();
				}
			}
		};

		_expiredMenuItem = new JMenuItem("Expired Items");
		_expiredMenuItem.setFont(View.createFont(_expiredMenuItem.getFont(),
				View.MenuFontSize));
		_expiredMenuItem.addActionListener(actionListener);
		add(_expiredMenuItem);

		_removedMenuItem = new JMenuItem("Removed Items");
		_removedMenuItem.setFont(View.createFont(_removedMenuItem.getFont(),
				View.MenuFontSize));
		_removedMenuItem.addActionListener(actionListener);
		add(_removedMenuItem);

		_supplyMenuItem = new JMenuItem("N-Month Supply");
		_supplyMenuItem.setFont(View.createFont(_supplyMenuItem.getFont(),
				View.MenuFontSize));
		_supplyMenuItem.addActionListener(actionListener);
		add(_supplyMenuItem);

		_productMenuItem = new JMenuItem("Product Statistics");
		_productMenuItem.setFont(View.createFont(_productMenuItem.getFont(),
				View.MenuFontSize));
		_productMenuItem.addActionListener(actionListener);
		add(_productMenuItem);

		_noticesMenuItem = new JMenuItem("Notices");
		_noticesMenuItem.setFont(View.createFont(_noticesMenuItem.getFont(),
				View.MenuFontSize));
		_noticesMenuItem.addActionListener(actionListener);
		add(_noticesMenuItem);
	}

	private void enableMenuItems()
	{
		_expiredMenuItem.setEnabled(_controller.canPrintExpiredReport());
		_supplyMenuItem.setEnabled(_controller.canPrintSupplyReport());
		_removedMenuItem.setEnabled(_controller.canPrintRemovedReport());
		_productMenuItem.setEnabled(_controller.canPrintProductReport());
		_noticesMenuItem.setEnabled(_controller.canPrintNoticesReport());
	}

	//
	//
	//

	private void printExpiredReport()
	{
		_controller.printExpiredReport();
	}

	private void printNoticesReport()
	{
		_controller.printNoticesReport();
	}

	private void printProductReport()
	{
		_controller.printProductReport();
	}

	private void printRemovedReport()
	{
		_controller.printRemovedReport();
	}

	private void printSupplyReport()
	{
		_controller.printSupplyReport();
	}

	public void setController(IMainController value)
	{
		_controller = value;
	}

}
