/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dotfps;

import dotfps.Level;
/**
 *
 * @author mark
 */
public enum Levels
{

    WOLRD1 (0, 0, new int[]
{
1, 2, 3, 4, 5, 6, 7, 8, 13, 17, 19, 21, 22, 25, 30, 40
}, new int[]
{
1, 4, 2, 6, 4, 7, 8, 7, 4, 8, 2, 6, 2, 5, 9, 4
});
    private final int masterX, masterY;
    private final int[] enemyX, enemyY;

    Levels (int masterx, int mastery, int[] enemyx, int[] enemyy)
    {
        masterX = masterx;
        masterY = mastery;
        enemyX = enemyx;
        enemyY = enemyy;
    }

    static public Levels getLevels (int i)
    {
        switch (i)
        {
        case 1:
            return WOLRD1;
        }
        return null;
    }

    public Level getLevel ()
    {
        return new Level (masterX, masterY, enemyX, enemyY);
    }
}
