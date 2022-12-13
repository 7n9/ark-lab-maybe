package game.elements;

import game.AABB;
import game.RenderGlobal;

import java.awt.*;

public class Block {

    public Block(){
        axisAligned = new AABB(0, 0, 0, 0);
    }

    public void render(RenderGlobal renderGlobal){
        rainbow = new Color(Color.HSBtoRGB((System.currentTimeMillis() % 1000L) / 1000.0F, 0.55F, 0.95F), false);

        renderGlobal.drawFilledRectWithColor(posX, posY, blockWidth, blockHeight, getShapeColor().getRGB());
        renderGlobal.drawHollowRectWithColor(posX+1, posY+1, blockWidth-1, blockHeight-1, 1.0F, rainbow.getRGB());
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

    public AABB getAABB(){
        return axisAligned;
    }

    public int blockWidth = 50, blockHeight = 20;
    public int posX, posY;
    private Color shapeColor = new Color(0.0F, 0.0F, 0.0F);
    private AABB axisAligned;
    private Color rainbow;
}
