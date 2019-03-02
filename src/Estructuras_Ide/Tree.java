/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras_Ide;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author antonio
 */
public class Tree {
     public void generacion_arbol(Nodo raiz) {
        try {
            String contenidoDot = textoDot(raiz);
            escribir("Imagenes/arbol_cjs.dot", contenidoDot);
            Pintar_Windows(contenidoDot);
        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
        }
    }
     public void Pintar_Windows(String contenido) throws FileNotFoundException, UnsupportedEncodingException, IOException{
        PrintWriter archivo=new PrintWriter("C:\\Users\\anton\\Desktop\\U\\Primer Semestre 2018\\Compi 2\\Proyecto1_Compi2\\src\\Pintar\\arbol_cjs.dot","UTF-8");
        archivo.println(contenido);
        archivo.close();
        String dotPath="C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";
        String fileInput="Imagenes/arbol_cjs.dot";
        String fileOutput="Imagenes/ARBOL_CJS.jpg";
        String tParam="-Tjpg";
        String tOParam="-o";
        String cmd[]=new String[5];
        cmd[0]=dotPath;
        cmd[1]=tParam;
        cmd[2]=fileInput;
        cmd[3]=tOParam;
        cmd[4]=fileOutput;
        Runtime rt=Runtime.getRuntime();
        rt.exec(cmd);
        File file=new File(fileOutput);
        Desktop desk=Desktop.getDesktop();
        desk.open(file);
    }
    public void escribir(String direccion, String texto) {
        //metodo que guarda lo que esta escrito en un archivo de texto
        try {
            FileWriter writer = new FileWriter(direccion);
            PrintWriter print = new PrintWriter(writer);
            print.print(texto);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String textoDot(Nodo raiz) {
        if (raiz == null) {
            return null;
        }
        StringBuilder str = new StringBuilder();
        str.append("digraph G {" + "\n");
        str.append("node[shape=Mrecord]" + "\n");
        str.append(raiz.index + "[label=\"" + raiz.nombre + " | " + raiz.valor + "\",style=bold]" + "\n");

        recorridoAST(raiz, str);
        str.append("\n}");
        return str.toString();
    }

    public void recorridoAST(Nodo nodo, StringBuilder str) {
        if (nodo == null) {
            return;
        }

        for (int x = 0; x < nodo.hijos.size(); x++) {
            str.append(nodo.hijos.get(x).index + "[label=\"" + nodo.hijos.get(x).nombre + " | " + nodo.hijos.get(x).valor + "\",style=bold]" + "\n");
            str.append("{" + nodo.index + "}->{" + nodo.hijos.get(x).index + "}\n");
            recorridoAST(nodo.hijos.get(x), str);
        }
    }

}
