/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supa.mobsta.bros;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import supa.mobsta.goodguys.EndZone;
import supa.mobsta.goodguys.MobstaTux;

/**
 *
 * @author mark
 */
public abstract class Player
{
	//coordinates for the top-Left of the player

	private int x = 0, y = 0;
	// size of the player
	private int width = SupaMobstaBros.TILE_WIDTH, height = SupaMobstaBros.TILE_HEIGHT;
	// Image of ourselves
	private BufferedImage selfImage = null;
	//Frames for moving and powerups and stuff
	private Image[] frames;
	private int frame = 0;
	// Level we are in
	private int[][][] currLevelCodes;
	/**
	 * only jump 5 blocks hight (from the top)
	 *
	 *
	 */
	public static final int JUMP_LENGTH = SupaMobstaBros.TILE_HEIGHT * 5;
	/**
	 * can only jump 5 blocks horizontally
	 *
	 *
	 */
	public static final int JUMP_MAX = SupaMobstaBros.TILE_HEIGHT * 5;
	/**
	 * the number of steps to do a jump in
	 *
	 */
	public static final int JUMP_STEPS = JUMP_LENGTH / SupaMobstaBros.RUN;
	/**
	 * Max Acceleration
	 */
	public static final int JUMP_ACCEL = 4;
	/**
	 * Starting Velocity
	 *
	 *
	 */
	public static final int JUMP_VEL_START = 20;
	/**
	 * Max Velocity
	 *
	 *
	 */
	public static final int JUMP_VEL_MAX = 50;
	/**
	 * where to start the the jump
	 *
	 *
	 */
	public static final int JUMP_START = JUMP_MAX;
	// curent level on which we stand
	private int standy = 0;
	// Current step in a jump
	private boolean jumping = false;
	// velocity of the guy
	private double velocity = 0;
	// current jump of player
	private int currJump = JUMP_START;

	/**
	 *
	 * @param currLevel
	 */
	public void setLevelTiles (int[][][] currLevel)
	{
		if (currLevel != null)
		{
			this.currLevelCodes = currLevel.clone ();
		}
		else
		{
			this.currLevelCodes = null;
		}

	}

	/**
	 * This creates a new player of integer type "type". The key for types can
	 * be found in codes.txt
	 *
	 * @param type Type of Player to make ourselves
	 *
	 * @return player The player of the specified type
	 *
	 * @throws TypeNotPresentException
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
				break;
			case 2:
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
	 * Returns X coordinate
	 *
	 * @return X value
	 */
	public int getX ()
	{
		return x;
	}

	/**
	 * Returns Y coordinate
	 *
	 * @return Y value
	 */
	public int getY ()
	{
		return y;
	}

	/**
	 *
	 * @param x
	 * @param y
	 */
	public void setLocation (int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 *
	 * @param x
	 */
	public void setX (int x)
	{
		this.x = x;
	}

	/**
	 *
	 * @param y
	 */
	public void setY (int y)
	{
		this.y = y;
	}

	/**
	 *
	 * @return
	 */
	public int getHeight ()
	{
		return height;
	}

	/**
	 *
	 * @return
	 */
	public int getWidth ()
	{
		return width;
	}

	/**
	 *
	 * @param width
	 * @param height
	 */
	public void setSize (int width, int height)
	{
		this.height = height;
		this.width = width;
	}

	/**
	 *
	 * @return
	 */
	public BufferedImage getImage ()
	{
		height = frames[0].getHeight (null);
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
	 *
	 * @return
	 */
	public Image[] getFrames ()
	{
		return frames;
	}

	/**
	 *
	 * @param newFrames
	 */
	public void setFrames (Image[] newFrames)
	{
		frames = newFrames;
	}

	/**
	 *
	 * @param frame
	 *
	 * @return
	 */
	public Image setFrame (int frame)
	{
		this.frame = frame;
		selfImage = (BufferedImage) frames[frame];
		return selfImage;
	}

	/**
	 *
	 * @return
	 */
	public int getFrame ()
	{
		return frame;
	}

	/**
	 *
	 * @param file
	 *
	 * @return
	 *
	 * @throws IOException
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
	 *
	 * @return
	 */
	public int[][][] getTileCodes ()
	{
		if (currLevelCodes != null)
		{
			return currLevelCodes.clone ();
		}
		else
		{
			//System.out.println (currLevelCodes);
			throw new NullPointerException ("LevelTiles NULL!");
		}
	}

	/**
	 *
	 */
	public void repaint ()
	{
		setFrame (frame);
	}

	/**
	 *
	 */
	public void jump ()
	{
		if (velocity == 0 && !getSurroundings ()[0])
		{
			velocity = JUMP_VEL_START;
		}
		//jumping = true;
	}

	/**
	 *
	 */
	public void move ()
	{
		System.out.println (getSurroundings ()[2]);
		if (!getSurroundings ()[2])
		{
			velocity += JUMP_ACCEL;
			if (velocity > 0);
			setY ((int) (getY () + velocity));
		}
	}

	private boolean[] getSurroundings ()
	{
		//System.out.println(currLevelCodes);
		// above, right, below, left
		boolean[] surrounds = new boolean[4];
		int[][][] tiles = getTileCodes ();
		if (getY () / SupaMobstaBros.TILE_HEIGHT >= SupaMobstaBros.TILE_HEIGHT)
		{
			surrounds[0] = (tiles[getX () / SupaMobstaBros.TILE_WIDTH][(getY () / SupaMobstaBros.TILE_HEIGHT) + (getHeight () / SupaMobstaBros.TILE_HEIGHT)][1] > 0);
		}
		if ((getX () / SupaMobstaBros.TILE_WIDTH) < tiles.length - 1)
		{
			surrounds[1] = (tiles[getX () / SupaMobstaBros.TILE_WIDTH][(getY () / SupaMobstaBros.TILE_HEIGHT) + (getHeight () / SupaMobstaBros.TILE_HEIGHT)][1] > 0);
		}
		surrounds[2] = (tiles[getX () / SupaMobstaBros.TILE_WIDTH][(getY () / SupaMobstaBros.TILE_HEIGHT) + (getHeight () / SupaMobstaBros.TILE_HEIGHT)][1] > 0);
		if (getX () / SupaMobstaBros.TILE_WIDTH - 1 > 0)
		{
			surrounds[3] = (tiles[getX () / SupaMobstaBros.TILE_WIDTH - 1][(getY () / SupaMobstaBros.TILE_HEIGHT) + (getHeight () / SupaMobstaBros.TILE_HEIGHT)][1] > 0);
		}
		for (boolean b : surrounds)
		{
			System.out.println (b);
		}
		return surrounds;
	}
}
