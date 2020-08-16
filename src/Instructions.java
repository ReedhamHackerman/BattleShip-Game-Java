import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Instructions extends JFrame {

    private static final long serialVersionUID = 1L;
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    static JButton mainmenu;
    public static JFrame rulesframe;

    /**
     * brief Constructor for instructions frame
     * details The instructions are displayed on the frame
     * param height
     * (Height of instructions frame)
     * param width
     * (Width of instructions frame)
     */
    public Instructions(int height, int width) {
        rulesframe = new JFrame();
        rulesframe.setTitle("Battleship Instructions");
        rulesframe.setPreferredSize(new Dimension(height, width));
        rulesframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel rulespanel = new JPanel();

        JTextArea text = new JTextArea("Game Instructions\nPlayers Have Following ships with sizes\n1xCarrier Boats(size=4)\n2xDestroyer Boats(size=3)\n3xSubmariens Boats(size=3)\n4xPatrol Boats(size=2)\n1) When the game starts you may begin to place your ships. \n2) Once you have placed all your ships the board will disappear and the opponent board will become visible, entering the attack stage.\n3) Click on the tiles you wish to fire torpedoes, a blue tile represents a missed shot, a red tile represents a hit.\n4) Once all your opponent's ships are sunk you win!");

        text.setRows(10);
        text.setColumns(10);

        rulespanel.add(text);
        rulespanel.setBorder(new LineBorder(Color.blue, 50));
        rulesframe.add(rulespanel, "Center");

        rulesframe.add(rulespanel);
        rulesframe.setResizable(true);
        rulesframe.setVisible(true);
        rulesframe.pack();

    }
}
