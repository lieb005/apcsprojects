/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gw4;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import java.util.ArrayList;

/**
 *
 * @author mark
 */
public class CornerCritter extends Critter
{

	@Override
	public ArrayList<Actor> getActors ()
	{
		ArrayList<Actor> ret = new ArrayList<Actor> ();
		ret.add (getGrid ().get (new Location (0, 0)));
		ret.add (getGrid ().get (new Location (getGrid ().getNumRows () - 1, 0)));
		ret.add (getGrid ().get (new Location (0, getGrid ().getNumCols () - 1)));
		ret.add (getGrid ().get (new Location (getGrid ().getNumRows () - 1, getGrid ().getNumCols () - 1)));
		return ret;
	}

	@Override
	public void processActors (ArrayList<Actor> actors)
	{
		for (Actor a : getActors ())
		{
			if (a == null || this.equals (a))
			{
				continue;
			}
			a.removeSelfFromGrid ();
		}
	}

	@Override
	public Location selectMoveLocation (ArrayList<Location> locs)
	{
		int place = (int) (4 * Math.random ());
		switch (place)
		{
			case 0:
				return new Location (0, 0);
			case 1:
				return new Location (0, getGrid ().getNumCols () - 1);

			case 2:
				return new Location (getGrid ().getNumRows () - 1, 0);
			case 3:
				return new Location (getGrid ().getNumRows () - 1, getGrid ().getNumCols () - 1);
			default:
				return null;
		}
	}
}
