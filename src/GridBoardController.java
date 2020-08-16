import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

public class GridBoardController implements ActionListener {

    int rows;
    int columns;
    int[][] field;
    int[][] enemyField;

    JButton buttonclicked;

    /**
     * brief Constructor for Controller of attacking enemy board
     * details Provides logic for interactions with UI
     * param rows
     * (The row on which the button was pressed)
     * param columns
     * (The column on which the button was pressed)
     */
    public GridBoardController(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.field = Battleship.getEnemyBoatPositions();
        this.enemyField = Battleship.getBoatPositions();
    }

    /**
     * brief action performed when button clicked on enemy board
     * details if button pressed checks if the attack was a hit or a miss and
     * then allows AI a turn
     * param event
     * (the event for the listener)
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        buttonclicked = (JButton) event.getSource();// just checking when button
        // is clicked
        buttonclicked.setEnabled(false);
        this.field = Battleship.getEnemyBoatPositions();
        this.enemyField = Battleship.getBoatPositions();
        for (int row = 0; row < Battleship.gridSize; row++) {
            for (int column = 0; column < Battleship.gridSize; column++) {
                if (field[row][column] != 1) {
                    Battleship.hitsAndMisses[row][column] = field[row][column];
                }
            }
        }
        for (int row = 0; row < Battleship.gridSize; row++) {
            for (int column = 0; column < Battleship.gridSize; column++) {
                if (enemyField[row][column] != 1) {
                    Battleship.enemyHitsAndMisses[row][column] = enemyField[row][column];
                }
            }
        }
        if (field[rows][columns] == 1) {
            System.out.println("HIT");

            buttonclicked.setBackground(Color.RED);
            JOptionPane.showMessageDialog(null, "You Hit Enemy Ship");
            Battleship.hitsAndMisses[rows][columns] = field[rows][columns];
            Battleship.enemysBoatsSunk++;
            GridBoard.enemyHealthPercent
                    .setText((100 - Battleship.enemysBoatsSunk * 100 / 20) + "%");
        } else {
            System.out.println("MISS");
            buttonclicked.setBackground(Color.BLUE);
            Battleship.hitsAndMisses[rows][columns] = field[rows][columns];
        }

        checkWin();

        computerTurn();

    }

    /**
     * brief Checks if game has been won
     * details This function will compare the hitsAndMisses array and
     * boatPositions array and end the game when all boats have been
     * sunk.
     */
    private void checkWin() {

        if (Battleship.gameState != "gameFinished") {
            System.out.println("Enemy boats fired: " + Battleship.playersBoatsSunk);

            if (Arrays.deepEquals(Battleship.hitsAndMisses, field) || Battleship.enemysBoatsSunk == 20) {
                System.out.println("You win!");
                Battleship.gameState = "gameFinished";
                JOptionPane.showMessageDialog(null, "All ships sunk, you win!");
            }
            if (Arrays.deepEquals(Battleship.enemyHitsAndMisses, field) || Battleship.playersBoatsSunk == 20) {
                System.out.println("You lose!");
                Battleship.gameState = "gameFinished";
                JOptionPane.showMessageDialog(null, "Computer sunk all ships, you lose!");
            }

        } else if (Battleship.gameState == "gameFinished") {
            for (int i = 0; i < BattleshipGUI.button.length; i++) {
                for (int j = 0; j < BattleshipGUI.button[i].length; j++) {
                    GridBoard.button[i][j].setEnabled(false);
                }
            }
        }
    }

    /**
     * brief Function for computer's attacks
     * details Randomly hits spot and determines if it was a hit or miss
     */
    private void computerTurn()
    {
        Boolean shotFired = false;
        Random randRow = new Random();
        Random randColumn = new Random();
        int row;
        int column;
        int count = 0;
        while (!shotFired) {
            row = randRow.nextInt(9 - 0 + 1) + 0;
            column = randColumn.nextInt(9 - 0 + 1) + 0;
            System.out.println("Random row: " + row);
            System.out.println("Random column: " + column);
            if (Battleship.enemySpotFired[row][column] == 0) {
                if (enemyField[row][column] == 1) {

                    System.out.println("Enemy hit: Row: " + row + " Column: " + column);
                    Battleship.enemyHitsAndMisses[row][column] = enemyField[row][column];
                    Battleship.enemySpotFired[row][column] = 1;
                    BattleshipGUI.button[row][column].setBackground(new Color(102, 33, 35));
                    BattleshipGUI.button[row][column].setText("X");
                    Battleship.playersBoatsSunk++;
                    JOptionPane.showMessageDialog(null, "Enemy hit Your Ship at: Row: " + row + " Column: " + column);
                    GridBoard.yourHealthPercent
                            .setText((100 - Battleship.playersBoatsSunk * 100 / 20) + "%");
                } else {
                    System.out.println("Enemy miss: Row: " + row + " Column: " + column);
                    Battleship.enemyHitsAndMisses[row][column] = enemyField[row][column];
                    Battleship.enemySpotFired[row][column] = -1;
                    BattleshipGUI.button[row][column].setBackground(Color.BLUE);
                    BattleshipGUI.button[row][column].setText("X");
                }
                shotFired = true;
            } else {
                System.out.println("Already fired here.");
            }
            if (count == 100) {
                System.out.println("Too many computer attempts made");
                for (int i = 0; i < Battleship.enemySpotFired.length; i++) {
                    System.out.println(Arrays.toString(Battleship.enemySpotFired[i]));
                }
                shotFired = true;
            }
            count++;
        }
        checkWin();
    }

}
