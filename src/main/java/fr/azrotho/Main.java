package fr.azrotho;

import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;

import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class Main extends Container implements Xlet {

    private HScene scene;
    private Font font;
    private Image blue;
    private Image green;
    private Image orange;
    private Image rose;

    private Image currentImage;


    private int size = 200;
    private int leftBound = 0;
    private int rightBound = 1280-size;
    private int topBound = 0;
    private int bottomBound = 720-size;

    private float x = 0.0f; // A random
    private float y = 0.0f; // A random
    private float dirX = 1.0f; // A random
    private float dirY = 1.0f; // A random

    private float speed = 0.15f;

    public void initXlet(XletContext context) {
        setSize(1280, 720);
        try {
            blue = AssetLoader.loadImage("img/blurayblue.png");
            green = AssetLoader.loadImage("img/bluraygreen.png");
            orange = AssetLoader.loadImage("img/blurayorange.png");
            rose = AssetLoader.loadImage("img/blurayrose.png");
            currentImage = blue;
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        scene = HSceneFactory.getInstance().getDefaultHScene();
        scene.add(this);
        scene.validate();
    }

    public void startXlet() {
        setVisible(true);
        scene.setVisible(true);

        this.x = (float)(Math.random() * (rightBound - leftBound)) + leftBound;
        this.y = (float)(Math.random() * (bottomBound - topBound)) + topBound;
        //this.dirX = (float)(Math.random() * 2.0f - 1.0f);
        //this.dirY = (float)(Math.random() * 2.0f - 1.0f);

        float milis = 0.0f;
        long lastTime = System.currentTimeMillis();
        while(true) {
            if(milis >= (1.0f / 60.0f)) {
                milis = 0.0f;

                this.x = (this.x + dirX * speed);
                this.y = (this.y + dirY * speed);
                if(this.x < leftBound) {
                    this.x = leftBound;
                    this.dirX = -this.dirX;
                    changeImage();
                } else if(this.x > rightBound) {
                    this.x = rightBound;
                    this.dirX = -this.dirX;
                    changeImage();
                }
                if(this.y < topBound) {
                    this.y = topBound;
                    this.dirY = -this.dirY;
                    changeImage();
                } else if(this.y > bottomBound) {
                    this.y = bottomBound;
                    this.dirY = -this.dirY;
                    changeImage();
                }

                //x++;
                repaint();
                lastTime = System.currentTimeMillis();
            }
            milis += (System.currentTimeMillis() - lastTime) / 1000.0f;

        }
    }


    public void changeImage() {
        int color = (int)(Math.random() * 4.0f);
        switch(color) {
            case 0:
                currentImage = blue;
                break;
            case 1:
                currentImage = green;
                break;
            case 2:
                currentImage = orange;
                break;
            case 3:
                currentImage = rose;
                break;
        }
    }

    public void pauseXlet() {
        setVisible(false);
    }

    public void destroyXlet(boolean unconditional) {
        AssetLoader.clearImageCache();
        blue = null;
        if (scene != null) {
            scene.remove(this);
            scene = null;
        }
    }

    public void paint(Graphics g) {
        g.setColor(new Color(0x000000));
        g.fillRect(0, 0, getWidth(), getHeight());
        if (blue != null) {
            //int x = (getWidth() - size) / 2;
            g.drawImage(currentImage, (int) this.x, (int) this.y, this.size, this.size, this);
        }
        g.setColor(new Color(0xffffff));
        if (font == null) {
            font = new Font(null, Font.PLAIN, 30);
        }
        g.setFont(font);
        g.drawString("Hello World!!!! :333 " + String.valueOf(x), 50, 50);
    }
}
