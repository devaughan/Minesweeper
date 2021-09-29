public class Board 
{
    private int length;
    private int width;
    private int area;
    private int[][] mine;
    private int mines;

    // constructors
    public Board() 
    {
        length = 10;
        width = 10;
        area = length * width;
        mines = Math.round(area / 5);
        mine = new int[length][width];
    }

    public Board(int l, int w, int m) 
    {
        length = l;
        width = w;
        mines = m;
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

    // get methods
    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getMines() {
        return mines;
    }

    // initialize array values
    public void createBoard() {
        int[][] mine = new int[length][width];
        for (int i = 0; i < length; i++) 
        {
            for (int j = 0; j < width; j++) 
            {
                mine[i][j] = 0;
            }
        }
    }

    public void createMines() {
        for(int i = mines; i > 0; i--) 
        {
            int x = (int)(Math.random() * length);
            int y = (int)(Math.random() * width);
            if (mine[x][y] == -1) {
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

}