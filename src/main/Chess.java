package main;
import main.ChessPiece.Bishop;
import main.ChessPiece.King;
import main.ChessPiece.Knight;
import main.ChessPiece.Pawn;
import main.ChessPiece.Queen;
import main.ChessPiece.Rook;

import java.util.ArrayList;
import java.util.List;

public class Chess {

	public static void main(String[] args) {		
		ChessGame Game = new ChessGame();
		Game.start();	
	}
	
	
	
	
	protected static class ChessGame {
		
		
		
		
		protected void start() {
			//{0, 0} is top left of chess board
			Pawn blackPawn1 = new Pawn(0, 1, "black");
			Pawn blackPawn2 = new Pawn(1, 1, "black");
			Pawn blackPawn3 = new Pawn(2, 1, "black");
			Pawn blackPawn4 = new Pawn(3, 1, "black");
			Pawn blackPawn5 = new Pawn(4, 1, "black");
			Pawn blackPawn6 = new Pawn(5, 1, "black");
			Pawn blackPawn7 = new Pawn(6, 1, "black");
			Pawn blackPawn8 = new Pawn(7, 1, "black");
			
			Pawn whitePawn1 = new Pawn(0, 6, "white");
			Pawn whitePawn2 = new Pawn(1, 6, "white");
			Pawn whitePawn3 = new Pawn(2, 6, "white");
			Pawn whitePawn4 = new Pawn(3, 6, "white");
			Pawn whitePawn5 = new Pawn(4, 6, "white");
			Pawn whitePawn6 = new Pawn(5, 6, "white");
			Pawn whitePawn7 = new Pawn(6, 6, "white");
			Pawn whitePawn8 = new Pawn(7, 6, "white");

			Knight whiteKnight1 = new Knight(1, 0, "white");
			Knight whiteKnight2 = new Knight(6, 0, "white");
			Knight blackKnight1 = new Knight(1, 7, "black");
			Knight blackKnight2 = new Knight(6, 7, "black");

			
			Bishop whiteBishop1 = new Bishop(2, 0, "white");
			Bishop whiteBishop2 = new Bishop(5, 0, "white");
			Bishop blackBishop1 = new Bishop(2, 7, "black");
			Bishop blackBishop2 = new Bishop(5, 7, "black");

			
			Rook whiteRook1= new Rook(0, 0, "white");
			Rook whiteRook2= new Rook(7, 0, "white");
			Rook blackRook1= new Rook(0, 7, "black");
			Rook blackRook2= new Rook(7, 7, "black");

			Queen whiteQueen1= new Queen(3, 0, "white");
			Queen blackQueen1= new Queen(3, 7, "black");

			
			King whiteKing1= new King(4, 0, "white");
			King blackKing1= new King(4, 7, "black");
			
			
			
			//TODO Spawn board and add pieces to it
			ChessBoard board = new ChessBoard(8, 8);
			board.addChessPiece(blackPawn1);
			board.addChessPiece(blackPawn2);
			board.addChessPiece(blackPawn3);
			board.addChessPiece(blackPawn4);
			board.addChessPiece(blackPawn5);
			board.addChessPiece(blackPawn6);
			board.addChessPiece(blackPawn7);
			board.addChessPiece(blackPawn8);
			board.addChessPiece(whitePawn1);
			board.addChessPiece(whitePawn2);
			board.addChessPiece(whitePawn3);
			board.addChessPiece(whitePawn4);
			board.addChessPiece(whitePawn5);
			board.addChessPiece(whitePawn6);
			board.addChessPiece(whitePawn7);
			board.addChessPiece(whitePawn8);
			
			board.addChessPiece(whiteKnight1);
			board.addChessPiece(whiteKnight2);
			board.addChessPiece(blackKnight1);
			board.addChessPiece(blackKnight2);
			
			board.addChessPiece(whiteBishop1);
			board.addChessPiece(whiteBishop2);
			board.addChessPiece(blackBishop1);
			board.addChessPiece(blackBishop2);

			board.addChessPiece(whiteRook1);
			board.addChessPiece(whiteRook2);
			board.addChessPiece(blackRook1);
			board.addChessPiece(blackRook2);

			board.addChessPiece(whiteQueen1);
			board.addChessPiece(blackQueen1);

			board.addChessPiece(whiteKing1);
			board.addChessPiece(blackKing1);
			
			//Create GUI and add board to it
			GUI graphicalUserInterface = new GUI(board);
			graphicalUserInterface.updateGUI();
						
		}
		
		
	}

}