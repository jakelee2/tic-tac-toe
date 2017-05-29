package org.game.service;

import static org.junit.Assert.*;

import org.game.model.StateData;
import org.junit.Test;

public class TestTicTacToe {
	
	@Test
	public void testTicTacToe(){
		testDoMove();
		testUpdateState();
		
	}

	public void testUpdateState(){
		
		// board set for DRAW
		int[][] board= new int[][]{
			  {2, 1, 1},
			  {1, 2, 2},
			  {2, 1, 1}
			};		

		StateData sd = new StateData();
		sd.setPlayer(1);
		sd.setStateOfGame(0);//PLAYING
		sd.setSelectedRow(3);
		sd.setSelectedColumn(2);
		sd.setBoard(board);

		TicTacToe.initGame(sd);
		TicTacToe.updateState(1, 2, 1);	// player=1, row => (3-1), column=> (2-1)
		assertEquals(TicTacToe.currentState, 1); 	// DRAW!

		// board set change for NOUGHT_WON
		board[0][2] = 2;
		sd.setBoard(board);
		TicTacToe.initGame(sd);
		TicTacToe.updateState(2, 0, 2);	// player=2, row => (1-1), column=> (3-1)
		assertEquals(TicTacToe.currentState, 3); 	// NOUGHT_WON!
		
		
	}

	public void testDoMove(){
		
		int[][] board= new int[][]{
			  {0, 1, 0},
			  {0, 2, 0},
			  {0, 0, 1}
			};		

		StateData sd = new StateData();
		sd.setPlayer(2);
		sd.setSequenceNum(3);
		sd.setStateOfGame(0);//PLAYING
		sd.setErrorMessage(0);
		sd.setSelectedRow(2);
		sd.setSelectedColumn(3);
		sd.setBoard(board);
		
		TicTacToe.initGame(sd);
		boolean valid = TicTacToe.doMove(1, 2, 3);// CROSS, selectedRow = 2, selectedCol = 3
		assertTrue(valid); 					// No overlapping between input(selectedRow, selectedCol) and the board
		valid = TicTacToe.doMove(1, 2, 2);		// CROSS, selectedRow = 2, selectedCol = 2
		assertFalse(valid);					// Overlapping between input(selectedRow, selectedCol) and the board
	}

}
