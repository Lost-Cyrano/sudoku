import java.util.Random;

public class SudokuGenerator {

    private static final int GRID_SIZE = 9;
    private int[][] grid = new int[GRID_SIZE][GRID_SIZE];
    private Random random = new Random();

    public SudokuGenerator() {
        fillGrid(0, 0);
    }

    public int[][] getGrid() {
        return grid;
    }

    private boolean fillGrid(int row, int col) {
        if (row == GRID_SIZE) return true;
        if (col == GRID_SIZE) return fillGrid(row + 1, 0);

        int[] nums = shuffleArray();
        for (int num : nums) {
            if (isSafe(row, col, num)) {
                grid[row][col] = num;
                if (fillGrid(row, col + 1)) return true;
                grid[row][col] = 0;
            }
        }
        return false;
    }

    private boolean isSafe(int row, int col, int num) {
        for (int x = 0; x < GRID_SIZE; x++) {
            if (grid[row][x] == num || grid[x][col] == num) return false;
            if (grid[row - row % 3 + x / 3][col - col % 3 + x % 3] == num) return false;
        }
        return true;
    }

    private int[] shuffleArray() {
        int[] nums = new int[GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) nums[i] = i + 1;
        for (int i = GRID_SIZE - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        return nums;
    }

    public static void main(String[] args) {
        SudokuGenerator generator = new SudokuGenerator();
        int[][] grid = generator.getGrid();
        for (int[] row : grid) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
