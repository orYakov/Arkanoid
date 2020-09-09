import backgrounds.*;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gameutils.*;
import geometry.*;
import interfaces.*;
import levels.*;
import listeners.*;
import objects.*;
import scoreuses.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.awt.Color.*;
import static java.awt.Color.yellow;

public class TestScores {
    public static void main(String[] args) {
//        HighScoresTable table = new HighScoresTable(5);
//        ScoreInfo scoreInfo1 = new ScoreInfo("Yossi", 10);
//        ScoreInfo scoreInfo2 = new ScoreInfo("Moshe", 20);
//        ScoreInfo scoreInfo3 = new ScoreInfo("Samir", 30);
//        ScoreInfo scoreInfo4 = new ScoreInfo("Vadim", 40);
//        ScoreInfo scoreInfo5 = new ScoreInfo("Abed", 50);
//        ScoreInfo scoreInfo6 = new ScoreInfo("Sergio", 60);
//        ScoreInfo scoreInfo7 = new ScoreInfo("David", 70);
//        table.add(scoreInfo1);
//        table.add(scoreInfo2);
//        table.add(scoreInfo3);
//        table.add(scoreInfo4);
//        table.add(scoreInfo5);
//        table.add(scoreInfo6);
//        table.add(scoreInfo7);
//        for (ScoreInfo scoreInfo : table.getHighScores()) {
//            System.out.println(scoreInfo.getName());
//            System.out.println(scoreInfo.getScore());
//        }
//        File file = new File("scoreTable.ser");
//        try {
//            table.save(file);
//            table.load(file);
//            GUI gui = new GUI("Arkanoid", 800, 600);
//            AnimationRunner ar = new AnimationRunner(gui, 60);
//            KeyboardSensor ks = gui.getKeyboardSensor();
//            HighScoresAnimation highScoresAnimation = new HighScoresAnimation(table, "s", ks);
//            while (!highScoresAnimation.shouldStop()) {
//                ar.run(highScoresAnimation);
//            }
//            gui.close();
//        } catch (Exception e) {
//
//        }
        String bg = "image(background_images/jungle.jpg)";
        //String bg = "color(RGB(30,150,150))";
        if (bg.startsWith("color")) {
            if (bg.contains("RGB")) {
                String regex = "(\\d*,\\s*\\d*,\\s*\\d*)";
                Pattern pattern = Pattern.compile(regex);
                Matcher m = pattern.matcher(bg);
                m.find();
                String rgb = m.group(1);
                String[] singleDigits = rgb.split(",");
                int x = Integer.parseInt(singleDigits[0]);
                int y = Integer.parseInt(singleDigits[1]);
                int z = Integer.parseInt(singleDigits[2]);
                ColorBackground colorBackground = new ColorBackground();
                Color c = new Color(x, y, z);
                colorBackground.setColor(c);
                GUI gui = new GUI("bg test", 800, 600);
                DrawSurface d = gui.getDrawSurface();
                colorBackground.drawOn(d);
                gui.show(d);

            } else {
                Color[] colors = {black, blue, cyan, gray, lightGray, green, orange, pink, red, white, yellow};
                String[] colorNames = {"black", "blue", "cyan", "gray", "lightGray", "green", "orange", "pink", "red", "white", "yellow"};
                Map<String, Color> nameColorMap = new TreeMap<>();
                for (int i = 0; i < colorNames.length; i++) {
                    nameColorMap.put(colorNames[i], colors[i]);
                }
                String colorType = bg.substring("color".length() + 1, bg.length() - 1);
                ColorBackground colorBackground = new ColorBackground();
                for (int i = 0; i < colorNames.length; i++) {
                    if (colorNames[i].equals(colorType)) {
                        colorBackground.setColor(nameColorMap.get(colorType));
                        break;
                    }
                }
                GUI gui = new GUI("bg test", 800, 600);
                DrawSurface d = gui.getDrawSurface();
                colorBackground.drawOn(d);
                gui.show(d);
            }
        } else if (bg.startsWith("image")) {
            String imageName = bg.substring("image".length() + 1, bg.length() - 1);
            Image img = null;
            ImageBackground imageBackground;
            try {
                img = ImageIO.read(new File(imageName));
                imageBackground = new ImageBackground(img);
                GUI gui = new GUI("bg test", 800, 600);
                DrawSurface d = gui.getDrawSurface();
                imageBackground.drawOn(d);
                gui.show(d);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
