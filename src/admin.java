import java.sql.*;
import java.util.Scanner;

public class admin {


    String username;
    String password;

    //constructeur par défault
    admin(){};

    //constructeur paramétré
    admin(String username, String password){
        this.username = username;
        this.password = password;
    };

    //les méthodes pour définir les valeurs des attributs de notre classe
    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }


    //les méthodes pour retourner les valeurs des attributs de notre classe
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }


    /*cette méthode demande à l'administrateur de saisir son nom d'utilisateur et son mot de passe puis vérifie si les données saisies figurent sur notre base
     de données pour pouvoir lui donner accès */

    public void loginAdmin(Connection conn) throws SQLException{
        Statement stmt = conn.createStatement();
        Scanner sc = new Scanner(System.in);
        System.out.println("------ LOGIN ADMIN -------");
        System.out.println("");
        System.out.println("Entrer votre login  : ");
        setUsername(sc.nextLine());
        System.out.println("Entrer votre mot de passe : ");
        setPassword(sc.nextLine());

        ResultSet rs =stmt.executeQuery("SELECT * FROM admin WHERE username ='"+this.username+"' AND password = '"+this.password+"'");

        if(rs.next()) menuAdmin(conn);
        else loginAdmin(conn);
    }

    /*cette méthode affiche le menu de l'administrateur et lui demande de choisir entre ajouter média, modifier média, supprimer média ou revenir au menu
    principale */

    public void menuAdmin(Connection conn) throws SQLException{
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
            System.out.println("-------- ESPACE ADMIN ---------");
            System.out.println("");
            System.out.println("1- Ajouter Media : ");
            System.out.println("2- Modifier Media : ");
            System.out.println("3- Supprimer Media : ");
            System.out.println("4- Menu Principal :");
            c = sc.nextInt();

            switch(c){

                case 1 : m.AjoutMedia(conn);
                    break;

                case 2 : m.ModifyMedia(conn);
                    break;

                case 3 : t.DeleteMedia(conn);
                    break;

                case 4 : application.MenuPrincipal();
                    break;

                case 0 : return;
            }
        }while(c!=0);
    }


}


