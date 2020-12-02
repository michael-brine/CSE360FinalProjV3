import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.util.Date;
import java.util.Locale;

public class DatePicker {
    JCalendar jCalendar;
    JDateChooser jDateChooser;
    JPanel jpanel;
    JFrame jFrame;

    DatePicker() {
        jDateChooser = new JDateChooser();
        jDateChooser.setLocale(Locale.US);
        jpanel = new JPanel();
        jFrame = new JFrame();
        jpanel.add(new JLabel("Date Chooser"));
        jpanel.add(jDateChooser);
        jFrame.add(jpanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(200,100);
    }

}
