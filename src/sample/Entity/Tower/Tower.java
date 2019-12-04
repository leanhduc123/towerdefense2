package sample.Entity.Tower;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import sample.Entity.Enemy.Enemy;
import sample.Entity.Upgradeable;
import sample.Gamefield;

import java.util.ArrayList;
import java.util.List;


public abstract class Tower implements Upgradeable {
    protected int posX;
    protected int posY;
    protected int speed;
    protected int range;
    protected int damage;
    protected int price;
    private boolean isUpgrade = false;
    private String url;
    protected Gamefield field;
    protected ImageView tower;
    protected List<Enemy> enemyNear = new ArrayList<>();

    public Tower(int posX,int posY,int speed, int range, int damage, int price, String url, Gamefield field){
        this.posX     = posX;
        this.posY     = posY;
        this.range  = range;
        this.speed  = speed;
        this.damage = damage;
        this.field  = field;
        this.price = price;
        this.url = url;
    }


    public void setTower(ImageView tower) {
        this.tower = tower;
    }

    public boolean isUpgrade() {
        return isUpgrade;
    }

    public void setUpgrade(boolean isUpgrade){
        this.isUpgrade = isUpgrade;
    }

    public ImageView getTower() {
        return tower;
    }

    public int getPosX() {
        return posX;
    }


    public int getPosY() {
        return posY;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }

    public Gamefield getField() {
        return field;
    }

    public List<Enemy> getEnemyNear() {
        return enemyNear;
    }

    @Override
    public void onUpgrade(){
        damage++;
        field.setMyGold(field.getMyGold() - price/2);
        tower.setImage(new Image(url));
    }

    public abstract void drawTower(Group root);

    public abstract void shooting(Group root);

    public abstract boolean distance(double dt);

    public void inCircle(){
        List<Enemy> enemyList = field.getEnemyList();
        double dt;
        for (int i = 0; i < enemyList.size(); i++){
            double ePosX = enemyList.get(i).getCanvas().getTranslateX();
            double ePosY = enemyList.get(i).getCanvas().getTranslateY();
            dt = Math.sqrt(Math.pow(posX-ePosX,2) + Math.pow(posY-ePosY,2));
            if (distance(dt) && !enemyList.get(i).hasDestroyed()) enemyNear.add(enemyList.get(i));
        }

        while(enemyNear.size() > 0){
            double ePosX = enemyNear.get(0).getCanvas().getTranslateX();
            double ePosY = enemyNear.get(0).getCanvas().getTranslateY();
            dt = Math.sqrt(Math.pow(posX-ePosX,2) + Math.pow(posY-ePosY,2));
            if (!distance(dt) || enemyNear.get(0).hasDestroyed() == true) enemyNear.remove(0);
            else break;
        }
    }

    public void towerRotate(){
        if (enemyNear.size() > 0  && !enemyNear.get(0).hasDestroyed() &&
                (int)enemyNear.get(0).getCanvas().getTranslateX() > 20 &&
                (int)enemyNear.get(0).getCanvas().getTranslateY() > 20){
            Enemy enemy = enemyNear.get(0);
            double vectorX = - posX + enemy.getCanvas().getTranslateX();
            double vectorY = - posY + enemy.getCanvas().getTranslateY();
            double X = posY + (-posX/vectorX)*vectorY;
            double Y = posX + (-posY/vectorY)*vectorX;
            double angle = Math.atan(Y/X) / (Math.PI) * 180;
            if (enemy.getCanvas().getTranslateY() >= posY) angle += 180;
            tower.setRotate(angle);
        }
    }
}
