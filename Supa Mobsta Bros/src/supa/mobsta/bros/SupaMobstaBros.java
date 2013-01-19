/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supa.mobsta.bros;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

/**
 *
 * @author 314Chan Telecommunications, LLC.
 */
public class SupaMobstaBros extends Canvas implements KeyListener
{

	public static final int TILE_WIDTH = 32,
			SCREEN_WIDTH = 20,
			TILE_HEIGHT = TILE_WIDTH,
			SCREEN_HEIGHT = 12;
	private World currWorld;
	private BufferedImage view;
	private int currLevel = 0;
	public final static boolean DEBUG = true;

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
				}
				else
				{
					//walk Left
					currWorld.getLevel(currLevel).move(-2);
				}
				break;
			case KeyEvent.VK_RIGHT:
				//move Right
				if ((ke.getModifiersEx() & KeyEvent.SHIFT_DOWN_MASK) != 0)
				{
					//run Right
					currWorld.getLevel(currLevel).move(5);
				}
				else
				{
					//walk Right
					currWorld.getLevel(currLevel).move(3);
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
		view = getView();
		g.drawImage(view, 0, 0, null);
		/*if (DEBUG)
		 {
		 JFrame f = new JFrame("Full Level");
		 f.setSize(600, 600);
		 f.add(new Canvas()
		 {
		 @Override
		 public void paint(Graphics g)
		 {
		 super.paint(g);
		 g.drawImage(view, 0, 0, null);
		 }
		 });
		 f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 f.pack();
		 f.setVisible(true);
		 }*/
	}

	public BufferedImage getView()
	{
		return currWorld.getLevel(currLevel).getView();
	}
}
