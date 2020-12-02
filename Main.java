import javax.swing.*;

public class Main extends JFrame {
    Main() {
        Repo repo = new Repo();
        World world = new World(repo);
        repo.addObserver(world);



        //String[][] data = new String[1][1];
        //data[0][0] = "jeff";
        //repo.setData(data);


    }

    public static void main(String[] args) {
        Main main = new Main();
    }
}
