/*
 * SUPER CLASSE PARA MANIPULAR O BANCO DE DADOS...
*/
package br.webverissimo.cadastro.model.MODEL;

import br.webverissimo.cadastro.model.DAO.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe responsável para facilitar o acesso a base de dados 
 * @author Edimar Veríssimo
 */
public class SuperModel extends TabelaDAO {

// ====================================================================================================    
//  MÉTODOS ABAIXO JÁ FORAM TESTADOS    
// -------------------------------------------------------------------------------------------------    
/**
     * Insere um novo registro na tabela de dados
     * @param tabela: string com o nome da tabela
     * @param campos: string com os campos
     * @param values: Objeto com o conteudo dos campos.
     */
    public void inserir(String tabela, String[] campos, SuperDTO geralDTO) throws SQLException {
        String campo = "";
        String valor = "";

        // Converte SuperDTO para um vetor de objetos...
        Object[] valores = RetornaValor(geralDTO, campos);
        
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
        gravar( sql, valores );
        
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
        // System.out.println(sql);
    }
    
// -------------------------------------------------------------------------------------------------    
    /**
     * Exclui um registro com condicao...
     */
    public void excluirCondicional(String tabela, String condicao) throws SQLException {
        String sql = "Delete from " + tabela + " where " + condicao;
        PreparedStatement pstmt = getDatabase().getInstance().prepareStatement(sql);
        // pstmt.setObject(1, condicao);
        pstmt.executeUpdate();
        pstmt.close();
        // System.out.println(sql);
    }    
// ----------------------------------------------------------------------------------
/**
     * Alterar um novo registro na tabela de dados
     * @param tabela: string com o nome da tabela
     * @param campos: string com os campos
     * @param values: Objeto com o conteudo dos campos.
     */
    public void alterar(String tabela, String[] campos, SuperDTO geralDTO) throws SQLException {
        String campo = "";
        String valor = "";

        // Converte SuperDTO para um vetor de objetos...
        Object[] valores = RetornaValor(geralDTO, campos);
        
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
        alt( sql, valores );
    }

// -------------------------------------------------------------------------------------------------
// metodo para gravar os dados via prepare statement
    // auxiliar do metodo incluir
    // pula o primeiro elemento que é a chave primaria...
    public void gravar( String sql , Object[] parametros) throws SQLException{
        PreparedStatement pstmt = getDatabase().getInstance().prepareStatement(sql);
        for (int ct=0;ct<parametros.length-1;ct++){
            pstmt.setObject(ct+1, parametros[ct+1]);
        }
        pstmt.executeUpdate();
        pstmt.close();
       
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
// metodo para selecionar um registro, todos ou especificar condicao
    public ResultSet list(String tabela, String[] campos,String condicao) throws SQLException {

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
        if (condicao.length() == 0){
            sql = "Select "+campo+" from "+tabela;
        } else {
            sql = "Select "+campo+" from "+tabela + " where " + condicao ;
        }    

        // System.out.println(sql);
        ResultSet lista = getDatabase().getInstance().createStatement().executeQuery(sql);

        // lista.close();

        return lista;
    }
// -------------------------------------------------------------------------------------    
// Este Método transforma o DTO em um Objeto genérico
    public Object[] RetornaValor(SuperDTO usuario, String[] campos) throws SQLException {
        Object[] ret = new Object[campos.length]; //  Total de campos a retornar
        
        for (int ct=0;ct<campos.length;ct++){
            ret[ct] = usuario.getAtrib().get(campos[ct]);
        }
        
        return ret;
    }
    
// ======================================================================================    
// Este método devolve uma lista de hash em vez do ResultSET    
public List DevolveLista(String Campos[],ResultSet lista) throws SQLException{
    
    List<Map<String , String>> listaRegistros  = new ArrayList<Map<String,String>>();
    while(lista.next()){                        
               Map<String, String> Mapa = new HashMap<String, String>();

               for (int ct=0;ct<Campos.length;ct++){
                    Mapa.put(Campos[ct], lista.getString(Campos[ct]));     
               }
               listaRegistros.add(Mapa);
           }    

           if (!listaRegistros.isEmpty()) {
               return listaRegistros;
           } else {
               return null;
           }
   }
// -----------------------------------------------------------------------------------------    
}