public class LevelRepeater extends LevelStarter
{
    int counter;
    int released;

    int enemy;
    int frequency;
    int total;

    public LevelRepeater( int enemy, int frequency, int total )
    {
        this.enemy = enemy;
        this.frequency = frequency;
        this.total = total;
    }

    public void start()
    {
        counter = 0;
        released = 0;
    }

    public Enemy tick()
    {
        counter++;
        if( counter == frequency ) {
            released++;
            counter = 0;
            return new Enemy( enemy );
        }
        return null;
    }

    public boolean isDone()
    {
        return released >= total;
    }
}
