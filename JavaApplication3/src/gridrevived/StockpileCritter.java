/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gridrevived;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author mark
 */
public class StockpileCritter extends Critter
{

	private int pile = 0;

	StockpileCritter (Color color)
	{
		super();
		setColor (color);
	}

	public void processActors (ArrayList<Actor> actors)
	{
		for (int i = 0;i < actors.size ();i++)
		{
			actors.get (i).removeSelfFromGrid ();
			pile++;
		}
		System.out.println (pile);
	}

	public void makeMove (Location loc)
	{
		super.makeMove (loc);
		pile--;
		if (pile < 1)
		{
			removeSelfFromGrid ();
		}
	}
}
