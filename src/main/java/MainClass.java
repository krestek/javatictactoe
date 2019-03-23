import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        Board b = new Board(3);
        Scanner scanner = new Scanner(System.in);
        Board.Player currentPlayer = Board.Player.X;
        b.printBoard();
        while (true) {
            System.out.println("Player: " + currentPlayer);
            System.out.print("Row: ");
            int row = scanner.nextInt();
            System.out.print("Col: ");
            int col = scanner.nextInt();
            try {
                if (b.set(row - 1, col - 1, currentPlayer)) {
                    b.printBoard();
                    System.out.println(currentPlayer + " has won!");
                    return;
                }
            } catch (Board.RowNotEmptyException e) {
                b.printBoard();
                System.out.println("Please enter a valid position.");
                continue;
            }
            currentPlayer = currentPlayer == Board.Player.X ? Board.Player.Y : Board.Player.X;
            b.printBoard();
        }
    }
}