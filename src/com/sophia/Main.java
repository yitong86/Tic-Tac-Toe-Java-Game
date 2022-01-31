package com.sophia;

import java.util.*;

public class Main {
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
//create gameboard
    public static void main(String[] args) {
	// write your code here 井字旗游戏
        //two array data type char called gameBoard,setup 3 rows 3 cloums
        char[][] gameBoard = {{' ', '|',' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};
        printGameBoard(gameBoard);

        while(true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your placement(1-9): ");
            int playerPos = scan.nextInt();
            while ((playerPositions.contains(playerPos) || cpuPositions.contains(playerPositions))){

            System.out.println("Position taken! enter a correct Position");
            playerPos = scan.nextInt();
        }
            placePiece(gameBoard, playerPos, "player");
            String result = checkWinner();
            if(result.length() > 0){
                System.out.println(result);
                break;
            }

            Random rand = new Random();
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
    //print out gameboard using for-each loop
    public  static  void  printGameBoard(char[][] gameBoard){
        //for (type var : array) for each array called row inside the gameboard
        for (char[] row : gameBoard){
            //for each char called inside the row
            for (char c :row) {
                System.out.print(c);
            }
            //after each row ,print line
            System.out.println();
        }

    }
    //create game loop.get the input from user using the scanner on the position 1-9
    // in the gameboard we store it .check who is winner
    //cpu we store it check it  who is winner
    public static void placePiece(char[][] gameBoard, int pos,String user){
        char symbol = ' ';
        if(user.equals("player")){
            symbol ='x';
            playerPositions.add(pos);
        }else if (user.equals("cpu")){
            symbol = '0';
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
    //check and replace
    public static  String checkWinner(){
        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,6);
        List botRow = Arrays.asList(7,8,9);
        List leftCol = Arrays.asList(1,4,7);
        List  midCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);
        List cross1= Arrays.asList(1,5,9);
        List cross2= Arrays.asList(7,5,3);

        List<List> winning = new ArrayList<>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for(List l : winning){
            if(playerPositions.containsAll(l)){
                return "Congratulations you won!";
            }else if (cpuPositions.containsAll(l)){
                return "CPU wins! sorry:)";
            }else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "CAT!";
            }
        }

        return " ";
    }
}
