import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;



public class EnemyProjectile {

    public int enemyProjectileX1;
    public int enemyProjectileX2;
    public int enemyProjectileY1;
    public int enemyProjectileY2;
    public int getProjectileX() { return (int) enemyProjectileX1; }
    private static Image projectileImage;
    

    public EnemyProjectile(int enemyX1,int enemyY1) {
        enemyProjectileX1 = enemyX1 ;
        enemyProjectileY1 = enemyY1 ;
        enemyProjectileX2 = enemyProjectileX1 + 10;
        enemyProjectileY2 = enemyProjectileY1 + 50;
    }

    
    void draw(Graphics g) {
        projectileImage = new ImageIcon("Space Invader/src/assets/aim9.png").getImage();
        
        enemyProjectileY1 = enemyProjectileY1 + 3;
        enemyProjectileY2 = enemyProjectileY2 + 3;
        

        g.drawImage(projectileImage, enemyProjectileX1, enemyProjectileY1, 10 , 50 ,null);
        
    }

    //checks collisions on X axis
    public boolean getCollisionProjectileBarrierX(Barrier barrier) {
        return (enemyProjectileX2 > barrier.barrierX1 && enemyProjectileX2 < barrier.barrierX1) || 
        (enemyProjectileX1 > barrier.barrierX1 && enemyProjectileX1 < barrier.barrierX2);
    }

    // checks collisions on Y axis
    public boolean getCollisionProjectileBarrierY(Barrier barrier) {
        return(enemyProjectileY1 > barrier.barrierY1 && enemyProjectileY1 < barrier.barrierY2);
    }
}

   