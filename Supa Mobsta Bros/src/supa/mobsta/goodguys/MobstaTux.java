/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supa.mobsta.goodguys;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JFrame;
import supa.mobsta.bros.Level;
import supa.mobsta.bros.SupaMobstaBros;

/**
 *
 * @author mark
 */
public class MobstaTux extends GoodGuy
{

	@Override
	public BufferedImage setFrame(int frame)
	{
		return null;
	}

	public MobstaTux()
	{
		try
		{
			setFrames(loadImage("src/supa/mobsta/img/Tux.png"));
		} catch (IOException ex)
		{
			ex.printStackTrace();
		}
		if (Level.DEBUG)
		{
			JFrame f = new JFrame("Tux");
			f.setSize(300, 300);
			f.add(new Canvas()
			{
				@Override
				public void paint(Graphics g)
				{
					super.paint(g);
					for (int i = 0; i < 5; i++)
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
