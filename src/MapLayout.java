import java.awt.*;


public class MayLayout extends Terrain
{
    public void draw( Graphics2D g )
    {
        g.setColor( Color.GREEN );
        g.fillRect(0,0,400,400);
        g.setColor( Color.darkGray);
        g.fillRect(90,0,20,300);
        g.fillRect(90,290,310,20);
    }

    public void move( Enemy b )
    {

        if( b.y < 300 ) {
            b.y += b.getSpeed();
            return;
        }
        b.y = 300;
        b.x += b.getSpeed();
    }

    public void place( Enemy b )
    {
       b.x = 100;
       b.y = -20;
    }

    public boolean isPast( Enemy b )
    {
        return b.x >= 415;
    }

    public boolean isFurther( Enemy a, Enemy b )
    {
        if( a.y > b.y )
        {
            return true;
        }
        else if( a.y < b.y )
        {
            return false;
        }
        else
        {
            return a.x > b.x;
        }
    }

    public boolean canPlace( Towers t )
    {
        if( t.getX() + t.getWidth() <= 90 )
        {
            return true;
        }
        if( t.getX() - t.getWidth() < 110 )
        {
            return t.getY() - t.getWidth() >= 310;
        }
        return t.getY() - t.getWidth() >= 310 || t.getY() + t.getWidth() <= 290;
    }
}
