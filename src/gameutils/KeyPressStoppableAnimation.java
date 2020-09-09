package gameutils;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/**
 * a KeyPressStoppableAnimation class.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed = true;

    /**
     * KeyPressStoppableAnimation - constructor.
     *
     * @param sensor    a keyboard sensor.
     * @param key       - the key that orders to stop.
     * @param animation - the stoppable animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
    }

    /**
     * doOneFrame - treats the logic of one turn of the game.
     *
     * @param d  the DrawSurface to draw the animation on.
     * @param dt specifies the amount of seconds passed since the last call.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d, dt);
        if (this.sensor.isPressed(key)) {
            if (!this.isAlreadyPressed) {
                //this.animation.shouldStop();
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }

    /**
     * reset - reset the stop value to false.
     */
    public void reset() {
        this.stop = false;
    }

    /**
     * shouldStop - defines the stopping condition.
     *
     * @return true if the game should stop, and false otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
