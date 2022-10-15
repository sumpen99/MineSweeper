package helper.interfaces;

public interface IMatrix {
    void resetMatrix();
    boolean hasFreeSpace();
    boolean freeIndex(int index);
    int getColFromIndex(int index);
    int getRowFromIndex(int index);
    int getIndex(int row,int col);
    boolean validRowCol(int row,int col);
    boolean validIndex(int index);

}
