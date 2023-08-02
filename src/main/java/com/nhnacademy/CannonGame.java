package com.nhnacademy;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CannonGame extends JFrame implements Runnable {

    private static final int FRAME_X = 800;
    private static final int FRAME_Y = 300;
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 727;
    private static final int DEFAULT_ANGLE = 45;
    private static final int DEFAULT_POWER = 50;
    private static final int DEFAULT_WIND = 0;
    private static final int delay = 10;

    private MovableWorld world;
    private Thread thread;
    private Logger logger;

    public CannonGame() {
        super();
        thread = new Thread(this, "Frame");
        logger = LogManager.getLogger(this.getClass().getSimpleName());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void prepare() {
        setLayout(null);
        setResizable(false);
        setLocation(FRAME_X, FRAME_Y);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);

        world = new MovableWorld();
        world.addEffect(Motion.createPosition(0, 2));
        world.addEffect(Motion.createPosition(0, 0));
        world.setMaxRefreshCount(Integer.MAX_VALUE);
        world.setDelay(delay);
        world.setBounds(0, 0, 800, 600);
        world.setBackground(Color.LIGHT_GRAY);
        add(world);

        JLabel angleValueLabel = new JLabel(DEFAULT_ANGLE + "");
        angleValueLabel.setBounds(200, 660, 200, 40);
        angleValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(angleValueLabel);

        JSlider angleSlider = new JSlider(0, 90, DEFAULT_ANGLE);
        angleSlider.setBounds(200, 600, 200, 100);
        angleSlider.setMajorTickSpacing(15);
        angleSlider.setMinorTickSpacing(5);
        angleSlider.setSnapToTicks(true);
        angleSlider.setPaintTicks(true);
        angleSlider.addChangeListener(e -> {
            angleValueLabel.setText(angleSlider.getValue() + "");
        });
        add(angleSlider);

        JLabel powerValueLabel = new JLabel(DEFAULT_POWER + "");
        powerValueLabel.setBounds(400, 660, 200, 40);
        powerValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(powerValueLabel);

        JSlider powerSlider = new JSlider(0, 100, DEFAULT_POWER);
        powerSlider.setBounds(400, 600, 200, 100);
        powerSlider.setMajorTickSpacing(25);
        powerSlider.setMinorTickSpacing(5);
        powerSlider.setSnapToTicks(true);
        powerSlider.setPaintTicks(true);
        powerSlider.addChangeListener(e -> {
            powerValueLabel.setText(powerSlider.getValue() + "");
        });
        add(powerSlider);

        JLabel windValueLabel = new JLabel(DEFAULT_WIND + "");
        windValueLabel.setBounds(600, 660, 200, 40);
        windValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(windValueLabel);

        JSlider windSlider = new JSlider(-5, 5, DEFAULT_WIND);
        windSlider.setBounds(600, 600, 200, 100);
        windSlider.setMajorTickSpacing(5);
        windSlider.setMinorTickSpacing(1);
        windSlider.setSnapToTicks(true);
        windSlider.setPaintTicks(true);
        windSlider.addChangeListener(e -> {
            windValueLabel.setText(windSlider.getValue() + "");
            world.getEffect(1).setDX(0);
            world.getEffect(1).setDY(0);
            world.getEffect(1).add(Motion.createDisplacement(windSlider.getValue(), 0));
        });
        add(windSlider);

        JLabel angleLabel = new JLabel("Angle");
        angleLabel.setBounds(200, 600, 200, 38);
        angleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(angleLabel);

        JLabel powerLabel = new JLabel("Power");
        powerLabel.setBounds(400, 600, 200, 38);
        powerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(powerLabel);

        JLabel windLabel = new JLabel("Wind");
        windLabel.setBounds(600, 600, 200, 38);
        windLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(windLabel);

        JButton fireButton = new JButton("Fire");
        fireButton.setBounds(0, 600, 100, 100);
        fireButton.addActionListener(e -> {
            Random random = new Random();
            final Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            CollisionBall ball = new CollisionBall(new Point(30, 560), 20, color);

            ball.setResistance(true);
            ball.setWorld(world);
            ball.setMotion(Motion.createDisplacement(powerSlider.getValue(), angleSlider.getValue()));
            ball.start();
            world.add(ball);
        });
        add(fireButton);

        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(100, 600, 100, 100);
        clearButton.addActionListener(e -> {
            angleSlider.setValue(DEFAULT_ANGLE);
            powerSlider.setValue(DEFAULT_POWER);
            windSlider.setValue(DEFAULT_WIND);
        });
        clearButton.addActionListener(e -> {
            List<Regionable> items = new ArrayList<>();

            world.getItems().stream() // item thread 중지
                    .filter(item -> item instanceof Movable)
                    .map(item -> (Movable) item)
                    .forEach(item -> item.stop());

            world.getItems().stream() // item 삭제
                    .filter(item -> !(item instanceof Movable))
                    .forEach(item -> items.add(item));
            world.setItems(items);
        });
        add(clearButton);
    }

    public void start() {
        thread.start();
    }

    public void stop() {
        thread.interrupt();
    }

    public void join() throws InterruptedException {
        thread.join();
    }

    private void preprocess() {
        prepare();
        setVisible(true);
        world.add(new Box(new Point(400, 300), 300, 20, Color.GRAY));
        world.start();
        logger.trace("Game prepared");
    }

    private void process() {
        repaint();
    }

    private void postProcess() {
        logger.trace("Game finished");
    }

    @Override
    public void run() {
        preprocess();

        logger.trace("Game start");
        while (!Thread.interrupted()) {
            try {
                process();
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        postProcess();
    }

    public static void main(String[] args) throws InterruptedException {
        CannonGame game = new CannonGame();

        game.start();
        game.join();
    }
}
