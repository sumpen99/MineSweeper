package helper.struct;

public class BoardCell {
    boolean isMine,isChecked,isSet;
    int surroundingMines,index;
    char cellChar;

    public int getIndex(){
        return index;
    }

    public boolean getIsMine(){
        return isMine;
    }

    public boolean getIsChecked(){
        return isChecked;
    }

    public boolean getIsSet(){
        return isSet;
    }

    public int getSurroundingsMines(){
        return surroundingMines;
    }

    public void setIsMine(){
        isMine = true;
        cellChar = '¤';
    }

    public void setIndex(int idx){
        index = idx;
    }

    public void setIsSet(){
        isSet = true;
    }

    public void setIsChecked(){
        isChecked = true;
    }

    public void setSurroundingMines(int value){
        surroundingMines = value;
        cellChar = "%d".formatted(value).charAt(0);
    }

    public void resetCell(){
        isMine = false;
        isChecked = false;
        isSet = false;
        surroundingMines = 0;
        cellChar = ' ';
    }

    public char getChar(){
        return isChecked ? cellChar : ' ';
    }
}
