/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gpa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import javax.swing.JApplet;

/**
 *
 * @author period5
 */
public class GPA extends JApplet
{
    HashMap <String, Byte[]> grades = new HashMap<String, Byte[]>();

    /**
     * @param args the command line arguments
     */
    public static void main (String[] args)
    {
	GPA gpa = new GPA ();
	gpa.init ();
	gpa.start ();
    }

    @Override
    public void init ()
    {
	super.init ();
	BufferedReader read = new BufferedReader (new InputStreamReader (System.in));
	try
	{
	    
	    while (true)
	    {
		System.out.println ("Enter the Name(s) separated by commas");
		while (!read.ready ())
		{
		}
		String[] names = read.readLine ().replaceAll("[ ]*,[ ]*", ",").split (",");
		String[][] letterGrades = new String[names.length][];
		for (int i = 0;i < names.length;i++)
		{
		    System.out.println ("Enter a comma separated list of letter grades for " + names[i]);
		    while (!read.ready ())
			    {}
		    letterGrades[i] = read.readLine ().toLowerCase().replaceAll("[ ]*,[ ]*", ",").split (",");
		    int[] numGrades;
		    for (int j = 0;j < letterGrades[i].length;j++)
		    {
			
		    }
		}
	    }
	}
	catch (IOException e)
	{
	}
    }

    private int letterToNum(char c)
    {
	c = Character.toLowerCase (c);
	return 0x66 - c;
    }
    
    private int 
    @Override
    public void start ()
    {
	super.start ();
    }
}
