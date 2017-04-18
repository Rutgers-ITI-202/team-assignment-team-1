package adventure;

import java.util.*;

public class Room {

	String name;
	String desc;
	ArrayList<Item> items;
	int roomNorth;
	int roomWest;
	int roomEast;
	int roomSouth;
	
	Room(String n, String d, ArrayList<Item> i, int rN, int rW, int rE, int rS){
		this.name = n;
		this.desc = d;
		this.items = i;
		this.roomNorth = rN;
		this.roomWest = rW;
		this.roomEast = rE;
		this.roomSouth = rS;
	}
	
}
