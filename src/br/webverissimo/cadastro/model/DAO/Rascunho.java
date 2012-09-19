/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.webverissimo.cadastro.model.DAO;

/**
 *
 * @author livia
 */
public class Rascunho {
    
}

// FORNECEDORES....
// =========================================================================================
/*    
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
// --------------------------------------------------------------------------------
// Método para excluir um registro pelo Id.
    public void excluir(SuperDTO fornecedor) throws SQLException {
        SuperModel model = new SuperModel();
        model.excluir(this.tabelaDAO, Integer.parseInt(fornecedor.getAtrib().get("id")));
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
// Listar Tabelas Associadas ao fornecedor: estado, contatos_fornecedores.
    public List listarTabelasAssociadas(String tabela, String Campos[],String condicao) throws SQLException {
        SuperModel model = new SuperModel();
        ResultSet lista = model.list(tabela,Campos,condicao);
        return model.DevolveLista(Campos,lista);
    }        
    */ 


/* USUARIOS
// =========================================================================================
// Método responsavel por inserir ou alterar informacoes no banco de dados...    
    public SuperDTO gravar(SuperDTO usuario) throws SQLException {
        if (usuario == null){   //  verifica erros no preenchimento do DTO...
            return null;
        }
        SuperModel model = new SuperModel();
        if (Integer.parseInt(usuario.getAtrib().get("id")) == 0) {
            model.inserir("usuarios", RetornaCampos(), usuario );
        } else {
            model.alterar("usuarios", RetornaCampos(), usuario );
        }
        gravarDepartamentos(usuario);   //  inserção e alteração.
        return usuario;
    }
// --------------------------------------------------------------------------------
// Método para excluir um registro pelo Id.
    public void excluir(SuperDTO usuario) throws SQLException {
        SuperModel model = new SuperModel();
        model.excluir("usuarios", Integer.parseInt(usuario.getAtrib().get("id")));
    }

// =========================================================================================    
// Lista todos os usuarios do banco de dados; permite especificar uma condicao...
    public List listar(String condicao) throws SQLException {
        SuperModel model = new SuperModel();
        String Campos[] = RetornaCampos();
        ResultSet lista = model.list("usuarios",RetornaCampos(),condicao);
        return model.DevolveLista(Campos,lista);
    }
// =========================================================================================    
// Listar Tabelas Associadas ao usuario: cargo, departamento, departamentos_usuarios.
    public List listarTabelasAssociadas(String tabela, String Campos[],String condicao) throws SQLException {
        SuperModel model = new SuperModel();
        ResultSet lista = model.list(tabela,Campos,condicao);
        return model.DevolveLista(Campos,lista);
    }        
// =========================================================================================    
* 
* 
* 
* 
* 
* CLIENTES:
// --------------------------------------------------------------------------------
// Método para excluir um registro pelo Id.
    public void excluir(SuperDTO cliente) throws SQLException {
        SuperModel model = new SuperModel();
        model.excluir("clientes", Integer.parseInt(cliente.getAtrib().get("id")));
    }

// =========================================================================================    
// Lista todos os clientes do banco de dados; permite especificar uma condicao...
    public List listar(String condicao) throws SQLException {
        SuperModel model = new SuperModel();
        String Campos[] = RetornaCampos();
        ResultSet lista = model.list("clientes",RetornaCampos(),condicao);
        return model.DevolveLista(Campos,lista);
    }
// =========================================================================================    
// Listar Tabelas Associadas ao cliente: estado, representantes_clientes
    public List listarTabelasAssociadas(String tabela, String Campos[],String condicao) throws SQLException {
        SuperModel model = new SuperModel();
        ResultSet lista = model.list(tabela,Campos,condicao);
        return model.DevolveLista(Campos,lista);
    }        
// =========================================================================================    * 
*/ 