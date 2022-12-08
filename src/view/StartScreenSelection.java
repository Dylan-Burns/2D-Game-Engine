/**
 * the purpose of this class is to respond to user input while on the main menu
 */

package view;

import java.net.MalformedURLException;
import java.net.URL;

public enum StartScreenSelection {
    START_GAME(0),
    VIEW_HELP(1),
    VIEW_ABOUT(2);
//    EASTER_EGG(3);

    private final int lineNumber;
    StartScreenSelection(int lineNumber){ this.lineNumber = lineNumber; }

    public StartScreenSelection getSelection(int number){
        switch(number) {
            case 0 -> { return START_GAME; }
            case 1 -> { return VIEW_HELP; }
            case 2 -> { return VIEW_ABOUT; }
//            case 3 -> { return EASTER_EGG; }
            default -> { return null; }
        }
    }
    int count = 0;
    public StartScreenSelection select(boolean toUp) {
        int selection;

        if(lineNumber > -1 && lineNumber < 3){
            selection = lineNumber - (toUp ? 1 : -1);

            switch(selection) {
                case -1 -> selection = 2;
                case 3 -> selection = 0;

            }
            System.out.println("line: " + selection);
            count++;
            System.out.println(count);


            return getSelection(selection);
        }
//        if(count > 12) easterEggFound();
        return null;
    }

    public int getLineNumber() {
        return lineNumber;
    }

//    public void easterEggFound() throws MalformedURLException {
//        return new Wi
////        return new URL("https://themushroomkingdom.net/smb_breakdown.shtml");
//    }
}
