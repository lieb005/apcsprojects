/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supa.mobsta.bros;

import java.awt.Canvas;
import java.awt.Graphics;
import java.io.File;
import javax.swing.JApplet;
import javax.swing.JFrame;

/**
 *
 * @author mark
 */
public class SMB_Container extends JApplet
{

	private static String[] arg;
	SupaMobstaBros s;

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args)
	{
		arg = args;
		JFrame f = new JFrame("Supa Mobsta Bros");
		SMB_Container c = new SMB_Container();
		f.add(c);
		c.init();
		c.start();
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.validate();
		c.requestFocusInWindow();
	}

	@Override
	public void init()
	{
		super.init();
		if ((arg.length > 0) && new File(arg[0]).exists())
		{
			s = new SupaMobstaBros(arg[0]);
		}
		else
		{
			s = new SupaMobstaBros();
		}
		add(s);
		if (Level.DEBUG)
		{
			JFrame f = new JFrame("View");
			f.setSize(300, 300);
			f.add(new Canvas()
			{
				@Override
				public void paint(Graphics g)
				{
					super.paint(g);
					g.drawImage(s.getView(), 0, 0, null);

				}
			});
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.pack();
			f.setVisible(true);
		}
		repaint();
	}
}
