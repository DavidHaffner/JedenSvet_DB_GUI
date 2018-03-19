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
public class DBController {

    // zatím bez SQL Injection - tj. nebezpečný kód!
    
    public void doInsertToFilm(String jmenoFilmu, String rok, String reziser, String popis) {

       try (Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeden_svet?user=root&password=chelsea");
        PreparedStatement dotaz = spojeni.prepareStatement("INSERT INTO film (jmeno_filmu, rok, reziser, popis) VALUES (?, ?, ?, ?)");) {
        dotaz.setString(1, jmenoFilmu);
        dotaz.setString(2, rok);
        dotaz.setString(3, reziser);
        dotaz.setString(4, popis);
        int radku = dotaz.executeUpdate();
        System.out.printf("Do DB uloženo %d řádků.\n", radku);
        } catch (SQLException ex) {
            System.out.println("Chyba při komunikaci s databází");
        }

    }
}