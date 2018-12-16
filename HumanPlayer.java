
import javafx.scene.image.Image;

/** Human Player needs to be reconstructed from the Ground up so that it will take a mouse click and
    then within that mouse click the AI will invoke its play(...) method! TOTALLY WILL NOT WORK WITH THIS BUILD*/
// The handle method will likely need to be implemented in the Game Class //


class HumanPlayer extends Player implements Global {
    private int row;
    private int column;

    //Overloaded constructor
    public HumanPlayer(String name, int symbol, Board gameBoard, Image GSymbol) {
        super(name, symbol, gameBoard, GSymbol);
    }

    @Override
    public void play(Board gameboard) {
        gameboard.Gboard[row][column].setState(getSymbol());
        gameboard.getGraphicBoard().getChildren().remove(gameboard.Gboard[row][column].getGraphicBlock());
        gameboard.Gboard[row][column].setGraphicBlock(getGSymbol());
        gameboard.getGraphicBoard().getChildren().add(gameboard.Gboard[row][column].getGraphicBlock());

    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}

