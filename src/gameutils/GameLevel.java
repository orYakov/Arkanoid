package gameutils;

import biuoop.KeyboardSensor;
import interfaces.Animation;
import interfaces.Collidable;
import interfaces.LevelInformation;
import interfaces.Sprite;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import geometry.Point;
import biuoop.DrawSurface;
import biuoop.GUI;
import objects.Block;
import objects.Counter;
import objects.Paddle;
import objects.KillingBlock;
import objects.Velocity;
import objects.Ball;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * a GameLevel class.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private biuoop.Sleeper sleeper;
    private DrawSurface d;
    private Block leftBlock;
    private Block rightBlock;
    private Block toppBlock;
    private Block botBlock;
    private Paddle paddle;
    private Counter blockCounter = new Counter();
    private Counter ballCounter = new Counter();
    private Counter score;
    private Counter lives;
    private BallRemover ballRemover;
    private BlockRemover blockRemover;
    private ScoreTrackingListener scoreKeeper;
    private AnimationRunner runner;
    private boolean running;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation levelInfo;

    /**
     * GameLevel - constractor.
     *
     * @param levelInformation the levelInformation.
     * @param ks               the KeyboardSensor.
     * @param ar               the AnimationRunner.
     * @param score            - the score.
     * @param lives            - the number of lives.
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor ks, AnimationRunner ar,
                     Counter score, Counter lives) {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        this.levelInfo = levelInformation;
        this.keyboard = ks;
        this.runner = ar;
        this.score = score;
        this.lives = lives;
        this.sprites.setDt(this.runner.getDt());
    }

    /**
     * addCollidable - add a collidable to the game.
     *
     * @param collidable the collidable to add.
     */
    public void addCollidable(Collidable collidable) {
        this.environment.addCollidable(collidable);
    }

    /**
     * addSprite - add a sprite to the game.
     *
     * @param sprite the sprite to add.
     */
    public void addSprite(Sprite sprite) {
        this.sprites.addSprite(sprite);
    }

    /**
     * removeCollidable - remove a collidable from the game.
     *
     * @param collidable the collidable to remove.
     */
    public void removeCollidable(Collidable collidable) {
        this.environment.removeCollidable(collidable);
    }

    /**
     * removeSprite - remove a sprite from the game.
     *
     * @param s the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * addBorderBlocks - add 4 blocks to the game, as borders.
     *
     * @param surface the DrawSurface instance to get the frame limits from.
     */
    public void addBorderBlocks(DrawSurface surface) {
        int frameWidth = surface.getWidth();
        int frameHeight = surface.getHeight();
        int sideBlockWidth = (int) (0.03 * frameWidth); // 3% of the frame width
        int sideBlockHeight = frameHeight;
        int topBotBlockWidth = frameWidth - (2 * sideBlockWidth);
        int topBotBlockheight = sideBlockWidth;
        Point leftSideBlockPoint = new Point(0, 0);
        // the -5 issue is explained 3 rows below
        Point rightSideBlockPoint = new Point((frameWidth - sideBlockWidth - 5), 0);
        Point bottomBlockPoint = new Point(sideBlockWidth, (frameHeight - topBotBlockheight));
        Point topBlockPoint = new Point(sideBlockWidth, topBotBlockheight);
        /*
        adding +5 to the side block width to cover the "inside frame" hermetically,
        so the ball won't get stuck inside the blocks.
         */
        Block leftSideBlock = new Block(leftSideBlockPoint, (sideBlockWidth + 5), sideBlockHeight);
        Block rightSideBlock = new Block(rightSideBlockPoint, (sideBlockWidth + 5), sideBlockHeight);
        Block topBlock = new Block(topBlockPoint, topBotBlockWidth, topBotBlockheight);
        Block bottomBlock = new KillingBlock(bottomBlockPoint, topBotBlockWidth, topBotBlockheight);
        Block[] borderBlocks = {leftSideBlock, rightSideBlock, topBlock, bottomBlock};
        for (int i = 0; i < 4; i++) {
            borderBlocks[i].getCollisionRectangle().setFrameColor(Color.blue);
            borderBlocks[i].setHitPoint(1);
            borderBlocks[i].addToGame(this);
        }
        this.leftBlock = leftSideBlock;
        this.rightBlock = rightSideBlock;
        this.toppBlock = topBlock;
        this.botBlock = bottomBlock;
    }


    /**
     * initialize - Initialize a new game: create the Blocks, the Ball and the Paddle,
     * and add them to the game.
     */
    public void initialize() {
        this.gui = this.runner.getGui();
        //this.gui = new GUI("Arkanoid", 800, 600);
        //this.runner = new AnimationRunner(this.gui, 60);
        //this.keyboard = this.gui.getKeyboardSensor();
        this.sleeper = new biuoop.Sleeper();
        this.d = this.gui.getDrawSurface();
        this.addSprite(levelInfo.getBackground());
        this.blockCounter.increase(this.levelInfo.numberOfBlocksToRemove());
        this.ballRemover = new BallRemover(this, this.ballCounter);
        this.addBorderBlocks(this.d);
        this.botBlock.addHitListener(ballRemover);
        this.scoreKeeper = new ScoreTrackingListener(this.score);
        this.blockRemover = new BlockRemover(this, this.blockCounter);
        Block indicBlock = new Block(new Point(0, 0),
                this.d.getWidth(), this.toppBlock.getCollisionRectangle().getHeight());
        indicBlock.getCollisionRectangle().setColor(Color.lightGray);
        indicBlock.setHitPoint(100);
        indicBlock.addToGame(this);
        ScoreIndicator scoreIndic = new ScoreIndicator(this.score);
        scoreIndic.addToGame(this);
        LivesIndicator livesIndic = new LivesIndicator(this.lives);
        livesIndic.addToGame(this);
        LevelIndicator levelIndic = new LevelIndicator(this.levelInfo.levelName());
        levelIndic.addToGame(this);
        this.addLevelBlocks();

    }

    /**
     * getLeftBlock.
     *
     * @return the left border block.
     */
    public Block getLeftBlock() {
        return this.leftBlock;
    }

    /**
     * getRightBlock.
     *
     * @return the right border block.
     */
    public Block getRightBlock() {
        return this.rightBlock;
    }

    /**
     * addLevelBlocks.
     */
    public void addLevelBlocks() {
        List<Block> blocks = new ArrayList<Block>(this.levelInfo.blocks());
        for (Block block : blocks) {
            block.addToGame(this);
            block.addHitListener(this.blockRemover);
            block.addHitListener(this.scoreKeeper);
//            for (BackGround value: block.getHitPointBg().values()) {
//                Sprite bg = (Sprite) value;
//                bg.
//            }
//            block.g(this);
//            block.addHitListener(this.blockRemover);
//            block.addHitListener(this.scoreKeeper);
        }
    }

    /**
     * createPaddle - create a paddle in the center of the screen.
     */
    public void createPaddle() {
        // 30 is paddle height
        double padY = this.d.getHeight() - this.botBlock.getCollisionRectangle().getHeight() - 30;
        int halfPadWidth = (this.levelInfo.paddleWidth() / 2);
        /*
        putting the Paddle in the center of the screen, by setting the x coordinate as follows:
        dividing the screen's width by 2, and subtracting half of the Paddle's width.
        */
        double toCenter = (this.d.getWidth() / 2) - halfPadWidth;
        this.paddle = new Paddle(keyboard, new Point(toCenter, padY),
                this.levelInfo.paddleWidth(), 30);
        this.paddle.getCollisionRectangle().setColor(Color.ORANGE);
        this.paddle.setSpeed(this.levelInfo.paddleSpeed());
        //this.paddle.setFrameRate(this.runner.getFramesPerSecond());
        this.paddle.setFrameRate(60);
        this.paddle.addToGame(this);
    }

    /**
     * createBalls.
     *
     * @param amount - the amount of Balls that should be created.
     */
    public void createBalls(int amount) {
        List<Velocity> velocities = this.levelInfo.initialBallVelocities();
        for (int i = 0; i < amount; i++) {
            Ball ball = new Ball(400, 540, 7, Color.white);
            Velocity v = velocities.get(i);
            ball.setVelocity(v);
            ball.setTrajectory();
            //ball.setFrameRate(this.runner.getFramesPerSecond());
            ball.setFrameRate(60);
            ball.addToGame(this);
            this.ballCounter.increase(1);
            ball.setGameEnvironment(this.environment);
        }
    }

    /**
     * createBallsOnTopOfPaddle.
     */
    public void createBallsOnTopOfPaddle() {
        createPaddle();
        createBalls(this.levelInfo.numberOfBalls());
    }

    /**
     * shouldStop - defines the stopping condition.
     *
     * @return true if the game should stop, and false otherwise.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * doOneFrame - treats the logic of one turn of the game.
     *
     * @param surface the DrawSurface to draw the animation on.
     * @param dt      specifies the amount of seconds passed since the last call.
     */
    public void doOneFrame(DrawSurface surface, double dt) {
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, this.keyboard.SPACE_KEY,
                    new PauseScreen(this.keyboard)));
        }
        if (this.blockCounter.getValue() == 0) {
            this.score.increase(100);
            //this.gui.close();
            this.running = false;
        }
        if (this.ballCounter.getValue() == 0) {
            this.lives.decrease(1);
            this.paddle.removeFromGame(this);
            this.running = false;
        }
        this.sprites.drawAllOn(surface);
        this.sprites.notifyAllTimePassed();
    }

    /**
     * playOneTurn.
     */
    public void playOneTurn() {
        this.createBallsOnTopOfPaddle();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * getBlockCounter.
     *
     * @return blockCounter.
     */
    public Counter getBlockCounter() {
        return this.blockCounter;
    }

    /**
     * getBallCounter.
     *
     * @return ballCounter.
     */
    public Counter getBallCounter() {
        return this.ballCounter;
    }

    /**
     * getScore.
     *
     * @return score.
     */
    public Counter getScore() {
        return this.score;
    }

    /**
     * getLives.
     *
     * @return lives.
     */
    public Counter getLives() {
        return this.lives;
    }

}
