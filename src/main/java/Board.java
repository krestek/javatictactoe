class Board {
    public enum Player {X, Y}

    private Player[][] board;

    Board(int size) {
        board = new Player[size][size];
    }

    void printBoard() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        for (Player[] players : board) {
            for (Player player : players) {
                System.out.print("[" + (player == null ? ' ' : player) + "]");
            }
            System.out.println();
        }
    }

    boolean set(int row, int col, Player player) throws RowNotEmptyException {
        if (player == null)
            throw new RuntimeException("Board section cannot be set to null");
        if (row < 0 || col < 0 || row >= board.length || col >= board[row].length || board[row][col] != null)
            throw new RowNotEmptyException();
        board[row][col] = player;
        int diaRes = 0, revDiaRes = 0;
        for (int r = 0; r < board.length; r++) {
            if (board[r][r] == player)
                diaRes++;
            if (board[2 - r][r] == player)
                revDiaRes++;
            int rowRes = 0, colRes = 0;
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] == player)
                    rowRes++;
                if (board[c][r] == player)
                    colRes++;
            }

            if (diaRes == board.length || revDiaRes == board.length || colRes == board.length || rowRes == board.length)
                return true;
        }
        return false;
    }


    class RowNotEmptyException extends Exception {
    }
}
