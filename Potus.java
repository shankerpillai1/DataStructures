import java.util.*;
import java.io.*;

public class Potus
{
	public static void main( String[] args )  throws Exception
	{
		BufferedReader state2PresidentsFile = new BufferedReader( new FileReader("state2Presidents.txt") );
		TreeMap<String,TreeSet<String>> state2Presidents= new TreeMap<String,TreeSet<String>>();

		BufferedReader allPresidentsFile = new BufferedReader( new FileReader("allPresidents.txt") );
		TreeSet<String> allPresidents = new TreeSet<String>();

		BufferedReader allStatesFile = new BufferedReader( new FileReader("allStates.txt") );
		TreeSet<String> allStates = new TreeSet<String>();

		System.out.println( "THESE STATES HAD THESE POTUS BORN IN THEM:\n");

        while(state2PresidentsFile.ready())
        {
            ArrayList<String> presidents=new ArrayList<String>(Arrays.asList(state2PresidentsFile.readLine().split(" ")));
            String state=presidents.get(0);
            presidents.remove(0);
            state2Presidents.put(state, new TreeSet<String>(presidents));
        }

        for(String state: state2Presidents.keySet())
        {
            System.out.print(state+ " ");
            for(String pres: state2Presidents.get(state))
                System.out.print(pres+" ");

            System.out.println();
        }

		System.out.println( "\nLIST OF POTUS AND STATE THEY WERE BORN IN:\n");
        TreeSet<String> nonNative=new TreeSet<String>();
        while(allPresidentsFile.ready())
            allPresidents.add(allPresidentsFile.readLine());
        
        for(String pres: allPresidents)
        {
            if(treeHasElement(pres, state2Presidents))
                System.out.println(pres+ " "+getState(pres, state2Presidents));
            else
                nonNative.add(pres);
        }
		System.out.println( "\nTHESE POTUS BORN BEFORE STATES WERE FORMED:\n");

        for(String pres: nonNative)
            System.out.println(pres);
		System.out.println( "\nTHESE STATES HAD NO POTUS BORN IN THEM:\n");	
        while(allStatesFile.ready())
            allStates.add(allStatesFile.readLine());

        for(String s: allStates)
        {
            if(!state2Presidents.keySet().contains(s))
                System.out.println(s);
        }
	} // END MAIN

	//       - - - - - - - - - - -  H E L P E R    M E T H O D S - - - - - - - -
    public static boolean treeHasElement(String pres, TreeMap<String, TreeSet<String>> state2Presidents)
    {
        for(String state: state2Presidents.keySet())
        {
            for(String p: state2Presidents.get(state))
            {
                if(p.equals(pres))
                    return true;
            }
        }
        return false;
    }

    public static String getState(String pres, TreeMap<String, TreeSet<String>> state2Presidents)
    {
        for(String state: state2Presidents.keySet())
        {
            for(String p: state2Presidents.get(state))
            {
                if(p.equals(pres))
                    return state;
            }
        }
        return "";
    }
	
}	// END CLASS
