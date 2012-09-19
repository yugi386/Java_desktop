// ===============================================================
// Acesso a banco para tabela Cliente e tabelas relacionadas...
// ===============================================================
package br.webverissimo.cadastro.model.DAO;

import br.webverissimo.cadastro.model.MODEL.SuperDTO;
import br.webverissimo.cadastro.model.MODEL.SuperModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteDAO extends ModelExtends {

    private String tabelaDAO = "clientes";  // Tabela Base de Dados.

    public ClienteDAO(){ //  Método construtor...
        setTabelaDAO(this.tabelaDAO); // inicia ModelExtends...
    } 
// ---------------------------------------------------------------------------------------
// Este Método retorna os campos da tabela de dados. Deve ser personalizado para cada DAO    
    @Override
    public String[] RetornaCampos() throws SQLException {
        String [] ret = 
            {"id","nome","telefone","limiteCredito","dataultimaCompra","rg",
             "cpf","ativo","observacao","rua","bairro","cidade","estado_id"};
        return ret;
    }
// =========================================================================================
// Método responsavel por inserir ou alterar informacoes no banco de dados...    
    public SuperDTO gravar(SuperDTO cliente) throws SQLException {
        if (cliente == null){   //  verifica erros no preenchimento do DTO...
            return null;
        }
        SuperModel model = new SuperModel();
        if (Integer.parseInt(cliente.getAtrib().get("id")) == 0) {
            model.inserir(this.tabelaDAO, RetornaCampos(), cliente );
        } else {
            model.alterar(this.tabelaDAO, RetornaCampos(), cliente );
        }
        gravarRepresentantes(cliente);   //  inserção e alteração.
        return cliente;
    }
// ===========================================================================================
// Gravar representantes dos clientes.
    public void gravarRepresentantes(SuperDTO cliente) throws SQLException {
        // Primeiro devemos excluir os representantes do cliente.
        excluirRepresentanteCliente(cliente);
        
        // Agora gravar aqueles que foram inseridos ou alterados:
        List<Map<String , String>> listaRegistros  = new ArrayList<Map<String,String>>();
        
        //  campo do SuperDTO destinado a relacionamentos de N para N (RelC).
        listaRegistros = cliente.getRelC(); 
        
        int tam = listaRegistros.size();    //  lendo total de registros...
        String campos[] = new String[3];   //  total de campos da tabela.
        String valores[] = new String[3];   //  total de valores a inserir.
        
        campos[0] = "id";   //  id falso para compatibilidade com SuperModel que não grava o id por supor auto-incremento
        campos[1] = "clientes_id";
        campos[2] = "representantes_id";   //  campos da tabela
        
        SuperModel model = new SuperModel();
        SuperDTO contatos_fornecedores = new SuperDTO();            
        
        // Gravando os departamentos de cada cliente no banco...
        for (int ct=0;ct<tam;ct++){
            // Lendo departamentos do cliente atual...
            valores[0] = "0"; //  id falso para compatibilidade com SuperModel
            valores[1] = listaRegistros.get(ct).get("clientes_id");  
            valores[2] = listaRegistros.get(ct).get("representantes_id");
            
            // aqui fazemos a inclusao de cada departamento em separado...
            contatos_fornecedores.setAtrib(campos, valores);
            model.inserir("representantes_clientes", campos, contatos_fornecedores );
        }
    }        
// --------------------------------------------------------------------------------
// Método para excluir um registro com condicao - representante do cliente...
    public void excluirRepresentanteCliente(SuperDTO cliente) throws SQLException {
        SuperModel model = new SuperModel();
        model.excluirCondicional("representantes_clientes", "clientes_id="+cliente.getAtrib().get("id"));
    }
// -------------------------------------------------------------------------------------------------------    
} /// fim da classe