
package gui.main;

import gui.common.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

@SuppressWarnings("serial")
public class SessionMenu extends JMenu
{

	private IMainController _controller;
	private GUI _parent;
	private JMenuItem _exitMenuItem;

	public SessionMenu(GUI parent)
	{
		super("Session");

		this._parent = parent;

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
				if(evt.getSource() == _exitMenuItem)
				{
					exit();
				}
			}
		};

		_exitMenuItem = new JMenuItem("Exit");
		_exitMenuItem.setFont(View.createFont(_exitMenuItem.getFont(),
				View.MenuFontSize));
		_exitMenuItem.addActionListener(actionListener);
		add(_exitMenuItem);
	}

	private void enableMenuItems()
	{
		_exitMenuItem.setEnabled(_controller.canExit());
	}

	//
	//
	//

	void exit()
	{
		_controller.exit();
		_parent.dispose();
		System.exit(0);
	}

	public void setController(IMainController value)
	{
		_controller = value;
	}

}
