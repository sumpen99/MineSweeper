package helper.matrix;
import helper.enums.Direction;
import helper.enums.Level;
import helper.io.IOHandler;
import helper.struct.BoardCell;
import helper.struct.BoardPosition;
import helper.struct.CellStack;


import static helper.methods.CommonMethods.getRandomInt;

public class GameBoard extends Matrix{
    BoardPosition newPos;
    BoardCell[] board;
    Direction[] directions = Direction.values();
    CellStack cellStack;
    int mines;
    public GameBoard(int boardsize) {
        super(boardsize);
        board = new BoardCell[size];
        newPos = new BoardPosition();
        cellStack = new CellStack();

    }

    public void initBoard(){
        int i = 0;
        while(i<size){board[i++] = new BoardCell();}
    }

    public void initLevel(Level level){
        mines = (int)(size*level.getValue());
        int i=0,pos;
        while(i++<mines){
            while(!validIndex((pos=getRandomInt(size))) || cellIsSet(pos));
            board[pos].setIsMine();
            board[pos].setIsSet();
            board[pos].setIndex(pos);
        }
    }

    public void initSurroundings(){
        int i=0;
        while(i<size){
            if(!board[i].getIsMine()){
                board[i].setSurroundingMines(searchMatrix(getRowFromIndex(i),getColFromIndex(i)));
                board[i].setIndex(i);
                board[i].setIsSet();
            }
            i++;
        }
    }

    public boolean cellIsSet(int index){
        return board[index].getIsSet();
    }

    @Override
    public boolean freeIndex(int index){
        return !board[index].getIsChecked();
    }

    @Override
    public void resetMatrix(){
        indexTaken = 0;
        int i = 0;
        while(i<size){board[i++].resetCell();}
    }

    public void drawToScreen(){
        IOHandler.printCurrentBoard(this);
    }

    public char getChar(int y,int x){
        return board[getIndex(y,x)].getChar();
    }

    public boolean hasSpace(){
        return indexTaken < (size-mines);
    }

    public void markCellAsChecked(int index){
        board[index].setIsChecked();
        indexTaken++;
    }

    public boolean cellIsMine(int index){
        return board[index].getIsMine();
    }

    public int getCellSurroundings(int row,int col){
        return board[getIndex(row,col)].getSurroundingsMines();
    }

    public void explodeBoard(int row,int col){
        if(getCellSurroundings(row,col) == 0){
            int i=0;
            while(i<directions.length){
                explodeDirection(row,col,directions[i++]);
            }
            if(cellStack.getItemsOnStack() > 0){
                BoardCell c = cellStack.pop();
                if(c != null){
                    int cellRow = getRowFromIndex(c.getIndex());
                    int cellCol = getColFromIndex(c.getIndex());
                    explodeBoard(cellRow,cellCol);
                }
            }
        }
    }

    public int searchMatrix(int row,int col){
        int sum = 0,i=0;
        while(i<directions.length){sum += searchDirection(row,col,directions[i++]);}
        return sum;
    }

    public int searchDirection(int row, int col,Direction dir){
        if(dir == Direction.NORTH){row-=1;}                  // NORTH
        else if(dir == Direction.SOUTH){row+=1;}             // SOUTH
        else if(dir == Direction.EAST){col+=1;}              // EAST
        else if(dir == Direction.WEST){col-=1;}              // WEST
        else if(dir == Direction.NORTH_EAST){row-=1;col+=1;} // NORTH EAST
        else if(dir == Direction.NORTH_WEST){row-=1;col-=1;} // NORTH WEST
        else if(dir == Direction.SOUTH_EAST){row+=1;col+=1;} // SOUTH EAST
        else if(dir == Direction.SOUTH_WEST){row+=1;col-=1;} // SOUTH WEST
        newPos.setValue(row,col,validRowCol(row,col));
        if(!newPos.validMove)return 0;
        return board[getIndex(newPos.row, newPos.col)].getIsMine() ? 1 : 0;
    }

    public void explodeDirection(int row,int col,Direction dir){
        if(dir == Direction.NORTH){row-=1;}                  // NORTH
        else if(dir == Direction.SOUTH){row+=1;}             // SOUTH
        else if(dir == Direction.EAST){col+=1;}              // EAST
        else if(dir == Direction.WEST){col-=1;}              // WEST
        else if(dir == Direction.NORTH_EAST){row-=1;col+=1;} // NORTH EAST
        else if(dir == Direction.NORTH_WEST){row-=1;col-=1;} // NORTH WEST
        else if(dir == Direction.SOUTH_EAST){row+=1;col+=1;} // SOUTH EAST
        else if(dir == Direction.SOUTH_WEST){row+=1;col-=1;} // SOUTH WEST
        newPos.setValue(row,col,validRowCol(row,col));
        if(!newPos.validMove)return;
        int index = getIndex(newPos.row, newPos.col);
        if(!board[index].getIsChecked()){
            markCellAsChecked(index);
            if(board[index].getSurroundingsMines() == 0){
                cellStack.push(board[index]);
            }
        }
    }

}
