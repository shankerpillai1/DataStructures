// 2021 FALL CS 445 LAB #4  STUDENT STARTER FILE

import java.io.*;
import java.util.*;

public class LL_Recursive<T>
{
	private Node<T> head;  // pointer to the front (first) element of the list

	public LL_Recursive()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// INSERTS NEW NODE AT FRONT PUSHING EXISTING NODES BACK ONE PLACE
	public void insertAtFront(T data)
	{
		head = new Node<T>(data,head);
	}

 	// MUST USE (CALL) search() METHOD IN THIS CODE TO DETERMINE THE RETURN VALUE
 	// NO LOOPS ALLOWED NO RECURSION ALLOWED.  THE SEARCH WILL BE RECURSIVE THOUGH
 	public boolean contains( T key )
 	{
 		return (search(key)!=null);
	}

	// #####  W R I T E  (O R  R E-W R I T E)  T H E S E   M E T H O D S   R E C U R S I V E L Y ####

	// COPY ALL NODES FROM OTHER LIST INTO THIS LIST. WHEN COMPLETED THIS LIST WILL BE IDENTICAL TO OTHER
	// MUST USE RECURSION. THIS MUST BE A DEEP COPY OF THE OTHER LIST INTO THIS LIST
	// YOU WILL HAVE TO WRITE A HELPER METHOD. THE HELPER WILL BE RECURSIVE
	public LL_Recursive( LL_Recursive<T> other )
	{
		copyHelper( other.head );  // got you started here
	}
	private void copyHelper( Node<T> otherHead )
	{
		return;
	}

	// YOU MUST WRITE THIS USING RECURSION
	// YOU WILL HAVE TO WRITE A HELPER METHOD. THE HELPER WILL BE RECURSIVE
	public void insertAtTail(T data)
	{
		boolean curr=true;
        if(head==null)
            insertAtFront(data);
        else
            curr=tailHelper(data, head);
	}

    public boolean tailHelper(T data, Node<T> n1)
    {
        if(n1.next==null)
        {
            n1.next=new Node<T>(data, null);
            return true;
        }
        return tailHelper(data, n1.next);
    }
 

	// WE DID THIs IN CLASS Oct 17/18th
	public int size()
	{
		return sizeHelper( head ); 
	}
	private int sizeHelper( Node<T> head )
	{
		if (head==null) return 0;
		return 1 + sizeHelper(head.next);
	}
	
	// USE THE TOSTRING AS OUR PRINT.  ***MUST RE-WRITE USING RECURSION***
	// YOU WILL HAVE TO WRITE A HELPER METHOD. THE HELPER WILL BE RECURSIVE
	public String toString()
	{
		String toString = "";

		/*for (Node<T> curr = head; curr != null; curr = curr.next)
		{
			toString += curr.data;		// WE ASSUME OUR T TYPE HAS toString() DEFINED
			if (curr.next != null)
				toString += " -> ";
		}*/
        if(head!=null)
		    return tsHelper(head) + "\n";
        else return "";
	}

    public String tsHelper(Node<T> n1)
    {
        if(n1.next==null) return ""+n1.data;
        return n1.data+" -> "+tsHelper(n1.next);
    }

	// MUST BE RECURSIVE. YOU WILL HAVE TO WRITE A HELPER METHOD. THE HELPER WILL BE RECURSIVE
	public Node<T> search( T key )
	{
		return sHelper(key, head);
	}

    public Node<T> sHelper(T key, Node<T> n1)
    {
        if(n1.data.equals(key)) return n1;
        if(n1.next==null) return null;
        return sHelper(key, n1.next);
    }
} //END OF LL_Recursive CLASS


///////////////////////////////////////////////////////////////////////////////////////////////////

class Node<T>
{ T data;
  Node<T> next;
  Node() { this( null, null ); }
  Node(T data){this( data, null ); }
  Node(T data, Node<T> next) { this.data=data; this.next=next; }
  public String toString() { return ""+data; }
} //END OF NODE CLASS