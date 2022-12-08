/**
 * the purpose of this class is to store the state of the various input listeners (i.e. mouse and keyboard input)
 * and respond accordingly to achieve the desired response from the InputManager class
 */

package manager;

public enum ButtonAction {
    JUMP,               // UP arrow key
    M_RIGHT,            // RIGHT arrow key
    M_LEFT,             // LEFT arrow key
    CROUCH,             // DOWN arrow key
    FIRE,               // F key
    START,              // ENTER key
    PAUSE_RESUME,       // ESC key
    ACTION_COMPLETED,
    SELECT,
    GO_UP,
    GO_DOWN,
    GO_TO_START_SCREEN,
    NO_ACTION
}
