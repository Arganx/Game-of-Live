package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import model.Mechanics;
import model.Game;

public class Controller {

    @FXML
    private Canvas canvas;

    @FXML
    private Button start;

    @FXML
    private Label title;

    @FXML
    private Button stop;

    @FXML
    private Button step;


    private GraphicsContext gc;

    public GraphicsContext getGc() {
        return gc;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    @FXML
    public void initialize() {
        Mechanics mechanics = new Mechanics(30);
        double sizeOfSingleRectangle=canvas.getHeight()/ mechanics.getBoard().getSize();
        Game game = new Game(this,sizeOfSingleRectangle, mechanics);
        Thread thread = new Thread(game);
        thread.setDaemon(true);
        thread.start();

        mechanics.step();

        gc = canvas.getGraphicsContext2D();
        //board.getTable1()[5][5].setState(1);
        //board.getTable1()[0][2].setState(1);
        //board.getTable1()[32][2].setState(1);

        mechanics.clear(gc,Color.WHITE,canvas);
        mechanics.drawTable(gc,sizeOfSingleRectangle,canvas);

        step.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!game.isGameRunning()) {
                    mechanics.step();
                    mechanics.drawTable(gc,sizeOfSingleRectangle,canvas);
                }
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        int x = (int)(t.getX()/sizeOfSingleRectangle);
                        int y = (int)(t.getY()/sizeOfSingleRectangle);
                        if(mechanics.isWhichPanel()==true)
                        {
                            if(mechanics.getBoard().getTable1()[x][y].getState()==0)
                            {
                                mechanics.drawRectangle(gc, Color.GREEN, x, y, sizeOfSingleRectangle,0);
                                mechanics.getBoard().getTable1()[x][y].setState(1);
                                mechanics.drawWeb(gc,sizeOfSingleRectangle);
                                //drawTable(sizeOfSingleRectangle);
                            }
                            else
                            {
                                mechanics.drawRectangle(gc, Color.WHITE, x, y, sizeOfSingleRectangle,0);
                                mechanics.getBoard().getTable1()[x][y].setState(0);
                                mechanics.drawWeb(gc,sizeOfSingleRectangle);
                                //drawTable(sizeOfSingleRectangle);
                            }
                        }
                        else
                        {
                            if(mechanics.getBoard().getTable2()[x][y].getState()==0)
                            {
                                mechanics.drawRectangle(gc, Color.GREEN, x, y, sizeOfSingleRectangle,0);
                                mechanics.getBoard().getTable2()[x][y].setState(1);
                                mechanics.drawWeb(gc,sizeOfSingleRectangle);
                                //drawTable(sizeOfSingleRectangle);
                            }
                            else
                            {
                                mechanics.drawRectangle(gc, Color.WHITE, x, y, sizeOfSingleRectangle,0);
                                mechanics.getBoard().getTable2()[x][y].setState(0);
                                mechanics.drawWeb(gc,sizeOfSingleRectangle);
                                //drawTable(sizeOfSingleRectangle);
                            }
                        }

                    }
                });

        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game.setGameRunning(true);
            }
        });

        stop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game.setGameRunning(false);
            }
        });

    }

}
