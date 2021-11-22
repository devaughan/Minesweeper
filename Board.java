public class Board 
{
    private int length;
    private int width;
    private int area;
    private int[][] mine;
    private boolean[][] clear;
    private int mines;
    private int moves;

    // constructors
    public Board(int l, int w, int m) 
    {
        length = l;
        width = w;
        area = length * width;
        mines = m;
        moves = 0;
        mine = new int[length][width];
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

    public void setMines(int m) {
        mines = m;
    }

    public void setClear(int x, int y) {
        moves++;
        clear[x][y] = true;
    }

    public void setInitialClear(int x, int y) {
        moves++;
        // clear target position
        clear[x][y] = true;
        clearAdjacentValues(x, y);
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

    public int getMines() {
        return mines;
    }

    public int getMoves() {
        return moves;
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

    public void createClear() {
        clear = new boolean[length][width];
        for (int i = 0; i < length; i++) 
        {
            for (int j = 0; j < width; j++) 
            {
                clear[i][j] = false;
            }
        }
    }

    public void createMines() {
        if (area - numberOfClears() < mines) {
            mines = area - numberOfClears();
        }
        for(int i = mines; i > 0; i--) 
        {
            int x = (int)(Math.random() * length);
            int y = (int)(Math.random() * width);
            if (isMine(x, y) || clear[x][y]) {
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

    public void checkClearValues() {
        for (int y = 0; y < length; y++) 
        {
            outer:
            for (int x = 0; x < width; x++) 
            {
                while (mine[x][y] != 0) {
                    if (x == (length - 1)) {
                        break outer;
                    }
                    x++;
                }

                // checks upper left position
                if (x > 0 && y > 0 && clear[x - 1][y - 1]) {
                    clear[x][y] = true;
                    clearAdjacentValues(x, y);
                }

                // checks upper position
                else if (y > 0 && clear[x][y - 1]) {
                    clear[x][y] = true;
                    clearAdjacentValues(x, y);
                }

                // checks upper right position
                else if (y > 0 && x < (length - 1) && clear[x + 1][y - 1] ) {
                    clear[x][y] = true;
                    clearAdjacentValues(x, y);
                }

                // checks left position
                else if (x > 0 && clear[x - 1][y]) {
                    clear[x][y] = true;
                    clearAdjacentValues(x, y);
                }

                // checks right position
                else if (x < (length - 1) && clear[x + 1][y]) {
                    clear[x][y] = true;
                    clearAdjacentValues(x, y);
                }

                // checks lower left position
                else if (x > 0 && y < (width - 1) && clear[x - 1][y + 1]) {
                    clear[x][y] = true;
                    clearAdjacentValues(x, y);
                }   

                // checks lower position
                else if (y < (width - 1) && clear[x][y + 1] ) {
                    clear[x][y] = true;
                    clearAdjacentValues(x, y);
                }

                // checks lower right position
                else if (x < (length - 1) && y < (width - 1) && clear[x + 1][y + 1]) {
                    clear[x][y] = true;
                    clearAdjacentValues(x, y);
                }
            }  
        }
    }

    public void printPlayerBoard() {
        System.out.println("\t| Mines: " + getMines() + " |     |  Moves: " + getMoves() + " |");
        System.out.print("\t");
        // print out x grid number
        for (int i = 1; i <= width; i++) {
            if ((i + 1) >= 10) {
                System.out.print(" " + i);
            }
            else {
                System.out.print(" " + i + " ");
            }
        }
        System.out.println();
        for (int i = 0; i < length; i++) {
            // print out y grid number
            if ((i + 1) >= 10) {
                System.out.print(" " + (i + 1) + "     ");
            }
            else {
                System.out.print(" " + (i + 1) + "      ");
            }
            for (int j = 0; j < width; j++) {
                System.out.print("[");
                // System.out.print("i = " + i);
                // System.out.print("j = " + j);
                if (!clear[i][j]) {
                    System.out.print("\\");
                }
                else {
                    if (mine[i][j] == 0) {
                        System.out.print(" ");
                    }
                    else if (mine[i][j] == -1) {
                        System.out.print("x");
                    }
                    else 
                        System.out.print(mine[i][j]);
                }
                System.out.print("]");
            }
            System.out.println();
        }
    }

    public void printMines() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print("[");
                if (mine[i][j] == -1) {
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

    public boolean isClear(int x, int y) {
        if (clear[x][y]) {
            return true;
        }
        return false;
    }

    public int numberOfClears() {
        int sum = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (clear[i][j]) {
                    sum++;
                }
            }
        }
        return sum;
    }

    public boolean hasWon() {
        if (numberOfClears() == area - mines) {
            return true;
        }
        return false;
    }

    private void clearAdjacentValues(int x, int y) {
        if (x > 0 && y > 0) {
            clear[x - 1][y - 1] = true;
        }

        // clear upper position
        if (y > 0) {
            clear[x][y - 1] = true;
        }

        // clear upper right position
        if (y > 0 && x < (length - 1)) {
            clear[x + 1][y - 1] = true;
        }

        // clear left position
        if (x > 0) {
            clear[x - 1][y] = true;
        }

        // clear right position
        if (x < (length - 1)) {
            clear[x + 1][y] = true;
        }

        // clear lower left position
        if (x > 0 && y < (width - 1)) {
            clear[x - 1][y + 1] = true;
        }   

        // clear lower position
        if (y < (width - 1)) {
            clear[x][y + 1] = true;
        }

        // clear lower right position
        if (x < (length - 1) && y < (width - 1)) {
            clear[x + 1][y + 1] = true;
        }
    }

}