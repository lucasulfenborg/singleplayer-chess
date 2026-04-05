package main;

import java.util.List;

import main.ChessPiece.Bishop;
import main.ChessPiece.King;
import main.ChessPiece.Knight;
import main.ChessPiece.Pawn;
import main.ChessPiece.Queen;
import main.ChessPiece.Rook;

public class ChessGame {

    private ChessBoard board;
    private String currentTurn = "white";

    public ChessGame() {
        board = new ChessBoard(8, 8);
        setupBoard();
    }

    public ChessBoard getBoard() {
        return board;
    }

    private void setupBoard() {

        // Pawns
        for (int x = 0; x < 8; x++) {
            board.addChessPiece(new Pawn(x, 1, "black"));
            board.addChessPiece(new Pawn(x, 6, "white"));
        }

        // Knights
        board.addChessPiece(new Knight(1, 0, "white"));
        board.addChessPiece(new Knight(6, 0, "white"));
        board.addChessPiece(new Knight(1, 7, "black"));
        board.addChessPiece(new Knight(6, 7, "black"));

        // Bishops
        board.addChessPiece(new Bishop(2, 0, "white"));
        board.addChessPiece(new Bishop(5, 0, "white"));
        board.addChessPiece(new Bishop(2, 7, "black"));
        board.addChessPiece(new Bishop(5, 7, "black"));

        // Rooks
        board.addChessPiece(new Rook(0, 0, "white"));
        board.addChessPiece(new Rook(7, 0, "white"));
        board.addChessPiece(new Rook(0, 7, "black"));
        board.addChessPiece(new Rook(7, 7, "black"));

        // Queens
        board.addChessPiece(new Queen(3, 0, "white"));
        board.addChessPiece(new Queen(3, 7, "black"));

        // Kings
        board.addChessPiece(new King(4, 0, "white"));
        board.addChessPiece(new King(4, 7, "black"));
    }

    public boolean movePiece(ChessPiece piece, int targetX, int targetY) {

        // 1. Check turn
        if (!piece.getColor().equals(currentTurn)) {
            System.out.println("Not your turn!");
            return false;
        }

        // 2. Check if move is valid
        List<int[]> possibleMoves = piece.calculatePossibleMoves();        
        
        boolean validMove = false;

        for (int[] move : possibleMoves) {
            if (move != null && move[0] == targetX && move[1] == targetY) {
                validMove = true;
                break;
            }
        }

        if (!validMove) {
            System.out.println("Invalid move!");
            return false;
        }

        // 3. Handle capture (basic)
        ChessPiece pieceAtTarget = getPieceAt(targetX, targetY);
        if (pieceAtTarget != null) {

            // Prevent capturing your own piece
            if (pieceAtTarget.getColor().equals(piece.getColor())) {
                System.out.println("Can't capture your own piece!");
                return false;
            }

            board.removeChessPiece(pieceAtTarget);
        }

        // 4. Move piece
        piece.makeMove(new int[]{targetX, targetY});

        // 5. Switch turn
        currentTurn = currentTurn.equals("white") ? "black" : "white";

        System.out.println("Turn: " + currentTurn);

        return true;
    }

    // Helper: find piece at position
    private ChessPiece getPieceAt(int x, int y) {
        for (ChessPiece piece : board.getChessPieces()) {
            if (piece.getX() == x && piece.getY() == y) {
                return piece;
            }
        }
        return null;
    }
}