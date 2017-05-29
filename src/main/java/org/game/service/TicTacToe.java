package org.game.service;

import org.game.model.StateData;

public class TicTacToe {

	// Name-constants to represent the players and cell contents
	public static final int EMPTY = 0;
	public static final int CROSS = 1;
	public static final int NOUGHT = 2;

	// Name-constants to represent the various states of the game
	public static final int PLAYING = 0;
	public static final int DRAW = 1;
	public static final int CROSS_WON = 2;
	public static final int NOUGHT_WON = 3;

	// The validaty of player's move
	public static final int VALID_MOVE = 0;
	public static final int INVALID_MOVE = 1;

	// The game board and the game status
	public static int ROWS, COLS; // number of rows and columns
	public static int[][] board; // game board in 2D array, containing (EMPTY, CROSS, NOUGHT)
	public static int currentState; // the current state of the game (PLAYING, DRAW, CROSS_WON, NOUGHT_WON)
	public static int currentPlayer; // the current player (CROSS or NOUGHT)
	public static int currntRow, currentCol; // current player's row and column input

	public static StateData doGameAction(StateData stateData) {
		initGame(stateData);

		boolean validMove = doMove(currentPlayer, stateData.getSelectedRow(), stateData.getSelectedColumn());
		if (validMove) {
			stateData.setSequenceNum(stateData.getSequenceNum() + 1);
			updateState(currentPlayer, currntRow, currentCol);
			printBoard();

			currentPlayer = (currentPlayer == CROSS) ? NOUGHT : CROSS;	// Switch to the next player
			stateData.setStateOfGame(currentState);
			stateData.setPlayer(currentPlayer);
			if (stateData.getErrorMessage() == INVALID_MOVE)
				stateData.setErrorMessage(VALID_MOVE);
		} else {	// inValid Move
			stateData.setErrorMessage(INVALID_MOVE);
		}
		return stateData;
	}

	/** Initialize the size of board, board contents and the current states */
	public static void initGame(StateData stateData) {
		ROWS = stateData.getBoard().length;
		COLS = stateData.getBoard().length;
		currntRow = stateData.getSelectedRow();
		currentCol = stateData.getSelectedColumn();
		currentState = stateData.getStateOfGame(); 	// ready to play
		currentPlayer = stateData.getPlayer(); 		// player (CROSS or NOUGHT)
		board = stateData.getBoard();
	}

	//Player's one move and validation of the move
	public static boolean doMove(int player, int selectedRow, int selectedCol) {
		boolean ifValidInput = false;

		int rowIndex = selectedRow - 1; // conversion to array index 
		int colIndex = selectedCol - 1; // conversion to array index
		if (rowIndex >= 0 && rowIndex < ROWS && 
			colIndex >= 0 && colIndex < COLS && 
			board[rowIndex][colIndex] == EMPTY) {
			currntRow = rowIndex;
			currentCol = colIndex;
			board[currntRow][currentCol] = player; 	// update board content
			ifValidInput = true; 						// input is valid
		} 

		return ifValidInput;
	}

	// Update the "currentState" after the player's move
	public static void updateState(int player, int currentRow, int currentCol) {
		if (hasWon(player, currentRow, currentCol)) { // check if winning move
			currentState = (player == CROSS) ? CROSS_WON : NOUGHT_WON;
		} else if (isDraw()) { // check for draw
			currentState = DRAW;
		}
		// else {no change to currentState (still PLAYING) }
	}

	// When all the cells are filled but no one wins, it is Draw */
	public static boolean isDraw() {
		for (int row = 0; row < ROWS; ++row) {
			for (int col = 0; col < COLS; ++col) {
				if (board[row][col] == EMPTY) {
					return false; // an empty cell found, not draw, exit
				}
			}
		}
		return true; // no empty cell, it's a draw
	}


	// Check if the player won the game by placing at 'currentRow' and 'currentCol'
	public static boolean hasWon(int player, int currentRow, int currentCol) {
		return (board[currentRow][0] == player && board[currentRow][1] == player && board[currentRow][2] == player ||	// check current row
				board[0][currentCol] == player && board[1][currentCol] == player && board[2][currentCol] == player ||	// check current column
				currentRow == currentCol && board[0][0] == player && board[1][1] == player && board[2][2] == player ||	// check left-high to right-low diagonal
				currentRow + currentCol == 2 && board[0][2] == player && board[1][1] == player && board[2][0] == player);//check right-high to left-low diagnoal 
	}

	// Print the update board status
	public static void printBoard() {
		for (int row = 0; row < ROWS; ++row) {
			for (int col = 0; col < COLS; ++col) {
				printCell(board[row][col]); // print each cell
				if (col != COLS - 1) {		// do not print at the end of column
					System.out.print("|");	// print vertical partition
				}
			}
			System.out.println();
			if (row != ROWS - 1) {
				System.out.println("-----------"); // print horizontal partition
			}
		}
		System.out.println();
	}

	// Print each cell */
	public static void printCell(int eachCell) {
		switch (eachCell) {
			case EMPTY:
				System.out.print("   ");
				break;
			case NOUGHT:
				System.out.print(" O ");
				break;
			case CROSS:
				System.out.print(" X ");
				break;
		}
	}

}
