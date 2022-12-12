package game.elements;

import game.RenderGlobal;

import java.awt.*;

public class Ball {

    public Ball(RenderGlobal rg, Bumper b){
        renderGlobal = rg;
        bumper = b;
        ballCircumference = 20.0f;
        ballRadius = ballCircumference/2.0f;
        posX = 500 - ballRadius;
        posY = 200;
        motionX = 0.5f;
        motionY = 0.5f;
    }

    public void updateOnTick(){

    }

    public void updateOnFrame(Block[] block, int blockCount) {
        posY += motionY;
        posX += motionX;

        if(posX <= 0){
            motionX = -motionX;
        }
        if(posX + ballCircumference >= 1000){
            motionX = -motionX;
        }
        if(posY + ballCircumference >= 700){
            motionY = -motionY;
        }
        if(posY <= 0){
            motionY = -motionY;
        }

        for(int i = 0; i < blockCount; i++){
            if(isBallCollidedWithBlock(block[i])){
                block[i].setPosX(-100);
                motionY = -motionY;
            }
        }

        if(isBallCollidedWithBumper(bumper)){
            motionY = -motionY;
        }
    }

    public void render(){
        renderGlobal.drawCircle(posX, posY, ballRadius, Color.RED.getRGB());
        renderGlobal.drawCircle(posX, posY, 2, Color.WHITE.getRGB());
    }

    //todo fix collisions (ball center IN CENTER)
    private boolean isBallCollidedWithBlock(Block block){

        if(posY >= block.posY && posY < block.posY + block.blockHeight && posX > block.posX && posX < block.posX + block.blockWidth){
            return true;
        }else{
            return false;
        }

    }
    /*bumper collision check*/
    private boolean isBallCollidedWithBumper(Bumper bumper){
        if(posX >= bumper.getPosX() && posX <= bumper.getPosX() + bumper.getBumperWidth() && posY <= bumper.getPosY() + bumper.getBumperHeight() && posY >= bumper.getPosY()){
            return true;
        }else{
            return false;
        }
    }

    private float posX, posY;
    private float motionX, motionY;
    private float ballRadius, ballCircumference;
    private RenderGlobal renderGlobal;
    private Bumper bumper;


}
