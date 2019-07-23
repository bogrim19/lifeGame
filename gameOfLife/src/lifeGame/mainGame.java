package lifeGame;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;


public class mainGame {
    private static final String TITLE = "Game of Life";
    public static final int CELL_SIZE = 14;
    public static final int WIDTH = currentGrid.X*20, HEIGHT = currentGrid.Y*20;
    public static final Dimension DIMENSIONS = new Dimension(WIDTH, HEIGHT);
    private static final boolean RUNNING = true;

    public static void main(String[] args) {

        JFrame frame = new JFrame(TITLE);
        frame.setSize(DIMENSIONS);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        GameCanvas canvas = new GameCanvas();
        frame.add(canvas);
        canvas.createBufferStrategy(3);

        //currentGrid game = new currentGrid();
        currentGrid game = new currentGrid(0);
        //game.printGrid();
        game.CSVRead();
        game.printGrid();
        startGameLoop(canvas,game);




        /*for(int i=1;i<31;i++) {
            game.countNeighbours();
            game.printNeigh();
            game.tick();
            try {Thread.sleep(100);}
            catch (Exception e){
                System.out.println("This was generation" + i);
            };
            System.out.println();System.out.println();System.out.println();
            game.printGrid();
        }*/
}
    private static void startGameLoop(GameCanvas canvas,currentGrid game) {
        Timer timer = new Timer();

        if (RUNNING) {
            timer.schedule(new TimerTask() {

                @Override
                public void run() {

                    game.countNeighbours();
                    game.tick();
                    canvas.paint(game);
                }

            }, 550, 550);
        }
    }
}
