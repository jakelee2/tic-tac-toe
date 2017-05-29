package org.game.model;

import java.io.Serializable;

public class StateData implements Serializable {

	private static final long serialVersionUID = -2505235479604536599L;
	
	public int player;
	public int sequenceNum;
	public int stateOfGame;
	public int errorMessage;
	public int selectedRow;
	public int selectedColumn;
	public int[][] board;
	
	public int getPlayer() {
		return player;
	}
	public void setPlayer(int player) {
		this.player = player;
	}
	public int getSequenceNum() {
		return sequenceNum;
	}
	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	public int getStateOfGame() {
		return stateOfGame;
	}
	public void setStateOfGame(int stateOfGame) {
		this.stateOfGame = stateOfGame;
	}
	public int getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(int errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getSelectedRow() {
		return selectedRow;
	}
	public void setSelectedRow(int selectedRow) {
		this.selectedRow = selectedRow;
	}
	public int getSelectedColumn() {
		return selectedColumn;
	}
	public void setSelectedColumn(int selectedColumn) {
		this.selectedColumn = selectedColumn;
	}
	public int[][] getBoard() {
		return board;
	}
	public void setBoard(int[][] board) {
		this.board = board;
	}
	
}