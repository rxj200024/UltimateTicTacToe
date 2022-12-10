package UltimateTicTacToe;

// this class will be used to create the ComputerPlayer and Player classes
public abstract class AbstractPlayer {

    private String name;
    private String mark;

    public AbstractPlayer(String name, String mark) {
        this.name = name;
        this.mark = mark;
    }

    // setter and getter

    public void setName(String name) {
        this.name = name;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getName() {
        return this.name;
    }

    public String getMark() {
        return this.mark;
    }




    public abstract int selectNextSpot(int range);
    public abstract void setNextBoard(int range);

    public abstract int getNextSpot();
    public abstract int getNextBoard();

    public abstract String displayCurrentBoard();

}
