import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {

    public String[][] readRoster(File f) throws FileNotFoundException {
        Scanner fScan = new Scanner(f);
        String t;
        ArrayList<String[]> data = new ArrayList<String[]>();
        while (fScan.hasNextLine()) {
            t = fScan.nextLine();
            data.add(t.split(","));
        }
        String[][] out = new String[data.size()][6];
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).length; j++) {
                out[i][j] = data.get(i)[j];
            }
        }
        return out;
    }

    public String[][] readAttendance(File f) throws FileNotFoundException {
        Scanner fScan = new Scanner(f);
        ArrayList<String[]> data = new ArrayList<String[]>();
        while (fScan.hasNextLine()) {
            data.add(fScan.next().split(","));
        }
        for (int i = 0; i < data.size(); i++) {
            for (int j = i + 1; j < data.size(); j++) {
                if (data.get(i)[0].equals(data.get(j)[0])) {
                    int t = Integer.parseInt(data.get(i)[1]) +
                            Integer.parseInt(data.get(j)[1]);
                    data.remove(j);
                    String[] tempArr = new String[2];
                    tempArr[0] = data.get(i)[0];
                    tempArr[1] = String.valueOf(t);
                    data.set(i, tempArr);
                }
            }
        }
        String[][] out = new String[data.size()][2];
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).length; j++) {
                out[i][j] = data.get(i)[j];
            }
        }
        return out;
    }
}
