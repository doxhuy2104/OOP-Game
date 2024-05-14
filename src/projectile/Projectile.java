package projectile;

import main.GamePanel;
import main.MouseClickListener;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Projectile {
    GamePanel gp;
    MouseClickListener mC;
    BufferedImage fire;

    public Projectile(GamePanel gp) {
        this.gp = gp;
        getProjectileImage();
    }

    public void getProjectileImage() {
        try {
            fire = ImageIO.read(getClass().getResourceAsStream("projectile/fireballSm.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fireBall() {

    }
}
