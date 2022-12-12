package game;

import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

public class RenderGlobal {

    public void drawRect(float x, float y, float width, float height) {
        glPushMatrix();

        glTranslatef(x, y, 0);
        glBegin(GL_QUADS);
        glVertex2f(0, 0);
        glVertex2f(0, height);
        glVertex2f(width, height);
        glVertex2f(width, 0);
        glEnd();
        glPopMatrix();
    }

    public void drawRectWithColor(float x, float y, float width, float height, int color) {

        float alpha = ((color >> 24) & 0xff) / 255F;
        float red = ((color >> 16) & 0xff) / 255F;
        float green = ((color >> 8) & 0xff) / 255F;
        float blue = (color & 0xff) / 255F;

        glPushMatrix();
        glColor4f(red, green, blue, alpha);
        glTranslatef(x, y, 0);
        glBegin(GL_QUADS);
        glVertex2f(0, 0);
        glVertex2f(0, height);
        glVertex2f(width, height);
        glVertex2f(width, 0);
        glEnd();
        glPopMatrix();
    }


    public void drawCircle(double x, double y, double radius, int color) {
        // 0xAARRGGBB
        float alpha = ((color >> 24) & 0xff) / 255F;
        float red = ((color >> 16) & 0xff) / 255F;
        float green = ((color >> 8) & 0xff) / 255F;
        float blue = (color & 0xff) / 255F;
        glEnable(GL11.GL_BLEND);
        glDisable(GL11.GL_TEXTURE_2D);
        glEnable(GL11.GL_POLYGON_SMOOTH);
        glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        glHint(GL11.GL_POLYGON_SMOOTH_HINT, GL11.GL_NICEST);
        glColor4f(red, green, blue, alpha);
        glBegin(GL_POLYGON);
        for (int i = 0; i <= 360; i++) {
            double x2 = Math.sin(((i * 3.141526D) / 180)) * radius;
            double y2 = Math.cos(((i * 3.141526D) / 180)) * radius;
            glVertex3d(x+x2, y+y2, 0);
        }
        glEnd();
        glDisable(GL_POLYGON_SMOOTH);
        glEnable(GL_TEXTURE_2D);
        glDisable(GL_BLEND);
    }
}
