import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;



public class Projectile {

    public int projectileX1;
    public int projectileX2;
    public int projectileY1;
    public int projectileY2;
    public int getProjectileX() { return (int) projectileX1; }
    private static Image projectileImage;
    

    public Projectile(int friendlyCircleX,int friendlyCircleY) {
        projectileX1 = friendlyCircleX + 36;
        projectileY1 = friendlyCircleY ;
        projectileX2 = projectileX1 + 10;
        projectileY2 = projectileY1 + 50;
    }

    
    void draw(Graphics g) {
        projectileImage = new ImageIcon("Space Invader/src/assets/MiddleEastInvaderAIM120.png").getImage();
        
        projectileY1 = projectileY1 - 5;
        projectileY2 = projectileY2 - 5;
        

        g.drawImage(projectileImage, projectileX1, projectileY1, 10 , 50 ,null);
        
    }

    //checks collisions on X axis
    public boolean getCollisionProjectileEnemyX(Enemy enemy) {
        return (projectileX2 > enemy.enemyX1 && projectileX2 < enemy.enemyX2) || 
        (projectileX1 > enemy.enemyX1 && projectileX1 < enemy.enemyX2);
    }

    // checks collisions on Y axis
    public boolean getCollisionProjectileEnemyY(Enemy enemy) {
        return(projectileY1 > enemy.enemyY1 && projectileY1 < enemy.enemyY2);
    }

    //checks collisions on X axis
    public boolean getCollisionProjectileBarrierX(Barrier barrier) {
        return (projectileX2 > barrier.barrierX1 && projectileX2 < barrier.barrierX1) || 
        (projectileX1 > barrier.barrierX1 && projectileX1 < barrier.barrierX2);
    }

    // checks collisions on Y axis
    public boolean getCollisionProjectileBarrierY(Barrier barrier) {
        return(projectileY1 > barrier.barrierY1 && projectileY1 < barrier.barrierY2);
    }
}

   