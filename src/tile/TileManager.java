package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager extends Tile {
    GamePanel gp;

    public Tile[] tile;

    public int mapTileNum[][];
    public int currentSprite = 0;

    public static int abs(int x) {
        return x >= 0 ? x : -x;
    }

    public TileManager(GamePanel gp) {
        this.gp = gp;
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        tile = new Tile[100];

        getTileImage();
        loadMap("/maps/map.txt");
    }

    public void getTileImage() {
        try {


            map = ImageIO.read(getClass().getResourceAsStream("/maps/Coremap.png"));

            for (int i = 0; i < 100; i++) {
                tile[i] = new Tile();
            }
            tile[1].collision = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String map) {
        try {
            InputStream is = getClass().getResourceAsStream(map);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(",");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
        }
    }

    public void draw(Graphics2D g2) {
        int screenX = -gp.player.x + gp.player.screenX;
        int screenY = -gp.player.y + gp.player.screenY;
        g2.drawImage(map, screenX, screenY, gp.scale * map.getWidth(), gp.scale * map.getHeight(), null);
        g2.setColor(Color.WHITE);
    }
}