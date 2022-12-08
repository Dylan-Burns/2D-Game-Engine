/**
 * the purpose of this class is to assign the enums from ButtonAction to their corresponding actions and monitor their
 * status while the program is active
 */

package manager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;


public class InputManager implements KeyListener, MouseListener {

    //game engine object to pass to functions to allow access for modification of game objects
    private GameEngine engine;

    InputManager(GameEngine engine) {
        this.engine = engine; }

    // keyboard listener function
    @Override
    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();
        GameStatus status = engine.getGameStatus();
        ButtonAction currentAction = ButtonAction.NO_ACTION;
        switch(keyCode) {
            case KeyEvent.VK_UP -> {
                if (status == GameStatus.START_SCREEN || status == GameStatus.MAP_SELECTION) {
                    currentAction = ButtonAction.GO_UP;
                }
            }
            case KeyEvent.VK_SPACE -> currentAction = ButtonAction.JUMP;
            case KeyEvent.VK_DOWN -> {
                if (status == GameStatus.START_SCREEN || status == GameStatus.MAP_SELECTION) {
                    currentAction = ButtonAction.GO_DOWN;
                } else {
                    currentAction = ButtonAction.CROUCH;
                }
            }
            case KeyEvent.VK_RIGHT -> currentAction = ButtonAction.M_RIGHT;
            case KeyEvent.VK_LEFT -> currentAction = ButtonAction.M_LEFT;
            case KeyEvent.VK_ENTER -> currentAction = ButtonAction.SELECT;
            case KeyEvent.VK_ESCAPE ->  {
                if (status == GameStatus.RUNNING || status == GameStatus.PAUSED) {
                    currentAction = ButtonAction.PAUSE_RESUME;
                } else {
                    currentAction = ButtonAction.GO_TO_START_SCREEN;
                }
            }
            case KeyEvent.VK_F -> currentAction = ButtonAction.FIRE;
        }
        try {
            notifyInput(currentAction);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    // mouse listener
    @Override
    public void mousePressed(MouseEvent e) {
        if(engine.getGameStatus() == GameStatus.MAP_SELECTION){
            engine.selectMapViaMouse();
        }
    }

    // release key action response
    @Override
    public void keyReleased(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.VK_RIGHT || event.getKeyCode() == KeyEvent.VK_LEFT) {
            try {
                notifyInput(ButtonAction.ACTION_COMPLETED);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // verify non-null input then notify game engine object to illicit appropriate response
    private void notifyInput(ButtonAction action) throws MalformedURLException {
        if(action != ButtonAction.NO_ACTION)
            engine.receiveInput(action);
    }

    @Override
    public void keyTyped(KeyEvent arg0) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
