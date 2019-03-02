/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos_Ejecucion_Draco;

import Estructuras_Ide.Nodo;
import Elementos_Draco.Draco_Clas;
import Elementos_Draco.Elemento_C_S;
import EjecutorPrincipal_Draco.EjecutorDraco;
import Elementos_Draco.Resultado_S;
import Elementos_Draco.Simbolo_S;
import Elementos_Draco.Reporte_Tabla_Simbolos;
import proyecto2_compi2.Principal;

/**
 *
 * @author antonio
 */
public class Asignacion extends Elemento_C_S{
    Nodo valorIndice;

    public Asignacion(Nodo raiz, Reporte_Tabla_Simbolos global, Reporte_Tabla_Simbolos tabla) {
        this.raiz = raiz;
        this.global = global;//tabla de sibolos de una clase
        this.tabla = tabla;//tabla de simbolos de un metodo
        opL = new Operacion_ALR(global, tabla);
        asignar();
    }
    
    
    public Asignacion(Nodo raiz, Reporte_Tabla_Simbolos global, Reporte_Tabla_Simbolos tabla, String tipo) {
        this.raiz = raiz;
        this.global = global;
        this.tabla = tabla;
        opL = new Operacion_ALR(global, tabla);
    }
    
    public Simbolo_S asignar() {
        switch (raiz.nombre) {
            case "asignacion"://asignacion de objetos,variables y arreglos
                return asignacion();   
        }
        return null;
    }

    private Simbolo_S asignacion() {
        valorIndice = raiz.hijos.get(1);
        Simbolo_S simbolo = acceso(raiz.hijos.get(0));
        
        Resultado_S resultado = opL.operar(raiz.hijos.get(1));
        if (resultado.valor != null) {
            if (simbolo != null) {   
                simbolo.tipo = resultado.tipo;
                simbolo.valor = resultado.valor;
                simbolo.inicializado = true;
                return simbolo;
                
            } else {
                Principal.reporteError.agregar("Semantico", raiz.linea, raiz.columna, "La variable " + raiz.valor + " no existe");
            }
        } else {
            Principal.reporteError.agregar("Semantico", raiz.linea, raiz.columna, "Tipo de dato invalido");
        }
        return null;
    }
    
    
    private Simbolo_S acceso(Nodo raiz) {
        Draco_Clas aux = EjecutorDraco.claseActual;
        Reporte_Tabla_Simbolos tablaAux = tabla;
        int nivel = 0;
        Simbolo_S sim = null;
        for (Nodo acceso : raiz.hijos) {
            String nombre;
            Simbolo_S simbolo;
            switch (acceso.nombre) {
                
                case "id":
                    nombre = acceso.valor;
                    simbolo = tabla.getSimbolo(nombre, aux);
                
                    if (simbolo != null) {

                        switch (simbolo.tipo) {
                            case "entero":
                            case "cadena":
                            case "booleano":
                            case "caracter":
                            case "decimal":
                            default:    
                                sim = simbolo;
                                break;
                        }

                    } else {
                        Principal.reporteError.agregar("Semantico", acceso.linea, acceso.columna, "La variable " + nombre + " no existe en el ambito donde fue invocada");
                        return null;
                    }
                    break;
                
            }

        }
        tabla = tablaAux;
        return sim;
    }

}
