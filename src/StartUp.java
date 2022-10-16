import program.MineSweeper;

public class StartUp {
    public static void main(String[] args){
        MineSweeper program;
        program = new MineSweeper();
        program.setGlobal();
        program.runLoop();
    }
}
