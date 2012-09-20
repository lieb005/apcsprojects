/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package login.applet;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JApplet;
import javax.swing.JFrame;

/**
 *
 * @author period5
 */
public class LoginApplet extends JApplet
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        LoginApplet la = new LoginApplet();
        JFrame f = new JFrame("Login...");
        la.init();
        la.start();
        f.add(la);
        f.pack();
    }

    public void init()
    {
    }

    @SuppressWarnings("CallToThreadDumpStack")
    public void start()
    {
        try
        {
            System.out.println(getDocumentBase().getHost());
            String ip = (new Socket(getDocumentBase().getHost(), getDocumentBase().getPort()))
                    .getLocalAddress().getHostAddress();
            System.out.println("IP: " + ip);
        }
        catch (UnknownHostException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
