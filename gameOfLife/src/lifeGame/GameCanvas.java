package lifeGame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;



public class GameCanvas extends Canvas {

    public GameCanvas() {
        super();
        this.setSize(mainGame.WIDTH, mainGame.HEIGHT);
        this.setBackground(Color.GRAY);
        this.setFocusable(true);
        this.setVisible(true);


    }

    public void paint(currentGrid game) {
        Graphics graphics = this.getGraphics();
        graphics.clearRect(0, 0, mainGame.WIDTH, mainGame.HEIGHT);
        graphics.setColor( Color.DARK_GRAY);

        for (int i = 0; i < currentGrid.X; i++) {
            for (int j = 0; j < currentGrid.Y; j++){
                if (game.gridCell[i][j].state) {
                    int size = mainGame.CELL_SIZE;
                    int x = i * size;
                    int y = j * size;

                    graphics.fillRect(x, y, size, size);
                }
            }
        }
    }

}