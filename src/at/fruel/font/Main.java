package at.fruel.font;

public class Main {

    public static void main(String[] args) {
        RenderWindow.loadLookAndFeel();

        ArgumentParser argumentParser = new ArgumentParser();

        if(argumentParser.parse(args)){
            RenderWindow renderWindow = new RenderWindow(argumentParser.getText(), argumentParser.getFont());
            renderWindow.show();
        }

    }
}
