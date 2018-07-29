package examples;

import com.harium.etyl.Etyl;
import com.harium.etyl.commons.context.Application;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.Button;
import com.harium.etyl.ui.CheckBox;
import com.harium.etyl.ui.UI;

public class ExampleUI extends Etyl {

    public ExampleUI() {
        super(800, 600);
    }

    public static void main(String[] args) {
        ExampleUI example = new ExampleUI();
        example.init();
    }

    @Override
    public Application startApplication() {
        // Setup
        addModule(UI.getInstance());
        return new UIApplication(w, h);
    }

    public class UIApplication extends Application {

        public UIApplication(int w, int h) {
            super(w, h);
        }

        @Override
        public void load() {
            UI.add(new Button(40, 50, 300, 50));
            UI.add(new Button(40, 120, 300, 50));
            UI.add(new CheckBox(40, 180, 50, 50));
            UI.add(new CheckBox(100, 180, 50, 50).checked(true));
        }

        @Override
        public void draw(Graphics graphics) {

        }
    }
}
