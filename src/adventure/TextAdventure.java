/*
This class:
�	Contains the main method used to start the game.
�	Talks to the AdventureModel and to the Adventurer classes, as needed.
*/

package adventure;

public class TextAdventure {

	public static void main(String[] args) {
		
		AdventureModel model = new AdventureModel();
		model.printLayout():
		
		// While the player is not in the exist, continue taking commands
		while (model.player1.location != 10){
			model.takeCommands();
		}

	}

}
