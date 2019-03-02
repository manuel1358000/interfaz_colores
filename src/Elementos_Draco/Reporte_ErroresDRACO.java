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
public class Reporte_ErroresDRACO {
    private ArrayList<Draco_Elemento_Error> listaErrores;

    public Reporte_ErroresDRACO() {
        listaErrores = new ArrayList<>();
    }

    public void agregar(String tipo, int linea, int columna, String descripcion) {
        String archivo = "";
        if (Elemento_C_S.claseActual != null) {
            archivo = Elemento_C_S.claseActual.archivo;
        } else {
            archivo = "archivoActual";
        }
        Draco_Elemento_Error error = new Draco_Elemento_Error(tipo, linea, columna, archivo, descripcion);
        String strError = tipo + "  linea:" + linea + "  columna:" + columna + " archivo:" + archivo + "   " + descripcion;
        System.err.println(strError);
        listaErrores.add(error);
    }

    public void agregar(String tipo, int linea, int columna, String descripcion, String archivo) {

        Draco_Elemento_Error error = new Draco_Elemento_Error(tipo, linea, columna, archivo, descripcion);
        String strError = tipo + "  linea:" + linea + "  columna:" + columna + " archivo:" + archivo + "   " + descripcion;
        System.err.println(strError);
        listaErrores.add(error);
    }

    public void generarHtml(String titulo, String lenguaje) {
        for(int i=0;i<Principal.listAreas.size();i++){
            if(Principal.listAreas.get(i).getName().equals("texto_errores")){
                String contenido="";
                for(int o=0;0<listaErrores.size();o++){
                    contenido+="cabeceras";
                }
                Principal.listAreas.get(i).setText(contenido);
                break;
            }
        
        }
        
    }
}
