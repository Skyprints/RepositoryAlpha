import java.util.Scanner;

// Partners: Skyler Bowers and Juan Diaz

public class ConnectFour {
    public static void main(String[] args) {
        int boardHeight;
        int boardLength;
        int columnChoice;
        int chipPlace;
        boolean gameOn = true;
        Scanner scnr = new Scanner(System.in);
        // setting up initial variables and printing starting questions
        System.out.println("What would you like the height of the board to be?");
        boardHeight = scnr.nextInt();
        System.out.println("What would you like the length of the board to be?");
        boardLength = scnr.nextInt();
        char[][] gameBoard = new char[boardHeight][boardLength];
        initializeBoard(gameBoard);
        printBoard(gameBoard);
        System.out.println("\nPlayer 1: x\nPlayer 2: o");

        // runs through the game as a loop infintely until closing the program.
        while(gameOn){
            System.out.println("Player 1: Which column would you like to choose?");
            columnChoice = scnr.nextInt();
            chipPlace = insertChip(gameBoard, columnChoice, 'x');
            //gameBoard[chipPlace][columnChoice] = 'x';
            printBoard(gameBoard);
            if(checkIfWinner(gameBoard,chipPlace,columnChoice, 'x')){
                System.out.println("Player 1 won the game!");
                System.exit(0);
            }
            if (checkIfDraw(gameBoard)){
                System.out.println("Draw. Nobody wins.");
                System.exit(0);
            }

            System.out.println("Player 2: Which column would you like to choose?");
            columnChoice = scnr.nextInt();
            chipPlace = insertChip(gameBoard, columnChoice, 'o');
            printBoard(gameBoard);
            if(checkIfWinner(gameBoard,chipPlace,columnChoice, 'o')){
                System.out.println("Player 2 won the game!");
                System.exit(0);
            }
            if (checkIfDraw(gameBoard)){
                System.out.println("Draw. Nobody wins.");
                System.exit(0);
            }
        }
    }

        public static void printBoard(char[][] array){
            for (int i = array.length - 1 ; i >= 0 ; i--){
                for (int j = 0 ; j < array[i].length ; j++){
                    System.out.print(array[i][j] + " ");
                }
                System.out.println("");
            }
        }

    public static void initializeBoard(char[][] array){
        for (int i = 0 ; i < array.length ; i++){
            for (int j = 0 ; j < array[i].length ; j++){
                array[i][j] = '-';
            }
        }
    }

        public static int insertChip(char[][] array, int col, char chipType){
            int chipPlace = 0;
            for (int j = 0 ; j <= array.length - 1 ; j++){
                if (array[j][col] == '-'){
                    array[j][col] = chipType;
                    chipPlace = j;
                    break;
                }
            }
            return chipPlace;
        }
        
    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType){
        int winRowCounter = 0;
        int winColCounter = 0;

        //check columns for four in a row
        for (int i = 0 ; i <= array.length - 1 ; i++){
            winColCounter = 0;
            for ( int j = 0 ; j <= array[0].length - 1 ; j++){
                if (array[i][j] == chipType){
                    winColCounter += 1;
                    if (winColCounter >= 4){
                        return true;
                    }
                } else {
                    winColCounter = 0;
                }
            }
        }
        // check rows for four in a row
        for (int i = 0 ; i <= array[0].length - 1 ; i++){
            winRowCounter = 0;
            for ( int j = 0 ; j <= array.length - 1 ; j++){
                if (array[j][i] == chipType){
                    winRowCounter += 1;
                    if (winRowCounter >= 4){
                        return true;
                    }
                } else {
                    winRowCounter = 0;
                }
            }
        }
        return false;
    }

    public static boolean checkIfDraw(char[][] array){
        // Counts how many empty spaces there are, and returns true if there are none.
        int arraySize = array[0].length * array.length;
        int drawCounter = arraySize;
        for (int i = 0 ; i <= array.length - 1 ; i++){
            for ( int j = 0 ; j <= array[0].length - 1 ; j++){
                if (array[i][j] == '-'){
                    drawCounter -= 1;
                }
            }
        }
        if (drawCounter == arraySize) {
            return true;
        } else {
            return false;
        }
    }
}