/* 
 * File:   main.c
 * Author: mark
 *
 * Created on 13. February 2013, 12:36
 */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>

/*
 * 0 = Insertion
 * 1 = Quick
 * 2 = Gnome
 */
#define TYPE 1

#define RANDOM 5
#define UPPER 10
#define LOWER -10

#define ARRAY_SIZE 1000

// Does an Insertion sort of the array of size numbers.
// Returns number of iterations needed.
int insertion (double (*insert_arr)[ARRAY_SIZE], int size);
int quicksort (double (*insert_arr)[ARRAY_SIZE], int size);
int gnomesort (double (*insert_arr)[ARRAY_SIZE], int size);

/*
 * 
 */
int main (int argc, char** argv)
{
	double array[ARRAY_SIZE];
	int i = 0, cnt = -1;
	srand (time (NULL));
#if !RANDOM
	printf ("Please input a list of numbers to sort... ");
	while (scanf ("%lf", &(array[i])) > 0)
	{
		//malloc(1000);
		//memmove(malloc((i + 2)*sizeof(double)), &array, (i + 1)*sizeof(double));
		//printf ("%lf\n", array[i]);
		i++;
	}
#else
	double rnd;
	while (i < RANDOM)
	{
		rnd = LOWER + (rand () / (UPPER - LOWER));
		//printf ("%0.1f ", rnd);
		array[i++] = rnd;
	}
	//printf ("\n\n");
#endif
#if TYPE == 0
	cnt = insertion (&array, i);
#elif TYPE == 1
	cnt = quicksort (&array, i);
#elif TYPE == 2
	cnt = gnomesort (&array, i);
#endif
	printf ("The contents of the array are: ");
	int j;
	for (j = 0; j < i; j++)
	{
		printf ("%0.1lf ", array[j]);
	}
	printf ("\nIt took this many swaps: %d", cnt);
	//printf ("\nIt took this many compares: %d", comp);
	return (EXIT_SUCCESS);
}

int insertion (double (*insert_arr)[ARRAY_SIZE], int size)
{
	int count = 0;
	int i = 0, j;
	double tmp;
	for (i = 0; i < size; i++)
	{
		tmp = (*insert_arr)[i];

		for (j = i - 1; j >= 0; j--)
		{
			if ((*insert_arr)[j] <= tmp)
			{
				break;
			}
			count++;
			(*insert_arr)[j + 1] = (*insert_arr)[j];
		}
		(*insert_arr)[j + 1] = tmp;
	}
	return count;
}

int quicksort (double (*insert_arr)[ARRAY_SIZE], int size)
{
	if (size <= 1)
		return 1;
	int count = 0;
	double low[size], high[size], equal[size];
	//srand(time(NULL));
	int i = 0, h = 0, l = 0, e = 0, u = 0;
	int pivot;
#if 1
	pivot = ((size - 1) / 2);
#else
	pivot = size * rand ();
#endif
	double tmp = 0;
	tmp = (*insert_arr)[pivot];
	for (i = 0; i < size; i++)
	{
		if (i == pivot)
		{
			continue;
		}
		if (tmp > (*insert_arr)[i])
		{
			count++;
			low[l++] = (*insert_arr)[i];
		}
		else if (tmp < (*insert_arr)[i])
		{
			count++;
			high[h++] = (*insert_arr)[i];
		}
		else if (tmp = (*insert_arr)[i])
		{
			count++;
			equal[e++] = (*insert_arr)[i];
		}
	}

	/*printf ("\n\n-------------------------\n");
	printf ("Before Organisation\n");
	printf ("Pivot index %d is %0.1lf", pivot, tmp);
	printf ("\nlow (%d) ", l);
	for (u = 0; u < l; printf ("%0.1lf ", low[u++]));
	printf ("\nequal (%d) ", e);
	for (u = 0; u < e; printf ("%0.1lf ", equal[u++]));
	printf ("\nhigh (%d) ", h);
	for (u = 0; u < h; printf ("%0.1lf ", high[u++]));
	printf ("\n\n");*/


	if (l > 1)
		count += quicksort (&low, l);
	if (h > 1)
		count += quicksort (&high, h);

	/*printf ("After Organisation\n");
	printf ("Pivot index %d is %0.1lf\n", pivot, tmp);
	printf ("low (%d) ", l);
	for (u = 0; u < l; printf ("%0.1lf ", low[u++]));
	printf ("\nequal (%d) ", e);
	for (u = 0; u < e; printf ("%0.1lf ", equal[u++]));
	printf ("\nhigh (%d) ", h);
	for (u = 0; u < h; printf ("%0.1lf ", high[u++]));
	printf ("\n____________________________\n\n");*/



	i = 0;

	/*printf ("\nBefore: ");
	for (u = i - 1; ++u < size; printf ("index%0d=%0.1lf, ", u, (*insert_arr)[u]));*/

	memcpy (&(*insert_arr)[i], low, (l) * sizeof (double));
	/*printf ("\nFrom Low: ");
	for (u = i - 1; ++u < i + l; printf ("index%d=%0.1lf, ", u, (*insert_arr)[u]));*/

	i += l;

	memcpy (&(*insert_arr)[i], &tmp, 1 * sizeof (double));
	/*printf ("\nFrom Pivot (%0.1lf): ", tmp);
	for (u = i - 1; ++u < i + 1; printf ("index%d=%0.1lf, ", u, (*insert_arr)[u]));*/

	i += 1;

	memcpy (&(*insert_arr)[i], equal, (e) * sizeof (double));
	/*printf ("\nFrom Equal: ");
	for (u = i - 1; ++u < i + e; printf ("index%d=%0.1lf, ", u, (*insert_arr)[u]));*/

	i += e;

	memcpy (&(*insert_arr)[i], high, (h) * sizeof (double));
	/*printf ("\nFrom High: ");
	for (u = i - 1; ++u < i + h; printf ("index%d=%0.1lf, ", u, (*insert_arr)[u]));*/

	i += h;

	/*printf ("\n\nfinal: ");
	for (u = -1; ++u < size; printf ("index%d=%0.1lf, ", u, (*insert_arr)[u]));*/

	/*printf ("\n========================================\n\n\n");*/
	return count;
}

int gnomesort (double (*insert_arr)[ARRAY_SIZE], int size)
{
	int i = 0, curr = 2, temp = 0, count = 0;
	while (i < size)
	{
		if (i == 0 || (*insert_arr) [i - 1] <= (*insert_arr)[i])
		{
			i++;
		}
		else
		{
			count++;
			temp = (*insert_arr)[i];
			(*insert_arr)[i] = (*insert_arr)[i - 1];
			(*insert_arr)[--i] = temp;
			continue;
		}
		i = curr++;
	}
	return count;
}