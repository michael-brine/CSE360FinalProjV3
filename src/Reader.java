import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
    /**
     * Returns a String[][], in format String[n] = "ID","First Name","Last Name","Program","Acedemic Level","ASURITE"
     * Where n is number of rows and size of returned Array
     * @param f
     * @return String[][]
     * @throws FileNotFoundException
     */
    public String[][] readRoster(File f) throws FileNotFoundException {
        //reads file f
        Scanner fScan = new Scanner(f);
        String t;
        ArrayList<String[]> data = new ArrayList<String[]>();
        //adds lines of file f
        while (fScan.hasNextLine()) {
            t = fScan.nextLine();
            data.add(t.split(","));
        }
        String[][] out = new String[data.size()][6];
        //turns data into string[][]
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).length; j++) {
                out[i][j] = data.get(i)[j];
            }
        }
        return out;
    }

    /**
     * Returns a String[][], with given format: "ASURITE","Time"
     * Removes duplicate ASURITES and merges time
     *
     * @param f
     * @return String[][]
     * @throws FileNotFoundException
     */
    public String[][] readAttendance(File f) throws FileNotFoundException {
        //makes Scanner to read file
        Scanner fScan = new Scanner(f);
        //there is unknown number row hence arraylist
        ArrayList<String[]> data = new ArrayList<String[]>();
        //reads through entire file
        while (fScan.hasNextLine()) {
            //adds a String[] to data
            data.add(fScan.nextLine().split(","));
        }
        //Traverses data
        for (int i = 0; i < data.size(); i++) {
            //Traverses data[i]
            for (int j = i + 1; j < data.size(); j++) {
                //If there is a duplicate ASURITE add times
                if (data.get(i)[0].equals(data.get(j)[0])) {
                    //adds times
                    int t = Integer.parseInt(data.get(i)[1]) +
                            Integer.parseInt(data.get(j)[1]);
                    data.remove(j);
                    String[] tempArr = new String[2];
                    tempArr[0] = data.get(i)[0];
                    tempArr[1] = String.valueOf(t);
                    data.set(i, tempArr);
                    j--;
                }
            }
        }
        //turns ArrayList in String[][]
        String[][] out = new String[data.size()][2];
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).length; j++) {
                out[i][j] = data.get(i)[j];
            }
        }
        return out;
    }
}
