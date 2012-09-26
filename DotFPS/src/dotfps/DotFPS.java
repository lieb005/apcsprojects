/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dotfps;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author mark
 */

public class DotFPS extends JApplet
{

    public static final int gridX = 40, gridY = 10, windowX = 400, windowY = 400;
    public static final int scaleX = windowX/gridX, scaleY = windowY/gridY;
    static final int initLevel = 0;
    int level = 0;

    /**
     * @param args the command line arguments
     */
    public static void main (String[] args)
    {
        DotFPS d = new DotFPS (initLevel);
        JFrame f = new JFrame ("Dot FPS");
        f.add (d);
        d.init ();
        d.start ();
        f.setSize (400, 400);
        f.setVisible (true);
        f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    }

    private DotFPS (int initLevel)
    {
        level = initLevel;
    }

    @Override
    public void init ()
    {
        super.init ();
        Stage bg = new Level (0, 0, new int[]
                {
                    2, 5, 7, 9, 12, 15, 17, 19, 21, 23, 26, 27
                }, new int[]
                {
                    1, 3, 7, 3, 9, 5, 2, 6, 9, 3, 7, 3
                });
        bg.add (new Assassin (true), 0, 0);
        bg.repaint ();
        add (bg);
        invalidate ();
        validate ();
        repaint ();
        System.out.println (bg.getSize ());
        System.out.println(new ArrayList<Assassin>(bg.getAssassinVector ()));

    }
}

abstract class Actor extends Canvas
{
    protected int currentX, currentY, startX, startY;
    public static final int ACTOR_SIZE = 31;

    class EmptyActor extends Actor
    {

        @Override
        public void paint (Graphics g)
        {
            super.paint (g);
        }
    }

}


class Assassin extends Actor
{

    Color assassinColor = Color.BLACK;
    boolean master = false;
    Bullet bullet;

    public Assassin ()
    {
        this (false);
    }

    public Assassin (Color c)
    {
        this (false, c);
    }

    public Assassin (boolean master)
    {
        this(master, Color.BLACK, 0, 0);
    }

    public Assassin (boolean master, Color c)
    {
        this (master, c, 0, 0);
    }

    public Assassin (boolean master, int x, int y)
    {
        this (master, Color.BLACK, 0, 0);
    }

    public Assassin (int x, int y)
    {
        this (false, Color.BLACK, 0, 0);
    }

    public Assassin (Color c, int x, int y)
    {
        this (false, c, 0, 0);
    }

    public Assassin (boolean master, Color c, int x, int y)
    {
        this.master =master;
        assassinColor = c;
        startX = x;
        startY = y;
        currentX = x;
        currentY = y;
    }
    public void draw (Graphics g)
    {
        super.paint (g);
        Color color = Color.WHITE;
        if (master)
        {
            color = Color.DARK_GRAY;
        }
        else
        {
            color = assassinColor;
        }
        g.setColor (color);
        g.fillOval (currentX*DotFPS.scaleX, currentY*DotFPS.scaleY, ACTOR_SIZE, ACTOR_SIZE);
        g.setColor (Color.BLACK);
        g.drawOval (currentX*DotFPS.scaleX, currentY*DotFPS.scaleY, ACTOR_SIZE, ACTOR_SIZE);
    }

    void moveBullet (int x, int y)
    {
        bullet.move (x, y);
    }

    public void shoot ()
    {
        if (bullet == null)
        {
            bullet = new Bullet (getX (), getY ());
        }
    }

    @Override
    public int getX ()
    {
        return currentX;
    }

    @Override
    public int getY ()
    {
        return currentY;
    }
}

class Bullet extends Actor
{

    Assassin parent;
    public final int BULLET_WIDTH = 7, BULLET_HEIGHT = 7;

    public Bullet (int startx, int starty)
    {
        startX = startx;
        startY = starty;
        currentX = startX;
        currentY = startY;
    }

    @Override
    public void setLocation (int x, int y)
    {
        currentX = x;
        currentY = y;
    }

    @Override
    public void move (int x, int y)
    {
        setLocation (x, y);
    }

    public void graw (Graphics g)
    {
        super.paint (g);
        g.setColor (Color.BLACK);
        g.fillOval (currentX, currentY, BULLET_WIDTH, BULLET_HEIGHT);
    }
}

class Level extends Stage
{

    int masterX, masterY;
    int[] enemyX, enemyY;

    public Level (int masterX, int masterY, int[] enemyX, int[] enemyY)
    {
        this.masterX = masterX;
        this.masterY = masterY;
        this.enemyX = enemyX;
        this.enemyY = enemyY;
        for (int i = 0; i < enemyX.length; i++)
        {
            add (new Assassin (false, new Color ((int) (Math.random () * 256), (int) (Math.random () * 256), (int) (Math.random () * 256))));
        }
    }

    public Point getMasterPos ()
    {
        return new Point (masterX, masterY);
    }

    public void setMasterPos (int masterX, int masterY)
    {
        this.masterX = masterX;
        this.masterY = masterY;
    }
}

class Stage extends JPanel
{

    Vector<Assassin> killers = new Vector<Assassin> ();

    public Stage ()
    {
        setLayout (null);
    }

    public void add (Assassin comp)
    {
        killers.add (comp);
    }

    public void remove (Assassin comp)
    {
        killers.remove (comp);
    }

    Point getLocationOf (Assassin a) throws NoComponentException
    {
        for (int i = 0; i < killers.size() - 1; i++)
        {
            if (killers.get(i).equals (a));
            {
                Point p = killers.get(i).getLocation ();
                return p;
            }
        }
        throw new NoComponentException ();
    }

    public Vector<Assassin> getAssassinVector()
    {
        return killers;
    }

    @Override
    public void paint (Graphics g)
    {
        super.paint (g);
        for (int i = 0; i < killers.size (); i++)
        {
            killers.get (i).draw (g);
        }
    }
}

class NoComponentException extends Exception
{

    Exception e = new ArrayIndexOutOfBoundsException ();

    public NoComponentException ()
    {
        super ();
    }

    public NoComponentException (String s)
    {
        super (s);
    }

    public NoComponentException (Throwable cause)
    {
        super (cause);
    }

    public NoComponentException (String s, Throwable cause)
    {
        super (s, cause);
    }
}
