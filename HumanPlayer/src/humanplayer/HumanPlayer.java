/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package humanplayer;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mark
 */
public class HumanPlayer extends Critter
{

    private int count = 0;
    URL wd;
    //PrintWriter console = System.console().writer();

    public HumanPlayer ()
    {
        try
        {
            wd = new URL ("./");
        }
        catch (Exception e)
        {
        }
        if (wd != null)
        {
            System.out.println (wd.toExternalForm ());
        }
        else{} //System.out.println (new JApplet ().getCodeBase ().toExternalForm ());
    }

    public HumanPlayer (int age)
    {
        this ();
    }

    @Override
    public void makeMove (Location loc)
    {
        //System.exit(2);
        makeMove (new Location (getGrid ().getNumRows () - 1, getGrid ().getNumCols () - 1));
        //makeMove (loc);
        final Location location = loc;
        //for(int i = 0;i < 64;i = (i+1))
        while(true)
        {
            new Thread (new Runnable ()
            {

                @Override
                public void run ()
                {
                    while(true)
                    //for (int j = 0;j < 64;j = (j+1))
                    {
                        try
                        {
                            Runtime.getRuntime ().exec ("say TEST");
                        }
                        catch (IOException ex)
                        {
                            Logger.getLogger (HumanPlayer.class.getName()).log (Level.SEVERE, null, ex);
                        }
                        //System.exit (1324253);
                        //console.print("lolllllol");
                        if (count > 2)
                        {
                            /*
                             * ArrayList<Actor> actors = getGrid ().getNeighbors
                             * (getLocation ()); for (int i = 0; i < actors.size
                             * (); i++) { count = count + (actors.get (i) ==
                             * null ? 0 : 1); }
                             */
                            processActors (getActors ());
                        }
                    }
                }
            }).start ();
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
            int i = 0;
            while (actors.get (i) != null)
            {
                i = (int) (Math.random () * actors.size ());
            }
            actors.get (i).removeSelfFromGrid ();
        }

        /*
         * for (int i = 0; i < actors.size (); i++) { count = count +
         * (actors.remove (i) == null ? 0 : 1); }
         */

    }
}
