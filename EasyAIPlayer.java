import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

class EasyAIPlayer extends Player implements Global{

    public EasyAIPlayer(String name, int symbol, Board gameBoard, Image GSymbol){
        super(name,symbol,gameBoard,GSymbol);
    }

    @Override
    public void play(Board gameboard) {
        int counts = 0;
        int move = RandomLegal();

        for (int row=0;row<3;row++){
            for(int col=0;col<3;col++){
                counts += 1;
                if (move == counts){
                    gameboard.Gboard[row][col].setState(getSymbol());
                    gameboard.getGraphicBoard().getChildren().remove(gameboard.Gboard[row][col].getGraphicBlock());
                    gameboard.Gboard[row][col].setGraphicBlock(getGSymbol());
                    gameboard.getGraphicBoard().getChildren().add(gameboard.Gboard[row][col].getGraphicBlock());
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
}