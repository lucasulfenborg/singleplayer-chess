package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUI {

	//Colors
	private Color squareColor1 = new Color(118,150,86);
	private Color squareColor2 = new Color(238,238,210);
	private Color squareColor1_Highlited = new Color(168,219,117);
	
	
	//Global JSWING components, accessible everywhere in the program
	private ChessBoard board;
	private JFrame frame;
	
	
	
	public List<JLabel> square_LabelArray = new ArrayList<>();
	public List<JPanel> square_PanelArray = new ArrayList<>();
	
	//Track which piece is currently selected
	private ChessPiece selectedPiece = null;
	
	
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
	
	//Method for drawing the pieces on the GUI
	public void updateGUI() {
	    List<ChessPiece> piecesOnBoard = board.getChessPieces();
	    
	    
	    //Remove old labels
	    for (JLabel label : square_LabelArray) {
	    	label.setText("");
	    	
	    	// Remove all mouse listeners before updating the label
	        for (MouseListener listener : label.getMouseListeners()) {
	            label.removeMouseListener(listener);
	        }
	    }
	    
	    //Place out chess pieces
	    for (ChessPiece piece : piecesOnBoard) {
	        int x = piece.getX();
	        int y = piece.getY();
	        
	        // counts the squares in (GridLayout one by one)
	        int index = y * board.getWidht() + x;
	        
	        //checks so the square exsists before putting piece in it
	        if (index >= 0 && index < square_LabelArray.size()) {
	            JLabel label = square_LabelArray.get(index);

	            
	            
	            // display a symbol representing the chessPiece
	            String symbol = "";
	            if (piece instanceof ChessPiece.Pawn) {
	                symbol = piece.getColor().equals("white") ? "\u2659" : "\u265F";
	                
	            } else if (piece instanceof ChessPiece.Knight) {
	                symbol = piece.getColor().equals("white") ? "\u265E" : "\u2658";
	                
	            } else if (piece instanceof ChessPiece.Bishop) {
	                symbol = piece.getColor().equals("white") ? "\u265D" : "\u2657";
	                
	            } else if (piece instanceof ChessPiece.Rook) {
	                symbol = piece.getColor().equals("white") ? "\u265C" : "\u2656";
	                
	            } else if (piece instanceof ChessPiece.Queen) {
	                symbol = piece.getColor().equals("white") ? "\u265B" : "\u2655";
	                
	            } else if (piece instanceof ChessPiece.King) {
	                symbol = piece.getColor().equals("white") ? "\u265A" : "\u2654";
	            }
	            label.setText(symbol);
	            
	            Font labelFont = label.getFont();
	            label.setFont(labelFont.deriveFont(75f));	    
	            
	            //Make symbol clickable
	            label.addMouseListener(new MouseAdapter() {
	                @Override
	                public void mouseClicked(MouseEvent e) {
	                	
	                	if (selectedPiece == piece) {
	                		return;
	                	}
	                	
	                	//Clear previous highlights
	                	clearHighlights();
	                	
	                	// Set the new selected piece
                        selectedPiece = piece;
	                	
	                	//Highlight possible moves
	                	int[][] possibleMoves = piece.calculatePossibleMoves();
	                	for (int[] move : possibleMoves) {
	                		//System.out.println(move);
	                		int targetX = move[0];
	                		int targetY = move[1];
	                		
	                		int targetIndex = targetX + targetY * 8;
	                        JPanel panel = square_PanelArray.get(targetIndex);
	                        panel.setBackground(squareColor1_Highlited);
	                				
	                	}
	                	System.out.println("clicked" + label.getText() );
	                }
	            });
	        }  
	    }  
	}
	
	private void clearHighlights() {
		//Draw squares on the chess board
				for (int y = 0; y < this.board.height; y++) {
					for (int x = 0; x < this.board.widht; x++) {
						
			            JPanel currentPanel = this.square_PanelArray.get(y * this.board.getWidht() + x); 

						//Draw square
						if ((x % 2 == 0 && y % 2 == 1) || (x % 2 == 1 && y % 2 == 0)) {
							currentPanel.setBackground(this.squareColor1);
						}
						
						else {
							currentPanel.setBackground(this.squareColor2);
						}
						
					}
				}
        
    
	}
}	
	

