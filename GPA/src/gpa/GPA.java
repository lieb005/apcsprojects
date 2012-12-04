/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gpa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JApplet;

/**
 *
 * @author period5
 */
public class GPA extends JApplet
{

    LinkedHashMap<String, int[]> grades = new LinkedHashMap<String, int[]> ();

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
                grades = new LinkedHashMap<String, int[]> ();
                System.out.println ("Enter the Name(s) separated by commas");
                String temp = read.readLine ();
                if (temp == "exit")
                {
                    System.exit (0);
                }
                String[] names = temp.replaceAll ("[ ]*,[ ]*", ",").split (",");
                String[][] letterGrades = new String[names.length][];
                for (int i = 0; i < names.length; i++)
                {
                    System.out.println ("Enter a comma separated list of letter grades for " + names[i]);
                    String tmp = read.readLine ();
                    if (tmp == "exit")
                    {
                        System.exit (0);
                    }
                    letterGrades[i] = tmp.toLowerCase ().replaceAll ("[ ]*,[ ]*", ",").split (",");
                    int[] numGrades = new int[letterGrades[i].length];
                    for (int j = 0; j < letterGrades[i].length; j++)
                    {
                        numGrades[j] = letterToNum (letterGrades[i][j].charAt (0));
                    }
                    grades.put (names[i], numGrades);
                }
                for (Map.Entry<String, int[]> entry : grades.entrySet ())
                {
                    String string = entry.getKey ();
                    int[] is = entry.getValue ();
                    //System.out.printf ("%s has %d,%d,%d,%d,%d,%d\n", string, is[0], is[1], is[2], is[3], is[4], is[5]);
                    System.out.printf ("%s has an average grade of %s\n", string, numToLetter ((is[0] + is[1] + is[2] + is[3] + is[4] + is[5]) / 6));
                }
                System.out.println ();
            }
        }
        catch (IOException e)
        {
        }
    }

    private int letterToNum (char c)
    {
        switch (c)
        {
        case 'a':
            return 0;
        case 'b':
            return 1;
        case 'c':
            return 2;
        case 'd':
            return 3;
        case 'f':
            return 4;
        default:
            return 9;
        }
    }

    private char numToLetter (int num)
    {
        final char[] gradeLetters = new char[]
        {
            'A', 'B', 'C', 'D', 'F'
        };
        return gradeLetters[num];
    }

    @Override
    public void start ()
    {
        super.start ();
    }
}
