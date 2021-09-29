import java.util.Scanner;
public class Minesweeper {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        
        Board board = new Board(10, 10, 20);
        board.createBoard();

        

        board.createMines();
        board.printBoard();
        System.out.println();
        board.calcValues();
        board.printBoard();

    }
}