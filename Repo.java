import java.util.ArrayList;
import java.util.Observable;

public class Repo extends Observable {
    private String[][] data;
    private ArrayList<String[]> nColumns;
    private ArrayList<String> dates;
    private String[] header;
    private int n;

    Repo() {
        dates = new ArrayList<>();
        header = new String[]{"ID", "First Name", "Last Name",
                "Program", "Academic Level", "ASURITE"};
        nColumns = new ArrayList<>();
        n = 0;
    }

    public int getN() {
        return n;
    }

    public String[] getHeader() {
        return header;
    }

    public String[][] getData() {
        return data;
    }

    public ArrayList<String[]> getNColumns() {
        return nColumns;
    }

    public ArrayList<String> getDates() {
        return dates;
    }

    public ArrayList<String[]> addColumn(String[][] columnData, String date) {
        dates.add(date);
        ArrayList<String[]> notInRoster = new ArrayList<>();
        String[] add = new String[data.length];
        int k = 0;
        boolean inTable = false;
        for (int i = 0; i < columnData.length; i++, inTable = false) {
            for (int j = 0; j < data.length; j++) {
                if (data[j][5].equals(columnData[i][0])) {
                    add[j] = columnData[i][1];
                    inTable = true;
                    k++;
                    break;
                }
            }
            if (!inTable) {
                notInRoster.add(columnData[i]);
            }
        }
        nColumns.add(add);
        String[] temp = {(String.valueOf(k))};
        notInRoster.add(temp);
        n++;
        setChanged();
        notifyObservers();
        return notInRoster;
    }

    public void setData(String[][] data) {
        if (n == 0) {
            this.data = data;
            n = 1;
            setChanged();
            notifyObservers();
        }
    }
}
