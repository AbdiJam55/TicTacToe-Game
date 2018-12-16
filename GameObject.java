
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class GameObject {

    int counter = 0;

    public ImageView backgroundImage(){
        //Loads the image
        Image image = new Image("Images/amazing background.jpg");
        //simply displays image as is
        ImageView img = new ImageView();
        img.setImage(image);
        img.setPreserveRatio(true);
        img.setSmooth(true);
        img.setCache(true);
        return img;
    }
    public ImageView titleImage(int width, int height){
        ImageView img = new ImageView();
        Image image = new Image("Images/tictactoe_title.png");
        img.setImage(image);
        img.setSmooth(true);
        // Width - 450 for Grid screen
        // Height - 150 for Grid screen
        img.setFitWidth(width);
        img.setFitHeight(height);
        img.setCache(true);
        return img;
    }

    public ImageView imageButton(String UnselectImage, String SelectImage,int width){
        ImageView img = new ImageView();
        Image Uns_image = new Image(UnselectImage);
        Image Sel_image = new Image(SelectImage);
        img.setImage(Uns_image);
        img.setSmooth(true);
        img.setFitWidth(width);
        img.setPreserveRatio(true);
        img.setCache(true);
        //Using Lambda expressions instead of Anonymous Inner Classes
        img.setOnMouseEntered((evt) -> img.setImage(Sel_image));
        img.setOnMouseExited((evt) -> img.setImage(Uns_image));
        return img;
    }

    /** This method sets up a background label
     * @param width of Label Background
     * @param height of Label Background
     * @return The background label as ImageView node
     */
    public ImageView Labelbackground(int width, int height){
        ImageView img = new ImageView();
        img.setImage(new Image("Images/whiterect.jpg"));
        img.setFitWidth(width); // 250 for scene 3
        img.setFitHeight(height); // 50 for scene 3
        img.setSmooth(true);
        return img;
    }

}
