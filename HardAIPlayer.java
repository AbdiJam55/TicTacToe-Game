import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

class HardAIPlayer extends Player implements Global {
    boolean winner;

    public HardAIPlayer(String name, int symbol, Board gameBoard, Image GSymbol){
        super(name,symbol,gameBoard,GSymbol);
    }

    @Override
    public void play(Board gameboard) {
        winner = false;
        boolean played = false;
        int counts = 0;
        for (int k = 0; k < 3; k++) {
            if (checkRowSymbol(k) != EMPTY) {
                // System.out.println("Row issue");
                senseWin();
                if (!winner) {
                    senseLoss();
                }
                played = true;
                break;
            }
            else if (checkColumnSymbol(k) != EMPTY) {
                // System.out.println("Column issue");
                senseWin();
                if (!winner) {
                    senseLoss();
                }
                played = true;
                break;
            }
        }
        if(!played) {
            if (checkDiagonalLeftToRight() != EMPTY) {
                //  System.out.println("Diagonal Left to Right");
                senseWin();
                if (!winner) {
                    senseLoss();
                }
                played = true;
            } else if (checkDiagonalRightToLeft() != EMPTY) {
                //System.out.println("Diagonal Right to Left");
                senseWin();
                if (!winner) {
                    senseLoss();
                }
                played = true;
            }
        }

        if (!played) {
            int move = RandomLegal();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    counts += 1;
                    if (move == counts) {
                        gameboard.Gboard[i][j].setState(getSymbol());
                        gameboard.getGraphicBoard().getChildren().remove(gameboard.Gboard[i][j].getGraphicBlock());
                        gameboard.Gboard[i][j].setGraphicBlock(getGSymbol());
                        gameboard.getGraphicBoard().getChildren().add(gameboard.Gboard[i][j].getGraphicBlock());
                    }
                }
            }
        }
    }

    private int RandomLegal(){
        int countpositions = 0;
        int counter = 0;
        ArrayList<Integer> OpenSpots = new ArrayList<Integer>();
        Random r = new Random();
        // This is to take note of the blocks that are available
        for (int i=0; i<3;i++){
            for(int j=0;j<3;j++){
                counter += 1;
                if (getGameBoard().makeMove(i,j)) {
                    countpositions += 1;
                    OpenSpots.add(counter);
                }
            }
        }
        // This is to make a move on the selected block from among the available blocks
        if (!(countpositions == 0)) {
            int rand = r.nextInt(countpositions); // will be used for random index position of ArrayList
            return OpenSpots.get(rand);
        }
        else {
            return 0;
        }
    }

    private void senseLoss() {
        boolean played = false;
        if (getSymbol() == O) {  // Enemy would be the opposite of O (AI Symbol) so it would be X
            for (int i = 0; i < 3; i++) {
                if (checkRowSymbol(i) == 4) {
                    for (int j = 0; j < 3; j++) {
                        if (getGameBoard().makeMove(i, j)) {
                            getGameBoard().Gboard[i][j].setState(getSymbol());
                            getGameBoard().getGraphicBoard().getChildren().remove(getGameBoard().Gboard[i][j].getGraphicBlock());
                            getGameBoard().Gboard[i][j].setGraphicBlock(getGSymbol());
                            getGameBoard().getGraphicBoard().getChildren().add(getGameBoard().Gboard[i][j].getGraphicBlock());
                            played = true;
                        }
                    }
                }
                else if (checkColumnSymbol(i) == 4 && !played) {
                    for (int j = 0; j < 3; j++) {
                        if (getGameBoard().makeMove(j, i)) {
                            getGameBoard().Gboard[j][i].setState(getSymbol());
                            getGameBoard().getGraphicBoard().getChildren().remove(getGameBoard().Gboard[j][i].getGraphicBlock());
                            getGameBoard().Gboard[j][i].setGraphicBlock(getGSymbol());
                            getGameBoard().getGraphicBoard().getChildren().add(getGameBoard().Gboard[j][i].getGraphicBlock());
                            played = true;
                        }
                    }
                }
            }
            if (!played) {
                if (checkDiagonalLeftToRight() == 4) {
                    for (int j = 0; j < 3; j++) {
                        if (getGameBoard().makeMove(j, j)) {
                            getGameBoard().Gboard[j][j].setState(getSymbol());
                            getGameBoard().getGraphicBoard().getChildren().remove(getGameBoard().Gboard[j][j].getGraphicBlock());
                            getGameBoard().Gboard[j][j].setGraphicBlock(getGSymbol());
                            getGameBoard().getGraphicBoard().getChildren().add(getGameBoard().Gboard[j][j].getGraphicBlock());
                        }
                    }
                }
                else if (checkDiagonalRightToLeft() == 4) {
                    for (int j = 0; j < 3; j++) {
                        if (getGameBoard().makeMove(j, 2 - j)) {
                            getGameBoard().Gboard[j][2 - j].setState(getSymbol());
                            getGameBoard().getGraphicBoard().getChildren().remove(getGameBoard().Gboard[j][2-j].getGraphicBlock());
                            getGameBoard().Gboard[j][2-j].setGraphicBlock(getGSymbol());
                            getGameBoard().getGraphicBoard().getChildren().add(getGameBoard().Gboard[j][2-j].getGraphicBlock());
                        }
                    }
                }
            }
        }
        else if (getSymbol() == X) { // Enemy would be the opposite of X (AI Symbol) so it would be O
            for (int i = 0; i < 3; i++) {
                if (checkRowSymbol(i) == 2) {
                    for (int j = 0; j < 3; j++) {
                        if (getGameBoard().makeMove(i, j)) {
                            getGameBoard().Gboard[i][j].setState(getSymbol());
                            getGameBoard().getGraphicBoard().getChildren().remove(getGameBoard().Gboard[i][j].getGraphicBlock());
                            getGameBoard().Gboard[i][j].setGraphicBlock(getGSymbol());
                            getGameBoard().getGraphicBoard().getChildren().add(getGameBoard().Gboard[i][j].getGraphicBlock());
                            played = true;
                        }
                    }
                }
                else if (checkColumnSymbol(i) == 2 && !played) {
                    for (int j = 0; j < 3; j++) {
                        if (getGameBoard().makeMove(j, i)) {
                            getGameBoard().Gboard[j][i].setState(getSymbol());
                            getGameBoard().getGraphicBoard().getChildren().remove(getGameBoard().Gboard[j][i].getGraphicBlock());
                            getGameBoard().Gboard[j][i].setGraphicBlock(getGSymbol());
                            getGameBoard().getGraphicBoard().getChildren().add(getGameBoard().Gboard[j][i].getGraphicBlock());
                            played = true;
                        }
                    }
                }
            }
            if (!played) {
                if (checkDiagonalLeftToRight() == 2) {
                    for (int j = 0; j < 3; j++) {
                        if (getGameBoard().makeMove(j, j)) {
                            getGameBoard().Gboard[j][j].setState(getSymbol());
                            getGameBoard().getGraphicBoard().getChildren().remove(getGameBoard().Gboard[j][j].getGraphicBlock());
                            getGameBoard().Gboard[j][j].setGraphicBlock(getGSymbol());
                            getGameBoard().getGraphicBoard().getChildren().add(getGameBoard().Gboard[j][j].getGraphicBlock());
                        }
                    }
                }
                else if (checkDiagonalRightToLeft() == 2) {
                    for (int j = 0; j < 3; j++) {
                        if (getGameBoard().makeMove(j, 2 - j)) {
                            getGameBoard().Gboard[j][2 - j].setState(getSymbol());
                            getGameBoard().getGraphicBoard().getChildren().remove(getGameBoard().Gboard[j][2-j].getGraphicBlock());
                            getGameBoard().Gboard[j][2-j].setGraphicBlock(getGSymbol());
                            getGameBoard().getGraphicBoard().getChildren().add(getGameBoard().Gboard[j][2-j].getGraphicBlock());
                        }
                    }
                }
            }
        }
    }

    private void senseWin(){
        boolean played = false;
        if (getSymbol() == O){  // Enemy would be the opposite of O (AI Symbol) so it would be X
            for (int i=0;i<3;i++){
                if (checkRowSymbol(i) == 2){
                    for (int j=0;j<3;j++){
                        if (getGameBoard().makeMove(i,j)){
                            getGameBoard().Gboard[i][j].setState(getSymbol());
                            getGameBoard().getGraphicBoard().getChildren().remove(getGameBoard().Gboard[i][j].getGraphicBlock());
                            getGameBoard().Gboard[i][j].setGraphicBlock(getGSymbol());
                            getGameBoard().getGraphicBoard().getChildren().add(getGameBoard().Gboard[i][j].getGraphicBlock());
                            winner = true; played = true;
                        }
                    }
                }
                else if (checkColumnSymbol(i) == 2 && !played){
                    for (int j=0;j<3;j++){
                        if (getGameBoard().makeMove(j,i)){
                            getGameBoard().Gboard[j][i].setState(getSymbol());
                            getGameBoard().getGraphicBoard().getChildren().remove(getGameBoard().Gboard[j][i].getGraphicBlock());
                            getGameBoard().Gboard[j][i].setGraphicBlock(getGSymbol());
                            getGameBoard().getGraphicBoard().getChildren().add(getGameBoard().Gboard[j][i].getGraphicBlock());
                            winner = true; played = true;
                        }
                    }
                }
            }
            if(!played) {
                if (checkDiagonalLeftToRight() == 2) {
                    for (int j = 0; j < 3; j++) {
                        if (getGameBoard().makeMove(j, j)) {
                            getGameBoard().Gboard[j][j].setState(getSymbol());
                            getGameBoard().getGraphicBoard().getChildren().remove(getGameBoard().Gboard[j][j].getGraphicBlock());
                            getGameBoard().Gboard[j][j].setGraphicBlock(getGSymbol());
                            getGameBoard().getGraphicBoard().getChildren().add(getGameBoard().Gboard[j][j].getGraphicBlock());
                            winner = true;
                        }
                    }
                }
                else if (checkDiagonalRightToLeft() == 2) {
                    for (int j = 0; j < 3; j++) {
                        if (getGameBoard().makeMove(j, 2 - j)) {
                            getGameBoard().Gboard[j][2 - j].setState(getSymbol());
                            getGameBoard().getGraphicBoard().getChildren().remove(getGameBoard().Gboard[j][2-j].getGraphicBlock());
                            getGameBoard().Gboard[j][2-j].setGraphicBlock(getGSymbol());
                            getGameBoard().getGraphicBoard().getChildren().add(getGameBoard().Gboard[j][2-j].getGraphicBlock());
                            winner = true;
                        }
                    }
                }
            }
        }
        else if (getSymbol() == X) { // Enemy would be the opposite of X (AI Symbol) so it would be O
            for (int i = 0; i < 3; i++) {
                if (checkRowSymbol(i) == 4) {
                    for (int j = 0; j < 3; j++) {
                        if (getGameBoard().makeMove(i, j)) {
                            getGameBoard().Gboard[i][j].setState(getSymbol());
                            getGameBoard().getGraphicBoard().getChildren().remove(getGameBoard().Gboard[i][j].getGraphicBlock());
                            getGameBoard().Gboard[i][j].setGraphicBlock(getGSymbol());
                            getGameBoard().getGraphicBoard().getChildren().add(getGameBoard().Gboard[i][j].getGraphicBlock());
                            winner = true;
                            played = true;
                        }
                    }
                }
                else if (checkColumnSymbol(i) == 4 && !played) {
                    for (int j = 0; j < 3; j++) {
                        if (getGameBoard().makeMove(j, i)) {
                            getGameBoard().Gboard[j][i].setState(getSymbol());
                            getGameBoard().getGraphicBoard().getChildren().remove(getGameBoard().Gboard[j][i].getGraphicBlock());
                            getGameBoard().Gboard[j][i].setGraphicBlock(getGSymbol());
                            getGameBoard().getGraphicBoard().getChildren().add(getGameBoard().Gboard[j][i].getGraphicBlock());
                            winner = true;
                            played = true;
                        }
                    }
                }
            }
            if (!played) {
                if (checkDiagonalLeftToRight() == 4) {
                    for (int j = 0; j < 3; j++) {
                        if (getGameBoard().makeMove(j, j)) {
                            getGameBoard().Gboard[j][j].setState(getSymbol());
                            getGameBoard().getGraphicBoard().getChildren().remove(getGameBoard().Gboard[j][j].getGraphicBlock());
                            getGameBoard().Gboard[j][j].setGraphicBlock(getGSymbol());
                            getGameBoard().getGraphicBoard().getChildren().add(getGameBoard().Gboard[j][j].getGraphicBlock());
                            winner = true;
                        }
                    }
                } else if (checkDiagonalRightToLeft() == 4) {
                    for (int j = 0; j < 3; j++) {
                        if (getGameBoard().makeMove(j, 2 - j)) {
                            getGameBoard().Gboard[j][2 - j].setState(getSymbol());
                            getGameBoard().getGraphicBoard().getChildren().remove(getGameBoard().Gboard[j][2-j].getGraphicBlock());
                            getGameBoard().Gboard[j][2-j].setGraphicBlock(getGSymbol());
                            getGameBoard().getGraphicBoard().getChildren().add(getGameBoard().Gboard[j][2-j].getGraphicBlock());
                            winner = true;
                        }
                    }
                }
            }
        }
    }


    private int checkRowSymbol(int row){
        int sum = 0;
        int countO = 0; int countX = 0;
        for (int i=0;i<3;i++){
            sum += getGameBoard().Gboard[row][i].getState();
            if (getGameBoard().Gboard[row][i].getState() == O){
                countO += 1;
            }
            else if (getGameBoard().Gboard[row][i].getState() == X){
                countX += 2;
            }
        }
        if (sum == countO && countO == 2){
            return 2;
        }
        else if (sum == countX && countX == 4){
            return 4;
        }
        return EMPTY;
    }

    private int checkColumnSymbol(int col){
        int sum = 0;
        int countO = 0; int countX = 0;
        for (int i=0;i<3;i++){
            sum += getGameBoard().Gboard[i][col].getState();
            if (getGameBoard().Gboard[i][col].getState() == O){
                countO += 1;
            }
            else if (getGameBoard().Gboard[i][col].getState() == X){
                countX += 2;
            }
        }
        if (sum == countO && countO == 2){
            return 2;
        }
        else if (sum == countX && countX == 4){
            return 4;
        }
        return EMPTY;
    }

    private int checkDiagonalLeftToRight() {
        int sum1 = 0;
        int countO1 = 0;
        int countX1 = 0;
        // Diagonal from Left to Right
        for (int i = 0; i < 3; i++) {
            sum1 += getGameBoard().Gboard[i][i].getState();
            if (getGameBoard().Gboard[i][i].getState() == O) {
                countO1 += 1;
            } else if (getGameBoard().Gboard[i][i].getState() == X) {
                countX1 += 2;
            }
        }
        if (sum1 == countO1 && countO1 == 2) {
            return 2;
        } else if (sum1 == countX1 && countX1 == 4) {
            return 4;
        }
        return EMPTY;
    }

    private int checkDiagonalRightToLeft(){
        int sum2 = 0;
        int countO2 = 0;
        int countX2 = 0;
        // Diagonal from Right to Left
        for(int i=0;i<3;i++){
            sum2 += getGameBoard().Gboard[i][2-i].getState();
            if (getGameBoard().Gboard[i][2-i].getState() == O){
                countO2 += 1;
            }
            else if (getGameBoard().Gboard[i][2-i].getState() == X){
                countX2 += 2;
            }
        }
        if (sum2 == countO2 && countO2 == 2){
            return 2;
        }
        else if (sum2 == countX2 && countX2 == 4){
            return 4;
        }
        return EMPTY;
    }


}