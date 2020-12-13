package org.jointheleague.modules;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import org.javacord.api.event.message.MessageCreateEvent;
import org.jointheleague.modules.pojo.HelpEmbed;

import net.aksingh.owmjapis.api.APIException;

public class MaxTicTacToe extends CustomMessageCreateListener {
	private char[][] board = new char[3][3];

	public MaxTicTacToe(String channelName) {
		super(channelName);
		resetBoard();
		helpEmbed = new HelpEmbed("!ttt",
				"Play TicTacToe with the computer." + " Type the command followed by two integers for the row and column"
						+ " (ex. '!ttt 1 2' to place your piece on the top middle spot or '!ttt 2 2 for the center spot. "
						+ "If the input is invalid, simply nothing will happen and you will have to retype the command.");
// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(MessageCreateEvent event) throws APIException { 
		
		String message = event.getMessageContent();
		Random r = new Random();
		if (message.contains("!ttt")) {
			String input = message.substring(5);
			String[] nums = input.split(" ");
			int[] playerInput = {Integer.parseInt(nums[0]),Integer.parseInt(nums[1])};
			if(playerInput.length!=2 || !validInput(playerInput[0],playerInput[1])) {
				return;
			}
			// logic for winner in between player and bot input is important
			int[] botInput = {r.nextInt(3),r.nextInt(3)};
			while(true) {
				if(validInput(botInput[0],botInput[1])) {
					boardInput(botInput[0],botInput[1]);
					break;
				}
				else {
					botInput[0] = r.nextInt(3);
					botInput[1] = r.nextInt(3);
				}
			}
			event.getChannel().sendMessage("There, I played my turn. Use the !ttt command to place your next token	");
		}
	}

	public void boardInput(int row, int col) {
		board[row][col] = 'x';
	}
	public boolean validInput(int row, int col) {
		if(board[row][col]=='-') 
			return true;
		return false;
	}

	public void resetBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = '-';
			}
		}
	}

	public char checkWinner() { // x for X, o for O, d for Draw, - for continue game
		if(""+board[0][0] + board[0][1] + board[0][2] == "xxx") {
			return 'x';
		}
		if(""+board[1][0] + board[1][1] + board[1][2] == "xxx") {
			return 'x';
		}
		if(""+board[2][0] + board[2][1] + board[2][2] == "xxx") {
			return 'x';
		}
		if(""+board[0][0] + board[1][0] + board[2][0] == "xxx") {
			return 'x';
		}
		if(""+board[0][1] + board[1][1] + board[2][1] == "xxx") {
			return 'x';
		}
		if(""+board[0][2] + board[1][2] + board[2][2] == "xxx") {
			return 'x';
		}
		if(""+board[0][0] + board[1][1] + board[2][2] == "xxx") {
			return 'x';
		}
		if(""+board[2][0] + board[1][1] + board[0][2] == "xxx") {
			return 'x';
		}
		
		if(""+board[0][0] + board[0][1] + board[0][2] == "ooo") {
			return 'o';
		}
		if(""+board[1][0] + board[1][1] + board[1][2] == "ooo") {
			return 'o';
		}
		if(""+board[2][0] + board[2][1] + board[2][2] == "ooo") {
			return 'o';
		}
		if(""+board[0][0] + board[1][0] + board[2][0] == "ooo") {
			return 'o';
		}
		if(""+board[0][1] + board[1][1] + board[2][1] == "ooo") {
			return 'o';
		}
		if(""+board[0][2] + board[1][2] + board[2][2] == "ooo") {
			return 'o';
		}
		if(""+board[0][0] + board[1][1] + board[2][2] == "ooo") {
			return 'o';
		}
		if(""+board[2][0] + board[1][1] + board[0][2] == "ooo") {
			return 'o';
		}
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(board[i][j]=='-')
					return '-';
			}
		}
		
		return 'd';

	}
}
