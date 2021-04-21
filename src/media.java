import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class media {

    String titre;
    int nbrCopies;

    //constructeur par défault
    public media(){};

    //constructeur paramétré
    public media(String titre, int nbrCopies){
        this.titre = titre;
        this.nbrCopies = nbrCopies;
    };

    //cette méthode demande à l'utlisateur de saisir le type de média qu'il veut ajouter
    public void AjoutMedia(Connection conn) throws SQLException {
        String c;
        Statement stmt = conn.createStatement();
        texte t = new texte();
        audio aud = new audio();
        video vid = new video();
        database db = new database();
        Scanner sc = new Scanner(System.in);
        System.out.println("Quel type de media voulez-vous ajouter? (texte/audio/video)");
        c = sc.nextLine();
        if(c.equals("texte")){
            t.AjoutTexte(db.dbStatus());
        }
        else if(c.equals("audio")){
            aud.AjoutAudio(db.dbStatus());
        }
        else if(c.equals("video")){
            vid.AjoutVideo(db.dbStatus());
        }
        else System.out.println("Choix indisponible!");
    }

    //cette méthode demande à l'utlisateur de saisir le type de média qu'il veut emprunter
    public void EmprunterMedia(Connection conn, etudiant e) throws SQLException{
        Statement stmt = conn.createStatement();
        String c;
        texte t = new texte();
        audio aud = new audio();
        video vid = new video();
        database db = new database();
        Scanner sc = new Scanner(System.in);
        System.out.println("Quel type de media voulez-vous emprunter? (texte/audio/video)");
        c = sc.nextLine();
        switch (c) {
            case "texte" -> {
                t.AfficheTexte(db.dbStatus());
                e.EmprunterTexte(conn);
            }
            case "audio" -> {
                aud.AfficheAudio(conn);
                e.EmprunterAudio(conn);
            }
            case "video" -> {
                vid.AfficheVideo(conn);
                e.EmprunterVideo(conn);
            }
            default -> System.out.println("Choix indisponible!");
        }

    }

    //cette méthode demande à l'utlisateur de saisir le type de média qu'il veut restituer
    public void RestituerMedia(Connection conn, etudiant e) throws SQLException{
        Statement stmt = conn.createStatement();
        String c;
        texte t = new texte();
        audio aud = new audio();
        video vid = new video();
        database db = new database();
        Scanner sc = new Scanner(System.in);
        System.out.println("Quel type de media voulez-vous restituer? (texte/audio/video)");
        c = sc.nextLine();
        if(c.equals("texte")){
            e.RestituerTexte(conn);
        }
        else if(c.equals("audio")){
            e.RestituerAudio(conn);
        }
        else if(c.equals("video")){
            e.RestituerVideo(conn);
        }
        else System.out.println("Choix indisponible!");

    }

    //cette méthode demande à l'utlisateur de saisir le type de média qu'il veut modifier
    public void ModifyMedia(Connection conn) throws SQLException {
        String c;
        Statement stmt = conn.createStatement();
        texte t = new texte();
        audio aud = new audio();
        video vid = new video();
        database db = new database();
        Scanner sc = new Scanner(System.in);
        System.out.println("Quel type de media voulez-vous modifier? (texte/audio/video)");
        c = sc.nextLine();
        if(c.equals("texte")){
            t.ModifyTexte(conn);
        }
        else if(c.equals("audio")){
            aud.ModifyAudio(conn);
        }
        else if(c.equals("video")){
            vid.ModifyVideo(conn);
        }
        else System.out.println("Choix indisponible!");
    }

    //cette méthode demande à l'utlisateur de saisir le type de média qu'il veut supprimer
    public void DeleteMedia(Connection conn) throws SQLException {
        String c;
        Statement stmt = conn.createStatement();
        texte t = new texte();
        audio aud = new audio();
        video vid = new video();
        database db = new database();
        Scanner sc = new Scanner(System.in);
        System.out.println("Quel type de media voulez-vous supprimer? (texte/audio/video)");
        c = sc.nextLine();
        if(c.equals("texte")){
            t.DeleteTexte(conn);
        }
        else if(c.equals("audio")){
            aud.DeleteAudio(conn);
        }
        else if(c.equals("video")){
            vid.DeleteVideo(conn);
        }
        else System.out.println("Choix indisponible!");
    }
}


