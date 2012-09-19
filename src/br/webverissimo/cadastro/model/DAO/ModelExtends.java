/*
 * GENERALIZAÇÃO AUXILIAR PARA DAO
  */
package br.webverissimo.cadastro.model.DAO;

import br.webverissimo.cadastro.model.MODEL.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ModelExtends extends TabelaDAO {
    
    private String tabelaDAO = "";  // Tabela Base de Dados.

    public String getTabelaDAO() {  //  Define tabela de trabalho
        return tabelaDAO;
    }

    public void setTabelaDAO(String tabelaDAO) {    //  Retorna tabela de trabalho
        this.tabelaDAO = tabelaDAO;
    }
// ============================================================================================
// Método para excluir um registro pelo Id.
    public void excluir(SuperDTO modeloSuperDTO) throws SQLException {
        SuperModel model = new SuperModel();
        model.excluir(this.tabelaDAO, Integer.parseInt(modeloSuperDTO.getAtrib().get("id")));
    }
// =========================================================================================    
// Lista todos os fornecedores do banco de dados; permite especificar uma condicao...
    public List listar(String condicao) throws SQLException {
        SuperModel model = new SuperModel();
        String Campos[] = RetornaCampos();
        ResultSet lista = model.list(this.tabelaDAO,RetornaCampos(),condicao);
        return model.DevolveLista(Campos,lista);
    }
// =========================================================================================    
// Listar Tabelas Associadas ao modeloSuperDTO: estado, contatos_fornecedores.
    public List listarTabelasAssociadas(String tabela, String Campos[],String condicao) throws SQLException {
        SuperModel model = new SuperModel();
        ResultSet lista = model.list(tabela,Campos,condicao);
        return model.DevolveLista(Campos,lista);
    }        
// =========================================================================================    
    // Este Método retorna os campos da tabela de dados. Deve ser personalizado para cada DAO    
    //  Método deve ser sobreescrito
    public String[] RetornaCampos() throws SQLException {
        String [] ret = {""};
        return ret;
    }
// ================================================================================================    
}