package helper.methods;

import helper.enums.Level;
import helper.struct.PassedCheck;

public class CommonMethods {

    public static Level intToLevel(int level){
        if(level == 1)return Level.EASY;
        if(level == 2)return Level.MEDIUM;
        if(level == 3)return Level.HARD;
        return null;
    }

    public static int getRandomInt(int maxValue){
        double val = Math.random()*1000;
        return (int)val%maxValue;
    }

    public static int stringIsInt(String s){
        try{
            int num = Integer.parseInt(s);
            return num;
        }
        catch( Exception err){
            return -1;
        }
    }

    public static int[] verifyNewPos(String s, PassedCheck result){
        try{
            int row,col;
            String[] pos = s.split(" ");
            row = Integer.parseInt(pos[0]);
            col = Integer.parseInt(pos[1]);
            return new int[]{row,col};
        }
        catch( Exception err){
            result.passed = false;
            result.message = s;
            return null;
        }
    }

    public static char evaluateInput(String str){
        if((str = str.trim()).length() == 0)return 0;
        return str.charAt(0);
    }
}
