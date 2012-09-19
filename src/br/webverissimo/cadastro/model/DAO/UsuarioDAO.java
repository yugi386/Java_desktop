// ===============================================================
// Acesso a banco para tabela Usuario e tabelas relacionadas...
// ===============================================================
package br.webverissimo.cadastro.model.DAO;

import br.webverissimo.cadastro.model.MODEL.SuperDTO;
import br.webverissimo.cadastro.model.MODEL.SuperModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UsuarioDAO extends ModelExtends {

      private String tabelaDAO = "usuarios";  // Tabela Base de Dados.

     public UsuarioDAO(){ //  Método construtor...
        setTabelaDAO(this.tabelaDAO); // inicia ModelExtends...
    } 
// -----------------------------------------------------------------------------
// Este Método retorna os campos da tabela de dados. Deve ser personalizado para cada DAO    
    public String[] RetornaCampos() throws SQLException {
        String [] ret = 
            {"id","nome","login","senha","dataNascimento","sexo",
             "salario","email","observacao","ativo","cargo_id"};
        return ret;
    }
// ==============================================================================    
// Método responsavel por inserir ou alterar informacoes no banco de dados...    
    public SuperDTO gravar(SuperDTO usuario) throws SQLException {
        if (usuario == null){   //  verifica erros no preenchimento do DTO...
            return null;
        }
        SuperModel model = new SuperModel();
        if (Integer.parseInt(usuario.getAtrib().get("id")) == 0) {
            model.inserir(this.tabelaDAO, RetornaCampos(), usuario );
        } else {
            model.alterar(this.tabelaDAO, RetornaCampos(), usuario );
        }
        gravarDepartamentos(usuario);   //  inserção e alteração.
        return usuario;
    }    
// =============================================================================
// Gravar departamentos do  usuario
    public void gravarDepartamentos(SuperDTO usuario) throws SQLException {
        // Primeiro devemos excluir os departamentos existentes deste usuario:
        excluirDepartamentoUsuario(usuario);
        
        // Agora gravar aqueles que foram inseridos ou alterados:
        List<Map<String , String>> listaRegistros  = new ArrayList<Map<String,String>>();
        
        //  campo do SuperDTO destinado a relacionamentos de N para N (RelC).
        listaRegistros = usuario.getRelC(); 
        
        int tam = listaRegistros.size();    //  lendo total de registros...
        String campos[] = new String[3];   //  total de campos da tabela.
        String valores[] = new String[3];   //  total de valores a inserir.
        
        campos[0] = "id";   //  id falso para compatibilidade com SuperModel que não grava o id por supor auto-incremento
        campos[1] = "usuarios_id";
        campos[2] = "departamentos_id";   //  campos da tabela
        
        SuperModel model = new SuperModel();
        SuperDTO departamentos_usuarios = new SuperDTO();            
        
        // Gravando os departamentos de cada usuario no banco...
        for (int ct=0;ct<tam;ct++){
            // Lendo departamentos do usuario atual...
            valores[0] = "0"; //  id falso para compatibilidade com SuperModel
            valores[1] = listaRegistros.get(ct).get("usuarios_id");  
            valores[2] = listaRegistros.get(ct).get("departamentos_id");
            
            // aqui fazemos a inclusao de cada departamento em separado...
            departamentos_usuarios.setAtrib(campos, valores);
            model.inserir("departamentos_usuarios", campos, departamentos_usuarios );
        }
    }        
// --------------------------------------------------------------------------------
// Método para excluir um registro com condicao - departamentos do usuario...
    public void excluirDepartamentoUsuario(SuperDTO usuario) throws SQLException {
        SuperModel model = new SuperModel();
        model.excluirCondicional("departamentos_usuarios", "usuarios_id="+usuario.getAtrib().get("id"));
    }
// -------------------------------------------------------------------------------------------------------    
} /// fim da classe