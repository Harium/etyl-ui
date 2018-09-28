package examples;

import com.harium.etyl.Etyl;
import com.harium.etyl.commons.context.Application;
import com.harium.etyl.commons.graphics.Color;
import com.harium.etyl.core.graphics.Graphics;

public class Main extends Etyl {

    public Main() {
        super(800, 600);
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.setTitle("Etyl");
        app.init();
    }

    @Override
    public Application startApplication() {
        return new HelloWorld(w, h);
    }

    public class HelloWorld extends Application {
        public HelloWorld(int w, int h) {
            super(w, h);
        }

        @Override
        public void load() {}

        @Override
        public void draw(Graphics g) {
            g.setColor(Color.GREEN_ETYL);
            g.fillRect(0, 0, w, h);
        }
    }
}
