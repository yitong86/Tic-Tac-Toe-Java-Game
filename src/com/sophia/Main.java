package com.sophia;

import java.util.*;

public class Main {
    //create array list called playerPositons store the user's input
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    //create array  list called cpupositions store the computer's input
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
    //create gameboard
    public static void main(String[] args) {
	    // 井字旗游戏
        // declare a nested array, data type char called gameBoard,setup 3 rows 3 column[[' ',"|" ,' ',"|" ,' '],[],[],[],[]]
        char[][] gameBoard = {{' ', '|',' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};
        //call the method called printGameBoard,passing the gameBoard
        printGameBoard(gameBoard);
        //looping through the code using while loop.continue to ask user
        while(true) {
            //get  input from user.
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your placement(1-9): ");
            //declare the variable called playerPos to get the position user just put
            int playerPos = scan.nextInt();

            //position condition,you can only choose the position which is you never choose or cpu never choose
            //check position the user just put equals the playerPosition or playerPosition equals cpuPosition
            while ((playerPositions.contains(playerPos) || cpuPositions.contains(playerPositions))){
            System.out.println("Position taken! enter a correct Position");
            //get input from user
            playerPos = scan.nextInt();
        }
            //passing the position we gave
            placePiece(gameBoard, playerPos, "player");
            //declare String variable called result and store checkWinner method,
            // if result.length() > 0, go to the checkWinner method to execute and stop running
            String result = checkWinner();
            if(result.length() > 0){
                System.out.println(result);
                break;
            }
            //generate the random number for cpu position
            // create random object
            Random rand = new Random();
            /// get a random int in the range 1..9
            int cpuPos = rand.nextInt(9) + 1;
            while ((playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos))) {
            cpuPos = rand.nextInt(9) + 1;
        }
            placePiece(gameBoard, cpuPos, "cpu");

            printGameBoard(gameBoard);

            result = checkWinner();
            if(result.length() > 0){
                System.out.println(result);
                break;
            }

        }
    }
    //print out game-board using for-each loop
    public  static  void  printGameBoard(char[][] gameBoard){
        //for (type var : array) for each loop called row
        //[[' ',"|" ,' ',"|" ,' '],[],[],[],[]],looping through arraylist
        for (char[] row : gameBoard){
            //for each loop to looping through sub-array
            for (char c :row) {
                System.out.print(c);
            }
            //after each row ,print line go to the second line.otherwise they go to one line
            System.out.println();
        }

    }
    //create game loop.get the input from user using the scanner on the position 1-9
    // in the gameboard we store it .check who is winner
    //cpu we store it check it  who is winner
    public static void placePiece(char[][] gameBoard, int pos,String user){
        //create variable called symbol to store whatever user or cpu input position
        char symbol = ' ';
        //take turn one by one
        //player turn
        if(user.equals("player")){
            //users=x
            symbol ='x';
            playerPositions.add(pos);
            //spu turn
        }else if (user.equals("cpu")){
            //cpu=O
            symbol = 'O';
            cpuPositions.add(pos);
        }
        switch (pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;

        }
    }
   //store winner position,
    //keep track where is cpu positions are and where users positions are
    ////checked and replace
    public static String checkWinner(){

        //ArrayList.add(1)
        // playerPositions.add(1);easy way to use Arrays.asList
        //List is data type
        //win condition
        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,6);
        List botRow = Arrays.asList(7,8,9);
        List leftCol = Arrays.asList(1,4,7);
        List  midCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);
        List cross1= Arrays.asList(1,5,9);
        List cross2= Arrays.asList(7,5,3);

        //declare arraylist data type is List named winning to store all the win condition
        List<List> winning = new ArrayList<>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        //for each List inside the winning list
        for(List l : winning){
            if(playerPositions.containsAll(l)){
                return "Congratulations you won!";
            }else if (cpuPositions.containsAll(l)){
                return "CPU wins! sorry:)";
                //if board is full
            }else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "CAT!";
            }
        }
        //""if board is empty
        return "";
    }
}
