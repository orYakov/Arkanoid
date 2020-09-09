package readers;

import interfaces.BackGround;
import interfaces.LevelInformation;
import interfaces.Sprite;
import objects.BackGroundParser;
import objects.Block;
import objects.Velocity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * a LevelSpecificationReader class.
 */
public class LevelSpecificationReader {
    private List<String> lines = new ArrayList<>();
    private List<List<String>> levelDesc = new ArrayList<>();
    //    private int blocksStartX;
//    private int blocksStartY;
//    private int rowHeight;
//    private int numBlocks;
//    private Map<String, String> blockInfoMap = new TreeMap<>();
    private List<String> blocksLayout;
    private Map<String, String> levelInfoMap = new TreeMap<>();
    //    private BlocksFromSymbolsFactory blocksFromSymbolsFactory = null;
    private List<LevelInformation> levelsInfo = new ArrayList<>();

    /**
     * readLines - read the lines.
     *
     * @param reader reads the lines.
     */
    public void readLines(java.io.Reader reader) {
        try {
            BufferedReader reader1 = new BufferedReader(reader);
            String line = reader1.readLine();
            while (line != null) {

                if ((!line.isEmpty()) && (!line.startsWith("#"))) {
                    lines.add(line);
                }
                line = reader1.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * separateLevels - split the lines to levels.
     */
    public void separateLevels() {
        int k = 0;
        int i = 0;
        while (i < lines.size()) {
            if (lines.get(i).startsWith("START_LEVEL")) {
                ++i;
                this.levelDesc.add(new ArrayList<>());
                while (!lines.get(i).startsWith("END_LEVEL")) {
                    this.levelDesc.get(k).add(lines.get(i));
                    ++i;
                    if (lines.get(i).startsWith("END_LEVEL")) {
                        ++k;
                        break;
                    }
                }
            }
            ++i;
        }


//        int k = 0;
//        for (int i = 0; i < lines.size(); i++) {
//            if (lines.get(i).equals("START_LEVEL")) {
//                for (int j = (i + 1); j < lines.size(); j++) {
//                    if (!lines.get(j).equals("END_LEVEL")) {
//                        this.levelDesc.get(k).add(lines.get(j));
//                        k++;
//                    } else {
//                        i = i + j;
//                        break;
//                    }
//                }
//            }
//        }
    }

    /**
     * separateToLevelInfos.
     *
     * @param singleLevelInfo the single level info
     */
    public void separateToLevelInfos(List<String> singleLevelInfo) {
        int i = 0;
        this.blocksLayout = null;
        this.blocksLayout = new ArrayList<>();
        while (i < singleLevelInfo.size()) {
            if (singleLevelInfo.get(i).startsWith("START_BLOCKS")) {
                i++;
                while (!singleLevelInfo.get(i).startsWith("END_BLOCKS")) {
                    this.blocksLayout.add(singleLevelInfo.get(i));
//                    String[] afterSplit = singleLevelInfo.get(i).split(":");
//                    this.blockInfoMap.put(afterSplit[0], afterSplit[1]);
                    i++;
                }
            }
            if (singleLevelInfo.get(i).contains(":")) {
                String[] afterSplit = singleLevelInfo.get(i).split(":");
                levelInfoMap.put(afterSplit[0], afterSplit[1]);
            }

            i++;
        }
    }

//    /**
//     * getBlocksStartX.
//     *
//     * @return blocksStartX blocks start x
//     */
//    public int getBlocksStartX() {
//        return this.blocksStartX;
//    }
//
//    /**
//     * getBlocksStartY.
//     *
//     * @return blocksStartY blocks start y
//     */
//    public int getBlocksStartY() {
//        return this.blocksStartY;
//    }
//
//    /**
//     * getRowHeight.
//     *
//     * @return rowHeight row height
//     */
//    public int getRowHeight() {
//        return this.rowHeight;
//    }
//
//    /**
//     * getNumBlocks.
//     *
//     * @return numBlocks num blocks
//     */
//    public int getNumBlocks() {
//        return this.numBlocks;
//    }

    /**
     * extractBlockInfo.
     */
    public void extractLevelInfo() {
        String levelName = "";
        int paddleSpeed = 0;
        int paddleWidth = 0;
        int blocksStartX = 0;
        int blocksStartY = 0;
        int rowHeight = 0;
        int numBlocks = 0;
        int numberOfBalls = 0;

        List<Velocity> initialBallVelocities = new ArrayList<>();
        List<Block> blocks;
        BackGround background = null;
        List<String> blocksInfo = this.blocksLayout;
        BlocksFromSymbolsFactory blocksFromSymbolsFactory = null;


        if (levelInfoMap.containsKey("level_name")) {
            levelName = levelInfoMap.get("level_name");
        } else {
            throw new RuntimeException("no level_name found");
        }
        if (levelInfoMap.containsKey("ball_velocities")) {
            String[] pairs = levelInfoMap.get("ball_velocities").split(" ");
            Velocity v;
            for (int i = 0; i < pairs.length; i++) {
                String[] angleAndSpeed = pairs[i].split(",");
                double angle = Double.parseDouble(angleAndSpeed[0]);
                double speed = Double.parseDouble(angleAndSpeed[1]);
                speed = speed / 60.0;
                v = Velocity.fromAngleAndSpeed(angle, speed);
                initialBallVelocities.add(v);
                numberOfBalls = initialBallVelocities.size();
            }
        } else {
            throw new RuntimeException("no ball_velocities found");
        }
        if (this.levelInfoMap.containsKey("background")) {
            String bg = levelInfoMap.get("background");
            BackGroundParser backGroundParser = new BackGroundParser();
            background = backGroundParser.backGroundFromString(bg);
        }
        if (this.levelInfoMap.containsKey("paddle_speed")) {
            String padSpeedString = this.levelInfoMap.get("paddle_speed");
            paddleSpeed = Integer.parseInt(padSpeedString);
        } else {
            throw new RuntimeException("no paddle_speed found");
        }
        if (this.levelInfoMap.containsKey("paddle_width")) {
            String padWidthString = this.levelInfoMap.get("paddle_width");
            paddleWidth = Integer.parseInt(padWidthString);
        } else {
            throw new RuntimeException("no paddle_width found");
        }


        if (levelInfoMap.containsKey("blocks_start_x")) {
            blocksStartX = Integer.parseInt(levelInfoMap.get("blocks_start_x"));
        } else {
            throw new RuntimeException("no blocks_start_x found");
        }
        if (levelInfoMap.containsKey("blocks_start_y")) {
            blocksStartY = Integer.parseInt(levelInfoMap.get("blocks_start_y"));
        } else {
            throw new RuntimeException("no blocks_start_y found");
        }
        if (levelInfoMap.containsKey("row_height")) {
            rowHeight = Integer.parseInt(levelInfoMap.get("row_height"));
        } else {
            throw new RuntimeException("no row_height found");
        }
        if (levelInfoMap.containsKey("num_blocks")) {
            numBlocks = Integer.parseInt(levelInfoMap.get("num_blocks"));
        } else {
            throw new RuntimeException("no num_blocks found");
        }
        if (levelInfoMap.containsKey("block_definitions")) {
            String blockDefFile = "resources/" + levelInfoMap.get("block_definitions");
            Reader blockDefReader = null;
            try {
                blockDefReader = new BufferedReader(// buffered reader - has readLine()
                        new InputStreamReader(// bytes to characters wrapper
                                new FileInputStream(blockDefFile))); // binary file stream
                //this.readLines(blockDefReader);
                blocksFromSymbolsFactory = BlocksDefinitionReader.fromReader(blockDefReader);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("no block_definitions found");
        }
        List<Object> blockThings = new ArrayList<>();
        blockThings.add(rowHeight);
        blockThings.add(blocksStartX);
        blockThings.add(blocksStartY);
        blockThings.add(blocksInfo);
        blockThings.add(blocksFromSymbolsFactory);
        //blocks = createBlocks(rowHeight, blocksStartX, blocksStartY, blocksInfo, blocksFromSymbolsFactory);
        levelsInfo.add(new LevelFactory(initialBallVelocities, paddleSpeed, paddleWidth,
                levelName, (Sprite) background, blockThings, numBlocks));
    }

//    public List<Block> createBlocks(int rowHeight, int blocksStartx, int blocksStarty, List<String> blocksInfo,
//                                    BlocksFromSymbolsFactory blocksFromSymbolsFactory) {
//
//        List<Block> blocks = new ArrayList<>();
//
//        int currentHeight = blocksStarty;
//        int currentWidth;
//
//        for (int i = 0; i < blocksInfo.size(); i++) {
//            currentWidth = blocksStartx;
//            for (int j = 0; j < blocksInfo.get(i).length(); j++) {
//                char c = blocksInfo.get(i).charAt(j);
//                if (blocksFromSymbolsFactory.isBlockSymbol(c + "")) {
//                    Block newBlock =
//                            blocksFromSymbolsFactory.getBlock(c + "", currentWidth, currentHeight);
//                    blocks.add(newBlock);
//                    currentWidth = currentWidth + (int) newBlock.getCollisionRectangle().getWidth();
//                } else if (blocksFromSymbolsFactory.isSpaceSymbol(c + "")) {
//                    currentWidth = currentWidth + (blocksFromSymbolsFactory.getSpaceWidth(c + ""));
//                } else {
//                    throw new RuntimeException("bad symbol " + c);
//                }
//
//            }
//            currentHeight = currentHeight + rowHeight;
//        }
//        return blocks;
//    }


    /**
     * From reader list.
     *
     * @param reader the reader
     * @return the list
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        this.readLines(reader);
        this.separateLevels();
        List<List<String>> levelDescCopy = new ArrayList<>(this.levelDesc);
        for (int i = 0; i < levelDescCopy.size(); i++) {
            this.separateToLevelInfos(levelDescCopy.get(i));
            this.extractLevelInfo();
        }
        return this.levelsInfo;
    }
}
