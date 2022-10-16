package helper.struct;

public class CellStack {
    public BoardCell[] cells;
    int itemsOnStack;

    public CellStack(){
        itemsOnStack = 0;
    }

    public int getItemsOnStack(){
        return itemsOnStack;
    }

    public void push(BoardCell cell){
        if(cells == null){
            cells = new BoardCell[1];
            cells[0] = cell;
            itemsOnStack = 1;
        }
        else{
            int i = 0;
            BoardCell[] temp = new BoardCell[itemsOnStack+1];
            while(i<itemsOnStack){
                temp[i] = cells[i];
                i++;
            }
            temp[i] = cell;
            cells = temp;
            itemsOnStack++;
        }
    }

    public BoardCell pop(){
        if(itemsOnStack <= 0)return null;
        BoardCell c = cells[itemsOnStack-1];
        removeLast();
        return c;
    }

    public void removeLast(){
        int newSize = itemsOnStack-1;
        if(newSize <= 0)cells = null;
        else{
            int i = 0;
            BoardCell[] temp = new BoardCell[newSize];
            while(i<newSize){
                temp[i] = cells[i];
                i++;
            }
            cells = temp;
        }
        itemsOnStack = newSize;
    }

    public void clear(){
        cells = null;
        itemsOnStack = 0;
    }
}
