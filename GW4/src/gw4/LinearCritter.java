/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gw4;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author mark
 */
public class LinearCritter extends Critter
{

	public LinearCritter ()
	{
		setDirection (180);
	}

	@Override
	public ArrayList<Actor> getActors ()
	{
		ArrayList<Actor> acts = new ArrayList<Actor> ();
		for (int i = 0; i < getGrid ().getNumRows (); i++)
		{
			Actor act = getGrid ().get (new Location (i, getLocation ().getCol ()));
			if ( act == null || act.equals (this) || act instanceof Rock || act instanceof Critter)
			{
				continue;
			}
			acts.add (act);
		}
		return acts;
	}

	@Override
	public void processActors (ArrayList<Actor> actors)
	{
		for (Actor a : getActors ())
		{
			a.removeSelfFromGrid ();
		}
	}

	@Override
	public Location selectMoveLocation (ArrayList<Location> locs)
	{
		return (new Location ((int)(getGrid ().getNumRows () * Math.random ()), getLocation ().getCol ()));
	}
}
