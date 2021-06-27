package com.afrd.limar.Helper;

import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Pattern;

public class ExpressoesRegulares {

    public static boolean confereTelefone(String telefone){
        String regex1 = "\\(\\d{2}\\)\\d{5}-\\d{4}";
        String regex2 = "\\(\\d{2}\\)\\d{4}-\\d{4}";
        if(Pattern.matches(regex1, telefone  ) || Pattern.matches(regex2, telefone) ){
            return true;
        }else{
            return false;
        }
    }

    public static boolean confereCnpj(String cnpj){
        String regex = "\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}";
        if(Pattern.matches(regex, cnpj  )){
            return true;
        }else{
            return false;
        }
    }

    public static boolean confereIE(String inscricao){
        String regex = "\\d{2}/\\d{3}.\\d{3}-\\d{1}";
        if(Pattern.matches(regex, inscricao  )){
            return true;
        }else{
            return false;
        }
    }

    public static boolean confereCpf(String inscricao){
        String regex = "\\d{3}.\\d{3}.\\d{3}-\\d{2}";
        if(Pattern.matches(regex, inscricao  )){
            return true;
        }else{
            return false;
        }
    }

    public static boolean confereDataNascimento(String data){
        String[] partes = data.split("/");
        Calendar cal = Calendar.getInstance();
        try{
            int anoAtual = cal.get(Calendar.YEAR);
            int dia = Integer.parseInt(partes[0]);
            int mes = Integer.parseInt(partes[1]);
            int ano = Integer.parseInt(partes[2]);
            if(dia <= 0 || dia > 31){
                return false;
            }else if(mes <=0 || mes > 12){
                return  false;
            }else if(ano < 1500 || ano > anoAtual){
                return false;
            }else{
                return true;
            }
        }catch (Exception ex){
         return false;
        }


    }

    public static boolean validaDataAtendimento(String data){
        String[] partes = data.split("/");
        Calendar cal = Calendar.getInstance();
        try{
            int anoAtual = cal.get(Calendar.YEAR);
            int dia = Integer.parseInt(partes[0]);
            int mes = Integer.parseInt(partes[1]);
            int ano = Integer.parseInt(partes[2]);
            if(dia <= 0 || dia > 31){
                return false;
            }else if(mes <=0 || mes > 12){
                return  false;
            }else if(ano >= anoAtual){
                return false;
            }else{
                return true;
            }
        }catch (Exception ex){
            return false;
        }


    }




}
