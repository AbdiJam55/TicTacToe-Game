
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class Block implements Global {
    private int state;
    private ImageView GraphicBlock;

    public Block(){
        state = EMPTY;
        // Generally is a safe rule for "Constructors should not invoke overridable methods" WHY????

        GraphicBlock = new ImageView();
        GraphicBlock.setImage(GraphicEMPTY);
        GraphicBlock.setPreserveRatio(true);
        GraphicBlock.setSmooth(true);
    }

    public void setState(int state) {
        if (isValidState(state)){
            this.state = state; }
    }


    private Rectangle blackBorder(){
        Rectangle newrect2 = new Rectangle(100,100);
        newrect2.setFill(Color.TRANSPARENT);
        newrect2.setStroke(Color.BLACK);
        newrect2.setStrokeWidth(5);
        return newrect2;
    }


    public void setGraphicBlock(Image graphicBlock) {
        if (graphicBlock.equals(GraphicO) || graphicBlock.equals(GraphicX)) {
            GraphicBlock.setImage(graphicBlock);
            GraphicBlock.setPreserveRatio(true);
            GraphicBlock.setSmooth(true);
        }
    }

    public int getState() {
        return state;
    }

    public ImageView getGraphicBlock() {
        return GraphicBlock;
    }

    /** This method tests the validity of the argument
     * @param state is the acceptable va;ue of the block (either O or X)
     * @return true if valid state and false if invalid
     */
    public boolean isValidState(int state){
        return (state == O || state == X);
    }

    @Override
    public String toString() {
        return ""+state;
    }

}
