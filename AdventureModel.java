/*
This class:
	Creates the Rooms, the Things, and the Adventurer.
	Connects the Rooms with "paths" to other Rooms.
	Places Things in the Rooms.
	Places the Adventurer in some Room.
	Accepts commands from the player, and executes them.
	o	As commands are entered, they should be copied to the main text area.
	o	The method that executes commands should return a String to be displayed in the main text area.
*/

package adventure;

import java.util.*;

public class AdventureModel {

	// Creates a new player
	// 	Parameter 1 : Places Character into a random room from 0 to 10.
	//	Parameter 2 : Gives Character a blank inventory.
	Adventurer player1 = new Adventurer((int)(Math.random()*9), new ArrayList<Item>());
	
	// Generates random code at the start of each game.
	String randomCodeString = "";
	int[] randomCode = new int[6];{
		for (int i = 0; i < 6; i++){
			randomCode[i] = (int)(Math.random()*9);
			randomCodeString = randomCodeString + randomCode[i];

		}

		System.out.println(randomCodeString);
	}
	
	// Generates Rooms and connects each Room.
	ArrayList<Room> rooms = new ArrayList<Room>();
	Room room1, room2, room3, room4, room5, room6, room7, room8, room9, room10, room11;{
	
	// Sets each room, name, and location in the arraylist
	room1 = new Room("Dining Hall", ".", new ArrayList<Item>(), -1, 8, 2, 5);
	rooms.add(room1);
	room2 = new Room("Garage", ".", new ArrayList<Item>(), 4, 10, 7, -1);
	rooms.add(room2);
	room3 = new Room("Master Bedroom", ".", new ArrayList<Item>(), 9, 0, -1, -1);
	rooms.add(room3);
	room4 = new Room("Guest Room", ".", new ArrayList<Item>(), 8, -1, -1, 6);
	rooms.add(room4);
	room5 = new Room("Living Room", ".", new ArrayList<Item>(), -1, 5, -1, 1);
	rooms.add(room5);
	room6 = new Room("Foyer", ".", new ArrayList<Item>(), 0, -1, 4, 10);
	rooms.add(room6);
	room7 = new Room("Bathroom", ".", new ArrayList<Item>(), 3, -1, -1, -1);
	rooms.add(room7);
	room8 = new Room("Laundry Room", ".", new ArrayList<Item>(), -1, 1, -1, -1);
	rooms.add(room8);
	room9 = new Room("Kitchen", ".", new ArrayList<Item> (), -1, -1, 0, 3);
	rooms.add(room9);
	room10 = new Room("Hidden Closet", ".", new ArrayList<Item> (), -1, -1, -1, 2);
	rooms.add(room10);
	room11 = new Room("Exit", ".", new ArrayList<Item>(), 5, -1, 1, -1);
	rooms.add(room11);
	
	// Items and adding them into a random room.
	Item battery = new Item("Battery1", "Just a regular AA battery.");
	int batteryNum = (int)(Math.random()*9); //goes to 9 so that the battery does not spawn in the 10th room
	int batteryNum2 = (int)(Math.random()*9); //^
	while (batteryNum == batteryNum2){
		batteryNum2 = (int)(Math.random()*9);
	}
	rooms.get(batteryNum).items.add(battery);
	Item deadBattery = new Item("Battery2", "Just a regular AA battery.");
	rooms.get(batteryNum2).items.add(deadBattery);
	Item flashLight = new Item("flashLight", "A flashlight, but it seems to be dead.");
	int num = (int)(Math.random()*9);
	rooms.get(num).items.add(flashLight);
	System.out.println("THE FREAKING FLASHLIGHT IS IN " + (num+1));
	
	Item hint1 = new Item("Hint1", "A hint to the exit!: ");
	rooms.get((int)(Math.random()*9)).items.add(hint1);
	Item hint2 = new Item("Hint2", "A hint to the exit!: ");
	rooms.get((int)(Math.random()*9)).items.add(hint2);
	Item hint3 = new Item("Hint3", "A hint to the exit!: ");
	rooms.get(9).items.add(hint3);
	Item trap = new Item("Hint4", "Whoa, this one looks a little different.");
	rooms.get((int)(Math.random()*9)).items.add(trap);
	}
	
	// Method to print out the map of the layout of the area.
	public void printLayout(){
		System.out.println("                [ H ]");
		System.out.println("                  |  ");
		System.out.println("[ 9 ] - [ 1 ] - [ 3 ]");
		System.out.println("  |       | ");
		System.out.println("[ 4 ]   [ 6 ] - [ 5 ]");
		System.out.println("  |       |       |");
		System.out.println("[ 7 ]   [ E ] - [ 2 ] - [ 8 ]");
		System.out.println();
		
		String roomName = rooms.get(player1.location).name;
		System.out.println(player1.location+1);
		System.out.println("Player is in the " + roomName + ".");
	}

	// Method that takes commands from the player.
	public void takeCommands(){
		// Takes initial command
		Scanner sc = new Scanner(System.in);
		System.out.println("What would you like to do?");
		String command = sc.nextLine();
		
		// Takes first word and second word of command.
		int indexOfSpace = command.indexOf(' ');
		String firstWord = "", secondWord = "";
		if (indexOfSpace > 0){
			firstWord = command.substring(0, indexOfSpace);
			secondWord = command.substring(indexOfSpace+1);
			System.out.println(firstWord + " " + secondWord);
		}
		
		// Check to see if player has the flashlight and batteries
		boolean deadBat = false;
		boolean bat = false;
		int numOfBats = 0;
		for (int i = 0; i < player1.items.size(); i++){
			if (player1.items.get(i).name.equalsIgnoreCase("Battery1")){
				deadBat = true;
				numOfBats ++;
			}
			else if (player1.items.get(i).name.equalsIgnoreCase("Battery2")){
				bat = true;
				numOfBats ++;
			}
		}
		
		
		//Check to see if player has a hint
		boolean hint = false;
		int numOfHints = 0;
		for (int i = 0; i < player1.items.size(); i++){
			if (player1.items.get(i).name.equalsIgnoreCase("hint")){
				hint = true;
				numOfHints ++;
			}
		}
		
		// Check if item exists in the room.
		boolean containedInRoom = false;
		int indexOfItemInRoom = -1;
		for (int i = 0; i < rooms.get(player1.location).items.size(); i++){
			if (rooms.get(player1.location).items.get(i).name.equalsIgnoreCase(secondWord)){
				containedInRoom = true;
				indexOfItemInRoom = i;
			}
		}
		
		// Check if item exists in the inventory
		boolean containedInInventory = false;
		int indexOfItemInInventory = -1;
		for (int i = 0; i < player1.items.size(); i++){
			if (player1.items.get(i).name.equalsIgnoreCase(secondWord)){
				containedInInventory = true;
				indexOfItemInInventory = i;
			}
		}
			
		// If command is "look" and player is in any room
		if (command.equals("look") && player1.location != 9){
			System.out.println("Player is looking around the " + rooms.get(player1.location).name + ".");
			String items = "";
			for (int i = 0; i < rooms.get(player1.location).items.size(); i++){
				items = items + " " + rooms.get(player1.location).items.get(i).name;
			}
			if (!(items.equals(""))){
				System.out.println("The item(s) in the room is/are:" + items + ".");
			}
			if (rooms.get(player1.location).roomEast != -1){
				System.out.println("There is a room on the east.");
			}
			if (rooms.get(player1.location).roomWest != -1){
				System.out.println("There is a room on the west.");
			}
			if (rooms.get(player1.location).roomSouth != -1){
				System.out.println("There is a room on the south.");
			}
			if (rooms.get(player1.location).roomNorth != -1){
				System.out.println("There is a room on the north.");
			}
		}	
		
		// If command is "look" and player is in room 10 and can look around.
		else if (command.equals("look") && player1.location == 9 && (numOfBats == 2 || bat)){
			System.out.println("Player is looking around the " + rooms.get(player1.location).name + ".");
			String items = "";
			for (int i = 0; i < rooms.get(player1.location).items.size(); i++){
				items = items + " " + rooms.get(player1.location).items.get(i).name;
			}
			if (!(items.equals(""))){
				System.out.println("The item(s) in the room is/are:" + items + ".");
			}
			if (rooms.get(player1.location).roomEast != -1){
				System.out.println("There is a room on the east.");
			}
			if (rooms.get(player1.location).roomWest != -1){
				System.out.println("There is a room on the west.");
			}
			if (rooms.get(player1.location).roomSouth != -1){
				System.out.println("There is a room on the south.");
			}
			if (rooms.get(player1.location).roomNorth != -1){
				System.out.println("There is a room on the north.");
			}
		}
		
		// If command is "look" and player is in room 10 and can't look around.
		else if (command.equals("look") && player1.location == 9 && (numOfBats == 0 || deadBat)){
			System.out.println("The room is too dark, you might need a flashlight to look around");
		}
		
		// If command is "look" and item exists
		else if (firstWord.equals("look") && (containedInRoom || containedInInventory) && player1.location != 9){
			// Read the item's description
			if (containedInRoom){
				System.out.println(rooms.get(indexOfItemInRoom).name+ ": " + rooms.get(indexOfItemInRoom).desc);
			}
			else{
				System.out.println(rooms.get(indexOfItemInInventory).name + ": " + rooms.get(indexOfItemInInventory).desc);
			}
		}
		
		// If command is "look", item exists, and player is in room 10 and can look around.
		else if (command.equals("look") && (containedInRoom || containedInInventory) && player1.location == 9 && (numOfBats == 2 || bat)){
			// Read the item's description
			if (containedInRoom){
				System.out.println(rooms.get(indexOfItemInRoom).name+ ": " + rooms.get(indexOfItemInRoom).desc);
			}
			else{
				System.out.println(rooms.get(indexOfItemInInventory).name + ": " + rooms.get(indexOfItemInInventory).desc);
			}
		}
		
		// If command is "look", item exists and player is in room 10 and can't look around.
		else if (command.equals("look") && (containedInRoom || containedInInventory) && player1.location == 9 && (numOfBats == 0 || deadBat)){
			System.out.println("The room is too dark, you might need a working flashlight to look around");
		}
		
		// If command is "look" and item does not exist
		else if (firstWord.equals("look") && (!containedInRoom || !containedInInventory)){
			System.out.println("Item is not in the current room or player's inventory");
		}
		
		// If command is "go" and a valid direction is given:
		else if (firstWord.equals("go") && (secondWord.equals("north") || secondWord.equals("east") || secondWord.equals("west") || secondWord.equals("south"))){
			
			System.out.println("The player " + firstWord + "es " + secondWord + ".");
			
			if (secondWord.equals("north")){
				if (rooms.get(player1.location).roomNorth != -1){
					player1.location = rooms.get(player1.location).roomNorth;
					System.out.println("Player has moved to the " + rooms.get(player1.location).name + ".");
				}
				else{
					System.out.println("There is no room in this direction.");
				}
			}
			else if (secondWord.equals("south")){
				if (rooms.get(player1.location).roomSouth != -1 && rooms.get(player1.location).roomSouth != 10){
					player1.location = rooms.get(player1.location).roomSouth;
					System.out.println("Player has moved to the " + rooms.get(player1.location).name + ".");
				}
				else if (rooms.get(player1.location).roomSouth != -1 && rooms.get(player1.location).roomSouth == 10){
					System.out.println("ENTER THE 6 DIGIT CODE CORRECTLY OR YOU DIE or you can write cancel and go back to your business");
					String code = sc.nextLine();
					if (code.equals("cancel")){
						
					}
					else if (code.equals(randomCodeString)){
						System.out.println("you win hohooh");
						player1.location = 10;
					}
					else{
						System.out.println("you died");
						player1.location = 10;
					}
				}
				else{
					System.out.println("There is no room in this direction.");
				}
			}
			else if (secondWord.equals("east")){
				if (rooms.get(player1.location).roomEast != -1){
					player1.location = rooms.get(player1.location).roomEast;
					System.out.println("Player has moved to the " + rooms.get(player1.location).name + ".");
				}
				else{
					System.out.println("There is no room in this direction.");
				}
			}
			else if (secondWord.equals("west")){
				if (rooms.get(player1.location).roomWest != -1 && rooms.get(player1.location).roomWest != 10){
					player1.location = rooms.get(player1.location).roomWest;
					System.out.println("Player has moved to the " + rooms.get(player1.location).name + ".");
				}
				else if (rooms.get(player1.location).roomWest != -1 && rooms.get(player1.location).roomWest == 10){
					System.out.println("ENTER THE 6 DIGIT CODE CORRECTLY OR YOU DIE or you can write cancel and go back to your business");
					String code = sc.nextLine();
					if (code.equals("cancel")){
						
					}
					else if (code.equals(randomCodeString)){
						System.out.println("you win hohooh");
						player1.location = 10;
					}
					else{
						System.out.println("you died");
						player1.location = 10;
					}
				}
				else{
					System.out.println("There is no room in this direction.");
				}
			}
		}
		
		// If command is "go" and a valid direction is not given:
		else if (firstWord.equals("go") && (!secondWord.equals("north") || !secondWord.equals("east") || !secondWord.equals("west") || !secondWord.equals("south"))){
			System.out.println("Invalid Direction");
		}
		
		// If command is "take" and item exists
		else if (firstWord.equals("take") && player1.items.size() < 4 && containedInRoom){ 
			//player needs to have less than four items
			
			player1.items.add(rooms.get(player1.location).items.get(indexOfItemInRoom));
			System.out.println("The item has been added to the player's inventory.");
			rooms.get(player1.location).items.remove(indexOfItemInRoom);
			System.out.println("The item has been removed from the room.");
						
		}
		
		// If command is "take" and item does not exist
		else if (firstWord.equals("take") && !containedInRoom){
			System.out.println("That item is not in this room");
		}
		
		// If command is "take" and the inventory is full
		else if (firstWord.equals("take") && player1.items.size() == 4){
			System.out.println("Inventory is full.");
		}
		
		// If command is "drop" and item exists
		else if (firstWord.equals("drop") && containedInInventory){
			System.out.println("Player 1 drops the item onto the " + rooms.get(player1.location).name);
			rooms.get(player1.location).items.add(player1.items.get(indexOfItemInInventory));
			player1.items.remove(indexOfItemInInventory);
		}
		
		// If command is "drop" and item does not exist
		else if (firstWord.equals("drop") && !containedInInventory){
			System.out.println("Item is not in the player's inventory.");
		}
		
		// If command is "use" and item exists
		else if (firstWord.equals("use") && containedInInventory){ //if you have the flashlight
			
			if (secondWord.equalsIgnoreCase("flashLight")){
				if (numOfBats == 2 || bat){
					System.out.println("You used the flashlight");
				}
				else if (numOfBats == 0 || deadBat){
					System.out.println("You can't use the flashlight because you either don't have a good battery or you don't have a battery at all");
				}
			}
			
			else if (numOfHints < 4 || hint){
				System.out.println("*Insert hint for 6 digit code*");
				System.out.println("Use of hints has not been implemented yet. The programmer is very tired.");
			}
			else{
				System.out.println("That item can't be used");
			}
			
		}
		
		// If command is "use" and the item is not in the inventory.
		else if(firstWord.equals("use") && !containedInInventory){
			
			System.out.println("Item is not in the player's inventory");
			/*for (int i = 0; i < player1.items.size(); i++){
				if(player1.items.get(i).name.equalsIgnoreCase("battery") || player1.items.get(i).name.equalsIgnoreCase("deadBattery")){
					//if (player1.items.contains("deadBattery") || player1.items.contains("Battery")){
						System.out.println("You do not have a flashLight in your inventory.");
					//}
					
				}
			}*/
		}
		
		// If command does not exist
		else{
			System.out.println("Invalid Command");
		}
		
	}
	
	
	
}