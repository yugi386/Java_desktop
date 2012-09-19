/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.webverissimo.cadastro.model.DAO;

import br.webverissimo.cadastro.model.DTO.CargoDTO;
import br.webverissimo.cadastro.model.MODEL.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsavél por gerenciar as alteração na tabela cargos
 * @author mario
 */
public class CargoDAO extends TabelaDAO {

    /**
     * Verifica se é um cargo novo ou uma atualização
     * @param CargoDTO - cargoDTO com os atributos preenchidos
     * @return CargoDTO 
     */
    public CargoDTO gravar(CargoDTO cargo) throws SQLException {
        Model model = new Model();
        if (cargo.getId() == 0) {
            model.inserir("cargos", RetornaCampos(), RetornaDTO(cargo));
        } else {
            model.alterar("cargos", RetornaCampos(), RetornaDTO(cargo));
        }
        return cargo;
    }

// -------------------------------------------------------------------------------------        
// Este Método transforma o DTO em um Objeto genérico.    
// Deve ser personalizado para cada DAO        
    public Object[] RetornaDTO(CargoDTO cargo) throws SQLException {
        Object[] ret = new Object[2]; // porque so tem 2 atributos neste caso
        ret[0] = cargo.getId();
        ret[1] = cargo.getDescricao();
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
     * @param cargoDTO
     */
    public void excluir(CargoDTO cargo) throws SQLException {
        Model model = new Model();
        model.excluir("cargos", cargo.getId());
    }

// --------------------------------------------------------------------------------
    /**
     * Transfere dados do registro para o CargoDTO
     * @param ResultSet - registro atual
     * @return CargoDTO - objeto cargo com os dados do registro
     *
     */
    private CargoDTO registroParaDTO(ResultSet dados) throws SQLException {

        CargoDTO cargo = new CargoDTO(); // instanciar um objeto CargoDTO 
        cargo.setId(dados.getInt("id"));
        cargo.setDescricao(dados.getString("descricao"));
        return cargo;
    }
    
// --------------------------------------------------------------------------------
    /**
     * Transfere dados do ObjetoRegistro para DTO
     * @param Object - registro atual
     * @return CargoDTO - objeto cargo com os dados do registro
     *
     */
    private CargoDTO ObjetoParaDTO(Object[][] registro) throws SQLException {

        CargoDTO cargo = new CargoDTO(); // instanciar um objeto CargoDTO 
        if (registro[0][0] == null){
            return null;
        }
        
        cargo.setId(Integer.parseInt((registro[0][0]+"")));
        cargo.setDescricao(registro[0][1]+"");
        return cargo;
    }    
// -------------------------------------------------------------------------------------    
    /**
     * Lista todos os cargos do banco de dados
     * @param String - nome tabela
     */
    public List list(String condicao) throws SQLException {
        List listaRegistros = new ArrayList();
        Model model = new Model();
        ResultSet lista = (ResultSet) model.list("cargos", 0, RetornaCampos(),condicao);

        while (lista.next()) {
            listaRegistros.add(registroParaDTO(lista));
        }
        lista.close();

        return listaRegistros;
    }
// ==========================================================================================
//  seleciona um registro do banco    
    public CargoDTO selecionar(int id) throws SQLException {
        Model model = new Model();
        Object[][] registro = (Object[][]) model.list("cargos", id, RetornaCampos(),"");
        return ObjetoParaDTO(registro);
    }

}