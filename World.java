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
        JButton aboutButton = new JButton("About");
        JMenuItem m1 = new JMenuItem("Load a Roster");
        JMenuItem m2 = new JMenuItem("Add Attendance");
        JMenuItem m3 = new JMenuItem("Save");
        JMenuItem m4 = new JMenuItem("Plot Data");
        menu.add(m1);
        menu.add(m2);
        menu.add(m3);
        menu.add(m4);
        aboutButton.addActionListener(action);
        m1.addActionListener(action);
        m2.addActionListener(action);
        m3.addActionListener(action);
        m4.addActionListener(action);
        menuBar.add(menu);
        menuBar.add(aboutButton);
        DatePicker p = new DatePicker();
        p.jDateChooser.getComponentPopupMenu();
        //this.add(world);
        //this.setLayout(new BorderLayout());
        //this.add(mb,BorderLayout.NORTH);
        this.setJMenuBar(menuBar);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setVisible(true);


    }

    @Override
    public void update(Observable o, Object arg) {
        if (repo.getN() == 1) {
            String[][] data = repo.getData();
            String[] header = repo.getHeader();
            table = new Table(data, header);
            table.getJTable().setBounds(30, 40, 200, 300);
            JScrollPane jScrollPane = new JScrollPane(table.getJTable());
            JScrollBar jScrollBar = new JScrollBar();
            //jScrollPane.setAutoscrolls(true);
            this.add(jScrollPane);
        }
        if (repo.getN() > 1) {
            table.addColumn(repo.getDates().get(repo.getN() - 2), repo.getNColumns().get(repo.getN() - 2));
        }

        revalidate();
        repaint();
    }
}
