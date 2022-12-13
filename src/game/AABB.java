package game;

public class AABB {

    public AABB(float miX, float miY, float maX, float maY)
    {
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

    private float minX;
    private float minY;
    private float maxX;
    private float maxY;
}
