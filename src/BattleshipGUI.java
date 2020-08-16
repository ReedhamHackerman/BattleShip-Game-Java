//View

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattleshipGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    JPanel panel = new JPanel();
    JPanel buttonPanel;
    JPanel directionPanel;
    JPanel progressPanel;
    JPanel menuButtonsPanel;

    GridBagConstraints gbc;

    public static JLabel boatSizeLabel;

    //creates a 10 x 10 matrix for the game board
    public static JButton[][] button = new JButton[10][10];

    public static JButton vertical;
    public static JButton horizontal;

    public static JButton mainMenu;
    public static JButton instructions;

    static int rows;
    static int columns;
    static int gridSize;

    /**
     * brief Constructor for GUI
     * details Sets configurations of frame and adds components to screen
     * param gridsize
     * (The size of the board grid, x by x)
     * param height
     * (The height of the frame)
     * param width
     * (The width of the frame)
     */
    public BattleshipGUI(int gridSize, int height, int width) {
        BattleshipGUI.gridSize = gridSize;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(width, height));
        setVisible(true);
        setTitle("Battleship");
        setResizable(true);
        panel = new JPanel();

        // using a GridBagLayout layout manager for its flexibility.
        // creates grid of rows and columns perfect for the battleship gameboard
        panel.setLayout(new GridBagLayout());

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(10, 10));

        //simply creating the main GUI, with a 10x10 grid w/ dimensions of 100x100
        for (rows = 0; rows < gridSize; rows++) {
            for (columns = 0; columns < gridSize; columns++) {
                button[rows][columns] = new JButton();
                button[rows][columns].setBackground(Color.GRAY); //default Gray color is easily interchangable here
                button[rows][columns].setPreferredSize(new Dimension(100, 100));

                //action listener allows for program to react to user button presses in correct grid quadrant
                button[rows][columns].addActionListener(new MissileLaunched(rows, columns));
                buttonPanel.add(button[rows][columns]);
            }
        }

        //setting up specific dimension/constraints for the grid layout manager
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.65;

        panel.add(buttonPanel, gbc);

        gbc.weighty = 0.05;
        gbc.gridy = 1;

        //logic behind boat/ship placement on board commences here
        directionPanel = new JPanel();
        directionPanel.setLayout(new GridLayout());

        JLabel instructionLabel = new JLabel("Place Boat Direction: ");
        directionPanel.add(instructionLabel);

        //simple dimensions and size constraints for the ships
        horizontal = new JButton("Horizontal");
        horizontal.addActionListener(new gameMenuActions()); //allows user to correctly interact with game menu on the main GUI
        horizontal.setPreferredSize(new Dimension(30, 50));
        horizontal.setEnabled(false);
        directionPanel.add(horizontal);
        vertical = new JButton("Vertical");
        vertical.addActionListener(new gameMenuActions());
        vertical.setPreferredSize(new Dimension(30, 50));
        directionPanel.add(vertical);

        panel.add(directionPanel, gbc);

        gbc.weighty = 0.20;
        gbc.gridy = 2;

        progressPanel = new JPanel();
        progressPanel.setLayout(new GridLayout());
        if (Battleship.boatSize[Battleship.whichBoat] == 1) {
            JLabel textLabel = new JLabel(" Placing Boat : ");
            progressPanel.add(textLabel);
        } else {
            JLabel textLabel = new JLabel(" Placing Boat Sized: ");
            progressPanel.add(textLabel);
        }
        // JLabel textLabel = new JLabel(" Placing Boat Sized: ");


        boatSizeLabel = new JLabel(Integer.toString(Battleship.boatSize[Battleship.whichBoat]));
        progressPanel.add(boatSizeLabel);

        panel.add(progressPanel, gbc);

        gbc.weighty = 0.10;
        gbc.gridy = 3;

        //creating the main menu, to help user easily navigate the game
        menuButtonsPanel = new JPanel();
        menuButtonsPanel.setLayout(new GridLayout());

        mainMenu = new JButton("Main Menu");
        mainMenu.addActionListener(new gameMenuActions());
        mainMenu.setPreferredSize(new Dimension(30, 50));
        menuButtonsPanel.add(mainMenu);
        instructions = new JButton("Instructions");

        //allows user to interact with the in-game instructions built by the original authors
        instructions.addActionListener(new gameMenuActions());
        instructions.setPreferredSize(new Dimension(30, 50));
        menuButtonsPanel.add(instructions);

        panel.add(menuButtonsPanel, gbc);

        setContentPane(panel);
        pack();
    }

}

class gameMenuActions implements ActionListener {

    /**
     * brief Constructor for action listener on the in game menu
     * details Allows user to interact with in-game main menu,
     * the logic creates virtual probes beneath each button and reacts accordingly.
     */
    public gameMenuActions() {

    }

    /**
     * brief Action listener on game menu
     * details Listener for Horizontal, Vertical, main menu and instructions button
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == BattleshipGUI.vertical) { //a vertical orientation is chosen for the ship placement
            Battleship.vertical = true;
            Battleship.horizontal = false;
            BattleshipGUI.vertical.setEnabled(false);   //disables user option for vertical orientation (already selected)
            BattleshipGUI.horizontal.setEnabled(true);  //enables horizontal orientation option, in case user changes their mind
        } else if (event.getSource() == BattleshipGUI.horizontal) { //a horizontal orientation is chosen for the ship placement
            Battleship.vertical = false;
            Battleship.horizontal = true;
            BattleshipGUI.vertical.setEnabled(true); //enables vertical orientation option, in case user changes their mind
            BattleshipGUI.horizontal.setEnabled(false); //disables user option for horizontal orientation (already selected)

            //hides everything and only shows main menu
        } else if (event.getSource() == BattleshipGUI.mainMenu) {
            Battleship.game.setVisible(false);
            Battleship.game2.setVisible(false);
            MainMenu.frame.setVisible(true);
        } else if (event.getSource() == BattleshipGUI.instructions) {   //displays in-game instructions written by authors
            Instructions rules = new Instructions(900, 300);
        }

    }

}
