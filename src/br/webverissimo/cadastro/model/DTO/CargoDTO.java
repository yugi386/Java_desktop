/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.webverissimo.cadastro.model.DTO;

/**
 * Classe responsavel pelos atributos do cargo
 * @author mario
 */
public class CargoDTO {

    private int id;
    private String descricao;

    
   /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

   public String toString() {
        return this.getDescricao();
    }
   
}
