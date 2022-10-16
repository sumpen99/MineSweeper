package program;

import helper.game.GameMode;
import helper.io.IOHandler;
import helper.struct.SMDateTime;

import static helper.methods.CommonMethods.evaluateInput;

public class MineSweeper {
    GameMode gameMode;
    public void setGlobal(){
        SMDateTime.initSMDateTime();
        IOHandler.initIOHandler();
    }
    public void runLoop(){
        char input;
        do {
            input = startGame();
            if(evaluateGameMode(input)){
                gameMode.addPlayerName();
                gameMode.setBoard();
                gameMode.run();
            }
        }while(!exit(input));
    }

    boolean evaluateGameMode(char c){
        boolean result = false;
        if(c == '1'){
            gameMode = new GameMode(1);
            result = true;
        }
        else if(c == '2'){
            gameMode = new GameMode(2);
            result = true;
        }
        else if(c == '3'){
            gameMode = new GameMode(3);
            result = true;
        }
        return result;
    }

    char startGame(){
        return evaluateInput(IOHandler.printGameMenu());
    }

    boolean exit(char c){
        return c == 'q';
    }
}
