import java.sql.*;
import java.util.Scanner;

public class texte extends media {

    String titre;
    String auteur;
    int nbrCopies;

    //constructeur par défault
    public texte(){};

    //constructeur paramétré
    public texte(String titre, String auteur, int nbrCopies){
        super(titre,nbrCopies);
        this.auteur = auteur;
    }

    // cette méthode demande à l'utlisateur de saisir le titre, l'auteur et le nombre de copies d'un texte pour l'ajouter à notre base de donnée
    public void AjoutTexte(Connection conn) throws SQLException{

        Statement stmt = conn.createStatement();
        Scanner sc = new Scanner(System.in);

        System.out.println("Entrer le titre de ce texte !");
        this.titre = sc.nextLine();
        System.out.println("Entrer l'auteur de ce texte !");
        this.auteur = sc.nextLine();
        System.out.println("Entrer le nombre de copies de ce texte !");
        this.nbrCopies = sc.nextInt();

        stmt.executeUpdate("INSERT INTO texte VALUES('"+this.titre+"','"+this.auteur+"','"+this.nbrCopies+"')");

    }

    /*cette méthode affiche la liste des textes disponibles, puis affiche un menu où l'utilisateur doit choisir entre modifier le titre, l'auteur ou le nombre
    de copies */
    public void ModifyTexte(Connection conn) throws SQLException{
        Statement stmt = conn.createStatement();
        Scanner sc = new Scanner(System.in);
        admin a = new admin();
        database db = new database();
        int choix;


        do{
            AfficheTexte(db.dbStatus());
            System.out.println("-----------------Modification Texte------------------");
            System.out.println("1- pour modifier le titre ");
            System.out.println("2- pour modifier l'auteur ");
            System.out.println("3- pour modifier le nombre de copies ");
            System.out.println("4- Menu Admin");
            choix = sc.nextInt();

            switch (choix){
                case 1 : ModifyTitle(db.dbStatus());
                break;
                case 2 : ModifyAuteur(db.dbStatus());
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
        stmt.executeUpdate("UPDATE texte SET nom='"+nvTitre+"' WHERE nom='"+this.titre+"'");
    }

    //cette méthode demande à l'utilisateur de saisir l'ancien auteur qu'il veut modifier et le nouveau auteur
    public void ModifyAuteur(Connection conn) throws SQLException{
        String nvAuteur;
        Statement stmt = conn.createStatement();
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez entrer quel auteur voulez-vous modifier : ");
        this.auteur = sc.nextLine();
        System.out.println("Entrez le nouveau auteur : ");
        nvAuteur = sc.nextLine();
        stmt.executeUpdate("UPDATE texte SET auteur='"+nvAuteur+"' WHERE auteur='"+this.auteur+"'");
    }

    //cette méthode demande à l'utilisateur de saisir le nouveau nombre de copies et le titre du texte qu'il veut modifier son nombrte de copies
    public void ModifyNbrCopies(Connection conn) throws SQLException{
        String nvNbr;
        Statement stmt = conn.createStatement();
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez entrer quel titre voulez-vous modifier le nombre de copies : ");
        this.titre = sc.nextLine();
        System.out.println("Entrez le nouveau nombre de copies : ");
        nvNbr = sc.nextLine();
        stmt.executeUpdate("UPDATE texte SET nbr_copies='"+nvNbr+"' WHERE nom='"+this.titre+"'");
    }

    //cette méthode demande à l'utilisateur de saisir le titre du texte qu'il veut supprimer
    public void DeleteTexte(Connection conn) throws SQLException{
        String choix;
        Statement stmt = conn.createStatement();
        Scanner sc = new Scanner(System.in);
        System.out.println("------Suppression d'un fichier Texte------");
        System.out.println("Entrer le titre du texte que vous voulez supprimer : ");
        choix = sc.nextLine();
        stmt.executeUpdate("DELETE FROM texte WHERE nom='"+choix+"'");

    }

    //cette demande affiche la liste des textes disponibles dans notre base de données
    public void AfficheTexte(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM texte");
        System.out.println("------- LISTE DES FICHIERS TEXTES DISPONIBLES -------");
        System.out.println("");
        while(rs.next()){
            this.titre = rs.getString("nom");
            this.auteur = rs.getString("auteur");
            this.nbrCopies = rs.getInt("nbr_copies");
            System.out.println("Titre : "+this.titre+"          | Auteur : "+this.auteur+"         |  Nombre de copies : "+this.nbrCopies);
        }
    }



}
