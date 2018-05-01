package model;

import javafx.concurrent.Task;
import sample.Controller;

public class Game extends Task {
    private boolean gameRunning;
    private Controller controller;
    private double sizeOfsingleRectangle;
    private Mechanics mechanics;

    public Game(Controller controller,double sizeOfsingleRectangle, Mechanics mechanics) {
        this.gameRunning = false;
        this.controller=controller;
        this.sizeOfsingleRectangle=sizeOfsingleRectangle;
        this.mechanics = mechanics;
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    @Override
    protected Object call() throws Exception {
        while(true)
        {
            while(gameRunning)
            {
                mechanics.step();
                mechanics.drawTable(controller.getGc(),sizeOfsingleRectangle,controller.getCanvas());
                Thread.sleep(300);
            }
            Thread.sleep(1000);
        }
    }
}
