/*
	Deck class
*/

import java.util.*;
import java.io.*;

public class Deck
{
	private int[] deck;
	private final int MAX_DECK_SIZE = 20;
	public Deck( int numCards )
	{	if ( numCards%2 != 0 || numCards > MAX_DECK_SIZE ) 
		{
			System.out.format("\nINVALID DECK SIZE: (" + numCards + "). Must be an small even number <= %d\n", MAX_DECK_SIZE);
			System.exit(0);
		}

		deck=new int[numCards];// YOU DO THIS => init deck to be exactly numCards long
		
		for(int i=0; i<numCards; i++)
		{
			deck[i]=i;		// YOU DO THIS => fill deck with with 0 1 2 3 ... numCards-1 in order
		}
	}
	
	public String toString()
	{
		String deckStr = "";
		for ( int i=0 ; i < deck.length ; ++i )
			deckStr += deck[i] + " ";
		return deckStr;
	}

	// ONLY WORKS ON DECK WITH EVEN NUMBER OF CARDS
	// MODIFIES THE MEMBER ARRAY DECK
	public void inShuffle()
	{
		int counter=0;
		int [] newdeck=new int[deck.length];
		for(int i=0; i<deck.length/2; i++)
		{
			newdeck[counter]=deck[(deck.length/2)+i];
			newdeck[counter+1]=deck[i];
			counter+=2;
		}
		this.deck=newdeck;
	}

	// ONLY WORKS ON DECK WITH EVEN NUMBER OF CARDS
	// MODIFIES THE MEMBER ARRAY DECK
	public void outShuffle()
	{
		int counter=0;
		int [] newdeck=new int[deck.length];
		for(int i=0; i<deck.length/2; i++)
		{
			newdeck[counter]=deck[i];
			newdeck[counter+1]=deck[(deck.length/2)+i];
			counter+=2;
		}
		this.deck=newdeck;	
	}
	
	// RETURNS TRUE IF DECK IN ORIGINAL SORTED:  0 1 2 3 ...
	public boolean inSortedOrder()
	{
		for(int i=0; i<deck.length; i++)
		{
			if(deck[i]!=i)
				return false; 
		}
		return true;
	}
}	// END DECK CLASS