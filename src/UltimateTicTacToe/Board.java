package UltimateTicTacToe;

public class Board {

    private Box[] boxes;
    private String name;
    private int boardRowSize;
    private int boardColSize;
    private int currentBoardIndex;
    private int maxBoardIndex;


    Board(int rowSize, int colSize, String name, int currentBoardIndex, int maxBoardIndex) {
        this.currentBoardIndex = currentBoardIndex;
        this.maxBoardIndex = maxBoardIndex;
        this.setName(name);
        this.setSize(rowSize, colSize);

    }

    // set name of board
    private void setName(String name) {
        this.name = name;
    }

    // set size of board
    private void setSize(int row, int col) {
        // check if minimum size of board is met
        if(row >= 3 && col >= 3) {
            this.boardRowSize = row;
            this.boardColSize = col;
            init();
        }
        else {
            System.out.println("The board is too small. Minimum size is 3x3");
        }
    }

    private void init() {
        boxes = new Box[boardRowSize * boardColSize];   // this will create row*col amount of boxes and store it in the boxes array
        for (int i = 0; i < boxes.length; i++) {
            Box b = new Box(i/boardColSize, 1%boardColSize);
            boxes[i] = b;
        }
        //print();
    }

    public void print(int row) {
            for(int j = 0; j < boxes.length/boardRowSize; j++) {
                boxes[(row * boardRowSize) + j].print();
            }
    }


    // will be used to make move on board
    public boolean makeMove(String mark, int move) {
        return boxes[move].setPlaceHolder(mark);

    }


    // isFull will check if the board is full
    public boolean isFull() {
        for(Box b : boxes) {
            if(b.isAvailable()) {
                return false;
            }
        }
        return true;
    }

    // possible moves for player
    public void possibleMoves() {
        System.out.print("All Legal Moves: ");
        int i = 0;
        for(Box b : boxes) {
            if(b.isAvailable()) {
                System.out.print(" " + i + " ");
            }
            i++;
        }
        System.out.println("\n");
    }

    public String getMark(int row, int col) {
        return boxes[row* this.boardRowSize + col].getPlaceHolder();
    }

}
