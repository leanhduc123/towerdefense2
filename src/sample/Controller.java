package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import sample.Entity.Enemy.*;
import sample.Entity.Tower.NormalTower;

import java.util.List;

public class Controller {
    private Group root;
    private Gamefield field;
    private GameStage stage;
    public List<Enemy> enemyList;
    public Controller(Group root, Gamefield field, GameStage stage){
        this.root = root;
        this.field = field;
        field.addEnemy();
        this.enemyList = field.getEnemyList();
        this.stage = stage;
    }

    public void start(){
        for (int i = 0; i < field.getMyHealth(); i++) {
            root.getChildren().add(field.drawHeart(i*Config.TILE_SIZE+1000,30));
        }
        NormalTower normalTower = new NormalTower(600,400,field);
        normalTower.drawTower(root);
        new AnimationTimer(){
            int i = 0;
            int j = 0;
            int health = field.getMyHealth();
            public void handle(long currentNanoTime){
                j++;
                if (j % 20 == 0){
                    normalTower.shooting(root);
                }
                if (j % 100 == 0 && i < 30){
                    enemyList.get(i).EnemyAppear(root);
                    i++;
                }
                if (j % 50 == 0) normalTower.inCircle();
                if (health != field.getMyHealth()){
                    root.getChildren().add(field.removeHeart((field.getMyHealth())*60+1000,30));
                }
                if (field.getMyHealth() == 0){
                    stop();
                    stage.restart();
                }
                health = field.getMyHealth();
            }
        }.start();
    }
}
