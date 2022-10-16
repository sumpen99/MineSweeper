package helper.matrix;
import helper.interfaces.IMatrix;
import java.util.Arrays;


public abstract class Matrix implements IMatrix {
    public int size,rows,columns,indexTaken;
    public int[] m;
    public Matrix(int type){
        rows = type;
        columns = type;
        size = type*type;
    }

    public boolean hasFreeSpace(){
        int cnt = 0;
        while(cnt<size)if(m[cnt++] == 0)return true;
        return false;
    }

    public boolean freeIndex(int index){
        return m[index] == 0;
    }

    public int getColFromIndex(int index){
        return index%columns;
    }

    public int getRowFromIndex(int index){
        return index/columns;
    }

    public void resetMatrix(){
        Arrays.fill(m,0);
        indexTaken = 0;
    }

    public int getIndex(int row,int col){
        if(!validRowCol(row,col))return -1;
        return (row*columns)+col;
    }

    public boolean validRowCol(int row,int col){
        return (row >= 0 && row < rows) && (col >= 0 && col < columns);
    }

    public boolean validIndex(int index){
        return index >= 0 && index < size;
    }

}