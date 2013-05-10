/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supa.mobsta.goodguys;

import java.io.IOException;
import supa.mobsta.bros.Player;

/**
 *
 * @author mark
 */
public class PowerUp extends Player
{

	public static final int BIG_TUX = 1;
	public static final int FIRE_TUX = 2;
	public static final int INDESTRUCTABLE_TUX = 3;
	public static final int ONE_UP = 4;
	public static final int DUCKS_TUX = 46205;
	int type;

	public PowerUp (int type, int x, int y)
	{
		setX (x);
		setY (y);
		this.type = type;
		try
		{
			switch (type)
			{
				case BIG_TUX:
					setFrames (loadImage ("src/supa/mobsta/img/Tux.png"));

					break;
				case FIRE_TUX:
					setFrames (loadImage ("src/supa/mobsta/img/Tux.png"));

					break;
				case INDESTRUCTABLE_TUX:
					setFrames (loadImage ("src/supa/mobsta/img/Tux.png"));

					break;
				case ONE_UP:
					setFrames (loadImage ("src/supa/mobsta/img/Tux.png"));

					break;
				case DUCKS_TUX:
					setFrames (loadImage ("src/supa/mobsta/img/Tux.png"));

					break;
			}
		} catch (IOException e)
		{
			e.printStackTrace ();
		}
	}
}
