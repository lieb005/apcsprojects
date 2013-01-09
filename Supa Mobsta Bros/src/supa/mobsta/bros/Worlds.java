/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supa.mobsta.bros;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mark
 */
public enum Worlds
{
    // These are the names of our worlds associated to the files
    World_1("world1.wld");
    private World world;
    // This function makes it so that 
    Worlds(String dataFile)
    {
        try
        {
            String data = "";
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            while (reader.ready())
            {
                data += reader.readLine();
            }
            createWorld(data);
        } catch (IOException ex)
        {
            Logger.getLogger(Worlds.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // This function decodes the data in the world file.
    // It will be annoying to write
    private void createWorld(String data)
    {
        
    }
}
