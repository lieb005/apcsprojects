/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supa.mobsta.bros;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 314Chan Telecommunications, LLC.
 */
public class SupaMobstaBros
{
    
        public static final int TILE_WIDTH = 8,
            SCREEN_WIDTH = 12,
            TILE_HEIGHT = 8,
            SCREEN_HEIGHT = 12;

    private World currWorld;
    SupaMobstaBros()
    {
        currWorld = Worlds.World_1.getWorld();
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
            Logger.getLogger(SupaMobstaBros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
