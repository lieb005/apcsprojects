/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package palindrome;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author period5
 */
public class Palindrome extends JApplet implements KeyListener
{

    JTextField in = new JTextField();
    JTextField out = new JTextField();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Palindrome p = new Palindrome();
        JFrame f = new JFrame("Palindrome");
        f.add(p);
        p.init();
        p.start();
        f.pack();
        f.setSize(300, f.getHeight()+20);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void init()
    {

        in.addKeyListener(this);
        out.setEditable(false);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(in, "North");
        panel.add(out, "South");
        add(new JScrollPane(panel));
    }

    @Override
    public void keyTyped(KeyEvent ke)
    {
        out.setText(flipString(in.getText()));
        validate();
    }

    @Override
    public void keyPressed(KeyEvent ke)
    {
    }

    @Override
    public void keyReleased(KeyEvent ke)
    {
        out.setText(flipString(in.getText()));
        validate();
    }

    String flipString(String s)
    {
        String tmp = "";
        for (int i = s.length() - 1; i >= 0; i--)
        {
            tmp += s.charAt(i);
        }
        return tmp;
    }
}
