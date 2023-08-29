import java.io.*;
import java.util.*;

// NOTICE THE "<T extends Comparable<T>>"
// using <T extends Comparable<T>> in here means compiler wont let the code in main send in any T type
// that does not implement Comparable.  Now we do not have to cast the incoming key to a Comparable
// in our insertInOrder() method. Compiler now lets us call .compareTo off the dot on the incoming key
// without throwing an error.

public class LinkedList<T extends Comparable<T>> 
{
	private Node<T> head;  // pointer to the front (first) element of the list

	public LinkedList()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FROM INCOMING FILE
	@SuppressWarnings("unchecked")
	public LinkedList( String fileName, boolean orderedFlag )
	{	head = null;
		try
		{
			BufferedReader infile = new BufferedReader( new FileReader( fileName ) );
			while ( infile.ready() )
			{
				if (orderedFlag)
					insertInOrder( (T)infile.readLine() );  // WILL INSERT EACH ELEM INTO IT'S SORTED POSITION
				else
					insertAtTail( (T)infile.readLine() );  // TACK EVERY NEWELEM ONTO END OF LIST. ORIGINAL ORDER PRESERVED
			}
			infile.close();
		}
		catch( Exception e )
		{
			System.out.println( "FATAL ERROR CAUGHT IN C'TOR: " + e );
			System.exit(0);
		}
	}

	//-------------------------------------------------------------

	// inserts new elem at front of list - pushing old elements back one place

	public void insertAtFront(T data)
	{
		head = new Node<T>(data,head);
	}

	// we use toString as our print

	public String toString()
	{
		String toString = "";

		for (Node<T> curr = head; curr != null; curr = curr.next )
		{
			toString += curr.data;		// WE ASSUME OUR T TYPE HAS toString() DEFINED
			if (curr.next != null)
				toString += " ";
		}

		return toString;
	}

	// ########################## Y O U   W R I T E    T H E S E    M E T H O D S ########################



	public int size() // OF COURSE MORE EFFICIENT to KEEP COUNTER BUT YOU  MUST WRITE LOOP
	{
		int x=1;
		if(head==null) return 0;
		Node<T> n1=head;
		while(n1.next!=null)
		{
			x++;
			n1=n1.next;
		}
		return x;
	}

	public boolean empty()
	{
		return (size()==0);  // YOUR CODE HERE
	}

	public boolean contains( T key )
	{
		return (search(key)!=null);  // YOUR CODE HERE
	}

	public Node<T> search( T key )
	{
		if(!empty())
		{
			for(Node<T> curr = head; curr != null; curr = curr.next )
			{
				if(curr.data.equals(key)) return curr;
			}
			return null;
		}
		else return null;
	}

	// TACK A NEW NODE (CABOOSE) ONTO THE END OF THE LIST
	public void insertAtTail(T data)
	{
		Node<T> curr=head;
		if(empty()) head=new Node<T> (data, null);
		else
		{
			while(curr.next!=null) curr=curr.next;
			curr.next=new Node<T> (data, null);
		}
		
		
	}

	// IF YOU DEFINE <T> at the top of this class as <T implements Comparable>
	// YOU DO NOT HAVE TO CAST TO COMPARABLE AND YOU DO NOT NEED TO SUPPRESS 
	public void insertInOrder(T  data)
	{
		if(empty()||data.compareTo(head.data)<0)
		{
			insertAtFront(data);
			return;
		}

		Node<T> curr=head;
		while(curr.next!=null&&(data.compareTo(curr.next.data)>0))
		{
			curr=curr.next;
		}

		/*if(data.compareTo(curr.data)<0)
		{
			curr=new Node<T> (data, curr);
			return;
		}*/

		curr.next=new Node<T> (data, curr.next);
				
				
		
	}

	public boolean remove(T key)
	{
		Node<T> dead;
		if(!contains(key)) return false; //  REPLACE WITH YOUR CODE 
		if(head.data.compareTo(key)==0)
		{
			removeAtFront();
			return true;
		}
		for(Node<T> curr = head; curr != null; curr = curr.next)
		{
			if(curr.data.compareTo(key)==0)
			{
				dead=curr;
				curr=head;
				while(curr.next.data.compareTo(dead.data)!=0)
					 curr=curr.next;

				curr.next=dead.next;
				return true;
			}
		}
		return true;
	}

	public boolean removeAtTail()	// RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		if(head==null) return false; 
		if(head.next==null)
		{
			head=null;
			return true;
		}
		Node<T> curr=head;
		
		while(curr.next.next!=null) curr=curr.next;
		curr.next=null;
		return true;
	}

	public boolean removeAtFront() // RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		if (head==null) return false; // YOUR CODE HERE
		head=head.next;
		return true;
	}

	public LinkedList<T> union( LinkedList<T> other )
	{
		LinkedList<T> union = new LinkedList<T>();

		for(Node<T> curr=this.head; curr!=null; curr=curr.next)
		{
			if(!union.contains(curr.data))
			{
				union.insertInOrder(curr.data);
			}
		}
		for(Node<T> curr=other.head; curr!=null; curr=curr.next)
		{
			if(!union.contains(curr.data))
			{
				union.insertInOrder(curr.data);
			}
		}
		
		// YOUR CODE HERE

		return union;
	}
	public LinkedList<T> inter( LinkedList<T> other )
	{
		LinkedList<T> inter = new LinkedList<T>();
		
		for(Node<T> curr=this.head; curr!=null; curr=curr.next)
		{
			if(other.contains(curr.data)&&!inter.contains(curr.data)) inter.insertInOrder(curr.data);
		}
		// YOUR CODE HERE

		return inter;
	}
	public LinkedList<T> diff( LinkedList<T> other )
	{
		LinkedList<T> diff = new LinkedList<T>();

		for(Node<T> curr=this.head; curr!=null; curr=curr.next)
		{
			if(!other.contains(curr.data)&&!diff.contains(curr.data)) diff.insertInOrder(curr.data);
		}

		return diff;
	}
	public LinkedList<T> xor( LinkedList<T> other )
	{
		return  union(other).diff(inter(other));  // REPLACE WITH YOUR CODE 
	}

} //END LINKEDLIST CLASS 

// A D D   N O D E   C L A S S  D O W N   H E R E 
// R E M O V E  A L L  P U B L I C  &  P R I V A T E (except toString)
// R E M O V E  S E T T E R S  &  G E T T E R S 
// M A K E  T O  S T R I N G  P U B L I C

class Node<T extends Comparable<T>> // tells compiler our incoming T type implements Comparable

{
  T data;
  Node<T> next;

  Node()
  {
    this( null, null );
  }

  Node(T data)
  {
    this( data, null );
  }

  Node(T data, Node<T> next)
  {
    this.data=data;
    this.next=next;
  }

  public String toString()
  {
	  return (""+this.data);
  } 
	 
} //EOF