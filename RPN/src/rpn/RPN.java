/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpn;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;
import javax.swing.*;

/**
 *
 * @author period5
 */
public class RPN extends JApplet implements ActionListener, ItemListener
{

    public static final int NONE = (0), ADD = (1), SUBTRACT = (2), MULTIPLY = (3), DIVIDE = (4), POWER = (5), LOG = (6), LOGB = (7), MODULO = (8), SIN = (9), COS = (10), TAN = (11), ASIN = (12), ACOS = (13), ATAN = (14), PUSH = (15);
    final int stackSize;
    Vector<Double> stack = new Vector<Double> ();
    JList stackList = new JList ();
    JTextField action = new JTextField (20);
    JButton enter = new JButton ("Enter");
    int globalCurrAction = PUSH;

    /**
     * @param args the command line arguments
     */
    public static void main (String[] args)
    {
        JFrame f = new JFrame ("RPN Calculator");
        if (args.length != 0)
        {
            if (args[0].contains ("-h"));
            System.out.println ("This program is an RPM calculator\nusage: RPM [stack_size] [-h]");
        }
        RPN r = new RPN (10);

        r.init ();

        r.start ();

        f.add (r);
        f.setVisible (true);
        f.setSize (400, 400);
        //f.pack ();
    }

    public RPN (int stack_size)
    {
        stackSize = stack_size;
    }

    @Override
    public void init ()
    {
        setLayout (new BorderLayout ());
        add (new JScrollPane (stackList), "Center");
        JPanel actions = new JPanel ();
        actions.add (action);
        actions.add (enter);
        add (actions, "South");
        action.addActionListener (this);
        enter.addActionListener (this);
        
    }

    public int getPreferredWidth ()
    {
        return 300;
    }

    public int getPreferredHeight ()
    {
        return 600;
    }

    private double doAction (String input)
    {
        input.replace (" ", "");
        int currAction;
        if (input.indexOf ("+") != -1)
        {
            input.replace ("+", "");
            currAction = ADD;
        }
        else if (input.indexOf ("-") != -1)
        {
            input.replace ("-", "");
            currAction = SUBTRACT;
        }
        else if (input.indexOf ("*") != -1)
        {
            input.replace ("*", "");
            currAction = MULTIPLY;
        }
        else if (input.indexOf ("/") != -1)
        {
            input.replace ("/", "");
            currAction = DIVIDE;
        }
        else if (input.indexOf ("%") != -1)
        {
            input.replace ("%", "");
            currAction = MODULO;
        }
        else if (input.indexOf ("^") != -1)
        {
            input.replace ("^", "");
            currAction = POWER;
        }
        else if (input.indexOf ("log") != -1)
        {
            input.replace ("log", "");
            currAction = LOG;
        }
        else if (input.indexOf ("logb") != -1)
        {
            input.replace ("logb", "");
            currAction = LOGB;
        }
        else if (input.indexOf ("sin") != -1)
        {
            input.replace ("sin", "");
            currAction = SIN;
        }
        else if (input.indexOf ("cos") != -1)
        {
            input.replace ("cos", "");
            currAction = COS;
        }
        else if (input.indexOf ("tan") != -1)
        {
            input.replace ("tan", "");
            currAction = TAN;
        }
        else if (input.indexOf ("asin") != -1)
        {
            input.replace ("asin", "");
            currAction = ASIN;
        }
        else if (input.indexOf ("acos") != -1)
        {
            input.replace ("acos", "");
            currAction = ACOS;
        }
        else if (input.indexOf ("atan") != -1)
        {
            input.replace ("atan", "");
            currAction = ATAN;
        }
        else
        {
            currAction = PUSH;
        }
        push (parseCurr ());
        int args = 1;
        try
        {
            switch (currAction)
            {
            case ADD:
                args = 2;
                return pop () + pop ();
            case SUBTRACT:
                args = 2;
                return -pop () + pop ();
            case MULTIPLY:
                args = 2;
                return pop () * pop ();
            case DIVIDE:
                args = 2;
                return Math.pow (pop () / pop (), -1);
            case POWER:
                args = 2;
                return Math.pow (pop (), pop ());
            case MODULO:
                args = 2;
                double first = pop ();
                return pop () % first;
            case SIN:
                args = 1;
                return Math.sin (pop ());
            case COS:
                args = 1;
                return Math.cos (pop ());
            case TAN:
                args = 1;
                return Math.tan (pop ());
            case ASIN:
                args = 1;
                return Math.asin (pop ());
            case ACOS:
                args = 1;
                return Math.acos (pop ());
            case ATAN:
                args = 1;
                return Math.atan (pop ());
            default:
                return Double.MAX_VALUE;
            }
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
            System.out.printf ("Please enter at least %i numbers\n", args - stack.size ());
        }
        return 0;
    }

    private double doAction ()
    {
        return doAction (action.getText ());
    }

    /**
     * Takes an object out of the stack at the specified index.
     *
     * @param index index to pop
     * @return number popped from stack
     */
    protected double pop (int index) throws IndexOutOfBoundsException
    {
        double temp = stack.remove (index);
        stackList.setListData ((Vector<Double>) reverseVector((Vector<Double>) stack.clone ()));
        return temp;
    }

    /**
     * Takes an object out of the stack at 0.
     *
     * @return number popped from stack
     */
    protected double pop () throws IndexOutOfBoundsException
    {
        return pop (0);
    }

    /**
     * Pushes a number into the stack at the specified index.
     *
     * @param num number to push
     * @param index index to push at
     * @return the number that was just pushed back
     */
    protected double push (double num, int index) throws IndexOutOfBoundsException
    {
        if (num == Double.MAX_VALUE)
        {
            return 0.0;
        }
        if (stack.size () > 0)
        {
            double temp = stack.get (index);
            stack.add (index, num);
            stackList.setListData ((Vector<Double>) stack.clone ());
            return temp;
        }
        stack.add (index, num);
        stackList.setListData ((Vector<Double>) reverseVector((Vector<Double>) stack.clone ()));
        return num;
    }

    /**
     * Pushes at index 0 the specified number.
     *
     * @param num number to push
     * @return the number that was just pushed back
     */
    protected double push (double num) throws IndexOutOfBoundsException
    {
        return push (num, 0);
    }

    /**
     * Pushes the current number in the text field onto the stack.
     *
     * @return The number just pushed
     */
    protected double parseCurr ()
    {
        String numberS = action.getText ().replaceAll ("[^0-9\\.]", "");
        if (numberS.length () > 0)
        {
            double num = Double.valueOf (numberS);
            return num;
        }
        else
        {
            return 0.0;
        }
    }

    @Override
    public void actionPerformed (ActionEvent ae)
    {
        push (doAction ());
    }

    @Override
    public void itemStateChanged (ItemEvent ie)
    {
        stack.remove (stackList.getSelectedIndex ());
    }
    
    Vector<?> reverseVector (Vector<?> v1)
    {
        int high = v1.size () - 1;
        Vector v2 = new Vector (v1.size () + 1);
        for (int l = high;l >= 0;l++)
        {
            v2.set (l, v1.get (high-l));
        }
        return v2;
    }
}
