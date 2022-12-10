package UltimateTicTacToe;

public class ComputerPlayer extends AbstractPlayer {
    private int nextBoard = -1;
    private int moveInput = -1;
    public ComputerPlayer(String name, String mark) {
        super(name, mark);
    }

    // changed to private because it is only called through member functions
    private int randomNumber(int range) {
        return (int) (Math.random() * range);
    }


    @Override // this will select a random number to range + 1 for the computer's next move
    public int selectNextSpot(int range) {
        this.moveInput = randomNumber(range + 1);
        return this.moveInput;
    }

    public int getNextSpot() {
        return this.moveInput;
    }

    // set value of nextBoard
    @Override // set next board using
    public void setNextBoard(int range) {
        this.moveInput = randomNumber(range + 1);
    }

    @Override
    public int getNextBoard() { // if moveInput is initialized by player, then set nextBoard = moveInput
        if(this.moveInput != -1) {
            //this.nextBoard = (this.rowInput * this.boardRowSize) + this.colInput;
            this.nextBoard = this.moveInput;
        }

        return nextBoard;
    }

    @Override   // display current board
    public String displayCurrentBoard() {
        return "Current Board (set by " + this.getName() + "): " + this.nextBoard;
    }


}
