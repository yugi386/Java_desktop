/*
 * CLASSE PARA VALIDAR CAMPOS DO FORMULÁRIO EM FORMATO STRING...
 */
package br.webverissimo.cadastro.model.MODEL;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validar {

// ----------------------------------------------------------------------------
//  Método para verificar se um campo está vazio:    
// parametros:  campo = campo a ser validado    
//              erro = mensagem de erro a ser retornada se o teste falhar.
    public static String ValidarVazio(String campo, String erro ){
        if (campo.trim().length()!=0){
            erro = null;
        }
        return erro;
    }
// ----------------------------------------------------------------------------
// Método para verificar tamanho mínimo e máximo de uma string
// parametros:  campo = campo a ser validado    
//              min = tamanho minimo
//              max = tamanho maximo    
//              erro = mensagem de erro a ser retornada se o teste falhar.
    public static String ValidarTamanho(String campo, int min, int max, String erro ){
        if (min != 0 && max != 0){
            if (campo.trim().length()>= min && campo.trim().length()<= max){
                erro = null;
            }
        } else if (min == 0) {
            if (campo.trim().length()<= max){
                erro = null;
            }
        } else if (max == 0){
            if (campo.trim().length()>= min){
                erro = null;
            }
        }
        return erro;
    }

// ----------------------------------------------------------------------------
// Método para verificar se dois campos string são iguais...
// parametros:  campo1 = 1º campo a ser comparado.
//              campo2 = 2º campo a ser comparado.
//              erro = mensagem de erro a ser retornada se o teste falhar.
    public static String ValidarComparar(String campo1, String campo2, String erro ){
        if (campo1.trim().equals(campo2.trim())){
            erro = null;
        }
        return erro;
    }

// ----------------------------------------------------------------------------
// Método para verificar se o campo é numérico
// parametros:  campo = campo a ser validado
//              erro = mensagem de erro a ser retornada se o teste falhar.
    public static String ValidarNumero(String campo, String erro ){
         try {
            Double.parseDouble(campo);
            erro = null;
        } catch(Exception e) {
            return erro;
        }
        return erro;
    }
    
// ----------------------------------------------------------------------------
// Método para verificar se o campo é numérico e inteiro
// parametros:  campo = campo a ser validado
//              erro = mensagem de erro a ser retornada se o teste falhar.
    public static String ValidarNumeroInteiro(String campo, String erro ){
         try {
            Integer.parseInt(campo);
            erro = null;
        } catch(Exception e) {
            return erro;
        }
        return erro;
    }
    
// ----------------------------------------------------------------------------
// Método para verificar se o campo é numérico e está dentro de um limite
// parametros:  campo = campo a ser validado
//              min = tamanho minimo
//              max = tamanho maximo    
//              erro = mensagem de erro a ser retornada se o teste falhar.
    public static String ValidarNumero(String campo,int min, int max, String erro ){
        
        erro = ValidarNumero(campo,erro);
        if (erro == null) {
            // Verificando se numero está dentro da faixa...
            Double num = Double.parseDouble(campo);
            if (num >= min && num <= max){
                erro = null;
            }
        } else {    //  não é numero
            return erro;
        }
        
        return erro;
    }

// ----------------------------------------------------------------------------
// Método para verificar se o campo é numérico inteiro e está dentro de um limite
// parametros:  campo = campo a ser validado
//              min = tamanho minimo
//              max = tamanho maximo    
//              erro = mensagem de erro a ser retornada se o teste falhar.
    public static String ValidarNumeroInteiro(String campo,int min, int max, String erro ){
        
        erro = ValidarNumeroInteiro(campo,erro);
        if (erro == null) {
            // Verificando se numero está dentro da faixa...
            int num = Integer.parseInt(campo);
                if (num >= min && num <= max){
                    erro = null;
                }
        } else {    //  não é numero
            return erro;
        }
        
        return erro;
    }    
// ----------------------------------------------------------------------------
// Método para verificar se o email é valido...
// parametros:  campo = campo a ser validado
//              erro = mensagem de erro a ser retornada se o teste falhar.
    public static String ValidarEmail(String campo, String erro ){
        if (campo.trim().length()==0){
            return null;    //  se o email estiver vazio nao retorna erro
        }
        
        Pattern pattern = Pattern.compile("[a-z_][a-z0-9._]+@.+\\.[a-z]+");
        Matcher matcher = pattern.matcher(campo);
        if (matcher.matches() == false) {
            return erro;
        } else {
            erro = null;
        }
            
        return erro;
    }    
// ----------------------------------------------------------------------------
// Método para verificar se uma data é valida... string dia/mes/ano
// parametros:  campo = campo a ser validado
//              erro = mensagem de erro a ser retornada se o teste falhar.
     public static String ValidarData(String campo, String erro ){
        campo = campo.trim();
        if (campo.length()!=10){ // nao tem 10 caracteres então nao é data   
            return erro;
        }
        
        // converte o campo para data banco...
        campo = DataBanco(campo);
        String dia = campo.substring(8, 10);
        String mes = campo.substring(5, 7);
        String ano = campo.substring(0, 4);
        
        String num = ano + mes + dia;
        if (ValidarNumeroInteiro(num,"Erro") != null){;
            return erro;
        }
        
        int kAno = Integer.parseInt(ano);
        int kMes = Integer.parseInt(mes);
        int kDia = Integer.parseInt(dia);
        
        if (kAno < 1 || kAno > 2999){   //  verifica ano inválido
            return erro;
        }
        
        if (kMes < 1 || kMes > 12){   //  verifica mes inválido
            return erro;
        }
        
        // Verificando ano bissexto:
        boolean bissexto = false;
        if (kAno % 4 == 0) {
                if ((kAno % 100 == 0) && (kAno % 400 == 0)) {
                        bissexto = true;
                } 
                if (kAno % 100 != 0) {
                        bissexto = true;			
                }
        }

         // verificando quantos dias tem o mes:
        int maximoDia = 31;
        if (kMes == 1 || kMes == 3 || kMes == 5  || kMes == 7  || kMes == 8  || kMes == 10  || kMes == 12) {
                maximoDia = 31;
        } else if (kMes == 2) {
                if (bissexto == true) {
                       maximoDia = 29;
                } else {
                       maximoDia = 28;
                }
        } else {
                maximoDia = 30;
        }        
        
        if (kDia < 1 || kDia > maximoDia) {
            return erro;
        } else {
            return null;
        }
        
        /*
        Date date;
        try {
            DateFormat formatter;
            formatter = new SimpleDateFormat("dd/MM/yyyy");
            date = (Date) formatter.parse(campo);
            return null;
        } catch (ParseException e) {
            return erro;
        }
        */
    }    

// -----------------------------------------------------------------------
// RETORNA A DATA ATUAL EM FORMATO STRING AA/MM/DD
// para que seja possível gravação no banco de dados...     
    public static String DataBanco(String data) {
        String s = data;
        String dia = s.substring(0, 2);
        String mes = s.substring(3, 5);
        String ano = s.substring(6, 10);
        String ret = ano + "-" + mes + "-" + dia;
        return ret;
    }     
    
// -----------------------------------------------------------------------
// RETORNA A DATA ATUAL EM FORMATO STRING DD/MM/AAAA
// le o formato ano/mes/dia do banco e converte
    public static String DataFormulario(String data) {
        String s = data;
        String dia = s.substring(8, 10);
        String mes = s.substring(5, 7);
        String ano = s.substring(0, 4);

        String ret = dia + "/" + mes + "/" + ano;
        return ret;
    }         
// ============================================================================    
} // fim da classe
