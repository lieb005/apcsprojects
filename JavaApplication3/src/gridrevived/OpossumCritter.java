/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gridrevived;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author mark
 */
public class OpossumCritter extends Critter
{

	private int numStepsDead;

	public OpossumCritter ()
	{
		numStepsDead = 0;
		setColor (Color.ORANGE);
	}

	/**
	 * Whenever actors contains more foes than friends, this OpossumCritter
	 * plays dead.
	 * Postcondition: (1) The state of all actors in the grid other than this
	 * critter and the
	 * elements of actors is unchanged. (2) The location of this critter is
	 * unchanged.
	 *
	 * @param actors a group of actors to be processed
	 */
	public void processActors (ArrayList<Actor> actors)
	{
		int foes = 0, friends = 0;
		for (int i = 0; i < actors.size (); i++)
		{
			if (isFoe (actors.get (i)))
			{
				foes++;
			}
			else if (isFriend (actors.get (i)))
			{
				friends++;
			}
		}
		if (foes > friends)
		{
			setColor (Color.BLACK);
			numStepsDead++;
		}
		else
		{
			setColor (Color.ORANGE);
			numStepsDead = 0;
		}
	}

	/**
	 * Selects the location for the next move.
	 * Postcondition: (1) The returned location is an element of locs, this
	 * critter's current location,
	 * or null. (2) The state of all actors is unchanged.
	 *
	 * @param locs the possible locations for the next move
	 *
	 * @return the location that was selected for the next move, or null to
	 *            indicate
	 *
	 * that this OpossumCritter should be removed from the grid.
	 */
	public Location selectMoveLocation (ArrayList<Location> locs)
	{
		if (numStepsDead == 0)
		{
			return super.selectMoveLocation (locs);
		}
		else if (numStepsDead > 3)
		{
			return null;
		}
		else
		{
			return getLocation ();
		}
	}

	/**
	 * @param other the actor to check
	 *
	 * @return true if other is a friend; false otherwise
	 */
	private boolean isFriend (Actor other)
	{
		if (other == null)
		{
			return false;
		}

		return (other instanceof Rock);
	}

	/**
	 * @param other the actor to check
	 *
	 * @return true if other is a foe; false otherwise
	 */
	private boolean isFoe (Actor other)
	{
		if (other == null)
		{
			return false;
		}
		return (other instanceof Critter);
	}
}
