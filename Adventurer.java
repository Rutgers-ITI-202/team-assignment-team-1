/*
An adventurer has:
�	A location (some room).
�	An inventory (the things being carried).  When the player executes the �take thing� command, the item should be added to the inventory.

An adventurer can:
�	Move from room to room.
�	Carry a number of objects.
�	Pick up, drop, look at, and use various objects.
*/

package adventure;

import java.util.*;

public class Adventurer {

	int location;
	ArrayList<Item> items;
	
	Adventurer(int l, ArrayList<Item> i){
		this.location = l;
		this.items = i;
	}
	
}