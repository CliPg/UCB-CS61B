package game2048;

import java.util.Formatter;
import java.util.Observable;


/** The state of a game of 2048.
 *  @author CliPg
 */
public class Model extends Observable {
    /** Current contents of the board. */
    private Board board;
    /** Current score. */
    private int score;
    /** Maximum score so far.  Updated when game ends. */
    private int maxScore;
    /** True iff game is ended. */
    private boolean gameOver;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
        gameOver = false;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        int size = rawValues.length;
        board = new Board(rawValues, score);
        this.score = score;
        this.maxScore = maxScore;
        this.gameOver = gameOver;
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     *  */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed. */
    public int size() {
        return board.size();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        checkGameOver();
        if (gameOver) {
            maxScore = Math.max(score, maxScore);
        }
        return gameOver;
    }

    /** Return the current score. */
    public int score() {
        return score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        gameOver = false;
        board.clear();
        setChanged();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /** Tilt the board toward SIDE. Return true iff this changes the board.
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     * */
    public boolean tilt(Side side) {
        boolean changed;
        changed = false;
        // TODO: Modify this.board (and perhaps this.score) to account
        switch (side){
            case NORTH:
                changed = moveNorth();
                break;
            case SOUTH:
                changed = moveSouth();
                break;
            case WEST:
                changed = moveWest();
                break;
            case EAST:
                changed = moveEast();
                break;
        }
        checkGameOver();
        if (changed) {
            setChanged();
        }
        return changed;
    }

    public boolean moveNorth(){
        boolean isChanged = false;
        for(int col = 0; col < board.size(); col++){
            int mergeMax = 0;
            for(int row = 0; row < board.size(); row++){
                if (board.tile(col,row) != null){
                    mergeMax++;
                }
            }
            mergeMax /= 2;
            for(int row = board.size()-2; row >= 0; row--){
                Tile t = board.tile(col,row);
                if(t != null){
                    int currentRow = row;
                    while(currentRow+1 < board.size() && board.tile(col,currentRow+1) == null){
                        currentRow++;
                    }
                    board.move(col,currentRow,t);
                    t = board.tile(col,currentRow);
                    if(currentRow < board.size() - 1){
                        if(board.tile(col,currentRow+1) != null && board.tile(col,currentRow+1).value() == t.value() && mergeMax > 0){
                            board.move(col,currentRow+1,t);
                            score += t.value()*2;
                            mergeMax--;
                        }
                    }
                    isChanged = true;
                }
            }
        }
        return isChanged;
    }

    public boolean moveSouth(){
        boolean isChanged = false;
        for(int col = 0; col < board.size(); col++){
            int mergeMax = 0;
            for(int row = 0; row < board.size(); row++){
                if (board.tile(col,row) != null){
                    mergeMax++;
                }
            }
            mergeMax /= 2;
            for(int row = 1; row < board.size(); row++){
                Tile t = board.tile(col,row);
                if(t != null){
                    int currentRow = row;
                    while(currentRow-1 >= 0 && board.tile(col,currentRow-1) == null){
                        currentRow--;
                    }
                    board.move(col,currentRow,t);
                    t = board.tile(col,currentRow);
                    if(currentRow >= 1){
                        if(board.tile(col,currentRow-1) != null && board.tile(col,currentRow-1).value() == t.value() && mergeMax > 0){
                            board.move(col,currentRow-1,t);
                            score += t.value()*2;
                            mergeMax--;
                        }
                    }
                    isChanged = true;
                }
            }
        }
        return isChanged;
    }

    public boolean moveWest(){
        boolean isChanged = false;
        for(int row = 0; row < board.size(); row++){
            int mergeMax = 0;
            for(int col = 0; col < board.size(); col++){
                if (board.tile(col,row) != null){
                    mergeMax++;
                }
            }
            mergeMax /= 2;
            for(int col = 1; col < board.size(); col++){
                Tile t = board.tile(col,row);
                if(t != null){
                    int currentCol = col;
                    while(currentCol-1 >= 0 && board.tile(currentCol-1,row) == null){
                        currentCol--;
                    }
                    board.move(currentCol,row,t);
                    t = board.tile(currentCol,row);
                    if(currentCol >= 1){
                        if(board.tile(currentCol-1,row) != null && board.tile(currentCol-1,row).value() == t.value() && mergeMax > 0){
                            board.move(currentCol-1,row,t);
                            score += t.value()*2;
                            mergeMax--;
                        }
                    }
                    isChanged = true;
                }
            }
        }
        return isChanged;
    }

    public boolean moveEast(){
        boolean isChanged = false;
        for(int row = 0; row < board.size(); row++){
            int mergeMax = 0;
            for(int col = 0; col < board.size(); col++){
                if (board.tile(col,row) != null){
                    mergeMax++;
                }
            }
            mergeMax /= 2;
            for(int col = board.size()-2; col >= 0; col--){
                Tile t = board.tile(col,row);
                if(t != null){
                    int currentCol = col;
                    while(currentCol+1 < board.size() && board.tile(currentCol+1,row) == null){
                        currentCol++;
                    }
                    board.move(currentCol,row,t);
                    t = board.tile(currentCol,row);
                    if(currentCol < board.size()-1){
                        if(board.tile(currentCol+1,row) != null && board.tile(currentCol+1,row).value() == t.value() && mergeMax > 0){
                            board.move(currentCol+1,row,t);
                            score += t.value()*2;
                            mergeMax--;
                        }
                    }
                    isChanged = true;
                }
            }
        }
        return isChanged;
    }

    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    /** Determine whether game is over. */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     * */
    public static boolean emptySpaceExists(Board b) {
        // TODO: Fill in this function.
        boolean isEmptyExists = false;
        for(int col = 0; col < b.size(); col++){
            for(int row = 0; row < b.size(); row++){
                if(b.tile(col,row) == null){
                    isEmptyExists = true;
                }
            }
        }
        return isEmptyExists;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {
        // TODO: Fill in this function.
        boolean isMaxTileExists = false;
        for(int col = 0; col < b.size(); col++){
            for(int row = 0; row < b.size(); row++){
                if(b.tile(col,row) != null){
                    if(b.tile(col,row).value() == MAX_PIECE){
                        isMaxTileExists = true;
                    }
                }
            }
        }
        return isMaxTileExists;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board b) {
        // TODO: Fill in this function.
        boolean isOneMoveExists = emptySpaceExists(b);
        if(!isOneMoveExists){
            for(int col = 0; col < b.size()-1; col++){
                for(int row = 0; row < b.size()-1; row++){
                    if(b.tile(col,row).value() == b.tile(col+1,row).value() || b.tile(col,row).value() == b.tile(col,row+1).value()){
                        isOneMoveExists = true;
                    }
                }
            }
            for(int row = 0; row < b.size()-1; row++){
                if (b.tile(b.size()-1,row).value() == b.tile(b.size()-1,row+1).value()){
                    isOneMoveExists = true;
                }
            }
        }
        return isOneMoveExists;
    }


    @Override
     /** Returns the model as a string, used for debugging. */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    /** Returns whether two models are equal. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    /** Returns hash code of Model’s string. */
    public int hashCode() {
        return toString().hashCode();
    }
}
