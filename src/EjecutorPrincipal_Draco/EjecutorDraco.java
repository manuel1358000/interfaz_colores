/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjecutorPrincipal_Draco;

import Estructuras_Ide.Tree;
import Estructuras_Ide.Nodo;
import Elementos_Draco.Draco_File;
import Elementos_Draco.Draco_Clas;
import Elementos_Draco.Elemento_C_S;
import Elementos_Draco.Draco_M_S;
import Elementos_Ejecucion_Draco.lexico_Dra;
import Elementos_Ejecucion_Draco.sintactico_Draco;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.text.BadLocationException;
import proyecto2_compi2.Principal;

/**
 *
 * @author antonio
 */
public class EjecutorDraco extends Elemento_C_S{
    
    public EjecutorDraco(File file, String archivoActual) throws IOException, BadLocationException {
        Nodo RAIX =null;
        archivos = new ArrayList();
        reporteSimbolos = new ArrayList();
        this.elementoActual = archivoActual;
        
        String nombre = file.getName();  
        String tipo = nombre.substring(nombre.length() - 3, nombre.length());
        if (tipo.equalsIgnoreCase("djs")) {
            String cadena = obtenerTextoArchivo(file);
            try {
                sintactico_Draco sin = new sintactico_Draco(new lexico_Dra(new BufferedReader(new StringReader(cadena))));
                sin.parse();
                Nodo raiz = sin.getRoot();
                if (raiz != null) {
                    RAIX = raiz;
                    raiz.valor = nombre;
                    Draco_File archivo = new Draco_File(nombre, raiz);
                    archivos.add(archivo);
                }
            } catch (Exception ex) {
                System.err.println(ex.toString());
            }
        }

        pilaTablas = new Stack<>();
        pilaNivelCiclo = new Stack<>();
        pilaClases = new Stack<>();
        pilaMetodos = new Stack<>();
        claseActual = getClasePrincipal();
        if (claseActual == null) {
            Principal.reporteError.agregar("Error Semantico", 0, 0, "El inicio no esta declarado");
            return;
        }
        tabla = claseActual.tabla;
        global = claseActual.global;
        
        if (claseActual != null) {
            new Tree().generacion_arbol(RAIX);
            metodoActual = getInicio(RAIX);
            pilaTablas = claseActual.pilaTablas;
            global = claseActual.global;
            tabla = claseActual.tabla;
            ejecutarSentencias(metodoActual.sentencias);
            System.out.println("");
        }
        
    }

    public void imprimirArbol(Nodo raiz)
    {
        System.out.println("Padre:"+raiz.nombre);
        for (Nodo hijo : raiz.hijos) {
            System.out.println("\thijo:"+hijo.nombre);
        }
        
        for (Nodo hijo : raiz.hijos) {
            imprimirArbol(hijo);
        }
    }

    private Draco_Clas getClasePrincipal() {
        ArrayList<Draco_Clas> clases;
        Draco_File archivo = getArchivoPrincipal();
        if (archivo == null) {
            return null;
        }
        clases = archivo.clases;
        if(clases.size()>0)
        {
            return clases.get(0);
        }
        return null;
    }

    private Draco_File getArchivoPrincipal() {
        if(archivos.size()>0)
        {
            return archivos.get(0);
        }else
        {
            return null;
        }
    }

    private Draco_M_S getInicio(Nodo raiz) {
        Draco_M_S metodo = new Draco_M_S(raiz);
        claseActual.metodos.add(metodo);
        return metodo;
    }
    
    
    String obtenerTextoArchivo(File file) {
        String texto = "";
        try {
            BufferedReader bufer = new BufferedReader(
                    new InputStreamReader(new FileInputStream((String) file.getAbsolutePath())));
            String temp = "";
            while (temp != null) {
                temp = bufer.readLine();
                if (temp != null) {
                    texto = texto + temp + "\n";
                    temp = "";
                } else {
                }
            }
            bufer.close();
        } catch (Exception e) {
        }
        return texto;
    }
}
