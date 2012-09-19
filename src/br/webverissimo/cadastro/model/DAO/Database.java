/*
 * Database.java
 *
 * Created on 14 de Agosto de 2007, 21:17
 *
 *  Classe com o Design Pattern Singleton para conex�o com o banco de dados
 *
 */

package br.webverissimo.cadastro.model.DAO;

//import br.faetec.aplicacaobasica.model.dao.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author pedro.quina
 */
public class Database {
    
    private String url;
    private String usuario;
    private String senha;
    private String driver;
    private static Connection conexao;
    
    /** Creates a new instance of Database */
    public Database() throws SQLException {
        
        inicializa("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/provajava","root","");
    }
    
    public Database(String driver, String url, String usuario, String senha) throws SQLException, ClassNotFoundException {
        inicializa( driver, url, usuario, senha);
    }
    
    
    private void inicializa(String driver, String url, String usuario, String senha) throws SQLException{
        setUsuario( usuario);
        setSenha(senha);
        setUrl(url);
        setDriver(driver);
        try {
            // carrega o driver do banco de dados
            Class.forName(getDriver());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        abreConexao();
    }
    
    public Connection getInstance() throws SQLException {
        abreConexao();
        return getConexao();
    }
    
    public void abreConexao() throws SQLException{
            if ( getConexao() == null ){
                setConexao( DriverManager.getConnection(getUrl(), getUsuario(), getSenha()) );
            }
        
    }
    
    public void fechaConexao() throws SQLException{
        getConexao().close();
        setConexao(null);
    }
    
    protected void finalize() throws SQLException{
        fechaConexao();
    }
    
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getUsuario() {
        return usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String getDriver() {
        return driver;
    }
    
    public void setDriver(String driver) {
        this.driver = driver;
    }
    
    private Connection getConexao() {
        return conexao;
    }
    
    private void setConexao(Connection conexao) {
        this.conexao = conexao;
    }
    
    
}
