/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package petstore;

import java.applet.AudioClip;

/**
 *
 * @author mark
 */
public abstract class Animal
{
    final static String AUDIODIR = "file:///home/mark/NetBeansProjects/PetStore/build/classes/";
    String myName;
    int myAge;
    AudioClip mySound;
    public void makeNoise()
    {
        mySound.play ();
    }
    public int getAge()
    {
        return myAge;
    }
    public String getName()
    {
        return myName;
    }
    public void setName(String name)
    {
        myName = name;
    }
    public void setAge(int age)
    {
        myAge = age;
    }
    protected abstract void refresh();
    public abstract String getType();
}
