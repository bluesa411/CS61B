import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import static com.google.common.truth.Truth.assertThat;

public class Percolation {
    // TODO: Add any necessary instance variables.
    private WeightedQuickUnionUF uf;
    private boolean[][] grid;
    private int sizeOfOpenSite;

    private int transindex(int row, int col){
        return row * grid[0].length + col + 1;
    }
    private boolean isvaildindex(int row, int col){
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }
    public Percolation(int N) {
        // TODO: Fill in this constructor.
        grid = new boolean[N][N];
        uf = new WeightedQuickUnionUF(N * N + 2);
        for(int i = 0; i < N; i++){
            uf.union(0, transindex(0, i));
            uf.union(transindex(N - 1, N - 1) + 1, transindex(N - 1, i));
        }
        sizeOfOpenSite = 0;
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        if(!isvaildindex(row, col)) throw new IllegalArgumentException("Invaild vertex");
        grid[row][col] = true;
        if(isvaildindex(row - 1, col) && grid[row - 1][col]){
            uf.union(transindex(row - 1, col), transindex(row, col));
        }
        if(isvaildindex(row, col - 1) && grid[row][col - 1]){
            uf.union(transindex(row, col - 1), transindex(row, col));
        }
        if(isvaildindex(row, col + 1) && grid[row][col + 1]){
            uf.union(transindex(row, col + 1), transindex(row, col));
        }
        if(isvaildindex(row + 1, col) && grid[row + 1][col]){
            uf.union(transindex(row + 1, col), transindex(row, col));
        }
        ++sizeOfOpenSite;
    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        if(!isvaildindex(row, col)) throw new IllegalArgumentException("Invaild vertex");
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        if(!isvaildindex(row, col)) throw new IllegalArgumentException("Invaild vertex");
        return grid[row][col] && uf.connected(0, transindex(row, col));
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return sizeOfOpenSite;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        return uf.connected(0, transindex(grid.length - 1, grid.length - 1) + 1);
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

}
