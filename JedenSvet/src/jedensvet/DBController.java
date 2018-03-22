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

       try (Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeden_svet?user=root&password=1111");
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
    
    public ResultSet doSelectFromFilm(String jmenoFilmu, String rok, String reziser, String popis) {
 
       ResultSet vysledky = null; 
        
       try (Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeden_svet?user=root&password=1111");
               PreparedStatement dotaz = spojeni.prepareStatement("SELECT * FROM film WHERE ? AND ? AND ? AND ?");) {
        
        // zabránění obalování "" (viz komplikace níže)   
        String queryText ="SELECT * FROM film WHERE 1 ";
           
        if ("".equals(jmenoFilmu)) {   
            
        } else {
           queryText +=" AND jmeno_filmu = ?";
           dotaz.setString(1, jmenoFilmu);
        }          
                   
        if ("".equals(rok)) { 
         
        } else {
           queryText +=" AND rok = ?";
           dotaz.setString(2, rok);
        } 
         
         
        // TODO: systém si vkládané hodnoty obaluje "" pro zabránění SQL Injection 
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
        
        vysledky = dotaz.executeQuery();
        
        
        } catch (SQLException ex) {
            System.out.println("Chyba při komunikaci s databází - select.");
        }

        return vysledky;       
    }
    
}