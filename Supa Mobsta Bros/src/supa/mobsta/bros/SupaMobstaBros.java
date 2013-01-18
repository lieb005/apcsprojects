/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supa.mobsta.bros;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import javax.swing.JPanel;

/**
 *
 * @author 314Chan Telecommunications, LLC.
 */
public class SupaMobstaBros extends JPanel implements KeyListener
{

	public static final int TILE_WIDTH = 32,
			SCREEN_WIDTH = 12,
			TILE_HEIGHT = 32,
			SCREEN_HEIGHT = 12;
	private World currWorld;
	private BufferedImage view;
	private int currLevel = 0;

	SupaMobstaBros()
	{
		this(Worlds.Example.getFileName());
	}

	/**
	 *
	 * @param string Level file to import
	 */
	SupaMobstaBros(String string)
	{
		setPreferredSize(new Dimension(SCREEN_WIDTH * TILE_WIDTH, SCREEN_HEIGHT * TILE_HEIGHT));
		try
		{
			currWorld = new World(string);
		} catch (FileNotFoundException ex)
		{
		}
	}

	@Override
	public void keyTyped(KeyEvent ke)
	{
	}

	@Override
	public void keyPressed(KeyEvent ke)
	{
		switch (ke.getKeyCode())
		{
			case KeyEvent.VK_LEFT:
				//move Left
				if ((ke.getModifiersEx() & KeyEvent.SHIFT_DOWN_MASK) != 0)
				{
					//run left
					currWorld.getLevel(currLevel).move(-5);
					view = currWorld.getLevel(currLevel).getView();
				}
				else
				{
					//walk Left
					currWorld.getLevel(currLevel).move(-2);
					view = currWorld.getLevel(currLevel).getView();
				}
				break;
			case KeyEvent.VK_RIGHT:
				//move Right
				if ((ke.getModifiersEx() & KeyEvent.SHIFT_DOWN_MASK) != 0)
				{
					//run Right
					currWorld.getLevel(currLevel).move(5);
					view = currWorld.getLevel(currLevel).getView();
				}
				else
				{
					//walk Right
					currWorld.getLevel(currLevel).move(3);
					view = currWorld.getLevel(currLevel).getView();
				}
				break;
			case KeyEvent.VK_DOWN:
				//duck or pipe
				break;

		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent ke)
	{
		switch (ke.getKeyCode())
		{
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_RIGHT:
				//stop
				break;
			case KeyEvent.VK_DOWN:
				//stand up
				break;
			case KeyEvent.VK_SPACE:
				// end Jump
				break;
		}
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		g.drawImage(view, 0, 0, null);
	}
}
