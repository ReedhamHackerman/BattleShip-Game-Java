import java.util.Random;

public class Battleship
{

    static int gridSize = 10;
    static int[][] boatPositions = new int[gridSize][gridSize];
    static int[][] enemyBoatPositions = new int[gridSize][gridSize];
    public static int[][] hitsAndMisses = new int[gridSize][gridSize];
    public static int[][] enemyHitsAndMisses = new int[gridSize][gridSize];
    public static int[][] enemySpotFired = new int[gridSize][gridSize];
    public static int whichBoat;
    public static int[] boatSize = {2, 2, 2, 2, 3, 3, 3, 3, 3, 4};
    public static int currentBoatProgress;
    public static Boolean vertical = false;
    public static Boolean horizontal = true;
    public static int[][] tempField = new int[gridSize][gridSize];
    public static String gameState = "placeBoats";
    public static int enemysBoatsSunk;
    public static int playersBoatsSunk;

    public static BattleshipGUI game;
    public static GridBoard game2;

    /**
     * brief Main function
     * details creates game board through BattleshipGUI class
     * param args
     * (string array of input arguments for main function)
     */
    public static void main(String[] args) {

        MainMenu menu = new MainMenu(600, 600);

        setSampleEnemyBoatBoard();

    }

    /**
     * brief Gets boat positions array
     * details Returns an array containing the positions of the boats placed by
     * the player in a 2D array
     *
     * @return boatPositions (positions of boats in 2D array)
     */
    public static int[][] getBoatPositions() {
        return boatPositions;
    }

    /**
     * brief Sets boat positions array
     * details Updates values in array containing the positions of the boats
     * placed by the player in a 2D array
     * param updateBoats
     * (updated 2D array of boat positions)
     */
    public static void setBoatPositions(int[][] updateBoats) {
        boatPositions = updateBoats;
    }

    /**
     * brief Gets enemy boat positions array
     * details Returns an array containing the positions of the boats placed by
     * the player in a 2D array
     *
     * @return boatPositions (positions of boats in 2D array)
     */
    public static int[][] getEnemyBoatPositions() {
        return enemyBoatPositions;
    }

    /**
     * brief Sets enemy boat positions array
     * details Updates values in array containing the positions of the boats
     * placed by the player in a 2D array
     * param updateBoats
     * (updated 2D array of boat positions)
     */
    public static void setEnemyBoatPositions(int[][] updateBoats) {
        enemyBoatPositions = updateBoats;
    }

    /**
     * brief Resets game when called
     * details Zeroes all arrays and values that change during the game, as
     * well as closing existing games
     */
    public static void resetGame() {
        try {
            game.dispose();
            game2.dispose();
        } catch (NullPointerException e) {

        }
        for (int rows = 0; rows < gridSize; rows++) {
            for (int columns = 0; columns < gridSize; columns++) {
                try {
                    boatPositions[rows][columns] = 0;
                } catch (ArrayIndexOutOfBoundsException e) {

                }
                try {
                    enemyBoatPositions[rows][columns] = 0;
                } catch (ArrayIndexOutOfBoundsException e) {

                }
                try {
                    hitsAndMisses[rows][columns] = 0;
                } catch (ArrayIndexOutOfBoundsException e) {

                }
                try {
                    enemyHitsAndMisses[rows][columns] = 0;
                } catch (ArrayIndexOutOfBoundsException e) {

                }
                try {
                    enemySpotFired[rows][columns] = 0;
                } catch (ArrayIndexOutOfBoundsException e) {

                }
                try {
                    tempField[rows][columns] = 0;
                } catch (ArrayIndexOutOfBoundsException e) {

                }

            }
        }
        gameState = "placeBoats";
        whichBoat = 0;

        game = new BattleshipGUI(gridSize, 600, 600);
        game.setSize(600, 800);
        game.setVisible(false);

        game2 = new GridBoard(gridSize, 600, 600);
        game2.setSize(600, 800);
        game2.setLocation(600, 0);
        game2.setVisible(false);

        setSampleEnemyBoatBoard();
    }

    /**
     * brief Chooses from random enemy boards
     * details Gives boat values to enemyBoatPositions array randomly from sample boards
     */
    private static void setSampleEnemyBoatBoard() {
        Random rand = new Random();
        int board = rand.nextInt(2 - 0 + 1) + 0;
        if (board == 0) {
            int[] array0 = {-1, -1, -1, 0, 0, 0, 0, 0, 0, 0};
            enemyBoatPositions[0] = array0;
            int[] array1 = {1, 1, -1, 0, 0, 0, 0, 0, 0, 0};
            enemyBoatPositions[1] = array1;
            int[] array2 = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
            enemyBoatPositions[2] = array2;
            int[] array3 = {1, 1, 1, 1, -1, -1, 1, 1, 1, -1};
            enemyBoatPositions[3] = array3;
            int[] array4 = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
            enemyBoatPositions[4] = array4;
            int[] array5 = {-1, 1, 1, 1, -1, -1, 1, -1, -1, -1};
            enemyBoatPositions[5] = array5;
            int[] array6 = {-1, -1, -1, -1, -1, -1, 1, -1, 1, 1};
            enemyBoatPositions[6] = array6;
            int[] array7 = {0, 0, 0, 0, 0, -1, -1, -1, -1, -1};
            enemyBoatPositions[7] = array7;
            int[] array8 = {-1, -1, -1, -1, -1, -1, -1, -1, 0, 0};
            enemyBoatPositions[8] = array8;
            int[] array9 = {1, -1, 1, -1, 1, -1, 1, -1, 0, 0};
            enemyBoatPositions[9] = array9;
        } else if (board == 1) {
            int[] array0 = {1, -1, 1, -1, 1, -1, 1, -1, 0, 0};
            enemyBoatPositions[0] = array0;
            int[] array1 = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
            enemyBoatPositions[1] = array1;
            int[] array2 = {0, -1, 1, 1, -1, -1, -1, -1, 1, 1};
            enemyBoatPositions[2] = array2;
            int[] array3 = {-1, -1, -1, -1, -1, 1, 1, -1, -1, -1};
            enemyBoatPositions[3] = array3;
            int[] array4 = {1, 1, 1, -1, -1, -1, -1, -1, -1, -1};
            enemyBoatPositions[4] = array4;
            int[] array5 = {-1, -1, -1, -1, 0, -1, 1, 1, 1, -1};
            enemyBoatPositions[5] = array5;
            int[] array6 = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
            enemyBoatPositions[6] = array6;
            int[] array7 = {-1, 1, 1, 1, 1, -1, 0, 0, 0, 0};
            enemyBoatPositions[7] = array7;
            int[] array8 = {-1, -1, -1, -1, -1, -1, 0, 0, 0, 0};
            enemyBoatPositions[8] = array8;
            int[] array9 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            enemyBoatPositions[9] = array9;
        } else {
            int[] array0 = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
            enemyBoatPositions[0] = array0;
            int[] array1 = {-1, 1, -1, -1, 1, 1, -1, -1, 1, -1};
            enemyBoatPositions[1] = array1;
            int[] array2 = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
            enemyBoatPositions[2] = array2;
            int[] array3 = {-1, 1, -1, 1, 1, 1, -1, -1, -1, -1};
            enemyBoatPositions[3] = array3;
            int[] array4 = {-1, 1, -1, -1, -1, -1, -1, -1, 1, -1};
            enemyBoatPositions[4] = array4;
            int[] array5 = {-1, 1, -1, 1, 1, 1, 1, -1, 1, -1};
            enemyBoatPositions[5] = array5;
            int[] array6 = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
            enemyBoatPositions[6] = array6;
            int[] array7 = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
            enemyBoatPositions[7] = array7;
            int[] array8 = {-1, 1, -1, -1, 1, 1, -1, -1, 1, -1};
            enemyBoatPositions[8] = array8;
            int[] array9 = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
            enemyBoatPositions[9] = array9;
        }
    }
}
