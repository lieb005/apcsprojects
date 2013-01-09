/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supa.mobsta.bros;

import java.io.File;
import javax.swing.JApplet;
import javax.swing.JFrame;

/**
 *
 * @author mark
 */
public class SMB_Container extends JApplet
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        JFrame f = new JFrame("Supa Mobsta Bros");
        SupaMobstaBros s;
        if (new File(args[0]).exists())
        {
            s = new SupaMobstaBros(args[0]);
        } else
        {
            s = new SupaMobstaBros();
        }

    }
}
