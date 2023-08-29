import java.io.*;
import java.util.*;

public class L2_SetOps
{
	public static void main( String args[] ) throws Exception
	{
		BufferedReader infile1 = new BufferedReader( new FileReader( args[0] ) );
		BufferedReader infile2 = new BufferedReader( new FileReader( args[1] ) );

		String[] set1 = loadSet( infile1 );
		Arrays.sort( set1 );
		String[] set2 = loadSet( infile2 );
		Arrays.sort( set2 );
		printSet( "set1: ",set1 );
		printSet( "set2: ",set2 );

		String[] union = union( set1, set2 );
		Arrays.sort( union );
		printSet( "\nunion: ", union );


		String[] intersection = intersection( set1, set2 );
		Arrays.sort( intersection );
		printSet( "\nintersection: ",intersection );

		String[] difference = difference( set1, set2 );
		Arrays.sort( difference );
		printSet( "\ndifference: ",difference );

		String[] xor = xor( set1, set2 );
		Arrays.sort( xor );
		printSet("\nxor: ", xor );

		System.out.println( "\nSets Echoed after operations.");

		printSet( "set1: ", set1 );
		printSet( "set2: ", set2 );

	}// END MAIN

	// USE AS GIVEN - DO NOT MODIFY
	// CAVEAT: This method will not work *correctly* until you write a working doubleLength() method.

	static String[] loadSet( BufferedReader infile ) throws Exception
	{
		final int INITIAL_LENGTH = 5;
		int count=0;
		String[] set = new String[INITIAL_LENGTH];
		while( infile.ready() )
		{
				if (count >= set.length)
					set = doubleLength( set );
				set[ count++ ] = infile.readLine();
		}
		infile.close();
		return trimArray( set, count );
	}

	// USE AS GIVEN - DO NOT MODIFY
	static void printSet( String caption, String [] set )
	{
		System.out.print( caption );
		for ( String s : set )
			System.out.print( s + " " );
		System.out.println();
	}


	/* ###############################################################
		For each of the following set operations you must execute the following steps:
		1) dimension an array that is just big enough to handle the largest possible set for that operation.
		2) add the appropriate elements to the array as the operation prescribes.
		3) before returning the array, resize it to the exact size as the number of elements in it.
	*/

	static String[] union( String[] set1, String[] set2 )
	{
		String [] arr=new String[set1.length+set2.length];
        int counter=0;

        for(int i=0; i<set1.length; i++)
        {
            if(!contains(arr, set1[i], counter))
            {
                arr[counter]=set1[i];
                counter++;
            }
        }

        for(int i=0; i<set2.length; i++)
        {
            if(!contains(arr, set2[i], counter))
            {
                arr[counter]=set2[i];
                counter++;
            }
        }
        
        return trimArray(arr, counter);
	}

	static String[] intersection( String[] set1, String[] set2 )
	{
		String[] arr=new String[set1.length];
        int counter=0;

        for(int i=0; i<set1.length; i++)
        {
            if((contains(set2, set1[i], set2.length))&&(!(contains(arr, set1[i], counter))))
            {
                arr[counter]=set1[i];
                counter++;
            }
        }
        return trimArray(arr, counter);
	}

	static String[] difference( String[] set1, String[] set2 )
	{
		String[] arr=new String[set1.length];
        int counter=0;

        for(int i=0; i<set1.length; i++)
        {
            if((!contains(set2, set1[i], set2.length))&&(!contains(arr, set1[i], counter)))
            {
                arr[counter]=set1[i];
                counter++;
            }
        }
        return trimArray(arr, counter);
	}

	static String[] xor( String[] set1, String[] set2 )
	{
		return difference(union(set1,set2), intersection(set1, set2)); 
	}

	// return an array of length 2x with all data from the old array stored in the new array
	static String[] doubleLength( String[] old )
	{
		String[] arr=new String[old.length*2];

        for(int i=0; i<old.length; i++)
            arr[i]=old[i];

        return arr;
	}

	// return an array of length==count with all data from the old array stored in the new array
	static String[] trimArray( String[] old, int count )
	{
		String[] arr=new String[count];

        for(int i=0; i<count; i++)
        {
            arr[i]=old[i];
        }
        return arr;
	}

    static boolean contains(String[] arr, String str, int c)
    {
        for(int i=0; i<c; i++)
        {
            if(arr[i].equalsIgnoreCase(str))
            {
                return true;
            }
        }
        return false;
    }

} // END CLASS