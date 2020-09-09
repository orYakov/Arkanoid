package gameutils;

import biuoop.DrawSurface;
import biuoop.GUI;
import interfaces.Animation;

/**
 * an AnimationRunner class.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private biuoop.Sleeper sleeper = new biuoop.Sleeper();
    private double dt;

    /**
     * AnimationRunner - constructor.
     *
     * @param gui             gui
     * @param framesPerSecond the number of frames per second
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
        this.dt = 1.0 / this.framesPerSecond;
    }

    /**
     * run - run the animation.
     *
     * @param animation - the animation to run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();

            animation.doOneFrame(d, dt);

            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * getGui.
     *
     * @return this Gui
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * getDt.
     *
     * @return this dt.
     */
    public double getDt() {
        return this.dt;
    }

    /**
     * getFramesPerSecond.
     *
     * @return this getFramesPerSecond.
     */
    public int getFramesPerSecond() {
        return this.framesPerSecond;
    }
}
