public abstract class Deployables
{
    public Deployables( double x, double y, double width )
    {
        this.x = x;
        this.y = y;
        this.width = width;
    }

    public int getX() { return (int)x; }
    public int getY() { return (int)y; }
    public int getWidth() { return (int)width; }

    public boolean intersects( Deployables other )
    {
        return width + other.width > distanceTo( other );
    }

    public double distanceTo( Deployables other )
    {
        double dx = x - other.x;
        double dy = y - other.y;
        return Math.sqrt( dx * dx + dy * dy );
    }

    public double width;
    public double x;
    public double y;

}
