/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dotfps;

/**
 *
 * @author mark
 */
public enum Levels
{

    WOLRD1 (1, 2, new int[]
{
1, 2, 3, 4, 5, 6, 7, 8
}, new int[]
{
1, 4, 2, 6, 4, 7, 8, 7
});
    private final int masterX, masterY;
    private final int[] enemyX, enemyY;

    Levels (int masterX, int masterY, int[] enemyX, int[] enemyY)
    {
        this.masterX = masterX;
        this.masterY = masterY;
        this.enemyX = enemyX;
        this.enemyY = enemyY;
    }
}
