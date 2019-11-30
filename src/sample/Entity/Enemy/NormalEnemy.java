package sample.Entity.Enemy;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import sample.Config;
import sample.Gamefield;


public class NormalEnemy extends Enemy {
    private Canvas canvas = new Canvas(Config.TILE_SIZE, Config.TILE_SIZE);

    public NormalEnemy(int health, int armor, int speed, int reward, Gamefield field) {
        super(health, armor, speed, reward, field);
    }

    @Override
    public Canvas getCanvas(){
        return canvas;
    }

    @Override
    public Canvas EnemyAppear() {
        canvas.setTranslateX(Config.spawner.getPosX()+30);
        canvas.setTranslateY(Config.spawner.getPosY()+30);
        canvas.getGraphicsContext2D().drawImage(new Image("file:src/resources/enemy/normal.png"), 0, 0);
        return enemyMovement(canvas);
    }
}
