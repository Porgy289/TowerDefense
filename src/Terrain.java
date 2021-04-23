import java.awt.*;


public abstract class Terrain
{
    public abstract void draw( Graphics2D g );
    public abstract void move( Enemy b );
    public abstract void place( Enemy b );
    public abstract boolean isPast( Enemy b );
    public abstract boolean isFurther( Enemy a, Enemy b );
    public abstract boolean canPlace( Towers t );
}

