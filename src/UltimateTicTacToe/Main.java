// Reuben John CS 2336.001, RXJ200024

/*  ~Analysis~
* Create an ultimate tic-tac-toe game using oop concepts
*   ~Design~
* This project was divided into 4 parts. The game, board/box, players and mark.
* TTTGame.java
*   Winning State: creates an array of 9 boards. Also creates an int array called boardWinners, this contents of boardWinners will be changed if a player wins at that
*   respective board. Ex: if player1 wins board0, boardWinner[0] = 0 (player1 index is 0 and player2 index is 1). The individual boards will be determined a winner if
*   isWinnerOfBoard() returns back true. This method will run all the possible ways of winning a game, if the board is won, then true will be returned to the isWinner()
*   which will assign the winner index to the respective board in the boardWinners array. If board is full and the boardWinner content is still -1, boardWinner[i] = 2 to indicate a full board with no winner.
*   Is winner() will be called in displayAllWinners() and display the boards that are not equal to -1. DisplayAllWinners() is called in gameOver(), which begins checking after moveCounter is more than 3, otherwise
*   there will be an out of bounds error. Also there cannot be a winner till many moves have been made. The gameOver() calls checkWinner(), which returns three values, 1 (winner), 2 (no winner) or 0 (no winner). Once gameOver()
*   returns true, the game will be over.
*
*   Display: Both the printFullBoard() and the printRowOfBoards(row) work together to print the boards. printRowOfBoards(row) is used to call the print method in the Board class, this will print each row of three boards before
*   moving to the next row. We use printFullBoard() to print the call the printRowOfBoards() to assist in printing the whole board
*
* Board.java/Box.java
*   Board Initialization: The board uses the box class to initialize the board. The box class consists of each individual box in the board. We also use the makeMove() to help make a move on the board. Using the parameters passed
*   in from the TTTGame class, we will initialize the board. The isFull() will check if the current board is full by using the box method isAvailable and looping through each element in the current board.
*
*   Possible moves (bonus + 5): After every move, the possibleMoves() will print a list of indices available to the player to place a marker. I used a range based for loop to go through each box and check if it is available.
*   used a counter (i) to display after each iteration if the spot was available.
*
* AbstractPlayer.java/ComputerPlayer.java/HumanPlayer.java
*   Both the ComputerPlayer and HumanPlayer class have the sane methods but different implementations. So I created an Abstract Player that allows for AI vs AI, AI vs Player and Player vs Player.
*
*   Selecting Spot and Selecting next board: In the computer class, any selection is done by a random number. The human class has two do-while loops, the inner to check for valid input, the outer to check whether it is inside range.
*
* Mark.java
*   The Mark class is only called when generating a random mark or entering a custom mark. This is commented out for testing purposes
 */


package UltimateTicTacToe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String player1Mark;
        String player2Mark;

//        player1Mark = setPlayerMark(1);
//        player2Mark = setPlayerMark(2);

        TTTGame game = new TTTGame();

        game.setPlayers(new HumanPlayer("Player 1", "X"), new ComputerPlayer("Player 2", "O"));
        game.start();
    }

        public static String setPlayerMark(int playerNumber) {
        Scanner input = new Scanner(System.in);
        Mark mark = new Mark();
        String temp;

        int markChoice = 0;
        System.out.println("Player " + playerNumber + ": Please enter a 1 or 2 for the following options\n");
        System.out.println("1: Enter custom mark");
        System.out.println("2: Get random mark");
        do {
            markChoice = input.nextInt();
        } while (markChoice < 0 && markChoice > 2);

        if(markChoice == 1) {
            System.out.print("Enter your mark: ");
            temp = input.next();
        } else {
            System.out.println("Generating random mark");
            temp = mark.getRandomMark();

        }
        return temp;
    }
}