package readers;

import interfaces.BackGround;
import interfaces.BlockCreator;
import objects.ColorParser;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * a BlocksDefinitionReader class.
 */
public class BlocksDefinitionReader {
    //    private Map<String, String> blockInfoMap;
//    private int blocksStartX;
//    private int blocksStartY;
//    private int rowHeight;
//    private int numBlocks;
    private List<String> lines = new ArrayList<>();
    private List<String> defaults = new ArrayList<>();
    private List<String> bdefs = new ArrayList<>();
    private List<String> sdefs = new ArrayList<>();
    private Map<String, BlockCreator> blockCreatorMap = new TreeMap<>();
    private Map<String, Integer> spacerWidthsMap = new TreeMap<>();
    private java.io.Reader readerr;
//    private List<String> blocksLayout = new ArrayList<>();

//    /**
//     * BlocksDefinitionReader - constructor.
//     *
//     * @param blockInfoMap blockInfoMap.
//     */
//    public BlocksDefinitionReader(Map<String, String> blockInfoMap) {
//        this.blockInfoMap = blockInfoMap;
//    }

//    /**
//     * getBlocksStartX.
//     *
//     * @return blocksStartX
//     */
//    public int getBlocksStartX() {
//        return this.blocksStartX;
//    }
//
//    /**
//     * getBlocksStartY.
//     *
//     * @return blocksStartY
//     */
//    public int getBlocksStartY() {
//        return this.blocksStartY;
//    }
//
//    /**
//     * getRowHeight.
//     *
//     * @return rowHeight
//     */
//    public int getRowHeight() {
//        return this.rowHeight;
//    }
//
//    /**
//     * getNumBlocks.
//     *
//     * @return numBlocks
//     */
//    public int getNumBlocks() {
//        return this.numBlocks;
//    }
//
//    /**
//     * extractBlockInfo.
//     */
//    public void extractBlockInfo() {
//        if (blockInfoMap.containsKey("blocks_start_x")) {
//            this.blocksStartX = Integer.parseInt(blockInfoMap.get("blocks_start_x"));
//        } else {
//            throw new RuntimeException("no blocks_start_x found");
//        }
//        if (blockInfoMap.containsKey("blocks_start_y")) {
//            this.blocksStartY = Integer.parseInt(blockInfoMap.get("blocks_start_y"));
//        } else {
//            throw new RuntimeException("no blocks_start_y found");
//        }
//        if (blockInfoMap.containsKey("row_height")) {
//            this.rowHeight = Integer.parseInt(blockInfoMap.get("row_height"));
//        } else {
//            throw new RuntimeException("no row_height found");
//        }
//        if (blockInfoMap.containsKey("num_blocks")) {
//            this.numBlocks = Integer.parseInt(blockInfoMap.get("num_blocks"));
//        } else {
//            throw new RuntimeException("no num_blocks found");
//        }
//        if (blockInfoMap.containsKey("block_definitions")) {
//            String blockDefFile = blockInfoMap.get("block_definitions");
//            Reader blockDefReader = null;
//            try {
//                blockDefReader = new BufferedReader(// buffered reader - has readLine()
//                        new InputStreamReader(// bytes to characters wrapper
//                                new FileInputStream(blockDefFile))); // binary file stream
//                this.readLines(blockDefReader);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            throw new RuntimeException("no block_definitions found");
//        }
//    }

    /**
     * readLines - get.
     */
    public void readLines() {
        java.io.Reader reader = this.readerr;
        try {
            String line = ((BufferedReader) reader).readLine();
            while (line != null) {
                if ((!line.isEmpty()) && (!line.startsWith("#"))) {
                    lines.add(line);
                }
                line = ((BufferedReader) reader).readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * separateStarts - split to default, bdef anf sdef.
     */
    public void separateStarts() {
        for (int i = 0; i < this.lines.size(); i++) {
            if (lines.get(i).startsWith("default")) {
                this.defaults.add(lines.get(i));
            } else if (lines.get(i).startsWith("bdef")) {
                this.bdefs.add(lines.get(i));
            } else if (lines.get(i).startsWith("sdef")) {
                this.sdefs.add(lines.get(i));
            } else {
                throw new RuntimeException("no default or bdef or sdef String");
            }
        }
    }

    /**
     * extractCreators.
     * <p>
     * return BlockCreator map.
     */
    public void extractCreators() {
        //Map<String, BlockCreator> blockCreatorMap = new TreeMap<>();
        Map<String, String> defaultMap = new TreeMap<>();
        Map<String, String> blocksMap = new TreeMap<>();

        if (!this.defaults.isEmpty()) {
            String[] defBlock = defaults.get(0).split(" ");
            for (int i = 1; i < defBlock.length; i++) {
                String[] defBlockSplit = defBlock[i].split(":");
                defaultMap.put(defBlockSplit[0], defBlockSplit[1]);
            }
        }

        for (int i = 0; i < bdefs.size(); i++) {
            blocksMap.clear();
            String[] whiteSpaceSplitBdefs = bdefs.get(i).split(" ");
            for (int j = 1; j < whiteSpaceSplitBdefs.length; j++) {
                String[] properties = whiteSpaceSplitBdefs[j].split(":");
                blocksMap.put(properties[0], properties[1]);
            }
            //}

            String symbol = null;
            int hitPoint = 1;
            int height = 0;
            int width = 0;
            Color stroke = null;
            //ArrayList<BackGround> backgrounds = new ArrayList<>();
            Map<Integer, BackGround> backGroundMap = new TreeMap<>();

            if (blocksMap.containsKey("symbol")) {
                symbol = blocksMap.get("symbol");
            }
            if (blocksMap.containsKey("hit_points")) {
                hitPoint = Integer.parseInt(blocksMap.get("hit_points"));
            } else if (!defaultMap.isEmpty() && defaultMap.containsKey("hit_points")) {
                hitPoint = Integer.parseInt(defaultMap.get("hit_points"));
            } else {
                hitPoint = 1;
            }
            ColorParser colorP = new ColorParser();
            if (blocksMap.containsKey("stroke")) {
                stroke = colorP.colorFromString(blocksMap.get("stroke"));
            } else if (!defaultMap.isEmpty() && defaultMap.containsKey("stroke")) {
                stroke = colorP.colorFromString(defaultMap.get("stroke"));
            }
            Object[] objectBlockKeys = blocksMap.keySet().toArray();
            String[] blockKeys = new String[objectBlockKeys.length];
            Map<String, String> fillStartsMap = new TreeMap<>();
            for (int j = 0; j < objectBlockKeys.length; j++) {
                blockKeys[j] = (String) objectBlockKeys[j];
                if (blockKeys[j].startsWith("fill")) {
                    fillStartsMap.put(blockKeys[j], blocksMap.get(blockKeys[j]));
                }
            }

//            for (int k = 0; k < blockKeys.length; k++) {
//                if (blockKeys[k].startsWith("fill")) {
//                    String regex = "(fill(-\\d*)?)";
//                    Pattern pattern = Pattern.compile(regex);
//                    Matcher m = pattern.matcher(blockKeys[k]);
//                    m.find();
//                    String fillString = m.group(1);
//                    if (blocksMap.containsKey(fillString)) {
//                        BackGroundParser backGroundParser = new BackGroundParser();
//                        BackGround bg = backGroundParser.backGroundFromString(blocksMap.get(fillString));
//                        if (stroke != null) {
//                            bg.setStroke(stroke);
//                        }
//                        int specialHp;
//                        if (fillString.length() > "fill".length()) {
//                            String numString =
//                                    fillString.substring("fill-".length(), fillString.length());
//                            specialHp = Integer.parseInt(numString);
//                        } else {
//                            specialHp = 1;
//                        }
//                        backGroundMap.put(specialHp, bg);
//                        //backgrounds.add(bg);
//                    } else if (!defaultMap.isEmpty() && defaultMap.containsKey(fillString)) {
//                        BackGroundParser backGroundParser = new BackGroundParser();
//                        BackGround bg = backGroundParser.backGroundFromString(blocksMap.get(fillString));
//
//                        if (stroke != null) {
//                            bg.setStroke(stroke);
//                        }
//                        int specialHp;
//                        if (fillString.length() > "fill".length()) {
//                            String numString =
//                                    fillString.substring("fill-".length(), fillString.length());
//                            specialHp = Integer.parseInt(numString);
//                        } else {
//                            specialHp = 1;
//                        }
//                        backGroundMap.put(specialHp, bg);
//                        //backgrounds.add(bg);
//                    }
//                }
//            }
            if (blocksMap.containsKey("height")) {
                height = Integer.parseInt(blocksMap.get("height"));
            } else if (!defaultMap.isEmpty() && defaultMap.containsKey("height")) {
                height = Integer.parseInt(defaultMap.get("height"));
            }
            if (blocksMap.containsKey("width")) {
                width = Integer.parseInt(blocksMap.get("width"));
            } else if (!defaultMap.isEmpty() && defaultMap.containsKey("width")) {
                width = Integer.parseInt(defaultMap.get("width"));
            }
            BlockCreator blockBySymbol = new FactoryBlockCreator(width, height, fillStartsMap, hitPoint);
            // set blockfactory
            blockCreatorMap.put(symbol, blockBySymbol);
            hitPoint = 1;
            //return blockCreatorMap;
        }
    }

    /**
     * parseSpacers.
     *
     * return spacers map
     */
    public void parseSpacers() {

        //Map<String, Integer> spacerWidthsMap = new TreeMap<>();

        for (int i = 0; i < sdefs.size(); i++) {
            String spaceSymbol = "";
            int spaceWidth = 0;
            Map<String, String> tempSpacersMap = new TreeMap<>();
            String[] spacers;
            spacers = sdefs.get(i).split(" ");
            for (int j = 1; j < spacers.length; j++) {
                String[] afterColonSplit = spacers[j].split(":");
                tempSpacersMap.put(afterColonSplit[0], afterColonSplit[1]);
            }
            if (tempSpacersMap.containsKey("symbol")) {
                spaceSymbol = tempSpacersMap.get("symbol");
            }
            if (tempSpacersMap.containsKey("width")) {
                spaceWidth = Integer.parseInt(tempSpacersMap.get("width"));
            }

            spacerWidthsMap.put(spaceSymbol, spaceWidth);
        }
        //return spacerWidthsMap;
    }

    /**
     * Gets block creator map.
     *
     * @return the block creator map
     */
    public Map<String, BlockCreator> getBlockCreatorMap() {
        return blockCreatorMap;
    }

    /**
     * Gets spacer widths map.
     *
     * @return the spacer widths map
     */
    public Map<String, Integer> getSpacerWidthsMap() {
        return spacerWidthsMap;
    }

    /**
     * Sets reader.
     *
     * @param r the r
     */
    public void setReader(Reader r) {
        this.readerr = r;
    }

    /**
     * From reader blocks from symbols factory.
     *
     * @param reader the reader
     * @return the blocks from symbols factory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        BlocksDefinitionReader bDr = new BlocksDefinitionReader();
        bDr.setReader(reader);
        bDr.readLines();
        bDr.separateStarts();
        bDr.extractCreators();
        bDr.parseSpacers();
        Map<String, Integer> spacerWidths = new TreeMap<>(bDr.getSpacerWidthsMap());
        Map<String, BlockCreator> blockCreators = new TreeMap<>(bDr.getBlockCreatorMap());
        return new BlocksFromSymbolsFactory(spacerWidths, blockCreators);
    }


}
