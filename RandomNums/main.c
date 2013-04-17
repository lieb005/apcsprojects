/* 
 * File:   main.c
 * Author: mark
 *
 * Created on 24. September 2012, 11:34
 */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>


#define MAX (double)(1000/RAND_MAX)

/*
 * 
 */
int main(int argc, char** argv) {
    int temp = 0, count = 0;
    while ((temp = (int) ((double)rand() * MAX)) != (int) ((double)rand() * MAX) || (temp != rand() * MAX)/* || (temp != rand() * MAX)*/) {
        srand((unsigned)time(0)-rand()); 
        count++;
    }
    printf("It took %i repititions", count);
    return (EXIT_SUCCESS);
}

