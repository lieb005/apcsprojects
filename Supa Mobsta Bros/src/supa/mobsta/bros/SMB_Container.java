/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supa.mobsta.bros;

import java.applet.AudioClip;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JApplet;
import javax.swing.JFrame;

/**
 *
 * @author mark
 */
public class SMB_Container extends JApplet
{

	private static String[] arg;
	SupaMobstaBros s;

	/**
	 * @param args the command line arguments
	 */
	public static void main (String[] args)
	{
		arg = args;
		JFrame f = new JFrame ("Supa Mobsta Bros");
		SMB_Container c = new SMB_Container ();
		f.add (c);
		c.init ();
		c.start ();
		f.pack ();
		f.setVisible (true);
		f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		f.validate ();
		c.requestFocusInWindow ();
		f.addKeyListener (c.getKeyListeners ()[0]);
	}

	/**
	 *
	 */
	@Override
	public void init ()
	{
		super.init ();
		try
		{
			if ((arg.length > 0) && new File (arg[0]).exists ())
			{
				s = new SupaMobstaBros (arg[0]);
			}
			else
			{
				s = new SupaMobstaBros ();
			}
		} catch (FileNotFoundException ex)
		{
		}
		add (s);
		if (Level.DEBUG)
		{
			JFrame f = new JFrame ("View");
			f.setSize (300, 300);
			f.add (new Canvas ()
			{
				@Override
				public void paint (Graphics g)
				{
					super.paint (g);
					g.drawImage (s.getView (), 0, 0, null);

				}
			});
			f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
			f.pack ();
			f.setVisible (true);
		}
		repaint ();
		s.setFocusable (true);
		s.requestFocusInWindow ();
		s.addFocusListener (new FocusListener () {

			@Override
			public void focusGained (FocusEvent fe)
			{
				s.requestFocusInWindow ();
			}

			@Override
			public void focusLost (FocusEvent fe)
			{
			}
		});
		addKeyListener (s);
		getContentPane ().addKeyListener (s);
		try
		{
			AudioClip a;
			File f = new File ("music.wav");
			//System.out.println (f.getAbsolutePath ());
			URL u = f.toURI ().toURL ();
			System.out.println (u);
			System.out.println ("Does the file exist? " + f.exists ());
			a = newAudioClip (u);
			a.loop ();
		} catch (MalformedURLException ex)
		{
			System.out.println ("asoichzgjx,hcimbrhceufhci9udfhmhihovgivvrnhz9");
		}
	}
}
