public class Clears {

    private boolean[][] clear;
    private int numberOfClears;
    private int length;
    private int width;

    public Clears() {
        clear = new boolean[10][10];
        numberOfClears = 0;
        length = 10;
        width = 10;
    }

    public Clears(int x, int y) {
        clear = new boolean[x][y];
        numberOfClears = 0;
        length = y;
        width = x;
    }

    public void setClear(int x, int y) {
        clear[x][y] = true;
        numberOfClears++;
    }

    public void setDimensions(int width, int length) {
        clear = new boolean[width][length];
    }

    public int getNumberOfClears() {
        return numberOfClears;
    }

    public boolean isClear(int x, int y) {
        if (clear[x][y]) {
            return true;
        }
        return false;
    }

    public void setAdjacentValuesFalse(int x, int y) {
        if (x > 0 && y > 0) {
            clear[x - 1][y - 1] = false;
        }

        // clear upper position
        if (y > 0) {
            clear[x][y - 1] = false;
        }

        // clear upper right position
        if (y > 0 && x < (length - 1)) {
            clear[x + 1][y - 1] = false;
        }

        // clear left position
        if (x > 0) {
            clear[x - 1][y] = false;
        }

        // clear right position
        if (x < (length - 1)) {
            clear[x + 1][y] = false;
        }

        // clear lower left position
        if (x > 0 && y < (width - 1)) {
            clear[x - 1][y + 1] = false;
        }   

        // clear lower position
        if (y < (width - 1)) {
            clear[x][y + 1] = false;
        }

        // clear lower right position
        if (x < (length - 1) && y < (width - 1)) {
            clear[x + 1][y + 1] = false;
        }
    }

    public void clearAdjacentValues(int x, int y) {
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