/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos_Draco;

import Estructuras_Ide.Nodo;
import Elementos_Ejecucion_Draco.Declaracion;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author antonio
 */
public class Draco_Clas {
      public Stack<Reporte_Tabla_Simbolos> pilaTablas;

    //--------------------------------------
    public String archivo;
    public Reporte_Tabla_Simbolos global;
    public Reporte_Tabla_Simbolos tabla;
    public String nombre;
    public String visibilidad;
    public Draco_Clas herencia;
    public String nombreHereda;
    public ArrayList<Draco_M_S> metodos;
    public ArrayList<Nodo> atributos;
    
    
    public Draco_Clas() {
        global = new Reporte_Tabla_Simbolos();
        tabla = new Reporte_Tabla_Simbolos();
        atributos = new ArrayList<>();
        metodos = new ArrayList<>();
        pilaTablas = new Stack<>();
    }
    
    public Draco_Clas(Nodo raiz) {

        global = new Reporte_Tabla_Simbolos();
        tabla = new Reporte_Tabla_Simbolos();
        atributos = new ArrayList<>();
        metodos = new ArrayList<>();
        pilaTablas = new Stack<>();
        
        this.nombre = raiz.valor;
    }
    

    public void ejecutar() {
        Elemento_C_S.claseActual = this;
        for (Nodo atributo : atributos) {
            /*atributo.nombre = "primitivaG";
            new Declaracion(atributo, global, tabla);*/
        }
    }

    
}
