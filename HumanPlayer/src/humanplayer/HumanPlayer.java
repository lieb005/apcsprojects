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
 * @author Parlebster Software, NFC
 */
public class HumanPlayer extends Critter
{

    public static final boolean TROLL = false;
    private int count = 0;
    URL wd;
    //PrintWriter console = System.console().writer();

    public HumanPlayer ()
    {
        //Runtime.getRuntime ().exec ("say A black person? That's racist!");
        try
        {
            if (TROLL)
            {
                Runtime.getRuntime ().exec ("say Mark and Ben, Ready! PREPARE YOURSELF!! Everybody else give up hope now, because we already win.");
            }
        }
        catch (Exception e)
        {
        }
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
            if (TROLL)
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
        if (true)
        //for (int i = 0; i < 64; i = (i + 1))
        //while (true)
        {
            /*
             * new Thread (new Runnable () {
             *
             * @Override public void run () {
             */
            //while (true)
            //for (int j = 0; j < 64; j = (j + 1))
            if (true)
            {
                /*
                 * try { Runtime.getRuntime ().exec ("say TEST"); } catch
                 * (IOException ex) { Logger.getLogger
                 * (HumanPlayer.class.getName ()).log (Level.SEVERE, null, ex);
                 * }
                 */
                //System.exit (1324253);
                //console.print("lolllllol");
                //while (true)
                //{

                Location l = new Location ((int) (Math.random () * getGrid ().getNumRows ()), (int) (Math.random () * getGrid ().getNumRows ()));
                while (!(getGrid ().isValid (l)))
                {
                    if (getGrid ().get (l) == null)
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
            }
            // }
            // }).start ();
        }
        disperse ();
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
        /*
         * new Thread (new Runnable () {
         *
         * @Override synchronized public void run () {
         */
        int b = actors.size ();
        for (int n = 0; n < b; n++)
        {
            if (!(actors.get (n) instanceof HumanPlayer) && (actors.get (n) instanceof Critter))
            {
                getGrid ().remove (actors.get (n).getLocation ());
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
            new HumanPlayer ().putSelfInGrid (getGrid (), l);
            //getGrid ().remove (l);
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
        //}
        //}).start ();

    }

    synchronized public void disperse ()
    {
        new Thread (new Runnable ()
        {

            @Override
            synchronized public void run ()
            {
                while (true)
                {
                    //ArrayList<Location> locations = getGrid ().getOccupiedLocations ();
                    if (getGrid () != null)
                    {
                        for (int j = 0; j < getGrid ().getNumRows (); j++)
                        {
                            for (int k = 0; k < getGrid ().getNumCols (); k++)
                            {
                                if ((getGrid ().get (new Location (j, k)) instanceof HumanPlayer))// && !(getGrid ().get (locations.get (j)) instanceof ChildPlayer))
                                {
                                    return;
                                }
                            }
                        }
                    }

                    Location l = new Location ((int) (Math.random () * getGrid ().getNumRows ()), (int) (Math.random () * getGrid ().getNumRows ()));
                    int d = 0;
                    if (true) //while (true)
                    {
                        while ((!(getGrid ().isValid (l)) || getGrid ().get (l) instanceof Critter) && d < (getGrid ().getNumRows () * getGrid ().getNumRows ()))
                        {
                            l = new Location ((int) (Math.random () * getGrid ().getNumRows ()), (int) (Math.random () * getGrid ().getNumRows ()));
                            d++;
                        }
                        //getGrid ().remove (l);
                        getGrid ().put (l, new HumanPlayer ());
                        //new HumanPlayer ().putSelfInGrid (getGrid (), l);
                        d = 0;
                    }
                }
            }
        }).start ();
        /*
         * //Grid g = getGrid (); int w = getGrid ().getNumRows (), h = getGrid
         * ().getNumCols (); for (int i = w - 1; i >= 0; i--) { for (int j = h -
         * 1; j >= 0; j--) { Actor a = getGrid ().get (new Location (i, j)); if
         * (a instanceof Critter || a instanceof HumanPlayer) { continue; }
         * //getGrid ().remove (new Location (i, j)); // GridWorld apparently
         * doesn't like threads... rude. getGrid ().put (new Location (i, j),
         * new ChildPlayer ()); //getGrid ().put (new Location (i, j), new
         * ChildPlayer ()); }
        }
         */
    }

    @Override
    synchronized public ArrayList<Actor> getActors ()
    {
        ArrayList<Actor> actors = new ArrayList<Actor> ();
        int w = getGrid ().getNumRows (), h = getGrid ().getNumCols ();
        for (int i = w - 1; i >= 0; i--)
        {
            for (int j = h - 1; j >= 0; j--)
            {
                if (getGrid ().get (new Location (i, j)) != null)
                {
                    actors.add (getGrid ().get (new Location (i, j)));
                }
            }
        }/*
         * for (int i = 0; i < locs.size (); i++) { //new Critter
         * ().removeSelfFromGrid (); actors.add (i, getGrid ().get (locs.get
         * (i))); }
         */
        return actors;
    }
}

class ChildPlayer extends Bug
{

    public ChildPlayer ()
    {
        super ();
        if (HumanPlayer.TROLL)
        {
            try
            {
                Runtime.getRuntime ().exec ("say It's a boy!");
            }
            catch (IOException ex)
            {
            }
        }
    }

    public ChildPlayer (int i)
    {
        this ();
    }

    @Override
    synchronized public void move ()
    {
        if (true) //while (true)
        {
        }
    }

    @Override
    public boolean canMove ()
    {
        return true;
    }
}
