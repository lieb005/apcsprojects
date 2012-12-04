/* 
 * File:   main.c
 * Author: mark
 *
 * Created on 15. November 2012, 15:07
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define SIZE 10
#define STR_LEN 4

char array[SIZE][SIZE][STR_LEN];

void on(int x, int y);
void off(int x, int y);
void seed();
void reset();
void selectPlace();
int howManyTries();
double findAverageTries(int seed);

/*
 * 
 */
int main(int argc, char** argv)
{
    printf("Hello World!");
    //seed();
    reset();
    printf("%f", findAverageTries(6));
    return (EXIT_SUCCESS);
}

void on(int x, int y)
{
    strncpy(array[x % SIZE][y % SIZE], "off", STR_LEN);
}

void off(int x, int y)
{
    strncpy(array[x % SIZE][y % SIZE], "off", STR_LEN);
}

void seed()
{
    printf("%d", (int) time(NULL));
    srand((unsigned) time(0) - rand());
}

void reset()
{
    int i = 0, j = 0;
    for (; i < SIZE; i++)
    {
        for (; j < SIZE; j++)
        {
            off(i, j);
        }
    }
}

void selectPlace()
{
    seed();
    int i = rand();
    seed();
    int j = rand();
    on(i, j);

}

int howManyTries()
{
    int count = 0;

    while (strncmp(array[rand()][rand()], "on", 2) != 0)
    {
        count++;
        seed();
    }
    return count;
}

double findAverageTries(int seed)
{
    int total = 0, i = 0;
    reset();
    for (; i < seed; i++)
    {
        selectPlace();
        total += howManyTries();
    }
    return ((double) total / (double) seed);
}
