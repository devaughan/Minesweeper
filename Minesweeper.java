import java.util.Scanner;
public class Minesweeper {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        
        int difficulty;

        System.out.println("\n\t/////////////////");
        System.out.println("\t///Minesweeper///");
        System.out.println("\t/////////////////\n");
        
        System.out.println("Would you like to play on Easy(0), Medium(1), or Hard(2)?");
        System.out.print("Input corresponding value: ");
        difficulty = input.nextInt();

        if (difficulty == 0) {
            System.out.println("Game mode is set to easy");
            gameStart(5, 5, 6);
        }
        else if (difficulty == 1) {
            System.out.println("Game mode is set to Medium");
            gameStart(10, 10, 20);
        }
        else if (difficulty == 2) {
            System.out.println("Game mode is set to Hard");
            gameStart(20, 20, 80);
        }

        input.close();

    }

    public static void gameStart(int width, int length, int numberOfMines) {
        
        Scanner input = new Scanner(System.in);
        
        Board board = new Board(width, length, numberOfMines);
        
        board.createBoard();
        board.createClear();

        board.printPlayerBoard();

        int xValue = -1;
        int yValue = -1;
        boolean hasLost = false;

        while (!board.hasWon() && !hasLost) {
            while (!board.inBounds(xValue, yValue)) {
                System.out.println("Input: ");
                
                // y and x are switched due to the arrays
                System.out.print("    x: ");
                yValue = input.nextInt() - 1;
                
                System.out.print("    y: ");
                xValue = input.nextInt() - 1;
                
                if (!board.inBounds(xValue, yValue)) {
                    System.out.println("Input not included in bounds");
                }
                
                // i have no idea why this resets loop
                if (board.isClear(xValue, yValue)) {
                    System.out.println("Input is already clear");
                }
            }
    
            if (board.getMoves() != 0) {
                board.setClear(xValue, yValue);
            }
            else {
                board.setInitialClear(xValue, yValue);
                //System.out.println("successfully set inital clear");
            
                board.createMines();
                //System.out.println("successfully created mines");

                board.calcValues();
                //System.out.println("successfully calculated mine values");

                board.setAdjacentValuesFalse(xValue, yValue);
                board.setClear(xValue, yValue);
            }

            if (board.isMine(xValue, yValue)) {
                hasLost = true;
                break;
            }                 
           /* 
            System.out.println("True Board:");
            board.printBoard();
            */

            System.out.println("Player Board:");
            board.printPlayerBoard();

            xValue = -1;
            yValue = -1;
        }
        if (hasLost) {
            System.out.println("Sorry you lost :(");
        }
        else {
            System.out.println("Congratulations you won! :D");
        }

        input.close();
    
    }
}