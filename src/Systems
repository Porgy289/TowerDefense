import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class Systems extends JComponent
{
    private ArrayList<Enemy> enemyBuffer;
    private ArrayList<Enemy> enemies;
    private ArrayList<Projectile> projectiles;
    private ArrayList<Projectile> projectileBuffer;
    private ArrayList<Towers> tower;
    private ArrayList<Towers> towerBuffer;

    private int currentTowerPrice;
    private Tower currentTower;

    private static boolean runningLevel;
    private LevelChanger levelChanger;

    private Terrain map;

    int money;

    private int lives;

    int mouseX;
    int mouseY;

    public System()
    {
        enemyBuffer = new ArrayList<>();
        enemies = new ArrayList<>();
        towerBuffer = new ArrayList<>();
        tower = new ArrayList<>();
        projectileBuffer = new ArrayList<>();
        projectiles = new ArrayList<>();

        this.setPreferredSize( new Dimension( 400, 400 ) );

        this.map = new MapLayout();
        levelChanger = new LevelChanger();

        lives = 100;

        money = 50;

        runningLevel = false;

        this.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if( currentTower == null )
                {
                    return;
                }

                int x = e.getX();
                int y = e.getY();
                if( money >= currentTowerPrice && canPlaceCurrentTower() ) {
                    addTower( currentTower );
                    currentTower = null;
                    money -= currentTowerPrice;
                }
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter()
        {
            @Override
            public void mouseMoved(MouseEvent e)
            {
                if( currentTower != null )
                {
                    currentTower.x = e.getX();
                    currentTower.y = e.getY();
                }
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        this.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if( e.getKeyCode() == KeyEvent.VK_SPACE )
                {
                    runningLevel = true;
                    return;
                }
                if( e.getKeyCode() == KeyEvent.VK_P )
                {
                    if( money < 50 )
                    {
                        return;
                    }
                    currentTower = new PeaShooter(mouseX,mouseY);
                    currentTowerPrice = 50;
                    return;
                }
                if( e.getKeyCode() == KeyEvent.VK_S )
                {
                    if( money < 75 )
                    {
                        return;
                    }
                    currentTower = new SpreadShot(mouseX,mouseY);
                    currentTowerPrice = 75;
                    return;
                }
                currentTower = null;
            }
        });
    }

    public void addEnemy( Enemy b )
    {
        enemyBuffer.add( b );
    }
    public void addTower( Towers t )
    {
        towerBuffer.add( t );
    }
    public void addProjectile( Projectile b )
    {
        projectileBuffer.add( b );
    }

    private boolean canPlaceCurrentTower()
    {
        for( Towers t : towerBuffer )
        {
            if( t.intersects( currentTower ) )
            {
                return false;
            }
        }
        for( Towers t : tower )
        {
            if( t.intersects( currentTower ) )
            {
                return false;
            }
        }
        return map.canPlace( currentTower );
    }


    public void tick()
    {
        Enemy released = null;
        if( !levelChanger.currentLevelIsDone() )
        {
            released = levelChanger.tickCurrentLevel() ;
        }
        if( released != null )
        {
            map.place( released );
            enemies.add( released );
        }

        // Tick all of the towers
        for( Towers t : tower )
        {
            t.tick( map, enemies, projectiles );
        }

        // Move each Enemy
        for( Enemy b : enemies )
        {
            map.move( b );
        }

        // Tick each projectile
        for( int i = 0; i < projectiles.size(); ++i )
        {
            Projectile b = projectiles.get( i );
            if( b.getX() < 0 || b.getX() > 400 ||
                    b.getY() < 0 || b.getY() > 400 )
            {
                projectiles.remove( i-- );
            }
            else
            {
                b.tick();
            }
        }
        for( Projectile b : projectiles )
        {
            b.tick();
        }

        for( int i = 0; i < projectiles.size(); ++i )
        {
            for( int j = 0; j < enemies.size(); ++j )
            {
                if( projectiles.get(i).intersects(enemies.get(j)))
                {
                    projectiles.remove(i--);
                    if(enemies.get(j).getHit())
                    {
                        enemies.remove(j--);
                        money += 5;
                    }
                    break;
                }
            }
        }

        // Check for Enemies that made it past
        for( int i = 0; i < enemies.size(); ++i )
        {
            if( map.isPast( enemies.get(i) ) )
            {
                lives -= enemies.get(i).rank;
                enemies.remove( i-- );
            }
        }

        if( lives <= 0 )
        {
            lives = 0;
            System.out.println("You Lost :(");
            System.exit(2);
        }

        // CHECK IF YOU BEAT THE LEVEL
        //Peyton finish this
        if( enemies.size() == 0 && levelChanger.currentLevelIsDone() )
        {
            boolean beaten = levelChanger.nextLevel();
            runningLevel = false;
            projectiles.clear();
            if( beaten )
            {
                System.out.println( "You Win!" );
                System.exit(1);
            }
        }
    }

    public void paintSystem( Graphics gi )
    {
        // Synchronously move spawned bodies into the actual lists
        for( Enemy b : enemyBuffer )
        {
            enemies.add( b );
        }
        enemyBuffer.clear();

        for( Towers t : towerBuffer )
        {
            tower.add( t );
        }
        towerBuffer.clear();

        for( Projectile b : projectileBuffer )
        {
            projectiles.add( b );
        }
        projectileBuffer.clear();

        if(runningLevel)
        {
            // Tick the world
            tick();
        }

        // DRAW STUFF
        Graphics2D g = (Graphics2D)gi;
        map.draw( g );
        for( Enemy b : enemies )
        {
            b.draw( g );
        }
        for( Towers t : tower )
        {
            t.draw( g );
        }
        for( Projectile b : projectiles )
        {
            b.draw( g );
        }

        if( currentTower != null )
        {
            if( canPlaceCurrentTower() )
            {
                g.setColor( new Color( 0, 0, 0, 50 ) );
            }
            else
            {
                g.setColor( new Color( 255, 0, 0, 120 ) );
            }
            g.fillOval( currentTower.getX() - currentTower.getRange(),
                    currentTower.getY() - currentTower.getRange(),
                    currentTower.getRange() * 2,
                    currentTower.getRange() * 2);
            currentTower.draw( g );
        }

        g.setColor( Color.BLACK );

        if( currentTower == null )
        {
            g.drawString("[P]: $50 Pea Shooter", 10, 30 );
            g.drawString("[S]: $75 Spread Shot", 10, 50 );
        }

        g.drawString( "Lives: " + lives, 10, 395 );
        g.drawString( "Level: " + levelChanger.getLevelNumber(), 150, 395 );
        g.drawString( "Money: $" + money, 290, 395 );
        if( !runningLevel)
        {
            g.drawString( "Press [Space] to Start Next Level", 10, 370 );
        }
    }
}

