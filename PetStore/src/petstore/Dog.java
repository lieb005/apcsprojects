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
public class Dog extends Animal
{

    AudioClip[] dogSounds;

    public Dog ()
    {
        myAge = 0;
        try
        {
            // have to fix absolute paths.  maybe get current directory
            dogSounds = new AudioClip[]
            {
                JApplet.newAudioClip (new URL (AUDIODIR + "petstore/dog0.wav")), JApplet.newAudioClip (new URL (AUDIODIR + "petstore/dog1.wav"))
            };
        }
        catch (Exception e)
        {
            e.printStackTrace ();
        }
        refresh ();
    }

    public Dog (int age)
    {
        this ();
        myAge = age;
        refresh ();
    }

    @Override
    protected void refresh ()
    {
        if (myAge < 2)
        {
            mySound = dogSounds[0];
        }
        else
        {
            mySound = dogSounds[1];
        }
    }

    @Override
    public String getType ()
    {
        return "dog";
    }
}
