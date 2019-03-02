/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos_Draco;

import EjecutorPrincipal_Draco.EjecutorDraco;
import java.util.Hashtable;

/**
 *
 * @author antonio
 */
public class Reporte_Tabla_Simbolos {
    public Hashtable<String, Simbolo_S> tabla;

    public Reporte_Tabla_Simbolos() {
        tabla = new Hashtable<>();
    }

    public Boolean existe(String nombre) {
        nombre = nombre.toLowerCase();
        return tabla.containsKey(nombre);
    }

    public Simbolo_S getSimbolo(String nombre, Draco_Clas claseActual) {
        Simbolo_S buscado = null;
        Reporte_Tabla_Simbolos principal = claseActual.global;
        nombre = nombre.toLowerCase();
        if (existe(nombre)) {
            return tabla.get(nombre);
        } else//si no existe en el ambito local se busca en el ambito global
        {
            if (principal.existe(nombre)) {
                return principal.tabla.get(nombre);
            } else {
                Draco_Clas hereda = claseActual.herencia;

                if (hereda != null) {
                    buscado = hereda.global.getSimbolo(nombre, hereda);
                } else {
                    return null;
                }
            }
        }
        if (buscado != null) {
            if (buscado.visibilidad.equalsIgnoreCase("publico") || buscado.visibilidad.equalsIgnoreCase("protegido")) {
                return buscado;
            } else {
                return null;
            }
        }
        return buscado;
    }

    public boolean setSimbolo(Simbolo_S simbolo) {
        simbolo.nombre = simbolo.nombre.toLowerCase();
        if (!existe(simbolo.nombre)) {
            tabla.put(simbolo.nombre, simbolo);
            if (EjecutorDraco.metodoActual != null) {
                simbolo.ambito = EjecutorDraco.claseActual.nombre + "_" + EjecutorDraco.metodoActual.nombre;
            } else {
                simbolo.ambito = EjecutorDraco.claseActual.nombre;
            }
            simbolo.rol = "Variable";
            EjecutorDraco.reporteSimbolos.add(simbolo);
            return true;//se agrego correctamente
        } else {
            return false;//no se agrego a la global
        }
    }

    public void cambiarAmbito(Reporte_Tabla_Simbolos actual) {
        for (Simbolo_S simbolo : actual.tabla.values()) {
            tabla.put(simbolo.nombre, simbolo);
        }
    }
}
