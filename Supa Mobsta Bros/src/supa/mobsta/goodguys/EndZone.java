/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supa.mobsta.goodguys;

import java.awt.Canvas;
import java.awt.Graphics;
import java.io.IOException;
import javax.swing.JFrame;
import supa.mobsta.bros.Level;
import supa.mobsta.bros.SupaMobstaBros;

/**
 *
 * @author mark
 */
public class EndZone extends GoodGuy
{

	public EndZone()
	{
		try
		{
			setFrames(loadImage("src/supa/mobsta/img/EndZone.png"));
		} catch (IOException ex)
		{
			ex.printStackTrace();
		}
		if (Level.DEBUG)
		{
			JFrame f = new JFrame("EndZone");
			f.setSize(300, 300);
			f.add(new Canvas()
			{
				@Override
				public void paint(Graphics g)
				{
					super.paint(g);
					for (int i = 0; i < 1; i++)
					{
						g.drawImage(getFrames()[i], i * (SupaMobstaBros.TILE_WIDTH + 1), (SupaMobstaBros.TILE_WIDTH), null);
					}

				}
			});
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.pack();
			f.setVisible(true);
		}
	}
}
