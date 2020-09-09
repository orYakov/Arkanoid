package objects;

/**
 * a Counter class.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public class Counter {
    private int counter = 0;

    /**
     * construct a Counter instance.
     */
    public Counter() {

    }

    /**
     * increase - add number to current count.
     *
     * @param number the number to add.
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * decrease - subtract number from current count.
     *
     * @param number the number to subtract.
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * getValue.
     *
     * @return get current count.
     */
    public int getValue() {
        return this.counter;
    }
}
