import javax.swing.*;

public class Main  {
    /**
	 * Constructor for class Main
	 * 
	 * @Description: this constructor intialize the teh Repo and world the adds world to repo
	 *
	 */
    Main() {
        Repo repo = new Repo();
        World world = new World(repo);
        repo.addObserver(world);
    }
/**
	 * @description: just create a main object to call everything for the program
	 * @param: String[] args 
	 * @return: [static void] 	  
	 */
    public static void main(String[] args) {
        Main main = new Main();
    }
}
