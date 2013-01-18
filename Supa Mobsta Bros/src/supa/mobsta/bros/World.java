/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supa.mobsta.bros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mark
 */
public class World
{

    private ArrayList<Level> levels = new ArrayList<Level>();
    private String name = "";

    public World(String file) throws FileNotFoundException
    {
	this(new File(file));
    }

    public World(File file) throws FileNotFoundException
    {
	if (!file.exists())
	{
	    throw new FileNotFoundException(file.getAbsolutePath() + " does not exist!");
	}
	else
	{
	    System.out.println("File Exists");
	}
	try
	{
	    String data = "";
	    BufferedReader reader = new BufferedReader(new FileReader(file));
	    while (reader.ready())
	    {
		data += reader.readLine() + "\n";
	    }
	    //System.out.println(data);
	    createWorld(data);
	} catch (IOException ex)
	{
	}
    }

    // This function decodes the data in the world file.
    // It will be annoying to write
    private void createWorld(String data)
    {
	boolean doingLevel = false;
	String level = "", levelName = "";
	for (String line : data.split("\n"))
	{
	    line = line.trim().toLowerCase();
	    if (line.isEmpty() || line.startsWith("#") || line.startsWith("//") || line.matches("[\n]+") || line.equals(""))
	    {
		continue;
	    }
	    if (!doingLevel)
	    {
		if (line.startsWith("name"))
		{
		    // cut off "name " (with space)
		    name = line.substring(5);
		}
		if (line.startsWith("level"))
		{
		    levelName = line.split(" ", 2)[1];
		    doingLevel = true;
		}
	    }
	    else
	    {
		if (line.startsWith("endlevel"))
		{
		    doingLevel = false;
		    levels.add(new Level(levelName, level));
		    level = "";
		    continue;
		}

		level += line + "\n";
	    }
	}
    }
    public Level getLevel(int level)
    {
	return levels.get(level);
    }
}
