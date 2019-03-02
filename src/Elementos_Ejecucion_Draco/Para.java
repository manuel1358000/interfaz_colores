/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos_Ejecucion_Draco;

import Estructuras_Ide.Nodo;
import Elementos_Draco.Elemento_C_S;
import Elementos_Draco.Draco_M_S;
import Elementos_Draco.Resultado_S;
import Elementos_Draco.Simbolo_S;
import Elementos_Draco.Reporte_Tabla_Simbolos;
import java.io.IOException;
import javax.swing.text.BadLocationException;
import proyecto2_compi2.Principal;

/**
 *
 * @author antonio
 */
public class Para extends Elemento_C_S{
    public Draco_M_S ejecutar(Nodo raiz) throws IOException, BadLocationException {

        Nodo variable = raiz.hijos.get(0);
        Nodo expCondicion = raiz.hijos.get(1);
        Nodo expSimplificada = raiz.hijos.get(2);
        Nodo sentencias = raiz.hijos.get(3);

        Reporte_Tabla_Simbolos tablaTempPrincipal = new Reporte_Tabla_Simbolos();
        tablaTempPrincipal.cambiarAmbito(tabla);
        pilaTablas.add(tabla);
        tabla = tablaTempPrincipal;

        Simbolo_S simbolo = null;
        if (variable.nombre.equals("asignacion")) {
            Asignacion asig = new Asignacion(variable, global, tabla, "asignacion");
            simbolo = asig.asignar();
        } else {
            variable.nombre+="Una";
            Declaracion declara = new Declaracion(variable, global, tabla, "Declaracion");
            simbolo = (Simbolo_S) declara.declarar();
        }

        if (simbolo != null) {

            opL = new Operacion_ALR(global, tabla);
            Resultado_S condicion = opL.operar(expCondicion);
            if (condicion.tipo.equals("entero")) {
                if ((int) condicion.valor == 1) {
                    condicion = new Resultado_S("boolean", true);
                } else if ((int) condicion.valor == 0) {
                    condicion = new Resultado_S("boolean", false);
                }
            }
            if (condicion.tipo.equals("booleano")) {
                while ((Boolean) condicion.valor) {
                    Reporte_Tabla_Simbolos tablaTemp = new Reporte_Tabla_Simbolos();
                    tablaTemp.cambiarAmbito(tabla);
                    pilaTablas.push(tabla);
                    tabla = tablaTemp;
                    metodoActual = ejecutarSentencias(sentencias);
                    tabla = pilaTablas.pop();
                    opL = new Operacion_ALR(global, tabla);
                    opL.operar(expSimplificada);
                    
                    if (metodoActual.estadoRetorno) {
                        break;
                    }
                    if (metodoActual.estadoTerminar) {
                        metodoActual.estadoTerminar = false;
                        break;
                    }

                    if (metodoActual.estadoContinuar) {
                        metodoActual.estadoContinuar = false;
                    }

                    condicion = opL.operar(expCondicion);
                    if (condicion.tipo.equals("entero")) {
                        if ((int) condicion.valor == 1) {
                            condicion = new Resultado_S("booleano", true);
                        } else if ((int) condicion.valor == 0) {
                            condicion = new Resultado_S("booleano", false);
                        }
                    }
                }
            } else {
                Principal.reporteError.agregar("Semantico", raiz.linea, raiz.columna, "La condicion del ciclo para solo permite operaciones que retornan un valor booleano");
            }

        }
        tabla = pilaTablas.pop();
        return metodoActual;
    }
}
