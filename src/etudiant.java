import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class etudiant {

     int apoge;
     String nomEtu;
     String prenomEtu;
     String passwordEtu;
     String status;

    //constructeur par défault
    etudiant() {};

    //constructeur paramétré
    etudiant(int apoge, String nomEtu, String prenomEtu, String passwordEtu, String status) {
        this.apoge = apoge;
        this.nomEtu = nomEtu;
        this.prenomEtu = prenomEtu;
        this.passwordEtu = passwordEtu;
        this.status = status;
    }


    //les méthodes pour définir les valeurs des attributs de notre classe
    public void setApoge(int apoge) {
        this.apoge = apoge;
    }

    public void setNom(String nomEtu) {
        this.nomEtu = nomEtu;
    }

    public void setPrenom(String prenomEtu) {
        this.prenomEtu = prenomEtu;
    }

    public void setPassword(String passwordEtu) {
        this.passwordEtu = passwordEtu;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    //les méthodes pour retourner les valeurs des attributs de notre classe
    public int getApoge() {
        return this.apoge;
    }

    public String getNomEtu() {
        return this.nomEtu;
    }

    public String getprenomEtu() {
        return this.prenomEtu;
    }

    public String getPasswordEtu() {
        return this.passwordEtu;
    }

    public String getStatus() {
        return this.status;
    }

    /* cette méthode demande à l'étudiant de saisir son code apogée, son nom, son prénom et son nouveau mot de passe pour pouvoir l'inscrire dans notre base
    de données */
    public void inscriptionEtu(Connection conn) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("---- ESPACE INSCRIPTION ETUDIANT ----");
        System.out.println("");
        System.out.println("Entrez votre code apoge : ");
        setApoge(sc.nextInt());
        sc.nextLine();
        System.out.println("Entrez votre prenom : ");
        setPrenom(sc.nextLine());
        System.out.println("Entrez votre nom : ");
        setNom(sc.nextLine());
        System.out.println("Saisir votre nouveau mot de passe : ");
        setPassword(sc.nextLine());

        Statement stmt = conn.createStatement();
        stmt.executeUpdate("INSERT INTO etudiant VALUES (" + this.apoge + ",'" + this.nomEtu + "','" + this.prenomEtu + "','" + this.passwordEtu + "')");
        System.out.println("");
        System.out.println(">>> INSCRIPTION AVEC SUCCES <<<");

    }

    /* cette méthode demande à l'étudiant de saisir son code apogée et son mot de passe pour lui donner accès à l'espace étudiant de notre programme, elle
    affiche aussi un message d'erreur si l'étudiant a saisi le mauvais login ou le mauvais mot de passe, on lui demande alors de réessayer */

    public void loginEtu(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        Scanner sc = new Scanner(System.in);
        System.out.println("---- LOGIN ETUDIANT ----");
        System.out.println("");
        System.out.println("Entrez votre code apoge : ");
        setApoge(sc.nextInt());
        sc.nextLine();
        System.out.println("Entrez votre code mot de passe : ");
        setPassword(sc.nextLine());

        ResultSet rs = stmt.executeQuery("SELECT * FROM etudiant WHERE code_apoge ='" + this.apoge + "' AND password = '" + this.passwordEtu + "'");

        if (rs.next()) {
            System.out.println(" ---------- ESPACE ETUDIANT ---------- ");
            System.out.println("");
            MenuEtudiant(conn);
           }

        else System.out.println("Login Failed! | Veuillez ressayer!");
    }


    /* cette méthode demande à l'étudiant de saisir le titre du texte qu'il veut emprunter, si le nombre de copies est supérieur à zéro, l'étudiant peut
    emprunter le texte, son code apogée est ajoutée à la table d'emprunt ainsi que le titre du texte emprunté, et le nombre de copies de ce texte diminue dans
    la table texte.
    On affiche aussi un message d'erreur si l'étudiant ne peut pas emprunter, ou s'il a déjà emprunté ce texte */

    public void EmprunterTexte(Connection conn) throws SQLException {
        String titre;
        texte t = new texte();
        Statement stmt = conn.createStatement();
        Statement stmt1 = conn.createStatement();
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.println("Entrez le titre du fichier texte que vous voulez emprunter : ");
        titre = sc.nextLine();
        ResultSet rs = stmt.executeQuery("SELECT * FROM texte WHERE nom='"+titre+"' AND nbr_copies>0");
       ResultSet re = stmt1.executeQuery("SELECT * FROM emprunt WHERE titre_media='"+titre+"' AND code_apoge = '"+this.apoge+"'");
        if(!re.next()) {
            if (rs.next()) {
                stmt.executeUpdate("INSERT INTO emprunt VALUES ('" + this.apoge + "','" + titre + "')");
                stmt.executeUpdate("UPDATE texte SET nbr_copies=nbr_copies - 1 WHERE nom='" + titre + "'");
            } else System.out.println(">>>>> Impossible d'emprunter <<<<<");
            System.out.println("");
        }
       else System.out.println(">>>>> Vous avez déjà emprunté ce fichier texte !! <<<<<");
    }

    /* cette méthode demande à l'étudiant de saisir le titre du fichier audio qu'il veut emprunter, si le nombre de copies est supérieur à zéro, l'étudiant peut
    emprunter l'audio, son code apogée est ajoutée à la table d'emprunt ainsi que le titre de l'audio emprunté, et le nombre de copies de cet audio diminue dans
    la table audio.
    On affiche aussi un message d'erreur si l'étudiant ne peut pas emprunter, ou s'il a déjà emprunté cet audio  */

    public void EmprunterAudio(Connection conn) throws SQLException {
        String titre;
        texte t = new texte();
        Statement stmt = conn.createStatement();
        Statement stmt1 = conn.createStatement();
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.println("Entrez le titre du fichier audio que vous voulez emprunter : ");
        titre = sc.nextLine();
        ResultSet rs = stmt.executeQuery("SELECT * FROM audio WHERE nom='"+titre+"' AND nbr_copies>0");
        ResultSet re = stmt1.executeQuery("SELECT * FROM emprunt WHERE titre_media='"+titre+"' AND code_apoge = '"+this.apoge+"'");
        if(!re.next()) {
            if (rs.next()) {
                stmt.executeUpdate("INSERT INTO emprunt VALUES ('" + getApoge() + "','" + titre + "')");
                stmt.executeUpdate("UPDATE audio SET nbr_copies=nbr_copies - 1 WHERE nom='" + titre + "'");

            } else System.out.println(">>>>> Impossible d'emprunter <<<<<");
            System.out.println("");
        }
        else System.out.println(">>>>> Vous avez déjà emprunté ce fichier Audio !! <<<<<");
    }

    /* cette méthode demande à l'étudiant de saisir le titre du fichier vidéo qu'il veut emprunter, si le nombre de copies est supérieur à zéro, l'étudiant peut
    emprunter la vidéo, son code apogée est ajoutée à la table d'emprunt ainsi que le titre de la video empruntée, et le nombre de copies de cette vidéo diminue
    dans la table vidéo.
    On affiche aussi un message d'erreur si l'étudiant ne peut pas emprunter, ou s'il a déjà emprunté cette vidéo  */

    public void EmprunterVideo(Connection conn) throws SQLException {
        String titre;
        texte t = new texte();
        Statement stmt = conn.createStatement();
        Statement stmt1 = conn.createStatement();
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.println("Entrez le titre du fichier video que vous voulez emprunter : ");
        titre = sc.nextLine();
        ResultSet rs = stmt.executeQuery("SELECT * FROM video WHERE nom='"+titre+"' AND nbr_copies>0");
        ResultSet re = stmt1.executeQuery("SELECT * FROM emprunt WHERE titre_media='"+titre+"' AND code_apoge = '"+this.apoge+"'");
        if(!re.next()) {
            if (rs.next()) {
                stmt.executeUpdate("INSERT INTO emprunt VALUES ('" + getApoge() + "','" + titre + "')");
                stmt.executeUpdate("UPDATE video SET nbr_copies=nbr_copies - 1 WHERE nom='" + titre + "'");

            } else System.out.println(">>>>> Impossible d'emprunter <<<<<");
            System.out.println("");
        }
        else System.out.println(">>>>> Vous avez déjà emprunté ce fichier Video !! <<<<<");
    }

    /* cette méthode demande à l'étudiant de saisir le titre du texte qu'il veut restituer, quand l'étudiant peut restituer le texte, la ligne d'emprunt de ce
    texte est supprimé de la table d'emprunt, et le nombre de copies de ce texte s'incrémente dans la table texte.
    On affiche aussi un message d'erreur si l'étudiant ne peut pas restituer. */

    public void RestituerTexte(Connection conn) throws SQLException {
        String titre;
        texte t = new texte();
        Statement stmt = conn.createStatement();
        Scanner sc = new Scanner(System.in);
        AfficheEmprunt(conn);
        System.out.println("");
        System.out.println("Entrez le titre du fichier texte que vous voulez restituer : ");
        titre = sc.nextLine();
        ResultSet rs = stmt.executeQuery("SELECT * FROM emprunt WHERE titre_media='"+titre+"' AND code_apoge='"+getApoge()+"'");
        if(rs.next()) {
            stmt.executeUpdate("DELETE FROM emprunt WHERE titre_media='"+titre+"'");
            stmt.executeUpdate("UPDATE texte SET nbr_copies=nbr_copies + 1 WHERE nom='"+titre+"'");

        }
        else System.out.println(">>>>> Impossible de restituer <<<<<");
        System.out.println("");
    }

    /* cette méthode demande à l'étudiant de saisir le titre du fichier audio qu'il veut restituer, quand l'étudiant peut restituer l'audio, la ligne d'emprunt
    de cet audio est supprimé de la table d'emprunt, et le nombre de copies de cet audio s'incrémente dans la table audio.
    On affiche aussi un message d'erreur si l'étudiant ne peut pas restituer. */

    public void RestituerAudio(Connection conn) throws SQLException {
        String titre;
        texte t = new texte();
        Statement stmt = conn.createStatement();
        Scanner sc = new Scanner(System.in);
        AfficheEmprunt(conn);
        System.out.println("");
        System.out.println("Entrez le titre du fichier audio que vous voulez restituer : ");
        titre = sc.nextLine();
        ResultSet rs = stmt.executeQuery("SELECT * FROM emprunt WHERE titre_media='"+titre+"' AND code_apoge='"+getApoge()+"'");
        if(rs.next()) {
            stmt.executeUpdate("DELETE FROM emprunt WHERE titre_media='"+titre+"'");
            stmt.executeUpdate("UPDATE audio SET nbr_copies=nbr_copies + 1 WHERE nom='"+titre+"'");

        }
        else System.out.println(">>>>> Impossible de restituer <<<<<");
        System.out.println("");
    }

    /* cette méthode demande à l'étudiant de saisir le titre du fichier vidéo qu'il veut restituer, quand l'étudiant peut restituer la vidéo, la ligne d'emprunt
    de cette vidéo est supprimée de la table d'emprunt, et le nombre de copies de cette vidéo s'incrémente dans la table vidéo.
    On affiche aussi un message d'erreur si l'étudiant ne peut pas restituer. */

    public void RestituerVideo(Connection conn) throws SQLException {
        String titre;
        texte t = new texte();
        Statement stmt = conn.createStatement();
        Scanner sc = new Scanner(System.in);
        AfficheEmprunt(conn);
        System.out.println("");
        System.out.println("Entrez le titre du fichier video que vous voulez restituer : ");
        titre = sc.nextLine();
        ResultSet rs = stmt.executeQuery("SELECT * FROM emprunt WHERE titre_media='"+titre+"' AND code_apoge='"+getApoge()+"'");
        if(rs.next()) {
            stmt.executeUpdate("DELETE FROM emprunt WHERE titre_media='"+titre+"'");
            stmt.executeUpdate("UPDATE video SET nbr_copies=nbr_copies + 1 WHERE nom='"+titre+"'");

        }
        else System.out.println(">>>>> Impossible de restituer <<<<<");
        System.out.println("");
    }

    //cette méthode affiche le menu de l'étudiant qui peut emprunter un média, le restituer ou revenir au menu principale de notre programme.
    public void MenuEtudiant(Connection conn) throws SQLException {
        media m = new media();
        Scanner sc = new Scanner(System.in);
        int c;

        do {
            AfficheEmprunt(conn);
            System.out.println("");
            System.out.println("1- Emprunter Media : ");
            System.out.println("2- Restituer Media : ");
            System.out.println("3- Menu Principal :");
            c = sc.nextInt();

            switch (c) {

                case 1:
                    m.EmprunterMedia(conn, this);
                    break;

                case 2:
                    m.RestituerMedia(conn, this);
                    break;

                case 3:
                    application.MenuPrincipal();
                    break;

                case 0:
                    return;
            }
        } while (c != 0);
    }

    //cette méthode affiche la liste d'emprunt de médias stockés dans la table emprunt de notre base de données.
    public void AfficheEmprunt(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        String c;
        ResultSet rs = stmt.executeQuery("SELECT * FROM emprunt WHERE code_apoge = '"+this.apoge+"'");
        System.out.println("");
        System.out.println("Les medias empruntés : ");
        System.out.println("");
        while(rs.next()){
            c = rs.getString("titre_media");
            System.out.println("----- "+c+" ------");
        }
    }

}