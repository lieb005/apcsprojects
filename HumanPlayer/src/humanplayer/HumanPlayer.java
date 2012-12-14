/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package humanplayer;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
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

    public HumanPlayer (int age)
    {
    }

    @Override
    public void makeMove (Location loc)
    {
        /*
         * MARK?!?!?!??!?!?!?!!
         * 
         * Exception in thread "AWT-EventQueue-0" java.lang.StackOverflowError
         *   at info.gridworld.grid.BoundedGrid.getNumRows(BoundedGrid.java:50)
         *    at info.gridworld.grid.BoundedGrid.isValid(BoundedGrid.java:62)
         * at info.gridworld.grid.BoundedGrid.get(BoundedGrid.java:87)
         * at info.gridworld.actor.Actor.moveTo(Actor.java:159)
         * at humanplayer.HumanPlayer.makeMove(HumanPlayer.java:33)
         * at humanplayer.HumanPlayer$1.run(HumanPlayer.java:52)
         */
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
                        if (count > 2)
                        {
                            ArrayList<Actor> actors = getGrid ().getNeighbors (getLocation ());
                            for (int i = 0; i < actors.size (); i++)
                            {
                                count = count + (actors.get (i) == null ? 0 : 1);
                            }
                        }
                        makeMove (location);
                    }
                }
            }.run ();
        }
    }

    /*
     * @Override public void moveTo (Location newLocation) { super.moveTo (new
     * Location(0, 0)); }
     */

    /*
     * @Override public void putSelfInGrid (Grid<Actor> gr, Location loc) {
     * //super.putSelfInGrid (gr, loc); }
     */
    @Override
    public void processActors (ArrayList<Actor> actors)
    {
        int n = actors.size ();

        if (count > 2)
        {
            while (actors.remove ((int) (Math.random () * actors.size ())) != null);
        }

        /*
         * for (int i = 0; i < actors.size (); i++) { count = count +
         * (actors.remove (i) == null ? 0 : 1); }
         */
        makeMove (new Location (getGrid ().getNumRows () - 1, getGrid ().getNumCols () - 1));
    }
}
