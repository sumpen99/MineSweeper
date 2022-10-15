package helper.game;

import helper.enums.Level;
import helper.io.IOHandler;
import helper.matrix.GameBoard;
import helper.player.GamePlayer;
import helper.struct.BoardPosition;
import helper.struct.GameInfo;
import helper.struct.PassedCheck;

import static helper.methods.CommonMethods.*;

public class GameMode {
    GameBoard gameBoard;
    BoardPosition newPos;
    PassedCheck validInput;
    GameInfo gameInfo;
    Level level;

    public GameMode(int l){
        level = intToLevel(l);
        gameInfo = new GameInfo();
        validInput = new PassedCheck();
        newPos = new BoardPosition();
    }

    public void run(){
        gameInfo.timer.startClock();
        runGame();
        drawBoard();
        gameInfo.runningTime = gameInfo.timer.getTimePassed();
        IOHandler.printGameInfo(gameInfo);
        if(evaluateNewGame(IOHandler.askForNewGame())){
            resetStateOfSession();
            run();
        }
    }

    public void runGame(){
        String pos;
        while(!gameInfo.quit && gameBoard.hasSpace()){
            drawBoard();
            pos = IOHandler.askForNewPosition();
            if(validBoardPosition(pos)){makeNewMove(newPos.row,newPos.col);}

        }
    }

    public void makeNewMove(int row,int col){
        int index = gameBoard.getIndex(row,col);
        gameBoard.markCellAsChecked(index);
        if(gameBoard.cellIsMine(index)){
            gameInfo.foundAMine = true;
            gameInfo.quit = true;
        }
        else{
            gameBoard.explodeBoard(row,col);
        }

    }


    public boolean validBoardPosition(String pos){
        validInput.clear();
        int[] tryPos = verifyNewPos(pos,validInput);
        int index;
        if(tryPos != null && gameBoard.validIndex((index=gameBoard.getIndex(tryPos[0],tryPos[1]))) && gameBoard.freeIndex(index)){
            newPos.row = tryPos[0];
            newPos.col = tryPos[1];
            return true;
        }
        else{
            if(validInput.message.equals("quit")){
                gameInfo.aborted = true;
                gameInfo.quit = true;
            }
        }
        return false;
    }

    boolean evaluateNewGame(char c){
        return c == 'y';
    }

    public void resetStateOfSession(){
        gameInfo.reset();
        gameBoard.resetMatrix();
        gameBoard.initLevel(level);
        gameBoard.initSurroundings();
    }

    public void addPlayerName(){
        String name;
        if((validName(name = IOHandler.askForPlayerName()))){
            gameInfo.player = new GamePlayer(name);
            return;
        }
        addPlayerName();
    }

    public void setBoard(){
        int boardSize;
        while(((boardSize = stringIsInt(IOHandler.askForBoardSize())) == -1) || !validBoardSize(boardSize));
        gameBoard = new GameBoard(boardSize);
        gameBoard.initBoard();
        gameBoard.initLevel(level);
        gameBoard.initSurroundings();
    }

    public void drawBoard(){
        gameBoard.drawToScreen();
    }

    public boolean validName(String name){
        int size = name.length();
        return size > 0 && size < 255;
    }

    public boolean validBoardSize(int size){
        return size >= 10 && size <= 50;
    }
}
