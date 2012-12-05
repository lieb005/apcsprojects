/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package humanplayer;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author mark
 */
public class HumanPlayer extends Critter
{

    private int count = 0;

    public HumanPlayer ()
    {
    }

    @Override
    public void makeMove (Location loc)
    {
        final Location location = loc;
        moveTo (new Location (0, 0));
        while (true)
        {
            new Runnable ()
            {

                @Override
                public void run ()
                {
                    while (true)
                    {
                        if (count >= 2)
                        {
                            for (int i = 0; i < getActors ().size (); i++)
                            {
                                getActors ().set (i, new HumanPlayer ());
                            }
                        }
                        else
                        {
                            ArrayList<Actor> actors = getGrid ().getNeighbors (getLocation ());
                            for (int i = 0; i < actors.size (); i++)
                            {
                                count = count + (actors.remove (i) == null ? 0 : 1);
                            }
                        }
                        makeMove (location);
                    }
                }
            }.run ();
        }
    }

    @Override
    public void moveTo (Location newLocation)
    {
        //super.moveTo (newLocation);
    }

    @Override
    public void putSelfInGrid (Grid<Actor> gr, Location loc)
    {
        //super.putSelfInGrid (gr, loc);
    }

    @Override
    public void processActors (ArrayList<Actor> actors)
    {
        int n = actors.size ();
        if (n == 0)
        {
            return;
        }
        for (int i = 0; i < actors.size (); i++)
        {
            count = count + (actors.remove (i) == null ? 0 : 1);
        }
        makeMove(getLocation ());
    }
}
