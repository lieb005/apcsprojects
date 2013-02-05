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

	Example ("../../../src/Example.wld"),
	World_1 ("../../../src/World1.wld");
	private World world;
	private String url;
	// This function makes it so that 

	Worlds (String dataFile)
	{
		try
		{
			url = dataFile;
			world = new World (dataFile);
		} catch (FileNotFoundException ex)
		{
			ex.printStackTrace ();
		}
	}

	public World getWorld ()
	{
		return world;
	}

	public String getFileName ()
	{
		return url;
	}
}
