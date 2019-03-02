/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos_Ejecucion_Draco;

import Estructuras_Ide.Nodo;
import Estructuras_Ide.Resultado;
import Elementos_Draco.Draco_Cast;
import Elementos_Draco.Elemento_C_S;
import Elementos_Draco.Resultado_S;
import Elementos_Draco.Simbolo_S;
import Elementos_Draco.Reporte_Tabla_Simbolos;
import proyecto2_compi2.Principal;

/**
 *
 * @author antonio
 */
public class Declaracion extends Elemento_C_S{
    Reporte_Tabla_Simbolos tabla;
    Reporte_Tabla_Simbolos global;
    
    public Declaracion(Nodo raiz, Reporte_Tabla_Simbolos global, Reporte_Tabla_Simbolos tabla) {
        this.raiz = raiz;
        this.global = global;
        this.tabla = tabla;
        opL = new Operacion_ALR(global, tabla);
        declarar();
    }
    
    public Declaracion(Nodo raiz, Reporte_Tabla_Simbolos global, Reporte_Tabla_Simbolos tabla, String tipo) {
        this.raiz = raiz;
        this.global = global;
        this.tabla = tabla;
        opL = new Operacion_ALR(global, tabla);
    }
    
    public Simbolo_S declarar()
    {
        switch (raiz.nombre) {
            case "primitivaG":
                return primitivaG();
            case "primitiva":
                return primitiva();
            case "primitivaUna":
                return primitivaUna();    
        }
        return null;
    }
    
    private Simbolo_S primitivaG()
    {
        for (Nodo id_hijo : raiz.hijos.get(0).hijos) {
            String nombre = id_hijo.hijos.get(0).valor;
            Nodo exp = id_hijo.hijos.get(1);
            
            if(exp.nombre.equals(""))
            {
                Simbolo_S simbolo = new Simbolo_S("", nombre,"", (int)-111111);
                simbolo.inicializado = false;
                if (!global.setSimbolo(simbolo)) {
                    Principal.reporteError.agregar("Semantico", raiz.linea, raiz.columna, "La variable " + nombre + " ya existe");
                }
                continue;
            }
            
            Resultado_S resultado = opL.operar(exp);
            if (resultado != null) {
                Simbolo_S simbolo = new Simbolo_S(resultado.tipo, nombre,"", resultado.valor);
                simbolo.inicializado = true;
                if (!global.setSimbolo(simbolo)) {
                    Principal.reporteError.agregar("Semantico", raiz.linea, raiz.columna, "La variable " + nombre + " ya existe");
                }
            }
            
        }
        return null;
    }
     
    private Simbolo_S primitiva()
    {
        for (Nodo id_hijo : raiz.hijos.get(0).hijos) {
            String nombre = id_hijo.hijos.get(0).valor;
            Nodo exp = id_hijo.hijos.get(1);
            
            if(exp.nombre.equals(""))
            {
                Simbolo_S simbolo = new Simbolo_S("", nombre, (int)-111111);
                simbolo.inicializado = false;
                if (!tabla.setSimbolo(simbolo)) {
                    Principal.reporteError.agregar("Semantico", raiz.linea, raiz.columna, "La variable " + nombre + " ya existe");
                }
                
            }
            
            Resultado_S resultado = opL.operar(exp);
            
            if (resultado != null) {
                Simbolo_S simbolo = new Simbolo_S(resultado.tipo, nombre,"", resultado.valor);
                simbolo.inicializado = true;
                if (!tabla.setSimbolo(simbolo)) {
                    Principal.reporteError.agregar("Semantico", raiz.linea, raiz.columna, "La variable " + nombre + " ya existe");
                }
            }
            
        }
        return null;
    }
    
    private Simbolo_S primitivaUna()
    {
        for (Nodo id_hijo : raiz.hijos.get(0).hijos) {
            String nombre = id_hijo.hijos.get(0).valor;
            Nodo exp = id_hijo.hijos.get(1);
            
            Resultado_S resultado = opL.operar(exp);
            
            if (resultado != null) {
                Simbolo_S simbolo = new Simbolo_S(resultado.tipo, nombre, resultado.valor);
                simbolo.inicializado = true;
                if (!tabla.setSimbolo(simbolo)) {
                    Principal.reporteError.agregar("Semantico", raiz.linea, raiz.columna, "La variable " + nombre + " ya existe");
                }else
                {
                    return simbolo;
                }
            }
        }
        return null;
    }
   
}
