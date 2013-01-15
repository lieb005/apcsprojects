/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supa.mobsta.bros;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
    private BufferedImage fullLevel;
    private ArrayList<Image[]> levelTiles = new ArrayList<Image[]>();
    private Image[][] tiles;

    public Level(String level)
    {
        try
        {
            loadTiles();

            // here we have to parse out the level from the data
            BufferedReader tileReader = new BufferedReader(new FileReader("src/codes.txt"));
            String line;
            while (tileReader.ready())
            {
                line = tileReader.readLine().trim().toLowerCase();

            }
        } catch (IOException ex)
        {
        }

    }

    public BufferedImage makeFull()
    {
        return null;
    }

    public BufferedImage getFull()
    {
        while (true)
        {
        }
    }

    public BufferedImage getSegment(int startx)
    {
        return getFull().getSubimage(startx, 0, TILE_WIDTH * SCREEN_WIDTH, TILE_HEIGHT * SCREEN_HEIGHT);
    }

    public void loadTiles()
    {
        BufferedImage pallette = (BufferedImage) Toolkit.getDefaultToolkit().createImage("src/img/pallette.png");
        //width x height
        tiles = new Image[16][5];
        for (int i = 0; i < 16; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                tiles[i][j] = pallette.getSubimage(i * TILE_WIDTH, j * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
            }
        }
    }
}
