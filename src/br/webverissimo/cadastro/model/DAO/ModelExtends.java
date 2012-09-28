/*
 * GENERALIZAÇÃO AUXILIAR PARA DAO
  */
package br.webverissimo.cadastro.model.DAO;

import br.webverissimo.cadastro.cadastroTelas.Usuarios;
import br.webverissimo.cadastro.model.MODEL.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
/**
 * Método para exportar PDF relatório
 * @param Lista de hash
 */    
public void exportarPDF(String titulo, String arquivo, List<Map<String , String>> lista, String[] rotulos, String[] campos) throws FileNotFoundException, SQLException {
        
        if (arquivo.trim().length()==0){
            arquivo = JOptionPane.showInputDialog(null, "Informar o caminho para salvar o "+ "arquivo: ");
        }    
        if (arquivo.trim().equals("|")){
            arquivo = System.getProperty("user.home")+"/arquivo.pdf"; // caminho independente so sistema operacional
        }    
        
        Document document = new Document() {};
        try {
            //FileOutputStream fileOut = new FileOutputStream(caminho + "/Usuario.pdf");
            FileOutputStream fileOut = new FileOutputStream(arquivo);
            PdfWriter.getInstance(document, fileOut);
            document.open();

            document.addSubject("Gerando PDF em Java");
            document.add(new Paragraph(line("=",73)));
            document.add(new Paragraph(titulo));
            document.add(new Paragraph(line("=",73)));
            // document.add(new Paragraph(" "));
            
            for (int lt=0;lt<lista.size();lt++){
                String linha = "";
                int contar =0;
                for (int ct=0;ct<campos.length;ct++){
                    linha = linha + rotulos[ct] + ": " + lista.get(lt).get(campos[ct]) + " | ";
                    contar++;
                    if (contar>3 || ct==campos.length-1){
                        document.add(new Paragraph(linha));
                        contar=0;
                        linha="";
                    }
                }
                document.add(new Paragraph(line("-",128)));
            }
            
        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        document.close();
        
         File file = new File(arquivo);
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
// -----------------------------------------------------------------------------
// Este Método retorna um caracter replicado
    public String line(String t,int tam){
        String ret = "";
        for (int ct=0;ct<=tam;ct++){
            ret = ret + t;
        }
            
        return ret;
    }
// ==================================================================================    
    
}