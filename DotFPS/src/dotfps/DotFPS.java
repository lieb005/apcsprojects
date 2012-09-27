/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dotfps;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.*;

/**
 *
 * @author mark
 */
public class DotFPS extends JApplet implements KeyListener, MouseListener
{

    public static final int gridSize = 32;
    public static final int gridX = 40, gridY = 10, windowX = 400, windowY = 400;
    static final int initLevel = 0;
    public static final int NONE = 0, UP = 1, LEFT = 2, RIGHT = 3, DOWN = 4;
    private int level = 0;
    private Stage bg;
    private int keyPressed = NONE;

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
        f.setSize (windowX, windowY);
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
        bg = Levels.getLevel (Levels.WOLRD1);
        bg.setSize (gridX * gridSize, gridY * gridSize);
        bg.addMouseListener (this);
        bg.addKeyListener (this);
        System.out.println (bg);
        JScrollPane scrollPane = new JScrollPane (bg);
        scrollPane.setHorizontalScrollBarPolicy (ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy (ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        add (scrollPane);
        invalidate ();
        validate ();
        repaint ();
        bg.repaint ();
    }

    @Override
    public void keyTyped (KeyEvent e)
    {
        System.out.println (e.getSource ());
    }

    @Override
    public void keyPressed (KeyEvent e)
    {
        switch (e.getKeyCode ())
        {
        case KeyEvent.VK_UP:
            bg.moveMaster (UP);
            break;
        case KeyEvent.VK_RIGHT:
            bg.moveMaster (RIGHT);
            break;
        case KeyEvent.VK_LEFT:
            bg.moveMaster (LEFT);
            break;
        case KeyEvent.VK_DOWN:
            bg.moveMaster (DOWN);
            break;
        }
        repaint ();
    }

    @Override
    public void keyReleased (KeyEvent e)
    {
        bg.stopMaster ();
        repaint ();
    }

    @Override
    public void mouseClicked (MouseEvent e)
    {
        bg.requestFocusInWindow ();

    }

    @Override
    public void mousePressed (MouseEvent e)
    {
        bg.requestFocusInWindow ();
    }

    @Override
    public void mouseReleased (MouseEvent e)
    {
        bg.requestFocusInWindow ();

    }

    @Override
    public void mouseEntered (MouseEvent e)
    {
    }

    @Override
    public void mouseExited (MouseEvent e)
    {
    }
}

abstract class Actor extends Canvas implements Runnable
{

    public final static int NONE = DotFPS.NONE, UP = DotFPS.UP, DOWN = DotFPS.DOWN, LEFT = DotFPS.LEFT, RIGHT = DotFPS.RIGHT;
    protected double currentX, currentY, startX, startY;
    public static final int ACTOR_SIZE = 31;
    protected int direction;
    protected Thread t;

    class EmptyActor extends Actor
    {

        @Override
        public void paint (Graphics g)
        {
            super.paint (g);
        }

        @Override
        public void run ()
        {
        }

        @Override
        public void move ()
        {
        }
    }

    public double getExactX ()
    {
        return currentX;
    }

    public double getExactY ()
    {
        return currentY;
    }

    @Override
    public int getX ()
    {
        return (int) getExactX ();
    }

    @Override
    public int getY ()
    {
        return (int) getExactY ();
    }

    void setDirection (int direction)
    {
        this.direction = direction;
    }

    abstract public void move ();
}

class Assassin extends Actor
{

    public static final int STEP = 1;
    private Color assassinColor = Color.BLACK;
    private Bullet bullet;

    public Assassin ()
    {
        this (Color.BLACK, 0, 0);
    }

    public Assassin (Color c)
    {
        this (c, 0, 0);
    }

    public Assassin (int x, int y)
    {
        this (Color.BLACK, 0, 0);
    }

    @SuppressWarnings("CallToThreadStartDuringObjectConstruction")
    public Assassin (Color c, int x, int y)
    {
        assassinColor = c;
        startX = x;
        startY = y;
        currentX = x;
        currentY = y;
        t = new Thread (this);
        t.start ();
    }

    public void draw (Graphics g)
    {
        super.paint (g);
        Color color = Color.WHITE;
        color = assassinColor;
        g.setColor (color);
        g.fillOval ((int) (currentX * (double) DotFPS.gridSize), (int) (currentY * (double) DotFPS.gridSize), ACTOR_SIZE, ACTOR_SIZE);
        g.setColor (Color.BLACK);
        g.drawOval ((int) (currentX * (double) DotFPS.gridSize), (int) (currentY * (double) DotFPS.gridSize), ACTOR_SIZE, ACTOR_SIZE);
    }

    void moveBullet (int x, int y)
    {
        bullet.setLocation (x, y);
    }

    void moveBullet (int direction)
    {
        bullet.setDirection (direction);
    }

    public void shoot ()
    {
        if (bullet == null)
        {
            bullet = new Bullet (getX (), getY ());
        }
    }

    public void move (int direction)
    {
        switch (direction)
        {
        case UP:
            currentY -= STEP;
        case LEFT:
            currentX -= STEP;
        case RIGHT:
            currentX += STEP;
        case DOWN:
            currentY += STEP;
        }
        repaint ();
    }

    @Override
    public void move ()
    {
        move (direction);
    }

    @Override
    public void run ()
    {
        while (true)
        {
            move ();
            try
            {
                Thread.sleep (100000000000L);
            }
            catch (InterruptedException ex)
            {
            }
            repaint ();
        }
    }
}

class Bullet extends Actor
{

    public static final double STEP = 0.8;
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

    public void move (int direction)
    {
        switch (direction)
        {
        case UP:
            setLocation ((int) (currentX), (int) (currentY - STEP));
        case LEFT:
            setLocation ((int) (currentX - STEP), (int) (currentY));
        case RIGHT:
            setLocation ((int) (currentX + STEP), (int) (currentY));
        case DOWN:
            setLocation ((int) (currentX), (int) (currentY + STEP));
        }
    }
@Override
    public void move ()
    {
        move (direction);
    }

    public void draw (Graphics g)
    {
        g.setColor (Color.BLACK);
        g.fillOval ((int) currentX, (int) currentY, BULLET_WIDTH, BULLET_HEIGHT);
    }

    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run ()
    {

        while (true)
        {
            move ();
            try
            {
                Thread.sleep (100);
            }
            catch (InterruptedException ex)
            {}
        }
    }
}

abstract class Stage extends JPanel
{

    Thread masterMoveThread;
    public final static int NONE = DotFPS.NONE, UP = DotFPS.UP, DOWN = DotFPS.DOWN, LEFT = DotFPS.LEFT, RIGHT = DotFPS.RIGHT;
    Vector<Assassin> killers = new Vector<Assassin> ();
    Assassin master;

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
        for (int i = 0; i < killers.size () - 1; i++)
        {
            if (killers.get (i).equals (a));
            {
                Point p = killers.get (i).getLocation ();
                return p;
            }
        }
        throw new NoComponentException ();
    }

    public Vector<Assassin> getAssassinVector ()
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

    public Assassin getMaster ()
    {
        return master;
    }

    public Assassin removeMaster ()
    {
        killers.remove (master);
        Assassin ret = master;
        master = null;
        return ret;
    }

    public void moveMaster (int direction)
    {
        final int dir = direction;
        masterMoveThread = new Thread (new Runnable ()
        {

            @Override
            public void run ()
            {
                while (true)
                {
                    master.move (dir);
                    System.out.println ("Moving " + dir);
                }
            }
        });
        masterMoveThread.start ();
    }

    public void stopMaster ()
    {
        master.move (NONE);
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
        master = new Assassin (Color.LIGHT_GRAY, masterX, masterY);
        add (master);
        for (int i = 0; i < enemyX.length; i++)
        {
            add (new Assassin (new Color ((int) (Math.random () * 256), (int) (Math.random () * 256), (int) (Math.random () * 256)), enemyX[i], enemyY[i]));
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
