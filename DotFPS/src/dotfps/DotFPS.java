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
import java.util.regex.Pattern;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author mark
 */
public class DotFPS extends JApplet implements KeyListener, MouseListener
{

    public static final int gridSize = 32;
    public static final int gridX = 40, gridY = 10, windowX = 400, windowY = 400;
    static final int initLevel = 1;
    public static final int NONE = 0, UP = 1, LEFT = 2, RIGHT = 3, DOWN = 4;
    public final static boolean DEBUG = true;
    public final static boolean VERBOSE_DEBUG = false;
    private int level = 0;
    private Stage bg;

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
        bg = Levels.WOLRD1.getLevel ();
        bg.setSize (gridX * gridSize, gridY * gridSize);
        bg.setViewSize (getWidth (), getHeight ());
        bg.addMouseListener (this);
        bg.addKeyListener (this);
        bg.initMaster ();
        bg.initEnemies ();
        add (bg);
        invalidate ();
        validate ();
        repaint ();
        bg.repaint ();
        bg.requestFocusInWindow ();
    }

    @Override
    public void keyTyped (KeyEvent e)
    {
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
        default:
            e.consume ();
        }
        //bg.getMaster ().repaint ();
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

abstract class Actor extends Canvas
{

    public final static int NONE = DotFPS.NONE, UP = DotFPS.UP, DOWN = DotFPS.DOWN, LEFT = DotFPS.LEFT, RIGHT = DotFPS.RIGHT;
    protected volatile double currentX, currentY, startX, startY;
    public static final int ACTOR_SIZE = 31;
    protected int direction;
    protected int windowWidth, windowHeight;

    class EmptyActor extends Actor
    {

        @Override
        public void move ()
        {
        }
    }

    public synchronized double getExactX ()
    {
        return currentX;
    }

    public synchronized double getExactY ()
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

    public synchronized void setWindowSize (int width, int height)
    {
        windowWidth = width;
        windowHeight = height;
    }

    abstract public void move ();
}

class Assassin extends Actor
{

    public static final double STEP = .1;
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

    public Assassin (Color c, int x, int y)
    {
        assassinColor = c;
        startX = x;
        startY = y;
        currentX = x;
        currentY = y;
    }

    public synchronized void draw (Graphics g)
    {
        Color color;
        color = assassinColor;
        g.setColor (color);
        g.fillOval ((int) (currentX * (double) DotFPS.gridSize), (int) (currentY * (double) DotFPS.gridSize), ACTOR_SIZE, ACTOR_SIZE);
        g.setColor (Color.BLACK);
        g.drawOval ((int) (currentX * (double) DotFPS.gridSize), (int) (currentY * (double) DotFPS.gridSize), ACTOR_SIZE, ACTOR_SIZE);

        if (DotFPS.VERBOSE_DEBUG)
        {
            g.setColor (Color.RED);
            Pattern p;
            g.drawString (this.toString ().split ("canvas([0-9]{1,2}){1}", 1)[0].replaceAll ("[^0-9]", ""), (int) (currentX * (double) DotFPS.gridSize) + 7, (int) (currentY * (double) DotFPS.gridSize) + 14);
        }
    }

    void moveBullet (int x, int y)
    {
        bullet.setLocation (x, y);
    }

    void moveBullet (int direction)
    {
        bullet.setDirection (direction);
    }

    public synchronized void shoot (double slope)
    {
        if (bullet == null)
        {
            bullet = new Bullet (getExactX () + (ACTOR_SIZE / (2 * DotFPS.gridSize)), getExactY () + (ACTOR_SIZE / (2 * DotFPS.gridSize)), slope);
        }
        else
        {
            bullet.move ();
            if ((bullet.getExactX () < (0 - bullet.BULLET_WIDTH) || bullet.getExactX () > windowWidth) || (bullet.getExactY () < (0 - bullet.BULLET_HEIGHT) || bullet.getExactY () > windowHeight))
            {
                bullet = null;
            }
        }
    }

    @Override
    public void move ()
    {
        switch (direction)
        {
        case UP:
            currentY -= STEP;
            break;
        case LEFT:
            currentX -= STEP;
            break;
        case RIGHT:
            currentX += STEP;
            break;
        case DOWN:
            currentY += STEP;
            break;
        }
        if (bullet != null)
        {
            bullet.move ();
        }
    }

    public synchronized Bullet getBullet ()
    {
        return bullet;
    }
}

class Bullet extends Actor
{

    public static final double STEP = 0.1;
    Assassin parent;
    public final int BULLET_WIDTH = 7, BULLET_HEIGHT = 7;
    private double slope;

    public Bullet (double startx, double starty, double slope)
    {
        startX = startx - ((Math.signum (Math.atan (slope))*Math.cos (Math.atan (slope)) * (ACTOR_SIZE / 2.0)) / DotFPS.gridSize) + (ACTOR_SIZE / 2.0) / DotFPS.gridSize;
        startY = starty - ((Math.signum (Math.atan (slope))*Math.sin (Math.atan (slope)) * (ACTOR_SIZE / 2.0)) / DotFPS.gridSize) + (ACTOR_SIZE / 2.0) / DotFPS.gridSize;
        currentX = startX;
        currentY = startY;
        this.slope = slope;
    }

    @Override
    public void setLocation (int x, int y)
    {
        currentX = x;
        currentY = y;
    }

    public synchronized void move (double slope)
    {
        this.slope = slope;
        repaint ();
    }

    @Override
    public synchronized void move ()
    {
        currentX += (Math.abs (slope) / slope) * STEP;
        currentY += -(slope * STEP); // negative since screen is top to bottom
    }

    public synchronized void draw (Graphics g)
    {
        g.setColor (Color.BLACK);
        g.fillOval ((int) (currentX * DotFPS.gridSize), (int) (currentY * DotFPS.gridSize), BULLET_WIDTH, BULLET_HEIGHT);
    }
}

abstract class Stage extends JPanel
{

    Thread masterMoveThread;
    public final static int NONE = DotFPS.NONE, UP = DotFPS.UP, DOWN = DotFPS.DOWN, LEFT = DotFPS.LEFT, RIGHT = DotFPS.RIGHT;
    volatile Vector<Assassin> killers = new Vector<Assassin> ();
    volatile Assassin master;
    protected int windowWidth, windowHeight;
    Thread masterThread = new Thread (new Runnable ()
    {

        @Override
        public void run ()
        {
            while (true)
            {
                master.move ();
                repaint ();
                try
                {
                    Thread.sleep (50);
                }
                catch (InterruptedException ex)
                {
                }
            }
        }
    });
    Thread enemyThread = new Thread (new Runnable ()
    {

        @Override
        public void run ()
        {
            int index = 0;
            Assassin tempAssassin;
            while (true)
            {
                index = (int) (Math.random () * killers.size ());

                if (DotFPS.DEBUG)
                {
                    index = 5;
                }

                tempAssassin = killers.get (index);
                if (tempAssassin.equals (master))
                {
                    continue;
                }
                do
                {
                    double slope = ((master.getExactY () + Actor.ACTOR_SIZE / 2.0) - (tempAssassin.getExactY () + Actor.ACTOR_SIZE / 2.0)) / ((master.getExactX () + Actor.ACTOR_SIZE / 2.0) - (tempAssassin.getExactX () + Actor.ACTOR_SIZE / 2.0));
                    tempAssassin.shoot (slope);
                    if (DotFPS.VERBOSE_DEBUG)
                    {
                        System.out.println (slope + "    " + tempAssassin);
                    }
                    repaint ();
                    try
                    {
                        Thread.sleep (100);
                    }
                    catch (InterruptedException ex)
                    {
                    }
                }
                while (tempAssassin.getBullet () != null);
                try
                {
                    Thread.sleep (DotFPS.DEBUG?0:100);
                }
                catch (InterruptedException ex)
                {
                }
            }
        }
    });

    public synchronized void add (Assassin comp)
    {
        killers.add (comp);
    }

    public synchronized void remove (Assassin comp)
    {
        killers.remove (comp);
    }

    public Point getLocationOf (Assassin a) throws NoComponentException
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

    public synchronized Vector<Assassin> getAssassinVector ()
    {
        return killers;
    }

    @Override
    public synchronized void paint (Graphics g)
    {
        super.paint (g);
        for (int i = 0; i < killers.size (); i++)
        {
            killers.get (i).draw (g);
            if (killers.get (i).getBullet () != null)
            {
                killers.get (i).getBullet ().draw (g);
            }
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

    public void initMaster ()
    {
        masterThread.start ();
    }

    public void moveMaster (int direction)
    {
        master.setDirection (direction);
    }

    public void stopMaster ()
    {
        master.setDirection (NONE);
    }

    public void moveAll ()
    {
        for (int i = 0; i < killers.size () - 1; i++)
        {
            killers.get (i).move ();
        }
    }

    void initEnemies ()
    {
        enemyThread.start ();
    }

    public void setViewSize (int width, int height)
    {
        windowWidth = width;
        windowHeight = height;
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
