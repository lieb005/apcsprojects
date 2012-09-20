/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package intro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

/**
 *
 * @author period5
 */
public class Intro
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        byte count = 0;
        Vector<String> favs = new Vector<String>();
        String temp = "";
        System.out.println("Name:");
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        for (int j = 0; j < 5; j++)
        {
            try
            {
                while (!read.ready());
                temp = read.readLine();
                System.out.println("Favorite:");
                favs.add(temp);
                temp = null;
            }
            catch (IOException ex)
            {
            }
        }
        for (int f = 0; f < 5; f++)
        {
            System.out.print(favs.get(f) + ", ");
        }

    }
}
