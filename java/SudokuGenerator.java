import java.util.Random;

public class SudokuGenerator {

    private static final int GRID_SIZE = 9;
    private static final int SUBGRID_SIZE = 3;
    private static final int EMPTY = 0;

    private int[][] grid = new int[GRID_SIZE][GRID_SIZE];
    private Random random = new Random();

    public SudokuGenerator() {
        generateFullGrid();
    }

    // Public method to retrieve the generated grid
    public int[][] getGrid() {
        return grid;
    }

    // Main grid generation method
    private void generateFullGrid() {
        fillGrid(0, 0);
    }

    // Recursive backtracking method to fill the grid
    private boolean fillGrid(int row, int col) {
        if (row == GRID_SIZE) {
            return true; // Reached end of the grid
        }

        int nextRow = col == GRID_SIZE - 1 ? row + 1 : row;
        int nextCol = col == GRID_SIZE - 1 ? 0 : col + 1;

        // Shuffle numbers for randomness
        int[] numbers = generateShuffledNumbers();

        for (int number : numbers) {
            if (isSafeToPlace(row, col, number)) {
                grid[row][col] = number;
                if (fillGrid(nextRow, nextCol)) {
                    return true;
                }
                grid[row][col] = EMPTY; // Backtrack
            }
        }
        return false; // No valid number found
    }

    // Check if placing a number is valid
    private boolean isSafeToPlace(int row, int col, int number) {
        return !isInRow(row, number) && !isInCol(col, number) && !isInSubGrid(row, col, number);
    }

    private boolean isInRow(int row, int number) {
        for (int col = 0; col < GRID_SIZE; col++) {
            if (grid[row][col] == number) {
                return true;
            }
        }
        return false;
    }

    private boolean isInCol(int col, int number) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (grid[row][col] == number) {
                return true;
            }
        }
        return false;
    }

    private boolean isInSubGrid(int row, int col, int number) {
        int startRow = row - row % SUBGRID_SIZE;
        int startCol = col - col % SUBGRID_SIZE;

        for (int r = startRow; r < startRow + SUBGRID_SIZE; r++) {
            for (int c = startCol; c < startCol + SUBGRID_SIZE; c++) {
                if (grid[r][c] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    // Generate numbers 1-9 in random order
    private int[] generateShuffledNumbers() {
        int[] numbers = new int[GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            numbers[i] = i + 1;
        }

        for (int i = GRID_SIZE - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }
        return numbers;
    }

    // Print the grid (for debugging or testing)
    public void printGrid() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                System.out.print(grid[row][col] + " ");
            }
            System.out.println();
        }
    }

    // Main method to test the generator
    public static void main(String[] args) {
        SudokuGenerator generator = new SudokuGenerator();
        generator.printGrid();
    }
}
