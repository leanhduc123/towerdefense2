package sample.Entity.Tower;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Config;
import sample.Entity.Bullet.NormalBullet;
import sample.Gamefield;


public class NormalTower extends Tower {

    public NormalTower(int posX,int posY, Gamefield field){
        super(posX,posY,Config.NORMAL_TOWER_SPEED,Config.NORMAL_TOWER_RANGE,Config.NORMAL_TOWER_DAMAGE,field);
    }
    @Override
    public ImageView drawTower(){
        ImageView tower = new ImageView(new Image("file:src/resources/tile/tower/normal.png"));
        tower.setX(super.getPosX());
        tower.setY(super.getPosY());
        tower.setFitWidth(Config.TILE_SIZE);
        tower.setFitHeight(Config.TILE_SIZE);
        return tower;
    }

    @Override
    public boolean distance(double dt){
        if (dt < Config.NORMAL_TOWER_RANGE) return true;
        return false;
    }

    @Override
    public Canvas shooting(){
        inCircle();
        if (super.getEnemyNear().size() > 0)
        return (new NormalBullet(super.getPosX(),super.getPosY(),(int)super.getEnemyNear().get(0).getCanvas().getTranslateX(),(int)super.getEnemyNear().get(0).getCanvas().getTranslateY(),super.getDamage(),super.getEnemyNear())).drawBullet();
        return new Canvas(0,0);
    }
}
