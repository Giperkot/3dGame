package controller;

import logic.GameLogic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyController implements KeyListener {

    private GameLogic gameLogic;

    public KeyController(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyChar() + " " + e.getKeyCode());

        switch (e.getKeyCode()) {
            case 81:
                // q
                gameLogic.rotate(-0.2);
                break;
            case 87:
                // w
                gameLogic.goForward();
                break;
            case 69:
                // e
                gameLogic.rotate(+0.2);
                break;
            case 68:
                // d
                gameLogic.goRight();
                break;
            case 83:
                // s
                gameLogic.goBackward();
                break;
            case 65:
                // a
                gameLogic.goLeft();
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
};
