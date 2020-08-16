//Controller

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class MissileLaunched implements ActionListener
{

    int rows;
    int columns;
    int[][] field;
    String direction;

    Boolean vertical;
    Boolean horizontal;

    /**
     * @param rows    (The row on which the button was pressed)
     * @param columns (The column on which the button was pressed)
     * @brief Constructor for Controller
     * @details Provides logic for interactions with UI
     */
    public MissileLaunched(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.field = Battleship.getBoatPositions();
    }

    /**
     * @param event (The action event of the button being pressed)
     * @brief Upon button press, function called
     * @details Initiates boat being placed on board
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        this.field = Battleship.getBoatPositions();
        this.vertical = Battleship.vertical;
        this.horizontal = Battleship.horizontal;

        for (int row = 0; row < Battleship.gridSize; row++) {
            for (int column = 0; column < Battleship.gridSize; column++) {
                if (field[row][column] != 1) {
                    Battleship.hitsAndMisses[row][column] = field[row][column];
                }
            }
        }
        System.out.println("Row: " + rows + "  Column:" + columns);
        if (Battleship.gameState == "placeBoats") {
            if (Battleship.whichBoat < Battleship.boatSize.length) {
                placeWholeBoat(event);
            } else {
                Battleship.gameState = "gameStarted";

            }
            System.out.println(Battleship.gameState);
        } else if (Battleship.gameState == "gameStarted") {
            // Battleship.game.setVisible(false);
            for (int i = 0; i < BattleshipGUI.button.length; i++) {
                for (int j = 0; j < BattleshipGUI.button[i].length; j++) {
                    BattleshipGUI.button[i][j].setEnabled(false);
                }
            }
        }
    }

    /**
     * @return false (The spot is not empty, so it is not possible to place a
     * boat on that spot)
     * @brief Used to determine if free space on board for boat
     * @details Uses the boats position 2D array to check if the button clicked
     * has a free space
     */
    private Boolean freeSpot() {
        return Battleship.getBoatPositions()[rows][columns] == 0;
    }

    /**
     * @param boatNumber (which boat is currently being placed)
     * @brief Determines if spot is valid for placing boat
     * @details Based on the size of the boat, it checks the spaces ahead and if
     * taken returns false
     */
    private Boolean validSpot(int boatNumber) {
        try {
            if (vertical) {
                for (int i = 1; i < boatNumber; i++) {
                    if (field[rows + i][columns] != 0) {
                        return false;
                    }
                }
            } else if (horizontal) {
                for (int i = 1; i < boatNumber; i++) {
                    if (field[rows][columns + i] != 0) {
                        return false;
                    }
                }
            }
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    /**
     * brief Performs further validation and if possible, places piece on board
     * details This function checks which boat is being placed on the board,
     * then it blocks the surrounding board places from having pieces
     * placed there as per the rules
     * param event
     * (The action event of button being pressed, used here to
     * indicate boat placed on spot)
     */
    private void placeWholeBoat(ActionEvent event)
    {
        JButton buttonclicked = (JButton) event.getSource();// just checking
        // when button is
        // clicked
        System.out.println("Boat Size: " + Battleship.boatSize[Battleship.whichBoat]);
        if (Battleship.boatSize[Battleship.whichBoat] == 1 && freeSpot()
                && validSpot(Battleship.boatSize[Battleship.whichBoat])) {
            Battleship.tempField[rows][columns] = 1;
            buttonclicked.setBackground(Color.RED);
            buttonclicked.setEnabled(false);

            blockSurroundSpace();

            Battleship.setBoatPositions(field);

            System.out.println("----------Final:");
            for (int i = 0; i < field.length; i++) {
                System.out.println(Arrays.toString(field[i]));
            }
            System.out.println("----------------");

            Battleship.whichBoat++;
            if (Battleship.gameState == "placeBoats") {
                BattleshipGUI.boatSizeLabel.setText(Integer.toString(Battleship.boatSize[Battleship.whichBoat]));
            } else {
                BattleshipGUI.boatSizeLabel.setText("All boats placed.");
            }
        } else if (Battleship.boatSize[Battleship.whichBoat] == 2 && freeSpot()
                && validSpot(Battleship.boatSize[Battleship.whichBoat])) {
            try {
                Battleship.tempField[rows][columns] = 1;

                if (vertical) {
                    Battleship.tempField[rows + 1][columns] = 1;

                } else if (horizontal) {
                    Battleship.tempField[rows][columns + 1] = 1;
                }

                blockSurroundSpace();

                Battleship.setBoatPositions(field);

                System.out.println("New:");
                for (int i = 0; i < field.length; i++) {
                    System.out.println(Arrays.toString(field[i]));
                }
                System.out.println("**************");

                buttonclicked.setBackground(Color.RED);
                buttonclicked.setEnabled(false);
                if (vertical) {
                    BattleshipGUI.button[rows + 1][columns].setBackground(Color.RED);
                    BattleshipGUI.button[rows + 1][columns].setEnabled(false);

                } else if (horizontal) {
                    BattleshipGUI.button[rows][columns + 1].setBackground(Color.RED);
                    BattleshipGUI.button[rows][columns + 1].setEnabled(false);
                }
                Battleship.whichBoat++;
                if (Battleship.gameState == "placeBoats") {
                    BattleshipGUI.boatSizeLabel.setText(Integer.toString(Battleship.boatSize[Battleship.whichBoat]));
                } else {
                    BattleshipGUI.boatSizeLabel.setText("All boats placed.");
                }
            } catch (ArrayIndexOutOfBoundsException e) {

            }

        } else if (Battleship.boatSize[Battleship.whichBoat] == 3 && freeSpot()
                && validSpot(Battleship.boatSize[Battleship.whichBoat])) {
            try {
                Battleship.tempField[rows][columns] = 1;

                if (vertical) {
                    Battleship.tempField[rows + 1][columns] = 1;
                    Battleship.tempField[rows + 2][columns] = 1;

                } else if (horizontal) {
                    Battleship.tempField[rows][columns + 1] = 1;
                    Battleship.tempField[rows][columns + 2] = 1;
                }

                blockSurroundSpace();

                Battleship.setBoatPositions(field);

                System.out.println("New:");
                for (int i = 0; i < field.length; i++) {
                    System.out.println(Arrays.toString(field[i]));
                }
                System.out.println("**************");

                buttonclicked.setBackground(Color.RED);
                buttonclicked.setEnabled(false);
                if (vertical) {
                    BattleshipGUI.button[rows + 1][columns].setBackground(Color.RED);
                    BattleshipGUI.button[rows + 2][columns].setBackground(Color.RED);
                    BattleshipGUI.button[rows + 1][columns].setEnabled(false);
                    BattleshipGUI.button[rows + 2][columns].setEnabled(false);

                } else if (horizontal) {
                    BattleshipGUI.button[rows][columns + 1].setBackground(Color.RED);
                    BattleshipGUI.button[rows][columns + 2].setBackground(Color.RED);
                    BattleshipGUI.button[rows][columns + 1].setEnabled(false);
                    BattleshipGUI.button[rows][columns + 2].setEnabled(false);
                }
                Battleship.whichBoat++;
                if (Battleship.gameState == "placeBoats") {
                    BattleshipGUI.boatSizeLabel.setText(Integer.toString(Battleship.boatSize[Battleship.whichBoat]));
                } else {
                    BattleshipGUI.boatSizeLabel.setText("All boats placed.");
                }
            } catch (ArrayIndexOutOfBoundsException e) {

            }
        } else if (Battleship.boatSize[Battleship.whichBoat] == 4 && freeSpot()
                && validSpot(Battleship.boatSize[Battleship.whichBoat])) {
            try {
                Battleship.tempField[rows][columns] = 1;

                if (vertical) {
                    Battleship.tempField[rows + 1][columns] = 1;
                    Battleship.tempField[rows + 2][columns] = 1;
                    Battleship.tempField[rows + 3][columns] = 1;

                } else if (horizontal) {
                    Battleship.tempField[rows][columns + 1] = 1;
                    Battleship.tempField[rows][columns + 2] = 1;
                    Battleship.tempField[rows][columns + 3] = 1;
                }

                blockSurroundSpace();

                Battleship.setBoatPositions(field);

                System.out.println("New:");
                for (int i = 0; i < field.length; i++) {
                    System.out.println(Arrays.toString(field[i]));
                }
                System.out.println("**************");

                buttonclicked.setBackground(Color.RED);
                buttonclicked.setEnabled(false);
                if (vertical) {
                    BattleshipGUI.button[rows + 1][columns].setBackground(Color.RED);
                    BattleshipGUI.button[rows + 2][columns].setBackground(Color.RED);
                    BattleshipGUI.button[rows + 3][columns].setBackground(Color.RED);
                    BattleshipGUI.button[rows + 1][columns].setEnabled(false);
                    BattleshipGUI.button[rows + 2][columns].setEnabled(false);
                    BattleshipGUI.button[rows + 3][columns].setEnabled(false);

                } else if (horizontal) {
                    BattleshipGUI.button[rows][columns + 1].setBackground(Color.RED);
                    BattleshipGUI.button[rows][columns + 2].setBackground(Color.RED);
                    BattleshipGUI.button[rows][columns + 3].setBackground(Color.RED);
                    BattleshipGUI.button[rows][columns + 1].setEnabled(false);
                    BattleshipGUI.button[rows][columns + 2].setEnabled(false);
                    BattleshipGUI.button[rows][columns + 3].setEnabled(false);
                }
                Battleship.whichBoat++;
                BattleshipGUI.boatSizeLabel.setText("All boats placed.");
            } catch (ArrayIndexOutOfBoundsException e) {

            }
        }
        if (Battleship.whichBoat >= 10) {
            Battleship.gameState = "gameStarted";
            Battleship.game2.setVisible(true);

            for (int i = 0; i < BattleshipGUI.button.length; i++) {
                for (int j = 0; j < BattleshipGUI.button[i].length; j++) {
                    BattleshipGUI.button[i][j].setEnabled(false);
                }
            }
        }
    }

    /**
     * brief Blocks spaces around placed boat so other boats cannot be placed
     * details puts -1 in space around spots with boats, boats cannot be placed
     * on -1 spots. This function checks the entire board
     */
    public void blockSurroundSpace() {
        for (int row = 0; row < Battleship.gridSize; row++) {
            for (int column = 0; column < Battleship.gridSize; column++) {
                if (Battleship.tempField[row][column] == 1) {
                    field[row][column] = 1;
                    try {
                        if (field[row - 1][column - 1] != 1) {
                            field[row - 1][column - 1] = -1;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {

                    }
                    try {
                        if (field[row - 1][column + 1] != 1) {
                            field[row - 1][column + 1] = -1;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {

                    }
                    try {
                        if (field[row - 1][column] != 1) {
                            field[row - 1][column] = -1;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {

                    }
                    try {
                        if (field[row + 1][column] != 1) {
                            field[row + 1][column] = -1;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {

                    }
                    try {
                        if (field[row + 1][column - 1] != 1) {
                            field[row + 1][column - 1] = -1;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {

                    }
                    try {
                        if (field[row + 1][column + 1] != 1) {
                            field[row + 1][column + 1] = -1;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {

                    }
                    try {
                        if (field[row][column - 1] != 1) {
                            field[row][column - 1] = -1;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {

                    }
                    try {
                        if (field[row][column + 1] != 1) {
                            field[row][column + 1] = -1;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {

                    }
                }
            }
        }
    }

}
