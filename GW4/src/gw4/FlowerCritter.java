/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gw4;

import info.gridworld.actor.Critter;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;

/**
 *
 * @author mark
 */
public class FlowerCritter extends Critter
{

	public FlowerCritter ()
	{
		setDirection (90);
	}

	public Location selectMoveLocation ()
	{
		System.out.println (getLocation ().toString ());
		if (getDirection () == 90)
		{
			Location l = new Location (getLocation ().getRow (), getLocation ().getCol () + 1);
			if (getLocation ().getCol () == getGrid ().getNumCols () - 1)
			{
				setDirection (270);
				return new Location (getLocation ().getRow () + 1, getLocation ().getCol ());
			}
			else if (getGrid ().isValid (l) && getGrid ().get (l) == null)
			{
				return l;
			}
		}
		else if (getDirection ()
				== 270)
		{
			Location l = new Location (getLocation ().getRow (), getLocation ().getCol () - 1);

			if (getLocation ().getCol () == 0)
			{
				setDirection (90);
				return new Location (getLocation ().getRow () + 1, getLocation ().getCol ());
			}
			else if (getGrid ().isValid (l) && getGrid ().get (l) == null)
			{
				return l;
			}
		}

		return null;
	}

	@Override
	public void makeMove (Location loc)
	{
		if (getLocation () == null)
		{
			return;
		}
		Location l = getLocation ();
		moveTo (selectMoveLocation ());
		getGrid ().put (l, new Flower ());
	}
}
