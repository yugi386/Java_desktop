// ===============================================================
// Acesso a banco para tabela Fornecedor e tabelas relacionadas...
// ===============================================================
package br.webverissimo.cadastro.model.DAO;

import br.webverissimo.cadastro.model.MODEL.SuperDTO;
import br.webverissimo.cadastro.model.MODEL.SuperModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// public class FornecedorDAO extends TabelaDAO {
public class FornecedorDAO extends ModelExtends {    

    private String tabelaDAO = "fornecedores";  // Tabela Base de Dados.

    public FornecedorDAO(){ //  Método construtor...
        setTabelaDAO(tabelaDAO); // inicia ModelExtends...
    } 
// -----------------------------------------------------------------------------
// Este Método retorna os campos da tabela de dados. Deve ser personalizado para cada DAO    
    public String[] RetornaCampos() throws SQLException {
        String [] ret = 
            {"id","nome","telefone","valorComprado","dataultimaCompra","ie",
             "cnpj","ativo","observacao","rua","bairro","cidade","estado_id"};
        return ret;
    }
// ------------------------------------------------------------------------------------    
// Método responsavel por inserir ou alterar informacoes no banco de dados...    
    public SuperDTO gravar(SuperDTO fornecedor) throws SQLException {
        if (fornecedor == null){   //  verifica erros no preenchimento do DTO...
            return null;
        }
        SuperModel model = new SuperModel();
        if (Integer.parseInt(fornecedor.getAtrib().get("id")) == 0) {
            model.inserir(this.tabelaDAO, RetornaCampos(), fornecedor );
        } else {
            model.alterar(this.tabelaDAO, RetornaCampos(), fornecedor );
        }
        gravarContatos(fornecedor);   //  inserção e alteração.
        return fornecedor;
    }    
// =========================================================================================    
// Gravar contatos dos fornecedores.
    public void gravarContatos(SuperDTO fornecedor) throws SQLException {
        // Primeiro devemos excluir os contatos do fornecedor.
        excluirContatoFornecedor(fornecedor);
        
        // Agora gravar aqueles que foram inseridos ou alterados:
        List<Map<String , String>> listaRegistros  = new ArrayList<Map<String,String>>();
        
        //  campo do SuperDTO destinado a relacionamentos de N para N (RelC).
        listaRegistros = fornecedor.getRelC(); 
        
        int tam = listaRegistros.size();    //  lendo total de registros...
        String campos[] = new String[3];   //  total de campos da tabela.
        String valores[] = new String[3];   //  total de valores a inserir.
        
        campos[0] = "id";   //  id falso para compatibilidade com SuperModel que não grava o id por supor auto-incremento
        campos[1] = "fornecedores_id";
        campos[2] = "contatos_id";   //  campos da tabela
        
        SuperModel model = new SuperModel();
        SuperDTO contatos_fornecedores = new SuperDTO();            
        
        // Gravando os departamentos de cada fornecedor no banco...
        for (int ct=0;ct<tam;ct++){
            // Lendo departamentos do fornecedor atual...
            valores[0] = "0"; //  id falso para compatibilidade com SuperModel
            valores[1] = listaRegistros.get(ct).get("fornecedores_id");  
            valores[2] = listaRegistros.get(ct).get("contatos_id");
            
            // aqui fazemos a inclusao de cada departamento em separado...
            contatos_fornecedores.setAtrib(campos, valores);
            model.inserir("contatos_fornecedores", campos, contatos_fornecedores );
        }
    }        
// --------------------------------------------------------------------------------
// Método para excluir um registro com condicao - contatos do fornecedor...
    public void excluirContatoFornecedor(SuperDTO fornecedor) throws SQLException {
        SuperModel model = new SuperModel();
        model.excluirCondicional("contatos_fornecedores", "fornecedores_id="+fornecedor.getAtrib().get("id"));
    }
// -------------------------------------------------------------------------------------------------------    
} /// fim da classe