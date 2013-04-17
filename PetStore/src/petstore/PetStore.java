/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package petstore;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Vector;
import javax.swing.*;

/**
 *
 * @author mark
 */
public class PetStore extends JApplet implements ActionListener
{

    String[] possibleTypes = new String[]
    {
        "Dog", "Cat", "Bird"
    };
    Vector<Animal> stock = new Vector<Animal> ();
    JComboBox animalTypes = new JComboBox (possibleTypes);
    JTextField ageField = new JTextField (3);
    LinkedHashMap<String, JLabel> animalLabels = new LinkedHashMap<String, JLabel> (possibleTypes.length);
    int birdCount = 0, dogCount = 0, catCount = 0;
    JLabel avgAge = new JLabel (), count = new JLabel (), oldest = new JLabel ();

    @Override
    public void init ()
    {
        super.init ();
        setLayout (new BorderLayout ());
        JPanel stuff = new JPanel ();
        animalTypes.addItem ("Random");
        stuff.add (animalTypes);
        stuff.add (ageField);
        ageField.addActionListener (this);
        JButton buy = new JButton ("Buy");
        buy.addActionListener (this);
        stuff.add (buy);
        JButton sell = new JButton ("Sell");
        sell.addActionListener (this);
        stuff.add (sell);
        add (stuff, "North");
        animalLabels.put ("dog", new JLabel ());
        animalLabels.put ("cat", new JLabel ());
        animalLabels.put ("bird", new JLabel ());
        JPanel labels = new JPanel ();
        labels.setLayout (new BoxLayout (labels, BoxLayout.PAGE_AXIS));
        labels.add (animalLabels.get ("dog"));
        labels.add (animalLabels.get ("cat"));
        labels.add (animalLabels.get ("bird"));
        labels.add (new JLabel ());
        labels.add (oldest);
        labels.add (avgAge);
        add (labels, "Center");
    }

    @Override
    public void actionPerformed (ActionEvent e)
    {
        if (e.getSource () instanceof JButton)
        {
            if (((JButton) e.getSource ()).getText () == "Sell")
            {
                for (int i = 0; i < stock.size (); i++)
                {
                    if (stock.get (i).getType ().equals (((String) animalTypes.getSelectedItem ()).toLowerCase ()))
                    {
                        stock.remove (i);
                        refresh ();
                        return;
                    }
                }
            }
        }
        addNewPet ((String) animalTypes.getSelectedItem (), Integer.valueOf (ageField.getText ()));
        refresh ();
    }

    public void addNewPet (String type, int age)
    {
        System.out.println (type + "   " + type.toLowerCase ());
        type = type.toLowerCase ();
        if (type.equals ("bird"))
        {
            stock.add (new Bird (age));
        }
        else if (type.equals ("dog"))
        {
            stock.add (new Dog (age));
        }
        else if (type.equals ("cat"))
        {
            stock.add (new Cat (age));
        }
        else if (type.equals ("random"))
        {
            addRandomPet ();
        }
        else
        {
            System.out.println (type + " Does not exist");
        }
        stock.get (stock.size () - 1).makeNoise ();
    }

    private void addRandomPet ()
    {
        addNewPet (possibleTypes[(int) (Math.random () * possibleTypes.length)], (int) (Math.random () * 15));
    }

    private void refresh ()
    {
        birdCount = 0;
        dogCount = 0;
        catCount = 0;
        for (int i = 0; i < stock.size (); i++)
        {
            Animal animal = stock.get (i);
            if (animal.getType ().equals ("bird"))
            {
                birdCount++;
            }
            else if (animal.getType ().equals ("dog"))
            {
                dogCount++;
            }
            else if (animal.getType ().equals ("cat"))
            {
                catCount++;
            }
        }
        animalLabels.get ("bird").setText ("Birds: " + String.valueOf (birdCount));
        animalLabels.get ("cat").setText ("Cats: " + String.valueOf (catCount));
        animalLabels.get ("dog").setText ("Dogs: " + String.valueOf (dogCount));
        oldest.setText ("The oldest is " + String.valueOf (getOldest ().getAge ()) + " years old.");
        StringWriter s = new StringWriter ();
        PrintWriter p = new PrintWriter (s);
        p.printf ("%5.2f", getAverageAge ());
        avgAge.setText ("The average age is " + s.toString () + " years old");
        validate ();
        invalidate ();
        repaint ();
    }

    private Animal getOldest ()
    {
        Animal oldestAnimal = new Cat (0);
        for (int i = 0; i < stock.size (); i++)
        {
            if (stock.get (i).getAge () >= oldestAnimal.getAge ())
            {
                oldestAnimal = stock.get (i);
            }
        }
        return oldestAnimal;
    }

    private double getAverageAge ()
    {
        double runningTotal = 0;
        int num = 0;
        for (num = 0; num < stock.size (); num++)
        {
            runningTotal += stock.get (num).getAge ();
        }
        return runningTotal / (double) num;
    }
}
