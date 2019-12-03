package sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sample.Entity.Enemy.*;
import sample.Entity.Tower.NormalTower;
import sample.Entity.Tower.SniperTower;

import java.util.List;

public class Controller {
    private Group root;
    private Gamefield field;
    private GameStage stage;
    private List<Enemy> enemyList;
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
        SniperTower sniperTower = new SniperTower(960, 400,field);
        sniperTower.drawTower(root);
        Image sound = new Image("file:src/sound.png");
        ImageView imageView = new ImageView(sound);
        imageView.setTranslateX(100);
        imageView.setTranslateY(50);
        root.getChildren().add(imageView);
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!field.sound()) {
                    imageView.setImage(new Image("file:src/mute.png"));
                    field.setMute(true);
                }
                else {
                    imageView.setImage(new Image("file:src/sound.png"));
                    field.setMute(false);
                }
            }
        });
        new AnimationTimer(){
            int i = 0;
            int j = 0;
            int health = field.getMyHealth();
            public void handle(long currentNanoTime){
                j++;
                if (j % 60 == 0){
                    normalTower.shooting(root);
                }
                if (j % 80 == 0){
                    sniperTower.shooting(root);
                }
                normalTower.towerRotate();
                sniperTower.towerRotate();
                if (j % 100 == 0 && i < 30){
                    enemyList.get(i).EnemyAppear(root);
                    i++;
                }
                if (j % 50 == 0) {
                    normalTower.inCircle();
                    sniperTower.inCircle();
                }
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
