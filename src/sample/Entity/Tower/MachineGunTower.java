package sample.Entity.Tower;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import sample.Config;
import sample.Entity.Bullet.MachineGunBullet;
import sample.Gamefield;

public class MachineGunTower extends Tower {
    public MachineGunTower(int posX,int posY, Gamefield field){
        super(posX,posY, Config.MACHINE_GUN_SPEED,Config.MACHINE_GUN_RANGE,Config.MACHINE_GUN_DAMAGE,field);
    }
    @Override
    public void drawTower(Group root){
        ImageView platform = new ImageView(new Image("file:src/resources/tile/tower/platform.png"));
        platform.setX(super.getPosX());
        platform.setY(super.getPosY());
        platform.setFitWidth(Config.TILE_SIZE);
        platform.setFitHeight(Config.TILE_SIZE);
        root.getChildren().add(platform);
        ImageView tower = new ImageView(new Image("file:src/resources/tile/tower/machine_gun.png"));
        tower.setX(super.getPosX());
        tower.setY(super.getPosY());
        tower.setFitWidth(Config.TILE_SIZE);
        tower.setFitHeight(Config.TILE_SIZE);
        root.getChildren().add(tower);
    }

    @Override
    public boolean distance(double dt){
        if (dt < Config.MACHINE_GUN_RANGE) return true;
        return false;
    }

    @Override
    public void shooting(Group root){
        inCircle();
        if (super.getEnemyNear().size() > 0) {
            Media media = new Media("File:/C:/Users/Asus/IdeaProjects/towerdefense2/src/shoot.mp3");
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            if (!getField().sound()) mediaPlayer.setAutoPlay(true);
            else mediaPlayer.setAutoPlay(false);
            root.getChildren().add((new MachineGunBullet(super.getPosX(), super.getPosY(), (int) super.getEnemyNear().get(0).getCanvas().getTranslateX(), (int) super.getEnemyNear().get(0).getCanvas().getTranslateY(), super.getDamage(), super.getEnemyNear())).drawBullet());
        }
        else root.getChildren().add(new Canvas(0,0));
    }
}
