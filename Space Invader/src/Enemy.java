import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Enemy {

    public double enemyX1;
    public double enemyY1;
    public double enemyX2;
    public double enemyY2;
    public double enemyMovementX = 0;
    public double enemyMovementY = 0;
    public boolean startMovingX = false;
    public int enemySize = 50;
    public static Image enemyImage;
    public int getEnemyX() { return (int) enemyX1; }
    public int getEnemyY() { return (int) enemyY1; }

    public Enemy(int enemyX, int enemyY) {

        enemyX1 = enemyX;
        enemyY1 = enemyY;
        enemyX2 = enemyX1 + enemySize;
        enemyY2 = enemyY1 + enemySize;

    }

    void draw(Graphics g, int width, int height) {

        enemyMovementY = enemyMovementY +1;
        if(enemyMovementY <= ((height / 2)+50)){
            enemyY1 = enemyY1 +1;
            enemyY2 = enemyY2 +1;
        }
        else{
            startMovingX = true;   
        }


        if(startMovingX){
        enemyMovementX = enemyMovementX + 1;
        if (enemyMovementX <= 300) {
            enemyX1 = enemyX1 += 0.3;
            enemyX2 = enemyX2 += 0.3;
        }
        if ((enemyMovementX > 600) && (enemyMovementX < 900)) {
            enemyX1 = enemyX1 -= 0.3;
            enemyX2 = enemyX2 -= 0.3;
        }
        if ((enemyMovementX > 1200) && (enemyMovementX < 1500)) {
            enemyX1 = enemyX1 -= 0.3;
            enemyX2 = enemyX2 -= 0.3;
        }
        if ((enemyMovementX > 1800) && (enemyMovementX < 2100)) {
            enemyX1 = enemyX1 += 0.3;
            enemyX2 = enemyX2 += 0.3;
        }
        if (enemyMovementX == 2400) {
            enemyMovementX = 0;
        }

        enemyMovementY = enemyMovementY +1;
        if(enemyMovementY <= ((height / 2)+50)){
            enemyY1 = enemyY1 +1;
            enemyY2 = enemyY2 +1;
        }
        }



        enemyImage = new ImageIcon("Space Invader/src/assets/MiddleEastInvaderSU25.png").getImage();

        g.drawImage(enemyImage, (int) enemyX1, (int) enemyY1, enemySize, enemySize, null);

    }

}
