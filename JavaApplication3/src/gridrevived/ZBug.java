/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gridrevived;

import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;
import java.awt.Color;

/**
 *
 * @author mark
 */
public class ZBug extends Bug
{

	boolean up = true;
	int count = 0, oldcount = 0;;
	Location startLocation = new Location (0, 0);
	int[][] movies;
	public static final int[][] Z = new int[][]
	{
		{
			1, 1
		},
		{
			0, 1
		},
		{
			-1, 1
		},
		{
			0, 0
		},
		{
			1, -1
		},
		{
			0, -1
		},
		{
			-1, -1
		}
	};
	public static final int[][] E = new int[][]
	{
		{
			1, 2
		},
		{
			0, 2
		},
		{
			-1, 2
		},
		{
			-1, 1
		},
		{
			-1, 0
		},
		{
			0, 0
		},
		{
			-1, 0
		},
		{
			-1, -1
		},
		{
			-1, -2
		},
		{
			0, -2
		},
		{
			1, -2
		}
	};

	public ZBug (final int[][] moves)
	{
		count = moves.length / 2;
		System.out.println (moves.length / 2);
		System.out.println (moves[count][0]);
		movies = moves;
	}

	@Override
	public void move ()
	{

		if (startLocation.equals (new Location (0, 0)))
		{
			startLocation = getLocation ();

			if ((movies == E && startLocation.getRow () < 2) || (movies == Z && startLocation.getRow () < 1))
			{
				throw new IndexOutOfBoundsException ("Must place critter at least 2 from top.");
			}
			if ((movies == E && startLocation.getCol () < 2) || (movies == Z && startLocation.getCol () < 1))
			{
				throw new IndexOutOfBoundsException ("Must place critter at least 2 from left.");
			}
			if ((movies == E && startLocation.getRow () > getGrid ().getNumRows () - 3) || (movies == Z && startLocation.getRow () > getGrid ().getNumRows () - 2))
			{
				throw new IndexOutOfBoundsException ("Must place critter at least 2 from bottom.");
			}
			if ((movies == E && startLocation.getCol () > getGrid ().getNumCols () - 3) || (movies == Z && startLocation.getCol () > getGrid ().getNumCols () - 2))
			{
				throw new IndexOutOfBoundsException ("Must place critter at least 2 from right.");
			}
		}
		oldcount = count;
		if (up)
		{
			if (++count >= movies.length)
			{
				count -= 2;
				up = false;
			}
		}
		else
		{
			if (--count < 0)
			{
				count += 2;
				up = true;
			}
		}
		moveTo(new Location (startLocation.getRow () + movies[count][1], startLocation.getCol () + movies[count][0]));
		new Flower (Color.RED).putSelfInGrid (getGrid (), new Location (startLocation.getRow () + movies[oldcount][1], startLocation.getCol () + movies[oldcount][0]));
//		setDirection (slopeToDirection (movies[count], movies[Math.min (up ? count + 1 : count - 1, movies.length - 1)]));
		
		//moveTo (startLocation);
	}

	private int slopeToDirection (int[] pt1, int[] pt2)
	{
		if (pt1[0] - pt1[0] == 0)
		{
			return (90 * (int) Math.signum ((pt1[1] - pt2[1])));
		}
		return (int) (Math.asin ((pt1[1] - pt2[1]) / (pt1[0] - pt2[0])) * 180 / Math.PI);
	}
}
