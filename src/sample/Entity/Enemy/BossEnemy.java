package sample.Entity.Enemy;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import sample.Config;
import sample.Gamefield;

public class BossEnemy extends Enemy {
    private Canvas canvas = new Canvas(Config.TILE_SIZE,Config.TILE_SIZE);

    public BossEnemy(Gamefield field){
        super(Config.BOSS_ENEMY_HEALTH, Config.BOSS_ENEMY_ARMOR, Config.BOSS_ENEMY_SPEED, Config.BOSS_ENEMY_REWARD, field);
    }

    @Override
    public Canvas getCanvas(){
        return canvas;
    }

    @Override
    public void EnemyAppear(Group root){
        canvas.setTranslateX(Config.spawner.getPosX());
        canvas.setTranslateY(Config.spawner.getPosY());
        canvas.getGraphicsContext2D().drawImage(new Image("file:src/resources/enemy/boss.png"),0,0);
        root.getChildren().add(enemyMovement(canvas));
    }
}
