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

public class BombermanGame extends Application {

    private GraphicsContext gc;
    private Canvas canvas;
    private Game game;
    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        game = new Game();
        game.loadMap("res/levels/Level1.txt");
        //createMap();
        // Tao Canvas

        canvas = new Canvas(Sprite.SCALED_SIZE * game.getWIDTH(), Sprite.SCALED_SIZE * game.getHEIGHT());
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        gc.setFill(Color.GREEN);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                game.render(gc);
                game.update();
                System.out.println("ok");
            }
        };
        timer.start();
    }


}
