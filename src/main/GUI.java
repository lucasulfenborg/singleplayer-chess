package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Dictionary;
import java.util.Hashtable;

public class GUI {

	//Global JSWING components, accessible everywhere in the program
	ChessBoard board;
	JFrame frame;
	List<JLabel> square_LabelArray;
	
	public GUI(ChessBoard board) {
		this.board = board;
		
		//Colors
		Color squareColor1 = new Color(118,150,86);
		Color squareColor2 = new Color(238,238,210);
		
		int width = this.board.getWidht();
		int height = this.board.getHeight();
		
		//Create the window
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Chess");
		frame.setSize(1000,1000);
		frame.setLayout(new GridLayout(height, width));
		frame.setResizable(false);
		
		//List for accessing each square
		this.square_LabelArray = new ArrayList<>();
        Dictionary<String, Integer> d = new Hashtable<>();

				
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
			}
		}

		frame.setVisible(true);
	}
	
	//Method for drawing the pieces on the GUI
	public void updateGUI() {
		List<ChessPiece> piecesOnBoard = new ArrayList<>();
		piecesOnBoard = board.getChessPieces();
		
		System.out.println(this.square_LabelArray.size());
		
		for (ChessPiece piece : piecesOnBoard) {
			 int x = piece.getX();
			 int y = piece.getY();
			 
			 
		}
	}
	
	

}
