/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supa.mobsta.goodguys;

import java.io.IOException;

/**

 @author mark
 */
public class AlCapone extends GoodGuy
{

	public static final int WALK = 4;

	public AlCapone ()
	{
		try
		{
			setFrames (loadImage ("supa/mobsta/img/AlCapone.png"));
		} catch (IOException e)
		{
			e.printStackTrace ();
		}
		setFrame (0);
		setHeight (1);
	}
}
