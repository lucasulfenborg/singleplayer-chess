package main;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {
	private int widht;
	private int height;
	private List<ChessPiece> chessPieces = new ArrayList<>();
	
	public ChessBoard(int widht, int height) {
		this.widht = widht;
		this.height =  height;
	}
	
	public int getWidht() {
		return widht;
	}

	public int getHeight() {
		return height;
	}
	
	public List<ChessPiece> getChessPieces(){
		return this.chessPieces;
	}

	public void addChessPiece(ChessPiece piece) {
		this.chessPieces.add(piece);
	}
	
	public void removeChessPiece(ChessPiece piece) {
		this.chessPieces.remove(piece);
	}
	
}
