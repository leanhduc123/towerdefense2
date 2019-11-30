package sample.Entity.Enemy;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import sample.Config;
import sample.Gamefield;

public class SmallEnemy extends Enemy {
    private Canvas canvas = new Canvas(Config.TILE_SIZE,Config.TILE_SIZE);

    public SmallEnemy(Gamefield field){
        super(Config.SMALL_ENEMY_HEALTH, Config.SMALL_ENEMY_ARMOR, Config.SMALL_ENEMY_SPEED, Config.SMALL_ENEMY_REWARD, field);
    }

    @Override
    public Canvas getCanvas(){
        return canvas;
    }

    @Override
    public Canvas EnemyAppear(){
        canvas.setTranslateX(Config.spawner.getPosX()+30);
        canvas.setTranslateY(Config.spawner.getPosY()+30);
        canvas.getGraphicsContext2D().drawImage(new Image("file:src/resources/enemy/small.png"),0,0);
        return enemyMovement(canvas);
    }
}
