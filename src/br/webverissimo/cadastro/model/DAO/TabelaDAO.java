/*
 * TabelaDAO.java
 *
 * Created on 14 de Agosto de 2007, 21:18
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.webverissimo.cadastro.model.DAO;

import java.sql.SQLException;

/**
 *
 * @author pedro.quina
 */
public class TabelaDAO {
    
    private Database database;
    
    
    /** Creates a new instance of TabelaDAO */
    public TabelaDAO() {
    }
    
    public Database getDatabase() throws SQLException {
        
            if ( database==null) database = new Database();
            
        
        return database;
    }
    
    public void setDatabase(Database database) {
        this.database = database;
    }
    
}
