package Vues;

import Entities.Acteur;
import Entities.Cinema;
import Entities.Film;
import Tools.ModelJTable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FrmMenu extends JFrame
{
    private JPanel pnlRoot;
    private JLabel lblTitre;
    private JLabel lblCinemas;
    private JTable tblCinemas;
    private JLabel lblFilms;
    private JTable tblFilms;
    private JLabel lblActeurs;
    private JTable tblActeurs;
    private JLabel lblPhotoActeur;
    private JSlider sldNoteFilm;
    private JButton btnNoter;
    private JTextField txtNomMeilleurActeur;
    private JTextField txtNoteMeilleurActeur;
    private JLabel lblNomMeilleurActeur;
    private JLabel lblNoteMeilleurActeur;
    private JLabel lblNoteFilm;

    ArrayList<Cinema> mesCinemas;
    ModelJTable mdl;
    Cinema cinemaSelectionne;
    Acteur acteurSelectionne;
    Film filmSelectionne;


    public FrmMenu()
    {
        this.setTitle("Devoir - Cinéma");
        this.setContentPane(pnlRoot);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);

        mesCinemas = new ArrayList<>();
        LoadDatas();

        // A compléter ici

        mdl = new ModelJTable();
        mdl.LoadCinema(mesCinemas);
        tblCinemas.setModel(mdl);

        tblCinemas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int numCinema = Integer.parseInt(tblCinemas.getValueAt(tblCinemas.getSelectedRow(),0).toString());

                for(Cinema cnm : mesCinemas)
                {
                    if (cnm.getIdCinema() == numCinema)
                    {
                        mdl = new ModelJTable();
                        mdl.LoadFilm(cnm.getLesFilms());
                        tblFilms.setModel(mdl);
                        cinemaSelectionne = cnm;

                        break;
                    }
                }
            }
        });

  

        tblFilms.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int numFilm = Integer.parseInt(tblFilms.getValueAt(tblCinemas.getSelectedRow(), 0).toString());
                for(Film flm : cinemaSelectionne.getLesFilms())
                {
                    if(flm.getIdFilm() == numFilm)
                    {
                        mdl = new ModelJTable();
                        mdl.LoadActeur(flm.getLesActeurs());
                        tblActeurs.setModel(mdl);
                        filmSelectionne = flm;
                        break;
                    }
                }
            }
        });

        tblActeurs.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                // NE PAS MODIFIER CE CODE
                // AUCUNE MODIFICATION A FAIRE ICI
                Image img1;
                try {
                    img1 = ImageIO.read(this.getClass().getResource(mesCinemas.get(tblCinemas.getSelectedRow()).getLesFilms().get(tblFilms.getSelectedRow()).getLesActeurs().get(tblActeurs.getSelectedRow()).getPhotoActeur()));
                    Image img2 = img1.getScaledInstance(200,150, Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(img2);
                    lblPhotoActeur.setIcon(icon);
                } catch (IOException ex) {
                    Logger.getLogger(FrmMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btnNoter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (tblFilms.getSelectedRow() == 0)
                {
                    JOptionPane.showMessageDialog(null, "Merci de sélectionner un acteur");
                }
                else
                {
                    int numActeur = Integer.parseInt(tblFilms.getValueAt(tblFilms.getSelectedRow(),0).toString());
                    for(Acteur act : filmSelectionne.getLesActeurs())
                    {
                        if(act.getIdActeur() == numActeur)
                        {
                            act.CalculerNoteActeur(sldNoteFilm.getValue());
                            break;
                        }
                    }
                }
            }
        });
    }
    public void LoadDatas()
    {
        Cinema cine1 = new Cinema(1,"Gaumont");
        Cinema cine2 = new Cinema(2,"UGC");

        Film film1 = new Film(1,"Film n°1",0,0);
        Film film2 = new Film(2,"Film n°2",0,0);
        Film film3 = new Film(3,"Film n°3",0,0);

        Acteur act1 = new Acteur(1,"Pierre Richard",0,"/Images/Richard.jpg");
        Acteur act2 = new Acteur(2,"Gilles Lellouche",0,"/Images/Lellouche.jpg");
        Acteur act3 = new Acteur(3,"Sally Hawkins",0,"/Images/Hawkins.jpg");
        Acteur act4 = new Acteur(4,"Karine Viard",0,"/Images/Viard.jpg");
        Acteur act5 = new Acteur(5,"Sylvia Hoeks",0,"/Images/Hoeks.jpg");
        Acteur act6 = new Acteur(6,"Jared Leto",0,"/Images/Leto.jpg");
        Acteur act7 = new Acteur(7,"Anne Dorval",0,"/Images/Dorval.jpg");
        Acteur act8 = new Acteur(8,"Harrison Ford",0,"/Images/Ford.jpg");

        film1.AjouterUnActeur(act1);film1.AjouterUnActeur(act2);
        film2.AjouterUnActeur(act2);film2.AjouterUnActeur(act3);film2.AjouterUnActeur(act4);
        film3.AjouterUnActeur(act5);film3.AjouterUnActeur(act6);film3.AjouterUnActeur(act7);film3.AjouterUnActeur(act8);
        cine1.AjouterUnFilm(film1);
        cine2.AjouterUnFilm(film2);cine2.AjouterUnFilm(film3);

        mesCinemas.add(cine1);mesCinemas.add(cine2);


    }
}
