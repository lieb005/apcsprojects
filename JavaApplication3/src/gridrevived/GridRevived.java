/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gridrevived;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;

/**
 *
 * @author mark
 */
public class GridRevived
{

	/**
	 * @param args the command line arguments
	 */
	public static void main (String[] args)
	{
		Grid g = new BoundedGrid (10, 10);
		ActorWorld w = new ActorWorld (g);
		//w.add (new OpossumCritter ());
		w.add (new Critter ());
		w.add (new Critter ());
		w.add (new Critter ());
		w.add ( new Critter ());
		w.add (new Rock ());
		w.add (new Rock ());
		w.add ( new Rock ());
		w.add (new Rock ());
		w.add (new StockpileCritter (Color.GREEN));
		//w.add (new Location (3,3), new ZBug (ZBug.E));
		w.show ();

	}
}
