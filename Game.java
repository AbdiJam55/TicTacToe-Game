import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Random;

public class Game extends Application implements Global {
    private boolean done;
    private Board gameBoard;
    private Player playerO = null;
    private Player playerX = null;
    private int P1Symbol, P2Symbol;
    private String gamemode;
    private String player1;
    private String player2;
    private StackPane label;
    private static int counter = 0;

    @Override
    public void start(Stage primaryStage) {
        GameObject GObj = new GameObject();
        gameBoard = new Board();
        Group root1 = new Group(GObj.backgroundImage());
        Group root2 = new Group(GObj.backgroundImage());
        Group root3 = new Group(GObj.backgroundImage());
        Scene scene1 = new Scene(root1,600,600);
        Scene scene2 = new Scene(root2,600,600);
        Scene scene3 = new Scene(root3,600,600);
        ImageView titleImage1 = GObj.titleImage(500,150);
        ImageView titleImage2 = GObj.titleImage(500,150);
        ImageView titleImage3 = GObj.titleImage(500,150);

        ImageView SE_button = GObj.imageButton("Images/button_SingleEasy_Unselected.png","Images/button_SingleEasy_Selected.png",200);
        ImageView SH_button = GObj.imageButton("Images/button_SingleHard_Unselected.png","Images/button_SingleHard_Selected.png",200);
        ImageView M_button = GObj.imageButton("Images/button_Multi_Unselected.png","Images/button_Multi_Selected.png",200);
        ImageView PO_button = GObj.imageButton("Images/playerO_Unselected.png","Images/playerO_Selected.png",200);
        ImageView PX_button = GObj.imageButton("Images/playerX_Unselected.png","Images/playerX_Selected.png",200);
        ImageView PA_button = GObj.imageButton("Images/play_again_Unselected.png","Images/play_again_Selected.png",140);
        ImageView E_button = GObj.imageButton("Images/Exit_Unselected.png","Images/Exit_Selected.png",140);

        GridPane layout1 = new GridPane();
        GridPane layout2 = new GridPane();
        VBox layout3 = new VBox(20);
        //insets are offsets for the 4 sides of rect area (so 5px from top,left,right,bottom)
        // gridpane.setPadding(new Insets(5));
        layout1.setMinSize(600,600);
        layout2.setMinSize(600,600);
        layout3.setMinSize(600,600);

/* ------------- Adjusting the GridPane Layout1 is done here ------------- */
        ColumnConstraints col1L1 = new ColumnConstraints(200);
        ColumnConstraints col2L1 = new ColumnConstraints(200);
        ColumnConstraints col3L1 = new ColumnConstraints(200);
        layout1.getColumnConstraints().addAll(col1L1,col2L1,col3L1);
        RowConstraints row1L1 = new RowConstraints(250);
        layout1.getRowConstraints().add(row1L1);
        RowConstraints[] rowL1 = new RowConstraints[3];
        for (int i=0;i<3;i++) {
            rowL1[i] = new RowConstraints(75);
            layout1.getRowConstraints().add(rowL1[i]);
        }
        GridPane.setConstraints(titleImage1,0,0,3,1, HPos.CENTER, VPos.CENTER);
        GridPane.setMargin(titleImage1,new Insets(20,0,50,0));
        GridPane.setConstraints(SE_button,1,1);
        GridPane.setConstraints(SH_button,1,2);
        GridPane.setConstraints(M_button,1,3);
        layout1.setAlignment(Pos.TOP_CENTER);
/* ----------------------------------------------------------------------- */

/* ------------- Adjusting the GridPane Layout2 is done here ------------- */
        ColumnConstraints col1L2 = new ColumnConstraints(200);
        ColumnConstraints col2L2 = new ColumnConstraints(200);
        ColumnConstraints col3L2 = new ColumnConstraints(200);
        layout2.getColumnConstraints().addAll(col1L2,col2L2,col3L2);
        RowConstraints row1L2 = new RowConstraints(300);
        layout2.getRowConstraints().add(row1L2);
        RowConstraints[] rowL2 = new RowConstraints[2];
        for (int i=0;i<2;i++){
            rowL2[i] = new RowConstraints(75);
            layout2.getRowConstraints().add(rowL2[i]);
        }
        GridPane.setConstraints(titleImage2,0,0,3,1, HPos.CENTER, VPos.CENTER);
        GridPane.setMargin(titleImage2,new Insets(20,0,100,0));
        GridPane.setConstraints(PO_button,1,1);
        GridPane.setConstraints(PX_button,1,2);
        layout1.setAlignment(Pos.TOP_CENTER);
/* ----------------------------------------------------------------------- */

/* ------------- Adjusting the GridPane Layout3 is done here ------------- */
        VBox.setMargin(titleImage3, new Insets(10,0,0,0));
        HBox lowpart = new HBox(15);
        lowpart.setMinSize(500,100);
        label = new StackPane();
        label.getChildren().addAll(GObj.Labelbackground(250,50),makeLabel("The Game is ON!"));
        lowpart.getChildren().addAll(label,PA_button,E_button);
        HBox.setMargin(label,new Insets(0,0,0,20));
        layout3.setAlignment(Pos.TOP_CENTER);
        gameBoard.displayGraphicBoard();
/* ----------------------------------------------------------------------- */


/* --------------------- Used for Debugging purposes --------------------- */
        //layout1.setGridLinesVisible(true);
        //layout2.setGridLinesVisible(true);


/* ----------------------------------- Button and Game Operations ---------------------------------- */
        SE_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                gamemode = "single easy";
               // System.out.println(primaryStage.sceneProperty());
                primaryStage.setScene(scene2);
            }
        });
        SH_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                gamemode = "single hard";
             //   System.out.println(primaryStage.sceneProperty());
                primaryStage.setScene(scene2);
            }
        });
        M_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                gamemode = "multi";
              //  System.out.println(primaryStage.sceneProperty());
                primaryStage.setScene(scene2);
            }
        });
        PA_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(scene1);
                counter = 0;  // *** This must be reset so that the AIfirstturn() method can be invoked again *** //
                gameBoard = new Board();
                gameBoard.displayGraphicBoard();
                label.getChildren().clear();
                label.getChildren().addAll(GObj.Labelbackground(250,50),makeLabel("The Game is ON!"));
                layout3.getChildren().clear();
                layout3.getChildren().addAll(titleImage3,gameBoard.getGraphicBoard(),lowpart);
            }
        });
        E_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.close();
            }
        });
        PO_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                player1 = "playerO";
                player2 = player2(player1);
                P1Symbol = player1Symbol(player1);
                P2Symbol = player2Symbol(player2);
                makePlayers();
                //System.out.println(player1 +"     "+ player2);
                //System.out.println(playerX.getName() +" "+ playerX.getSymbol());
                //System.out.println(playerO.getName() +" "+ playerO.getSymbol());

                primaryStage.setScene(scene3);
                if (counter == 0) {
                    AIfirstturn();
                    counter++;
                    //System.out.println(counter);
                }
                CoreGame();
            }
        });
        PX_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                player1 = "playerX";
                player2 = player2(player1);
                P1Symbol = player1Symbol(player1);
                P2Symbol = player2Symbol(player2);
                makePlayers();
                //System.out.println(player1 +"     "+ player2);
                //System.out.println(playerX.getName() +" "+ playerX.getSymbol());
                //System.out.println(playerO.getName() +" "+ playerO.getSymbol());

                primaryStage.setScene(scene3);
                if (counter == 0) {
                    AIfirstturn();
                    counter++;
                    //System.out.println(counter);
                }
                CoreGame();
            }
        });
/* ----------------------------------------------------------------------------------------------------- */

        layout1.getChildren().addAll(titleImage1,SE_button,SH_button,M_button);
        root1.getChildren().add(layout1);
        layout2.getChildren().addAll(titleImage2,PO_button,PX_button);
        root2.getChildren().add(layout2);
        layout3.getChildren().addAll(titleImage3,gameBoard.getGraphicBoard(),lowpart);
        root3.getChildren().add(layout3);

        primaryStage.setTitle("Tic-Tac-Toe Game");
        primaryStage.setScene(scene1);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

/* ------------------------------------------ Core Game Operation ------------------------------------------ */
    /** This method is the Core operation of the Game Board and deals with player actions
     *  and updating the Game Board state. This method has 9 MOUSE_CLICK EventListeners for
     *  each block so every time this method is invoked, it will execute the handle() method
     *  for the block that was clicked. (Note: If no block was clicked then nothing happens)
     */
    private void CoreGame(){
        gameBoard.getGraphicBoard().getChildren().get(0).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int row; int column;
                Node node = (Node) event.getSource();
                row = GridPane.getRowIndex(node);
                column = GridPane.getColumnIndex(node);
                //System.out.println("Row: " + row);   //Checks error
                //System.out.println("Column: " + column);
                //gameBoard.displayBoard();

                if (gameBoard.makeMove(row,column)){
                    //System.out.println("First IF entered");
                    if (playerO instanceof HumanPlayer && done){
                        ((HumanPlayer) playerO).setRow(row);
                        ((HumanPlayer) playerO).setColumn(column);
                        //System.out.println("Second IF entered");
                        if (gameBoard.getGstate() == EMPTY) {
                            playerO.play(gameBoard);
                            if (gamemode.equals("multi")) {
                                GraphicStateUpdate("Player X Turn!");
                            }
                        }
                        gameBoard.updateState();
                        if (gameBoard.getGstate() == O){
                            //System.out.println("Player O Wins!");
                            //System.out.println("Block 1");
                            GraphicStateUpdate("Player O Wins!");
                        }
                        else if (gameBoard.getGstate() == X){
                            //System.out.println("Player X Wins!");
                            //System.out.println("Block 1");
                            GraphicStateUpdate("Player X Wins!");
                        }
                        else if (gameBoard.getGstate() == DRAW){
                            //System.out.println("Its a Draw!");
                            //System.out.println("Block 1");
                            GraphicStateUpdate("Its a Draw!");
                        }
                        if (playerX instanceof HumanPlayer) {
                            done = false;
                        }
                        if (playerX instanceof EasyAIPlayer || playerX instanceof HardAIPlayer) {
                            //System.out.println("Third IF entered");
                            if (gameBoard.getGstate() == EMPTY) {
                                playerX.play(gameBoard);
                            }
                            gameBoard.updateState();
                            if (gameBoard.getGstate() == O){
                                //System.out.println("Player O Wins!");
                                //System.out.println("Block 1");
                                GraphicStateUpdate("Player O Wins!");
                            }
                            else if (gameBoard.getGstate() == X){
                                //System.out.println("Player X Wins!");
                                //System.out.println("Block 1");
                                GraphicStateUpdate("Player X Wins!");
                            }
                            else if (gameBoard.getGstate() == DRAW){
                                //System.out.println("Its a Draw!");
                                //System.out.println("Block 1");
                                GraphicStateUpdate("Its a Draw!");
                            }
                        }
                    }
                    else if (playerX instanceof HumanPlayer && !done){
                        ((HumanPlayer) playerX).setRow(row);
                        ((HumanPlayer) playerX).setColumn(column);
                        //System.out.println("Second IF entered");
                        if (gameBoard.getGstate() == EMPTY) {
                            playerX.play(gameBoard);
                            if (gamemode.equals("multi")) {
                                GraphicStateUpdate("Player O Turn!");
                            }
                        }
                        gameBoard.updateState();
                        if (gameBoard.getGstate() == O){
                            //System.out.println("Player O Wins!");
                            //System.out.println("Block 1");
                            GraphicStateUpdate("Player O Wins!");
                        }
                        else if (gameBoard.getGstate() == X){
                            //System.out.println("Player X Wins!");
                            //System.out.println("Block 1");
                            GraphicStateUpdate("Player X Wins!");
                        }
                        else if (gameBoard.getGstate() == DRAW){
                            //System.out.println("Its a Draw!");
                            //System.out.println("Block 1");
                            GraphicStateUpdate("Its a Draw!");
                        }
                        if (playerO instanceof HumanPlayer) {
                            done = true;
                        }
                        if (playerO instanceof EasyAIPlayer || playerO instanceof HardAIPlayer) {
                            //System.out.println("Third IF entered");
                            if (gameBoard.getGstate() == EMPTY) {
                                playerO.play(gameBoard);
                            }
                            gameBoard.updateState();
                            if (gameBoard.getGstate() == O){
                                //System.out.println("Player O Wins!");
                                //System.out.println("Block 1");
                                GraphicStateUpdate("Player O Wins!");
                            }
                            else if (gameBoard.getGstate() == X){
                                //System.out.println("Player X Wins!");
                                //System.out.println("Block 1");
                                GraphicStateUpdate("Player X Wins!");
                            }
                            else if (gameBoard.getGstate() == DRAW){
                                //System.out.println("Its a Draw!");
                                //System.out.println("Block 1");
                                GraphicStateUpdate("Its a Draw!");
                            }
                        }
                    }
                }
            }
        });

        gameBoard.getGraphicBoard().getChildren().get(1).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int row; int column;
                Node node = (Node) event.getSource();
                row = GridPane.getRowIndex(node);
                column = GridPane.getColumnIndex(node);
                //System.out.println("Row: " + row);   //Checks error
                //System.out.println("Column: " + column);
                //gameBoard.displayBoard();

                if (gameBoard.makeMove(row,column)){
                    //System.out.println("First IF entered");
                    if (playerO instanceof HumanPlayer && done){
                        ((HumanPlayer) playerO).setRow(row);
                        ((HumanPlayer) playerO).setColumn(column);
                        //System.out.println("Second IF entered");
                        if (gameBoard.getGstate() == EMPTY) {
                            playerO.play(gameBoard);
                            if (gamemode.equals("multi")) {
                                GraphicStateUpdate("Player X Turn!");
                            }
                        }
                        gameBoard.updateState();
                        if (gameBoard.getGstate() == O){
                            //System.out.println("Player O Wins!");
                            //System.out.println("Block 2");
                            GraphicStateUpdate("Player O Wins!");
                        }
                        else if (gameBoard.getGstate() == X){
                            //System.out.println("Player X Wins!");
                            //System.out.println("Block 2");
                            GraphicStateUpdate("Player X Wins!");
                        }
                        else if (gameBoard.getGstate() == DRAW){
                            //System.out.println("Its a Draw!");
                            //System.out.println("Block 2");
                            GraphicStateUpdate("Its a Draw!");
                        }
                        if (playerX instanceof HumanPlayer) {
                            done = false;
                        }
                        if (playerX instanceof EasyAIPlayer || playerX instanceof HardAIPlayer) {
                            //System.out.println("Third IF entered");
                            if (gameBoard.getGstate() == EMPTY) {
                                playerX.play(gameBoard);
                            }
                            gameBoard.updateState();
                            if (gameBoard.getGstate() == O){
                                //System.out.println("Player O Wins!");
                                //System.out.println("Block 2");
                                GraphicStateUpdate("Player O Wins!");
                            }
                            else if (gameBoard.getGstate() == X){
                                //System.out.println("Player X Wins!");
                                //System.out.println("Block 2");
                                GraphicStateUpdate("Player X Wins!");
                            }
                            else if (gameBoard.getGstate() == DRAW){
                                //System.out.println("Its a Draw!");
                                //System.out.println("Block 2");
                                GraphicStateUpdate("Its a Draw!");
                            }
                        }
                    }
                    else if (playerX instanceof HumanPlayer && !done){
                        ((HumanPlayer) playerX).setRow(row);
                        ((HumanPlayer) playerX).setColumn(column);
                        //System.out.println("Second IF entered");
                        if (gameBoard.getGstate() == EMPTY) {
                            playerX.play(gameBoard);
                            if (gamemode.equals("multi")) {
                                GraphicStateUpdate("Player O Turn!");
                            }
                        }
                        gameBoard.updateState();
                        if (gameBoard.getGstate() == O){
                            //System.out.println("Player O Wins!");
                            //System.out.println("Block 2");
                            GraphicStateUpdate("Player O Wins!");
                        }
                        else if (gameBoard.getGstate() == X){
                            //System.out.println("Player X Wins!");
                            //System.out.println("Block 2");
                            GraphicStateUpdate("Player X Wins!");
                        }
                        else if (gameBoard.getGstate() == DRAW){
                            //System.out.println("Its a Draw!");
                            //System.out.println("Block 2");
                            GraphicStateUpdate("Its a Draw!");
                        }
                        if (playerO instanceof HumanPlayer) {
                            done = true;
                        }
                        if (playerO instanceof EasyAIPlayer || playerO instanceof HardAIPlayer) {
                            //System.out.println("Third IF entered");
                            if (gameBoard.getGstate() == EMPTY) {
                                playerO.play(gameBoard);
                            }
                            gameBoard.updateState();
                            if (gameBoard.getGstate() == O){
                                //System.out.println("Player O Wins!");
                                //System.out.println("Block 2");
                                GraphicStateUpdate("Player O Wins!");
                            }
                            else if (gameBoard.getGstate() == X){
                                //System.out.println("Player X Wins!");
                                //System.out.println("Block 2");
                                GraphicStateUpdate("Player X Wins!");
                            }
                            else if (gameBoard.getGstate() == DRAW){
                                //System.out.println("Its a Draw!");
                                //System.out.println("Block 2");
                                GraphicStateUpdate("Its a Draw!");
                            }
                        }
                    }
                }
            }
        });
        gameBoard.getGraphicBoard().getChildren().get(2).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int row; int column;
                Node node = (Node) event.getSource();
                row = GridPane.getRowIndex(node);
                column = GridPane.getColumnIndex(node);
                //System.out.println("Row: " + row);   //Checks error
                //System.out.println("Column: " + column);
                //gameBoard.displayBoard();

                if (gameBoard.makeMove(row,column)){
                    //System.out.println("First IF entered");
                    if (playerO instanceof HumanPlayer && done){
                        ((HumanPlayer) playerO).setRow(row);
                        ((HumanPlayer) playerO).setColumn(column);
                        //System.out.println("Second IF entered");
                        if (gameBoard.getGstate() == EMPTY) {
                            playerO.play(gameBoard);
                            if (gamemode.equals("multi")) {
                                GraphicStateUpdate("Player X Turn!");
                            }
                        }
                        gameBoard.updateState();
                        if (gameBoard.getGstate() == O){
                            //System.out.println("Player O Wins!");
                            //System.out.println("Block 3");
                            GraphicStateUpdate("Player O Wins!");
                        }
                        else if (gameBoard.getGstate() == X){
                            //System.out.println("Player X Wins!");
                            //System.out.println("Block 3");
                            GraphicStateUpdate("Player X Wins!");
                        }
                        else if (gameBoard.getGstate() == DRAW){
                            //System.out.println("Its a Draw!");
                            //System.out.println("Block 3");
                            GraphicStateUpdate("Its a Draw!");
                        }
                        if (playerX instanceof HumanPlayer) {
                            done = false;
                        }
                        if (playerX instanceof EasyAIPlayer || playerX instanceof HardAIPlayer) {
                            //System.out.println("Third IF entered");
                            if (gameBoard.getGstate() == EMPTY) {
                                playerX.play(gameBoard);
                            }
                            gameBoard.updateState();
                            if (gameBoard.getGstate() == O){
                                //System.out.println("Player O Wins!");
                                //System.out.println("Block 3");
                                GraphicStateUpdate("Player O Wins!");
                            }
                            else if (gameBoard.getGstate() == X){
                                //System.out.println("Player X Wins!");
                                //System.out.println("Block 3");
                                GraphicStateUpdate("Player X Wins!");
                            }
                            else if (gameBoard.getGstate() == DRAW){
                                //System.out.println("Its a Draw!");
                                //System.out.println("Block 3");
                                GraphicStateUpdate("Its a Draw!");
                            }
                        }
                    }
                    else if (playerX instanceof HumanPlayer && !done){
                        ((HumanPlayer) playerX).setRow(row);
                        ((HumanPlayer) playerX).setColumn(column);
                        //System.out.println("Second IF entered");
                        if (gameBoard.getGstate() == EMPTY) {
                            playerX.play(gameBoard);
                            if (gamemode.equals("multi")) {
                                GraphicStateUpdate("Player O Turn!");
                            }
                        }
                        gameBoard.updateState();
                        if (gameBoard.getGstate() == O){
                            //System.out.println("Player O Wins!");
                            //System.out.println("Block 3");
                            GraphicStateUpdate("Player O Wins!");
                        }
                        else if (gameBoard.getGstate() == X){
                            //System.out.println("Player X Wins!");
                            //System.out.println("Block 3");
                            GraphicStateUpdate("Player X Wins!");
                        }
                        else if (gameBoard.getGstate() == DRAW){
                            //System.out.println("Its a Draw!");
                            //System.out.println("Block 3");
                            GraphicStateUpdate("Its a Draw!");
                        }
                        if (playerO instanceof HumanPlayer) {
                            done = true;
                        }
                        if (playerO instanceof EasyAIPlayer || playerO instanceof HardAIPlayer) {
                            //System.out.println("Third IF entered");
                            if (gameBoard.getGstate() == EMPTY) {
                                playerO.play(gameBoard);
                            }
                            gameBoard.updateState();
                            if (gameBoard.getGstate() == O){
                                //System.out.println("Player O Wins!");
                                //System.out.println("Block 3");
                                GraphicStateUpdate("Player O Wins!");
                            }
                            else if (gameBoard.getGstate() == X){
                                //System.out.println("Player X Wins!");
                                //System.out.println("Block 3");
                                GraphicStateUpdate("Player X Wins!");
                            }
                            else if (gameBoard.getGstate() == DRAW){
                                //System.out.println("Its a Draw!");
                                //System.out.println("Block 3");
                                GraphicStateUpdate("Its a Draw!");
                            }
                        }
                    }
                }
            }
        });
        gameBoard.getGraphicBoard().getChildren().get(3).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int row; int column;
                Node node = (Node) event.getSource();
                row = GridPane.getRowIndex(node);
                column = GridPane.getColumnIndex(node);
                //System.out.println("Row: " + row);   //Checks error
                //System.out.println("Column: " + column);
                //gameBoard.displayBoard();

                if (gameBoard.makeMove(row,column)){
                    //System.out.println("First IF entered");
                    if (playerO instanceof HumanPlayer && done){
                        ((HumanPlayer) playerO).setRow(row);
                        ((HumanPlayer) playerO).setColumn(column);
                        //System.out.println("Second IF entered");
                        if (gameBoard.getGstate() == EMPTY) {
                            playerO.play(gameBoard);
                            if (gamemode.equals("multi")) {
                                GraphicStateUpdate("Player X Turn!");
                            }
                        }
                        gameBoard.updateState();
                        if (gameBoard.getGstate() == O){
                            //System.out.println("Player O Wins!");
                            //System.out.println("Block 4");
                            GraphicStateUpdate("Player O Wins!");
                        }
                        else if (gameBoard.getGstate() == X){
                            //System.out.println("Player X Wins!");
                            //System.out.println("Block 4");
                            GraphicStateUpdate("Player X Wins!");
                        }
                        else if (gameBoard.getGstate() == DRAW){
                            //System.out.println("Its a Draw!");
                            //System.out.println("Block 4");
                            GraphicStateUpdate("Its a Draw!");
                        }
                        if (playerX instanceof HumanPlayer) {
                            done = false;
                        }
                        if (playerX instanceof EasyAIPlayer || playerX instanceof HardAIPlayer) {
                            //System.out.println("Third IF entered");
                            if (gameBoard.getGstate() == EMPTY) {
                                playerX.play(gameBoard);
                            }
                            gameBoard.updateState();
                            if (gameBoard.getGstate() == O){
                                //System.out.println("Player O Wins!");
                                //System.out.println("Block 4");
                                GraphicStateUpdate("Player O Wins!");
                            }
                            else if (gameBoard.getGstate() == X){
                                //System.out.println("Player X Wins!");
                                //System.out.println("Block 4");
                                GraphicStateUpdate("Player X Wins!");
                            }
                            else if (gameBoard.getGstate() == DRAW){
                                //System.out.println("Its a Draw!");
                                //System.out.println("Block 4");
                                GraphicStateUpdate("Its a Draw!");
                            }
                        }
                    }
                    else if (playerX instanceof HumanPlayer && !done){
                        ((HumanPlayer) playerX).setRow(row);
                        ((HumanPlayer) playerX).setColumn(column);
                        //System.out.println("Second IF entered");
                        if (gameBoard.getGstate() == EMPTY) {
                            playerX.play(gameBoard);
                            if (gamemode.equals("multi")) {
                                GraphicStateUpdate("Player O Turn!");
                            }
                        }
                        gameBoard.updateState();
                        if (gameBoard.getGstate() == O){
                            //System.out.println("Player O Wins!");
                            //System.out.println("Block 4");
                            GraphicStateUpdate("Player O Wins!");
                        }
                        else if (gameBoard.getGstate() == X){
                            //System.out.println("Player X Wins!");
                            //System.out.println("Block 4");
                            GraphicStateUpdate("Player X Wins!");
                        }
                        else if (gameBoard.getGstate() == DRAW){
                            //System.out.println("Its a Draw!");
                            //System.out.println("Block 4");
                            GraphicStateUpdate("Its a Draw!");
                        }
                        if (playerO instanceof HumanPlayer) {
                            done = true;
                        }
                        if (playerO instanceof EasyAIPlayer || playerO instanceof HardAIPlayer) {
                            //System.out.println("Third IF entered");
                            if (gameBoard.getGstate() == EMPTY) {
                                playerO.play(gameBoard);
                            }
                            gameBoard.updateState();
                            if (gameBoard.getGstate() == O){
                                //System.out.println("Player O Wins!");
                                //System.out.println("Block 4");
                                GraphicStateUpdate("Player O Wins!");
                            }
                            else if (gameBoard.getGstate() == X){
                                //System.out.println("Player X Wins!");
                                //System.out.println("Block 4");
                                GraphicStateUpdate("Player X Wins!");
                            }
                            else if (gameBoard.getGstate() == DRAW){
                                //System.out.println("Its a Draw!");
                                //System.out.println("Block 4");
                                GraphicStateUpdate("Its a Draw!");
                            }
                        }
                    }
                }
            }
        });
        gameBoard.getGraphicBoard().getChildren().get(4).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int row; int column;
                Node node = (Node) event.getSource();
                row = GridPane.getRowIndex(node);
                column = GridPane.getColumnIndex(node);
                //System.out.println("Row: " + row);   //Checks error
                //System.out.println("Column: " + column);
                //gameBoard.displayBoard();

                if (gameBoard.makeMove(row,column)){
                    //System.out.println("First IF entered");
                    if (playerO instanceof HumanPlayer && done){
                        ((HumanPlayer) playerO).setRow(row);
                        ((HumanPlayer) playerO).setColumn(column);
                        //System.out.println("Second IF entered");
                        if (gameBoard.getGstate() == EMPTY) {
                            playerO.play(gameBoard);
                            if (gamemode.equals("multi")) {
                                GraphicStateUpdate("Player X Turn!");
                            }
                        }
                        gameBoard.updateState();
                        if (gameBoard.getGstate() == O){
                            //System.out.println("Player O Wins!");
                            //System.out.println("Block 5");
                            GraphicStateUpdate("Player O Wins!");
                        }
                        else if (gameBoard.getGstate() == X){
                            //System.out.println("Player X Wins!");
                            //System.out.println("Block 5");
                            GraphicStateUpdate("Player X Wins!");
                        }
                        else if (gameBoard.getGstate() == DRAW){
                            //System.out.println("Its a Draw!");
                            //System.out.println("Block 5");
                            GraphicStateUpdate("Its a Draw!");
                        }
                        if (playerX instanceof HumanPlayer) {
                            done = false;
                        }
                        if (playerX instanceof EasyAIPlayer || playerX instanceof HardAIPlayer) {
                            //System.out.println("Third IF entered");
                            if (gameBoard.getGstate() == EMPTY) {
                                playerX.play(gameBoard);
                            }
                            gameBoard.updateState();
                            if (gameBoard.getGstate() == O){
                                //System.out.println("Player O Wins!");
                                //System.out.println("Block 5");
                                GraphicStateUpdate("Player O Wins!");
                            }
                            else if (gameBoard.getGstate() == X){
                                //System.out.println("Player X Wins!");
                                //System.out.println("Block 5");
                                GraphicStateUpdate("Player X Wins!");
                            }
                            else if (gameBoard.getGstate() == DRAW){
                                //System.out.println("Its a Draw!");
                                //System.out.println("Block 5");
                                GraphicStateUpdate("Its a Draw!");
                            }
                        }
                    }
                    else if (playerX instanceof HumanPlayer && !done){
                        ((HumanPlayer) playerX).setRow(row);
                        ((HumanPlayer) playerX).setColumn(column);
                        //System.out.println("Second IF entered");
                        if (gameBoard.getGstate() == EMPTY) {
                            playerX.play(gameBoard);
                            if (gamemode.equals("multi")) {
                                GraphicStateUpdate("Player O Turn!");
                            }
                        }
                        gameBoard.updateState();
                        if (gameBoard.getGstate() == O){
                            //System.out.println("Player O Wins!");
                            //System.out.println("Block 5");
                            GraphicStateUpdate("Player O Wins!");
                        }
                        else if (gameBoard.getGstate() == X){
                            //System.out.println("Player X Wins!");
                            //System.out.println("Block 5");
                            GraphicStateUpdate("Player X Wins!");
                        }
                        else if (gameBoard.getGstate() == DRAW){
                            //System.out.println("Its a Draw!");
                            //System.out.println("Block 5");
                            GraphicStateUpdate("Its a Draw!");
                        }
                        if (playerO instanceof HumanPlayer) {
                            done = true;
                        }
                        if (playerO instanceof EasyAIPlayer || playerO instanceof HardAIPlayer) {
                            //System.out.println("Third IF entered");
                            if (gameBoard.getGstate() == EMPTY) {
                                playerO.play(gameBoard);
                            }
                            gameBoard.updateState();
                            if (gameBoard.getGstate() == O){
                                //System.out.println("Player O Wins!");
                                //System.out.println("Block 5");
                                GraphicStateUpdate("Player O Wins!");
                            }
                            else if (gameBoard.getGstate() == X){
                                //System.out.println("Player X Wins!");
                                //System.out.println("Block 5");
                                GraphicStateUpdate("Player X Wins!");
                            }
                            else if (gameBoard.getGstate() == DRAW){
                                //System.out.println("Its a Draw!");
                                //System.out.println("Block 5");
                                GraphicStateUpdate("Its a Draw!");
                            }
                        }
                    }
                }
            }
        });
        gameBoard.getGraphicBoard().getChildren().get(5).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int row; int column;
                Node node = (Node) event.getSource();
                row = GridPane.getRowIndex(node);
                column = GridPane.getColumnIndex(node);
                //System.out.println("Row: " + row);   //Checks error
                //System.out.println("Column: " + column);
                //gameBoard.displayBoard();

                if (gameBoard.makeMove(row,column)){
                    //System.out.println("First IF entered");
                    if (playerO instanceof HumanPlayer && done){
                        ((HumanPlayer) playerO).setRow(row);
                        ((HumanPlayer) playerO).setColumn(column);
                        //System.out.println("Second IF entered");
                        if (gameBoard.getGstate() == EMPTY) {
                            playerO.play(gameBoard);
                            if (gamemode.equals("multi")) {
                                GraphicStateUpdate("Player X Turn!");
                            }
                        }
                        gameBoard.updateState();
                        if (gameBoard.getGstate() == O){
                            //System.out.println("Player O Wins!");
                            //System.out.println("Block 6");
                            GraphicStateUpdate("Player O Wins!");
                        }
                        else if (gameBoard.getGstate() == X){
                            //System.out.println("Player X Wins!");
                            //System.out.println("Block 6");
                            GraphicStateUpdate("Player X Wins!");
                        }
                        else if (gameBoard.getGstate() == DRAW){
                            //System.out.println("Its a Draw!");
                            //System.out.println("Block 6");
                            GraphicStateUpdate("Its a Draw!");
                        }
                        if (playerX instanceof HumanPlayer) {
                            done = false;
                        }
                        if (playerX instanceof EasyAIPlayer || playerX instanceof HardAIPlayer) {
                            //System.out.println("Third IF entered");
                            if (gameBoard.getGstate() == EMPTY) {
                                playerX.play(gameBoard);
                            }
                            gameBoard.updateState();
                            if (gameBoard.getGstate() == O){
                                //System.out.println("Player O Wins!");
                                //System.out.println("Block 6");
                                GraphicStateUpdate("Player O Wins!");
                            }
                            else if (gameBoard.getGstate() == X){
                                //System.out.println("Player X Wins!");
                                //System.out.println("Block 6");
                                GraphicStateUpdate("Player X Wins!");
                            }
                            else if (gameBoard.getGstate() == DRAW){
                                //System.out.println("Its a Draw!");
                                //System.out.println("Block 6");
                                GraphicStateUpdate("Its a Draw!");
                            }
                        }
                    }
                    else if (playerX instanceof HumanPlayer && !done){
                        ((HumanPlayer) playerX).setRow(row);
                        ((HumanPlayer) playerX).setColumn(column);
                        //System.out.println("Second IF entered");
                        if (gameBoard.getGstate() == EMPTY) {
                            playerX.play(gameBoard);
                            if (gamemode.equals("multi")) {
                                GraphicStateUpdate("Player O Turn!");
                            }
                        }
                        gameBoard.updateState();
                        if (gameBoard.getGstate() == O){
                            //System.out.println("Player O Wins!");
                            //System.out.println("Block 6");
                            GraphicStateUpdate("Player O Wins!");
                        }
                        else if (gameBoard.getGstate() == X){
                            //System.out.println("Player X Wins!");
                            //System.out.println("Block 6");
                            GraphicStateUpdate("Player X Wins!");
                        }
                        else if (gameBoard.getGstate() == DRAW){
                            //System.out.println("Its a Draw!");
                            //System.out.println("Block 6");
                            GraphicStateUpdate("Its a Draw!");
                        }
                        if (playerO instanceof HumanPlayer) {
                            done = true;
                        }
                        if (playerO instanceof EasyAIPlayer || playerO instanceof HardAIPlayer) {
                            //System.out.println("Third IF entered");
                            if (gameBoard.getGstate() == EMPTY) {
                                playerO.play(gameBoard);
                            }
                            gameBoard.updateState();
                            if (gameBoard.getGstate() == O){
                                //System.out.println("Player O Wins!");
                                //System.out.println("Block 6");
                                GraphicStateUpdate("Player O Wins!");
                            }
                            else if (gameBoard.getGstate() == X){
                                //System.out.println("Player X Wins!");
                                //System.out.println("Block 6");
                                GraphicStateUpdate("Player X Wins!");
                            }
                            else if (gameBoard.getGstate() == DRAW){
                                //System.out.println("Its a Draw!");
                                //System.out.println("Block 6");
                                GraphicStateUpdate("Its a Draw!");
                            }
                        }
                    }
                }
            }
        });
        gameBoard.getGraphicBoard().getChildren().get(6).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int row; int column;
                Node node = (Node) event.getSource();
                row = GridPane.getRowIndex(node);
                column = GridPane.getColumnIndex(node);
                //System.out.println("Row: " + row);   //Checks error
                //System.out.println("Column: " + column);
                //gameBoard.displayBoard();

                if (gameBoard.makeMove(row,column)){
                    //System.out.println("First IF entered");
                    if (playerO instanceof HumanPlayer && done){
                        ((HumanPlayer) playerO).setRow(row);
                        ((HumanPlayer) playerO).setColumn(column);
                        //System.out.println("Second IF entered");
                        if (gameBoard.getGstate() == EMPTY) {
                            playerO.play(gameBoard);
                            if (gamemode.equals("multi")) {
                                GraphicStateUpdate("Player X Turn!");
                            }
                        }
                        gameBoard.updateState();
                        if (gameBoard.getGstate() == O){
                            //System.out.println("Player O Wins!");
                            //System.out.println("Block 7");
                            GraphicStateUpdate("Player O Wins!");
                        }
                        else if (gameBoard.getGstate() == X){
                            //System.out.println("Player X Wins!");
                            //System.out.println("Block 7");
                            GraphicStateUpdate("Player X Wins!");
                        }
                        else if (gameBoard.getGstate() == DRAW){
                            //System.out.println("Its a Draw!");
                            //System.out.println("Block 7");
                            GraphicStateUpdate("Its a Draw!");
                        }
                        if (playerX instanceof HumanPlayer) {
                            done = false;
                        }
                        if (playerX instanceof EasyAIPlayer || playerX instanceof HardAIPlayer) {
                            //System.out.println("Third IF entered");
                            if (gameBoard.getGstate() == EMPTY) {
                                playerX.play(gameBoard);
                            }
                            gameBoard.updateState();
                            if (gameBoard.getGstate() == O){
                                //System.out.println("Player O Wins!");
                                //System.out.println("Block 7");
                                GraphicStateUpdate("Player O Wins!");
                            }
                            else if (gameBoard.getGstate() == X){
                                //System.out.println("Player X Wins!");
                                //System.out.println("Block 7");
                                GraphicStateUpdate("Player X Wins!");
                            }
                            else if (gameBoard.getGstate() == DRAW){
                                //System.out.println("Its a Draw!");
                                //System.out.println("Block 7");
                                GraphicStateUpdate("Its a Draw!");
                            }
                        }
                    }
                    else if (playerX instanceof HumanPlayer && !done){
                        ((HumanPlayer) playerX).setRow(row);
                        ((HumanPlayer) playerX).setColumn(column);
                        //System.out.println("Second IF entered");
                        if (gameBoard.getGstate() == EMPTY) {
                            playerX.play(gameBoard);
                            if (gamemode.equals("multi")) {
                                GraphicStateUpdate("Player O Turn!");
                            }
                        }
                        gameBoard.updateState();
                        if (gameBoard.getGstate() == O){
                            //System.out.println("Player O Wins!");
                            //System.out.println("Block 7");
                            GraphicStateUpdate("Player O Wins!");
                        }
                        else if (gameBoard.getGstate() == X){
                            //System.out.println("Player X Wins!");
                            //System.out.println("Block 7");
                            GraphicStateUpdate("Player X Wins!");
                        }
                        else if (gameBoard.getGstate() == DRAW){
                            //System.out.println("Its a Draw!");
                            //System.out.println("Block 7");
                            GraphicStateUpdate("Its a Draw!");
                        }
                        if (playerO instanceof HumanPlayer) {
                            done = true;
                        }
                        if (playerO instanceof EasyAIPlayer || playerO instanceof HardAIPlayer) {
                            //System.out.println("Third IF entered");
                            if (gameBoard.getGstate() == EMPTY) {
                                playerO.play(gameBoard);
                            }
                            gameBoard.updateState();
                            if (gameBoard.getGstate() == O){
                                //System.out.println("Player O Wins!");
                                //System.out.println("Block 7");
                                GraphicStateUpdate("Player O Wins!");
                            }
                            else if (gameBoard.getGstate() == X){
                                //System.out.println("Player X Wins!");
                                //System.out.println("Block 7");
                                GraphicStateUpdate("Player X Wins!");
                            }
                            else if (gameBoard.getGstate() == DRAW){
                                //System.out.println("Its a Draw!");
                                //System.out.println("Block 7");
                                GraphicStateUpdate("Its a Draw!");
                            }
                        }
                    }
                }
            }
        });
        gameBoard.getGraphicBoard().getChildren().get(7).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int row; int column;
                Node node = (Node) event.getSource();
                row = GridPane.getRowIndex(node);
                column = GridPane.getColumnIndex(node);
                //System.out.println("Row: " + row);   //Checks error
                //System.out.println("Column: " + column);
                //gameBoard.displayBoard();

                if (gameBoard.makeMove(row,column)){
                    //System.out.println("First IF entered");
                    if (playerO instanceof HumanPlayer && done){
                        ((HumanPlayer) playerO).setRow(row);
                        ((HumanPlayer) playerO).setColumn(column);
                        //System.out.println("Second IF entered");
                        if (gameBoard.getGstate() == EMPTY) {
                            playerO.play(gameBoard);
                            if (gamemode.equals("multi")) {
                                GraphicStateUpdate("Player X Turn!");
                            }
                        }
                        gameBoard.updateState();
                        if (gameBoard.getGstate() == O){
                            //System.out.println("Player O Wins!");
                            //System.out.println("Block 8");
                            GraphicStateUpdate("Player O Wins!");
                        }
                        else if (gameBoard.getGstate() == X){
                            //System.out.println("Player X Wins!");
                            //System.out.println("Block 8");
                            GraphicStateUpdate("Player X Wins!");
                        }
                        else if (gameBoard.getGstate() == DRAW){
                            //System.out.println("Its a Draw!");
                            //System.out.println("Block 8");
                            GraphicStateUpdate("Its a Draw!");
                        }
                        if (playerX instanceof HumanPlayer) {
                            done = false;
                        }
                        if (playerX instanceof EasyAIPlayer || playerX instanceof HardAIPlayer) {
                            //System.out.println("Third IF entered");
                            if (gameBoard.getGstate() == EMPTY) {
                                playerX.play(gameBoard);
                            }
                            gameBoard.updateState();
                            if (gameBoard.getGstate() == O){
                                //System.out.println("Player O Wins!");
                                //System.out.println("Block 8");
                                GraphicStateUpdate("Player O Wins!");
                            }
                            else if (gameBoard.getGstate() == X){
                                //System.out.println("Player X Wins!");
                                //System.out.println("Block 8");
                                GraphicStateUpdate("Player X Wins!");
                            }
                            else if (gameBoard.getGstate() == DRAW){
                                //System.out.println("Its a Draw!");
                                //System.out.println("Block 8");
                                GraphicStateUpdate("Its a Draw!");
                            }
                        }
                    }
                    else if (playerX instanceof HumanPlayer && !done){
                        ((HumanPlayer) playerX).setRow(row);
                        ((HumanPlayer) playerX).setColumn(column);
                        //System.out.println("Second IF entered");
                        if (gameBoard.getGstate() == EMPTY) {
                            playerX.play(gameBoard);
                            if (gamemode.equals("multi")) {
                                GraphicStateUpdate("Player O Turn!");
                            }
                        }
                        gameBoard.updateState();
                        if (gameBoard.getGstate() == O){
                            //System.out.println("Player O Wins!");
                            //System.out.println("Block 8");
                            GraphicStateUpdate("Player O Wins!");
                        }
                        else if (gameBoard.getGstate() == X){
                            //System.out.println("Player X Wins!");
                            //System.out.println("Block 8");
                            GraphicStateUpdate("Player X Wins!");
                        }
                        else if (gameBoard.getGstate() == DRAW){
                            //System.out.println("Its a Draw!");
                            //System.out.println("Block 8");
                            GraphicStateUpdate("Its a Draw!");
                        }
                        if (playerO instanceof HumanPlayer) {
                            done = true;
                        }
                        if (playerO instanceof EasyAIPlayer || playerO instanceof HardAIPlayer) {
                            //System.out.println("Third IF entered");
                            if (gameBoard.getGstate() == EMPTY) {
                                playerO.play(gameBoard);
                            }
                            gameBoard.updateState();
                            if (gameBoard.getGstate() == O){
                                //System.out.println("Player O Wins!");
                                //System.out.println("Block 8");
                                GraphicStateUpdate("Player O Wins!");
                            }
                            else if (gameBoard.getGstate() == X){
                                //System.out.println("Player X Wins!");
                                //System.out.println("Block 8");
                                GraphicStateUpdate("Player X Wins!");
                            }
                            else if (gameBoard.getGstate() == DRAW){
                                //System.out.println("Its a Draw!");
                                //System.out.println("Block 8");
                                GraphicStateUpdate("Its a Draw!");
                            }
                        }
                    }
                }
            }
        });
        gameBoard.getGraphicBoard().getChildren().get(8).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int row; int column;
                Node node = (Node) event.getSource();
                row = GridPane.getRowIndex(node);
                column = GridPane.getColumnIndex(node);
                //System.out.println("Row: " + row);   //Checks error
                //System.out.println("Column: " + column);
                //gameBoard.displayBoard();

                if (gameBoard.makeMove(row,column)){
                    //System.out.println("First IF entered");
                    if (playerO instanceof HumanPlayer && done){
                        ((HumanPlayer) playerO).setRow(row);
                        ((HumanPlayer) playerO).setColumn(column);
                        //System.out.println("Second IF entered");
                        if (gameBoard.getGstate() == EMPTY) {
                            playerO.play(gameBoard);
                            if (gamemode.equals("multi")) {
                                GraphicStateUpdate("Player X Turn!");
                            }
                        }
                        gameBoard.updateState();
                        if (gameBoard.getGstate() == O){
                            //System.out.println("Player O Wins!");
                            //System.out.println("Block 9");
                            GraphicStateUpdate("Player O Wins!");
                        }
                        else if (gameBoard.getGstate() == X){
                            //System.out.println("Player X Wins!");
                            //System.out.println("Block 9");
                            GraphicStateUpdate("Player X Wins!");
                        }
                        else if (gameBoard.getGstate() == DRAW){
                            //System.out.println("Its a Draw!");
                            //System.out.println("Block 9");
                            GraphicStateUpdate("Its a Draw!");
                        }
                        if (playerX instanceof HumanPlayer) {
                            done = false;
                        }
                        if (playerX instanceof EasyAIPlayer || playerX instanceof HardAIPlayer) {
                            //System.out.println("Third IF entered");
                            if (gameBoard.getGstate() == EMPTY) {
                                playerX.play(gameBoard);
                            }
                            gameBoard.updateState();
                            if (gameBoard.getGstate() == O){
                                //System.out.println("Player O Wins!");
                                //System.out.println("Block 9");
                                GraphicStateUpdate("Player O Wins!");
                            }
                            else if (gameBoard.getGstate() == X){
                                //System.out.println("Player X Wins!");
                                //System.out.println("Block 9");
                                GraphicStateUpdate("Player X Wins!");
                            }
                            else if (gameBoard.getGstate() == DRAW){
                                //System.out.println("Its a Draw!");
                                //System.out.println("Block 9");
                                GraphicStateUpdate("Its a Draw!");
                            }
                        }
                    }
                    else if (playerX instanceof HumanPlayer && !done){
                        ((HumanPlayer) playerX).setRow(row);
                        ((HumanPlayer) playerX).setColumn(column);
                        //System.out.println("Second IF entered");
                        if (gameBoard.getGstate() == EMPTY) {
                            playerX.play(gameBoard);
                            if (gamemode.equals("multi")) {
                                GraphicStateUpdate("Player O Turn!");
                            }
                        }
                        gameBoard.updateState();
                        if (gameBoard.getGstate() == O){
                            //System.out.println("Player O Wins!");
                            //System.out.println("Block 9");
                            GraphicStateUpdate("Player O Wins!");
                        }
                        else if (gameBoard.getGstate() == X){
                            //System.out.println("Player X Wins!");
                            //System.out.println("Block 9");
                            GraphicStateUpdate("Player X Wins!");
                        }
                        else if (gameBoard.getGstate() == DRAW){
                            //System.out.println("Its a Draw!");
                            //System.out.println("Block 9");
                            GraphicStateUpdate("Its a Draw!");
                        }
                        if (playerO instanceof HumanPlayer) {
                            done = true;
                        }
                        if (playerO instanceof EasyAIPlayer || playerO instanceof HardAIPlayer) {
                            //System.out.println("Third IF entered");
                            if (gameBoard.getGstate() == EMPTY) {
                                playerO.play(gameBoard);
                            }
                            gameBoard.updateState();
                            if (gameBoard.getGstate() == O){
                                //System.out.println("Player O Wins!");
                                //System.out.println("Block 9");
                                GraphicStateUpdate("Player O Wins!");
                            }
                            else if (gameBoard.getGstate() == X){
                                //System.out.println("Player X Wins!");
                                //System.out.println("Block 9");
                                GraphicStateUpdate("Player X Wins!");
                            }
                            else if (gameBoard.getGstate() == DRAW){
                                //System.out.println("Its a Draw!");
                                //System.out.println("Block 9");
                                GraphicStateUpdate("Its a Draw!");
                            }
                        }
                    }
                }
            }
        });
    }
/* --------------------------------------------------------------------------------------------------------- */

    /** This method decides which player plays first and executes the first move if
     *  this starting player happens to be an AI Player
     */
    private void AIfirstturn() {
        int turn = randomnumber();
        if (turn == O) {
            if (playerO instanceof EasyAIPlayer || playerO instanceof HardAIPlayer) {
                //System.out.println("PlayerO the AI starts!");
                playerO.play(gameBoard);
                done = false;
            } else if (playerO instanceof HumanPlayer) {
                //System.out.println("Player 1 is the User Starts!");
                done = true;
                if (gamemode.equals("multi")){
                    GraphicStateUpdate("Player O Starts!");
                }
            }
        }
        else if (turn == X) {
            if (playerX instanceof EasyAIPlayer || playerX instanceof HardAIPlayer) {
                //System.out.println("PlayerX the AI starts!");
                playerX.play(gameBoard);
                done = true;
            } else if (playerX instanceof HumanPlayer) {
                //System.out.println("Player 1 is the User Starts!");
                if (gamemode.equals("multi")){
                    GraphicStateUpdate("Player X Starts!");
                }
                done = false;
            }
        }
    }

    /** This method creates the players "Player X" and "Player O" based on what the user chooses
     */
    private void makePlayers(){
        if (gamemode.equals("single easy")) {
            if (player1.equals("playerO")) {
                playerO = new HumanPlayer(player1, P1Symbol, gameBoard, GraphicO);
                playerX = new EasyAIPlayer(player2, P2Symbol, gameBoard, GraphicX);
                //System.out.println(gamemode + "  " + player1);
            } else if (player1.equals("playerX")) {
                playerX = new HumanPlayer(player1, P1Symbol, gameBoard, GraphicX);
                playerO = new EasyAIPlayer(player2, P2Symbol, gameBoard, GraphicO);
                //System.out.println(gamemode + "  " + player1);
            }
        } else if (gamemode.equals("single hard")) {
            if (player1.equals("playerO")) {
                playerO = new HumanPlayer(player1, P1Symbol, gameBoard, GraphicO);
                playerX = new HardAIPlayer(player2, P2Symbol, gameBoard, GraphicX);
                //System.out.println(gamemode + "  " + player1);
            } else if (player1.equals("playerX")) {
                playerX = new HumanPlayer(player1, P1Symbol, gameBoard, GraphicX);
                playerO = new HardAIPlayer(player2, P2Symbol, gameBoard, GraphicO);
                //System.out.println(gamemode + "  " + player1);
            }
        } else if (gamemode.equals("multi")) {
            if (player1.equals("playerO")) {
                playerO = new HumanPlayer(player1, P1Symbol, gameBoard, GraphicO);
                playerX = new HumanPlayer(player2, P2Symbol, gameBoard, GraphicX);
                //System.out.println(gamemode + "  " + player1);
            } else if (player1.equals("playerX")) {
                playerX = new HumanPlayer(player1, P1Symbol, gameBoard, GraphicX);
                playerO = new HumanPlayer(player2, P2Symbol, gameBoard, GraphicO);
                //System.out.println(gamemode + "  " + player1);
            }
        }
        //System.out.println("Players Made Brother");
    }

    /** This method fixes the graphic text value to be displayed when the game state has changed
     * @param newmessage is the text value to be saved into the Label
     */
    private void GraphicStateUpdate(String newmessage){
        GameObject GO = new GameObject();
        label.getChildren().removeAll(GO.Labelbackground(250,50),makeLabel(newmessage));
        label.getChildren().addAll(GO.Labelbackground(250,50),makeLabel(newmessage));
    }

    /** This method makes labels with changing text values
     * @param temp is string value in the Label
     * @return new label object with some chosen text value
     */
    private Label makeLabel(String temp){
        Label newlabel = new Label(temp);
        newlabel.setFont(new Font("Arial",25));
        return newlabel;
    }

    /** This method is used to get the name of Player 2
     * @param P1 name of Player 1
     * @return name of Player 2
     */
    private String player2(String P1){
        String P2 = null;
        if (P1.equals("playerO")){
            P2 = "playerX";
        }
        else if(P1.equals("playerX")){
            P2 = "playerO";
        }
        return P2;
    }

    /** This method essentially flips a coin to see which player starts
     * @return a random number being either 1 or 2
     */
    private int randomnumber(){
        Random rnd = new Random();
        return rnd.nextInt(2) + 1;
    }

    /** This method is used to get the symbol of Player 1
     * @param P1 name of Player 1
     * @return the symbol of Player 1
     */
    private int player1Symbol(String P1){
        char[] Py1 = P1.toCharArray();
        char P1symbol = Py1[Py1.length -1];
        if (P1symbol =='X'){
            return X;
        }
        else if (P1symbol == 'O'){
            return O;
        }
        else { return EMPTY; }
    }

    /** This method is used to get the symbol of Player 2
     * @param P2 name of Player 2
     * @return the symbol of Player 2
     */
    private int player2Symbol(String P2){
        char[] Py2 = P2.toCharArray();
        char P2symbol = Py2[Py2.length -1];
        if (P2symbol =='X'){
            return X;
        }
        else if (P2symbol == 'O'){
            return O;
        }
        else { return EMPTY; }
    }

}
