/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supa.mobsta.bros;

import java.io.FileNotFoundException;

/**
 *
 * @author mark
 */
public enum Worlds
{
    // These are the names of our worlds associated to the files

    World_1("src/world1.wld");
    private World world;
    // This function makes it so that 

    Worlds(String dataFile)
    {
        try
        {
            world = new World(dataFile);
        } catch (FileNotFoundException ex)
        {
        }
    }

    public World getWorld()
    {
        return world;
    }
}
