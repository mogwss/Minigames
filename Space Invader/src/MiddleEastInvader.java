import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class MiddleEastInvader extends JPanel implements KeyListener, Runnable {

    private int friendlyX = 0;
    private int friendlyY = 0;
    private List<Enemy> enemies = new ArrayList<>();
    private List<Projectile> projectiles = new ArrayList<>();
    public boolean isCollision = false;
    private boolean aIsPressed = false;
    private boolean dIsPressed = false;
    private boolean spaceIsPressed = false;
    private int shotCooldown = 0;
    private List<Enemy> enemiesCopy = new ArrayList<>();
    private List<Projectile> projectilesCopy = new ArrayList<>();
    private List<Barrier> barriers = new ArrayList<>();
    private List<Barrier> barriersCopy = new ArrayList<>();
    private List<Explosion> activeExplosions = new ArrayList<>();
    private List<EnemyProjectile> enemyProjectiles = new ArrayList<>();
    private List<EnemyProjectile> enemyProjectilesCopy = new ArrayList<>();
    private int enemySize = 50;
    private Image friendlyImage;
    private int barrierHealth = 10;
    private int level = 0;
    private int oldScore = 55 * level;
    private Image background = new ImageIcon("Space Invader/src/assets/oilfires_resized.png").getImage();
    private int enemyStartPoint = 0;
    private double shotProbability;

    public MiddleEastInvader() {
        this.setFocusable(true);
        this.addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, 1300, 750, null);
        int width = this.getWidth();
        int height = this.getHeight();

        friendly(g, width, height);
        highscore(g);

        // redraws enemies from list
        for (Enemy enemy : enemies) {
            enemy.draw(g, width, height);

            shotProbability = Math.random() * 25000;
            if (shotProbability <= 1) {
                EnemyProjectile missile = new EnemyProjectile(enemy.getEnemyX() + 10, enemy.getEnemyY());
                enemyProjectiles.add(missile);
            }
        }

        for (EnemyProjectile missile : enemyProjectiles) {
            missile.draw(g);

        }

        // redraws projectiles from list
        for (Projectile projectile : projectiles) {
            projectile.draw(g);
        }

        for (Barrier barrier : barriers) {
            barrier.draw(g, width, height, friendlyY);
        }

        for (Explosion explosion : activeExplosions) {
            explosion.draw(g);
        }

        // checks collisions
        for (Projectile projectile : projectiles) {
            for (Enemy enemy : enemies) {
                if (projectile.getCollisionProjectileEnemyX(enemy) && projectile.getCollisionProjectileEnemyY(enemy)) {
                    isCollision = true;
                    enemiesCopy.add(enemy);
                    projectilesCopy.add(projectile);

                    Explosion explosion = new Explosion(enemy.getEnemyX(), enemy.getEnemyY());
                    activeExplosions.add(explosion);
                }

            }

            if (projectile.projectileY1 < -20) {
                projectilesCopy.add(projectile);
            }

        }

        for (EnemyProjectile missile : enemyProjectiles) {

            if ((missile.enemyProjectileY1) > height + 20) {
                enemyProjectilesCopy.add(missile);
            }
        }

        for (Barrier barrier : barriers) {
            for (Projectile projectile : projectiles) {
                if (projectile.getCollisionProjectileBarrierX(barrier)
                        && projectile.getCollisionProjectileBarrierY(barrier)) {
                    projectilesCopy.add(projectile);
                    barrierHealth = barrierHealth - 1;
                    if (barrierHealth < 1) {
                        barriersCopy.add(barrier);
                        barrierHealth = 10;

                    }

                    Explosion explosion = new Explosion((projectile.getProjectileX()) - 20,
                            (barrier.getBarrierY()) + (barrier.getBarrierHeight() / 2));
                    activeExplosions.add(explosion);

                }

            }
        }

        enemies.removeAll(enemiesCopy);
        projectiles.removeAll(projectilesCopy);
        barriers.removeAll(barriersCopy);
        enemyProjectiles.removeAll(enemyProjectilesCopy);

        if (enemies.size() == 0) {
            drawEnemies();
        }

        if (shotCooldown != 0) {
            shotCooldown--;
        }
        if (spaceIsPressed) {

            if (shotCooldown == 0) {
                projectiles.add(new Projectile((friendlyX) + 23, friendlyY));
                shotCooldown = 70;
            }
        }

        if (aIsPressed) {
            friendlyX -= 2;
        }

        if (dIsPressed) {
            friendlyX += 2;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();

        if (key == 'a') {
            aIsPressed = true;
        }
        if (key == 'd') {
            dIsPressed = true;
        }
        if (key == ' ') {
            spaceIsPressed = true;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char key = e.getKeyChar();
        if (key == 'a') {
            aIsPressed = false;
        }
        if (key == 'd') {
            dIsPressed = false;
        }
        if (key == ' ') {
            spaceIsPressed = false;
        }
    }

    public void run() {
        int width = this.getWidth();
        int height = this.getHeight();

        drawEnemies();

        int barrierWidth = 200; // size of the barrier
        int barrierHeight = 50;
        int barrierNumber = 4; // number of barriers

        // Calculate total space occupied by all barriers and spaces between them
        int totalBarrierWidth = barrierNumber * barrierWidth;
        int totalSpaceWidth = width - totalBarrierWidth;
        int barrierSpaceX = totalSpaceWidth / (barrierNumber + 1); // space between barriers and edges

        for (int i = 0; i < barrierNumber; i++) {
            int barrierX = barrierSpaceX * (i + 1) + (barrierWidth * i);
            int barrierY = (((width / 2) + 50) + friendlyY) / 2 + barrierHeight + 20; // height of barriers
            Barrier newBarrier = new Barrier(barrierX, barrierY);
            barriers.add(newBarrier);
        }

        friendlyX = (getWidth() / 2) - 125 / 2;
        friendlyY = getHeight() - (int) (getHeight() / 7.5);
        while (true) {
            try {
                revalidate();
                // System.out.println(".");
                repaint();
                // how many refreshes per second
                Thread.sleep(10);
                // System.out.println(this.getWidth() + " x " + this.getHeight());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    // draws a circle in red
    public void friendly(Graphics g, int width, int height) {
        friendlyImage = new ImageIcon("src/assets/MiddleEastInvaderF15.png").getImage();
        int xPosition = friendlyX;
        int yPosition = height - (int) (height / 5);
        g.drawImage(friendlyImage, xPosition, yPosition, 125, 125, null);
    }

    public void highscore(Graphics g) {
        int score = enemiesCopy.size();
        g.setColor(Color.WHITE);
        g.drawString("Highscore: " + score, 20, 20);
    }

    public void drawEnemies() {
        // draws 10 enemies
        int width = this.getWidth();
        int height = this.getHeight();

        // System.out.println( width + " x " + height);
        int enemySpaceX = 50; // Horizontal space between enemies
        int enemySpaceY = 25; // Vertical space between enemies
        int enemyWidth = enemySize; // Assume width of the enemy block

        int totalEnemyWidth = 11 * (enemyWidth + enemySpaceX) - enemySpaceX;
        int sideMarginX = (width - totalEnemyWidth) / 2;

        for (int j = 1; j <= 5; j++) {
            for (int i = 1; i <= 11; i++) {
                int enemyX = sideMarginX + (enemyWidth + enemySpaceX) * (i - 1);
                int enemyY = 0 - ((enemySpaceY + enemyWidth) * j);
                Enemy newEnemy = new Enemy(enemyX, enemyY);
                enemies.add(newEnemy);
            }
        }
    }

}
