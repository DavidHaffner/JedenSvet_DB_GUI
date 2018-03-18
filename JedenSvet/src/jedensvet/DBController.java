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
    
    public void doInsertToFilm(String jmenoFilmu, int rok, String reziser, String popis) {

       try (Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost/jeden_svet?user=root&password=1111");
        PreparedStatement dotaz = spojeni.prepareStatement("INSERT INTO film (jmeno_filmu, rok, reziser, popis) VALUES (?, ?, ?, ?)");) {
        dotaz.setString(2, jmenoFilmu);
        dotaz.setString(3, Integer.toString(rok));
        dotaz.setString(4, reziser);
        dotaz.setString(5, popis);
        int radku = dotaz.executeUpdate();
        System.out.println(radku);
        } catch (SQLException ex) {
            System.out.println("Chyba při komunikaci s databází");
        }

    }
}