/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supa.mobsta.enemies;

import supa.mobsta.bros.Player;

/**
 *
 * @author mark
 */
public abstract class Enemy extends Player
{

	public static Enemy createEnemy (int type)
	{
		return (Enemy) Player.createPlayer (type);
	}
}
