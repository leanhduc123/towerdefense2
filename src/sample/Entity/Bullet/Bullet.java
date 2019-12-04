package sample.Entity.Bullet;

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import sample.Entity.Destroyable;
import sample.Entity.Enemy.Enemy;

import java.util.List;


public abstract class Bullet implements Destroyable {
    private int posX;
    private int toX;
    private int posY;
    private int toY;
    private int damage;
    private List<Enemy> enemyNear;

    public Bullet(int posX,int posY, int toX, int toY, int damage, List<Enemy> enemyNear){
        this.posX = posX;
        this.posY = posY;
        this.toX = toX;
        this.toY = toY;
        this.damage = damage;
        this.enemyNear = enemyNear;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void onDestroy(Canvas canvas) {
        canvas.setWidth(0);
        canvas.setHeight(0);
    }

    public abstract Canvas drawBullet();

    public Canvas bulletMovement(Canvas canvas){
        Path path = new Path();
        path.getElements().add(new MoveTo(posX+30,posY+30));
        path.getElements().add(new LineTo(toX+30,toY+30));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(30));
        pathTransition.setNode(canvas);
        pathTransition.setPath(path);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(1);
        pathTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                onDestroy(canvas);
                Enemy enemy;
                if (enemyNear.size() > 0) {
                    enemy = enemyNear.get(0);
                    if (!enemy.hasDestroyed()) {
                        enemy.setHealth(enemy.getHealth() - damage);
                        if (enemy.getHealth() <= 0) {
                            enemy.onDestroy(enemy.getCanvas());
                            enemy.setDestroyed(true);
                        }
                    }
                }
            }
        });
        pathTransition.setAutoReverse(false);
        pathTransition.play();
        return canvas;
    }
}
