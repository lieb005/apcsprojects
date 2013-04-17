/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package petstore;

import javax.swing.JFrame;

/**
 *
 * @author mark
 */
public class Runner
{
    public static void main (String[] args)
    {
        JFrame f = new JFrame ("Pet Store");
        PetStore p = new PetStore ();
        f.add(p);
        p.init();
        p.start();
        f.setSize (400, 600);
        f.setVisible (true);
        f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    }
}
