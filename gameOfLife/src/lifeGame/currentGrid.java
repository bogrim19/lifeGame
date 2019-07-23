package lifeGame;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

public class currentGrid {

    public class cell {
        public boolean state;
        public short neighbours;
    }

    public static final int X = 10;
    public static final int Y = 10;
    public cell[][] gridCell = new cell[X][Y];


    currentGrid() {  //seed grid initialization
        Random r = new Random();

        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                gridCell[i][j] = new cell();
                gridCell[i][j].state = Math.random() > 0.4;
                gridCell[i][j].neighbours = 0;
            }
        }
    }
    currentGrid(int x) {  //seed grid initialization
        Random r = new Random();

        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                gridCell[i][j] = new cell();
                gridCell[i][j].state = false;
                gridCell[i][j].neighbours = 0;
            }
        }
    }

    void printGrid() {
        System.out.println("Current grid: ");
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++)
                if (gridCell[i][j].state) System.out.print("o ");
                else System.out.print("~ ");
            System.out.println();
        }
    }

    void countNeighbours() {
        boolean ghostMatrix[][] = new boolean[X + 2][Y + 2];

        //constructie colturi
        //stanga sus
        ghostMatrix[0][0] = gridCell[X - 1][Y - 1].state;
        //stanga jos
        ghostMatrix[X + 1][0] = gridCell[0][Y - 1].state;
        //dreapta sus
        ghostMatrix[0][Y + 1] = gridCell[X - 1][0].state;
        //dreaota jos
        ghostMatrix[X + 1][Y + 1] = gridCell[0][0].state;

        //constructie laturi
        //stanga
        for (int i = 1; i <= X; i++) {
            ghostMatrix[i][0] = gridCell[i - 1][Y - 1].state;
        }
        //jos
        for (int i = 1; i <= Y; i++) {
            ghostMatrix[X + 1][i] = gridCell[0][i - 1].state;
        }
        //dreapta
        for (int i = 1; i <= X; i++) {
            ghostMatrix[i][Y + 1] = gridCell[i - 1][Y - 1].state;
        }
        //sus
        for (int i = 1; i <= Y; i++) {
            ghostMatrix[0][i] = gridCell[X - 1][i - 1].state;
        }
        //constructie interior
        for (int i = 1; i <= X; i++) {
            for (int j = 1; j <= Y; j++) {
                ghostMatrix[i][j] = gridCell[i - 1][j - 1].state;
            }
        }


        //print ghost
        /*System.out.println("Ghost matrix:");
        for (int i = 0; i <= X+1; i++) {
            for (int j = 0; j <= Y+1; j++)
            {
                if (ghostMatrix[i][j]) System.out.print("o ");
                else System.out.print("~ ");}
            System.out.println();
        }*/
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                gridCell[i][j].neighbours = 0;
            }
        }

        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                if (ghostMatrix[i][j]) gridCell[i][j].neighbours++;
                if (ghostMatrix[i][j + 1]) gridCell[i][j].neighbours++;
                if (ghostMatrix[i][j + 2]) gridCell[i][j].neighbours++;
                if (ghostMatrix[i + 1][j]) gridCell[i][j].neighbours++;
                if (ghostMatrix[i + 1][j + 2]) gridCell[i][j].neighbours++;
                if (ghostMatrix[i + 2][j]) gridCell[i][j].neighbours++;
                if (ghostMatrix[i + 2][j + 1]) gridCell[i][j].neighbours++;
                if (ghostMatrix[i + 2][j + 2]) gridCell[i][j].neighbours++;
            }
        }
    }


    void printNeigh() {
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++)
                System.out.print(gridCell[i][j].neighbours);
            System.out.println();
        }
    }

    void tick() {
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                if (gridCell[i][j].neighbours < 2) gridCell[i][j].state = false;
                if (gridCell[i][j].neighbours > 3) gridCell[i][j].state = false;
                if (gridCell[i][j].neighbours == 3) gridCell[i][j].state = true;
            }

        }
    }

    void CSVRead(){
        String csvFile = "C:\\Users\\bogdan.barbus\\Downloads\\Collections\\Workshop\\gameOfLife\\src\\lifeGame\\myGrid.csv";
        BufferedReader br = null;
        String line;
        String cvsSplitBy = ";";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            String firstLine = br.readLine();
            int i=0;
            while ((line = br.readLine()) != null) {


                        //try {

                            String[] values = line.split(cvsSplitBy);
                        //} catch (NumberFormatException g) {}


                        for (int j = 0; j < Y; j++)
                        {
                            int aux = Integer.parseInt(values[j]);
                            //System.out.print(aux + " ");
                            if (aux == 1){

                                this.gridCell[i][j].state = true;
                                //System.out.print(gridCell[i][j].state + " ");
                            }

                            if (aux==0||values[j]==null) {
                                this.gridCell[i][j].state = false;
                                //System.out.print(gridCell[i][j].state + " ");
                            }

                        }
                        //System.out.println();



            i++;

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

