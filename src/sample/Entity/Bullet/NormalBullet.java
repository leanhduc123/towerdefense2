package sample.Entity.Bullet;


import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import sample.Config;
import sample.Entity.Enemy.Enemy;
import sample.Gamefield;

import java.util.List;

public class NormalBullet extends Bullet {
    public NormalBullet(int posX, int posY, int toX, int toY, int damage, List<Enemy> enemyNear){
        super(posX,posY,toX,toY,damage,enemyNear);
    }
    @Override
    public Canvas drawBullet(){
        Canvas bullet = new Canvas(Config.TILE_SIZE,Config.TILE_SIZE);
        bullet.setTranslateX(super.getPosX());
        bullet.setTranslateY(super.getPosY());
        bullet.getGraphicsContext2D().drawImage(new Image("file:src/resources/bullet/normal.png"),0,0);
        return bulletMovement(bullet);
    }
}
