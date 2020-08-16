import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuActions implements ActionListener {

    /**
     * brief Constructor for main menu action listener
     * details creates listener object for each button
     */
    public MenuActions() {
    }

    /**
     * brief which actions to perform based on button
     * details Which action to perform on button click. Start button starts
     * game, instructions button opens instructions
     * param event
     * (the event for the listener)
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == MainMenu.start) {
            Battleship.resetGame();
            Battleship.game.setVisible(true);
            MainMenu.frame.setVisible(false);
        }
        if (event.getSource() == MainMenu.rules) {
            Instructions rules = new Instructions(900, 300);
        }

    }

}
