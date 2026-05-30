import java.awt.*;
import javax.swing.ImageIcon;


public class Explosion {

   private Image explosionTile1 = new ImageIcon("Space Invader/src/assets/explosion_tile_1.png").getImage();
   private Image explosionTile2 = new ImageIcon("Space Invader/src/assets/explosion_tile_2.png").getImage();
   private Image explosionTile3 = new ImageIcon("Space Invader/src/assets/explosion_tile_3.png").getImage();
   private Image explosionTile4 = new ImageIcon("Space Invader/src/assets/explosion_tile_4.png").getImage();
   private Image explosionTile5 = new ImageIcon("Space Invader/src/assets/explosion_tile_5.png").getImage();
   private Image explosionTile6 = new ImageIcon("Space Invader/src/assets/explosion_tile_6.png").getImage();
   private Image explosionTile7 = new ImageIcon("Space Invader/src/assets/explosion_tile_7.png").getImage();
   private Image explosionTile8 = new ImageIcon("Space Invader/src/assets/explosion_tile_8.png").getImage();
   private Image explosionTile9 = new ImageIcon("Space Invader/src/assets/explosion_tile_9.png").getImage();
   private Image explosionTile10 = new ImageIcon("Space Invader/src/assets/explosion_tile_10.png").getImage();
   private Image explosionTile11 = new ImageIcon("Space Invader/src/assets/explosion_tile_11.png").getImage();
   private Image explosionTile12 = new ImageIcon("Space Invader/src/assets/explosion_tile_12.png").getImage();
   private int explosionX;
   private int explosionY;
   private int frameCounter = 0;

    	public Explosion(int enemyX,int enemyY){
            explosionX = enemyX;
            explosionY = enemyY;
        }


        public void draw(Graphics g) {
                frameCounter++;
                if (frameCounter <= 5) g.drawImage(explosionTile1, explosionX, explosionY, 50, 50, null);
                else if (frameCounter <= 10) g.drawImage(explosionTile2, explosionX, explosionY, 50, 50, null);
                else if (frameCounter <= 15) g.drawImage(explosionTile3, explosionX, explosionY, 50, 50, null);
                else if (frameCounter <= 20) g.drawImage(explosionTile4, explosionX, explosionY, 50, 50, null);
                else if (frameCounter <= 25) g.drawImage(explosionTile5, explosionX, explosionY, 50, 50, null);
                else if (frameCounter <= 30) g.drawImage(explosionTile6, explosionX, explosionY, 50, 50, null);
                else if (frameCounter <= 35) g.drawImage(explosionTile7, explosionX, explosionY, 50, 50, null);
                else if (frameCounter <= 40) g.drawImage(explosionTile8, explosionX, explosionY, 50, 50, null);
                else if (frameCounter <= 45) g.drawImage(explosionTile9, explosionX, explosionY, 50, 50, null);
                else if (frameCounter <= 50) g.drawImage(explosionTile10, explosionX, explosionY, 50, 50, null);
                else if (frameCounter <= 55) g.drawImage(explosionTile11, explosionX, explosionY, 50, 50, null);
                else if (frameCounter <= 60) g.drawImage(explosionTile12, explosionX, explosionY, 50, 50, null);
            }
}