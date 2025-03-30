package main;

public abstract class ChessPiece {
	private int x;
	private int y;
	private int[][] arrayOfPossibleMoves = {}; //Array of coordinates e.g. {(x,y), (x,y)}
	private String color;
	
	public ChessPiece(int x, int y, String color) {
		this.x = x;
		this.y = y;
		this.color = color;

	}
	
	public String getColor() { 
		return this.color;
	}

	//For accessing the coordinates
	public int getX() { 
		return this.x;
	}
	
	public int getY() { 
		return this.y;
	}
	

	public void setCoordinates(int x, int y) { //For changing the coordinates
		this.x = x;
		this.y = y;
	}
	
	public int[][] calculatePossibleMoves() {
		//arrayOfPossibleMoves[0] = "a1";
		//arrayOfPossibleMoves[0] = "a2"; etc

		return arrayOfPossibleMoves;
	}
	
	
	//Define each type of ChessPiece
	public static class Pawn extends ChessPiece {
		
		public Pawn(int x, int y, String color) {
			super(x, y, color);
		}
		
	}
	
	public static class Knight extends ChessPiece {
		
		public Knight(int x, int y, String color) {
			super(x, y, color);
		}
	}
	
	public static class Bishop extends ChessPiece {
		
		public Bishop(int x, int y, String color) {
			super(x, y, color);
		}
	}

	public static class Rook extends ChessPiece {
		
		public Rook(int x, int y, String color) {
			super(x, y, color);
		}
	}
	
	public static class Queen extends ChessPiece {
			
			public Queen(int x, int y, String color) {
				super(x, y, color);
			}
		}
	
	public static class King extends ChessPiece {
			
			public King(int x, int y, String color) {
				super(x, y, color);
			}
	}
	
	
}
