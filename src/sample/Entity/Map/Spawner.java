package sample.Entity.Map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Spawner {
    private int posX;
    private int posY;

    public Spawner(){

    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosY() {
        return posY;
    }

    public Spawner(GraphicsContext gc, int posX, int posY){
        this.posX = posX;
        this.posY = posY;
        gc.drawImage(new Image("file:src/resources/tile/spawner/spawner.png"),posX,posY);
    }
}
