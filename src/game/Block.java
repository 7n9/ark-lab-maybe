package game;

import java.awt.*;
import java.util.Random;

public class Block {
    

    public Block(int pass_shapeInt){
        setShapeColor((new Random().nextInt(4)) + 1);
    }


    private void setShapeColor(int colorInt){
        switch (colorInt) {
            case 1:
                this.shapeColor = Color.BLUE;
            case 2:
                this.shapeColor = Color.RED;
            case 3:
                this.shapeColor = Color.YELLOW;
            case 4:
                this.shapeColor = Color.WHITE;
            case 5:
                this.shapeColor = Color.GREEN;
            default:
                break;
        }
    }

    private Color shapeColor = new Color(0.0F, 0.0F, 0.0F);
}
