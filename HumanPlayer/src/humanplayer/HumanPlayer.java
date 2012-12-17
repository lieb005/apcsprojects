/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package humanplayer;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Parlebster Software, NFC
 */
public class HumanPlayer extends Critter
{

    public static final boolean MAC = true;
    private int count = 0;
    URL wd;
    //PrintWriter console = System.console().writer();

    public HumanPlayer ()
    {
        //Runtime.getRuntime ().exec ("say A black person? That's racist!");
        try
        {
            Runtime.getRuntime ().exec ("say Mark and Ben, Ready! PREPARE YOURSELF!! Everybody else give up hope now, because we already win.");
            wd = new URL ("./");
        }
        catch (Exception e)
        {
        }
        if (wd != null)
        {
            System.out.println (wd.toExternalForm ());
        }
        else
        {
        } //System.out.println (new JApplet ().getCodeBase ().toExternalForm ());

    }

    public HumanPlayer (int age)
    {
        this ();
    }

    @Override
    public void makeMove (Location loc)
    {

        try
        {
            if (MAC)
            {
                Runtime.getRuntime ().exec ("say Is there an echo in here?!");
                //Runtime.getRuntime ().exec ("say h!");
            }
            else
            {
                //Runtime.getRuntime ().exec ("wall TEST");
            }
        }
        catch (IOException ex)
        {
        }

        //System.exit(2);
        //Location locat = new Location ((int) (Math.random () * getGrid ().getNumRows ()), (int) (Math.random () * getGrid ().getNumRows ()));

        //super.moveTo (locat);
        //makeMove (loc);
        //processActors (getActors ());

        final Location location = loc;
        for (int i = 0; i < 64; i = (i + 1))
        //while (true)
        {
            new Thread (new Runnable ()
            {

                @Override
                public void run ()
                {
                    //while (true)
                    for (int j = 0; j < 64; j = (j + 1))
                    {
                        /*
                         * try { Runtime.getRuntime ().exec ("say TEST"); }
                         * catch (IOException ex) { Logger.getLogger
                         * (HumanPlayer.class.getName ()).log (Level.SEVERE,
                         * null, ex); }
                         */
                        //System.exit (1324253);
                        //console.print("lolllllol");
                        //while (true)
                        //{

                            Location l = new Location ((int) (Math.random () * getGrid ().getNumRows ()), (int) (Math.random () * getGrid ().getNumRows ()));
                            while (!(getGrid ().isValid (l)))
                            {
                                if (getGrid ().get (l) != null)
                                {
                                    break;
                                }
                                l = new Location ((int) (Math.random () * getGrid ().getNumRows ()), (int) (Math.random () * getGrid ().getNumRows ()));
                            }
                            //if (!(getGrid ().get (l) instanceof Critter))
                            //{
                            //    //getGrid ().get(l).removeSelfFromGrid ();
                            //    getGrid ().remove (l);
                            //    new ChildPlayer ().putSelfInGrid (getGrid (), l);
                            //    break;
                            //}
                            moveTo (l);
                            disperse ();
                        //}
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
    synchronized public void processActors (final ArrayList<Actor> actors)
    {
        new Thread (new Runnable ()
        {

            @Override
            synchronized public void run ()
            {
                int b = actors.size ();
                for (int n = 0; n < b; n++)
                {
                    if (!(actors.get (n) instanceof HumanPlayer) && !(actors.get (n) instanceof ChildPlayer))
                    {
                        actors.remove (n);
                        count++;
                        break;
                    }
                }
                if (count > 2)
                {
                    Location l = new Location ((int) (Math.random () * getGrid ().getNumRows ()), (int) (Math.random () * getGrid ().getNumRows ()));
                    while (!(getGrid ().isValid (l)) || !(getGrid ().get (l) instanceof Critter))
                    {
                        l = new Location ((int) (Math.random () * getGrid ().getNumRows ()), (int) (Math.random () * getGrid ().getNumRows ()));
                    }
                    //getGrid ().remove (l);
                    new HumanPlayer ().putSelfInGrid (getGrid (), l);
                    count = 0;
                    //int i = 0;
                    //while (actors.get (i) != null)
                    //{
                    //    i = (int) (Math.random () * actors.size ());
                    //}
                    //actors.get (i).removeSelfFromGrid ();
                }

                /*
                 * for (int i = 0; i < actors.size (); i++) { count = count +
                 * (actors.remove (i) == null ? 0 : 1); }
                 */
            }
        }).start ();

    }

    synchronized public void disperse ()
    {
        //Grid g = getGrid ();
        int w = getGrid ().getNumRows (), h = getGrid ().getNumCols ();
        for (int i = w - 1; i >= 0; i--)
        {
            for (int j = h - 1; j >= 0; j--)
            {
                if (getGrid ().get (new Location (i, j)) instanceof Critter)
                {
                    continue;
                }
                //getGrid ().remove (new Location (i, j));
                // GridWorld apparently doesn't like threads... rude.
                new ChildPlayer ().putSelfInGrid (getGrid (), new Location (i, j));
                //getGrid ().put (new Location (i, j), new ChildPlayer ());
            }
        }
    }

    @Override
    synchronized public ArrayList<Actor> getActors ()
    {
        ArrayList<Actor> actors = new ArrayList<Actor> ();
        ArrayList<Location> locs = getGrid ().getOccupiedLocations ();
        for (int i = 0; i < locs.size (); i++)
        {
            //new Critter ().removeSelfFromGrid ();
            actors.add (i, getGrid ().get (locs.get (i)));
        }
        return actors;
    }
}

class ChildPlayer extends Bug
{

    public ChildPlayer ()
    {        super ();
        try {
            Runtime.getRuntime ().exec ("say It's a boy!");
        } catch (IOException ex) {
            Logger.getLogger(ChildPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ChildPlayer (int i)
    {        super ();
        try {
            Runtime.getRuntime ().exec ("say It's a boy!");
        } catch (IOException ex) {
            Logger.getLogger(ChildPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    synchronized public void move ()
    {
        while (true)
        {
            new Thread (new Runnable ()
            {

                @Override
                synchronized public void run ()
                {

                    ArrayList<Location> locations = getGrid ().getOccupiedLocations ();
                    for (int j = 0; j < locations.size (); j++)
                    {
                        if ((getGrid ().get (locations.get (j)) instanceof HumanPlayer))// && !(getGrid ().get (locations.get (j)) instanceof ChildPlayer))
                        {
                            return;
                        }
                    }

                    Location l = new Location ((int) (Math.random () * getGrid ().getNumRows ()), (int) (Math.random () * getGrid ().getNumRows ()));
                    int d = 0;
                    while (true)
                    {
                        while ((!(getGrid ().isValid (l)) || getGrid ().get (l) instanceof Critter) && d < (getGrid ().getNumRows () * getGrid ().getNumRows ()))
                        {
                            l = new Location ((int) (Math.random () * getGrid ().getNumRows ()), (int) (Math.random () * getGrid ().getNumRows ()));
                            d++;
                        }
                        getGrid ().remove (l);
                        new HumanPlayer ().putSelfInGrid (getGrid (), l);
                        d = 0;
                    }
//      super.move ();
                }
            }).start ();
        }
    }

    @Override
    public boolean canMove ()
    {
        return true;
    }
}
