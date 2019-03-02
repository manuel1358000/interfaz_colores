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
public class Draco_M_S {
    public String nombre;
    public String id;
    public boolean estadoTerminar = false;
    public boolean estadoContinuar = false;
    public Nodo sentencias;
    public ArrayList<Nodo> parametros;
    public Resultado_S retorno;
    public String tipo;
    public String visibilidad;

    public boolean estadoRetorno = false;

    public Draco_M_S() {

    }

    public Draco_M_S(Nodo raiz) {
        this.tipo = "vacio";
        this.nombre = "principal";
        this.sentencias = raiz.hijos.get(0);
        this.id = nombre;
        this.visibilidad = "privado";
        this.parametros = new ArrayList();

    }
}
