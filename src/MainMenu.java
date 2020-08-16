import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MainMenu extends JFrame {

    private static final long serialVersionUID = 1L;
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    public static JFrame frame;
    public static JButton start;
    public static JButton rules;

    /**
     * brief Constructor for main menu GUI
     * details Creates menu frame with play game button and instructions button
     * param height (height of menu frame)
     * param width (width of menu frame)
     */
    public MainMenu(int height, int width) {
        frame = new JFrame();
        frame.setTitle("Battleship Game Using Java Swing!");
        frame.setPreferredSize(new Dimension(height, width));
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainpanel = new JPanel();
        // j
        mainpanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill) {
            // natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        JLabel label = new JLabel("Welcome to Battleship Game Using Java Swing! Let's Play!");
        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        c.fill = GridBagConstraints.CENTER;
        c.insets = new Insets(2, 2, 100, 2);
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.PAGE_START;
        mainpanel.add(label, c);

        start = new JButton("Start New Game");
        start.addActionListener(new MenuActions());
        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        c.fill = GridBagConstraints.CENTER;
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 1;
        c.gridy = 3;
        c.anchor = GridBagConstraints.CENTER;
        mainpanel.add(start, c);

        rules = new JButton("See Instructions");
        rules.addActionListener(new MenuActions());
        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        c.fill = GridBagConstraints.CENTER;
        c.insets = new Insets(10, 2, 2, 2);
        c.gridx = 1;
        c.gridy = 4;
        c.anchor = GridBagConstraints.CENTER;
        mainpanel.add(rules, c);

        mainpanel.setBorder(new LineBorder(Color.black, 50));
        frame.add(mainpanel, "Center");

        frame.add(mainpanel);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();

    }
}
