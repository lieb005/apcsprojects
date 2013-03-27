/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gridrevived;

import info.gridworld.actor.Actor;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mark
 */
public class GridChecker
{

//The grid to check; guaranteed never to be null
	private BoundedGrid<Actor> gr;

	/**
	 * @return an Actor in the grid gr with the most neighbors; null if no
	 *            actors in the grid.
	 */
	public Actor actorWithMostNeighbors ()
	{
		Location maxLoc = gr.getOccupiedLocations ().get (0);
		for (Location l : gr.getOccupiedLocations ())
		{
			if (gr.getNeighbors (l).size () > gr.getNeighbors (maxLoc).size ())
			{
				maxLoc = l;
			}
		}
		return gr.get (maxLoc);
	}

	/**
	 * Returns a list of all occupied locations in the grid gr that are within 2
	 * rows
	 * and 2 columns of loc. The object references in the returned list may
	 * appear in any order.
	 *
	 * @param loc a valid location in the grid gr
	 *
	 * @return a list of all occupied locations in the grid gr that are within 2
	 *            rows
	 *
	 * and 2 columns of loc.
	 */
	public List<Location> getOccupiedWithinTwo (Location loc)
	{
		List<Location> list = new ArrayList<Location> ();
		for (int i = loc.getCol () - 2; i <= loc.getCol () + 2; i++)
		{
			for (int j = loc.getRow () - 2; j <= loc.getRow () + 2; j++)
			{
				if (gr.isValid (new Location (j, i)))
				{
					list.add (new Location (j, i));
				}
			}
		}
		list.remove (loc);
		return list;
	}
// There may be instance variables, constructors, and methods that are not shown.
}
