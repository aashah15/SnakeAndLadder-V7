package sample;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main extends Application {


    public int rand = 0;
    public Label randResult;
    public Label gameResult;

    public static final int tileSize = 80;
    public static final int width = 10;
    public static final int height = 10;

    public Circle player1;
    public Circle player2;

    public Circle player3;
    public Circle player4;

    public boolean player1Turn = false;
    public boolean player2Turn = false;

    public boolean player3Turn = false;
    public boolean player4Turn = false;

    public int player1Position = 1;
    public int player2Position = 1;

    public int player3Position = 1;
    public int player4Position = 1;

    public int player1XPos = 150;
    public int player1YPos = 840;

    public int player2XPos = 640;
    public int player2YPos = 840;

    public int player3XPos = 150;
    public int player3YPos = 840;

    public int player4XPos = 640;
    public int player4YPos = 840;

    public int posCir1 = 1;
    public int posCir2 = 1;

    public int posCir3 = 1;
    public int posCir4 = 1;

    public int[] intArray1= new int[3];
    public int[] intArray2 = new int[3];
    public int[] intArray3= new int[3];
    public int[] intArray4 = new int[3];

    public boolean gameStart = false;
    public Button gameButton;

    private Group titleGroup = new Group();
    private PlayerPos playerXYPos;

    private Parent createContent(){
        // root
        Pane root = new Pane();
        root.setPrefSize(width * tileSize, (height * tileSize) + 160);
        root.getChildren().addAll(titleGroup);

        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                Tile tile = new Tile(tileSize, tileSize);
                tile.setTranslateX(j * tileSize);
                tile.setTranslateY(i * tileSize);
                titleGroup.getChildren().add(tile);

            }

        }

        PlayerPos player1Pos = new PlayerPos(150,840, 1);

        player1 = new Circle(40);
        player1.setId("Player 1");
        player1.setFill(Color.GREEN);
        player1.getStyleClass().add("Style.css");
        player1.setTranslateX(player1Pos.getPlayerXPos());
        player1.setTranslateY(player1Pos.getPlayerYPos());

        PlayerPos player2Pos = new PlayerPos(640,840, 1);

        player2 = new Circle(40);
        player2.setId("Player 2");
        player2.setFill(Color.RED);
        player2.getStyleClass().add("Style.css");
        player2.setTranslateX(player2Pos.getPlayerXPos());
        player2.setTranslateY(player2Pos.getPlayerYPos());

        PlayerPos player3Pos = new PlayerPos(150,930, 1);

        player3 = new Circle(40);
        player3.setId("Player 3");
        player3.setFill(Color.CHOCOLATE);
        player3.getStyleClass().add("Style.css");
        player3.setTranslateX(player3Pos.getPlayerXPos());
        player3.setTranslateY(player3Pos.getPlayerYPos());

        PlayerPos player4Pos = new PlayerPos(640,930, 1);

        player4 = new Circle(40);
        player4.setId("Player 4");
        player4.setFill(Color.BLUE);
        player4.getStyleClass().add("Style.css");
        player4.setTranslateX(player4Pos.getPlayerXPos());
        player4.setTranslateY(player4Pos.getPlayerYPos());


        // Player 1 Button event
        Button button1 = new Button("Player 1");
        button1.setTranslateX(20);
        button1.setTranslateY(820);
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(gameStart) {
                    if (player1Turn) {
                        getDiceValue();
                        randResult.setText(String.valueOf(rand));
                        player1Position += rand;

                        intArray1 =  player1Pos.movePlayer(player1Position);

                        player1XPos = intArray1[0];
                        player1YPos = intArray1[1];
                        player1Position = intArray1[2];

                        translatePlayer(player1XPos, player1YPos, player1);

                        if(player1XPos < 30 || player1YPos < 30 || player1Position == 100) {
                            gameResult.setTranslateX(530);
                            gameResult.setText("Player One Won");
                            gameButton.setText("Start Again");
                            gameStart = false;
                        }

                        player1Turn = false;
                        player2Turn = true;
                        player3Turn = false;
                        player4Turn = false;

                        if(gameStart)
                            gameResult.setText("Player Two turn");
                    }
                }
            }
        });


        // Player 2 Button event
        Button button2 = new Button("Player 2");
        button2.setTranslateX(700);
        button2.setTranslateY(820);
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (gameStart) {
                    if (player2Turn) {
                        getDiceValue();
                        randResult.setText(String.valueOf(rand));

                        player2Position += rand;

                        intArray2 =  player2Pos.movePlayer(player2Position);

                        player2XPos = intArray2[0];
                        player2YPos = intArray2[1];
                        player2Position = intArray2[2];
                        translatePlayer(player2XPos, player2YPos, player2);

                        if(player2XPos < 30 || player2YPos < 30 || player2Position == 100) {
                            gameResult.setTranslateX(530);
                            gameResult.setText("Player Two Won");
                            gameButton.setText("Start Again");
                            gameStart = false;
                        }

                        player1Turn = false;
                        player2Turn = false;
                        player3Turn = true;
                        player4Turn = false;

                        if(gameStart)
                            gameResult.setText("Player Three turn");
                    }
                }
            }
        });

        // Player 3 Button event
        Button button3 = new Button("Player 3");
        button3.setTranslateX(20);
        button3.setTranslateY(910);
        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (gameStart) {
                    if (player3Turn) {
                        getDiceValue();
                        randResult.setText(String.valueOf(rand));
                        player3Position += rand;

                        intArray3 =  player3Pos.movePlayer(player3Position);

                        player3XPos = intArray3[0];
                        player3YPos = intArray3[1];
                        player3Position = intArray3[2];

                        translatePlayer(player3XPos, player3YPos, player3);

                        if(player3XPos < 30 || player3YPos < 30 || player3Position == 100) {
                            gameResult.setTranslateX(530);
                            gameResult.setText("Player Three Won");
                            gameButton.setText("Start Again");
                            gameStart = false;
                        }

                        player1Turn = false;
                        player2Turn = false;
                        player3Turn = false;
                        player4Turn = true;

                        if(gameStart)
                            gameResult.setText("Player Four turn");

                    }
                }
            }
        });

        // Player 4 Button event
        Button button4 = new Button("Player 4");
        button4.setTranslateX(700);
        button4.setTranslateY(910);
        button4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(gameStart){
                    if(player4Turn){
                        getDiceValue();
                        randResult.setText(String.valueOf(rand));
                        player4Position += rand;

                        intArray4 =  player4Pos.movePlayer(player4Position);

                        player4XPos = intArray4[0];
                        player4YPos = intArray4[1];
                        player4Position = intArray4[2];

                        translatePlayer(player4XPos, player4YPos, player4);

                        if(player4XPos < 30 || player4YPos < 30 || player4Position == 100) {
                            gameResult.setTranslateX(530);
                            gameResult.setText("Player Four Won");
                            gameButton.setText("Start Again");
                            gameStart = false;
                        }

                        player1Turn = true;
                        player2Turn = false;
                        player3Turn = false;
                        player4Turn = false;

                        if(gameStart)
                            gameResult.setText("Player One turn");

                    }
                }
            }
        });

        // Game starts here
        gameButton = new Button("Start Game");
        gameButton.setTranslateX(350);
        gameButton.setTranslateY(860);

        gameButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (!gameStart) {
                    gameStart = true;
                    randResult.setText("Dice Result");
                    randResult.setTranslateX(220);
                    gameButton.setText("Game Started");

                    player1XPos = 40;
                    player1YPos = 760;

                    player2XPos = 40;
                    player2YPos = 760;

                    player3XPos = 40;
                    player3YPos = 760;

                    player4XPos = 40;
                    player4YPos = 760;

                    player1Position = 1;
                    player2Position = 1;

                    posCir1 = 1;
                    posCir2 = 1;

                    player3Position = 1;
                    player4Position = 1;

                    posCir3 = 1;
                    posCir4 = 1;

                    player1.setTranslateX(player1XPos);
                    player1.setTranslateY(player1YPos);
                    player2.setTranslateX(player2XPos);
                    player2.setTranslateY(player2YPos);

                    player3.setTranslateX(player1XPos);
                    player3.setTranslateY(player1YPos);
                    player4.setTranslateX(player2XPos);
                    player4.setTranslateY(player2YPos);

                    player1Turn = true;

                    rand = (int) (Math.random() * 4 + 1);
                    if (rand == 1) {
                        player1Turn = true;
                        gameResult.setText("Player One Turn");
                    }
                    if (rand == 2) {
                        player2Turn = true;
                        gameResult.setText("Player two Turn");
                    }
                    if (rand == 3) {
                        player3Turn = true;
                        gameResult.setText("Player Three Turn");
                    }
                    if (rand == 4 ){
                        player4Turn = true;
                        gameResult.setText("Player Four Turn");
                    }
                }
            }

        });

        randResult = new Label("Dice Result");
        randResult.setTranslateX(230);
        randResult.setTranslateY(870);

        gameResult = new Label("Game Result");
        gameResult.setTranslateX(490);
        gameResult.setTranslateY(870);

        Image img = new Image("/sample/snakebg.jpeg");
        ImageView imageView = new ImageView();
        imageView.setImage(img);
        imageView.setFitWidth(800);
        imageView.setFitHeight(800);
        titleGroup.getChildren().addAll(imageView,player1, player2, player3, player4, button1, button2, button3, button4, gameButton, randResult, gameResult);

        return root;

    }

    public void getDiceValue(){
        rand = (int)(Math.random() * 6 +1);
    }




    public void translatePlayer(int x, int y, Circle b){

        TranslateTransition animate = new TranslateTransition(Duration.millis(1000), b);
        animate.setToX(x);
        animate.setToY(y);
        animate.setAutoReverse(false);
        animate.play();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Snake and Ladder Game");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}


