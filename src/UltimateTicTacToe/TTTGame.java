package UltimateTicTacToe;

public class TTTGame {

    private AbstractPlayer[] players = new AbstractPlayer[2];   // testing two computer players

    // 10/28

    private Board[] allBoards = new Board[9];
    private int[] boardWinners = {-1,-1,-1,-1,-1,-1,-1,-1,-1};


    // initialize boards

    //private String[] marks = {"X", "O"};
    //private String name = "TicTacToe";

    private int gameRowSize = 3;
    private int gameColSize = 3;
    private int gameScoreToWin = 3;
    private int boardIndexRange = allBoards.length - 1;

    private int moveCounter = 0;

    private int currentPlayerIndex = -1;
    private int otherPlayerIndex = -1;


    // default game
    public TTTGame() {
        // setPlayers();
        System.out.println("\t*********************************");
        System.out.println("\t| Welcome to Ultimate TicTacToe |");
        System.out.println("\t*********************************\n");
        setBoard();
    }

    // setBoard function
    private void setBoard() {
        //this.board = new Board(gameRowSize, gameColSize, "TTTGame");
        for (int i = 0; i < allBoards.length; i++) {
            this.allBoards[i] = new Board(gameRowSize, gameColSize, "TTTGame", i, allBoards.length);
        }
    }


    // setPlayers will be able to make any combination of AbstractPlayer subclasses go against each other
    public void setPlayers(AbstractPlayer player1, AbstractPlayer player2) {
        players[0] = player1;
        players[1] = player2;
    }

    public void start() {
        System.out.println("Loading...");
        System.out.println("Boards and spots are displayed 0-8. Enjoy! \n\n");
        do {
            switchPlayer();
            // if getNextBoard() == -1 (no next board has been inputted) or is full, current player gets to choose nextboard
            if(players[this.otherPlayerIndex].getNextBoard() == -1) {
                players[this.currentPlayerIndex].setNextBoard(boardIndexRange);
                if(players[this.currentPlayerIndex] instanceof ComputerPlayer) {
                    System.out.println("Selected Board: " + players[this.currentPlayerIndex].getNextBoard());
                }
                allBoards[players[this.currentPlayerIndex].getNextBoard()].makeMove(players[this.currentPlayerIndex].getMark(),
                        players[this.currentPlayerIndex].selectNextSpot(boardIndexRange));
                        if(players[this.currentPlayerIndex] instanceof ComputerPlayer) {
                            System.out.println("Selected Spot: " + players[this.currentPlayerIndex].getNextSpot() + "\n");
                        }
                //players[this.currentPlayerIndex].setNextBoard(boardIndexRange);
            }
            else if(allBoards[players[this.otherPlayerIndex].getNextBoard()].isFull()) {
                System.out.println("Board #" + players[this.otherPlayerIndex].getNextBoard() + " is full. Current player may choose board");
                players[this.currentPlayerIndex].setNextBoard(boardIndexRange);
                if(players[this.currentPlayerIndex] instanceof ComputerPlayer) {
                    System.out.println("Selected Board: " + players[this.currentPlayerIndex].getNextBoard());
                }
                allBoards[players[this.currentPlayerIndex].getNextBoard()].makeMove(players[this.currentPlayerIndex].getMark(),
                        players[this.currentPlayerIndex].selectNextSpot(boardIndexRange));
                if(players[this.currentPlayerIndex] instanceof ComputerPlayer) {
                    System.out.println("Selected Spot: " + players[this.currentPlayerIndex].getNextSpot());
                }
            }
            else {

                System.out.println(players[this.otherPlayerIndex].displayCurrentBoard());

                // display all possible moves
                allBoards[players[this.otherPlayerIndex].getNextBoard()].possibleMoves();

                while(!allBoards[players[this.otherPlayerIndex].getNextBoard()].makeMove(players[this.currentPlayerIndex].getMark(),
                      players[this.currentPlayerIndex].selectNextSpot(boardIndexRange)));
                    // use this to show process of selecting spot for a ComputerPlayer
                    if(players[this.currentPlayerIndex] instanceof ComputerPlayer) {
                        System.out.println("Selected Spot: " + players[this.currentPlayerIndex].getNextSpot() + "\n");
                    }
                    //players[this.currentPlayerIndex].setNextBoard(boardIndexRange);

            }
            moveCounter++;
            printFullBoard();

        }while(!gameOver());
    }



    // use these functions to assist in printing full board
    private void printFullBoard() {
        printRowOfBoards(0);
        System.out.println("------+-------+--------");
        printRowOfBoards(3);
        System.out.println("------+-------+--------");
        printRowOfBoards(6);
        System.out.println("\n");

        // this was used to check contents of boardWinners array
//        for(int i = 0; i < boardWinners.length; i++) {
//            System.out.println("Board #" + i + ": " + boardWinners[i]);
//        }
    }

    // print each row side by side in order to display accurately
    private void printRowOfBoards(int boardIndex) {
        int row;

        for(row = 0; row < gameRowSize; row++) {
            allBoards[boardIndex].print(row);
            System.out.print("| ");
            allBoards[boardIndex + 1].print(row);
            System.out.print("| ");
            allBoards[boardIndex + 2].print(row);
            System.out.println();

        }

    }
    private void switchPlayer() {
        if(this.currentPlayerIndex == -1 || this.currentPlayerIndex == 1) {
            this.currentPlayerIndex = 0;
            System.out.println("Current Player/Mark: " + players[currentPlayerIndex].getName() + " | " + players[currentPlayerIndex].getMark());
            this.otherPlayerIndex = 1;
        }
        else {
            this.currentPlayerIndex = 1;
            System.out.println("Current Player/Mark: " + players[currentPlayerIndex].getName() + " | " + players[currentPlayerIndex].getMark());
            this.otherPlayerIndex = 0;
        }
    }


private boolean gameOver() {

        if(moveCounter >= 3) {
            displayAllWinners();
            if(checkWinner() == 1) {
                System.out.println(players[currentPlayerIndex].getName() + " is winner!\n\n");
                creditDisplay();
                //System.out.println("Total Moves: " + moveCounter);
                return true;
            }
            else if(checkWinner() == 2) {
                System.out.println("Game has ended in a tie\n\n");
                creditDisplay();
                return true;
            }
        }

    return false;
}

public void creditDisplay() {
        System.out.println("________________________________");
        System.out.println("|Game created by Reuben John \uD83D\uDE0E|");
        System.out.println("--------------------------------");
}

//use isWinner to declare winner of the board by inputting winner index in boardWinners array
//boardWinners : -1 = no winner yet on respective board,
//                0 = player index at 0 is winner on respective board,
//                1 = player index at 1 is winner on respective board,
//                2 = respective board is full
private void displayAllWinners() {
        // this will display winners/full of each board
        isWinner(); // first run isWinner to determine any winners or full boards for each board

        for(int i = 0; i < boardWinners.length; i++) {
            if(boardWinners[i] == 0) {
                System.out.println("Winner of Board #" + i + ": "+ players[0].getName());
            }
            else if (boardWinners[i] == 1) {
                System.out.println("Winner of Board #" + i + ": "+ players[1].getName());
            }
            else if (boardWinners[i] == 2) {
                System.out.println("Board #" + i + " is full");
            }
        }
        System.out.println();
}

private int checkWinner() {
        // this will be returning int because there are three total outcomes of the game: player winner, tie, game not finished
        // this is why checkWinner() does not return boolean

    // possible ways of winning ttt index(0-8)
    //  rows: 0,1,2; 3,4,5; 6,7,8
    //  cols: 0,3,6; 1,4,7; 2,5,8
    //  diagonals: 0,4,8; 2,4,6

    // first check will be for rows
    if(boardWinners[0] == boardWinners[1] && boardWinners[1] == boardWinners[2] && boardWinners[0] != -1 ||
       boardWinners[3] == boardWinners[4] && boardWinners[4] == boardWinners[5] && boardWinners[3] != -1 ||
       boardWinners[6] == boardWinners[7] && boardWinners[7] == boardWinners[8] && boardWinners[6] != -1) {
        return 1;
    } // second check will be for columns
    else if(boardWinners[0] == boardWinners[3] && boardWinners[3] == boardWinners[6] && boardWinners[0] != -1 ||
            boardWinners[1] == boardWinners[4] && boardWinners[4] == boardWinners[7] && boardWinners[1] != -1 ||
            boardWinners[2] == boardWinners[5] && boardWinners[5] == boardWinners[8] && boardWinners[2] != -1) {
        return 1;
    } // third check will be for diagonals
    else if(boardWinners[0] == boardWinners[4] && boardWinners[4] == boardWinners[8] && boardWinners[0] != -1 ||
            boardWinners[2] == boardWinners[4] && boardWinners[4] == boardWinners[6] && boardWinners[2] != -1) {
        return 1;
    } //final game outcome is tied, this algorithm checks to make sure all boards are won or tied, else return 0 to continue game
    else if(boardWinners[0] != -1 &&
            boardWinners[1] != -1 &&
            boardWinners[2] != -1 &&
            boardWinners[3] != -1 &&
            boardWinners[4] != -1 &&
            boardWinners[5] != -1 &&
            boardWinners[6] != -1 &&
            boardWinners[7] != -1 &&
            boardWinners[8] != -1) {
        return 2;
    }

    // return 0 if no winner yet
    return 0;
}

// create check for rows in big board
// create check for cols in big board
// create check for diagonals in big board

private void isWinner() {
        if(isWinnerOfBoard() && boardWinners[players[otherPlayerIndex].getNextBoard()] == -1) { // if there is a winner, assign currentPlayerIndex to board
            boardWinners[players[otherPlayerIndex].getNextBoard()] = currentPlayerIndex;
        }
        else if(allBoards[players[otherPlayerIndex].getNextBoard()].isFull() && boardWinners[players[otherPlayerIndex].getNextBoard()] == -1) { // if tied, assign 2 to board
            boardWinners[players[otherPlayerIndex].getNextBoard()] = 2;
        }

}

// check if there is a winner for a single board
private boolean isWinnerOfBoard() {
        if(checkRows()) {
            return true;
        }
        else if(checkCols()) {
            return true;
        }
        else if(checkDiagLR()){
            return true;
        }
        else if(checkDiagRL()){
            return true;
        }
        else return false;
    }

    private boolean checkCols() {
        for(int col = 0; col < this.gameColSize; col++) {
            if(checkCol(col)) return true;
        }
        return false;
    }

    private boolean checkCol(int col) {
        int count = 0;
        for(int row = 0; row < this.gameRowSize; row++) {
            if(allBoards[players[otherPlayerIndex].getNextBoard()].getMark(row,col).equals(players[currentPlayerIndex].getMark()))
                count++;
        }
        if(count == this.gameScoreToWin) return true;
        return false;
    }

    private boolean checkRows() {
        for(int row = 0; row < this.gameRowSize; row++) {
            if(checkRow(row)) return true;
        }
        return false;
    }

    private boolean checkRow(int row) {
        int count = 0;

            for(int col = 0; col < this.gameColSize; col++) {
                if(allBoards[players[otherPlayerIndex].getNextBoard()].getMark(row,col).equals(players[currentPlayerIndex].getMark()))
                    count++;
            }
        if(count == this.gameScoreToWin) return true;
        return false;
    }

    private boolean checkDiagLR() {
        int count = 0;
        for(int row = 0, col = this.gameRowSize - 1; row < this.gameColSize && col >= 0; row++, col--)
            if(allBoards[players[otherPlayerIndex].getNextBoard()].getMark(row,col).equals(players[currentPlayerIndex].getMark())) count++;
        if(count == this.gameScoreToWin) return true;
        return false;
    }

    private boolean checkDiagRL() {
        int count = 0;
        for(int col = 0, row = 0; col < this.gameColSize && row < this.gameRowSize; row++, col++)
            if(allBoards[players[otherPlayerIndex].getNextBoard()].getMark(row,col).equals(players[currentPlayerIndex].getMark())) count++;
        if(count == this.gameScoreToWin) return true;
        return false;
    }

}
