package me.mattyhd0.minecraftooltipgenerator;

public class Util {

    public static String getRandomString(String chars, int length){

        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < length; i++){
            stringBuilder.append( chars.charAt((int) (chars.length()*Math.random())) );
        }

        return stringBuilder.toString();
    }

}
