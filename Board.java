
import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.Arrays;


class Board implements Global{
    Block[][] Gboard = new Block[3][3];
    private int Gstate;
    private GridPane GraphicBoard;

    public Board(){
        Gstate = EMPTY;
        for (int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                Gboard[i][j] = new Block();
            }
        }
        GraphicBoard = new GridPane();
    }

    /** This method ensures that players can only makes move if the selected block is EMPTY
     * @param x row value
     * @param y column value
     * @return true or false if that position has been played on
     */

    public boolean makeMove(int x, int y){
        if (Gboard[x][y].getState() == EMPTY){
            return true;
        }
        else { return false; }
    }

    /** This method is for updating the state of board
     */
    public void updateState(){
        for(int j=0;j<3;j++){
            if (ColumnCheck(j) != EMPTY){
                Gstate = ColumnCheck(j); return;}
            else if (RowCheck(j) != EMPTY){
                Gstate = RowCheck(j); return;}
        }
        if (DiagonalCheck() != EMPTY){
            Gstate = DiagonalCheck(); }
        else if (DrawCheck() != EMPTY){
            Gstate = DrawCheck(); }
    }

    public int getGstate() {
        return Gstate;
    }

//    public void displayBoard(){
//        for(int i=0; i<3;i++) {
//            System.out.println(Arrays.toString(Gboard[i]));
//        }
//    }

    public GridPane getGraphicBoard() {
        return GraphicBoard;
    }

    //--------------------------------------------------------------------------------
    //------- This is where we display the board --------//
    // We will be attempting to display a GridPane in a GridPane


    public void displayGraphicBoard(){
        GraphicBoard.setMinSize(300,300);
        ColumnConstraints col1 = new ColumnConstraints(100);
        ColumnConstraints col2 = new ColumnConstraints(100);
        ColumnConstraints col3 = new ColumnConstraints(100);
        GraphicBoard.getColumnConstraints().addAll(col1,col2,col3);
        RowConstraints row1 = new RowConstraints(100);
        RowConstraints row2 = new RowConstraints(100);
        RowConstraints row3 = new RowConstraints(100);
        GraphicBoard.getRowConstraints().addAll(row1,row2,row3);

        for (int row=0;row<3;row++){
            for(int col=0;col<3;col++){
                // The parameters below are (Node, ColumnIndex, RowIndex)
                GridPane.setColumnIndex(Gboard[row][col].getGraphicBlock(),col);
                GridPane.setRowIndex(Gboard[row][col].getGraphicBlock(),row);
                GraphicBoard.getChildren().add(Gboard[row][col].getGraphicBlock());
                GraphicBoard.setAlignment(Pos.CENTER);
            }
        }
        GraphicBoard.setGridLinesVisible(true);
        //return GraphicBoard;
    }



    //--------------------------------------------------------------------------------
    /**Helper method to check columns
     */
    private int ColumnCheck(int col){
        int sum = 0;
        int countO = 0;
        for (int i=0; i<3;i++) {
            sum += Gboard[i][col].getState();
            if (Gboard[i][col].getState() == O){
                countO += 1; }
        }
        if (sum == countO && countO == 3){
            return O; }
        else if (sum == 6){
            return X; }
        return EMPTY;
    }

    /**Helper method to check rows
     */
    private int RowCheck(int row){
        int sum = 0;
        int countO = 0;
        for (int i=0; i<3;i++) {
            sum += Gboard[row][i].getState();
            if (Gboard[row][i].getState() == O){
                countO += 1; }
        }
        if (sum == countO && countO == 3){
            return O; }
        else if (sum == 6){
            return X; }
        return EMPTY;
    }

    /**Helper method to check diagonal lines
     */
    private int DiagonalCheck(){
        int sum1 = 0; int sum2 = 0;
        int countO1 = 0; int countO2 = 0;
        for (int i=0; i<3;i++) {
            sum1 += Gboard[i][i].getState();
            if (Gboard[i][i].getState() == O){
                countO1 += 1; }
        }
        if (sum1 == countO1 && countO1 == 3){
            return O; }
        else if (sum1 == 6){
            return X; }

        for (int j=0; j<3;j++) {
            sum2 += Gboard[j][2-j].getState();
            if (Gboard[j][2-j].getState() == O){
                countO2 += 1; }
        }
        if (sum2 == countO2 && countO2 == 3){
            return O; }
        else if (sum2 == 6){
            return X; }
        return EMPTY;
    }

    /**Helper method to check diagonal lines
     */
    private int DrawCheck() {
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Gboard[i][j].getState() != 0) { counter += 1; }
            }
        }
        if (counter == 9){ return DRAW; }
        else { return EMPTY; }
    }

}
