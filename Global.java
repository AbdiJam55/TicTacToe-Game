import javafx.scene.image.Image;

interface Global {

    /** This interface is for representing the game board and block states */

    int DRAW = 3;
    int EMPTY = 0;
    int O = 1;
    int X = 2;
    Image GraphicX = new Image("Images/cross.jpg");
    Image GraphicO = new Image("Images/circle.jpg");
    Image GraphicEMPTY = new Image("Images/whiterect.jpg");

}
