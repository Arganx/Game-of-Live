package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Mechanics {

    private boolean whichPanel;
    private Board board;

    public Mechanics(int size)
    {
        board = new Board(size);
        whichPanel=true;
    }

    public boolean isWhichPanel() {
        return whichPanel;
    }

    public Board getBoard() {
        return board;
    }

    public static void copyTable(Cell[][] tab1, Cell[][] tab2, int size)//z 2 do 1
    {
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                tab1[i][j].setState(tab2[i][j].getState());
            }
        }
    }

    public void step()
    {
        int neighbours =0;
        if(whichPanel==true) //from table 1 we create table 2
        {
            copyTable(board.getTable2(),board.getTable1(),board.getSize());
            for (int y = 0; y < board.getSize(); y++)
            {
                for (int x = 0; x < board.getSize(); x++)
                {

                    neighbours=board.numberOfLivingNeighbours(x,y,board.getTable1());
                    //board.getTable2()[x][y]=board.getTable1()[x][y];
                    //System.out.print(neighbours);
                    //System.out.print(board.getTable2()[x][y].getState());
                    if(neighbours<2)
                    {
                        board.getTable2()[x][y].setState(0);
                    }
                    else if(neighbours>3)
                    {
                        board.getTable2()[x][y].setState(0);
                    }
                    else if(neighbours==3)
                    {
                        board.getTable2()[x][y].setState(1);
                    }
                }
            }
            whichPanel=false;
        }
        else
        {
            copyTable(board.getTable1(),board.getTable2(),board.getSize());
            for (int y = 0; y < board.getSize(); y++)
            {
                for (int x = 0; x < board.getSize(); x++)
                {

                    neighbours=board.numberOfLivingNeighbours(x,y,board.getTable2());
                    //board.getTable2()[x][y]=board.getTable1()[x][y];
                    //System.out.print(neighbours);
                    //System.out.print(board.getTable2()[x][y].getState());
                    if(neighbours<2)
                    {
                        board.getTable1()[x][y].setState(0);
                    }
                    else if(neighbours>3)
                    {
                        board.getTable1()[x][y].setState(0);
                    }
                    else if(neighbours==3)
                    {
                        board.getTable1()[x][y].setState(1);
                    }
                }
            }
            whichPanel=true;
        }
    }

    public void drawRectangle(GraphicsContext gc, Color color, int x, int y, double sizeOfSingleRectangle, int lineWith) {
        gc.setFill(color);
        gc.setLineWidth(lineWith);
        gc.fillRect(x*sizeOfSingleRectangle,y*sizeOfSingleRectangle,sizeOfSingleRectangle,sizeOfSingleRectangle);
    }

    public void clear(GraphicsContext gc, Color color, Canvas canvas) {
        gc.setFill(color);
        gc.setLineWidth(50);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }


    public void drawWeb(GraphicsContext gc,double sizeOfSingleRectangle)
    {
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.BLACK);
        gc.setLineWidth(1);
        for(int i=0;i<board.getSize();i++)
        {
            for(int j=0;j<board.getSize();j++)
            {
                gc.strokeRect(i*sizeOfSingleRectangle,j*sizeOfSingleRectangle,sizeOfSingleRectangle,sizeOfSingleRectangle);
            }
        }
    }

    public void drawTable(GraphicsContext gc,double sizeOfSingleRectangle,Canvas canvas)
    {
        clear(gc,Color.WHITE,canvas);
        for(int i=0;i<board.getSize();i++)
        {
            for(int j=0;j<board.getSize();j++)
            {
                if(whichPanel==true) {
                    if (board.getTable1()[i][j].getState() == 1) {
                        drawRectangle(gc, Color.GREEN, i, j, sizeOfSingleRectangle,0);
                    }
                    else
                    {
                        drawRectangle(gc, Color.WHITE, i, j, sizeOfSingleRectangle,0);
                    }
                }
                else
                {
                    if (board.getTable2()[i][j].getState() == 1) {
                        drawRectangle(gc, Color.GREEN, i, j, sizeOfSingleRectangle,0);
                    }
                    else
                    {
                        drawRectangle(gc, Color.WHITE, i, j, sizeOfSingleRectangle,0);
                    }
                }
            }
        }
        drawWeb(gc,sizeOfSingleRectangle);
    }


}
