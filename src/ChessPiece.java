
public abstract class ChessPiece {
	private int[] coordinates = new int[2];  //X, Y coordinates
	private String[] arrayOfPossibleMoves = {};

	
	
	public int[] getCoordinates() { //For accessing the coordinates
		return coordinates;
	}

	public void setCoordinates(int[] coordinates) { //For changing the coordinates
		this.coordinates = coordinates;
	}
	
	public String[] calculatePossibleMoves() {
		//arrayOfPossibleMoves[0] = "a1";
		//arrayOfPossibleMoves[0] = "a2";

		return arrayOfPossibleMoves;
	}
	
	
	//Define each type of ChessPiece
	public static class Pawn extends ChessPiece {
		
		public Pawn() {
			
		}
		
	}
	
	public static class Knight extends ChessPiece {
		
		public Knight() {
			
		}
	}
	
	public static class Bishop extends ChessPiece {
		
		public Bishop() {
			
		}
	}

	public static class Rook extends ChessPiece {
		
		public Rook() {
			
		}
	}
	
	public static class Queen extends ChessPiece {
			
			public Queen() {
				
			}
		}
	
	public static class King extends ChessPiece {
			
			public King() {
				
			}
	}
	
	
}

