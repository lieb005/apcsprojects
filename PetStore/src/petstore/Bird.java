/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package petstore;

import java.applet.AudioClip;
import java.net.URL;
import javax.swing.JApplet;

/**
 *
 * @author mark
 */
public class Bird extends Animal
{

    AudioClip[] birdSounds;

    public Bird ()
    {
        myAge = 0;

        try
        {
            birdSounds = new AudioClip[]
            {
                JApplet.newAudioClip (new URL (AUDIODIR + "petstore/bird0.wav"))
            };
        }
        catch (Exception e)
        {
        }
        refresh ();
    }

    public Bird (int age)
    {
        this ();
        myAge = age;
        refresh ();
    }

    @Override
    protected void refresh ()
    {
        mySound = birdSounds[0];
    }

    @Override
    public String getType ()
    {
        return "bird";
    }
}
