package at.fruel.font;

import sun.font.Font2D;
import sun.font.Font2DHandle;
import sun.font.PhysicalFont;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.lang.reflect.Field;

/**
 * Created by lukas on 21.12.2015.
 */
public class RenderWindow {

    private String mText;
    private Font mFont;

    public RenderWindow(String text, Font font){
        mText = text;
        mFont = font;
    }

    public void show(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGui();
            }
        });
    }

    private void createAndShowGui(){
        JFrame frame = new JFrame("Jave Font Rendering Test");
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JTextArea textArea = new JTextArea();
        textArea.setFont(mFont);
        textArea.setText(mText);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JLabel systemInfo = new JLabel();
        systemInfo.setBorder(new EmptyBorder(5, 5, 5, 5));

        String fontName = mFont.getFamily();
        String fontSize = mFont.getSize2D()+"";
        String fontFile = getFontFilename();
        String fontStyle = "regular";
        if(mFont.isBold() && mFont.isItalic()){
            fontStyle = "bold, italic";
        }
        else if(mFont.isBold()){
            fontStyle = "bold";
        }
        else if(mFont.isBold()){
            fontStyle = "italic";
        }


        systemInfo.setText("<html><b>Java</b>: " +
                Runtime.class.getPackage().getImplementationVersion() +
                "<br/>"+
                Runtime.class.getPackage().getImplementationVendor() + " " + Runtime.class.getPackage().getImplementationTitle() +
                "<br/><b>Location</b>: " +
                System.getProperty("java.home") +
                "<br/><b>OS</b>: " + System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ")<br><b>Font</b>: " +
                fontName + ", " + fontSize + "pt, " + fontStyle + " (" + fontFile + ")</html>");

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(systemInfo, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public static void loadLookAndFeel(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.err.println("Could not load system look and feel.");
            e.printStackTrace();
        }
    }

    private String getFontFilename(){
        // Using reflection we determine which font file was really loaded.
        // This allows to check what happens when we load system fonts instead of providing out own files.

        String fontFile = "unknown file";
        try {
            Field font2Dhandle = Font.class.getDeclaredField("font2DHandle");
            Field font2D = Font2DHandle.class.getDeclaredField("font2D");
            Field filename = PhysicalFont.class.getDeclaredField("platName");

            font2Dhandle.setAccessible(true);
            font2D.setAccessible(true);
            filename.setAccessible(true);

            Object handle = font2Dhandle.get(mFont);
            Object font = font2D.get(handle);
            fontFile = (String) filename.get(font);
        }
        catch(NoSuchFieldException | IllegalAccessException | IllegalArgumentException ex){
            ex.printStackTrace();
        }

        return fontFile;
    }
}
