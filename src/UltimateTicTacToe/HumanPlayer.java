package UltimateTicTacToe;

import java.util.Scanner;

public class HumanPlayer extends AbstractPlayer{
    //will need for user input
    Scanner input = new Scanner(System.in);


    //private int rowInput = -1;
    //private int colInput = -1;

    private int moveInput = -1;
    //private int boardRowSize = 3;

    private int nextBoard = -1;

    public HumanPlayer(String name, String mark) {
        super(name, mark);
    }
    @Override
    public int selectNextSpot(int range) {
        String inputStr; // will be used to check for valid input
        boolean valIsInt;
        do{ // first do-while will be used to repeat if entered value is not within range
            do{ // second do-while will be used to repeat if entered value is not a digit
                try{
                    System.out.print("Select Spot: ");
                    inputStr = input.next();
                    this.moveInput = Integer.parseInt(inputStr);
                    valIsInt = true;
                }
                catch(NumberFormatException e){
                    System.out.println("Invalid input. Try again");
                    valIsInt = false;
                }
            } while(!valIsInt);

            if(this.moveInput > 8) {
                System.out.println("Input value is too large. Try again");
            }
            else if(this.moveInput < 0) {
                System.out.println("Input value is too small. Try again");
            }

        } while(this.moveInput > range || this.moveInput < 0 || valIsInt == false);

        return this.moveInput;
    }

    public int getNextSpot() {
        return this.moveInput;
    }

    @Override
    public void setNextBoard(int range) {
        String inputStr; // will be used to check for valid input
        boolean valIsInt;
        do{ // first do-while will be used to repeat if entered value is not within range
            do{ // second do-while will be used to repeat if entered value is not a digit
                try{
                    System.out.print("Select Board (0-8): ");
                    inputStr = input.next();
                    this.moveInput = Integer.parseInt(inputStr);
                    valIsInt = true;
                }
                catch(NumberFormatException e){
                    System.out.println("Invalid must be digit. Try again");
                    valIsInt = false;
                }
            } while(!valIsInt);

            if(this.moveInput > 8) {
                System.out.println("Input value is too large. Try again");
            }
            else if(this.moveInput < 0) {
                System.out.println("Input value is too small. Try again");
            }

        } while(this.moveInput > range || this.moveInput < 0 || valIsInt == false);
    }

    @Override
    public int getNextBoard() { // this will return the nextboard as long as moveInput is not -1 (if moveInput = -1, it is the first move of the game)
        if(this.moveInput != -1) {
            this.nextBoard = this.moveInput;
        }
        return nextBoard;
    }

    @Override // display the current board
    public String displayCurrentBoard() {
        return "Current Board (set by " + this.getName() + "): " + this.nextBoard;
    }

}
