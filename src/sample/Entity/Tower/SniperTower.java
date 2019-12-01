package sample.Entity.Tower;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Config;
import sample.Entity.Bullet.SniperBullet;
import sample.Gamefield;

public class SniperTower extends Tower{
    public SniperTower(int posX,int posY, Gamefield field){
        super(posX,posY, Config.SNIPER_TOWER_SPEED,Config.SNIPER_TOWER_RANGE,Config.SNIPER_TOWER_DAMAGE,field);
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
        root.getChildren().add(tower);
    }

    @Override
    public boolean distance(double dt){
        if (dt < Config.SNIPER_TOWER_RANGE) return true;
        return false;
    }

    @Override
    public void shooting(Group root){
        inCircle();
        if (super.getEnemyNear().size() > 0)
            root.getChildren().add((new SniperBullet(super.getPosX(),super.getPosY(),(int)super.getEnemyNear().get(0).getCanvas().getTranslateX(),(int)super.getEnemyNear().get(0).getCanvas().getTranslateY(),super.getDamage(),super.getEnemyNear())).drawBullet());
        else root.getChildren().add(new Canvas(0,0));
    }
}
