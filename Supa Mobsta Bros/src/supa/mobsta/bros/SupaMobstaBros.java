/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supa.mobsta.bros;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	public static final int RUN = 16,
			/**
			 *
			 */
			WALK = 8;
	private final static int time = 1000 / 24;
	private Timer drawer = new Timer (time, this);
	// up, down, left, right, jump, shift
	private boolean[] keys = new boolean[]
	{
		false, false, false, false, false, false
	};
	private boolean oldSpace = false;

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
				keys[2] = true;
				break;
			case KeyEvent.VK_RIGHT:
				//move Right
				keys[3] = true;
				break;
			case KeyEvent.VK_DOWN:
				//duck or pipe
				keys[1] = true;
				break;
			case KeyEvent.VK_SPACE:
				oldSpace = keys[4];
				keys[4] = true;
				break;
		}
		keys[5] = ke.isShiftDown ();
		if (ke.getKeyChar () - 0x30 >= 0 && ke.getKeyChar () - 0x30 < 10)
		{
			currLevel = ke.getKeyChar () - 0x30;
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
				keys[2] = false;
				break;
			case KeyEvent.VK_RIGHT:
				keys[3] = false;
				break;
			case KeyEvent.VK_UP:
				//stand up
				keys[0] = true;
				break;
			case KeyEvent.VK_DOWN:
				//stand up
				keys[1] = true;
				break;
			case KeyEvent.VK_SPACE:
				// end Jump
				oldSpace = keys[4];
				keys[4] = false;
				break;
		}
		keys[5] = ke.isShiftDown ();
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
		for (Window w : JFrame.getOwnerlessWindows ())
		{
			if (w.getName ().equals ("lose"))
			{
				return;
			}
		}
		JFrame f = new JFrame ("You Lose.");
		JLabel l = new JLabel ("You Lose.");
		// I set the name so that we don't create more than one of these windows
		f.setName ("lose");
		l.setFont (new Font ("Ariel", Font.BOLD, 100 / 6));
		f.setSize (100, 100);
		f.add (l);
		f.setVisible (true);

		System.out.println ("You Lose.");
	}

	/**
	 *
	 */
	public static void win ()
	{
		for (Window w : JFrame.getOwnerlessWindows ())
		{
			if (w.getName ().equals ("win"))
			{
				return;
			}
		}
		JFrame f = new JFrame ("You Won!");
		JLabel l = new JLabel ("You Won!");
		// I set the name so that we don't create more than one of these windows
		f.setName ("win");
		l.setFont (new Font ("Ariel", Font.BOLD, 100 / 6));
		f.setSize (100, 100);
		f.add (l);
		f.setVisible (true);
		System.out.println ("You Win!");
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
		if (currWorld.getLevel (currLevel).win)
		{
			win ();
		}
		if (currWorld.getLevel (currLevel).lose)
		{
			lose ();
		}
		repaint ();
		//up
		if (keys[0]);
		//down
		if (keys[1]);
		//left
		if (keys[2])
		{
			if (keys[5])
			{
				//run left
				currWorld.getLevel (currLevel).move (-RUN);
			}
			else
			{
				//walk Left
				currWorld.getLevel (currLevel).move (-WALK);
			}
		}
		//right
		if (keys[3])
		{
			if (keys[5])
			{
				//run Right
				currWorld.getLevel (currLevel).move (RUN);
			}
			else
			{
				//walk Right
				currWorld.getLevel (currLevel).move (WALK);
			}
		}
		//space
		if (keys[4])
		{
			System.out.println ("space pressed");
			currWorld.getLevel (currLevel).getTux ().jump ();
		}
		else if (oldSpace != keys[4])
		//else
		{
			System.out.println ("space not pressed");
			currWorld.getLevel (currLevel).getTux ().fall ();
		}
		if (currWorld.getLevel (currLevel).win)
		{
			currLevel++;
		}
	}
}
