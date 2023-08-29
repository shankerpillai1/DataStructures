import java.io.*;
import java.util.*;

public class CDLL_JosephusList<T>
{
	private CDLL_Node<T> head;  // pointer to the front (first) element of the list
	private int count=0;
	// private Scanner kbd = new Scanner(System.in); // FOR DEBUGGING. See executeRitual() method 
	public CDLL_JosephusList()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FORM INCOMING FILE
	
	public CDLL_JosephusList( String infileName ) throws Exception
	{
		BufferedReader infile = new BufferedReader( new FileReader( infileName ) );	
		while ( infile.ready() )
		{	@SuppressWarnings("unchecked") 
			T data = (T) infile.readLine(); // CAST CUASES WARNING (WHICH WE CONVENIENTLY SUPPRESS)
			insertAtTail( data ); 
		}
		infile.close();
	}
	

	
	// ########################## Y O U   W R I T E / F I L L   I N   T H E S E   M E T H O D S ########################
	
	// TACK ON NEW NODE AT END OF LIST
	@SuppressWarnings("unchecked")
	public void insertAtTail(T data)
	{
		CDLL_Node<T> curr=head;
        if(size()==0)	
        {
			curr=new CDLL_Node<T>(data);
            curr.next=curr;
            curr.prev=curr;
            curr.next=curr;
            curr.prev=curr;
            head=curr;
            return;
		}
        else
        {
            while(!curr.next.equals(head))
                curr=curr.next;

            curr=new CDLL_Node<T>(data, curr, head);
            head.prev=curr;
            curr.prev.next=curr;
        }
	}

	
	public int size()
	{	
		if(head==null)
            return 0;
        int counter=1;
        CDLL_Node<T> curr=head.prev;
        while(!curr.data.equals(head.data))
        {
            counter++;
            curr=curr.prev;
        }
        return counter;
	}
	
	// RETURN REF TO THE FIRST NODE CONTAINING  KEY. ELSE RETURN NULL
	public CDLL_Node<T> search( T key )
	{	
		if(size()==0)
			return null;
		else
		{
			CDLL_Node<T> curr=head;
			do
			{   
				if(curr.data.equals(key))
					return curr;
				curr=curr.next;
			}
			while(!curr.equals(head));
		}
		return null;
	}
	
	// RETURNS CONATENATION OF CLOCKWISE TRAVERSAL
	@SuppressWarnings("unchecked")
	public String toString()
	{
		String s="";
        if(size()==0)
		    return "";
        s=(String)head.data;
        CDLL_Node<T> curr=head.next;

        while(!curr.equals(head))
        {
            s=s+"<=>"+(String)curr.data;
            curr=curr.next;
        }
        return s;
	}
	
	void removeNode( CDLL_Node<T> deadNode )
	{
		deadNode.prev.next=deadNode.next;
		deadNode.next.prev=deadNode.prev;
	}
	
	public void executeRitual( T first2Bdeleted, int skipCount )
	{
		if (size() <= 1 ) return;
		CDLL_Node<T> curr = search( first2Bdeleted );
		if ( curr==null ) return;
		
		// OK THERE ARE AT LEAST 2 NODES AND CURR IS SITING ON first2Bdeleted
		//do
		//{
			CDLL_Node<T> deadNode = curr;
			T deadName = deadNode.getData();
			
			System.out.println( "stopping on "+curr.data+" to delete "+curr.data);
			
			// BEFORE DOING ACTUAL DELETE DO THESE TWO THINGS 
			removeNode(deadNode);
			String direc="";
			// 1: you gotta move that curr off of the deadNode. 
			//    if skipCount poitive do curr=curr.next  esle do  curr=curr.prev
			if(skipCount>0)
			{
				curr=curr.next;
				direc="CLOCKWISE";
			}
			else
			{
				curr=curr.prev;
				direc="COUNTER_CLOCKWISE";
			}
			// 2: check to see if HEAD is pointing to the deadnode. 
			//    If so make head=curr 
			if(head.data.equals(deadNode.data))
				head=curr;
			
			// NOW DELETE THE DEADNODE

			System.out.println("deleted. list now: "+ toString() ); // toString prints the
			
			// if the list size has reached 1 return YOU ARE DONE.  RETURN RIGHT HERE
	
			System.out.println("resuming at "+curr.data+", skipping "+curr.data + ((Math.abs(skipCount))-1)+" nodes "+direc +" after");
			
			// write loop that advances curr pointer skipCount times (be sure of CLOCKWISE or COUNTER)
			if(skipCount>0)
			{
				for(int i=0; i<skipCount; i++)
					curr=curr.next;
			}
			else
				for(int i=0; i>skipCount; i--)
					curr=curr.prev;

			// OPTIONAL HERE FOR DEBUGGING TO MAKE IT STOP AT BOTTOM OF LOOP
			// Scanner kbd = new Scanner( System.in ); String junk = kbd.nextLine();   
			
		//}
		//while (size() > 1 );  // ACTUALLY COULD BE WHILE (TRUE) SINCE WE RETURN AS SOON AS SIZE READES 1
		executeRitual(curr.data, skipCount);

	}

class CDLL_Node<T>
{
  private T data;
  private CDLL_Node<T> prev, next; // EACH CDLL_Node PTS TO ITS PREV  & NEXT

  public CDLL_Node()
  {
    this( null, null, null );  // 3 FIELDS TO INIT
  }

  public CDLL_Node(T data)
  {
    this( data, null, null);
  }

  public CDLL_Node(T data, CDLL_Node<T> prev, CDLL_Node<T> next)
  {
    setData( data );
	setPrev( prev );
    setNext( next );
  }

  public T getData()
  {
    return data;
  }

  public CDLL_Node<T> getPrev()
  {
    return prev;
  }
  public CDLL_Node<T> getNext()
  {
    return next;
  }

  public void setData(T data)
  {
     this.data = data;
  }

  public void setNext(CDLL_Node<T> next)
  {
    this.next = next;
  }
  
  public void setPrev(CDLL_Node<T> prev)
  {
    this.prev = prev;
  }
 
  public String toString()
  {
	  return ""+getData();
  } 
	 
} //EOF
	
} // END CDLL_LIST CLASS