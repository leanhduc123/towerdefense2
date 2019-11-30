package sample.Entity.Enemy;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import sample.Config;
import sample.Gamefield;


public class NormalEnemy extends Enemy {
    private Canvas canvas = new Canvas(Config.TILE_SIZE, Config.TILE_SIZE);

    public NormalEnemy(Gamefield field) {
        super(Config.NORMAL_ENEMY_HEALTH, Config.NORMAL_ENEMY_ARMOR, Config.NORMAL_ENEMY_SPEED, Config.NORMAL_ENEMY_REWARD, field);
    }

    @Override
    public Canvas getCanvas(){
        return canvas;
    }

    @Override
    public void EnemyAppear(Group root) {
        canvas.setTranslateX(Config.spawner.getPosX());
        canvas.setTranslateY(Config.spawner.getPosY());
        canvas.getGraphicsContext2D().drawImage(new Image("file:src/resources/enemy/normal.png"), 0, 0);
        root.getChildren().add(enemyMovement(canvas));
    }
}
