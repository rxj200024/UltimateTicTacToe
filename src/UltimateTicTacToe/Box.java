package UltimateTicTacToe;

public class Box {

    private int row;
    private int col;
    private final static String DASH = "◦";
    private String placeHolder = Box.DASH;

    // amount of boxes will be determined by row and col amount
    Box(int row, int col) {
        this.row = row;
        this.col = col;
    }

    String getPlaceHolder() {
        return this.placeHolder;
    }

    boolean setPlaceHolder(String placeHolder) {
        if(isAvailable()) {
            this.placeHolder = placeHolder;
            return true;
        }
        else {
            return false;
        }
    }

    boolean isAvailable() {
        return this.placeHolder.equals(Box.DASH);   // Dash indicates open spot
    }

    void print() {
        System.out.print(placeHolder + " ");
    }
}
