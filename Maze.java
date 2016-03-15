/* Dahv Reinhart
 * CSC115 Assignment 3: Maze.java
 * Recusively solves a maze by systematically trying different moves while testing if
 * a solution is possible with each successive move. Stores data in a RefBased linked list.
 * To be used with the MazeLocation package written by Prof. Mike Zastre.
 */
public class Maze {
    //global variables to be used to solve the maze.
    private String[] textmaze;
    private int startRow;
    private int startCol;
    private int finishRow;
    private int finishCol;
    private MazeLocationList path;
    private char[][] mazeArray;
    private MazeLocation currLoc;
    
    public Maze(String[] textmaze, int startRow, int startCol, int finishRow, int finishCol) {
        this.textmaze = textmaze;
        this.startRow = startRow;
        this.startCol = startCol;
        this.finishRow = finishRow;
        this.finishCol = finishCol;
        path = new MazeLocationListRefBased(); //the linkled list for the path data to be stored into.
        mazeArray = new char[textmaze.length][textmaze[0].length()]; //a char array to represent the maze.
        String readLine = "";
        for (int i = 0; i < mazeArray.length; i++) {
            for( int j = 0; j < mazeArray[0].length; j++) { //this populates the char array with chars according to
                readLine = textmaze[i];                     //what the textmaze that was passed in looks like.
                if (readLine.charAt(j) == '*') {
                    mazeArray[i][j] = '*';
                }
                else
                    mazeArray[i][j] = ' ';
            }   
        }
        mazeArray[finishRow][finishCol] = 'F'; //denotes the end of the maze as 'F': the Finish.
    }
    //this method calles the recursive method and returns an instance of a MazeLocationList populated with all
    //the nessessary moves to solve the maze.
    public MazeLocationList solve() {
        if (findPath(startRow , startCol , finishRow , finishCol)) {
            //maze was successfully solved.
            return path;
        }
        else {
            //maze not solved.
            return null;
        }
    }
    //this method is the recursive method that systematically tries each move (brute force method) to solve the maze.
    //it makes checks as to if any base cases are satisfyed prior to making each move. 
    private boolean findPath(int fromRow, int fromCol, int toRow, int toCol) {
        
        if (fromRow < 0 || fromRow > mazeArray.length - 1 || fromCol < 0 || fromCol > mazeArray[0].length - 1) {
            return false; //location is out of bounds!
        }
        
        if (mazeArray[fromRow][fromCol] == 'F') {
            currLoc = new MazeLocation(fromRow , fromCol);
            path.insertTail(currLoc);
            return true; //the end of the maze! Hooray!
        }
        
        if (mazeArray[fromRow][fromCol] == '*') {
            return false; //location is a wall!
        }
        
        if (mazeArray[fromRow][fromCol] == 'V') {
            return false; //location is marked with a 'V' because it has been previously Visited.
        }
        
        mazeArray[fromRow][fromCol] = 'V'; //marks the current location as Visited.
        currLoc = new MazeLocation(fromRow , fromCol);
        path.insertTail(currLoc); //inputs the location into our linked list.
        
        if (findPath(fromRow + 1 , fromCol , toRow , toCol)) {
            return true; //move down
        }
        
        
        else if (findPath(fromRow - 1 , fromCol , toRow , toCol)) {
            return true; //move up
        }
        
        
        else if (findPath(fromRow , fromCol + 1 , toRow , toCol)) {
            return true; //move right
        }
        
        
        else if (findPath(fromRow , fromCol - 1 , toRow , toCol)) {
            return true; //move left
        }
        
        mazeArray[fromRow][fromCol] = ' '; //if we get to here, it means that no moves were valid. we need to backtrack.
        path.removeTail();                 //thus, we remove the 'V' and remove the last entry in our linked list.
        return false;
    }
    //this method is only used for testing purposes. it prints a representation of the array to the console.
    public String toString() {
        //*********************print***************************
        for (int k = 0; k < mazeArray.length; k++) {
            for (int l = 0; l < mazeArray[0].length; l++) {
                System.out.print(mazeArray[k][l]);
            }
            System.out.println();
        }
        System.out.println("----------------------------------------");
        //*****************************************************
       return null;
    }
    //main method only used for testing purposes. for official tests, see MazeTester.java.
    public static void main(String[] args) {
        String[] easyMaze = {"* *",
                             "* *",
                             "* *",
                             "* *",
                             "* *",
                             "* *",
                             "* *",
                             "* *",
                             "* *",
                             "* *",
                             "* *",
                             "* *",
                             "* *"};
        String[] harderMaze =  {"* *******************",
                                "* *         *       *",
                                "* * ******* ***** * *",
                                "*   *     *     * * *",
                                "* *** *** ***** * * *",
                                "* *   *     *   * * *",
                                "* * *** *** * *** ***",
                                "* * * *   * * * *   *",
                                "* * * *** * * * * * *",
                                "* *   *   * *   * * *",
                                "* *** * *** ******* *",
                                "*   * * *         * *",
                                "*** *** ********* * *",
                                "* *   * *   *   * * *",
                                "* *** * * * * * * * *",
                                "*   * *   * * * *   *",
                                "* *** ***** *** *** *",
                                "*     *   *   *     *",
                                "* ***** * *** * *****",
                                "*       *     *     *",
                                "******************* *"};
        Maze myMaze = new Maze(harderMaze, 0, 1, 20, 19);
        //myMaze.toString();
        //myMaze.solve();
        //myMaze.toString();
    }
}//end of maze class