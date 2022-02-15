package me.mattyhd0.minecraftooltipgenerator.enums;

public class Minecraft {

    public static final char CODE = '§';

    public enum Color{

        BLACK("0", new java.awt.Color(0, 0 ,0)),
        DARK_BLUE("1", new java.awt.Color(0, 0 ,170)),
        DARK_GREEN("2", new java.awt.Color(0, 170 ,0)),
        DARK_AQUA("3", new java.awt.Color(0, 170 ,170)),
        DARK_RED("4", new java.awt.Color(170, 0 ,0)),
        PURPLE("5", new java.awt.Color(170, 0 ,170)),
        GOLD("6", new java.awt.Color(255, 170 ,0)),
        GRAY("7", new java.awt.Color(170, 170 ,170)),
        DARK_GRAY("8", new java.awt.Color(85, 85 ,85)),
        BLUE("9", new java.awt.Color(85, 85 ,255)),
        LIME("a", new java.awt.Color(85, 255 ,85)),
        AQUA("b", new java.awt.Color(85, 255 ,255)),
        RED("c", new java.awt.Color(255, 85 ,85)),
        LIGHT_PURPLE("d", new java.awt.Color(255, 85 ,255)),
        YELLOW("e", new java.awt.Color(255, 255 ,85)),
        WHITE("f", new java.awt.Color(255, 255 ,255));

        private String code;
        private java.awt.Color color;

        private Color(String code, java.awt.Color color){

            this.code = code;
            this.color = color;

        }

        public String getCode() {
            return code;
        }

        public java.awt.Color getColor() {
            return color;
        }

    }

    public enum Format{

        MAGIC("k"),
        BOLD("l"),
        STRIKETHROUGH("m"),
        UNDERLINE("n"),
        ITALIC("o"),
        RESET("r");

        private String code;

        private Format(String code){
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    public static String MAGIC_CHARS = "abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ0123456789[]-_{}<>¡!¿?#$%&/();:";

}
