import java.util.Scanner;
public class Minesweeper {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        
        int difficulty;

        System.out.println("\n\t/////////////////");
        System.out.println("\t///Minesweeper///");
        System.out.println("\t/////////////////\n");
        
        System.out.println("Would you like to play on Easy(0), Medium(1), or Hard(2)?");
        System.out.print("Input corresponding : ");
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

        board.printPlayerBoard();

        int x = -1;
        int y = -1;
        String userInput = "";
        boolean hasLost = false;

        while (!board.hasWon() && !hasLost) {
            while (!board.checkValidMove(x, y)) {
                System.out.print("Input: ");
                userInput = input.next();
                userInput = userInput.toLowerCase();
                y = input.nextInt() - 1;
                x = input.nextInt() - 1;

                System.out.println(userInput + "(" + x + ", " + y + ")");

                /*
                // y and x are switched due to the arrays
                System.out.print("    x: ");
                y = input.nextInt() - 1;
                
                System.out.print("    y: ");
                x = input.nextInt() - 1;
                */

                if (!board.inBounds(x, y)) {
                    System.out.println("Input not included in bounds");
                }
                
                // i have no idea why this resets loop
                /* if (board.isClear(x, y)) {
                    System.out.println("Input is already clear");
                } */
            }
            if (userInput.equals("f")) {
                board.flagMove(x, y);
            }
            else if (userInput.equals("r")) {
                board.removeFlagMove(x, y);
            }
            else {
                if (board.getMoves() != 0) {
                    board.playerMove(x, y);
                }
                else {
                    board.firstMove(x, y);
                }
    
                if (board.isMine(x, y)) {
                    hasLost = true;
                    break;
                }    
            }
                         
            /* 
            System.out.println("True Board:");
            board.printBoard();
            */

            System.out.println("Player Board:");
            board.printPlayerBoard();

            x = -1;
            y = -1;

        }

        if (hasLost) {
            board.printBoard();
            System.out.println("Sorry you lost :(");
        }
        else {
            board.printBoard();
            System.out.println("Congratulations you won! :D");
        }

        input.close();
    
    }
}