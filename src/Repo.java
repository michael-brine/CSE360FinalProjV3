import java.util.ArrayList;
import java.util.Observable;

public class Repo extends Observable {
    private String[][] data;
    private ArrayList<String[]> nColumns;
    private ArrayList<String> dates;
    private String[] header;
    private int n;

    /**
     * Constructs known data structs, like header, and initializes arraylists
     */
    Repo() {
        dates = new ArrayList<>();
        header = new String[]{"ID", "First Name", "Last Name",
                "Program", "Academic Level", "ASURITE"};
        nColumns = new ArrayList<>();
        n = 0;
    }

    /**
     * Returns n, numbers of columns added, so can accessed
     *
     * @return int
     */
    public int getN() {
        return n;
    }

    /**
     * Returns the header, String[]
     *
     * @return String[]
     */
    public String[] getHeader() {
        return header;
    }

    /**
     * Returns private data
     *
     * @return data
     */
    public String[][] getData() {
        return data;
    }

    /**
     * returns private nColumns
     *
     * @return nColumns
     */
    public ArrayList<String[]> getNColumns() {
        return nColumns;
    }

    /**
     * Returns private dates
     *
     * @return dates
     */
    public ArrayList<String> getDates() {
        return dates;
    }

    /**
     * Returns Arraylist of all ASURITES and time, that were inputted but not on roster,
     * and the count of students added to the table, Takes String[][] column data as "ASURITE","Time"
     *
     * @param columnData
     * @param date
     * @return
     */
    public ArrayList<String[]> addColumn(String[][] columnData, String date) {
        dates.add(date);
        ArrayList<String[]> notInRoster = new ArrayList<>();
        String[] add = new String[data.length];
        for (int i = 0; i < add.length; i++)
        {
          add[i] = "0";
        }
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

    /**
     * sets data only does this once
     *
     * @param data
     */
    public void setData(String[][] data) {
        if (n == 0) {
            this.data = data;
            n = 1;
            setChanged();
            notifyObservers();
        }
    }
}
