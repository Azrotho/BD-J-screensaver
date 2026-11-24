package fr.azrotho;

import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;

import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import java.awt.*;

public class Main extends Container implements Xlet {

    private HScene scene;
    private Font font;
    private Image image;
    private int x = -6000;

    public void initXlet(XletContext context) {
        setSize(1280, 720);
        try {
            image = AssetLoader.loadImage("img/blurayblue.png");
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
        float milis = 0.0f;
        long lastTime = System.currentTimeMillis();
        while(true) {
            if(milis >= (1.0f / 60.0f)) {
                milis = 0.0f;
                x++;
                repaint();
                lastTime = System.currentTimeMillis();
            }
            milis += (System.currentTimeMillis() - lastTime) / 1000.0f;

        }
    }

    public void pauseXlet() {
        setVisible(false);
    }

    public void destroyXlet(boolean unconditional) {
        AssetLoader.clearImageCache();
        image = null;
        if (scene != null) {
            scene.remove(this);
            scene = null;
        }
    }

    public void paint(Graphics g) {
        g.setColor(new Color(0x000000));
        g.fillRect(0, 0, getWidth(), getHeight());
        if (image != null) {
            int size = 200;
            //int x = (getWidth() - size) / 2;
            int y = (getHeight() - size) / 2;
            g.drawImage(image, (this.x / 40), y, size, size, this);
        }
        g.setColor(new Color(0xffffff));
        if (font == null) {
            font = new Font(null, Font.PLAIN, 30);
        }
        g.setFont(font);
        g.drawString("Hello World!!!! :333 " + String.valueOf(x), 50, 50);
    }
}
