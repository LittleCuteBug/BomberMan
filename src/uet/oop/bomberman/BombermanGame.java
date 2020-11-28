package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Keyboard;

public class BombermanGame extends Application {

    private GraphicsContext gc;
    private Canvas canvas;
    private Game game;
    private final int DEFAULT_WIDTH = 20;
    private final int DEFAULT_HEIGHT = 8 ;
    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        game = new Game();
        Map.loadMap("res/levels/Level1.txt",game);
        //createMap();
        // Tao Canvas

        canvas = new Canvas(Sprite.SCALED_SIZE * DEFAULT_WIDTH, Sprite.SCALED_SIZE * DEFAULT_HEIGHT);
        gc = canvas.getGraphicsContext2D();
        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        scene.setOnKeyPressed(Keyboard::keyPressed);
        scene.setOnKeyReleased(Keyboard::keyReleased);

        // Them scene vao stage
        stage.setScene(scene);
        stage.setMaxWidth((game.getWIDTH()+0.5)*Sprite.SCALED_SIZE);
        stage.setMaxHeight((game.getHEIGHT()+1.2)*Sprite.SCALED_SIZE);
        stage.show();

        gc.setFill(Color.GREEN);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                canvas.setWidth(scene.getWidth());
                canvas.setHeight(scene.getHeight());
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                game.render(gc);
                game.update(canvas.getWidth(),canvas.getHeight());
            }
        };
        timer.start();
    }


}
