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
public class Cat extends Animal
{

    AudioClip[] catSounds;

    public Cat ()
    {
        myAge = 0;

        try
        {
            catSounds = new AudioClip[]
            {
                JApplet.newAudioClip (new URL (AUDIODIR + "petstore/cat0.wav"))
            };
        }
        catch (Exception e)
        {
        }
        refresh ();
    }

    public Cat (int age)
    {
        this ();
        myAge = age;
        refresh ();
    }

    @Override
    protected void refresh ()
    {
        mySound = catSounds[0];
    }

    @Override
    public String getType ()
    {
        return "cat";
    }
}
