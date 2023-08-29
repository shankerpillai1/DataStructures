import java.io.*;
import java.util.*;

public class Pacs
{	public static void main( String args[] ) throws Exception
	{	BufferedReader memberToPacsFile = new BufferedReader(new FileReader( "member2Pacs.txt"));
		BufferedReader AllPacsFile= new BufferedReader(new FileReader("allPacs.txt"));
		TreeSet<String> allPacs= new TreeSet<String>();
		while( AllPacsFile.ready())
			allPacs.add(AllPacsFile.readLine());			
		
		TreeMap<String, TreeSet<String>> pacToMembers = new TreeMap<String, TreeSet<String>>(); // THE MAP THAT GETS PRINTED	
		//POPULATE THE TREE MAP ABOVE
        /*while(memberToPacsFile.ready())
        {
            TreeSet<String> members=new TreeSet<String>();
            ArrayList<String> pacs=new ArrayList<String>(Arrays.asList(memberToPacsFile.readLine().split(" ")));
            String member=pacs.get(0);
            pacs.remove(0);
            members.add(member);
        }*/
        ArrayList<String> lines=new ArrayList<String>();
        while(memberToPacsFile.ready())
        {
            lines.add(memberToPacsFile.readLine());
        }
        for(String pac: allPacs)
        {
            TreeSet<String> listMem=new TreeSet<String>();
            for(String s: lines)
            {
                ArrayList<String> pacs=new ArrayList<String>(Arrays.asList(s.split(" ")));
                if(pacs.contains(pac))
                    listMem.add(pacs.get(0));
            }
            pacToMembers.put(pac, listMem);
        }
		//NOw PRINT THAT MAP (see output)
        for(String pac: pacToMembers.keySet())
        {
            System.out.print(pac+ " ");
            for(String member: pacToMembers.get(pac))
                System.out.print(member+ " ");
            System.out.println();
        }
	} // END MAIN
} // CLASS