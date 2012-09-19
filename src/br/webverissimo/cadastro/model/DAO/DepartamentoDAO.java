package br.webverissimo.cadastro.model.DAO;

import br.webverissimo.cadastro.model.DTO.DepartamentoDTO;
import br.webverissimo.cadastro.model.MODEL.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDAO extends TabelaDAO {

    /**
     * Verifica se é um departamento novo ou uma atualização
     * @param DepartamentoDTO - departamentoDTO com os atributos preenchidos
     * @return DepartamentoDTO 
     */
    public DepartamentoDTO gravar(DepartamentoDTO departamento) throws SQLException {
        Model model = new Model();
        if (departamento.getId() == 0) {
            model.inserir("departamentos", RetornaCampos(), RetornaDTO(departamento));
        } else {
            model.alterar("departamentos", RetornaCampos(), RetornaDTO(departamento));
        }
        return departamento;
    }

// -------------------------------------------------------------------------------------        
// Este Método transforma o DTO em um Objeto genérico.    
// Deve ser personalizado para cada DAO        
    public Object[] RetornaDTO(DepartamentoDTO departamento) throws SQLException {
        Object[] ret = new Object[2]; // porque so tem 2 atributos neste caso
        ret[0] = departamento.getId();
        ret[1] = departamento.getDescricao();
        return ret;
    }
// -------------------------------------------------------------------------------------        
// Este Método retorna os campos da tabela de dados.
// Deve ser personalizado para cada DAO    
    public String[] RetornaCampos() throws SQLException {
        String[] ret = new String[2];  // dois campos
        ret[0] = "id";
        ret[1] = "descricao";
        return ret;
    }

// -------------------------------------------------------------------------------------    
    /**
     * Exclui um registro selecionado
     * @param departamentoDTO
     */
    public void excluir(DepartamentoDTO departamento) throws SQLException {
        Model model = new Model();
        model.excluir("departamentos", departamento.getId());
    }

// --------------------------------------------------------------------------------
    /**
     * Transfere dados do registro para o DepartamentoDTO
     * @param ResultSet - registro atual
     * @return DepartamentoDTO - objeto departamento com os dados do registro
     *
     */
    private DepartamentoDTO registroParaDTO(ResultSet dados) throws SQLException {

        DepartamentoDTO departamento = new DepartamentoDTO(); // instanciar um objeto DepartamentoDTO 
        departamento.setId(dados.getInt("id"));
        departamento.setDescricao(dados.getString("descricao"));
        return departamento;
    }
    
// --------------------------------------------------------------------------------
    /**
     * Transfere dados do ObjetoRegistro para DTO
     * @param Object - registro atual
     * @return DepartamentoDTO - objeto departamento com os dados do registro
     *
     */
    private DepartamentoDTO ObjetoParaDTO(Object[][] registro) throws SQLException {

        DepartamentoDTO departamento = new DepartamentoDTO(); // instanciar um objeto DepartamentoDTO 
        // caso nao haja nenhum registro...
         if (registro[0][0] == null){
            return null;
        }
        departamento.setId(Integer.parseInt((registro[0][0]+"")));
        departamento.setDescricao(registro[0][1]+"");
        return departamento;
    }    
// -------------------------------------------------------------------------------------    
    /**
     * Lista todos os departamentos do banco de dados
     * @param String - nome tabela
     */
    public List list(String condicao) throws SQLException {
        List listaRegistros = new ArrayList();
        Model model = new Model();
        ResultSet lista = (ResultSet) model.list("departamentos", 0, RetornaCampos(),condicao);

        while (lista.next()) {
            listaRegistros.add(registroParaDTO(lista));
        }
        lista.close();

        return listaRegistros;
    }
// ==========================================================================================
//  seleciona um registro do banco    
    public DepartamentoDTO selecionar(int id) throws SQLException {
        Model model = new Model();
        Object[][] registro = (Object[][]) model.list("departamentos", id, RetornaCampos(),"");
        return ObjetoParaDTO(registro);
    }

}