package main;

import java.util.ArrayList;
import java.util.List;

public abstract class ChessPiece {
    private int x;
    private int y;
    private String color;

    public ChessPiece(int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract List<int[]> calculatePossibleMoves();

    public void makeMove(int[] newLocation) {
        this.setCoordinates(newLocation[0], newLocation[1]);
    }

    // =========================
    // PIECES
    // =========================

    public static class Pawn extends ChessPiece {
        private boolean hasMoved = false;

        public Pawn(int x, int y, String color) {
            super(x, y, color);
        }

        @Override
        public List<int[]> calculatePossibleMoves() {
            List<int[]> moves = new ArrayList<>();

            int direction = getColor().equals("white") ? -1 : 1;

            // Move forward 1
            moves.add(new int[]{getX(), getY() + direction});

            // Move forward 2 if first move
            if (!hasMoved) {
                moves.add(new int[]{getX(), getY() + (2 * direction)});
            }

            return moves;
        }

        @Override
        public void makeMove(int[] newLocation) {
            hasMoved = true;
            super.makeMove(newLocation);
        }
    }

    public static class Knight extends ChessPiece {

        public Knight(int x, int y, String color) {
            super(x, y, color);
        }

        @Override
        public List<int[]> calculatePossibleMoves() {
            List<int[]> moves = new ArrayList<>();

            int x = getX();
            int y = getY();

            int[][] offsets = {
                {1, 2}, {2, 1}, {-1, 2}, {-2, 1},
                {1, -2}, {2, -1}, {-1, -2}, {-2, -1}
            };

            for (int[] o : offsets) {
                moves.add(new int[]{x + o[0], y + o[1]});
            }

            return moves;
        }
    }

    public static class Bishop extends ChessPiece {

        public Bishop(int x, int y, String color) {
            super(x, y, color);
        }

        @Override
        public List<int[]> calculatePossibleMoves() {
            List<int[]> moves = new ArrayList<>();

            int x = getX();
            int y = getY();

            // Diagonals (no collision yet)
            for (int i = 1; i < 8; i++) {
                moves.add(new int[]{x + i, y + i});
                moves.add(new int[]{x - i, y + i});
                moves.add(new int[]{x + i, y - i});
                moves.add(new int[]{x - i, y - i});
            }

            return moves;
        }
    }

    public static class Rook extends ChessPiece {

        public Rook(int x, int y, String color) {
            super(x, y, color);
        }

        @Override
        public List<int[]> calculatePossibleMoves() {
            List<int[]> moves = new ArrayList<>();

            int x = getX();
            int y = getY();

            for (int i = 1; i < 8; i++) {
                moves.add(new int[]{x + i, y});
                moves.add(new int[]{x - i, y});
                moves.add(new int[]{x, y + i});
                moves.add(new int[]{x, y - i});
            }

            return moves;
        }
    }

    public static class Queen extends ChessPiece {

        public Queen(int x, int y, String color) {
            super(x, y, color);
        }

        @Override
        public List<int[]> calculatePossibleMoves() {
            List<int[]> moves = new ArrayList<>();

            int x = getX();
            int y = getY();

            for (int i = 1; i < 8; i++) {
                // Rook moves
                moves.add(new int[]{x + i, y});
                moves.add(new int[]{x - i, y});
                moves.add(new int[]{x, y + i});
                moves.add(new int[]{x, y - i});

                // Bishop moves
                moves.add(new int[]{x + i, y + i});
                moves.add(new int[]{x - i, y + i});
                moves.add(new int[]{x + i, y - i});
                moves.add(new int[]{x - i, y - i});
            }

            return moves;
        }
    }

    public static class King extends ChessPiece {

        public King(int x, int y, String color) {
            super(x, y, color);
        }

        @Override
        public List<int[]> calculatePossibleMoves() {
            List<int[]> moves = new ArrayList<>();

            int x = getX();
            int y = getY();

            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (dx != 0 || dy != 0) {
                        moves.add(new int[]{x + dx, y + dy});
                    }
                }
            }

            return moves;
        }
    }
}