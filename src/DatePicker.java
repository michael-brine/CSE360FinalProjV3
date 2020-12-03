import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.util.Locale;

public class DatePicker extends JFrame {
    private JDateChooser jDateChooser;

    /**
     * Creates JFrame with date chooser in it,
     * adding actions action listner to the confirm date button
     *
     * @param action
     */
    DatePicker(Action action) {
        jDateChooser = new JDateChooser();
        jDateChooser.setSize(200, 200);
        jDateChooser.setLocale(Locale.US);
        JButton jButton = new JButton("Confirm Date");
        jButton.addActionListener(action);
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(jButton);
        //adds to new JFrame
        this.setJMenuBar(jMenuBar);
        this.add(jDateChooser);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 100);
        this.setLocation(500, 300);
        this.setVisible(true);
    }

    /**
     * Returns private jDatechooser
     *
     * @return jDateChooser
     */
    public JDateChooser getJDateChooser() {
        return jDateChooser;
    }
}
