package game.elements;

import game.AABB;
import game.RenderGlobal;

import java.awt.*;

public class Ball {

    public Ball(RenderGlobal rg, Bumper b){
        axisAligned = new AABB(posX - ballRadius, posY - ballRadius, posX + ballRadius, posY + ballRadius);
        renderGlobal = rg;
        bumper = b;
        ballCircumference = 20.0f;
        ballRadius = ballCircumference/2.0f;
        posX = 500 - ballRadius;
        posY = 200;
        motionX = 1.5f;
        motionY = 1.5f;
    }

    public void updateOnTick(){

    }

    public void updateOnFrame(Block[] block, int blockCount) {
        posY += motionY;
        posX += motionX;

        if(posX - ballRadius <= 0){
            motionX = -motionX;
        }
        if(posX + ballRadius >= 1000){
            motionX = -motionX;
        }
        if(posY + ballRadius >= 700){
            motionY = -motionY;
        }
        if(posY - ballRadius <= 0){
            motionY = -motionY;
        }

        for(int i = 0; i < blockCount; i++){
            if(isBallCollidedWithBlock(block[i])){
                block[i].setPosX(-100);
                motionY = -motionY;
            }
            if(axisAligned.intersectsWith(block[i].getAABB())){
                System.out.println("AXIS COLLIDED");
            }
        }

        if(isBallCollidedWithBumper(bumper)){
            motionY = -motionY;
        }
    }

    public void render(){
        renderGlobal.drawCircle(posX, posY, ballRadius, Color.RED.getRGB());
        renderGlobal.drawCircle(posX, posY, 2, Color.WHITE.getRGB());
        renderGlobal.drawRectWithColor(posX - ballRadius, posY - ballRadius, ballRadius*2, ballRadius*2, 0x1Affffff);
    }

    //todo fix collisions (ball center IN CENTER)
    private boolean isBallCollidedWithBlock(Block block){

        if(posY + ballRadius >= block.posY && posY - ballRadius < block.posY + block.blockHeight && posX + ballRadius > block.posX && posX - ballRadius < block.posX + block.blockWidth){
            return true;
        }else{
            return false;
        }

    }
    /*bumper collision check*/
    private boolean isBallCollidedWithBumper(Bumper bumper){
        if(posX + ballRadius >= bumper.getPosX() && posX - ballRadius <= bumper.getPosX() + bumper.getBumperWidth() && posY - ballRadius <= bumper.getPosY() + bumper.getBumperHeight() && posY + ballRadius >= bumper.getPosY()){
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
    private AABB axisAligned;


}
