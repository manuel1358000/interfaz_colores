/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras_Ide;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author antonio
 */
public class TablaSimbolo {

    ArrayList<Simbolo> metodos = new ArrayList<>();
    ArrayList<Simbolo> simbolos = new ArrayList<>();
    public ArrayList<Simbolo> globales = new ArrayList<>();
    ArrayList<Simbolo> constructores = new ArrayList<>();
    ArrayList<String> ambito = new ArrayList<>();
    int direccion = 0;

    public void generar_codigo(Nodo raiz) {
        for (Nodo hijo : raiz.hijos) {
            //System.out.println(">>"+hijo.nombre);
            switch (hijo.nombre) {
                case "declaracion_estructura":
                    String nombre_estructura = hijo.hijos.get(0).valor;
                    ArrayList<String> ambito_tmp = this.ambito;
                    int direccion_tmp = this.direccion;
                    this.direccion = 0;
                    this.ambito = new ArrayList<>();
                    this.ambito.add(nombre_estructura);
                    generar_codigo(hijo.hijos.get(1));
                    this.ambito.remove(this.ambito.size() - 1);
                    this.ambito = ambito_tmp;

                    String ambito_generado = generarAmbito(ambito);
                    Simbolo sim_struct = new Simbolo(nombre_estructura, "struct", ambito_generado, "struct", -1, this.direccion, new ArrayList<>(), hijo.archivo);

                    this.agregar_Simbolo(sim_struct, hijo);
                    this.direccion = direccion_tmp;
                    break;
                case "primitiva":
                case "primitivaDA":    
                case "struct":
                case "structDA":    
                case "arreglo":    
                    for (Nodo Nodo_ID : hijo.hijos.get(1).hijos) {
                        String nombre = Nodo_ID.valor;
                        String tipo = hijo.hijos.get(0).valor;

                        ArrayList<Nodo> DIMENSION = new ArrayList<>();
                        Nodo Dimensiones = hijo.hijos.get(2);
                        if (!Dimensiones.hijos.isEmpty()) {
                            for (Nodo nodoDim : Dimensiones.hijos) {
                                DIMENSION.add(nodoDim);
                            }
                        }

                        Simbolo sim = new Simbolo(nombre, tipo, this.generarAmbito(this.ambito), "variable", this.direccion, 1, DIMENSION, hijo.archivo);
                        this.agregar_Simbolo(sim, Nodo_ID);
                    }
                    break;
                case "primitivaGlobal":
                case "primitivaDAGlobal":    
                case "structGlobal":
                case "structDAGlobal":    
                case "arregloGlobal":                    
                    for (Nodo Nodo_ID : hijo.hijos.get(1).hijos) {
                        String nombre = Nodo_ID.valor;
                        String tipo = hijo.hijos.get(0).valor;

                        ArrayList<Nodo> DIMENSION = new ArrayList<>();
                        Nodo Dimensiones = hijo.hijos.get(2);
                        if (!Dimensiones.hijos.isEmpty()) {
                            for (Nodo nodoDim : Dimensiones.hijos) {
                                DIMENSION.add(nodoDim);
                            }
                        }

                        Simbolo sim = new Simbolo(nombre, tipo, this.generarAmbito(this.ambito), "variable global", this.direccion, 1, DIMENSION, hijo.archivo);
                        this.agregar_Simbolo(sim, Nodo_ID);
                    }
                    break;
                case "metodo":
                case "metodoArray":
                    int direccion_aux = this.direccion;
                    this.direccion = 0;
                    String tipo_metodo = hijo.hijos.get(0).valor;
                    String nombre_metodo = generar_Id(hijo);
                    ArrayList<Nodo> Parametros = hijo.hijos.get(2).hijos;
                    this.ambito.add(nombre_metodo);

                    Simbolo sim_ret = new Simbolo("$ret", tipo_metodo, this.generarAmbito(this.ambito), "variable", this.direccion, 1, new ArrayList<>(), hijo.archivo);
                    this.agregar_Simbolo(sim_ret, hijo);

                    Simbolo sim_this = new Simbolo("$this", tipo_metodo, this.generarAmbito(this.ambito), "variable", this.direccion, 1, new ArrayList<>(), hijo.archivo);
                    this.agregar_Simbolo(sim_this, hijo);

                    Simbolo sim_calc = new Simbolo("$calc", tipo_metodo, this.generarAmbito(this.ambito), "variable", this.direccion, 1, new ArrayList<>(), hijo.archivo);
                    this.agregar_Simbolo(sim_calc, hijo);


                    for (Nodo Parametro : Parametros) {
                        switch (Parametro.nombre) {
                            case "parametroVal":
                            case "parametroStruct":
                                String tipo_parametro = Parametro.hijos.get(0).valor;
                                String nombre_parametro = Parametro.hijos.get(1).valor;
                                Simbolo sim_paramVal = new Simbolo(nombre_parametro, tipo_parametro, this.generarAmbito(this.ambito), "parametro", this.direccion, 1, new ArrayList<>(), hijo.archivo);
                                this.agregar_Simbolo(sim_paramVal, hijo);
                                break;
                        }
                    }

                    ArrayList<Nodo> DIMENSION = new ArrayList<>();
                    if (hijo.nombre.equals("metodoArray")) {
                        Nodo Dimensiones = hijo.hijos.get(4);
                        if (!Dimensiones.hijos.isEmpty()) {
                            for (Nodo nodoDim : Dimensiones.hijos) {
                                DIMENSION.add(nodoDim);
                            }
                        }
                    }

                    generar_codigo(hijo.hijos.get(hijo.hijos.size()-2));
                    this.ambito.remove(this.ambito.size() - 1);

                    Simbolo sim_metodo = new Simbolo(nombre_metodo, tipo_metodo, this.generarAmbito(this.ambito), "metodo", -1, this.direccion, DIMENSION, hijo.archivo);
                    sim_metodo.nodo = hijo;
                    this.agregar_Simbolo(sim_metodo, hijo);
                    this.direccion = direccion_aux;

                    break;
                case "Mientras":
                    this.generar_codigo(hijo.hijos.get(1));
                    break;
                case "Si":
                    this.generar_codigo(hijo.hijos.get(1));
                    Nodo otra_parte = hijo.hijos.get(2);
                    if(otra_parte.hijos.size()==1)
                    {
                        this.generar_codigo(otra_parte.hijos.get(0).hijos.get(0));
                    }else if(otra_parte.hijos.size()==2)
                    {
                        Nodo muchos_sino = otra_parte.hijos.get(0);
                        for (Nodo hijo1 : muchos_sino.hijos) {
                            this.generar_codigo(hijo1.hijos.get(1));
                        }
                        this.generar_codigo(otra_parte.hijos.get(1).hijos.get(0));
                    }
                    break;
            }
        }
    }

    public String generarAmbito(ArrayList<String> ambito_lista) {
        String str_ambito = "";
        if (ambito_lista.isEmpty()) {
            return "global";
        } else {
            for (String cad : ambito_lista) {
                str_ambito += cad + "/";
            }
            str_ambito = str_ambito.substring(0, str_ambito.length() - 1);
        }
        return str_ambito;
    }

    public Simbolo ObtenerSimboloDeclarado(String nombre,ArrayList<String> ambito) 
    {
        String ambito_str = this.generarAmbito(ambito);
        for (Simbolo simbolo : this.simbolos) {
            if(nombre.equals(simbolo.nombre))
            {
                int estado = this.pertenece_ambito(ambito_str, simbolo.ambito);
                if( (estado==0 || simbolo.ambito.equals("global")) && simbolo.declarado)
                {
                    return simbolo;
                }
            }
        }
        
        return null;
    }
    
    
    public Simbolo HacerSimboloDeclarado(Simbolo sim) 
    {
        if(this.si_existe(sim))
        {
            if(!sim.declarado)
            {
                sim.declarado=true;
                return sim;
            }
        }
        return null;
    }
    
    public void agregar_Simbolo(Simbolo sim, Nodo nodo) {
        if (!si_existe(sim)) {
            this.simbolos.add(sim);
            this.direccion++;

            if (sim.ambito.equals("global") && sim.rol.equals("variable global")) {
                this.globales.add(sim);
            }

            if (sim.rol.equals("metodo")) {
                this.metodos.add(sim);
            }

            if (sim.rol.equals("constructor")) {
                this.constructores.add(sim);
            }

        } else {
            Ejecucion_D.Interpretacion.Lista_Errores.add(nodo.linea, nodo.columna, "Semantico", "La variable " + sim.nombre + " ya existe", nodo.archivo);
        }
    }

    public Simbolo obtener_Simbolo(String nombre, ArrayList<String> ambito) {
        String amb_tmp = this.generarAmbito(ambito);
        for (Simbolo simbolo : this.simbolos) {
            if (nombre.equals(simbolo.nombre)) {
                if (es_primitivo(simbolo.tipo)) {
                    if (amb_tmp.equals(simbolo.ambito)) {
                        return simbolo;
                    }
                } else {
                    int estado = this.pertenece_ambito(amb_tmp, simbolo.ambito);
                    if (estado == 0 || simbolo.ambito.equals("global")) {
                        return simbolo;
                    }
                }
            }
        }
        return null;
    }

    public boolean si_existe(Simbolo sim) {
        for (Simbolo simbolo : simbolos) {
            if (simbolo.nombre.equals(sim.nombre)) {
                if (es_primitivo(simbolo.tipo)) {
                    int estado = pertenece_ambito(simbolo.ambito, sim.ambito);
                    if (estado == 0) {
                        return true;
                    }
                } else {
                    if (simbolo.ambito.equals(sim.ambito)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int pertenece_ambito(String ambito_simbolo, String ambito_agregar) {
        String[] amb1 = ambito_simbolo.split("/");
        String[] amb2 = ambito_agregar.split("/");
        if (amb1 == amb2) {
            return 0;
        }
        for (int i = 0; i < amb2.length; i++) {
            if (!amb1[i].equals(amb2[i])) {
                return -1;
            }
        }
        return 0;
    }

    public boolean es_primitivo(String tipo) {
        switch (tipo) {
            case "entero":
            case "decimal":
            case "booleano":
            case "caracter":
            case "cadena":
                return true;
            default:
                return false;
        }
    }

    public void generar_reporte() throws IOException {
        String str_html = "<html >";
        str_html += "<body >";
        str_html += "<table style=\"width:100%\">";
        str_html += "<tr>";
        str_html += "<th>" + "Nombre" + "</th>";
        str_html += "<th>" + "Tipo" + "</th>";
        str_html += "<th>" + "Ambito" + "</th>";
        str_html += "<th>" + "Rol" + "</th>";
        str_html += "<th>" + "Direccion" + "</th>";
        str_html += "<th>" + "Tamanio" + "</th>";
        str_html += "<th>" + "Dimension" + "</th>";
        str_html += "<th>" + "Archivo" + "</th>";

        str_html += "</tr>";

        for (Simbolo item : this.simbolos) {
            str_html += "<tr>";
            str_html += "<th>" + item.nombre + "</th>";
            str_html += "<th>" + item.tipo + "</th>";
            str_html += "<th>" + item.ambito + "</th>";
            str_html += "<th>" + item.rol + "</th>";
            str_html += "<th>" + item.direccion + "</th>";
            str_html += "<th>" + item.tamanio + "</th>";
            str_html += "<th>" + item.dimension.size() + "</th>";
            str_html += "<th>" + item.archivo + "</th>";
            str_html += "</tr>";
        }

        str_html += "</table>";
        str_html += "</body>";
        str_html += "</html>";

        PrintWriter writer = new PrintWriter("r.html", "UTF-8");
        writer.println(str_html);

        writer.close();
        //Desktop.getDesktop().open(new File("r.html"));

    }

    public String generar_Id(Nodo nodo) {
        String id = nodo.hijos.get(1).valor;
        ArrayList<Nodo> parametros = nodo.hijos.get(2).hijos;
        for (Nodo parametro : parametros) {
            id += "$" + parametro.hijos.get(0).valor;
        }
        return id;
    }
    
    
    public Simbolo obtener_Estructura(Simbolo sim ,  ArrayList<String> ambito)
    {
        String ambito_tmp  = this.generarAmbito(ambito);
        for (Simbolo simbolo : simbolos) {
            if(simbolo.tipo.equals("struct"))
            {
                if(simbolo.nombre.equals(sim.tipo) && simbolo.ambito.equals("global"))
                {
                    return simbolo;
                }
            }
        }
        return null;
    }

}
