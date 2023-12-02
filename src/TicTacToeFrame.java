import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame {
    private TicTacToeTile[][] board;
    private JButton quitButton;
    private TicTacToe gameLogic;

    public TicTacToeFrame() {
        super("Tic Tac Toe");
        setLayout(new GridLayout(4, 3));
        board = new TicTacToeTile[3][3];
        quitButton = new JButton("Quit");
        gameLogic = new TicTacToe();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = new TicTacToeTile(row, col);
                board[row][col].setText(" ");
                board[row][col].addActionListener(new ButtonClickListener());
                add(board[row][col]);
            }
        }
        quitButton.addActionListener(new ButtonClickListener());
        add(quitButton);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof TicTacToeTile) {
                TicTacToeTile clickedTile = (TicTacToeTile) e.getSource();
                if (gameLogic.makeMove(clickedTile.getRow(), clickedTile.getCol())) {
                    if (gameLogic.checkForWin()) {
                        updateBoard();
                        int option = JOptionPane.showConfirmDialog(TicTacToeFrame.this, "Player " + gameLogic.getCurrentPlayer() + " wins! Do you want to play another game?", "Game Over", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            resetGame();
                        } else {
                            System.exit(0);
                        }
                    } else if (gameLogic.checkForTie()) {
                        updateBoard();
                        int option = JOptionPane.showConfirmDialog(TicTacToeFrame.this, "It's a Tie! Do you want to play another game?", "Game Over", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            resetGame();
                        } else {
                            System.exit(0);
                        }
                    } else {
                        updateBoard();
                    }
                } else {
                    JOptionPane.showMessageDialog(TicTacToeFrame.this, "Invalid move. Try again.");
                }
            } else if (e.getSource() == quitButton) {
                System.exit(0);
            }
        }
    }

    private void updateBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col].setText(gameLogic.getBoard()[row][col]);
            }
        }
    }

    private void resetGame() {
        gameLogic.resetGame();
        updateBoard();
    }
}

