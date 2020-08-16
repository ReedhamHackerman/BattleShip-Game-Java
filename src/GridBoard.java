import javax.swing.*;
import java.awt.*;

public class GridBoard extends JFrame {

    private static final long serialVersionUID = 1L;
    static JPanel panel;
    public static JPanel buttonPanel;
    public static JPanel yourProgressPanel;
    public static JPanel enemyProgressPanel;

    public static JLabel boatSizeLabel;
    public static JLabel yourHealthPercent;
    public static JLabel enemyHealthPercent;

    static JButton[][] button = new JButton[10][10];

    GridBagConstraints gbc;

    static int rows;
    static int columns;
    static int gridSize;

    /**
     * brief Constructor for enemy board GUI
     * details Creates enemy board frame for attacking the enemies board
     * param gridSize
     * (x and y size of board)
     * param height
     * (height of menu frame)
     * param width
     * (width of menu frame)
     */
    public GridBoard(int gridSize, int height, int width) {
        GridBoard.gridSize = gridSize;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(width, height));
        setVisible(true);
        setTitle("Opponent's Board");
        setResizable(false);

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(10, 10));
        for (rows = 0; rows < gridSize; rows++) {
            for (columns = 0; columns < gridSize; columns++) {
                button[rows][columns] = new JButton();
                button[rows][columns].setBackground(Color.GRAY);
                button[rows][columns].setPreferredSize(new Dimension(100, 100));
                button[rows][columns].addActionListener(new GridBoardController(rows, columns));
                buttonPanel.add(button[rows][columns]);
            }
        }

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.65;

        panel.add(buttonPanel, gbc);

        gbc.weighty = 0.15;
        gbc.gridy = 1;

        yourProgressPanel = new JPanel();
        yourProgressPanel.setLayout(new GridLayout());

        JLabel yourHealthLabel = new JLabel("Your health: ");
        yourProgressPanel.add(yourHealthLabel);

        yourHealthPercent = new JLabel("100%");
        yourProgressPanel.add(yourHealthPercent);

        panel.add(yourProgressPanel, gbc);

        gbc.weighty = 0.20;
        gbc.gridy = 2;

        enemyProgressPanel = new JPanel();
        enemyProgressPanel.setLayout(new GridLayout());

        JLabel enemyHealthLabel = new JLabel("Enemy's health: ");
        enemyProgressPanel.add(enemyHealthLabel);

        enemyHealthPercent = new JLabel("100%");
        enemyProgressPanel.add(enemyHealthPercent);

        panel.add(enemyProgressPanel, gbc);

        setContentPane(panel);
        pack();

    }
}
