package br.webverissimo.cadastro.model.MODEL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DTO padrão para qualquer tabela...
 */
public class SuperDTO {

    // Atributos da tabela:
    private Map<String, String> atrib = new HashMap<String, String>();
    
    // Atributos do relacionamento 1 - N.
    private Map<String, String> relS = new HashMap<String, String>();
    
    // Atributos do relacionamento N - N.
    private List<Map<String , String>> relC  = new ArrayList<Map<String,String>>();
    
// ============================================================================
//  MÉTODOS GENÉRICOS SET E GET
// ============================================================================
    
    // Setando atributos...
    public void setAtrib(String[] campos, String[] valores) throws SQLException{
        for (int ct=0;ct<campos.length;ct++){
            this.atrib.put(campos[ct], valores[ct]);     
        }    
    }

    // Retorna o hash de atributos do objeto
    public Map<String , String> getAtrib() {
        return atrib;
    }    
    
// -----------------------------------------------------------------------------    
// Método para preencher uma lista com dados de relacionamento 1 - N.
// RELACIONAMENTO 1 - N.
    public void setRelS(String[] campos,String[] valores) throws SQLException{
        for (int ct=0;ct<campos.length;ct++){
            this.relS.put(campos[ct], valores[ct]);     
        }    
    }

// Retorna uma lista de hash com dados do relacionamento...
// RELACIONAMENTO 1 - N.    
    public Map<String , String> getRelS() {
        return relS;
    }
    
// ----------------------------------------------------------------------------        
// Método para preencher uma lista com dados de relacionamento N - N.
// RELACIONAMENTO N - N.

    public void setRelC(String[] campos, String valores[]) throws SQLException{
            Map<String, String> Mapa = new HashMap<String, String>();

            for (int ct=0;ct<campos.length;ct++){
                Mapa.put(campos[ct], valores[ct]);     
            }
            
            this.relC.add(Mapa); // adiciona hash à lista.
       }
    
 // Retorna uma lista de hash com dados do relacionamento...
 // RELACIONAMENTO N - N.    
    public List<Map<String , String>> getRelC() {
        return relC;
    }
    
    /*
    public void setRelC(String[] campos, ResultSet dados) throws SQLException{
        while(dados.next()){                        
            Map<String, String> Mapa = new HashMap<String, String>();

            for (int ct=0;ct<campos.length;ct++){
                Mapa.put(campos[ct], dados.getString(campos[ct]));     
            }
            this.relC.add(Mapa); // adiciona hash à lista.
        }    
    }
*/
    
}

// ----------------------------------------------------------------------------
