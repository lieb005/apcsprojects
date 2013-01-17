/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supa.mobsta.bros;

import java.io.FileNotFoundException;
import javax.swing.JPanel;

/**
 *
 * @author 314Chan Telecommunications, LLC.
 */
public class SupaMobstaBros extends JPanel
{

    public static final int TILE_WIDTH = 8,
            SCREEN_WIDTH = 12,
            TILE_HEIGHT = 8,
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
}
