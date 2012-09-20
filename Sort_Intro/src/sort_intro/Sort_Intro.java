/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sort_intro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;

/**
 *
 * @author period5
 */
public class Sort_Intro
{

    static final int TRIES = 5;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        try
        {
            byte count = 0;
            Vector<Integer> favs = new Vector<Integer>();
            Integer temp;

            BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
            for (int j = 0; j < TRIES; j++)
            {
                System.out.println("Num:");

                while (!read.ready())
                {
                }
                String tmp = read.readLine();
                if (tmp.equals(""))
                {
                    j--;
                    continue;
                }
                temp = Integer.decode(tmp);
                favs.add(new Integer(temp));
            }
            for (int f = 0; f < TRIES; f++)
            {
                System.out.print(favs.get(f) + ", ");
            }
            System.out.println("OUT");
            sort(favs);
            for (int f = 0; f < TRIES; f++)
            {
                System.out.print(favs.get(f) + ", ");
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace(System.out);
        }
    }

    static void sort(Vector<Integer> nums)
    {
        int cap = nums.size();
        for (int l = cap - 1; l > 0; l--)
        {
            for (int i = cap - 1; i > 0; i--)
            {
                if (nums.get(i).intValue() > nums.get(i - 1).intValue())
                {
                    swap(nums, i, i - 1);
                }
            }
        }

    }

    static void swap(Vector<Integer> nums, int idex1, int idex2)
    {
        int temp = nums.get(idex1);
        nums.set(idex1, nums.get(idex2));
        nums.set(idex2, temp);
    }
}
