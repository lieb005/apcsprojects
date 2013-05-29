/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supa.mobsta.enemies;

import java.io.IOException;

/**
 *
 * @author mark
 */
public class Gangstas extends Enemy
{

	public Gangstas ()
	{
		try{
		setFrames (loadImage ("src/supa/mobsta/img/Gangsta.png"));
		}catch(IOException e)
		{
			e.printStackTrace ();
		}
		setFrame (0);
	}

}
