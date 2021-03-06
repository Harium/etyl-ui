package com.harium.etyl.ui;

import com.harium.etyl.commons.context.Context;
import com.harium.etyl.commons.event.*;
import com.harium.etyl.commons.module.Module;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.core.input.mouse.MouseStateChanger;
import com.harium.etyl.i18n.Language;
import com.harium.etyl.i18n.LanguageModule;
import com.harium.etyl.ui.base.BaseRadioButton;
import com.harium.etyl.ui.theme.ArrowDrawer;
import com.harium.etyl.ui.theme.ArrowTheme;
import com.harium.etyl.ui.theme.Theme;
import com.harium.etyl.ui.theme.ThemeManager;
import com.harium.etyl.ui.theme.base.BaseArrowTheme;
import com.harium.etyl.ui.theme.base.BaseTheme;
import com.harium.etyl.ui.theme.listener.ThemeListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UI implements Module, ThemeListener, MouseStateChanger {
    private static UI instance;

    private Context context;

    public int w, h;
    private int mx, my;

    // Accessibility Options
    public static boolean timerClick = false;
    public static int timedDelay = 3000;
    public static long currentTime = 0;
    public static float timerClickAmount = 0;

    // Useful in mobile environment
    private static boolean drawCursor = false;

    // Arrow
    public ArrowDrawer arrowDrawer;
    public ArrowTheme arrowTheme;

    private View focus = NULL_VIEW;

    // View above Mouse
    public View mouseOver = NULL_VIEW;
    private long mouseOverStart = 0;

    // Alt text
    private long altDelay = 2000;
    private boolean drawAlt = false;

    private boolean overClickable = false;

    public boolean needReload = false;

    private boolean locked = false;
    private boolean updating = false;
    private boolean updatingEvents = false;

    public static List<GUIEvent> guiEvents = new ArrayList<GUIEvent>();
    private static List<View> views = new ArrayList<View>();

    private UI() {
        super();
        BaseArrowTheme theme = new BaseArrowTheme();
        arrowDrawer = theme;
        arrowTheme = theme;
    }

    public static UI getInstance() {
        if (instance == null) {
            instance = new UI();

            // Set default theme
            ThemeManager.getInstance().setTheme(new BaseTheme());
            ThemeManager.getInstance().setArrowThemeListener(instance.arrowDrawer);
            ThemeManager.getInstance().setArrowTheme(instance.arrowTheme);
            ThemeManager.getInstance().setThemeListener(instance);
        }

        return instance;
    }

    public void updateGui(List<View> components) {
        Iterator<GUIEvent> eventIterator = guiEvents.listIterator();

        while (eventIterator.hasNext()) {
            GUIEvent event = eventIterator.next();

            dispatchEvent(event, components);
            // Better than clear (avoid clear unhandled events)
            eventIterator.remove();
        }
    }

    @Override
    public void updateGuiEvent(GUIEvent event) {
        dispatchEvent(event, views);
    }

    public void dispatchEvent(GUIEvent event, List<View> views) {
        Iterator<View> componentIterator = views.listIterator();

        while (componentIterator.hasNext()) {
            View component = componentIterator.next();
            dispatchEvent(event, component);
        }
    }

    private void dispatchEvent(GUIEvent event, View component) {
        component.updateEvent(event);

        Iterator<View> viewIterator = component.views.listIterator();
        while (viewIterator.hasNext()) {
            View child = viewIterator.next();
            dispatchEvent(event, child);
        }
    }

    public void updateMouseViews(PointerEvent event, List<View> views) {
        updatingEvents = true;

        Iterator<View> viewIterator = views.listIterator();
        while (viewIterator.hasNext()) {
            View view = viewIterator.next();
            boolean wasMouseOver = view.isMouseOver();
            //Update View
            GUIEvent guiEvent = view.updateMouse(event);
            updateEvent(view, guiEvent);

            if (view == mouseOver) {
                if (!view.isMouseOver()) {
                    // Lose Mouse Focus
                    mouseOver = NULL_VIEW;
                    drawAlt = false;
                } else {
                    // Check for alt
                    if (event.getTimestamp() - mouseOverStart > altDelay) {
                        drawAlt = true;
                        //show alt
                    }
                }

            } else if (!wasMouseOver && view.isMouseOver()) {
                mouseOverStart = event.getTimestamp();
                // Gain Mouse Focus
                mouseOver = view;
            }

            //Update Children
            if (guiEvent == GUIEvent.NONE) {
                updateMouseViews(event, view.getViews());
            }

        }
        updatingEvents = false;
    }

    private GUIEvent updateMouse(View view, PointerEvent event) {

        if (!view.isVisible()) {
            return GUIEvent.NONE;
        }

        GUIEvent result = view.updateMouse(event);

        if (GUIEvent.MOUSE_IN == result) {
            setMouseOver(view);
        } else if (GUIEvent.MOUSE_OUT == result) {
            resetMouseOver();
        } else if (GUIEvent.GAIN_FOCUS == result) {
            setFocus(view);
        } else if (GUIEvent.LOST_FOCUS == result) {
            removeFocus(view);
        } else if (result != GUIEvent.NONE && result != null) {
            updateEvent(view, result);
        }

        return GUIEvent.NONE;
    }

    private GUIEvent updateEvent(View view, GUIEvent lastEvent) {

        switch (lastEvent) {

            case GAIN_FOCUS:

                //Remove focus from last
                if (focus != null) {
                    focus.updateEvent(GUIEvent.LOST_FOCUS);
                }

                view.setOnFocus(true);

                focus = view;

                break;

            case LOST_FOCUS:

                if (view == focus) {
                    //TODO Mouse.loseFocus()
                    //events.add(new Event(Tecla.NONE, KeyState.LOSE_FOCUS));
                    //events.add(new Event(DeviceType.KEYBOARD, Tecla.NONE, KeyState.LOSE_FOCUS));

                    focus = NULL_VIEW;
                }

                break;

			/*case MOUSE_OVER:
            if(!mouseOver) {
				mouseOver = true;
				mouseOverClickable = true;
				//TODO componente.setMouseOver(true);
			}

			break;*/

			/*case MOUSE_OVER_UNCLICKABLE:
            if(!mouseOver) {
				mouseOver = true;
				mouseOverClickable = false;
			}			
			break;*/

            case MOUSE_OVER_WITH_FOCUS:

                //lastOver = componente;
                break;

            case NEXT_COMPONENT:
                System.out.println("LostFocus");
                view.updateEvent(GUIEvent.LOST_FOCUS);

                View next = view.getNext();
                if (next == NULL_VIEW) {
                    next = view.findNext();
                }

                focus = next;
                focus.updateEvent(GUIEvent.GAIN_FOCUS);

                break;

            case WINDOW_CLOSE:

                //TODO
                //((Window)componente.setClose(true));

                break;

			/*case ONCE:
            //this.event (param)
			event.setState(KeyState.PRESSED);

			//Prevent consume
			events.add(event);
			break;
			 */

            case UPDATE_MOUSE:
                updateMouse(view, new PointerEvent(MouseEvent.MOUSE_NONE, PointerState.MOVE, mx, my));
                break;

            case APPLICATION_CHANGED:
                //onClickListener.changeApplication();
                break;

            default:

			/*if(view.isMouseOver()) {
                view.update(GUIEvent.MOUSE_OUT);
			}*/

                break;
        }

        //view.setLastEvent(lastEvent);
        //view.update(lastEvent);
        //view.executeAction(lastEvent);

        return GUIEvent.NONE;
    }

    public void setMouseOver(View view) {
        if (mouseOver != null) {
            removeMouseOver(mouseOver);
        }

        mouseOver = view;
        mouseOver.mouseIn();

        overClickable = true;
    }

    public void resetMouseOver() {
        removeMouseOver(mouseOver);
        mouseOver = NULL_VIEW;
        overClickable = false;
    }

    private void setFocus(View view) {
        if (focus != NULL_VIEW) {
            removeFocus(focus);
        }
        focus = view;
        view.setOnFocus(true);
        view.updateEvent(GUIEvent.GAIN_FOCUS);
    }

    private void removeFocus(View view) {
        if (view == focus) {
            view.setOnFocus(false);
            view.updateEvent(GUIEvent.LOST_FOCUS);
            focus = NULL_VIEW;
        }
    }

    private void removeMouseOver(View view) {
        if (view == null)
            return;
        view.setMouseOver(false);
        view.updateEvent(GUIEvent.MOUSE_OUT);
    }

    public void drawCursor(Graphics g) {
        // Update Arraw Location
        arrowDrawer.setLocation(mx, my);
        if (!drawCursor) {
            return;
        }
        // Draw Timed Cursor Information
        if (timerClick && overClickable) {
            arrowDrawer.drawTimedIndicator(g, timerClickAmount);
        }
        // Draw the real cursor
        arrowDrawer.draw(g);
    }

    //Move to ArrowDrawer
    public void updateTimerClick(long now) {
        if (mouseOver != null) {

            if (currentTime == 0) {
                currentTime = now;
            }

            if (timerClick) {

                // If not ready calculate timerClockAmount
                if (currentTime + timedDelay < now) {
                    float diff = (now - currentTime);
                    timerClickAmount = diff / timedDelay;
                } else {
                    // Dispatch click Event
                    updateEvent(mouseOver, GUIEvent.MOUSE_LEFT_BUTTON_DOWN);
                    updateEvent(mouseOver, GUIEvent.MOUSE_LEFT_BUTTON_UP);

                    resetMouseOver();
                }
            }
        } else {
            if (timerClick) {
                // Reset time
                currentTime = 0;
                timerClickAmount = 0;
            }
        }
    }

    public void drawUIViews(Graphics g) {
        for (int i = 0; i < views.size(); i++) {
            View child = views.get(i);
            child.drawWithChildren(g);
            //if (drawAlt) {
            //   Draw child.getAlt();
            //}
        }
    }

    @Override
    public void updateMouse(PointerEvent event) {
        mx = event.getX();
        my = event.getY();
        updateMouseViews(event, views);
    }

    public void updateKeyboard(KeyEvent event) {
        //Only the focused component handles the keyboard
        if (focus != null) {
            GUIEvent focusEvent = focus.updateKeyboard(event);

            if (focusEvent != GUIEvent.NONE && focusEvent != null) {
                //TODO Update NExtComponent

                if (focusEvent == GUIEvent.NEXT_COMPONENT) {
                    View next = focus.findNext();

                    if (next != null) {
                        updateEvent(focus, focusEvent);
                        updateEvent(next, GUIEvent.GAIN_FOCUS);
                    }
                } else {
                    updateEvent(focus, focusEvent);
                }
            }
        }
    }

    @Override
    public void init(Context context) {
        this.context = context;
        this.w = context.getW();
        this.h = context.getH();
        ThemeManager.getInstance().getTheme().loadFonts();
    }

    @Override
    public void dispose(Context context) {
        views.clear();
    }

    @Override
    public void draw(Graphics g) {
        if (locked || isUpdating()) {
            return;
        }

        drawUIViews(g);

        //Draw Cursor
        if (context.isDrawCursor() && drawCursor) {
            drawCursor(g);
        }
    }

    @Override
    public void update(long now) {
        updating = true;

        if (needReload) {
            fastReload();
            needReload = false;
        }

        updateTimerClick(now);
        updateGui(views);

        updating = false;
    }

    private void fastReload() {
        locked = true;

        // Just Rebuild UI Components
        for (View view : views) {
            view.rebuild();
        }

        locked = false;
    }

    @Override
    public void updateTheme(Theme theme) {
        needReload = true;
    }

    public static void add(View view) {
        UI.views.add(view);
    }

    public static void addAll(List<View> views) {
        UI.views.addAll(views);
    }

    public static void remove(View view) {
        if (view instanceof BaseRadioButton) {
            RadioGroup.removeRadioButton((BaseRadioButton) view);
        }

        UI.views.remove(view);
    }

    public static void clear() {
        UI.views.clear();
        RadioGroup.clear();
    }

    private boolean isUpdating() {
        return updating || updatingEvents;
    }

    public static void changeLanguage(Language language) {
        LanguageModule.getInstance().changeLanguage(language);
        guiEvents.add(GUIEvent.LANGUAGE_CHANGED);
    }

    @Override
    public void changeMouseState(MouseState state) {
        arrowDrawer.changeMouseState(state);
    }

    public View getMouseOver() {
        return mouseOver;
    }

    public boolean isMouseOver() {
        return mouseOver != NULL_VIEW;
    }

    public static final View NULL_VIEW = new View() {
        @Override
        public GUIEvent updateKeyboard(KeyEvent event) {
            return null;
        }

        @Override
        public void updateEvent(GUIEvent event) {
        }
    };

    public static void showCursor() {
        drawCursor = true;
    }

    public static void hideCursor() {
        drawCursor = false;
    }

    public static void setTheme(Theme theme) {
        ThemeManager.getInstance().setTheme(theme);
    }

    public static void setArrowTheme(ArrowTheme arrowTheme) {
        ThemeManager.getInstance().setArrowTheme(arrowTheme);
    }
}
