package business.entities;

import business.GameManager;

import java.awt.*;

/**
 * Clase que se encargará de manejar toda la lógica referente al jugador y sus movimientos.
 */
public class Human extends Player implements Runnable {

    private GameManager gameManager;

    /**
     * Constructor de Human.
     *
     * @param board       Board donde están colocadas los barcos del jugador.
     * @param delay
     * @param gameManager GameManager which controls the game status.
     */
    public Human(Board board, int delay, GameManager gameManager) {
        super(board, Color.WHITE, delay);
        this.gameManager = gameManager;
    }

    /**
     * Constructor de Human.
     *
     * Mostly used when reading a file to load the player status.
     *
     * @param alive Boolean with the player's status.
     * @param recharging Boolean indicating if the player is reloading.
     * @param numberOfAttacks Integer with the number of attacks performed during the game.
     * @param board Board where the ships are placed.
     * @param gameManager GameManger which controls the game status.
     */
    public Human(boolean alive, boolean recharging, int numberOfAttacks, Board board, boolean[][] attacked, GameManager gameManager, int delay){
        super(board, recharging, alive, numberOfAttacks, attacked, Color.WHITE, delay);
        this.gameManager = gameManager;

    }

    @Override
    public void run() {
        while (!isStop() && isAlive()) {
            try {
                if (isRecharging()) {
                    Thread.sleep(getDelay());
                    setRecharging(false);
                    gameManager.updatePhase("Attack");
                    System.out.println("Human");
                }
            } catch (InterruptedException e) {
                /* We catch the interrupted exception but don't show any kind of message. */
            }
        }
        if (!isAlive()) {
            gameManager.updatePhase("Dead");
        }
        setRecharging(true);
    }
}
