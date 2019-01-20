# etyl-ui
Etyl's UI module

This is a draft module to add UI fetures to Etyl.

Java's default UI hurts my eyes so badly that I start creating a new UI to be used over Etyl.
It is obviously not finished but it suits my needs most of the time.

## Core Features
- Easy to customize
- UI Theme changes on the fly
- Internationalization on the fly

## Maven
```
<dependency>
    <groupId>com.harium.etyl</groupId>
    <artifactId>ui</artifactId>
    <version>0.2.2</version>
</dependency>
```

## Minimal Example
```
import com.harium.etyl.Etyl;
import com.harium.etyl.commons.context.Application;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.Button;
import com.harium.etyl.ui.UI;
import com.harium.etyl.ui.theme.base.BaseArrowTheme;
import com.harium.etyl.ui.theme.base.BaseTheme;

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
        UI.setTheme(new BaseTheme());
        UI.setArrowTheme(new BaseArrowTheme());
        addModule(UI.getInstance());
        return new UIApplication(w, h);
    }

    public class UIApplication extends Application {

        public UIApplication(int w, int h) {
            super(w, h);
        }

        @Override
        public void load() {
            // Add buttons without label
            UI.add(new Button(40, 50, 300, 50));
            UI.add(new Button(40, 120, 300, 50));
        }

        @Override
        public void draw(Graphics graphics) {

        }
    }
}
```
