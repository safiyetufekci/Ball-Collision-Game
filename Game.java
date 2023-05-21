public class Game {
    public static String[][] board(String boardFile){ // board txt to map
        String gameBoard[][] = FileReader.fileReader(boardFile);
        for(int i =0;i< gameBoard.length;i++){
            for(int j =0; j < gameBoard[i].length;j++){
                WriteOutput.write(gameBoard[i][j]+" "); // writing game board to output.txt
                System.out.print(gameBoard[i][j]+" ");
            }
            WriteOutput.write("\n");
            System.out.println();
        }
   return gameBoard; }

    public static String[][] initialBoard(String boardFile){ // board txt to map for the main
        String gameBoard[][] = FileReader.fileReader(boardFile);

        return gameBoard; }
    public static String[] commentLine(String commentFile){ // movement list for the main
        String[] comment= FileReader.fileReader(commentFile)[0]; //

    return comment;}
}
