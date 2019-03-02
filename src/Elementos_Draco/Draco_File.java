/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos_Draco;

import Estructuras_Ide.Nodo;
import java.util.ArrayList;

/**
 *
 * @author antonio
 */
public class Draco_File {
    public String nombre;
    Nodo raiz;
    public ArrayList<Draco_Clas> clases;

    public Draco_File(String nombre, Nodo raiz) {
        clases = new ArrayList();
        this.nombre = nombre;
        this.raiz = raiz;
        
        Draco_Clas clase = new Draco_Clas(raiz);
        clase.archivo = nombre;
        clases.add(clase);
    }
    
    
    
}
