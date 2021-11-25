public class Flags {

    private int numberOfFlags;
    private boolean[][] flagBoard;

    public Flags() {
        flagBoard = new boolean[10][10];
        numberOfFlags = 0;
    }

    public Flags(int width, int length) {
        flagBoard = new boolean[width][length];
        numberOfFlags = 0;
    }

    public void setDimensions(int width, int length) {
        flagBoard = new boolean[width][length];
    }

    // sets indicated flag location to true
    public void flagLocation(int x, int y) {
        flagBoard[x][y] = true;
        numberOfFlags++;
    }

    public void unflagLocation (int x, int y) {
        flagBoard[x][y] = false;
        numberOfFlags--;
    }

    // returns whether specified location is flagged or not
    public boolean getLocation (int x, int y) {
        return flagBoard[x][y];
    }

    public int totalFlags() {
        return numberOfFlags;
    }
}