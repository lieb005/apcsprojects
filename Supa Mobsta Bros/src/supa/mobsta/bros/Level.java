/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supa.mobsta.bros;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 *
 * @author mark
 */
public final class Level
{

    public static final int TILE_WIDTH = SupaMobstaBros.TILE_WIDTH,
	    SCREEN_WIDTH = SupaMobstaBros.SCREEN_WIDTH,
	    TILE_HEIGHT = SupaMobstaBros.TILE_HEIGHT,
	    SCREEN_HEIGHT = SupaMobstaBros.SCREEN_HEIGHT;
    public final String palletteFile = "src/supa/mobsta/img/pallette.png";
    private BufferedImage fullLevel;
    private ArrayList<Image[]> levelTiles = new ArrayList<Image[]>();
    private ArrayList<ArrayList<Player>> players = new ArrayList<ArrayList<Player>>();
    private static Image[][] tiles = null;

    public Level(String level)
    {
	System.out.println("Making Level");
	try
	{
	    if (tiles == null)
	    {
		loadTiles();
	    }
	    // here we have to parse out the level from the data
	    while (level.startsWith("\n"))
	    {
		//System.out.println("before");
		//System.out.println(level.substring(level.length() - 5));
		level = level.replaceFirst("\n", "");
		//System.out.println(level.substring(level.length() - 5));
	    }
	    while (level.endsWith("\n"))
	    {
		//System.out.println("after");
		//System.out.println(level.substring(level.length() - 5));
		level = level.substring(0, level.length() - 1);
		//System.out.println(level.substring(level.length() - 5));
	    }
	    level = level.trim().toLowerCase().replaceAll("[ ]+", " ").replaceAll("[\n]+", "\n");
	    String[] lev = level.split("\n");



	    int[][][] rawTiles = new int[lev[0].split(" ").length][SupaMobstaBros.SCREEN_HEIGHT][3];
	    if (lev.length != SupaMobstaBros.SCREEN_HEIGHT)
	    {
		throw new IndexOutOfBoundsException("Level Height Mismatch.  Found: " + lev.length + " Expected: " + SupaMobstaBros.SCREEN_HEIGHT);
	    }
	    //System.out.println("\n\n");
	    int j, enemy, col, row;
	    String line;
	    for (int i = 0; i < SupaMobstaBros.SCREEN_HEIGHT; i++)
	    {
		line = lev[i];
		if (line == null || line.equals(""))
		{
		    continue;
		}
		String[] codes = line.split(" ");
		String code;
		for (j = 0; j < codes.length; j++)
		{
		    code = codes[j];
		    enemy = Integer.parseInt(code.substring(0, 1), 16);
		    col = Integer.parseInt(code.substring(2), 16);
		    row = Integer.parseInt(code.substring(1, 2), 16);
		    //System.out.println(enemy + " " + row + " " + col);
		    rawTiles[j][i] = new int[]
		    {
			enemy, col, row
		    };
		}
	    }
	    System.out.println("This is what is in the Arrays");
	    for (int k = 0; k < SupaMobstaBros.SCREEN_HEIGHT; k++)
	    {
		for (int l = 0; l < rawTiles.length; l++)
		{
		    System.out.print(rawTiles[l][k][0] + " " + rawTiles[l][k][1] + " " + rawTiles[l][k][2] + " ");
		}
		System.out.println();
	    }
	} catch (Exception ex)
	{
	    System.out.println("ERROR");
	    ex.printStackTrace();
	} finally
	{
	    System.out.println("End");
	}

    }

    public BufferedImage makeFull()
    {
	fullLevel.getRaster();
	return null;
    }

    public BufferedImage getFull()
    {
	return fullLevel;
    }

    public BufferedImage getSegment(int startx)
    {
	return getFull().getSubimage(startx, 0, TILE_WIDTH * SCREEN_WIDTH, TILE_HEIGHT * SCREEN_HEIGHT);
    }

    public void loadTiles()
    {
	try
	{
	    BufferedImage pallette = ImageIO.read(new File(palletteFile));
	    //width x height
	    tiles = new Image[16][5];
	    for (int i = 0; i < 16; i++)
	    {
		for (int j = 0; j < 5; j++)
		{
		    tiles[i][j] = pallette.getSubimage(i * TILE_WIDTH, j * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
		}
	    }
	    JFrame f = new JFrame("Tiles");
	    f.setSize(300, 300);
	    f.add(new Canvas()
	    {
		@Override
		public void paint(Graphics g)
		{
		    super.paint(g);
		    for (int i = 0; i < 5; i++)
		    {
			for (int j = 0; j < 16; j++)
			{
			    g.drawImage(tiles[j][i], j * (TILE_WIDTH + 1), i * (TILE_WIDTH + 1), null);
			}
		    }

		}
	    });
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.pack();
	    f.setVisible(true);
	    System.out.println("Loaded Tiles");
	} catch (IOException ex)
	{
	}

    }
}
