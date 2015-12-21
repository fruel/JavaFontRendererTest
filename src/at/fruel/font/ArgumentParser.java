package at.fruel.font;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Created by lukas on 21.12.2015.
 */
public class ArgumentParser {
    private String mText;
    private Font mFont;
    private float mFontSize = 12;
    private int mFontStyle = Font.PLAIN;

    public boolean parse(String[] args){
        if(args != null) {
            for (int i = 0; i < args.length; ++i) {
                String arg = args[i];

                if("-h".equals(arg)){
                    printHelp();
                    return false;
                }
                else if(i < args.length - 1){
                    if ("-i".equals(arg)) {
                        loadTextFile(args[i + 1]);
                    }
                    else if ("-t".equals(arg)) {
                        mText = args[i + 1];
                    }
                    else if ("-s".equals(arg)) {
                        loadSystemFont(args[i + 1]);
                    }
                    else if ("-f".equals(arg)) {
                        loadTTFFont(args[i + 1]);
                    }
                    else if ("-p".equals(arg)) {
                        parseFontSize(args[i + 1]);
                    }
                    else if ("-o".equals(arg)) {
                        parseStyleArgs(args[i + 1]);
                    }
                }
            }

            if(mText == null){
                showFileOpenDialog();
            }
            if(mFont == null){
                showFontDialog();
            }

            if(mText == null || mFont == null) {
                System.err.println("Missing arguments. Use -h to show help.");
                return false;
            }

            mFont = mFont.deriveFont(mFontStyle, mFontSize);
            return true;
        }

        System.err.println("Command line arguments array is NULL.");
        return false;
    }

    public String getText() {
        return mText;
    }

    public Font getFont() {
        return mFont;
    }

    private void printHelp(){
        System.out.println("Java Font Rendering test application.");
        System.out.println("Input Data:");
        System.out.println("\t-i [file]\tLoad text from input file.");
        System.out.println("\t-t [text]\tProvide text directly.");
        System.out.println("\n\tIf no options are specified you will be prompted to select a file.");

        System.out.println("\nFont:");
        System.out.println("\t-s [font]\tUse an installed system font with the given name.");
        System.out.println("\t-f [file]\tLoad the specified TTF file and use it to render the text.");
        System.out.println("\t-p [pt]\t\tSet font size");
        System.out.println("\t-o [style]\tFont style option. A comma separated list of: bold, italic");
        System.out.println("\n\tIf no font options are specified a selection dialog will be shown.");

        System.out.println("\nOther Options:");
        System.out.println("\t-h\t\tShow this help page.");
    }

    private void loadSystemFont(String font){
        //TODO check if font exists
        mFont = new Font(font, mFontStyle, (int)mFontSize);
    }

    private void loadTTFFont(String file){
        File inputFile = new File(file);
        if(inputFile.exists() && inputFile.canRead()){

            InputStream fileStream = null;
            try {
                fileStream = new FileInputStream(inputFile);
                mFont = Font.createFont(Font.TRUETYPE_FONT, fileStream);
                return;
            }
            catch(FontFormatException | IOException ex){
                ex.printStackTrace();
            }
            finally {
                if(fileStream != null){
                    try {
                        fileStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.err.println("Cannot load font file.");
    }

    private void loadTextFile(String file){
        File inputFile = new File(file);
        if(inputFile.exists() && inputFile.canRead()){

            BufferedReader fileReader = null;
            try {
                StringBuilder stringBuilder = new StringBuilder();
                fileReader = new BufferedReader(new FileReader(inputFile));

                String line;
                while ((line = fileReader.readLine()) != null){
                    stringBuilder.append(line);
                    stringBuilder.append("\n");
                }

                mText = stringBuilder.toString();
                return;
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
            finally {
                if(fileReader != null){
                    try {
                        fileReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.err.println("Cannot load text file.");
    }

    private void parseFontSize(String arg) {
        try {
            mFontSize = Float.parseFloat(arg);
        }
        catch(NumberFormatException ex){
            System.err.println("Invalid font size.");
        }
    }

    private void showFontDialog(){
        JFontChooser fontChooser = new JFontChooser();
        int result = fontChooser.showDialog(null);

        if(result == JFontChooser.OK_OPTION){
            mFont = fontChooser.getSelectedFont();
        }
    }

    private void showFileOpenDialog(){
        JFileChooser fileChoser = new JFileChooser();
        int result = fileChoser.showOpenDialog(null);

        if(result == JFileChooser.APPROVE_OPTION){
            loadTextFile(fileChoser.getSelectedFile().getAbsolutePath());
        }
    }

    private void parseStyleArgs(String styles){
        if(styles != null){
            for(String style : styles.split(",")){
                if(style != null){
                    style = style.trim().toLowerCase();
                    if("bold".equals(style)){
                        mFontStyle |= Font.BOLD;
                    }
                    else if("italic".equals(style)){
                        mFontStyle |= Font.BOLD;
                    }
                }
            }
        }
    }
}
