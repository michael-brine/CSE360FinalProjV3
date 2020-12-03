import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Action implements ActionListener {
    private static DatePicker datePicker;
    private World world;
    private Repo repo;

    Action(World world, Repo repo) {
        this.world = world;
        this.repo = repo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "About") {
                JOptionPane.showMessageDialog(world, "CSE 360 Final Project done by:"
                        + "\nWilliam Bowden\nKyle Otstot\nShawn Karunanayake\nMichael Brine\nReema Alamanki");
        }
        //If Load a Roster is selected
        if (e.getActionCommand() == "Load a Roster") {
            //Opens File Path Selecter
            JFileChooser chooser = new JFileChooser();
            Reader reader = new Reader();
            chooser.showOpenDialog(null);
            try {
                //Uses reader class to process file selected by chooser
                String[][] data = reader.readRoster(chooser.getSelectedFile());
                //adds data to repository
                repo.setData(data);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }
        //Add Attendance is selected
        if (e.getActionCommand() == "Add Attendance") {
            //Makes a new Date Picker and makes it Visible
            datePicker = new DatePicker(this);
            datePicker.toFront();
            datePicker.requestFocus();
            datePicker.isAlwaysOnTop();
        }
        //Inside the Date Picker Confirming the date
        if (e.getActionCommand() == "Confirm Date") {
            //removes date picker
            datePicker.setVisible(false);
            //Gets date
            Date date = datePicker.getJDateChooser().getDate();
            //Parses Date into String
            String dateString = date.toLocaleString().substring(0, date.toLocaleString().indexOf(','));
            //Open File Path Selecter
            JFileChooser chooser = new JFileChooser();
            Reader reader = new Reader();
            chooser.showOpenDialog(null);
            ArrayList<String[]> dialog = null;
            try {
                //Uses reader to read file pases output repo, dialog then catches the out put of repo.addColumn
                //Which is an arraylist of entries that were in selected file but now in the roster
                dialog = repo.addColumn(reader.readAttendance(chooser.getSelectedFile()), dateString);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            //Uses helper dialog line to make correct tenses, and formatting
            String dialogLine = dialogLine(dialog);
            //Prints the output of repo.addColumn in a message dialog
            JOptionPane.showMessageDialog(world, "Data loaded for " + dialog.get(dialog.size() - 1)[0]
                    + " users in the roster.\n" + (dialog.size() - 1) + dialogLine);
        }
        //Save selected
        if (e.getActionCommand() == "Save") {
            //Makes the output file
            File save = new File("save.csv");
            try {
                FileWriter saveWriter = new FileWriter("save.csv");
                String comma = ",";
                //Traverses header writing the header and dates
                for (int i = 0, k = 0; i < repo.getHeader().length + repo.getDates().toArray().length; i++) {
                    //does not add a column to the end of the csv line
                    if (i == repo.getHeader().length + repo.getDates().toArray().length - 1) {
                        comma = "";
                    }
                    //Checks when to switch to dates
                    if (i < repo.getHeader().length) {
                        saveWriter.write(repo.getHeader()[i] + comma);
                    } else {
                        saveWriter.write(repo.getDates().get(k) + comma);
                        k++;
                    }
                }
                saveWriter.write("\n");
                comma = ",";
                //Traverses the number of rows i
                for (int i = 0, l = 0; i < repo.getData().length; i++, l = 0) {
                    //Traverses the number of columns
                    for (int j = 0, k = 0; j < repo.getData()[i].length + repo.getNColumns().size(); j++) {
                        //does not add a column to the end of the csv line
                        if (j == repo.getData()[i].length + repo.getNColumns().size() - 1) {
                            comma = "";
                        }
                        //when to switch to attendance columns
                        if (j < repo.getData()[i].length) {
                            saveWriter.write(repo.getData()[i][j] + comma);
                        } else {
                            saveWriter.write(repo.getNColumns().get(l)[k] + comma);
                            l++;
                            k++;
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
            PlotData plot = new PlotData(repo);
            //world.add()
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

