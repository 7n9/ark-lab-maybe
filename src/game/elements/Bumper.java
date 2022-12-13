package game.elements;

import game.RenderGlobal;

import java.awt.*;

public class Bumper {

    public Bumper(RenderGlobal rg){
        this.renderGlobal = rg;
        bumperWidth = 100;
        bumperHeight = 20;
        posY = 30;
    }

    public void render() {
        renderGlobal.drawFilledRectWithColor(posX, posY, bumperWidth, bumperHeight, Color.BLUE.getRGB());
    }

    public float getPosX(){
        return posX;
    }

    public float getPosY(){
        return posY;
    }

    public void setPosX(float pos){
        this.posX = pos;
    }

    public void incrementPosX(float pos){
        this.posX += pos;
    }

    public int getBumperWidth(){
        return bumperWidth;
    }

    public int getBumperHeight(){
        return bumperHeight;
    }

    private float posX, posY;
    private int bumperWidth, bumperHeight;
    private RenderGlobal renderGlobal;


}
