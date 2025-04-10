package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUI {
	private List<JPanel> highlightedPanels = new ArrayList<>(); // Track highlighted panels
	private ChessBoard board;
	private JFrame frame;
	
	private List<JLabel> square_LabelArray = new ArrayList<>();
	private List<JPanel> square_PanelArray = new ArrayList<>();
	
	//Colors
	private Color squareColor1 = new Color(118,150,86);
	private Color squareColor2 = new Color(238,238,210);
	private Color squareColor1_Highlited = new Color(168,219,117);
	
	//Track which piece is currently selected
	private ChessPiece selectedPiece = null;
	
	//Constructor (takes in a chess board and creates a GUI for it)
	public GUI(ChessBoard board) {
		this.board = board;
		
		
		
		int width = this.board.getWidht();
		int height = this.board.getHeight();
		
		//Create the window
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Chess");
		frame.setSize(1000,1000);
		frame.setLayout(new GridLayout(height, width));
		frame.setResizable(false);
		
		//Draw squares on the chess board
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				
				//Add to the list of squares
				//squaresArray.add
				
				//Draw square
				JPanel panel = new JPanel();
				if ((x % 2 == 0 && y % 2 == 1) || (x % 2 == 1 && y % 2 == 0)) {
					panel.setBackground(squareColor1);
				}
				
				else {
					panel.setBackground(squareColor2);
				}
				
				//Add label to the panel and make it accessible later on
				JLabel label = new JLabel(x + " " + y);
				panel.add(label);
			    frame.add(panel);
			    
			    square_LabelArray.add(label);
			    square_PanelArray.add(panel);
			}
		}

		frame.setVisible(true);
	}
	
	//Method for updating/drawing the pieces on the GUI
	public void updateGUI() {
        List<ChessPiece> piecesOnBoard = board.getChessPieces();
        
        // Clear old labels and mouse listeners
        for (JLabel label : square_LabelArray) {
            label.setText("");
            for (MouseListener listener : label.getMouseListeners()) {
                label.removeMouseListener(listener);
            }
        }
        
        // Display pieces on the board and add listeners for each piece
        for (ChessPiece piece : piecesOnBoard) {
            int x = piece.getX();
            int y = piece.getY();
            int index = y * board.getWidht() + x;
            JLabel label = square_LabelArray.get(index);
            
            // Set piece symbol
            String symbol = getPieceSymbol(piece);
            label.setText(symbol);
            label.setFont(label.getFont().deriveFont(75f)); // Adjust font size
            
            // Add mouse listener to the piece
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (selectedPiece == piece) return;
                    
                    // Clear previous highlights
                    clearHighlights();
                    
                    selectedPiece = piece;
                    highlightPossibleMoves(piece); // Highlight possible moves when piece is selected
                }
            });
        }
    }
	
	//Method for highlighting the possible moves for a chess piece
	private void highlightPossibleMoves(ChessPiece piece) {
        int[][] possibleMoves = piece.calculatePossibleMoves();
        
        // Highlight the possible move squares
        for (int[] move : possibleMoves) {
            int targetX = move[0];
            int targetY = move[1];
            
            int targetIndex = targetX + targetY * 8;
            JPanel panel = square_PanelArray.get(targetIndex);
            panel.setBackground(squareColor1_Highlited);
            
            // Add a MouseListener to each highlighted square
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // If the square is clicked, move the piece there
                    if (selectedPiece != null) {
                        int[] newLocation = {targetX, targetY};
                        selectedPiece.makeMove(newLocation);
                        selectedPiece = null;
                        
                        // Update the board and GUI
                        updateGUI();
                    }
                    clearHighlights(); // Clear highlights after move
                }
            });
            
            // Track the highlighted panels to clear later
            highlightedPanels.add(panel);
        }
    }
	
	//Get the symbol for the piece
	private String getPieceSymbol(ChessPiece piece) {
        // Return the symbol for the chess piece
        if (piece instanceof ChessPiece.Pawn) {
            return piece.getColor().equals("white") ? "\u2659" : "\u265F";
        } else if (piece instanceof ChessPiece.Knight) {
            return piece.getColor().equals("white") ? "\u265E" : "\u2658";
        } else if (piece instanceof ChessPiece.Bishop) {
            return piece.getColor().equals("white") ? "\u265D" : "\u2657";
        } else if (piece instanceof ChessPiece.Rook) {
            return piece.getColor().equals("white") ? "\u265C" : "\u2656";
        } else if (piece instanceof ChessPiece.Queen) {
            return piece.getColor().equals("white") ? "\u265B" : "\u2655";
        } else if (piece instanceof ChessPiece.King) {
            return piece.getColor().equals("white") ? "\u265A" : "\u2654";
        }
        return "";
    }
	
	//Method for clearing highlighted areas
	private void clearHighlights() {
		// Clear all highlights (reset background colors)
        for (JPanel panel : highlightedPanels) {
            int index = square_PanelArray.indexOf(panel);
            int x = index % 8;
            int y = index / 8;
            if ((x % 2 == 0 && y % 2 == 1) || (x % 2 == 1 && y % 2 == 0)) {
                panel.setBackground(squareColor1);
            } else {
                panel.setBackground(squareColor2);
            }
        }
        highlightedPanels.clear(); // Clear the list of highlighted panels
    }
}	
	

