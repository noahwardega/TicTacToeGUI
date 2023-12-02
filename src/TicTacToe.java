public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private String[][] board;
    private String currentPlayer;
    private int moveCount;

    public TicTacToe() {
        board = new String[ROW][COL];
        currentPlayer = "X";
        moveCount = 0;
        clearBoard();
    }

    private void clearBoard() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                board[row][col] = " ";
            }
        }
    }

    public String[][] getBoard() {
        return board;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean makeMove(int row, int col) {
        if (isValidMove(row, col)) {
            board[row][col] = currentPlayer;
            if (checkForWin()) {
                moveCount++;
                return true;
            }
            switchPlayer();
            moveCount++;
            return true;
        }
        return false;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
    }

    private boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    public boolean checkForWin() {
        if (isColWin() || isRowWin() || isDiagnalWin()) {
            return true;
        }
        return false;
    }

    private boolean isColWin() {
        for (int col = 0; col < COL; col++) {
            if (board[0][col].equals(currentPlayer) &&
                    board[1][col].equals(currentPlayer) &&
                    board[2][col].equals(currentPlayer)) {
                return true;
            }
        }
        return false;
    }

    private boolean isRowWin() {
        for (int row = 0; row < ROW; row++) {
            if (board[row][0].equals(currentPlayer) &&
                    board[row][1].equals(currentPlayer) &&
                    board[row][2].equals(currentPlayer)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDiagnalWin() {
        if (board[0][0].equals(currentPlayer) &&
                board[1][1].equals(currentPlayer) &&
                board[2][2].equals(currentPlayer)) {
            return true;
        }
        if (board[0][2].equals(currentPlayer) &&
                board[1][1].equals(currentPlayer) &&
                board[2][0].equals(currentPlayer)) {
            return true;
        }
        return false;
    }

    public boolean checkForTie() {
        boolean xFlag = false;
        boolean oFlag = false;
        for (int row = 0; row < ROW; row++) {
            if (board[row][0].equals("X") ||
                    board[row][1].equals("X") ||
                    board[row][2].equals("X")) {
                xFlag = true; //there is an X in this row
            }
            if (board[row][0].equals("O") ||
                    board[row][1].equals("O") ||
                    board[row][2].equals("O")) {
                oFlag = true; //there is an O in this row
            }

            if (!(xFlag && oFlag)) {
                return false; //No tie can still have a row win
            }

            xFlag = oFlag = false;

        }

        for (int col = 0; col < COL; col++) {
            if (board[0][col].equals("X") ||
                    board[1][col].equals("X") ||
                    board[2][col].equals("X")) {
                xFlag = true; //there is an X in this col
            }
            if (board[0][col].equals("O") ||
                    board[1][col].equals("O") ||
                    board[2][col].equals("O")) {
                oFlag = true; //there is an O in this col
            }

            if (!(xFlag && oFlag)) {
                return false; //No tie can still have a col win
            }
        }

        xFlag = oFlag = false;

        if (board[0][0].equals("X") ||
                board[1][1].equals("X") ||
                board[2][2].equals("X")) {
            xFlag = true;
        }
        if (board[0][0].equals("O") ||
                board[1][1].equals("O") ||
                board[2][2].equals("O")) {
            oFlag = true;
        }
        if (!(xFlag && oFlag)) {
            return false; //No tie can still have a diag win
        }
        xFlag = oFlag = false;

        if (board[0][2].equals("X") ||
                board[1][1].equals("X") ||
                board[2][0].equals("X")) {
            xFlag = true;
        }
        if (board[0][2].equals("O") ||
                board[1][1].equals("O") ||
                board[2][0].equals("O")) {
            oFlag = true;
        }
        if (!(xFlag && oFlag)) {
            return false; //No tie can still have a diag win
        }

        return true;
    }
    public void resetGame() {
        currentPlayer = "X";
        moveCount = 0;
        clearBoard();
    }
}


