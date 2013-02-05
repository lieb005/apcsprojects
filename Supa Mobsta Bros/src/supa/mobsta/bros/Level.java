/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supa.mobsta.bros;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import supa.mobsta.goodguys.MobstaTux;

/**
 *
 * @author mark
 */
public final class Level
{

	/**
	 *
	 */
	public static final int TILE_WIDTH = SupaMobstaBros.TILE_WIDTH,
			/**
			 *
			 */
			SCREEN_WIDTH = SupaMobstaBros.SCREEN_WIDTH,
			/**
			 *
			 */
			TILE_HEIGHT = SupaMobstaBros.TILE_HEIGHT,
			/**
			 *
			 */
			SCREEN_HEIGHT = SupaMobstaBros.SCREEN_HEIGHT;
	/**
	 *
	 */
	public static final String palletteFile = "src/supa/mobsta/img/pallette.png";
	private String name;
	private BufferedImage fullLevel, view;
	private Image[][] levelTiles;
	private int[][][] levelCodes;
	private Player[] players;
	private Player mainTux = null;
	private static Image[][] tiles = null;
	// current location of tux
	private int currX = 0;
	/**
	 *
	 */
	public static final boolean DEBUG = false;
	// have we gotten to the end yet?
	public boolean win = false;
	public boolean lose = false;

	/**
	 * This Creates a level from the specified Data with the given name.
	 *
	 * @param levelName The name to give the level
	 * @param level     The actual hex data for the level
	 */
	public Level (String levelName, String level)
	{
		name = levelName;
		System.out.println (name);
		try
		{
			if (tiles == null)
			{
				tiles = loadTiles ();
			}
			// here we have to parse out the level from the data
			while (level.startsWith ("\n"))
			{
				level = level.replaceFirst ("\n", "");
			}
			while (level.endsWith ("\n"))
			{
				level = level.substring (0, level.length () - 1);
			}
			level = level.trim ().toLowerCase ().replaceAll ("[ ]+", " ").replaceAll ("[\n]+", "\n");
			String[] lev = level.split ("\n");

			int[][][] rawTiles = new int[lev[0].split (" ").length][SupaMobstaBros.SCREEN_HEIGHT][3];
			if (lev.length != SupaMobstaBros.SCREEN_HEIGHT)
			{
				throw new IndexOutOfBoundsException ("Level Height Mismatch.  Found: " + lev.length + " Expected: " + SupaMobstaBros.SCREEN_HEIGHT);
			}
			int j, enemy, enemyY = 0, col, row;
			String line;
			for (int i = 0; i < SupaMobstaBros.SCREEN_HEIGHT; i++)
			{
				line = lev[i];
				if (line == null || line.isEmpty ())
				{
					continue;
				}
				String[] codes = line.split (" ");
				String code;
				for (j = 0; j < codes.length; j++)
				{
					code = codes[j];
					enemy = Integer.parseInt (code.substring (0, 1), 16);
					col = Integer.parseInt (code.substring (2), 16);
					row = Integer.parseInt (code.substring (1, 2), 16);
					rawTiles[j][i] = new int[]
					{
						enemy, col, row
					};
				}
			}
			levelCodes = rawTiles.clone ();
			if (DEBUG)
			{
				System.out.println ("This is what is in the Arrays");
				for (int k = 0; k < SupaMobstaBros.SCREEN_HEIGHT; k++)
				{
					for (int l = 0; l < rawTiles.length; l++)
					{
						System.out.print (rawTiles[l][k][0] + " " + rawTiles[l][k][1] + " " + rawTiles[l][k][2] + " ");
					}
					System.out.println ();
				}
			}
			//here we take all of the codes and turn them into tiles
			levelTiles = new Image[rawTiles.length][SCREEN_HEIGHT];
			Image[] currCol;
			players = new Player[rawTiles.length];
			for (int i = 0; i < rawTiles.length; i++)
			{
				currCol = new Image[SCREEN_HEIGHT];
				for (int u = 0; u < SCREEN_HEIGHT; u++)
				{
					currCol[u] = tiles[rawTiles[i][u][1]][rawTiles[i][u][2]];
				}
				enemy = 0;
				for (int n = 0; n < SCREEN_HEIGHT; n++)
				{
					if (rawTiles[i][n][0] != 0)
					{
						if (enemy == 0)
						{
							enemy = rawTiles[i][n][0];
							enemyY = n;
						}
						else
						{
							throw new Exception ("Too many Enemies in column " + n + ".  Please place only one.");
						}
					}
				}
				players[i] = Player.createPlayer (enemy);
				if (players[i] != null)
				{
					players[i].setLocation (i * TILE_WIDTH, enemyY * TILE_HEIGHT);
					players[i].setLevelTiles (levelCodes);
					if (players[i] instanceof MobstaTux && mainTux == null)
					{
						mainTux = players[i];
					}
				}
				levelTiles[i] = currCol;
			}
			if (DEBUG)
			{
				JFrame f = new JFrame (name);
				f.setSize (300, 300);
				f.add (new Canvas ()
				{
					@Override
					public void paint (Graphics g)
					{
						super.paint (g);
						for (int i = 0; i < levelTiles[0].length; i++)
						{
							for (int j = 0; j < levelTiles.length; j++)
							{
								g.drawImage (levelTiles[j][i], j * (TILE_WIDTH + 1), i * (TILE_WIDTH + 1), null);
							}
						}

					}
				});
				f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
				f.pack ();
				f.setVisible (true);
			}
			if (mainTux == null)
			{
				throw new NullPointerException ("MainTux null!");
			}
			else if (levelCodes == null)
			{
				throw new NullPointerException ("LevelCodes null!");
			}
		} catch (Exception ex)
		{
			System.out.println ("ERROR");
			ex.printStackTrace ();
		}
	}

	/**
	 *
	 * @return
	 */
	public Player[] getPlayers ()
	{
		return players.clone ();
	}

	/**
	 * Creates the Full Level image
	 *
	 * @return The full level Image
	 */
	public BufferedImage makeFull ()
	{
		fullLevel = new BufferedImage (levelTiles.length * TILE_WIDTH, TILE_HEIGHT * SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB_PRE);
		Graphics g = fullLevel.getGraphics ();
		g.setColor (Color.WHITE);
		g.fillRect (0, 0, fullLevel.getWidth (), fullLevel.getHeight ());
		for (int i = 0; i < levelTiles[0].length; i++)
		{
			for (int j = 0; j < levelTiles.length; j++)
			{
				g.drawImage (levelTiles[j][i], j * TILE_WIDTH, i * TILE_WIDTH, null);
			}
		}
		//fullLevel = full.getSubimage(0, 0, buffer.getWidth(), buffer.getHeight());
		if (DEBUG)
		{
			JFrame f = new JFrame ("Full Level");
			f.setSize (600, 600);
			f.add (new Canvas ()
			{
				@Override
				public void paint (Graphics g)
				{
					super.paint (g);
					g.drawImage (fullLevel, 0, 0, null);
				}
			});
			f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
			f.pack ();
			f.setVisible (true);
		}
		return fullLevel;
	}

	/**
	 * Returns the Full level image
	 *
	 * @return A full view of the level
	 */
	public BufferedImage getFull ()
	{
		return fullLevel;
	}

	/**
	 * Get a segment of the level
	 *
	 * @param startx
	 *
	 * @return The segment starting at location "startx"
	 */
	public BufferedImage getSegment (int startx)
	{
		BufferedImage ret;
		int x;
		x = Math.min (Math.max (startx, 0), getFull ().getWidth () - SCREEN_WIDTH * TILE_WIDTH);
		if (getFull ().getWidth () >= TILE_WIDTH * SCREEN_WIDTH)
		{
			if (getFull ().getWidth () - x >= TILE_WIDTH * SCREEN_WIDTH)
			{
				ret = getFull ().getSubimage (x, 0, TILE_WIDTH * SCREEN_WIDTH, TILE_HEIGHT * SCREEN_HEIGHT);
			}
			else
			{
				x = getFull ().getWidth () - TILE_WIDTH * SCREEN_WIDTH;
				ret = getFull ().getSubimage (x, 0, TILE_WIDTH * SCREEN_WIDTH, TILE_HEIGHT * SCREEN_HEIGHT);
			}
		}
		else
		{
			ret = getFull ();
		}
		/*
		 * // TODO need to make it so that the players draw in the right
		 * places,
		 * not off-screen
		 * for (int i = (x / TILE_WIDTH); i < ret.getWidth () / TILE_WIDTH; i++)
		 * {
		 * if (players[i] != null)
		 * {
		 * players[i].repaint ();
		 * }
		 * }
		 */
		//mainTux.setX(x + (SCREEN_WIDTH / 2) * TILE_WIDTH);
		return ret;
	}

	/**
	 * Loads the pallette into memory.
	 * This has to be assigned to something since it is static.
	 *
	 * @return The tiles that were loaded
	 */
	public static Image[][] loadTiles ()
	{
		Image[][] tiled = new Image[16][5];
		try
		{
			BufferedImage pallette = ImageIO.read (new File (palletteFile));
			//width x height
			for (int i = 0; i < 16; i++)
			{
				for (int j = 0; j < 5; j++)
				{
					tiled[i][j] = pallette.getSubimage (i * TILE_WIDTH, j * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
				}
			}
			if (DEBUG)
			{
				JFrame f = new JFrame ("Tiles");
				f.setSize (300, 300);
				f.add (new Canvas ()
				{
					@Override
					public void paint (Graphics g)
					{
						super.paint (g);
						for (int i = 0; i < 5; i++)
						{
							for (int j = 0; j < 16; j++)
							{
								g.drawImage (tiles[j][i], j * (TILE_WIDTH + 1), i * (TILE_WIDTH + 1), null);
							}
						}

					}
				});
				f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
				f.pack ();
				f.setVisible (true);
				System.out.println ("Loaded Tiles");
			}
		} catch (IOException ex)
		{
		}
		return tiled.clone ();
	}

	/**
	 * This moves the Level Image by the specified amount.
	 *
	 * @param amount Positive = move the view to the right, negative = right.
	 *
	 * @return The new View of the level(ish)
	 */
	public BufferedImage move (int amount)
	{/*
		 * if (Math.signum (amount) == -1)
		 * {
		 * if (mainTux.canMove ()[1])
		 * {
		 * if (!((getCurrX () + amount) < 0 && (getCurrX () + amount) > (getFull
		 * ().getWidth () - TILE_WIDTH)))
		 * {
		 * currX += amount;
		 * }
		 * }
		 * }
		 * else if (Math.signum (amount) == 1)
		 * {
		 * if (mainTux.canMove ()[0])
		 * {
		 */
		if (!((getCurrX () + amount) < 0 && (getCurrX () + amount) > (getFull ().getWidth () - TILE_WIDTH)))
		{
			currX += amount;
		}
		/*
		 * }
		 * }
		 */
		mainTux.setX (getCurrX ());
		// padded so that Tux is Centered
		if (getCurrX () + TILE_WIDTH >= getFull ().getWidth ())
		{
			win = true;
		}
		view = getSegment (getStartX ());
		return view;
	}

	/**
	 *
	 * @param x
	 */
	public void setLocation (int x)
	{
		currX = x;
		// so that we get the view of the level, not tux
		getSegment (getStartX ());
	}

	/**
	 *
	 * @return
	 */
	public BufferedImage getView ()
	{
		if (getFull () == null)
		{
			makeFull ();
		}
		view = move (0);
		BufferedImage buffer = new BufferedImage (TILE_WIDTH * SCREEN_WIDTH, TILE_HEIGHT * SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB_PRE);
		Graphics g = buffer.getGraphics ();
		g.drawImage (view, 0, 0, null);
		//g.drawImage(mainTux.getImage(), mainTux.getX(), mainTux.getY(), null);
		g.drawImage (drawPlayers (getStartX ()), 0, 0, null);
		return buffer.getSubimage (0, 0, buffer.getWidth (), buffer.getHeight ());
	}

	private BufferedImage drawPlayers (int startx)
	{
		BufferedImage ret = new BufferedImage (TILE_WIDTH * SCREEN_WIDTH, TILE_HEIGHT * SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB_PRE);
		Graphics g = ret.getGraphics ();
		if (DEBUG)
		{
			g.setColor (new Color (255, 255, 0, 100));
		}
		else
		{
			g.setColor (new Color (255, 255, 255, 0));
		}
		g.fillRect (0, 0, ret.getWidth (), ret.getHeight ());
		g.setColor (new Color (0, 0, 0, 255));
		Player player;
		for (int i = startx / TILE_WIDTH; i < ((startx + TILE_WIDTH - 1) / TILE_WIDTH) + SCREEN_WIDTH; i++)
		{
			player = players[i];
			if (player != null)
			{
				//player.setFrame(0);
				//if (player.getX() >= ((int) (getCurrX () / TILE_WIDTH) * TILE_WIDTH)*SCREEN_WIDTH && player.getX() < ((int) (getCurrX () / TILE_WIDTH + 1) * TILE_WIDTH)*SCREEN_WIDTH)
				//{

				// this number gets the location to draw the player at
					/*
				 * EnemyX = 6, startx = 4, screenw = 3
				 *
				 *  012
				 *  |||
				 * |  E|
				 * |---|
				 *  456
				 * startx = 4
				 * (Ex - startx) = 2
				 *
				 * | E |
				 * |---|
				 *  567
				 * startx = 5
				 * (Ex - startx) = 1
				 *
				 *
				 * |E  |
				 * |---|
				 *  678
				 * startx = 6
				 * (Ex - startx) = 0
				 */
				int dx = (player.getX () - getStartX ());
				g.drawImage (player.getImage (), dx, player.getY (), player.getWidth (), player.getHeight (), null);
				//}
			}
		}
		if (mainTux.getY () >= SCREEN_HEIGHT * TILE_HEIGHT - 1)
		{
			lose = true;
		}
		g.drawImage (mainTux.getImage (), getCurrX () - getStartX (), mainTux.getY (), mainTux.getWidth (), mainTux.getHeight (), null);
		return ret;
	}

	/**
	 *
	 * @return
	 */
	public Player getTux ()
	{
		return mainTux;
	}

	/**
	 *
	 * @return
	 */
	public Image[][] getTiles ()
	{
		return levelTiles;
	}

	/**
	 *
	 * @return
	 */
	public int[][][] getLevelCodes ()
	{
		return levelCodes;
	}

	public int getCurrX ()
	{
		int ret;
		if (currX > getFull ().getWidth () - TILE_WIDTH)
		{
			ret = (getFull ().getWidth () - TILE_WIDTH);
		}
		else if (currX < 0)
		{
			ret = 0;
		}
		else
		{
			ret = currX;
		}
		currX = ret;
		return ret;
	}

	private int getStartX ()
	{

		int ret;
		int cX = getCurrX () - SCREEN_WIDTH * TILE_WIDTH / 2;
		if (cX > (getFull ().getWidth () - SCREEN_WIDTH * TILE_WIDTH))
		{
			ret = (getFull ().getWidth () - SCREEN_WIDTH * TILE_WIDTH);
		}
		else if (cX < 0)
		{
			ret = 0;
		}
		else
		{
			ret = cX;
		}
		return ret;
	}
}