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
	int remainingChances = 3;
	
	// Generates random code at the start of each game.
	String randomCodeString = "";
	String firstHint="";
	String secondHint="";
	String thirdHint="";
	int[] randomCode = new int[6];{
		for (int i = 0; i < 6; i++){
			randomCode[i] = (int)(Math.random()*9);
			randomCodeString = randomCodeString + randomCode[i];
		}

		firstHint="A hint to the exit: "+randomCodeString.substring(0, 2);
		secondHint="A hint to the exit: "+randomCodeString.substring(2, 4);
		thirdHint="A hint to the exit: "+randomCodeString.substring(4, 6);
	}
	
	// Generates Rooms and connects each Room.
	ArrayList<Room> rooms = new ArrayList<Room>();
	Room room1, room2, room3, room4, room5, room6, room7, room8, room9, room10, room11;{
	
	// Sets each room, name, and location in the arraylist
	room1 = new Room("Dining Room", "There is a note on the dresser: \n     'Does anyone know how to read Dutch?'", new ArrayList<Item>(), -1, 8, 2, 5);
	rooms.add(room1);
	
	room2 = new Room("Garage", "The owners are gone. You must move quickly before they arrive.", new ArrayList<Item>(), 4, 10, 7, -1);
	rooms.add(room2);
	
	room3 = new Room("Master Bedroom", "You find a note on the desk. \n     'The closet is here. It's dark. So bring a flashlight and working batteries. \n     Someone may have left a dud. \n     I blame the new interns.' \n     - E", new ArrayList<Item>(), 9, 0, -1, -1);
	rooms.add(room3);
	
	room4 = new Room("Guest Room", "You stub your toe on the dresser and swear loudly. There is a note taped to the wood : \n     'E is a liar. Hint2 will get you caught.' \n     - A", new ArrayList<Item>(), 8, -1, -1, 6);
	rooms.add(room4);
	
	room5 = new Room("Living Room", "You creep behind the couch and find a note on the coffee table: \n     'Everthing is not as it seems within the organization. \n     The fourth hint is lying.'\n     - E", new ArrayList<Item>(), -1, 5, -1, 1);
	rooms.add(room5);
	
	room6 = new Room("Foyer", "You paw at the piano. It sounds horrible. Mother always said you should have practiced more.", new ArrayList<Item>(), 0, -1, 4, 10);
	rooms.add(room6);
	
	room7 = new Room("Bathroom", "You debate on whether or not you should use the toilet. It seems rude.", new ArrayList<Item>(), 3, -1, -1, -1);
	rooms.add(room7);
	
	room8 = new Room("Laundry Room", "There is a note taped on the dryer. \n     'I can't believe Notarbartolo gave us letters. Didn't he know there'd be more than 26 agents?!' \n     - PP", new ArrayList<Item>(), -1, 1, -1, -1);
	rooms.add(room8);
	
	room9 = new Room("Kitchen", "You're hungry. You take some chocolate.", new ArrayList<Item> (), -1, -1, 0, 3);
	rooms.add(room9);
	
	room10 = new Room("Hidden Closet", "You're afraid of the dark. You turn on your flashlight.", new ArrayList<Item> (), -1, -1, -1, 2);
	rooms.add(room10);
	
	room11 = new Room("Exit", "You've made it! Now you get to join Mr. Stan's organization in New York. You hate Belgium.", new ArrayList<Item>(), 5, -1, 1, -1);
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
	//System.out.println("Test: The flashlight is in: " + (num+1));
	
	Item hint1 = new Item("Hint1", firstHint);
	rooms.get((int)(Math.random()*9)).items.add(hint1);
	Item hint2 = new Item("Hint2", secondHint);
	rooms.get((int)(Math.random()*9)).items.add(hint2);
	Item hint3 = new Item("Hint3", thirdHint);
	rooms.get(9).items.add(hint3);
	
	String fakeHint="A hint to the safe: ";
	int[] fakeCode=new int[2];
	for(int j=0; j<2; j++){
		fakeCode[j] = (int)(Math.random()*9);
		fakeHint = fakeHint + fakeCode[j];
	}
	Item trap = new Item("Hint4", fakeHint);
	rooms.get((int)(Math.random()*9)).items.add(trap);
	}
	
	// Method to print out the map of the layout of the area.
	public void printLayout(){
		System.out.println("Leonardo Notarbartolo has left you a note and a blueprint of the house layout:");
		System.out.println("'Segments of the code are hidden throughout the rooms for security. \n Be wary of double agents within the organization. \n Do not fail me.' \n      -Notarbartolo \n");
		System.out.println("                            [Hidden Closet]");
		System.out.println("                                    |        ");
		System.out.println(" [Kitchen] - [Dining Room] - [Master Bedroom]");
		System.out.println("     |             | ");
		System.out.println("[Guest Room]    [Foyer] - [Living Room]");
		System.out.println("     |             |         |");
		System.out.println(" [Bathroom]      [Exit] - [Garage] - [Laundry Room]");
		System.out.println();
		

		System.out.println ("What would you like to do?" + "\n \n     The commands are: " + "\n          Go [direction] like north, south, east or west" + "\n          Look" + "\n          Take [item]" + "\n          Drop [item]" + "\n          Use [item]\n");
		System.out.println("Try starting with 'look' to see where you can go.");
		
		
		String roomName = rooms.get(player1.location).name;
		//System.out.println(player1.location+1);
		System.out.println("You are in the " + roomName + ".");
	}

	// Method that takes commands from the player.
	public void takeCommands(){
		// Takes initial command
		Scanner sc = new Scanner(System.in);
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
			System.out.println("You are looking around the " + rooms.get(player1.location).name + ".");
			System.out.println(rooms.get(player1.location).desc);
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
			System.out.println("You are looking around the " + rooms.get(player1.location).name + ".");
			System.out.println(rooms.get(player1.location).desc);
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
			
			System.out.println("You " + firstWord + " " + secondWord + ".");
			
			if (secondWord.equals("north")){
				if (rooms.get(player1.location).roomNorth != -1){
					player1.location = rooms.get(player1.location).roomNorth;
					System.out.println("You have moved to the " + rooms.get(player1.location).name + ".");
				}
				else{
					System.out.println("There is no room in this direction.");
				}
			}
			else if (secondWord.equals("south")){
				if (rooms.get(player1.location).roomSouth != -1 && rooms.get(player1.location).roomSouth != 10){
					player1.location = rooms.get(player1.location).roomSouth;
					System.out.println("You have moved to the " + rooms.get(player1.location).name + ".");
				}
				else if (rooms.get(player1.location).roomSouth != -1 && rooms.get(player1.location).roomSouth == 10){
					System.out.println("ENTER THE 6 DIGIT CODE CORRECTLY OR THE ALARM WILL GO OFF or you can write cancel and go back to your business.");
					System.out.println("You have " + remainingChances + " chances to crack the code.");
					while(remainingChances != 0){
						String code = sc.nextLine();
						
						if (code.equals("cancel")){
							
						}
						else if (code.equals(randomCodeString)){
							System.out.println("You have found the diamond. Report back to Mr. Notarbartolo.");
							System.out.println("*************GAME FINISHED*************");
							player1.location = 10;
							System.out.println(rooms.get(player1.location).desc);
						}
						else{
							remainingChances --;
							if (remainingChances == 0){
								System.out.println("You have been caught. Babou will now kill you. Goodbye.");
								player1.location = 10;
								System.out.println("*************GAME OVER*************");
								break;
							}
							else{
								System.out.println("An alarm goes off. You have " + remainingChances + " remaining chances left.");
							}
						}
					}
				}
				else{
					System.out.println("There is no room in this direction.");
				}
			}
			else if (secondWord.equals("east")){
				if (rooms.get(player1.location).roomEast != -1){
					player1.location = rooms.get(player1.location).roomEast;
					System.out.println("You have moved to the " + rooms.get(player1.location).name + ".");
				}
				else{
					System.out.println("There is no room in this direction.");
				}
			}
			else if (secondWord.equals("west")){
				if (rooms.get(player1.location).roomWest != -1 && rooms.get(player1.location).roomWest != 10){
					player1.location = rooms.get(player1.location).roomWest;
					System.out.println("You have moved to the " + rooms.get(player1.location).name + ".");
				}
				else if (rooms.get(player1.location).roomWest != -1 && rooms.get(player1.location).roomWest == 10){
					System.out.println("ENTER THE 6 DIGIT CODE CORRECTLY OR THE ALARM WILL GO OFF or you can write cancel and go back to your minion business.");
					String code = sc.nextLine();
					if (code.equals("cancel")){
						
					}
					else if (code.equals(randomCodeString)){
						System.out.println("You have found the diamond. Report back to Mr. Notarbartolo.");
						player1.location = 10;
						System.out.println("*************GAME FINISHED*************");
						System.out.println(rooms.get(player1.location).desc);
					}
					else{
						System.out.println("You have been caught. Babou will now kill you. Goodbye.");
						player1.location = 10;
						System.out.println("*************GAME OVER*************");
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
			System.out.println("The item has been added to your inventory.");
				if(secondWord.equalsIgnoreCase("Hint1") || secondWord.equalsIgnoreCase("Hint2") ||
						secondWord.equalsIgnoreCase("Hint3") || secondWord.equalsIgnoreCase("Hint4")){
					System.out.println(player1.items.get(player1.items.size()-1).desc+". Save it for later.");
					}
			rooms.get(player1.location).items.remove(indexOfItemInRoom);
			System.out.println("The item has been removed from the room.");
						
		}
		
		// If command is "take" and item does not exist
		else if (firstWord.equals("take") && !containedInRoom){
			System.out.println("That item is not in this room.");
		}
		
		// If command is "take" and the inventory is full
		else if (firstWord.equals("take") && player1.items.size() == 4){
			System.out.println("Your inventory is full.");
		}
		
		// If command is "drop" and item exists
		else if (firstWord.equals("drop") && containedInInventory){
			System.out.println("Player 1 drops the item onto the " + rooms.get(player1.location).name);
			rooms.get(player1.location).items.add(player1.items.get(indexOfItemInInventory));
			player1.items.remove(indexOfItemInInventory);
		}
		
		// If command is "drop" and item does not exist
		else if (firstWord.equals("drop") && !containedInInventory){
			System.out.println("Item is not in your inventory.");
		}
		
		// If command is "use" and item exists
		else if (firstWord.equals("use") && containedInInventory){ //if you have the flashlight
			
			if (secondWord.equalsIgnoreCase("flashLight")){
				if (numOfBats == 2 || bat){
					System.out.println("You used the flashlight");
				}
				else if (numOfBats == 0 || deadBat){
					System.out.println("You can't use the flashlight because you either don't have a good battery. Or, you don't have a battery at all");
				}
			}
			
			else if (secondWord.equalsIgnoreCase("Hint1") || secondWord.equalsIgnoreCase("Hint2") ||
					secondWord.equalsIgnoreCase("Hint3") || secondWord.equalsIgnoreCase("Hint4")){
				System.out.println("Enter the number from the hint: ");
				String hintCode= sc.nextLine();
				if(randomCodeString.contains(hintCode)){
							System.out.println("You just found a section of the secret code need to win this game."+
				"\nNow lets just hope that you enter it in the right sequence.");
				}
				
			}
			else{
				System.out.println("That item can't be used.");
			}
			
		}
		
		// If command is "use" and the item is not in the inventory.
		else if(firstWord.equals("use") && !containedInInventory){
			
			System.out.println("Item is not in the your inventory");
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
