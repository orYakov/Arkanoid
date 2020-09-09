package gameutils;

import backgrounds.MenuBackGround;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Menu;
import interfaces.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * a MenuAnimation class.
 *
 * @param <T>
 */
public class MenuAnimation<T> implements Menu<T> {
    private String title;
    private KeyboardSensor ks;
    private List<String> keys;
    private List<String> messages;
    //private List<T> results;
    private boolean stop;
    private T status;
    private Map<String, T> keyResultMap;
    private AnimationRunner runner = null;


    /**
     * constructor.
     *
     * @param menuTitle the menu title.
     * @param keyboard  the keyboard sensor.
     * @param ar        AnimationRunner
     */
    public MenuAnimation(String menuTitle, KeyboardSensor keyboard, AnimationRunner ar) {
        this.title = menuTitle;
        this.ks = keyboard;
        this.keys = new ArrayList<>();
        this.messages = new ArrayList<>();
        //this.results = new ArrayList<>();
        this.keyResultMap = new TreeMap<>();
        this.runner = ar;
    }

    /**
     * addSelection.
     *
     * @param key       the key to wait for.
     * @param message   the line to print.
     * @param returnVal what to return.
     */
    public void addSelection(String key, String message, T returnVal) {
        this.keys.add(key);
        this.messages.add(message);
        //this.results.add(returnVal);
        this.keyResultMap.put(key, returnVal);
    }

    /**
     * getStatus.
     *
     * @return returnVal.
     */
    public T getStatus() {
        return this.status;
    }

    /**
     * doOneFrame - treats the logic of one turn of the game.
     *
     * @param d  the DrawSurface to draw the animation on.
     * @param dt specifies the amount of seconds passed since the last call.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        MenuBackGround menuBackGround = new MenuBackGround(this.title, this.keys, this.messages);
        menuBackGround.drawOn(d);
        for (String key : this.keyResultMap.keySet()) {
            if (this.ks.isPressed(key)) {
                this.status = this.keyResultMap.get(key);
                this.stop = true;
                break;
            }
        }
    }

    /**
     * shouldStop - defines the stopping condition.
     *
     * @return true if the game should stop, and false otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * reset - reset the stop value to false.
     */
    public void reset() {
        this.stop = false;
    }

    /**
     * @param key     the key to wait for.
     * @param message the line to print.
     * @param subMenu the sub menu to add.
     */
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        /**
         * a RunSubMenuTask class.
         */
        class RunSubMenuTask implements Task<Void> {
            private AnimationRunner ar;
            private MenuAnimation<Task<Void>> subMenuMember;

            /**
             * RunSubMenuTask.
             * @param ar AnimationRunner
             * @param subMenu Menu<T>
             */
            public RunSubMenuTask(AnimationRunner ar, Menu<T> subMenu) {
                this.ar = ar;
                this.subMenuMember = (MenuAnimation<Task<Void>>) subMenu;
            }

            /**
             * run.
             *
             * @return null.
             */
            public Void run() {
                while (true) {
                    this.subMenuMember.reset();
                    ar.run(this.subMenuMember);
                    // wait for user selection
                    Task<Void> task = this.subMenuMember.getStatus();
                    task.run();
                    break;
                }
                return null;
            }
        }
        addSelection(key, message, (T) new RunSubMenuTask(this.runner, subMenu));
    }

    /**
     * setRunner.
     *
     * @param ar AnimationRunner.
     */
    public void setRunner(AnimationRunner ar) {
        this.runner = ar;
    }
}
