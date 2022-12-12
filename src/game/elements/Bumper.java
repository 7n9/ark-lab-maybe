package game.elements;

import game.RenderGlobal;

import java.awt.*;

public class Bumper {

    public Bumper(RenderGlobal rg){
        this.renderGlobal = rg;
        bumperWidth = 100;
    }

    public void render() {
        renderGlobal.drawRectWithColor(posX, 50, bumperWidth, 20, Color.BLUE.getRGB());
    }

    public float getPosX(){
        return posX;
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

    private float posX;
    private int bumperWidth;
    private RenderGlobal renderGlobal;


}
