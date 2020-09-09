package readers;

import interfaces.LevelInformation;
import interfaces.Sprite;
import objects.Block;
import objects.Velocity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * a LevelFactory class.
 */
public class LevelFactory implements LevelInformation {
    private int numberOfBalls = 0;
    private List<Velocity> initialBallVelocities = null;
    private int paddleSpeed = 0;
    private int paddleWidth = 0;
    private String levelName = null;
    private Sprite background = null;
    //private List<Block> blocks = null;
    private List<Object> blockThings = null;
    private int numberOfBlocksToRemove = 0;
    private List<String> levelDesc;
    private Map<String, String> levelInfoMap = new TreeMap<>();
    private Map<String, String> blockInfoMap = new TreeMap<>();
    private List<String> blocksLayout = new ArrayList<>();


    /**
     * Instantiates a new Level factory.
     *
     * @param initialBallVelocities the initial ball velocities
     * @param paddleSpeed           the paddle speed
     * @param paddleWidth           the paddle width
     * @param levelName             the level name
     * @param background            the background
     * @param blockThings           the block things
     * @param numBlocks             the num blocks
     */
    public LevelFactory(List<Velocity> initialBallVelocities, int paddleSpeed, int paddleWidth,
                        String levelName, Sprite background, List<Object> blockThings,
                        int numBlocks) {
        this.initialBallVelocities = initialBallVelocities;
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        this.levelName = levelName;
        this.background = background;
        //this.blocks = new ArrayList<Block>(blocks);
        this.blockThings = new ArrayList<Object>(blockThings);
        this.numberOfBlocksToRemove = numBlocks;
        this.numberOfBalls = this.initialBallVelocities.size();
    }

    /**
     * numberOfBalls.
     *
     * @return the number of balls.
     */
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    /**
     * initialBallVelocities.
     *
     * @return the initial velocity of each ball, in a list.
     */
    public List<Velocity> initialBallVelocities() {
        return this.initialBallVelocities;
    }

    /**
     * paddleSpeed.
     *
     * @return the paddle's speed.
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * paddleWidth.
     *
     * @return the paddle's width.
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * levelName.
     *
     * @return the level name, which will be displayed at the top of the screen.
     */
    public String levelName() {
        return this.levelName;
    }

    /**
     * getBackground.
     *
     * @return a sprite with the background of the level.
     */
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * blocks.
     *
     * @return the Blocks that make up this level, each block contains its size, color and location.
     */
    public List<Block> blocks() {
        //return new ArrayList<>(this.blocks);
        return createBlocks(this.blockThings);

    }

    /**
     * numberOfBlocksToRemove.
     *
     * @return the number of Blocks that should be removed before the level is considered to be "cleared".
     * this number should be <= blocks.size();
     */
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }

    /**
     * Create blocks list.
     *
     * @param blockThingss the block things
     * @return the list
     */
    public List<Block> createBlocks(List<Object> blockThingss) {

        int rowHeight = (int) blockThingss.get(0);
        int blocksStartx = (int) blockThingss.get(1);
        int blocksStarty = (int) blockThingss.get(2);
        List<String> blocksInfo = (List<String>) blockThingss.get(3);
        BlocksFromSymbolsFactory blocksFromSymbolsFactory = (BlocksFromSymbolsFactory) blockThingss.get(4);

        List<Block> blocks = new ArrayList<>();

        int currentHeight = blocksStarty;
        int currentWidth;

        for (int i = 0; i < blocksInfo.size(); i++) {
            currentWidth = blocksStartx;
            for (int j = 0; j < blocksInfo.get(i).length(); j++) {
                char c = blocksInfo.get(i).charAt(j);
                if (blocksFromSymbolsFactory.isBlockSymbol(c + "")) {
                    Block newBlock =
                            blocksFromSymbolsFactory.getBlock(c + "", currentWidth, currentHeight);
                    blocks.add(newBlock);
                    currentWidth = currentWidth + (int) newBlock.getCollisionRectangle().getWidth();
                } else if (blocksFromSymbolsFactory.isSpaceSymbol(c + "")) {
                    currentWidth = currentWidth + (blocksFromSymbolsFactory.getSpaceWidth(c + ""));
                } else {
                    throw new RuntimeException("bad symbol " + c);
                }

            }
            currentHeight = currentHeight + rowHeight;
        }
        return blocks;
    }

//    /**
//     * mapinfo - splits every line of the level line according to ":".
//     */
//    public void mapinfo() {
//        int i = 0;
//        while (i < this.levelDesc.size()) {
//            if (this.levelDesc.get(i).startsWith("block_definitions")) {
//                while (!this.levelDesc.get(i).startsWith("END_BLOCKS")) {
//                    String[] afterSplit = this.levelDesc.get(i).split(":");
//                    this.blockInfoMap.put(afterSplit[0], afterSplit[1]);
//                    i++;
//                }
//            }
//            String[] afterSplit = this.levelDesc.get(i).split(":");
//            levelInfoMap.put(afterSplit[0], afterSplit[1]);
//            i++;
//        }
//
//        for (int i = 0; i < levelDesc.size(); i++) {
//            String[] afterSplit = this.levelDesc.get(i).split(":");
//            if (afterSplit[0].startsWith("block_definitions"))
//            levelInfoMap.put(afterSplit[0], afterSplit[1]);
//        }
//    }
//
//    public void extractBlockLayout() {
//        int i = 0;
//        while (i < this.levelDesc.size()) {
//            if (this.levelDesc.get(i).startsWith("START_BLOCKS")) {
//                i++;
//                while (!this.levelDesc.get(i).startsWith("END_BLOCKS")) {
//                    if (!levelDesc.get(i).startsWith("#") && (!levelDesc.isEmpty())) {
//                        this.blocksLayout.add(levelDesc.get(i));
//                    }
//                    i++;
//                }
//            }
//        }
//    }
//
//    /**
//     * extractLevlInfo.
//     */
//    public void extractLevlInfo() {
//        if (levelInfoMap.containsKey("level_name")) {
//            this.levelName = levelInfoMap.get("level_name");
//        } else {
//            throw new RuntimeException("no level_name found");
//        }
//        if (levelInfoMap.containsKey("ball_velocities")) {
//            String[] pairs = levelInfoMap.get("ball_velocities").split(" ");
//            Velocity v;
//            for (int i = 0; i < pairs.length; i++) {
//                String[] angleAndSpeed = pairs[i].split(",");
//                double angle = Double.parseDouble(angleAndSpeed[0]);
//                double speed = Double.parseDouble(angleAndSpeed[1]);
//                v = Velocity.fromAngleAndSpeed(angle, speed);
//                this.initialBallVelocities.add(v);
//                this.numberOfBalls = this.initialBallVelocities.size();
//            }
//        } else {
//            throw new RuntimeException("no ball_velocities found");
//        }
//        if (this.levelInfoMap.containsKey("background")) {
//            String bg = levelInfoMap.get("background");
//            if (bg.startsWith("color")) {
//                if (bg.contains("RGB")) {
//                    String regex = "(\\d*,\\s*\\d*,\\s*\\d*)";
//                    Pattern pattern = Pattern.compile(regex);
//                    Matcher m = pattern.matcher(bg);
//                    m.find();
//                    String rgb = m.group(1);
//                    String[] singleDigits = rgb.split(",");
//                    int x = Integer.parseInt(singleDigits[0]);
//                    int y = Integer.parseInt(singleDigits[1]);
//                    int z = Integer.parseInt(singleDigits[2]);
//                    ColorBackground colorBackground = new ColorBackground();
//                    Color c = new Color(x, y, z);
//                    colorBackground.setColor(c);
//                    this.background = colorBackground;
//                } else {
//                    Color[] colors = {black, blue, cyan, gray, lightGray, green,
//                            orange, pink, red, white, yellow};
//                    String[] colorNames = {"black", "blue", "cyan", "gray", "lightGray", "green",
//                            "orange", "pink", "red", "white", "yellow"};
//                    Map<String, Color> nameColorMap = new TreeMap<>();
//                    for (int i = 0; i < colorNames.length; i++) {
//                        nameColorMap.put(colorNames[i], colors[i]);
//                    }
//                    String colorType = bg.substring("color".length() + 1, bg.length() - 1);
//                    ColorBackground colorBackground = new ColorBackground();
//                    for (int i = 0; i < colorNames.length; i++) {
//                        if (colorNames[i].equals(colorType)) {
//                            colorBackground.setColor(nameColorMap.get(colorType));
//                            break;
//                        }
//                    }
//                    this.background = colorBackground;
//                }
//            } else if (bg.startsWith("image")) {
//                String imageName = bg.substring("image".length() + 1, bg.length() - 1);
//                Image img = null;
//                ImageBackground imageBackground;
//                try {
//                    img = ImageIO.read(new File(imageName));
//                    imageBackground = new ImageBackground(img);
//                    this.background = imageBackground;
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        if (this.levelInfoMap.containsKey("paddle_speed")) {
//            String padSpeedString = this.levelInfoMap.get("paddle_speed");
//            this.paddleSpeed = Integer.parseInt(padSpeedString);
//        } else {
//            throw new RuntimeException("no paddle_speed found");
//        }
//        if (this.levelInfoMap.containsKey("paddle_width")) {
//            String padWidthString = this.levelInfoMap.get("paddle_width");
//            this.paddleWidth = Integer.parseInt(padWidthString);
//        } else {
//            throw new RuntimeException("no paddle_width found");
//        }

//    }
}
