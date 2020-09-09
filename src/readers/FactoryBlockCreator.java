package readers;

import geometry.Point;
import geometry.Rectangle;
import interfaces.BackGround;
import interfaces.BlockCreator;
import objects.BackGroundParser;
import objects.Block;

import java.util.Map;
import java.util.TreeMap;

/**
 * a FactoryBlockCreator class.
 */
public class FactoryBlockCreator implements BlockCreator {
    private int width;
    private int height;
    private Map<Integer, BackGround> backgrounds;
    private int hitPoint = 1;
    private Map<String, String> fillStartsMap;

    /**
     * FactoryBlockCreator - constructor.
     *
     * @param width         the block width.
     * @param height        the block height.
     * @param fillStartsMap Strings that start with "fill" and their  map values.
     * @param hitPoint      the block hit point.
     */
    public FactoryBlockCreator(int width, int height, Map<String, String> fillStartsMap, int hitPoint) {
        this.width = width;
        this.height = height;
        //this.backgrounds = backgrounds;
        this.hitPoint = hitPoint;
        this.fillStartsMap = fillStartsMap;
    }

    /**
     * create - create a block at the specified location.
     *
     * @param xpos x position.
     * @param ypos y position.
     * @return a Block.
     */
    public Block create(int xpos, int ypos) {
        Map<Integer, BackGround> backGroundMap = new TreeMap<>();
        String[] fillStartsArr = new String[fillStartsMap.keySet().size()];
        for (int j = 0; j < fillStartsMap.keySet().size(); j++) {
            fillStartsArr[j] = (String) fillStartsMap.keySet().toArray()[j];
        }
        for (int i = 0; i < fillStartsArr.length; i++) {
            BackGroundParser backGroundParser = new BackGroundParser();
            BackGround bg = backGroundParser.backGroundFromString(fillStartsMap.get(fillStartsArr[i]));
            int specialHp;
            if (fillStartsArr[i].length() > "fill".length()) {
                String numString =
                        fillStartsArr[i].substring("fill-".length(), fillStartsArr[i].length());
                specialHp = Integer.parseInt(numString);
            } else {
                specialHp = 1;
            }
            backGroundMap.put(specialHp, bg);
        }

        Block block = new Block(new Point(xpos, ypos), this.width, this.height);
        block.setHitPoint(this.hitPoint);
        for (BackGround value : backGroundMap.values()) {
            Rectangle rectangle = new Rectangle(new Point(xpos, ypos), this.width, this.height);
            value.setRec(rectangle);
        }
        block.addHpBackground(backGroundMap);
        return block;
    }
}
