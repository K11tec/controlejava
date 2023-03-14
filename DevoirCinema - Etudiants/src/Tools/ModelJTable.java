package Tools;

import Entities.Acteur;
import Entities.Cinema;
import Entities.Film;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ModelJTable extends AbstractTableModel
{
    private String[] columns;
    private Object[][] rows;

    @Override
    public int getRowCount() {
        return rows.length;
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rows[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }


    public void LoadActeur(ArrayList<Acteur> desActeur)
    {
        columns = new String[]{"Numéro", "Nom", "Note"};
        rows = new Object[desActeur.size()][3];
        int i = 0;

        for(Acteur act : desActeur)
        {
            rows[i][0] = act.getIdActeur();
            rows[i][1] = act.getNomActeur();
            rows[i][2] = act.getNoteActeur();
            i++;
        }
        fireTableChanged(null);
    }

    public void LoadCinema (ArrayList<Cinema> desCinema)
    {
        columns = new String[]{"Numéro", "Nom"};
        rows = new Object[desCinema.size()][2];
        int i = 0;

        for (Cinema cnm : desCinema)
        {
            rows[i][0] = cnm.getIdCinema();
            rows[i][1] = cnm.getNomCinema();
            i++;
        }
        fireTableChanged(null);
    }
    public void  LoadFilm (ArrayList<Film> desFilm)
    {
        columns = new String[]{"Numéro", "Nom", "Nbvotes", "TotalVotes"};
        rows = new Object[desFilm.size()][3];
        int i = 0;

        for (Film flm : desFilm)
        {
            rows[i][0] = flm.getIdFilm();
            rows[i][1] = flm.getNomFilm();
            rows[i][2] = flm.getNbVotes();
            rows[i][3] = flm.getTotalVotes();
            i++;
        }
        fireTableChanged(null);
    }
}
