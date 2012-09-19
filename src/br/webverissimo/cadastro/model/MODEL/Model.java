package br.webverissimo.cadastro.model.MODEL;

import br.webverissimo.cadastro.model.DAO.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsável para facilitar o acesso a base de dados dia DAO
 * @author Edimar Veríssimo
 */
public class Model extends TabelaDAO {

// ====================================================================================================    
//  MÉTODOS ABAIXO JÁ FORAM TESTADOS    
// -------------------------------------------------------------------------------------------------    
/**
     * Insere um novo registro na tabela de dados
     * @param tabela: string com o nome da tabela
     * @param campos: string com os campos
     * @param values: Objeto com o conteudo dos campos.
     */
    public void inserir(String tabela, String[] campos, Object[] values) throws SQLException {
        String campo = "";
        String valor = "";

        // Montando a string de campos para inclusão...
        // Começa em 1 porque o 0 é a chave primária autoincremento
        for (int ct=1;ct<campos.length;ct++){
            if (ct==campos.length-1){
                campo = campo  + campos[ct];
            } else {
                campo = campo  + campos[ct] + ", "; 
            }    
        }
        
        // Começa em 1 porque o 0 é a chave primária autoincremento
        // preenchendo ? para prepareStatement
        for (int ct=1;ct<campos.length;ct++){
            if (ct==campos.length-1){
                valor = valor  + "?";
            } else {
                valor = valor  + "?" + ",";
            }    
        }
        
        String sql = "Insert into " + tabela + " (" + campo + ") values("+valor+")";
        // System.out.print(sql);
        gravar( sql, values );
        
        // exemplo do uso de hash em java....
        /*
        Map<String, String> hash = new HashMap<String, String>();  
        hash.put("estado", "alagoas");  
        hash.put("pais", "brasil");  
        System.out.print(hash.get("pais").toString());
        System.out.print(hash.get("estado").toString());
        */ 
    }


// -------------------------------------------------------------------------------------------------
// Método que retorna o ultimo ID da tabela
    public int selecionarUltimoID(String tabela) throws SQLException {
        String sql = "Select MAX(id) as ultimo from " + tabela;
        ResultSet rs = getDatabase().getInstance().createStatement().executeQuery(sql);
        int id = -1;
        while (rs.next()) {
            id = rs.getInt("ultimo");
        }
        return id;
    }    

// -------------------------------------------------------------------------------------------------    
    /**
     * Exclui um registro selecionado
     * @param tabela = nome da tabela
     *  Id = chave primaria do registro a excluir.
     */
    public void excluir(String tabela, int Id) throws SQLException {
        String sql = "Delete from " + tabela + " where id=?";
        PreparedStatement pstmt = getDatabase().getInstance().prepareStatement(sql);
        pstmt.setObject(1, Id);
        pstmt.executeUpdate();
        pstmt.close();
    }
    
// ----------------------------------------------------------------------------------
/**
     * Alterar um novo registro na tabela de dados
     * @param tabela: string com o nome da tabela
     * @param campos: string com os campos
     * @param values: Objeto com o conteudo dos campos.
     */
    public void alterar(String tabela, String[] campos, Object[] values) throws SQLException {
        String campo = "";
        String valor = "";

        // Montando a string de campos para inclusão...
        // Começa em 1 porque o 0 é a chave primária autoincremento
        for (int ct=1;ct<campos.length;ct++){
            if (ct==campos.length-1){
                campo = campo  + campos[ct] + " = " + "?";
            } else {
                campo = campo  + campos[ct] +  " = " + "?, "; 
            }    
        }
        
        String sql = "Update " + tabela + " set " + campo + " where " + campos[0] + "=?";
        alt( sql, values );
    }

// -------------------------------------------------------------------------------------------------
// metodo para gravar os dados via prepare statement
    // auxiliar do metodo incluir
    public void gravar( String sql , Object[] parametros) throws SQLException{
        PreparedStatement pstmt = getDatabase().getInstance().prepareStatement(sql);
        for (int ct=0;ct<parametros.length-1;ct++){
            pstmt.setObject(ct+1, parametros[ct+1]);
        }
        pstmt.executeUpdate();
        pstmt.close();
        
        /*
         * // pequeno teste...
         * no banco de dados se o dado estiver no formato correto
         * mesmo que string em campo numero o valor é aceito.
         * 
        String sql2 = "Insert into cargos (id, descricao) values (?,?)";
        PreparedStatement pstmt2 = getDatabase().getInstance().prepareStatement(sql2);
        
        int num;
        num = selecionarUltimoID("cargos")+1;
        Object[] campos = {num + "","teste"};
        
        pstmt2.setObject(1, campos[0]);
        pstmt2.setObject(2, campos[1]);
        pstmt2.executeUpdate();
        pstmt2.close();
        */

    }    

// -------------------------------------------------------------------------------------------------
// metodo para gravar os dados via prepare statement
// auxiliar do metodo alterar para alteracao de dados
    public void alt( String sql , Object[] parametros) throws SQLException{
        PreparedStatement pstmt = getDatabase().getInstance().prepareStatement(sql);
        for (int ct=1;ct<parametros.length;ct++){
            pstmt.setObject(ct, parametros[ct]);
        }
        pstmt.setObject(parametros.length, parametros[0]);
        pstmt.executeUpdate();
        pstmt.close();

    }            

// ------------------------------------------------------------------------------    
    // metodo para selecionar um registro, todos ou especificar condicao - retorna um objeto.
    public Object list(String tabela, int id, String[] campos,String condicao) throws SQLException {

        int tam = 1;
        if (id != 0){
            // verifica total de registros se a listagem for de todos os registros.    
            String conta =  "Select count(*) as total from "+tabela + " " + condicao ;
            ResultSet lista = getDatabase().getInstance().createStatement().executeQuery(conta);
            while (lista.next()) {
                tam = lista.getInt("total");    
            }
        }
        
        Object registro[][] = new Object[tam][campos.length];
        String campo = "", sql="";
        
        // Montando a string de campos para inclusão...
        // Começa em 0, todos os campos
        for (int ct=0;ct<campos.length;ct++){
            if (ct==campos.length-1){
                campo = campo  + campos[ct];
            } else {
                campo = campo  + campos[ct] + ", "; 
            }    
        }
        if (id != 0){
            sql = "Select "+campo+" from "+tabela+" where id=" + id;
        } else {
            sql = "Select "+campo+" from "+tabela + " " + condicao ;
        }    

        ResultSet lista = getDatabase().getInstance().createStatement().executeQuery(sql);
        // se forem listados mais de um registro deve se retornar uma lista
        if (id == 0){
            return lista;
        }    
        
        int ct = 0;
        while (lista.next()) {
            for (int lp=0;lp<campos.length;lp++){
                registro[ct][lp] = lista.getObject(campos[lp]);    
            }    
            ct++;
        }

        lista.close();
        return registro;
    }
// -------------------------------------------------------------------------------------    
}