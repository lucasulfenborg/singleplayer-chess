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
		return arrayOfPossibleMoves;
	}
	
	public void makeMove(int[] newLocation) {
		
		System.out.println("Moved to " + newLocation);
		this.setCoordinates(newLocation[0], newLocation[1]);
	}
	
	
	//Define each type of ChessPiece
	public static class Pawn extends ChessPiece {
		private boolean hasMoved = false;
		
		public Pawn(int x, int y, String color) {
			super(x, y, color);
		}
		
		@Override
		public int[][] calculatePossibleMoves() {
		    int[][] possibleMoves = new int[2][2];

		    // Determine the direction of movement based on the pawn's color
		    int stepDirection = this.getColor().equals("white") ? -1 : 1; // 1 for white (up), -1 for black (down)
		    
		    // First move: Move 1 square forward
		    int[] move1 = {this.getX(), this.getY() + stepDirection};
		    possibleMoves[0] = move1;
		    
		    // Second move: Move 2 squares forward if the pawn hasn't moved yet
		    if (!hasMoved) {
		        int[] move2 = {this.getX(), this.getY() + (stepDirection * 2)}; // Move 2 squares forward
		        possibleMoves[1] = move2;
		    }

		    return possibleMoves;
		}
		
		@Override 
		public void makeMove(int[] newLocation) {
			this.hasMoved = true;
			this.setCoordinates(newLocation[0], newLocation[1]);
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
