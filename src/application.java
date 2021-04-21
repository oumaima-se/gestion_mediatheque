import java.sql.SQLException;
import java.util.Scanner;

public class application {

    //la méthode main exécute la méthode du menu principale
    public static void main(String[] args) throws SQLException {
        MenuPrincipal();
    }

    //cette méthode affiche le menu principalle de notre programme et demande à l'utlisateur de saisir son choix
public static void MenuPrincipal() throws SQLException {
    etudiant e = new etudiant();
    admin a = new admin();
    database db = new database();
    media m = new media();
    texte t = new texte();
    audio aud = new audio();
    video vid = new video();
    Scanner sc = new Scanner(System.in);
    int c;

        do{
        System.out.println("-------------------------------------------------------------");
        System.out.println("---------- BIENVENUE A LA BIBLIOTHEQUE ELECTRONIQUE ---------");
        System.out.println("-------------------------------------------------------------");
        System.out.println("");
        System.out.println("   [ ESPACE ETUDIANT ]   ");
        System.out.println("");
        System.out.println("1- Etudiant Inscription : ");
        System.out.println("2- Etudiant Login : ");
        System.out.println("");
        System.out.println("----------------------------");
        System.out.println("");
        System.out.println("   [ ESPACE ADMIN ]   ");
        System.out.println("");
        System.out.println("3- Admin Login : ");
        System.out.println("");
        System.out.println("----------------------------");
        c = sc.nextInt();

        switch(c){
            case 1 : e.inscriptionEtu(db.dbStatus());
                break;
            case 2 : e.loginEtu(db.dbStatus());
                break;
            case 3 : a.loginAdmin(db.dbStatus());
                break;


            case 0 : return;
        }
    }while(c!=0);
    }
}
