/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jedensvet;

import java.sql.*;

/**
 *
 * @author dhaffner
 */
public class DBController implements IDBController {

    // zatím bez SQL Injection - tj. nebezpečný kód!
    
    public final String DB_PATH = "jdbc:mysql://localhost:3306/jeden_svet?user=root&password=1111";

    
    @Override
    public void doInsertToFilm(String jmenoFilmu, String rok, String reziser, String popis) {

        try (Connection spojeni = DriverManager.getConnection(DB_PATH);
                PreparedStatement dotaz = spojeni.prepareStatement("INSERT INTO film (jmeno_filmu, rok, reziser, popis) VALUES (?, ?, ?, ?)");) {
            dotaz.setString(1, jmenoFilmu);
            dotaz.setString(2, rok);
            dotaz.setString(3, reziser);
            dotaz.setString(4, popis);
            int radku = dotaz.executeUpdate();
            System.out.printf("Do DB uloženo %d řádků.\n", radku);
        } catch (SQLException ex) {
            System.out.println("Chyba při komunikaci s databází - insert.");
        }

    }

    @Override
    public ResultSet doSelectFromFilm(String jmenoFilmu, String rok, String reziser, String popis) {

        ResultSet vysledky = null;

        // zabránění obalování "" (viz komplikace níže)   
        String queryText = "SELECT * FROM film WHERE 1";

        if ("".equals(jmenoFilmu)) {
            queryText += " AND 1";
        } else {
            queryText += " AND jmeno_filmu = \"" + jmenoFilmu + "\"";
        }
        if ("".equals(rok)) {
            queryText += " AND 1";
        } else {
            queryText += " AND rok = \"" + rok + "\"";
        }
        if ("".equals(reziser)) {
            queryText += " AND 1";
        } else {
            queryText += " AND reziser = \"" + reziser + "\"";
        }
        if ("".equals(popis)) {
            queryText += " AND 1;"; 
        } else {
            queryText += " AND popis = \"" + popis + "\";";
        }

        try (Connection spojeni = DriverManager.getConnection(DB_PATH);
                PreparedStatement dotaz = spojeni.prepareStatement(queryText);) {

            /* nefunkční: systém si vkládané hodnoty obaluje "" pro zabránění SQL Injection 
        String text;   
        if ("".equals(jmenoFilmu)) {   
            text ="1";
            dotaz.setString(1, text);   
        } else {
           text =" jmeno_filmu =" + jmenoFilmu;
           dotaz.setString(1, text);
        }
        if ("".equals(rok)) { 
           text ="1";
           dotaz.setString(2, text);   
        } else {
           text =" rok =" + rok;
           dotaz.setString(2, text);
        }
        if ("".equals(reziser)) { 
           text ="1"; 
           dotaz.setString(3, text);   
        } else {
           text =" reziser = " + reziser;
           dotaz.setString(3, text);
        }
        if ("".equals(popis)) {   
           text ="1"; 
           dotaz.setString(4, text);   
        } else {
           text =" popis = " + popis;
           dotaz.setString(4, text);
        }
             */
            vysledky = dotaz.executeQuery();

        } catch (SQLException ex) {
            System.out.println("Chyba při komunikaci s databází - select.");
        }

        return vysledky;
    }

    @Override
    public void doUpdateToFilm(String jmenoFilmu, String rok, String reziser, String popis) {

    }
}
