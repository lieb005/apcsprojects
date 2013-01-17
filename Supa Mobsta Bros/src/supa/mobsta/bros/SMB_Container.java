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

    private static String[] arg;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        arg = args;
        JFrame f = new JFrame("Supa Mobsta Bros");
        SMB_Container c = new SMB_Container();
        f.add(c);
        f.setSize(SupaMobstaBros.SCREEN_WIDTH * SupaMobstaBros.TILE_WIDTH, SupaMobstaBros.SCREEN_HEIGHT * SupaMobstaBros.TILE_HEIGHT);
        c.init();
        c.start();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void init()
    {
        super.init();
        SupaMobstaBros s;
        if ((arg.length > 0) && new File(arg[0]).exists())
        {
            s = new SupaMobstaBros(arg[0]);
        } else
        {
            s = new SupaMobstaBros();
        }
        add(s);
    }
}
