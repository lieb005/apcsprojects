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

	public void setLevelTiles(int[][][] currLevel)
	{
		this.currLevelCodes = currLevel.clone();
	}

	/**
	 * This creates a new player of integer type "type". The key for types can
	 * be found in codes.txt
	 *
	 * @param type Type of Player to make ourselves
	 * @return player The player of the specified type
	 * @throws TypeNotPresentException
	 */
	public static Player createPlayer(int type) throws TypeNotPresentException
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
				player = new EndZone();
				break;
			case 15:
				player = new MobstaTux();
				break;
			case 14:
			default:
				throw new TypeNotPresentException(String.valueOf(type), new Throwable("This Type is not real!"));
		}
		return player;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public void setLocation(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public int getHeight()
	{
		return height;
	}

	public int getWidth()
	{
		return width;
	}

	public void setSize(int width, int height)
	{
		this.height = height;
		this.width = width;
	}

	public BufferedImage getImage()
	{
		height = frames[0].getHeight(null);
		if (selfImage != null)
		{
			return selfImage;
		}
		else
		{
			return (BufferedImage) setFrame(frame);
		}
	}

	public Image[] getFrames()
	{
		return frames;
	}

	public void setFrames(Image[] newFrames)
	{
		frames = newFrames;
	}

	/**
	 *
	 * @param frame
	 * @return
	 */
	public Image setFrame(int frame)
	{
		this.frame = frame;
		selfImage = (BufferedImage) frames[frame];
		return selfImage;
	}

	public int getFrame()
	{
		return frame;
	}

	public static Image[] loadImage(String file) throws IOException
	{
		BufferedImage img = ImageIO.read(new File(file));
		Image[] tmp = new Image[img.getWidth() / SupaMobstaBros.TILE_WIDTH];
		for (int i = 0; i < (img.getWidth() / SupaMobstaBros.TILE_WIDTH); i++)
		{
			tmp[i] = img.getSubimage(i * SupaMobstaBros.TILE_WIDTH, 0, SupaMobstaBros.TILE_WIDTH, img.getHeight());
		}
		return tmp;
	}

	public int[][][] getTileCodes()
	{
		return getTileCodes();
	}

	public void repaint()
	{
		setFrame(frame);
	}
}
