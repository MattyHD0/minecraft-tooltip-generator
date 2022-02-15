package me.mattyhd0.minecraftooltipgenerator.generator;

import me.mattyhd0.minecraftooltipgenerator.Util;
import me.mattyhd0.minecraftooltipgenerator.enums.Minecraft;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TooltipBuilder {

    private static final int FONT_SIZE = 50;
    private static final double SHADOW_COLOR = 0.2;
    private static final int TEXT_OFFSET = (int) (FONT_SIZE *0.2);

    private static final int LINE_OFFSET = (int)(FONT_SIZE *0.2);
    private static final int BACKGROUND_OFFSET = (int)(FONT_SIZE *0.08);
    private static final int TEXT_SHADOW_OFFSET = (int)(FONT_SIZE *0.08);

    private static final int LINE_SIZE = (int)(FONT_SIZE *0.10);

    private static final int NAME_LORE_DIVIDER_SIZE = FONT_SIZE /2;

    private static final int UNDERLINE_STRIKETHROUGH_LINE_HEIGH = (int) (FONT_SIZE *0.05);
    private static final int UNDERLINE_STRIKETHROUGH_LINE_HEIGH_BOLD = UNDERLINE_STRIKETHROUGH_LINE_HEIGH*2;

    private Font font = new Font(Font.SANS_SERIF, Font.PLAIN, (int) FONT_SIZE);
    private boolean textShadow = true;
    private boolean betterMagic = false;


    private String code = String.valueOf(Minecraft.CODE);

    private String name = "";
    private List<String> lore = new ArrayList<>();

    public TooltipBuilder(){}

    public TooltipBuilder setFont(Font font){
        this.font = font;
        return this;
    }

    public TooltipBuilder setTextShadow(boolean textShadow){
        this.textShadow = textShadow;
        return this;
    }

    public TooltipBuilder setBetterMagic(boolean betterMagic){
        this.betterMagic = betterMagic;
        return this;
    }

    public TooltipBuilder setName(String name){
        this.name = name;
        return this;
    }

    public TooltipBuilder setLore(List<String> lore){
        this.lore = lore;
        return this;
    }

    public TooltipBuilder setLore(String lore){
        this.lore = Arrays.asList(lore.split("\\\\n"));
        return this;
    }

    public TooltipBuilder addLore(String line){
        this.lore.add(line);
        return this;
    }

    public TooltipBuilder setLore(int index, String line){
        this.lore.set(index, line);
        return this;
    }

    public TooltipBuilder removeLore(String line){
        this.lore.remove(line);
        return this;
    }

    public TooltipBuilder removeLore(int index){
        this.lore.remove(index);
        return this;
    }

    public TooltipBuilder setColorCode(String code){
        this.code = code;
        return this;
    }

    public BufferedImage asBufferedImage(){

        double maxTextWidth = getTextWidth(font, name);
        double maxTextHeight = getTextHeight(font, name)+(lore.size() > 0 ? NAME_LORE_DIVIDER_SIZE : 0)+TEXT_OFFSET+LINE_OFFSET+BACKGROUND_OFFSET+TEXT_SHADOW_OFFSET;

        for(String loreLine: lore){

            double number = getTextWidth(font, loreLine);
            if(number > maxTextWidth) maxTextWidth = number;
            maxTextHeight += getTextHeight(font, loreLine);

        }

        int width = (int) maxTextWidth+TEXT_OFFSET*2; //600;
        int height = lore.size() == 0 ? (int) (1.5*getTextHeight(font, name)) : (int) maxTextHeight; //300;

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setColor(new Color(0, 0, 0));
        graphics2D.fillRect(0, 0, width, height);

        graphics2D.setColor(new Color(22, 8, 24));
        graphics2D.fillRect(BACKGROUND_OFFSET, BACKGROUND_OFFSET, width-BACKGROUND_OFFSET*2, height-BACKGROUND_OFFSET*2);

        graphics2D.setColor(new Color(46, 6, 95));

        //LEFT TOP > LEFT BOTTOM
        graphics2D.fillRect(LINE_OFFSET, LINE_OFFSET, LINE_SIZE, height-LINE_OFFSET*2);
        //LEFT TOP > RIGHT TOP
        graphics2D.fillRect(LINE_OFFSET, LINE_OFFSET, width-LINE_OFFSET*2, LINE_SIZE);

        //LEFT BOTTOM > RIGHT BOTTOM
        graphics2D.fillRect(LINE_OFFSET, height-LINE_OFFSET-LINE_SIZE, width-LINE_OFFSET-LINE_OFFSET, LINE_SIZE);
        //RIGHT TOP > RIGHT BOTTOM
        graphics2D.fillRect(width-LINE_OFFSET-LINE_SIZE, LINE_OFFSET, LINE_SIZE, height-LINE_OFFSET*2);

        graphics2D.setColor(new Color(255, 255, 255));


        int textCurrentX = LINE_OFFSET+BACKGROUND_OFFSET+TEXT_OFFSET;
        int textCurrentY = (int) (LINE_OFFSET+BACKGROUND_OFFSET+getTextHeight(font, name)*0.70);

        graphics2D.setFont(font);
        drawColoredText(graphics2D, name, textCurrentX, textCurrentY);

        textCurrentY += getTextHeight(font, name)+NAME_LORE_DIVIDER_SIZE;

        int i = 1;
        for(String line: lore){

            drawColoredText(graphics2D, line, textCurrentX, textCurrentY);
            textCurrentY += getTextHeight(font, line);

            i++;
        }

        graphics2D.setColor(new Color(0, 255, 0));

        return bufferedImage;

    }

    public void save(String outputDirectory){
        try {
            outputDirectory = outputDirectory.replaceAll("\\\\", "/");

            String[] fileName = outputDirectory.split("/");
            String ext[] = fileName[fileName.length-1].split("\\.");
            String extension = ext[ext.length-1];

            ImageIO.write(asBufferedImage(), extension, new File(outputDirectory));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private double getTextWidth(Font font, String text){
        return font.getStringBounds(text, new FontRenderContext(font.getTransform(), false, false)).getBounds().getWidth();
    }

    private double getTextHeight(Font font, String text){
        return font.getStringBounds(text, new FontRenderContext(font.getTransform(), false, false)).getBounds().getHeight();
    }

    private void drawColoredText(Graphics2D graphics2D, String text, int x, int y){

        Color color = Minecraft.Color.WHITE.getColor();

        for(String textMatched: text.split(code)){

            String colorCode = textMatched.length() > 0 ?  String.valueOf(textMatched.charAt(0)) : "";

            if(textMatched.length() > 0) textMatched = textMatched.substring(1);


            for(Minecraft.Color mccolor: Minecraft.Color.values()){

                if( mccolor.getCode().equals(colorCode) ) {
                    color = mccolor.getColor();
                    break;
                }

            }

            List<Minecraft.Format> formats = new ArrayList<>();

            for (Minecraft.Format format: Minecraft.Format.values()){

                if(format.getCode().equals(colorCode)) {
                    formats.add(format);
                    break;
                }

            }

            Font font = this.font;

            if(formats.contains(Minecraft.Format.BOLD)) formats.add(Minecraft.Format.BOLD);

            if (formats.contains(Minecraft.Format.ITALIC)) formats.add(Minecraft.Format.ITALIC);

            if (formats.contains(Minecraft.Format.RESET)) color = Minecraft.Color.WHITE.getColor();

            if(formats.contains(Minecraft.Format.MAGIC)){

                if(!betterMagic) {
                    textMatched = Util.getRandomString(Minecraft.MAGIC_CHARS, textMatched.length());
                } else {
                    textMatched = Util.getRandomString("█", textMatched.length());
                }

            }

            if(formats.contains(Minecraft.Format.BOLD) && formats.contains(Minecraft.Format.ITALIC)){
                font = font.deriveFont(Font.BOLD + Font.ITALIC);
            } else if(formats.contains(Minecraft.Format.BOLD)){
                font = font.deriveFont(Font.BOLD);
            } else if (formats.contains(Minecraft.Format.ITALIC)){
                font = font.deriveFont(Font.ITALIC);
            }

            graphics2D.setFont(font);

            if(textShadow){
                Color shadowColor = new Color((int)(color.getRed()*SHADOW_COLOR), (int)(color.getGreen()*SHADOW_COLOR), (int)(color.getBlue()*SHADOW_COLOR));
                graphics2D.setColor(shadowColor);
                graphics2D.drawString(textMatched, x+TEXT_SHADOW_OFFSET, y+TEXT_SHADOW_OFFSET);
            }

            graphics2D.setColor(color);
            graphics2D.drawString(textMatched, x, y);

            if (formats.contains(Minecraft.Format.UNDERLINE)){

                int width = (int) getTextWidth(font, textMatched);
                int height = (int) getTextHeight(font, textMatched);

                int lineSize = formats.contains(Minecraft.Format.BOLD) ? UNDERLINE_STRIKETHROUGH_LINE_HEIGH_BOLD : UNDERLINE_STRIKETHROUGH_LINE_HEIGH;
                graphics2D.fillRect(x, (int) (y+(height*0.1)), width, lineSize);

            }

            if (formats.contains(Minecraft.Format.STRIKETHROUGH)){

                int width = (int) getTextWidth(font, textMatched);
                int height = (int) getTextHeight(font, textMatched);

                int lineSize = formats.contains(Minecraft.Format.BOLD) ? UNDERLINE_STRIKETHROUGH_LINE_HEIGH_BOLD : UNDERLINE_STRIKETHROUGH_LINE_HEIGH;
                graphics2D.fillRect(x, (int) (y-(height*0.25)), width, lineSize);

            }

            x += getTextWidth(graphics2D.getFont(), textMatched);

        }

    }

}
