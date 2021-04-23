import java.awt.*;
import java.util.ArrayList;


public abstract class Towers extends Deployables
{
    public Towers( int x, int y, int range, int frequency )
    {
        super( x, y, 10 );
        this.range = range;
        this.frequency = frequency;
        this.counter = 0;
    }

    public void tick( Terrain map, ArrayList<Enemy> enemies, ArrayList<Projectile> projectiles)
    {
        if( counter > 0 )
        {
            counter--;
        }
        Enemy closest = getFurthestEnemyInRange( map, enemies );
        if( closest != null && canShoot() )
        {
            counter = frequency;
            shoot( projectiles, closest );
        }
    }

    public Enemy getFurthestEnemiesInRange( Terrain map, ArrayList<Enemy> enemies )
    {
        Enemies toShoot = null;
        for( Enemies b : enemy )
        {
            double dx = x - b.x;
            double dy = y - b.y;
            if( Math.sqrt( dx*dx + dy*dy) <= range )
            {
                if( toShoot == null || map.isFurther( b, toShoot ) )
                {
                    toShoot = b;
                }
            }
        }
        return toShoot;
    }

    public boolean canShoot()
    {
        return counter == 0;
    }

    public abstract void shoot( ArrayList<Projectile> projectiles, Enemies target );

    public int getRange()
    {
        return (int)range;
    }

    public abstract void draw( Graphics2D g );

    protected double range;
    protected int frequency;
    protected int counter;
}
