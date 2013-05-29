/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supa.mobsta.goodguys;

import java.awt.Canvas;
import java.awt.Graphics;
import java.io.IOException;
import javax.naming.OperationNotSupportedException;
import javax.swing.JFrame;
import supa.mobsta.bros.Level;
import supa.mobsta.bros.SupaMobstaBros;

/**

 @author mark
 */
public class MobstaTux extends GoodGuy
{

	/**
	 Speed at which to change Tux's location each step while running
	 */
	public static final int RUN = 12;
	/**
	 Speed at which to change Tux's location each step while walking
	 */
	public static final int WALK = 8;
	/**
	 Rate at which to decrease Tux's speed for each step of a stop
	 */
	public static final double STOP = .7;
	private boolean mainTuxness;

	/**

	 */
	public MobstaTux (boolean isIt_CouldItBe___ItIs__MainTux)
	{
		mainTuxness = isIt_CouldItBe___ItIs__MainTux;
		try
		{
			setFrames (loadImage ("src/supa/mobsta/img/Tux.png"));
			setFrame (0);
			setHeight (1);
			//setSize(32, 32);
		} catch (IOException ex)
		{
			ex.printStackTrace ();
		}
		if (Level.DEBUG)
		{
			JFrame f = new JFrame ("Tux");
			f.setSize (300, 300);
			f.add (new Canvas ()
			{
				@Override
				public void paint (Graphics g)
				{
					super.paint (g);
					for (int i = 0; i < 5; i++)
					{
						g.drawImage (getFrames ()[i], i * (SupaMobstaBros.TILE_WIDTH + 1), (SupaMobstaBros.TILE_WIDTH), null);
					}

				}
			});
			f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
			f.pack ();
			f.setVisible (true);
		}
	}

	public MobstaTux ()
	{
		this (true);
	}

	/**

	 */
	@Override
	public void repaint ()
	{
		super.repaint ();
		//move ();
	}

	public boolean isMainTux ()
	{
		return mainTuxness;
	}

	/**
	 Runs tux forward one step.

	 @param forward Move the character forward?
	 */
	@Override
	public void run (boolean forward)
	{
		if (forward)
		{
			setSpeed (RUN);
		}
		else
		{
			setSpeed (-RUN);
		}
		if (getFrame () == 1 || getFrame () == 2)
		{
			setFrame (getFrame () % 3);
		}
		if (getFrame () == 3 || getFrame () == 4)
		{
			setFrame (getFrame () % 7);
		}
	}

	@Override
	public void walk (boolean forward)
	{
		if (forward)
		{
			setSpeed (WALK);
		}
		else
		{
			setSpeed (-WALK);
		}
		if (getFrame () == 1 || getFrame () == 2)
		{
			setFrame (getFrame () % 3);
		}
		if (getFrame () == 3 || getFrame () == 4)
		{
			setFrame (getFrame () % 7);
		}
	}

	@Override
	public void stop (boolean skid)
	{
		if (skid)
		{
			setSpeed (0);
		}
		else
		{
			if (getSpeed () > STOP)
			{
				setSpeed ((getSpeed () + 0.5) / 2 - STOP);
			}
			else if (getSpeed () < -STOP)
			{
				setSpeed ((getSpeed () - 0.5) / 2 + STOP);
			}
		}
	}

	@Override
	public void step ()
	{
		if (cantMove ()[0] && getSpeed () < 1)
		{
			direction = !direction;
			setSpeed (-getSpeed ());
			flipForward (false);
			System.out.println ("left blocked");
		}
		else if (!cantMove ()[0] && getSpeed () < 1)
		{
			System.out.println ("left good");
			setX (getX () + (int) getSpeed ());
		}
		else if (cantMove ()[1] && getSpeed () > 1)
		{
			System.out.println ("right blocked");

			direction = !direction;
			setSpeed (-getSpeed ());
			flipForward (true);
		}
		else if (!cantMove ()[1] && getSpeed () > 1)
		{
			System.out.println ("right good  " + getSpeed ());

			setX (getX () + (int) getSpeed ());
			
		}
	}
}
