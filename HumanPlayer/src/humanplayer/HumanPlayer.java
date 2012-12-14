/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package humanplayer;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author mark
 */
public class HumanPlayer extends Critter
{

    public static final boolean MAC = false;
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
                Runtime.getRuntime ().exec ("say TEST");
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
        processActors (getActors ());

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
                        while (true)
                        {

                            Location l = new Location ((int) (Math.random () * getGrid ().getNumRows ()), (int) (Math.random () * getGrid ().getNumRows ()));
                            while (!(getGrid ().isValid (l)))
                            {
                                l = new Location ((int) (Math.random () * getGrid ().getNumRows ()), (int) (Math.random () * getGrid ().getNumRows ()));
                            }
                            if (!(getGrid ().get (l) instanceof Critter))
                            {
                                //getGrid ().get(l).removeSelfFromGrid ();
                                getGrid ().remove (l);
                                new ChildPlayer ().putSelfInGrid (getGrid (), l);
                                break;
                            }
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
        int b = actors.size ();
        for (int n = 0;; n++)
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
                            while (!(getGrid ().isValid (l)))
                            {
                                l = new Location ((int) (Math.random () * getGrid ().getNumRows ()), (int) (Math.random () * getGrid ().getNumRows ()));
                            }
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

    @Override
    public ArrayList<Actor> getActors ()
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

class Thing extends Critter
{

    public Thing ()
    {
    }
}

class ChildPlayer extends Bug
{

    public ChildPlayer ()
    {
        super ();
    }

    public ChildPlayer (int i)
    {
        super ();
    }

    @Override
    public void move ()
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
        while (!(getGrid ().isValid (l)))
        {
            l = new Location ((int) (Math.random () * getGrid ().getNumRows ()), (int) (Math.random () * getGrid ().getNumRows ()));
        }
        getGrid ().remove(l);        
        new HumanPlayer ().putSelfInGrid (getGrid (), l);
//      super.move ();
    }

    @Override
    public boolean canMove ()
    {
        return true;
    }
}
