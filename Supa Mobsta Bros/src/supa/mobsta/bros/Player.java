/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supa.mobsta.bros;

/**
 *
 * @author mark
 */
public class Player
{

    Player whoWeAre;
    /**
     * This creates a new player (hopefully on a level). of integer type "type".
     * The key for types can be found in codes.txt
     * @param type Type of Player to make ourselves
     * @throws TypeNotPresentException 
     */
    Player(int type) throws TypeNotPresentException
    {
	// Here we have to use the index to create a player of the proper type
	switch (type)
	{
	    case 0:
		break;
	    case 1:
		break;
	    case 2:
		break;
	    case 3:
		break;
	    case 4:
		break;
	    case 5:
		break;
	    case 6:
		break;
	    case 7:
		break;
	    case 8:
		break;
	    case 9:
		break;
	    case 10:
		break;
	    case 11:
		break;
	    case 12:
		break;
	    case 13:
	    case 14:
	    case 15:
	    default:
		throw new TypeNotPresentException(String.valueOf(type), new Throwable("This Type is not real!"));
	}
    }
}
