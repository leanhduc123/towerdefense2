package sample.Entity.Tower;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import sample.Config;
import sample.Entity.Bullet.NormalBullet;
import sample.Gamefield;


public class NormalTower extends Tower {

    public NormalTower(int posX,int posY, Gamefield field){
        super(posX,posY,Config.NORMAL_TOWER_SPEED,Config.NORMAL_TOWER_RANGE,Config.NORMAL_TOWER_DAMAGE,Config.NORMAL_TOWER_PRICE,"file:src/resources/tile/tower/normal_upgrade.png",field);
    }
    @Override
    public void drawTower(Group root){
        ImageView platform = new ImageView(new Image("file:src/resources/tile/tower/platform.png"));
        platform.setX(super.getPosX());
        platform.setY(super.getPosY());
        platform.setFitWidth(Config.TILE_SIZE);
        platform.setFitHeight(Config.TILE_SIZE);
        root.getChildren().add(platform);
        ImageView tower = new ImageView(new Image("file:src/resources/tile/tower/normal.png"));
        tower.setX(super.getPosX());
        tower.setY(super.getPosY());
        tower.setFitWidth(Config.TILE_SIZE);
        tower.setFitHeight(Config.TILE_SIZE);
        setTower(tower);
        root.getChildren().add(tower);
    }

    @Override
    public boolean distance(double dt){
        if (dt < Config.NORMAL_TOWER_RANGE) return true;
        return false;
    }

    @Override
    public void shooting(Group root){
        inCircle();
        if (super.getEnemyNear().size() > 0 &&
                (int)super.getEnemyNear().get(0).getCanvas().getTranslateX() > 10 &&
                (int)super.getEnemyNear().get(0).getCanvas().getTranslateY() > 20){
            Media media = new Media("File:/C:/Users/Asus/IdeaProjects/towerdefense2/src/shoot.mp3");
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
            if (!getField().sound()) mediaPlayer.setVolume(0.5);
            else mediaPlayer.setVolume(0);
            root.getChildren().add((new NormalBullet(super.getPosX(),super.getPosY(),(int)super.getEnemyNear().get(0).getCanvas().getTranslateX(),(int)super.getEnemyNear().get(0).getCanvas().getTranslateY(),super.getDamage(),super.getEnemyNear())).drawBullet());
        }
        else root.getChildren().add(new Canvas(0,0));
    }
}
