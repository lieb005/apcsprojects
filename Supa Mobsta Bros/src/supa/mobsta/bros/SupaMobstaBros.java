/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supa.mobsta.bros;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import javax.swing.JPanel;

/**
 *
 * @author 314Chan Telecommunications, LLC.
 */
public class SupaMobstaBros extends JPanel implements KeyListener
{

    public static final int TILE_WIDTH = 32,
	    SCREEN_WIDTH = 12,
	    TILE_HEIGHT = 32,
	    SCREEN_HEIGHT = 12;
    private World currWorld;

    SupaMobstaBros()
    {
	currWorld = Worlds.Example.getWorld();
	//currWorld = Worlds.World_1.getWorld();
    }

    /**
     *
     * @param string Level file to import
     */
    SupaMobstaBros(String string)
    {
	try
	{
	    currWorld = new World(string);
	} catch (FileNotFoundException ex)
	{
	}
    }

    @Override
    public void keyTyped(KeyEvent ke)
    {
    }

    @Override
    public void keyPressed(KeyEvent ke)
    {
	switch (ke.getKeyCode())
	{
	    case KeyEvent.VK_LEFT:
		//move Left
		if ((ke.getModifiersEx() & KeyEvent.SHIFT_DOWN_MASK) != 0)
		{
		    //run left
		}
		else
		{
		    //walk Left
		}
		break;
	    case KeyEvent.VK_RIGHT:
		//move Right
		if ((ke.getModifiersEx() & KeyEvent.SHIFT_DOWN_MASK) != 0)
		{
		    //run Right
		}
		else
		{
		    //walk Right
		}
		break;
	    case KeyEvent.VK_DOWN:
		//duck or pipe
		break;
	    case KeyEvent.VK_SHIFT:
		
		break;

	}
    }

    @Override
    public void keyReleased(KeyEvent ke)
    {
	switch (ke.getKeyCode())
	{
	    case KeyEvent.VK_LEFT:
	    case KeyEvent.VK_RIGHT:
		//stop
		break;
	    case KeyEvent.VK_DOWN:
		//stand up
		break;
	    case KeyEvent.VK_SPACE:
		// end Jump
		break;
	}
    }
}
