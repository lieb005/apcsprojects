/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gw4;

import info.gridworld.actor.Bug;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;

/**
 *
 * @author mark
 */
public class FlowerCritter extends Bug
{

	boolean up = false;

	public FlowerCritter ()
	{
		setDirection (90);
	}

	@Override
	public void move ()
	{
		Location l = null;
		// is it facing up or down?
		if (getDirection () % 180 == 0)
		{
			// is it facing up?
			if ((getDirection ()) / 180 == 0)
			{
				l = new Location (getLocation ().getRow () - 1, getLocation ().getCol ());
			}
			//is it facing down?
			else if ((getDirection ()) / 180 == 1)
			{
				l = new Location (getLocation ().getRow () + 1, getLocation ().getCol ());
			}

		}
		// is it facing left or right?
		else
		{
			// right?
			if ((getDirection () - 90) / 180 == 0)
			{
				l = new Location (getLocation ().getRow (), getLocation ().getCol () + 1);
			}
			// left?
			else if ((getDirection () - 90) / 180 == 1)
			{
				l = new Location (getLocation ().getRow (), getLocation ().getCol ()  - 1);
			}
		}
		if (getGrid ().isValid (l))
		{
			if (getGrid ().get (l) instanceof Flower)
			{
				turn ();
			}
		}

		super.move ();
	}

	@Override
	public void turn ()
	{
		setDirection (getDirection () + Location.RIGHT);
	}
}
