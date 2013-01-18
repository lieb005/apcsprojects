/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supa.mobsta.goodguys;

import supa.mobsta.bros.Player;

/**
 *
 * @author mark
 */
public abstract class GoodGuy extends Player
{
    public static GoodGuy createGoodGuy(int type)
    {
	    return (GoodGuy)Player.createPlayer(type);
    }
}
