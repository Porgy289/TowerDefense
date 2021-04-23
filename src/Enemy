import java.awt.*;


public class Enemy extends Deployables
{

    public int rank;

    public Enemy( int rank )
    {
        super( 0, 0, 10 );
        this.rank = rank;
    }

   
    public boolean getHit()
    {
        rank--;
        return rank == 0;
    }

    public double getSpeed()
    {
        return rank;
    }

    public void draw( Graphics2D g )
    {
        Color color = Color.WHITE;
        switch ( rank )
        {
            case 1:
                color = Color.RED;
                break;
            case 2:
                color = Color.BLUE;
                break;
            case 3:
                color = Color.GREEN;
                break;
            case 4:
                color = Color.YELLOW;
                break;
            case 5:
                color = Color.ORANGE;
                break;
            case 6:
                color = Color.BLACK;
                break;
            case 7:
                color = Color.WHITE;
                break;
        }
        g.setColor( color );
        g.fillOval(
                getX() - getWidth(),
                getY() - getWidth(),
                getWidth() * 2,
                getWidth() * 2 );
        g.setColor( Color.WHITE );
        g.drawOval(
                getX() - getWidth(),
                getY() - getWidth(),
                getWidth() * 2,
                getWidth() * 2 );
    }
}

