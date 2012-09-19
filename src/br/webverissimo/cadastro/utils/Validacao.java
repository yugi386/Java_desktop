package br.webverissimo.cadastro.utils;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   Format.java

import java.math.BigDecimal;
import java.text.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Validacao {
    /**
     * Converte uma data em string
     * @param Date - data
     * @param String - formto para conversão da data
     * @return String - string com a data
     */
    public static String convertDate(Date date, String format) {
        if(date != null) {
            SimpleDateFormat sdf = format != null ? new SimpleDateFormat(format) : new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            return sdf.format(date);
        } else {
            return "";
        }
    }

    /**
     * Converte um string em uma data
     * @param String - string com uma data válida
     * @param String - formato da data na string
     * @return Date - data
     */
    public static Date convertDate(String date, String format)
    throws ParseException {
        if(date != null) {
            SimpleDateFormat sdf = format != null ? new SimpleDateFormat(format) : new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            return sdf.parse(date);
        } else {
            return null;
        }
    }

    /**
     * Converte um número real em uma string formatada
     * @param double - número
     * @param String - formato ( máscara )
     * @return String - string com o número no formato desejado
     */
    public static String convertNumber(double number, String format) {
        return convertNumber(number, format, new Locale("PT", "BR"));
    }

    /**
     * Converte um número real em uma string formatada de acordo com a localidade
     * @param double - número
     * @param String - formato ( máscara )
     * @param Locale - localização
     * @return String - string com o número no formato desejado
     */
    public static String convertNumber(double number, String format, Locale locale) {
        DecimalFormat df = format != null ? new DecimalFormat(format) : new DecimalFormat("0.00");
        df.setDecimalFormatSymbols(new DecimalFormatSymbols(locale));
        return df.format(number);
    }

    /**
     * Converte uma string numérica em um número
     * @param String - string com um número válido
     * @param String - formato ( máscara )
     * @return double - número convertido
     */
    public static double convertNumber(String number, String format)
    throws ParseException {
        return convertNumber(number, format, new Locale("PT", "BR"));
    }

    /**
     * Converte uma string numérica em um número
     * @param String - string com um número válido
     * @param String - formato ( máscara )
     * @param Locale - localização
     * @return double - número convertido
     */
    public static double convertNumber(String number, String format, Locale locale)
    throws ParseException {
        DecimalFormat df = format != null ? new DecimalFormat(format) : new DecimalFormat("0.00");
        df.setDecimalFormatSymbols(new DecimalFormatSymbols(locale));
        number = convertNumber(df.parse(number).doubleValue(), format);
        return df.parse(number).doubleValue();
    }

    /**
     * Converte uma string numérica em um objeto da classe BigDecimal
     * @param String - string com um número válido
     * @return BigDecimal - objeto
     */
    public static BigDecimal convertNumber(String valorBr) {
        valorBr = valorBr.replace(".", "").replace(",", ".");
        return new BigDecimal(valorBr);
    }

    /**
     * Converte um objeto da classe BigDecimal em uma String
     * @param BigDecimal - valor a ser convertido
     * @return String - valor convertido
     */
    public static String convertNumber(BigDecimal valorUs) {
        DecimalFormat decimalFormat = new DecimalFormat();
        return decimalFormat.format(valorUs);
    }

    /**
     * Verifica se uma string é um número válido
     * @param String - número a ser validado
     * @return boolean - true, false caso seja um número válido
     */
    public static boolean isNaN(String str) {
        try {
            convertNumber(str);
        } catch(Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Verifica se uma string é um inteiro válido
     * @param String - número inteiro a ser validado
     * @return boolean - true, false caso seja um inteiro válido
     */
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
        } catch(Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Converte uma lista de string em um Array de string
     * @param List - lista de strings
     * @return String[] - array
     */
    public static String[] listToArray(List lista){
        String[] array= new String[lista.size()];
        for(int index=0; index < lista.size(); index++)
            array[index]= (String)lista.get(index);
        return array;
    }

    /**
     * Converte um Array de objetos em um List
     * @param Object[] - array
     * @return List - objeto List com os elementos do array
     */
    public static List arrayToList(Object[] array){
        List lista = null;
        if ( array != null ){
            lista = new ArrayList();
            for(int index=0; index < array.length; index++)
                lista.add( array[index] );
        }
        return lista;
    }

    /**
     * Coloca String entre aspas
     * @param String - string a ser colocada entre aspas
     * @param String - string delimitadora, default = '
     * @return String - string com o caracter delimitador
     */
    public static String delimiter(String str, String chr){
        return  chr+str+chr;
    }
    public static String delimiter(String str){
        return  delimiter(str,"'");
    }

    /**
     * Verifica se a String contém null ou ""
     * @param String - string a ser validada
     * @return boolean - true, ou false caso a string contenha dados
     */
    public static boolean isEmpty(String dado){
        return (dado.equals(null) || dado.equals("") )?true:false;
    }

    public static String lPad(String str, int tamanho, String preenchimento){
        tamanho = Math.abs(tamanho-str.length());
        for(int index=0; index < tamanho; index ++){
            str = preenchimento + str;
        }
        return str;
    }

    public static String rPad(String str, int tamanho, String preenchimento){
        tamanho = Math.abs(tamanho-str.length());
        for(int index=0; index < tamanho; index ++){
            str = str + preenchimento;
        }
        return str;
    }

    public static String rSubstring(String str, int tamanho){
        return str.substring( str.length()-tamanho );
    }

    public static String trimAll(String str){
        String local="";
        for( int index=0; index < str.length()-1; index++){
            if ( ! String.valueOf(str.charAt(index)).equals(" ")){
                local = local + String.valueOf(str.charAt(index));
            }
        }
        return local;
    }

    public static void main(String[] a){
        System.out.println( lPad("1000", 3, "0"));
        System.out.println( lPad("100", 3, "0"));
        System.out.println( lPad("10", 3, "0"));
        System.out.println( lPad("1", 3, "0"));

        System.out.println( rSubstring("1234", 2));
        System.out.println( rSubstring("123", 2));
        System.out.println( rSubstring("12", 2));
        System.out.println( rSubstring("1", 2)); // erro nesta linha
    }
}