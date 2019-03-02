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
import Elementos_Draco.Reporte_Tabla_Simbolos;
import java.io.IOException;
import javax.swing.text.BadLocationException;
import proyecto2_compi2.Principal;

/**
 *
 * @author antonio
 */
public class Mientras extends Elemento_C_S{
      public Draco_M_S ejecutar(Nodo raiz) throws IOException, BadLocationException {
        Nodo exp = raiz.hijos.get(0);
        Nodo sentencias = raiz.hijos.get(1);

        opL = new Operacion_ALR(global, tabla);
        Resultado_S condicion = opL.operar(exp);
        if (condicion.tipo.equals("entero")) {
            if ((int) condicion.valor == 1) {
                condicion = new Resultado_S("booleano", true);
            } else if ((int) condicion.valor == 0) {
                condicion = new Resultado_S("booleano", false);
            }
        }
        if (condicion.tipo.equalsIgnoreCase("booleano")) {
            while ((Boolean) condicion.valor) {
                //se cambia el ambito
                Reporte_Tabla_Simbolos tablaTemp = new Reporte_Tabla_Simbolos();
                tablaTemp.cambiarAmbito(tabla);
                pilaTablas.push(tabla);
                tabla = tablaTemp;
                metodoActual = ejecutarSentencias(sentencias);
                //regresamos al ambito
                tabla = pilaTablas.pop();
                opL = new Operacion_ALR(global, tabla);
                condicion = opL.operar(exp);

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

            }
        } else {
            Principal.reporteError.agregar("Semantico", raiz.linea, raiz.columna, "El tipo de dato de la condicion no es Bool");
        }
        return metodoActual;
    }
}
