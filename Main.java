import javax.swing.*;

public class Main  {
    Main() {
        Repo repo = new Repo();
        World world = new World(repo);
        repo.addObserver(world);
    }

    public static void main(String[] args) {
        Main main = new Main();
    }
}
