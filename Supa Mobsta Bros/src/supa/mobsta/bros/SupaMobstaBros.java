/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supa.mobsta.bros;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import javax.swing.Timer;

/**
 *
 * @author 314Chan Telecommunications, LLC.
 */
public class SupaMobstaBros extends Canvas implements KeyListener, ActionListener
{

	/**
	 *
	 */
	public static final int TILE_WIDTH = 32,
			/**
			 *
			 */
			SCREEN_WIDTH = 12,
			/**
			 *
			 */
			TILE_HEIGHT = TILE_WIDTH,
			/**
			 *
			 */
			SCREEN_HEIGHT = 12;
	private World currWorld;
	private BufferedImage view;
	private int currLevel = 0;
	/**
	 *
	 */
	public final static boolean DEBUG = true;
	/**
	 *
	 */
	public static final int RUN = 8,
			/**
			 *
			 */
			WALK = 4;
	private final static int time = 1000 / 24;
	private Timer drawer = new Timer (time, this);

	SupaMobstaBros () throws FileNotFoundException
	{
		this (Worlds.Example.getFileName ());
	}

	SupaMobstaBros (Worlds world) throws FileNotFoundException
	{
		this (world.getFileName ());
	}

	SupaMobstaBros (World world)
	{
		setPreferredSize (new Dimension (SCREEN_WIDTH * TILE_WIDTH, SCREEN_HEIGHT * TILE_HEIGHT));
		currWorld = world;
		repaint ();
		requestFocus ();
		drawer.start ();
	}

	/**
	 *
	 * @param string Level file to import
	 */
	SupaMobstaBros (String string) throws FileNotFoundException
	{
		this (new World (string));
	}

	/**
	 *
	 * @param ke
	 */
	@Override
	public void keyTyped (KeyEvent ke)
	{
	}

	/**
	 *
	 * @param ke
	 */
	@Override
	public void keyPressed (KeyEvent ke)
	{
		//System.out.println(ke.getKeyChar());
		switch (ke.getKeyCode ())
		{
			case KeyEvent.VK_LEFT:
				//move Left
				if ((ke.getModifiersEx () & KeyEvent.SHIFT_DOWN_MASK) != 0)
				{
					//run left
					currWorld.getLevel (currLevel).move (-RUN);
				}
				else
				{
					//walk Left
					currWorld.getLevel (currLevel).move (-WALK);
				}
				break;
			case KeyEvent.VK_RIGHT:
				//move Right
				if ((ke.getModifiersEx () & KeyEvent.SHIFT_DOWN_MASK) != 0)
				{
					//run Right
					currWorld.getLevel (currLevel).move (RUN);
				}
				else
				{
					//walk Right
					currWorld.getLevel (currLevel).move (WALK);
				}
				break;
			case KeyEvent.VK_DOWN:
				//duck or pipe
				break;
			case KeyEvent.VK_SPACE:
				currWorld.getLevel (currLevel).getTux ().jump ();
				break;
		}
		if (ke.getKeyChar () - 0x30 >= 0 && ke.getKeyChar () - 0x30 < 10)
		{
			currLevel = ke.getKeyChar () - 0x30;
		}
		if (currWorld.getLevel (currLevel).win)
		{
			currLevel++;
		}
		repaint ();
	}

	/**
	 *
	 * @param ke
	 */
	@Override
	public void keyReleased (KeyEvent ke)
	{
		switch (ke.getKeyCode ())
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
				//currWorld.getLevel(currLevel).getTux().fall();
				break;
		}
		repaint ();
	}

	/**
	 *
	 * @param g
	 */
	@Override
	public void paint (Graphics g)
	{
		super.paint (g);
		if (g == null)
		{
			return;
		}
		BufferedImage i = new BufferedImage (SCREEN_WIDTH * TILE_WIDTH, SCREEN_HEIGHT * TILE_HEIGHT, BufferedImage.TYPE_INT_ARGB_PRE);
		super.paint (i.getGraphics ());
		view = getView ();
		i.getGraphics ().drawImage (view, 0, 0, null);

		g.drawImage (i, 0, 0, null);
		/*
		 * if (DEBUG)
		 * {
		 * JFrame f = new JFrame("Full Level");
		 * f.setSize(600, 600);
		 * f.add(new Canvas()
		 * {
		 * @Override
		 * public void paint(Graphics g)
		 * {
		 * super.paint(g);
		 * g.drawImage(view, 0, 0, null);
		 * }
		 * });
		 * f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 * f.pack();
		 * f.setVisible(true);
		 * }
		 */
	}

	/**
	 *
	 * @param g
	 */
	@Override
	public void update (Graphics g)
	{
		BufferedImage i = new BufferedImage (g.getClipBounds ().width, g.getClipBounds ().height, BufferedImage.TYPE_INT_ARGB_PRE);
		super.update (i.getGraphics ());
		g.drawImage (i, 0, 0, null);
	}

	/**
	 *
	 * @return
	 */
	public BufferedImage getView ()
	{
		return currWorld.getLevel (currLevel).getView ();
	}

	/**
	 *
	 */
	public static void lose ()
	{
		System.out.println ("You Lose");
	}

	/**
	 *
	 * @param ae
	 */
	@Override
	public void actionPerformed (ActionEvent ae)
	{
		for (Player p : currWorld.getLevel (currLevel).getPlayers ())
		{
			if (p != null)
			{
				p.move ();
			}
		}
		repaint ();
	}
}
