package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUI {
    private List<JPanel> highlightedPanels = new ArrayList<>();
    private ChessGame game;
    private ChessBoard board;
    private JFrame frame;

    private List<JLabel> square_LabelArray = new ArrayList<>();
    private List<JPanel> square_PanelArray = new ArrayList<>();

    // Colors
    private Color squareColor1 = new Color(118,150,86);
    private Color squareColor2 = new Color(238,238,210);
    private Color squareColor1_Highlited = new Color(168,219,117);

    private ChessPiece selectedPiece = null;

    public GUI(ChessGame game) {
        this.game = game;
        this.board = game.getBoard();

        int width = board.getWidht();
        int height = board.getHeight();

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Chess");
        frame.setSize(1000,1000);
        frame.setLayout(new GridLayout(height, width));
        frame.setResizable(false);

        // Draw board
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                JPanel panel = new JPanel();

                if ((x % 2 == 0 && y % 2 == 1) || (x % 2 == 1 && y % 2 == 0)) {
                    panel.setBackground(squareColor1);
                } else {
                    panel.setBackground(squareColor2);
                }

                JLabel label = new JLabel("");
                panel.add(label);
                frame.add(panel);

                square_LabelArray.add(label);
                square_PanelArray.add(panel);
            }
        }

        frame.setVisible(true);
    }

    public void updateGUI() {
        List<ChessPiece> piecesOnBoard = board.getChessPieces();

        // Clear labels + listeners
        for (JLabel label : square_LabelArray) {
            label.setText("");
            for (MouseListener listener : label.getMouseListeners()) {
                label.removeMouseListener(listener);
            }
        }

        // Draw pieces
        for (ChessPiece piece : piecesOnBoard) {
            int x = piece.getX();
            int y = piece.getY();
            int index = y * board.getWidht() + x;

            JLabel label = square_LabelArray.get(index);

            label.setText(getPieceSymbol(piece));
            label.setFont(label.getFont().deriveFont(75f));

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (selectedPiece == piece) return;

                    clearHighlights();
                    selectedPiece = piece;
                    highlightPossibleMoves(piece);
                }
            });
        }
    }

    private void highlightPossibleMoves(ChessPiece piece) {
        List<int[]> possibleMoves = piece.calculatePossibleMoves();

        int width = board.getWidht();
        int height = board.getHeight();

        for (int[] move : possibleMoves) {
            int targetX = move[0];
            int targetY = move[1];

            // prevent out-of-bounds crash
            if (targetX < 0 || targetX >= width || targetY < 0 || targetY >= height) {
                continue;
            }

            int targetIndex = targetX + targetY * width;
            JPanel panel = square_PanelArray.get(targetIndex);
            panel.setBackground(squareColor1_Highlited);

            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (selectedPiece != null) {

                        boolean moved = game.movePiece(selectedPiece, targetX, targetY);

                        if (moved) {
                            selectedPiece = null;
                            updateGUI();
                        }
                    }
                    clearHighlights();
                }
            });

            highlightedPanels.add(panel);
        }
    }

    private String getPieceSymbol(ChessPiece piece) {
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

    private void clearHighlights() {
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
        highlightedPanels.clear();
    }
}