/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supa.mobsta.bros;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import supa.mobsta.goodguys.AlCapone;
import supa.mobsta.goodguys.BramCohen;
import supa.mobsta.goodguys.EndZone;
import supa.mobsta.goodguys.MobstaTux;

/**

 @author mark
 */
public abstract class Player
{
	//coordinates for the top-Left of the player in pixels

	private int x = 0, y = 0;
	// size of the player
	private int width = SupaMobstaBros.TILE_WIDTH, height = 0;
	// Image of ourselves
	private BufferedImage selfImage = null;
	//Frames for moving and powerups and stuff
	private Image[] frames;
	private int frame = 0;
	// Level we are in
	private Level currLevel;
	/**
	 only jump 5 blocks hight (from the top)


	 */
	public static final int JUMP_LENGTH = SupaMobstaBros.TILE_HEIGHT * 5;
	/**
	 can only jump 5 blocks horizontally


	 */
	public static final int JUMP_MAX = SupaMobstaBros.TILE_HEIGHT * 5;
	/**
	 the number of steps to do a jump in

	 */
	public static final int JUMP_STEPS = JUMP_LENGTH / MobstaTux.RUN;
	/**
	 Max Acceleration
	 */
	public static final int JUMP_ACCEL = -3;
	/**
	 Starting Velocity


	 */
	public static final int JUMP_VEL_START = 30;
	/**
	 Max Velocity


	 */
	public static final int JUMP_VEL_MAX = 30;
	/**
	 where to start the the jump


	 */
	public static final int JUMP_START = JUMP_MAX;
	// curent level on which we stand
	private int standy = 0;
	// Current step in a jump
	private boolean jumping = false;
	// velocity of the guy in a jump
	private double velocity = 0.0;
	// Speed of the player horizontally
	private double speed = 0.0;
	// current jump of player
	private int currJump = JUMP_START;
	// is the player facing forwards?
	public boolean direction = true;
	public static final double WALK = 3.0;

	public Player ()
	{
	}

	/**

	 @param currLevel
	 */
	public void setLevel (Level currLevel)
	{
		if (currLevel != null)
		{
			this.currLevel = currLevel;
		}
		else
		{
			this.currLevel = null;
		}

	}

	/**
	 This creates a new player of integer type "type". The key for types can
	 be found in codes.txt

	 @param type Type of Player to make ourselves

	 @return player The player of the specified type

	 @throws TypeNotPresentException
	 */
	public static Player createPlayer (int type) throws TypeNotPresentException
	{
		Player player = null;
		// Here we have to use the index to create a player of the proper type
		switch (type)
		{
			case 0:
				break;
			case 1:
				player = new AlCapone ();

				break;
			case 2:
				player = new BramCohen ();

				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				break;
			case 10:
				break;
			case 11:
				break;
			case 12:
				break;
			case 13:
				player = new EndZone ();
				break;
			case 15:
				player = new MobstaTux ();
				break;
			case 14:
			default:
				throw new TypeNotPresentException (String.valueOf (type), new Throwable ("This Type is not real!"));
		}
		return player;
	}

	/**
	 Returns X coordinate

	 @return X value
	 */
	public int getX ()
	{
		return x;
	}

	/**
	 Returns Y coordinate

	 @return Y value
	 */
	public int getY ()
	{
		return y;
	}

	/**

	 @param x
	 @param y
	 */
	public void setLocation (int x, int y)
	{
		setX (x);
		setY (y);
	}

	/**

	 @param x
	 */
	public void setX (int x)
	{
		this.x = x;
	}

	/**

	 @param y
	 */
	public void setY (int y)
	{
		this.y = y;
	}

	/**

	 @return
	 */
	public int getHeight ()
	{
		return height;
	}

	/**

	 @return
	 */
	public int getWidth ()
	{
		return width;
	}

	/**

	 @param width
	 @param height
	 */
	public void setSize (int width, int height)
	{
		setHeight (height);
		this.width = width;
	}

	public void setHeight (int height)
	{
		this.height = height;
	}

	/**

	 @return
	 */
	public BufferedImage getImage ()
	{
		if (getHeight () == 0)
		{
			setHeight (frames[0].getHeight (null) / SupaMobstaBros.TILE_HEIGHT);
		}
		if (selfImage != null)
		{
			return selfImage;
		}
		else
		{
			return (BufferedImage) setFrame (frame);
		}
	}

	/**

	 @return
	 */
	public Image[] getFrames ()
	{
		return frames;
	}

	/**

	 @param newFrames
	 */
	public void setFrames (Image[] newFrames)
	{
		frames = newFrames;
	}

	/**

	 @param frame

	 @return
	 */
	public Image setFrame (int frame)
	{
		this.frame = frame;
		selfImage = (BufferedImage) frames[frame];
		return selfImage;
	}

	/**

	 @return
	 */
	public int getFrame ()
	{
		return frame;
	}

	/**

	 @param file

	 @return

	 @throws IOException
	 */
	public static Image[] loadImage (String file) throws IOException
	{
		BufferedImage img = ImageIO.read (new File (file));
		Image[] tmp = new Image[img.getWidth () / SupaMobstaBros.TILE_WIDTH];
		for (int i = 0; i < (img.getWidth () / SupaMobstaBros.TILE_WIDTH); i++)
		{
			tmp[i] = img.getSubimage (i * SupaMobstaBros.TILE_WIDTH, 0, SupaMobstaBros.TILE_WIDTH, img.getHeight ());
		}
		return tmp;
	}

	/**

	 @return
	 */
	public int[][][] getTileCodes ()
	{
		if (currLevel != null)
		{
			return currLevel.getLevelCodes ();
		}
		else
		{
			//System.out.println (currLevelCodes);
			throw new NullPointerException ("LevelTiles NULL!");
		}
	}

	/**

	 */
	public void repaint ()
	{
		setFrame (frame);
	}

	/**

	 */
	public void jump ()
	{
		if (velocity == 0 && !currLevel.getSurroundings (getX (), getY (), getHeight (), this.getClass ().getName ())[0])
		{
			velocity = JUMP_VEL_START;
		}
	}

	public void fall ()
	{
		velocity = 0;
	}

	/**

	 */
	public void move ()
	{
		velocity -= JUMP_ACCEL;
		if (velocity < -JUMP_VEL_MAX)
		{
			velocity = -JUMP_VEL_MAX;
		}
		if (velocity > JUMP_VEL_MAX)
		{
			velocity = JUMP_VEL_MAX;
		}
		boolean surroundings[] = currLevel.getSurroundings (getX (), getY (), getHeight (), this.getClass ().getName ());
		if (!surroundings[2] && velocity <= 0)
		{
			setY ((int) (getY () + velocity));
		}
		if (surroundings[2] && velocity <= 0)
		{
			velocity = 0;
			// used to round down to the nearest block
			setY ((int) (getY () / SupaMobstaBros.TILE_HEIGHT) * SupaMobstaBros.TILE_HEIGHT);
		}
		if (surroundings[0] && velocity >= 0)
		{
			fall ();
		}
		//System.out.println (velocity + " new");
	}

	// can move left or right
	public boolean[] cantMove ()
	{
		boolean[] ret = new boolean[2];
		ret[0] = currLevel.getSurroundings (getX (), getY (), getHeight (), this.getClass ().getName ())[1];
		ret[1] = currLevel.getSurroundings (getX (), getY (), getHeight (), this.getClass ().getName ())[3];
		return ret;
	}

	public void setSpeed (double speed)
	{
		this.speed = speed;
	}

	public double getSpeed ()
	{
		return speed;
	}

	public void walk (boolean forward)
	{
		if (forward)
		{
			setSpeed (-WALK);
		}
		else
		{
			setSpeed (WALK);
		}
	}

	public void run (boolean forward)
	{
	}

	public void stop (boolean skid)
	{
		setSpeed (0.0);
	}

	public void step ()
	{
		if (getSpeed () == 0)
		{
			walk (true);
		}
		if (cantMove ()[0] && getSpeed () < 1)
		{
			direction = !direction;
			walk (direction);

			flipForward (false);
		}
		else if (!cantMove ()[0] && getSpeed () < 1)
		{
			setX (getX () + (int) getSpeed ());
		}
		else if (cantMove ()[1] && getSpeed () > 1)
		{
			direction = !direction;
			walk (direction);
			flipForward (true);
		}
		else if (!cantMove ()[1] && getSpeed () > 1)
		{
			setX (getX () + (int) getSpeed ());
		}
	}

	public void flipForward (boolean forward)
	{
		if (forward != direction)
		{
			int w = getWidth (), h = Math.max (getHeight () * SupaMobstaBros.TILE_HEIGHT, SupaMobstaBros.TILE_HEIGHT);
			BufferedImage buffer = new BufferedImage (w, h, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = buffer.createGraphics ();
			//g.drawImage(img, 0, 0, w, h, w, 0, 0, h, null);
			g.drawImage (frames[getFrame ()], 0, 0, w, h, w, 0, 0, h, null);
			g.dispose ();
			frames[getFrame ()] = buffer;
		}
	}
}
