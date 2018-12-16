import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

abstract class Player implements Global {
    private String name;
    private int symbol;
    private Board gameBoard;
    private Image GSymbol;

    public Player() {
        name = "Unknown";
        symbol = EMPTY;
        // The Global interface does not have a default image for in case an image is not chosen
    }

    public Player(String name, int symbol, Board gameBoard, Image GSymbol) {
        this();
        setName(name);
        setSymbol(symbol);
        setGameBoard(gameBoard);
        setGSymbol(GSymbol);
    }

    public void setName(String name) {
        if (!name.trim().isEmpty()) {
            this.name = name;
        }
    }

    public void setSymbol(int symbol) {
        if (symbol == O || symbol == X) {
            this.symbol = symbol;
        }
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void setGSymbol(Image GSymbol) {
        if (GSymbol.equals(GraphicO) || GSymbol.equals(GraphicX)) {
            this.GSymbol = GSymbol;
        }
    }

    public String getName() {
        return name;
    }

    public int getSymbol() {
        return symbol;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public Image getGSymbol() {
        return GSymbol;
    }

    public abstract void play(Board gameboard);

    @Override
    public String toString() {
        return "Player Name: " + name + "Player Symbol: " + symbol;
    }

}

