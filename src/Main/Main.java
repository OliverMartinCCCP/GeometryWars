package Main;


public class Main {

    public static void main(String[] args) {
        
        //Toolkit tk = Toolkit.getDefaultToolkit();
        //int width = (int)tk.getScreenSize().getWidth();
        //int height = (int)tk.getScreenSize().getHeight();
        Game game = new Game( 1920, 1080, "geometry wars" );
        game.start();
        
    }
}
