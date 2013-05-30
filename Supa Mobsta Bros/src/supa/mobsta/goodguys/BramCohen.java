/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supa.mobsta.goodguys;

import java.io.IOException;

/**

 @author mark
 */
public class BramCohen extends GoodGuy
{

	public BramCohen ()
	{
		try
		{
			setFrames (loadImage ("supa/mobsta/img/BramCohen.png"));
		} catch (IOException e)
		{
			e.printStackTrace ();
		}
		setFrame (0);
		setHeight (1);
	}
}
