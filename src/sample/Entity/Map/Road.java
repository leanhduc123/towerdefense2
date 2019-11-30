package sample.Entity.Map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Road {
    private int posX;
    private int posY;

    public Road(GraphicsContext gc, int posX, int posY){
        this.posX = posX;
        this.posY = posY;
        gc.drawImage(new Image("file:src/resources/tile/1.png"),posX,posY);
    }
}
