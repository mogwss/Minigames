import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Barrier {


    public Image barrierImage;
    public int barrierX1;
    public int barrierY1;
    public int barrierX2;
    public int barrierY2;
    public int barrierHealth;
    public int barrierHeight = 65;
    public int barrierWidth = 130;
    public int barrierNumber = 4;
    public int barrierInterval;
    public int getBarrierX() { return (int) barrierX1; }
    public int getBarrierY() { return (int) barrierY1; }
    public int getBarrierWidth() { return (int) barrierWidth; }
    public int getBarrierHeight() { return (int) barrierHeight; }

    public Barrier(int barrierX, int barrierY) {
        barrierX1 = barrierX;
        barrierY1 = barrierY;
        barrierX2 = barrierX1 + barrierWidth;
        barrierY2 = barrierY1 + barrierHeight;
    }
   
    void draw(Graphics g, int width, int height, int friendlyY){
        barrierImage = new ImageIcon("Space Invader/src/assets/MiddleEastInvaderBurke.png").getImage();

        g.drawImage(barrierImage, barrierX1, barrierY1, barrierWidth , barrierHeight, null);
    }






}
