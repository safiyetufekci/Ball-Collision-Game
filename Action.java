import java.util.ArrayList;


public class Action { // the most important class in this project whole game actions are here
    public ArrayList<Integer> points = new ArrayList<>(); // to calculate total score
    public Integer[] beginPoint(String[][] board){ // beginning coordinates of white ball
        Integer coordinates[] = new Integer[2]; // coordinates = (row,column)

        for(int i =0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++) {
                if(board[i][j].equals("*")){ // finding white ball
                    coordinates[0]=i; // row
                    coordinates[1]=j; // column

                }
            }

        }
       return coordinates;
    }
    public void switches(String[][] board , int firstRow, int secondRow, int firstColumn, int secondColumn ){ // to switch elements , a basic code
        String tmp="";
        tmp = board[firstRow][firstColumn];
        board[firstRow][firstColumn] = board[secondRow][secondColumn];
        board[secondRow][secondColumn] = tmp;
        }
     // most important method in this project
    // there is some printing outputs instead of a lots of return method
    public void remove(String[] comment,String[][] board,String fileName){
        Action r = new Action();
        int totalPoint = 0; // total point
        Integer coordinates[] = r.beginPoint(board); // beginner coordinate points (i,j)

        int row=coordinates[0]; // beginner row
        int column=coordinates[1]; // beginner column
        int longEdge = board.length; //satır sayısı
        int shortEdge =board[0].length; // sütun sayısı


        WriteOutput.write("Game board:\n"); //  before everything print the original board
        Game.board(fileName);
        WriteOutput.write("\n");

        //Main part of the project
        WriteOutput.write("Your movement is:\n");
        for (int k = 0; k < comment.length; k++) { // now, quick trip on the movement list
            String element = comment[k];
            WriteOutput.write(comment[k] +" "); // to print movements

            if (element.equals("L")) { //left
                if (r.nextElement(board, row, (column + shortEdge - 1) % shortEdge)) { // important to is next element HOLE
                    return; // and because of  game over, loop should be end.
                }

                if (board[row][(column + shortEdge - 1) % shortEdge].equals("W")) { // important to next element is WALL
                    if (r.nextElement(board, row, (column + 1) % shortEdge)) //after wall move we should check again is there any hole
                    {return; // and because of  game over, loop should be end.
                    }
                    else{ // there is no hole but there is wall
                        r.switches(board, row, row, column, (column + 1) % shortEdge); // so switch upto "Right"
                        column = (column + 1) % shortEdge;
                    }


                } else { // there is no wall
                    r.switches(board, row, row, column, (column + shortEdge - 1) % shortEdge); // so just switch
                    column = (column + shortEdge - 1) % shortEdge;
                }

            } else if (element.equals("R")) { //right
                if (r.nextElement(board, row, (column + 1) % shortEdge)) { // important to is next element HOLE
                    return; // and because of  game over, loop should be end.
                }

                if (board[row][(column + 1) % shortEdge].equals("W")) { // is next element WALL
                    if (r.nextElement(board, row, (column + 1) % shortEdge)) // important to is next element HOLE after wall movement
                    {   return; // and because of  game over, loop should be end.
                    }
                    r.switches(board, row, row, column, (column + shortEdge - 1) % shortEdge); //there is no hole but there is wall so switch up to "LEFT"
                    column = (column + shortEdge - 1) % shortEdge;

                } else { // there is no wall
                    r.switches(board, row, row, column, (column + 1) % shortEdge); // so just switch
                    column = (column + 1) % shortEdge;
                }


            } else if (element.equals("U")) { //up
                if (r.nextElement(board, (row + longEdge - 1) % longEdge, column)) { // important to is next element HOLE
                    return; // and because of  game over, loop should be end.
                }
                if (board[(row + longEdge - 1) % longEdge][column].equals("W")) { // is next element WALL
                    if (r.nextElement(board, row, (column + 1) % shortEdge)) { // important to is next element HOLE after WALL movement
                        return; // and because of  game over, loop should be end.
                    }

                    r.switches(board, row, (row + 1) % longEdge, column, column); // there is no Hole but there is a wall so switch up to "Down"
                    row = (row + 1) % longEdge;

                }
                else { // there is no WALL
                    r.switches(board, row, (row + longEdge - 1) % longEdge, column, column); // so just switch
                    row = (row + longEdge - 1) % longEdge;
                }

            } else if (element.equals("D")) { //down
                if (r.nextElement(board, (row + 1) % longEdge, column)) { // // important to is next element HOLE
                    return; // and because of  game over, loop should be end.
                }
                if (board[(row + 1) % longEdge][column].equals("W")) { // is next element WALL
                    if (r.nextElement(board, row, (column + 1) % shortEdge)) { // important to is next element HOLE after wall movement
                        return; // and because of  game over, loop should be end.
                    }

                    r.switches(board, row, (row + longEdge - 1) % longEdge, column, column); // there is wall so switch up to "UP"
                    row = (row + longEdge - 1) % longEdge;

                } else { // there is no wall
                    r.switches(board, row, (row + 1) % longEdge, column, column); // so just  switch elements
                    row = (row + 1) % longEdge;
                }

            }

        }

        //The below part is for condition which ball doesnot drop the hole

        WriteOutput.write("\n"); // to be better output.txt
        WriteOutput.write("\n");

        WriteOutput.write("Your output is:\n");// to print last shape of the board
        for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[i].length; j++) {
            WriteOutput.write(board[i][j]+" ");
        }
        WriteOutput.write("\n");
    }
        WriteOutput.write("\n");


        for(int i =0;i<r.points.size();i++){ // calculate total score
            totalPoint+=r.points.get(i);
        }

        WriteOutput.write("Score: "+ totalPoint); // print total score

    }
    // in this game the movements of the ball change up to next element, so we need to check next element;
    private boolean nextElement(String[][] board, int row,int column){ // this method also checks "HOLE", if there is a hole, it returns true
        String element= board[row][column];
        Action r = new Action();

    // we have special characters "R", "Y" "B" and "H" we especially  have to check them, and also calculate their points
        if( element.equals("R")){
            board[row][column]="X"; // change "R" to X
            points.add(10); // calculate its score

        } else if (element.equals("Y")) {
            board[row][column]="X"; //change "Y" to X
            points.add(5); // calculate its score

        } else if (element.equals("B")) {
            board[row][column]="X"; //change "B" to X
            points.add(-5); // calculate its score

        }

        else if(element.equals("H")){ // if there is a hole the game must be stopped
            r.hole(board); // to show the ball dropped the hole
            int totalPoint=0; // initalizing the total point
            for(int i =0;i<points.size();i++){ // to calculate the total point
                totalPoint+=points.get(i);
            }


            WriteOutput.write("\n"); // to be better output.txt
            WriteOutput.write("\n");
            WriteOutput.write("Your output is:\n");// to print last shape of the board


            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    WriteOutput.write(board[i][j]+" "); //to print last shape of the board after hole
                }
                WriteOutput.write("\n");}
            WriteOutput.write("\n");

            WriteOutput.write("Game Over!\n"); // because of Hole now game is over so just print it
            WriteOutput.write("Score: "+ totalPoint); // after game over, print total point

            return true; // because ball dropped the hole, this method returns true, and the loop must stop
        }
    return false;}

    public void hole(String[][] board) { // a small method for replacing the ball character "*" to space character " " showing ball dropped the hole.
        for(int i = 0;i< board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(board[i][j].equals("*")){
                    board[i][j]=" ";
                }
            }
        }
    }



}
