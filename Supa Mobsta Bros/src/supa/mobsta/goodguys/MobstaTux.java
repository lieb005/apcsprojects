/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supa.mobsta.goodguys;

import java.awt.Canvas;
import java.awt.Graphics;
import java.io.IOException;
import javax.swing.JFrame;
import supa.mobsta.bros.Level;
import supa.mobsta.bros.SupaMobstaBros;

/**
 *
 * @author mark
 */
public class MobstaTux extends GoodGuy
{

	// only jump 5 blocks hight (from the top)
	public static final int JUMP_LENGTH = SupaMobstaBros.TILE_HEIGHT * 5;
	// can only jump 5 blocks horizontally
	public static final int JUMP_MAX = SupaMobstaBros.TILE_HEIGHT * 5;
	// the number of steps to do a jump in
	public static final int JUMP_STEPS = JUMP_LENGTH / SupaMobstaBros.RUN;
	// curent level on which we stand
	private int standy = 0;
	// Current step in a jump
	private int currJump = 1;
	// velocity and aceleration of the guy
	private double acceleration = 50, velocity = 0;

	public MobstaTux()
	{
		try
		{
			setFrames(loadImage("src/supa/mobsta/img/Tux.png"));
			//setFrame(0);
			//setSize(32, 32);
		} catch (IOException ex)
		{
			ex.printStackTrace();
		}
		if (Level.DEBUG)
		{
			JFrame f = new JFrame("Tux");
			f.setSize(300, 300);
			f.add(new Canvas()
			{
				@Override
				public void paint(Graphics g)
				{
					super.paint(g);
					for (int i = 0; i < 5; i++)
					{
						g.drawImage(getFrames()[i], i * (SupaMobstaBros.TILE_WIDTH + 1), (SupaMobstaBros.TILE_WIDTH), null);
					}

				}
			});
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.pack();
			f.setVisible(true);
		}
	}

	public void jump()
	{
		System.out.println(currJump + "johk");
		if (currJump == 0)
		{
			return;
		}
		else
		{
			currJump++;
		}
		// parabola: y = b(x)(x-JUMP_LENGTH)
		// b = JUMP_HEIGHT/(JUMP_LENGTH*(JUMP_LENGTH/2)
		// x = currJump
		double b = JUMP_MAX / ((JUMP_STEPS*JUMP_STEPS) / 2);
		int curvey = (int)((double)b * ((double)currJump) * ((double)currJump - JUMP_STEPS));
		setY(standy + curvey);
		//if (currJump >= JUMP_STEPS / 2 && getSurroundings()[2])
		//{
		//	currJump = 0;
		//	standy = getY();
		//}
		/*
		else if (getY() - getHeight() <= 0)
		{
			SupaMobstaBros.lose();
		}*/
	}

	public void fall()
	{
		currJump = JUMP_STEPS / 2;
		jump();
	}

	private boolean[] getSurroundings()
	{
		//System.out.println(currLevelCodes);
		// above, right, below, left
		boolean[] surrounds = new boolean[4];
		int[][][] tiles = getTileCodes();
		if (getY() / SupaMobstaBros.TILE_HEIGHT >=SupaMobstaBros.TILE_HEIGHT)
		{
			surrounds[0] = (tiles[getX() / SupaMobstaBros.TILE_WIDTH][(getY() / SupaMobstaBros.TILE_HEIGHT) + (getHeight() / SupaMobstaBros.TILE_HEIGHT)][2] > 0);
		}
		if (getX() / SupaMobstaBros.TILE_WIDTH + 1 < tiles.length)
		{
			surrounds[1] = (tiles[getX() / SupaMobstaBros.TILE_WIDTH][(getY() / SupaMobstaBros.TILE_HEIGHT) + (getHeight() / SupaMobstaBros.TILE_HEIGHT)][2] > 0);
		}
		surrounds[2] = (tiles[getX() / SupaMobstaBros.TILE_WIDTH][(getY() / SupaMobstaBros.TILE_HEIGHT) + (getHeight() / SupaMobstaBros.TILE_HEIGHT)][2] > 0);
		if (getX() / SupaMobstaBros.TILE_WIDTH - 1 > 0)
		{
			surrounds[3] = (tiles[getX() / SupaMobstaBros.TILE_WIDTH - 1][(getY() / SupaMobstaBros.TILE_HEIGHT) + (getHeight() / SupaMobstaBros.TILE_HEIGHT)][2] > 0);
		}

		return surrounds;
	}

	@Override
	public void repaint()
	{
		super.repaint();
		jump();
	}
	public int getJump()
	{
		return currJump;
	}
	public void setJump(int i)
	{
		currJump = i;
	}
}
