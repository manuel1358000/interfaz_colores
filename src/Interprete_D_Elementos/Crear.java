/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete_D_Elementos;

import Estructuras_Ide.Nodo;
import Estructuras_Ide.Simbolo;
import java.util.ArrayList;

/**
 *
 * @author antonio
 */
public class Crear extends Ejecucion_D.Interpretacion{
    public void crear_estructuras()
    {
        ArrayList<Nodo> elementos = new ArrayList<>();
        for (Nodo estructura : estructuras) {
            elementos.add(estructura);
            crear_(estructura,elementos);
        }
    }
    
    public void crear_(Nodo estructura, ArrayList<Nodo> elementos)
    {
        ArrayList<Nodo> sentencias = estructura.hijos.get(1).hijos;
        Simbolo simbolo = tabla.obtener_Simbolo(estructura.hijos.get(0).valor, ambito);
        ArrayList<String> ambito_aux = ambito;
        ambito = new ArrayList<>();
        ambito.add(simbolo.nombre);
        
        String codigo_tmp = "\n\nfunction struct_"+simbolo.nombre+"\n";
        Xblockes.agregar(codigo_tmp, estructura);
        for (Nodo sentencia : sentencias) {
            switch(sentencia.nombre)
            {
                case "primitiva":
                    Xdeclaracion.declaracionPrimitivaDGlobal(sentencia);
                    break;
                case "struct":
                    Xdeclaracion.declaracionEstructuraGlobal(sentencia);
                    
                default:
                    Lista_Errores.add(sentencia.linea, sentencia.columna, "Semantico", "Dentro de una estructura solo se puede declarar", sentencia.archivo);
            }
        }
        
        codigo_tmp = "End";
        Xblockes.agregar_AlUltimoBloque(codigo_tmp);
        ambito.remove(ambito.size()-1);
        ambito = ambito_aux;
    }
}