package sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import sample.Entity.Enemy.*;
import sample.Entity.Tower.MachineGunTower;
import sample.Entity.Tower.NormalTower;
import sample.Entity.Tower.SniperTower;
import sample.Entity.Tower.Tower;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private Group root;
    private Gamefield field;
    private GameStage stage;
    private List<Enemy> enemyList;
    private MediaPlayer mediaPlayer;
    private List<Tower> towerList = new ArrayList<>();
    public Controller(Group root, Gamefield field, GameStage stage){
        this.root = root;
        this.field = field;
        field.addEnemy();
        this.enemyList = field.getEnemyList();
        this.stage = stage;
        mediaPlayer = new MediaPlayer(new Media("File:/C:/Users/Asus/IdeaProjects/towerdefense2/src/soundtrack.mp3"));
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(0.7);
    }

    public void start(){
        for (int i = 0; i < field.getMyHealth(); i++) {
            root.getChildren().add(field.drawHeart(i*Config.TILE_SIZE+1000,30));
        }

        setTower();
        for (int k = 0; k < towerList.size(); k++)
            towerList.get(k).drawTower(root);
        Image sound = new Image("file:src/sound.png");
        ImageView imageView = new ImageView(sound);
        imageView.setTranslateX(1200);
        imageView.setTranslateY(50);
        root.getChildren().add(imageView);
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!field.sound()) {
                    imageView.setImage(new Image("file:src/mute.png"));
                    mediaPlayer.setVolume(0);
                    field.setMute(true);
                }
                else {
                    imageView.setImage(new Image("file:src/sound.png"));
                    mediaPlayer.setVolume(0.7);
                    field.setMute(false);
                }
            }
        });
        new AnimationTimer(){
            int i = 0;
            int j = 0;
            int k = 1;
            long lastEnemyRun = System.nanoTime();
            int health = field.getMyHealth();
            public void handle(long currentNanoTime){
                j++;
                for (int k = 0; k < towerList.size(); k++){
                    if (j % (towerList.get(k).getSpeed()*10) == 0) {
                        towerList.get(k).shooting(root);
                    }
                    towerList.get(k).towerRotate();
                    if (j % 50 == 0) towerList.get(k).inCircle();
                }
                System.out.println(field.getMyGold());
                if (j % 100 == 0 && i < k*10){
                    enemyList.get(i).EnemyAppear(root);
                    lastEnemyRun = System.nanoTime();
                    i++;
                }
                if (System.nanoTime() - lastEnemyRun > 1e9 * 6 && k < 3){
                    k++;
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
    public void setTower(){
        ImageView normalTower = new ImageView(new Image("file:src/resources/tile/tower/normal.png"));
        normalTower.setTranslateX(0);
        normalTower.setTranslateY(0);
        normalTower.setFitWidth(Config.TILE_SIZE);
        normalTower.setFitHeight(Config.TILE_SIZE);
        TowerEvent event = new TowerEvent();
        ImageView sniperTower = new ImageView(new Image("file:src/resources/tile/tower/sniper.png"));
        sniperTower.setTranslateX(60);
        sniperTower.setTranslateY(0);
        sniperTower.setFitWidth(Config.TILE_SIZE);
        sniperTower.setFitHeight(Config.TILE_SIZE);
        ImageView machineGunTower = new ImageView(new Image("file:src/resources/tile/tower/machine_gun.png"));
        machineGunTower.setTranslateX(120);
        machineGunTower.setTranslateY(0);
        machineGunTower.setFitWidth(Config.TILE_SIZE);
        machineGunTower.setFitHeight(Config.TILE_SIZE);
        event.makeevent(normalTower,1);
        event.makeevent(sniperTower,2);
        event.makeevent(machineGunTower,3);
        root.getChildren().add(normalTower);
        root.getChildren().add(sniperTower);
        root.getChildren().add(machineGunTower);
    }

    public class TowerEvent{
        double sceneX, sceneY;
        double tranglateX, tranglateY;
        double newTranslateX;
        double newTranslateY;

        public void makeevent(ImageView imageView, int kind) {
            double A = imageView.getTranslateX();
            double B = imageView.getTranslateY();
            imageView.setOnMousePressed(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t){
                    sceneX = t.getSceneX();
                    sceneY = t.getSceneY();
                    ImageView p = ((ImageView)(t.getSource()));
                    tranglateX = p.getTranslateX();
                    tranglateY = p.getTranslateY();
                }
            });
            imageView.setOnMouseDragged(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t){
                    double offsetX = t.getSceneX() - sceneX;
                    double offsetY = t.getSceneY() - sceneY;

                    newTranslateX = tranglateX + offsetX;
                    newTranslateY = tranglateY + offsetY;

                    ImageView p = ((ImageView)(t.getSource()));
                    p.setTranslateX(newTranslateX);
                    p.setTranslateY(newTranslateY);
                }
            });
            imageView.setOnMouseReleased( new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t){
                    int XX = (int)newTranslateX;
                    int YY = (int)newTranslateY;
                    int xPos = (XX+10)/60;
                    int yPos = (YY+10)/60;
                    boolean isSet = false;
                    if (towerList.size() == 1) System.out.println(towerList.get(0).getPosX() + " " + towerList.get(0).getPosY());
                    for (int k = 0; k < towerList.size(); k++){
                        if (towerList.get(k).getPosX() + 10 < XX + 10 && XX + 10 < towerList.get(k).getPosX() + 50 &&
                                towerList.get(k).getPosY() + 10 < YY + 10 && YY + 10 < towerList.get(k).getPosY() + 50)
                            isSet = true;
                        else if (towerList.get(k).getPosX() + 10 < XX + 50 && XX + 50 < towerList.get(k).getPosX() + 50 &&
                                towerList.get(k).getPosY() + 10 < YY + 10 && YY + 10 < towerList.get(k).getPosY() + 50)
                            isSet = true;
                        else if (towerList.get(k).getPosX() + 10 < XX + 10 && XX + 10 < towerList.get(k).getPosX() + 50 &&
                                towerList.get(k).getPosY() + 10 < YY + 50 && YY + 50 < towerList.get(k).getPosY() + 50)
                            isSet = true;
                        else if (towerList.get(k).getPosX() + 10 < XX + 50 && XX + 50 < towerList.get(k).getPosX() + 50 &&
                                towerList.get(k).getPosY() + 10 < YY + 50 && YY + 50 < towerList.get(k).getPosY() + 50)
                            isSet = true;
                    }
                    if (Config.MAP_SPRITES[yPos][xPos] != "1" &&
                            Config.MAP_SPRITES[yPos+1][xPos] != "1" &&
                            Config.MAP_SPRITES[yPos][xPos+1] != "1" &&
                            Config.MAP_SPRITES[yPos+1][xPos+1] != "1" &&
                            YY >= 100 && !isSet) {
                        Tower tower;
                        if (kind == 1 && field.getMyGold() >= Config.NORMAL_TOWER_PRICE) {
                            tower = new NormalTower(XX, YY, field);
                            field.setMyGold(field.getMyGold() - Config.NORMAL_TOWER_PRICE);
                            towerList.add(tower);
                            tower.drawTower(root);
                        }
                        else if (kind == 2 && field.getMyGold() >= Config.SNIPER_TOWER_PRICE) {
                            tower = new SniperTower(XX, YY, field);
                            field.setMyGold(field.getMyGold() - Config.SNIPER_TOWER_PRICE);
                            towerList.add(tower);
                            tower.drawTower(root);
                        }
                        else if (kind == 3 && field.getMyGold() >= Config.MACHINE_GUN_PRICE){
                            tower = new MachineGunTower(XX, YY, field);
                            field.setMyGold(field.getMyGold() - Config.MACHINE_GUN_PRICE);
                            towerList.add(tower);
                            tower.drawTower(root);
                        }
                    }
                    ImageView p = ((ImageView)(t.getSource()));
                    p.setTranslateX(A);
                    p.setTranslateY(B);
                }
            });
        }
    }
}
