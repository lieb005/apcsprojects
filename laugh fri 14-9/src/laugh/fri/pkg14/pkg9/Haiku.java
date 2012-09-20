/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laugh.fri.pkg14.pkg9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author period5
 */
public class Haiku
{

    public final static double MAX_SYLLABLES = 7.0;
    public final static int LINE_1 = 5;
    public final static int LINE_2 = 7;
    File dict = new File ("./src/laugh/fri/pkg14/pkg9/c06d");
    ArrayList<ArrayList<String>> syllableSorted = new ArrayList<ArrayList<String>> ();

    /**
     * @param args the command line arguments
     */
    public static void main (String[] args)
    {
	try
	{
	    System.out.println (new File ("./").getCanonicalPath ());
	}
	catch (IOException ex)
	{
	    Logger.getLogger (Haiku.class.getName ()).log (Level.SEVERE, null, ex);
	}
	Haiku h = new Haiku ();
	h.init ();
	while (true)
	{
	    h.run (true);
	}
    }

    void init ()
    {
	for (byte b = (byte) MAX_SYLLABLES; b >= 0; b--)
	{
	    syllableSorted.add (new ArrayList<String> ());
	}
	parseDict ();
    }

    String run (boolean wait)
    {
	System.out.println ("New Haiku:");
	int currSyll;
	String haiku = "";
	int syllables, lines = 0, lineMax;
	while (++lines <= 3)
	{
	    syllables = 0;
	    if (lines % 2 == 1)
	    {
		lineMax = LINE_1;
	    }
	    else
	    {
		lineMax = LINE_2;
	    }
	    while (syllables < lineMax)
	    {
		currSyll = (int) ((Math.random () * (MAX_SYLLABLES)) + 1.0);
		//System.out.println(currSyll);
		if (currSyll > (lineMax - syllables))
		{
		    currSyll = (lineMax - syllables);
		}
		String word = syllableSorted.get (currSyll).get ((int) (Math.random () * syllableSorted.get (currSyll).size ()));
		haiku += word + " ";// + " (" + currSyll + ") ";
		syllables += currSyll;
	    }
	    haiku += "\n";
	    haiku = haiku.toLowerCase ();
	}

	System.out.println (haiku);
	if (wait)
	{
	    try
	    {
		while (System.in.available () == 0)
		{
		}
		while (System.in.available () > 0)
		{
		    System.in.read ();
		}
	    }
	    catch (IOException e)
	    {
	    }
	}
	return haiku;
    }

    void parseDict ()
    {
	try
	{
	    BufferedReader read = new BufferedReader (new FileReader (dict));
	    String temp;
	    while (read.ready ())
	    {
		temp = read.readLine ();
		if (temp.startsWith ("##"))
		{
		    continue;
		}
		int len = temp.split (" ").length - 3;
		len = len - (int) ((double) len / 2.0);
		if (len > MAX_SYLLABLES)
		{
		    continue;
		}
		if (len < 0)
		{
		    continue;
		}

		syllableSorted.get (len).add (temp.substring (0, temp.indexOf (" ")));
	    }
	}
	catch (IOException ex)
	{
	    Logger.getLogger (Haiku.class.getName ()).log (Level.SEVERE, null, ex);
	    System.exit (1);
	}

    }

    public Haiku ()
    {
	init ();
    }
}
