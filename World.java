import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class World extends JFrame implements Observer {
    private Repo repo;
    private Action action;
    private Table table;

    World(Repo repo) {
        this.repo = repo;
        this.action = new Action(this, repo);
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        //sets menu items labels
        JButton aboutButton = new JButton("About");
        JMenuItem m1 = new JMenuItem("Load a Roster");
        JMenuItem m2 = new JMenuItem("Add Attendance");
        JMenuItem m3 = new JMenuItem("Save");
        JMenuItem m4 = new JMenuItem("Plot Data");
        //Adds menu items
        menu.add(m1);
        menu.add(m2);
        menu.add(m3);
        menu.add(m4);
        //adds listeners
        aboutButton.addActionListener(action);
        m1.addActionListener(action);
        m2.addActionListener(action);
        m3.addActionListener(action);
        m4.addActionListener(action);
        //adds menu to menu bar
        aboutButton.setBorder(null);
        menuBar.add(menu);
        menuBar.add(aboutButton);
        //adds items to JFrame and
        this.setJMenuBar(menuBar);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 400);
        this.setLocation(400, 200);
        this.setVisible(true);
    }

    /**
     * Receives notification from repo, and updates View
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        //checks if roster has been set for the first time
        if (repo.getN() == 1) {
            String[][] data = repo.getData();
            String[] header = repo.getHeader();
            table = new Table(data, header);
            table.getJTable().setBounds(30, 40, 500, 400);
            JPanel jPanel = new JPanel();
            jPanel.add(table.getJTable());
            JScrollPane jScrollPane = new JScrollPane(jPanel);
            //jScrollPane.setHorizontalScrollBar(new JScrollBar());
            //jScrollPane.setAutoscrolls(true);
            this.add(new JScrollPane((jPanel)));
        }
        //if roster has been updated for the first time must add attendance
        if (repo.getN() > 1) {
            table.addColumn(repo.getDates().get(repo.getN() - 2), repo.getNColumns().get(repo.getN() - 2));
        }
        //updates view
        revalidate();
        repaint();
    }
}
