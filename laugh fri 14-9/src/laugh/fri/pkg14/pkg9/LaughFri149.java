/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laugh.fri.pkg14.pkg9;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author period5
 */
public class LaughFri149 extends JApplet
{

    Haiku h = new Haiku ();
    //JPanel haiku = new JPanel ();
    JLabel haiku = new JLabel ();
    JButton next = new JButton ("Next"), exit = new JButton ("Exit");

    /**
     * @param args the command line arguments
     */
    public static void main (String[] args)
    {
	LaughFri149 l = new LaughFri149 ();
	JFrame f = new JFrame ("Laugh Fri 14-9");
	f.add (l);
	l.init ();
	l.start ();
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment ();
	GraphicsDevice gd = ge.getDefaultScreenDevice ();
	gd.setFullScreenWindow (f);
	f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	//f.setVisible (true);
	//f.setSize (400, 400);
    }

    @Override
    public void init ()
    {
	super.init ();
	setLayout (new BorderLayout ());
	add (haiku, "Center");
	JPanel buttons = new JPanel ();
	buttons.add (next);
	buttons.add (exit);
	add (buttons, "South");
	haiku.setFont (new Font ("Arial", Font.BOLD, 150));
	haiku.setText ("<html>" + h.run (false).replaceAll ("\n", "<br/>") + "</html>");

	exit.addActionListener (new ActionListener ()
	{
	    @Override
	    public void actionPerformed (ActionEvent ae)
	    {
		System.exit (0);
	    }
	});
	next.addActionListener (new ActionListener ()
	{
	    @Override
	    public void actionPerformed (ActionEvent ae)
	    {
		haiku.setText ("<html>" + h.run (false).replaceAll ("\n", "<br/>") + "</html>");
	    }
	});
    }
}
