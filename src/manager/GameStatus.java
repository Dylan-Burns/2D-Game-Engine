/**
 * the purpose of this class is to monitor and update the status of the game in response to user input or game objectives
 */

package manager;

public enum GameStatus {
    GAME_OVER,
    PAUSED,
    RUNNING,
    START_SCREEN,
    MAP_SELECTION,
    HELP_SCREEN,
    MISSION_PASSED,
    ABOUT_SCREEN
}
