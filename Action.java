import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Action implements ActionListener {
    private World world;
    private Repo repo;

    Action(World world, Repo repo) {
        this.world = world;
        this.repo = repo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "About") {

        }
        if (e.getActionCommand() == "Load a Roster") {
            JFileChooser chooser = new JFileChooser();
            Reader reader = new Reader();
            chooser.showOpenDialog(null);
            try {
                String[][] data = reader.readRoster(chooser.getSelectedFile());
                repo.setData(data);
                System.out.println(data.length);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }
        if (e.getActionCommand() == "Add Attendance") {
            JFileChooser chooser = new JFileChooser();
            Reader reader = new Reader();
            chooser.showOpenDialog(null);
            try {

                DatePicker datePicker = new DatePicker();
                JPopupMenu popupMenu = new JPopupMenu();
                //popupMenu.show(datePicker.jFrame, 100, 100);
                //world.add(popupMenu);
                //world.add(datePicker.panel);
                //String date = datePicker.getDatePicker().showInputDialog(world, "Enter date: ");
                ArrayList<String[]> dialog = repo.addColumn(reader.readAttendance(chooser.getSelectedFile()), "lk");
                String dialogLine = dialogLine(dialog);
                JOptionPane.showMessageDialog(world, "Data loaded for " + dialog.get(dialog.size() - 1)[0]
                        + " users in the roster.\n" + (dialog.size() - 1) + dialogLine);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }
        if (e.getActionCommand() == "Save") {
            File save = new File("save.csv");
            try {
                FileWriter saveWriter = new FileWriter("save.csv");
                String comma = ",";
                System.out.println(" extra col dates " + repo.getDates().size());
                System.out.println(" extra col NCol " + repo.getNColumns().size());
                for(int i = 0, k = 0; i < repo.getHeader().length + repo.getDates().toArray().length; i++) {
                    if(i == repo.getHeader().length + repo.getDates().toArray().length -1) {
                        comma = "";
                    }
                    if(i < repo.getHeader().length) {
                        saveWriter.write(repo.getHeader()[i] + comma );
                    } else {
                        saveWriter.write(repo.getDates().get(k) + comma);
                        k++;
                    }
                }
                saveWriter.write("\n");
                comma = ",";
                for(int i = 0, l = 0; i < repo.getData().length; i++, l = 0) {
                    for(int j = 0, k = 0; j < repo.getData()[i].length + repo.getNColumns().size(); j++) {
                        if(j == repo.getData()[i].length + repo.getNColumns().size() - 1) {
                            comma = "";
                        }
                        if(j < repo.getData()[i].length) {
                            saveWriter.write(repo.getData()[i][j] + comma );
                        } else {
                            saveWriter.write(repo.getNColumns().get(l)[k] + comma);
                            l++; k++;
                        }
                    }
                    comma = ",";
                    saveWriter.write("\n");
                }
                saveWriter.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        if (e.getActionCommand() == "Plot Data") {

        }
    }

    String dialogLine(ArrayList<String[]> dialog) {
        String dialogLine, plural;
        if (dialog.size() - 1 == 1) {
            dialogLine = " additional attendee was found: \n";
        } else {
            dialogLine = " additional attendees were found:\n";
        }
        for (int i = 0; i < dialog.size() - 1; i++) {
            if (Integer.parseInt(dialog.get(i)[1]) > 1) {
                plural = " minutes\n";
            } else {
                plural = " minute\n";
            }
            dialogLine += dialog.get(i)[0] + ", connected for " + dialog.get(i)[1] + plural;
        }
        return dialogLine;
    }
}

