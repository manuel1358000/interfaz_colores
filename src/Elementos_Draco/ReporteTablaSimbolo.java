/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos_Draco;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import proyecto2_compi2.Principal;

/**
 *
 * @author antonio
 */
public class ReporteTablaSimbolo {
      private ArrayList<Simbolo_S> lista;

    public ReporteTablaSimbolo() {
        lista = Elemento_C_S.reporteSimbolos;
        generarHtml();
    }

    public void generarHtml() {
        String contenido="NO.|NOMBRE|TIPO|ROL|AMBITO|VALOR\n";
        for(int i=0;i<Principal.listAreas.size();i++){
            if(Principal.listAreas.get(i).getName().equals("texto_tabla")){
                for(int o=0;o<lista.size();o++){
                    contenido+=o+"|";
                    contenido+=lista.get(o).nombre+"|"+lista.get(o).tipo+"|"+lista.get(o).rol+"|"+lista.get(o).ambito+"|";
                    if(lista.get(o).valor!=null){
                        contenido+=lista.get(0).valor+"\n";
                    }else{
                        contenido+="No Aplica \n";
                    }
                }
                Principal.listAreas.get(i).setText(contenido);
                break;
            }
        
        }
    }
}
