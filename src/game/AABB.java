package game;

public class AABB {

    public AABB(float miX, float miY, float maX, float maY)
    {
        minX = miX;
        minY = miY;
        maxY = maY;
        maxX = maX;
    }

    public void setAABB(float miX, float miY, float maX, float maY){
        minX = miX;
        minY = miY;
        maxY = maY;
        maxX = maX;
    }

    public boolean intersectsWith(AABB aabb)
    {
        if((aabb.maxX >= minX || aabb.minX <= maxX) && (aabb.maxY >= minY || aabb.minY <= maxY))
        {
            return false;
        }
        return true;
    }

    public boolean intersects(AABB other) {
        if (maxX < other.minX) {
            return false;
        }

        if (this.maxY < other.minY) {
            return false;
        }

        if (this.minX > other.maxX) {
            return false;
        }

        if (this.minY > other.maxY) {
            return false;
        }

        return true;
    }


    public float minX;
    public float minY;
    public float maxX;
    public float maxY;
}
