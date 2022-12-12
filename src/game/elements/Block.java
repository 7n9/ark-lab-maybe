package game.elements;

import game.RenderGlobal;

import java.awt.*;

public class Block {

    public void render(RenderGlobal renderGlobal){
            renderGlobal.drawRectWithColor(posX, posY, blockWidth, blockHeight, getShapeColor().getRGB());
    }

    public void setPosX(int pos){
        this.posX = pos;
    }

    public void setShapeColor(int colorInt){
        switch (colorInt) {
            case 1:
                this.shapeColor = Color.BLUE;
                break;
            case 2:
                this.shapeColor = Color.RED;
                break;
            case 3:
                this.shapeColor = Color.YELLOW;
                break;
            case 4:
                this.shapeColor = Color.WHITE;
                break;
            case 5:
                this.shapeColor = Color.GREEN;
                break;
            case 6:
                this.shapeColor = Color.MAGENTA;
            default:
                break;
        }
    }

    public Color getShapeColor(){
        return this.shapeColor;
    }


    public int blockWidth = 50, blockHeight = 20;
    public int posX, posY;
    private Color shapeColor = new Color(0.0F, 0.0F, 0.0F);
}
