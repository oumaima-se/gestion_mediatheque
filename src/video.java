import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class video extends media{

    String titre;
    String duree;
    int nbrCopies;

    //constructeur par défault
    public video(){};

    //constructeur paramétré
    public video(String titre, String duree, int nbrCopies){
        super(titre,nbrCopies);
        this.duree = duree;
    }

    // cette méthode demande à l'utlisateur de saisir le titre, la durée et le nombre de copies d'une vidéo pour l'ajouter à notre base de donnée
    public void AjoutVideo(Connection conn) throws SQLException {

        Statement stmt = conn.createStatement();
        Scanner sc = new Scanner(System.in);

        System.out.println("Entrer le titre de cette video !");
        this.titre = sc.nextLine();
        System.out.println("Entrer la durée de cette video !");
        this.duree = sc.nextLine();
        System.out.println("Entrer le nombre de copies de cette video !");
        this.nbrCopies = sc.nextInt();

        stmt.executeUpdate("INSERT INTO video VALUES('"+this.titre+"','"+this.duree+"','"+this.nbrCopies+"')");

    }

    /*cette méthode affiche la liste des vidéos disponibles, puis affiche un menu où l'utilisateur doit choisir entre modifier le titre, la durée ou le nombre
    de copies */
    public void ModifyVideo(Connection conn) throws SQLException{
        Statement stmt = conn.createStatement();
        Scanner sc = new Scanner(System.in);
        admin a = new admin();
        database db = new database();
        int choix;

        ResultSet rs = stmt.executeQuery("SELECT * FROM video");
        while(rs.next()){
            this.titre = rs.getString("nom");
            this.duree = rs.getString("duree");
            this.nbrCopies = rs.getInt("nbr_copies");

            System.out.println("Titre : "+this.titre+" Durée : "+this.duree+" Nombre de copies : "+this.nbrCopies);
        }

        do{
            System.out.println("-----------------Modification Video------------------");
            System.out.println("1- pour modifier le titre ");
            System.out.println("2- pour modifier la durée ");
            System.out.println("3- pour modifier le nombre de copies ");
            System.out.println("4- Menu Admin");
            choix = sc.nextInt();

            switch (choix){
                case 1 : ModifyTitle(db.dbStatus());
                    break;
                case 2 : ModifyDuree(db.dbStatus());
                    break;
                case 3 : ModifyNbrCopies(db.dbStatus());
                    break;
                case 4 : a.menuAdmin(conn);
            }

        }while(choix!=5);

    }

    //cette méthode demande à l'utilisateur de saisir l'ancien titre qu'il veut modifier et le nouveau titre
    public void ModifyTitle(Connection conn) throws SQLException{
        String nvTitre;
        Statement stmt = conn.createStatement();
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez entrer quel titre voulez-vous modifier : ");
        this.titre = sc.nextLine();
        System.out.println("Entrez le nouveau titre : ");
        nvTitre = sc.nextLine();
        stmt.executeUpdate("UPDATE video SET nom='"+nvTitre+"' WHERE nom='"+this.titre+"'");
    }

    //cette méthode demande à l'utilisateur de saisir la nouvelle durée et le titre de la vidéo qu'il veut modifier sa durée
    public void ModifyDuree(Connection conn) throws SQLException{
        String nvDuree;
        Statement stmt = conn.createStatement();
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez entrer le titre de la video que vous voulez modifier sa durée : ");
        this.duree = sc.nextLine();
        System.out.println("Entrez la nouvelle durée : ");
        nvDuree = sc.nextLine();
        stmt.executeUpdate("UPDATE video SET duree='"+nvDuree+"' WHERE duree='"+this.duree+"'");
    }

    //cette méthode demande à l'utilisateur de saisir le nouveau nombre de copies et le titre de la vidéo qu'il veut modifier son nombre de copies
    public void ModifyNbrCopies(Connection conn) throws SQLException{
        String nvNbr;
        Statement stmt = conn.createStatement();
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez entrer le titre vous voulez modifier son nombre de copies : ");
        this.titre = sc.nextLine();
        System.out.println("Entrez le nouveau nombre de copies : ");
        nvNbr = sc.nextLine();
        stmt.executeUpdate("UPDATE video SET nbr_copies='"+nvNbr+"' WHERE nom='"+this.titre+"'");
    }

    //cette méthode demande à l'utilisateur de saisir le titre de la vidéo qu'il veut supprimer
    public void DeleteVideo(Connection conn) throws SQLException{
        String choix;
        Statement stmt = conn.createStatement();
        Scanner sc = new Scanner(System.in);
        System.out.println("------Suppression d'un fichier Video------");
        System.out.println("Entrer le titre du video que vous voulez supprimer : ");
        choix = sc.nextLine();
        stmt.executeUpdate("DELETE FROM video WHERE nom='"+choix+"'");

    }

    //cette demande affiche la liste des vidéos disponibles dans notre base de données
    public void AfficheVideo(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM video");
        System.out.println("------- LISTE DES FICHIERS VIDEOS DISPONIBLES -------");
        System.out.println("");
        while(rs.next()){
            this.titre = rs.getString("nom");
            this.duree = rs.getString("duree");
            this.nbrCopies = rs.getInt("nbr_copies");
            System.out.println("Titre : "+this.titre+"           |  Durée : "+this.duree+"         |  Nombre de copies : "+this.nbrCopies);
        }
    }
}
