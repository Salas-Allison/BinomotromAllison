import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Récupérer la liste des apprennats en BDD
        ArrayList<String> listeApprenants = getListeApprenants();

    }

    private static ArrayList<String> getListeApprenants() {

        ArrayList<String> resultat = null;

        String user = "root";
        String pwd = "root";
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/binomontron";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(url, user, pwd);
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM apprenant");

            //Connexion à apprenant dans la BDD.
            //Recuperation des informations.
            ArrayList<String> listeApprenants = new ArrayList<>();
            while (rs.next()) {
                System.out.println(rs.getString("nom") + " " + rs.getString("prenom") + " " + rs.getString("email"));
                String nomcomplet = rs.getString("prenom") + " " + rs.getString("nom");

                listeApprenants.add(nomcomplet);
            }

            resultat = listeApprenants;

            // Mélanger la liste
            Collections.shuffle(listeApprenants);

            // Affichage de la liste
            System.out.println(listeApprenants);

            ArrayList<String> listegroupe = new ArrayList<String>();


            for (int i = 0; i < listeApprenants.size(); i+=2) {
                String st1 = listeApprenants.get(i);
                String st2 = null;
                if (i + 1 < listeApprenants.size()) {
                  String st2 = listeApprenants.get(i + 1);
                }
                // Créer une liste à partir de (n) éléments
                listegroupe.add(st1);
                listegroupe.add(st2);

            }
            // Affichage de la liste de groupe
            System.out.println(listegroupe);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Exception : " + e.getMessage());
            //Permet d'afficher un message d'erreur.

        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (Exception e) {
                //Deconnecter la BDD.
            }
        }
        return resultat;

    }

}