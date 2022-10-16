package helper.io;
import helper.matrix.GameBoard;
import helper.struct.BoardCell;
import helper.struct.GameInfo;
import java.util.Scanner;
import static helper.methods.CommonMethods.evaluateInput;


public class IOHandler {
    static IOHandler self;
    static boolean isSet;
    Scanner scannerIn;

    public IOHandler(){
        assert !IOHandler.isSet :"IOHandler is already set!";
        IOHandler.setInstance();
    }

    private static void setInstance(){
        IOHandler.isSet = true;
    }

    public static void initIOHandler(){
        if(self == null){
            self = new IOHandler();
            self.scannerIn = new Scanner(System.in);
        }
    }

    public static void printInt(int num){
        System.out.printf("%d", num);
    }

    public static void printBoolean(boolean b){
        System.out.printf("%b",b);
    }

    public static void printString(String str){
        System.out.printf("%s",str);
    }

    public static void printChar(char c){
        System.out.printf("%c",c);
    }

    public static void printFloat(float num){
        System.out.printf("%f", num);
    }

    public static String printGameMenu(){
        System.out.println("Welcome To MineSweeper!");
        System.out.println("Enter Level Easy (1) Medium (2) or Hard (3) Exit (q)");
        System.out.print("Enter: ");
        return self.scannerIn.nextLine();
    }

    public static void printGameInfo(GameInfo gameInfo){
        String msg = "";
        if(gameInfo.aborted){msg="";}
        else if(gameInfo.foundAMine){msg = "Sorry %s You Found A Mine!\n".formatted(gameInfo.player.name);}
        else{msg = "Congratulations %s!\n".formatted(gameInfo.player.name);}
        printString(msg);
        printString("Running Time For Last Game      :: %d sec\n".formatted((int)gameInfo.runningTime));
        printString("Games Played For This Session   :: %d\n".formatted(gameInfo.gamesPlayed));
    }

    public static String askForPlayerName(){
        System.out.printf("Enter Name For Player %n");
        System.out.print("Enter: ");
        return self.scannerIn.nextLine();
    }

    public static char askForNewGame(){
        System.out.printf("New Game? (y) (n)\n");
        System.out.print("Enter: ");
        return evaluateInput(self.scannerIn.nextLine());
    }

    public static String askForNewPosition(){
        System.out.print("Enter (Row Col): ");
        return self.scannerIn.nextLine();
    }

    public static String askForBoardSize(){
        System.out.println("Enter The Size Of Board You Would Like To Play (min 10 max 50)");
        System.out.print("Enter: ");
        return self.scannerIn.nextLine();
    }

    public static void printCurrentBoard(GameBoard m){
        int x,y = 0;
        printCellStart();
        for(x = 0;x<m.columns;x++){printColBase(x);}
        printString("\n");
        for(;y < m.rows;y++){
            printRowIndex(y);
            for(x = 0;x<m.columns;x++){
                char value = m.getChar(y,x);
                if(x == m.columns-1)printCellEnd(value);
                else printCellBase(value);
            }
            if(y < m.rows-1){
                printRow(m.columns*4);
            }
            else printString("\n");
        }
    }

    public static void printRowIndex(int value){
        if(value < 10)printString(" %d  ".formatted(value));
        else printString(" %d ".formatted(value));
    }

    public static void printCellStart(){
        printString("    ");
    }

    public static void printRowStart(){
        printString("   |");
    }

    public static void printColBase(int value){
        if(value < 10)printString(" %d  ".formatted(value));
        else printString(" %d ".formatted(value));
    }

    public static void printCellBase(int value){
        printString(" %d  ".formatted(value));
    }

    public static void printCellBase(char value){
        printString(" %c |".formatted(value));
    }

    public static void printCellEnd(char value){
        printString(" %c ".formatted(value));
    }

    public static void printRow(int size){
        int cnt = 0;
        printString("\n");
        printCellStart();
        while(cnt++ < size){printChar('-');}
        printString("\n");
    }

    public static void printBoardCell(BoardCell cell){
        printString("IsMine: %b IsChecked: %b IsSet: %b SurroundingMines: %d Index: %d char: %c\n".formatted(
                cell.getIsMine(), cell.getIsChecked(),cell.getIsSet(),cell.getSurroundingsMines(),cell.getIndex(),cell.getChar()));
    }


}
