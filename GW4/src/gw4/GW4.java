/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gw4;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;

/**
 *
 * @author mark
 */
public class GW4
{

	/**
	 * @param args the command line arguments
	 */
	public static void main (String[] args)
	{
		Grid g = new BoundedGrid (10, 10);
		ActorWorld w = new ActorWorld (g);
		w.setMessage ("ljdscc");
		w.add (new Location (0, 0), new FlowerCritter ());
		w.show ();
	}
}
