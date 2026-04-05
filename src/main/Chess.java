package main;

public class Chess {

    public static void main(String[] args) {
        ChessGame game = new ChessGame();
        GUI gui = new GUI(game);
        gui.updateGUI();
    }
}