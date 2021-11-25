public class Board 
{
    private int length;
    private int width;
    private int area;
    private int[][] mine;
    private int numberOfMines;
    private int moves;
    Clears clearBoard = new Clears();
    Flags flagBoard = new Flags();

    // constructors
    public Board(int l, int w, int m) 
    {
        length = l;
        width = w;
        area = length * width;
        numberOfMines = m;
        moves = 0;
        mine = new int[length][width];
        clearBoard.setDimensions(width, length);
        flagBoard.setDimensions(width, length);
    }

    // set methods
    public void setLength(int l) 
    {
        length = l;
    }

    public void setWidth(int w)
    {
        width = w;
    }

    public void setNumberOfMines(int m) {
        numberOfMines = m;
    }

    // get methods
    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getArea() {
        return area;
    }

    public int getNumberOfMines() {
        return numberOfMines;
    }

    public int getMoves() {
        return moves;
    }

    public int getValue(int x, int y) {
        return mine[x][y];
    }

    // only used on move 0
    // fix this code sucks
    public void firstMove(int x, int y) {
        clearBoard.setClear(x, y);
        clearBoard.clearAdjacentValues(x, y);
        moves++;
        createMines();
        calcValues();
        clearBoard.setAdjacentValuesFalse(x, y);
        playerMove(x, y);
    }

    // works
    public void playerMove(int x, int y) {
        clearBoard.setClear(x, y);
        moves++;
        if (getValue(x, y) == 0) {
            seedAdjacentValues(x, y);
        }
    }

    // 
    public void flagMove(int x, int y) {
        flagBoard.flagLocation(x, y);
        moves++;
    }

    public void removeFlagMove(int x, int y) {
        flagBoard.unflagLocation(x, y);
        moves++;
    }

    public boolean checkValidMove(int x, int y) {
        if (!inBounds(x, y)){
            return false;
        }
        if (flagBoard.getLocation(x, y)) {
            return false;
        }
        if (clearBoard.isClear(x, y)) {
            return false;
        }
        return true;
    }

    // initialize array values
    public void createBoard() {
        mine = new int[length][width];
        for (int i = 0; i < length; i++) 
        {
            for (int j = 0; j < width; j++) 
            {
                mine[i][j] = 0;
            }
        }
    }

    public void createMines() {
        if (area - clearBoard.getNumberOfClears() < numberOfMines) {
            numberOfMines = area - clearBoard.getNumberOfClears();
        }
        for(int i = numberOfMines; i > 0; i--) 
        {
            int x = (int)(Math.random() * length);
            int y = (int)(Math.random() * width);
            if (isMine(x, y) || clearBoard.isClear(x, y)) {
                i++;
            }
            else {
                mine[x][y] = -1;
            }
        }
    }

    public void calcValues() {
        for (int j = 0; j < length; j++) 
        {
            outer:
                for (int i = 0; i < width; i++) 
                {
                    // checks if current space is a mine
                    while (mine[i][j] == -1) 
                    {
                        if (i == (length - 1)) {
                            break outer;
                        }
                        i++;
                    }

                    // checks upper left position
                    if (i > 0 && j > 0 && mine[i - 1][j - 1] == -1) {
                        mine[i][j]++;
                    }

                    // checks upper position
                    if (j > 0 && mine[i][j - 1] == -1) {
                        mine[i][j]++;
                    }

                    // checks upper right position
                    if (j > 0 && i < (length - 1) && mine[i + 1][j - 1] == -1) {
                        mine[i][j]++;
                    }

                    // checks left position
                    if (i > 0 && mine[i - 1][j] == -1) {
                        mine[i][j]++;
                    }

                    // checks right position
                    if (i < (length - 1) && mine[i + 1][j] == -1) {
                        mine[i][j]++;
                    }

                    // checks lower left position
                    if (i > 0 && j < (width - 1) && mine[i - 1][j + 1] == -1) {
                        mine[i][j]++;
                    }   

                    // checks lower position
                    if (j < (width - 1) && mine[i][j + 1] == -1) {
                        mine[i][j]++;
                    }

                    // checks lower right position
                    if (i < (length - 1) && j < (width - 1) && mine[i + 1][j + 1] == -1) {
                        mine[i][j]++;
                    }             
                }
        }
    }

    public void printBoard() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print("[");
                if (mine[i][j] == -1) {
                    System.out.print("x");
                }
                else if (mine[i][j] == 0) {
                    System.out.print(" ");
                }
                else 
                    System.out.print(mine[i][j]);
                System.out.print("]");
            }
            System.out.println();
        }
    }

    public void printPlayerBoard() {
        System.out.println("\t| Mines: " + (getNumberOfMines() - flagBoard.totalFlags()) + " |     |  Moves: " + getMoves() + " |");
        System.out.print("\t");

        // print out x grid number
        for (int x = 1; x <= width; x++) {
            if ((x + 1) >= 10) {
                System.out.print(" " + x);
            }
            else {
                System.out.print(" " + x + " ");
            }
        }

        System.out.println();

        for (int x = 0; x < length; x++) {
            // print out y grid number
            if ((x + 1) >= 10) {
                System.out.print(" " + (x + 1) + "     ");
            }
            else {
                System.out.print(" " + (x + 1) + "      ");
            }

            for (int y = 0; y < width; y++) {

                System.out.print("[");

                if (!clearBoard.isClear(x, y)) {
                    if (flagBoard.getLocation(x, y)) {
                        System.out.print("f");
                    }
                    else {
                    System.out.print("\\");
                    }
                }

                else {
                    if (mine[x][y] == 0) {
                        System.out.print(" ");
                    }
                    else if (mine[x][y] == -1) {
                        System.out.print("x");
                    }
                    
                    else 
                        System.out.print(mine[x][y]);
                }

                System.out.print("]");

            }

            System.out.println();

        }
    }

    public void printMines() {
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < width; y++) {
                System.out.print("[");
                if (mine[x][y] == -1) {
                    System.out.print("x");
                }
                else {
                    System.out.print(" ");
                }
                System.out.print("]");
            }
            System.out.println();
        }
    }

    public boolean inBounds(int x, int y) {
        if (x >= 0 && x < getWidth() && y >= 0 && y < getLength()) {
            return true;
        }
        return false;
    }

    public boolean isMine(int x, int y) {
        if (mine[x][y] == -1) {
            return true;
        }
        return false;
    }

    

    

    public boolean hasWon() {
        if (clearBoard.getNumberOfClears() == area - numberOfMines) {
            return true;
        }
        return false;
    }

    private int checkGridPoint(int x, int y) {
        clearBoard.setClear(x, y);
        return mine[x][y];
    }

    public void seedAdjacentValues(int x, int y) {
        if (x > 0 && y > 0) {
            if (!clearBoard.isClear(x - 1, y - 1)) {
                if (checkGridPoint(x - 1, y - 1) == 0) {
                    seedAdjacentValues(x - 1, y - 1);
                }
            }
        }

        // clear upper position
        if (y > 0) {
            if (!clearBoard.isClear(x, y - 1)) {
                if (checkGridPoint(x, y - 1) == 0) {
                    seedAdjacentValues(x, y - 1);
                }
            }
        }

        // clear upper right position
        if (y > 0 && x < (length - 1)) {
            if (!clearBoard.isClear(x + 1, y - 1)) {
                if (checkGridPoint(x + 1, y - 1) == 0) {
                    seedAdjacentValues(x + 1, y - 1);
                }
            }
        }

        // clear left position
        if (x > 0) {
            if (!clearBoard.isClear(x - 1, y)) {
                if (checkGridPoint(x - 1, y) == 0) {
                    seedAdjacentValues(x - 1, y);
                }
            }
        }

        // clear right position
        if (x < (length - 1)) {
            if (!clearBoard.isClear(x + 1, y)) {
                if (checkGridPoint(x + 1, y) == 0) {
                    seedAdjacentValues(x + 1, y);
                }
            }
        }

        // clear lower left position
        if (x > 0 && y < (width - 1)) {
            if (!clearBoard.isClear(x - 1, y + 1)) {
                if (checkGridPoint(x - 1, y + 1) == 0) {
                    seedAdjacentValues(x - 1, y + 1);
                }
            }
        }   

        // clear lower position
        if (y < (width - 1)) {
            if (!clearBoard.isClear(x, y + 1)) {
                if (checkGridPoint(x, y + 1) == 0) {
                    seedAdjacentValues(x, y + 1);
                }
            }
        }

        // clear lower right position
        if (x < (length - 1) && y < (width - 1)) {
            if (!clearBoard.isClear(x + 1, y + 1)) {
                if (checkGridPoint(x + 1, y + 1) == 0) {
                    seedAdjacentValues(x + 1, y + 1);
                }
            }
        }
    }

}