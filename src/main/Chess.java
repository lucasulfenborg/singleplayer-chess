package main;
import main.ChessPiece.Pawn;

public class Chess {

	public static void main(String[] args) {		
		ChessGame Game = new ChessGame();
		Game.start();	
	}
	
	protected static class ChessGame {
		protected void start() {
			System.out.println("started");

			//TODO Spawn chess pieces
			//{0, 0} is top left of chess board
			Pawn blackPawn1 = new Pawn(0, 1, "black");
			Pawn blackPawn2 = new Pawn(1, 1, "black");
			Pawn blackPawn3 = new Pawn(2, 1, "black");
			Pawn blackPawn4 = new Pawn(3, 1, "black");
			Pawn blackPawn5 = new Pawn(4, 1, "black");
			Pawn blackPawn6 = new Pawn(5, 1, "black");
			Pawn blackPawn7 = new Pawn(6, 1, "black");
			Pawn blackPawn8 = new Pawn(7, 1, "black");
			
			//TODO Spawn board and add pieces to it
			
			//TODO create GUI,
			
			
			
			
			System.out.println(blackPawn1.getX());
			
		}
		
		
	}

}
