/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos_Draco;

import Ejecucion_D.Ejecutor;
import Estructuras_Ide.Nodo;
import Elementos_Ejecucion_Draco.Operacion_ALR;
import Elementos_Ejecucion_Draco.Asignacion;
import Elementos_Ejecucion_Draco.Declaracion;
import Elementos_Ejecucion_Draco.Mientras;
import Elementos_Ejecucion_Draco.Para;
import Elementos_Ejecucion_Draco.Si;
import Interprete_D_Elementos.ControlValor;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import org.fife.ui.rtextarea.RTextScrollPane;
import proyecto2_compi2.Elemento;
import proyecto2_compi2.Principal;

/**
 *
 * @author anton
 */
public  class Elemento_C_S {
    //--
    public int  nivelCondicion = 0;
      //archivos
    public static ArrayList<Draco_File> archivos;
    public String elementoActual;
    public static ArrayList<Simbolo_S> reporteSimbolos;
    public static Stack<Draco_Clas> pilaClases;
    public static Stack<Draco_M_S> pilaMetodos;
    public static Draco_M_S metodoActual;
    public static Stack<Reporte_Tabla_Simbolos> pilaTablas;

    //public static ArrayList<Clase> clases;
    public static Draco_Clas claseActual;
    public static Reporte_Tabla_Simbolos tabla;
    public static Reporte_Tabla_Simbolos global;
    //sirven para el controlar las sentencias continuar y terminar
    public static int alturaCiclo = 0;
    public static Stack<Integer>pilaNivelAmbiente;
    public static Stack<Integer> pilaNivelCiclo;
    public Operacion_ALR opL;
    protected Nodo raiz;

    protected Draco_M_S ejecutarSentencias(Nodo sentencias) throws IOException, BadLocationException 
    {   
        Nodo lista_expr;
        Resultado_S result;

        for (Nodo sentencia : sentencias.hijos) 
        {
            if(Principal.variableDebugger==true){
                int referencia=sentencia.linea-1;
                if(referencia>=0){
                    Principal.agregar_puntos(sentencia.linea-1);
                    JOptionPane.showMessageDialog(null, "Linea que se genera "+ (sentencia.linea));
                    Principal.quitar_puntos(sentencia.linea-1);
                }
            }
            switch (sentencia.nombre) 
            {
                
                case "primitiva":
                    try {
                        if(alturaCiclo==0 && nivelCondicion==0)
                        {
                            sentencia.nombre += "G";
                        }
                        new Declaracion(sentencia, global, tabla);
                    } catch (Exception e) {
                        Principal.reporteError.agregar("Ejecucion", sentencia.linea, sentencia.columna,"Declaracion variable:"+ e.getMessage());
                    }
                    break;
                case "imprimo_valores":
                    break;
                case "imprimir":
                    try {
                        opL = new Operacion_ALR(global, tabla);
                        //rincipal.pila.push(1.0);
                        try{
                            Resultado_S imprimir = opL.operar(sentencia.hijos.get(0));
                            System.out.println(imprimir.valor+"aca");
                            for(int i=0;i<Principal.listAreas.size();i++){
                                if(Principal.listAreas.get(i).getName().equals("texto_consola")){
                                    String contenido=Principal.listAreas.get(0).getText();
                                    contenido+="\n"+imprimir.valor;
                                    Principal.listAreas.get(0).setText(contenido);
                                }
                            }
                        }catch(Exception f){
                            System.out.println("Tengo un error en la logica");
                        }
                    } catch (Exception e) {
                        Principal.reporteError.agregar("Ejecucion", sentencia.linea, sentencia.columna, "Sentencia imprimir:"+e.getMessage());
                    }
                    break;
                case "mientras":
                    try {
                        Mientras mientras = new Mientras();
                        alturaCiclo++;
                        
                        metodoActual = mientras.ejecutar(sentencia);
                        
                        alturaCiclo--;
                    } catch (Exception e) {
                        Principal.reporteError.agregar("Ejecucion", sentencia.linea, sentencia.columna, "Sentencia Mientras:"+e.getMessage());
                    }
                    break; 
                   
                case "simplificada":
                    opL = new Operacion_ALR(global, tabla);
                    opL.operar(sentencia);
                    break;
                case "para":
                    try {
                        alturaCiclo++;
                        Para para = new Para();
                        metodoActual = para.ejecutar(sentencia);
                        alturaCiclo--;
                    } catch (Exception e) {
                        Principal.reporteError.agregar("Ejecucion", sentencia.linea, sentencia.columna, "Sentencia Mientras:"+e.getMessage());
                    }
                    
                    break; 
                case "detener":
                    if (alturaCiclo > 0) {
                        metodoActual.estadoTerminar = true;
                        return metodoActual;
                    } else {
                        Principal.reporteError.agregar("Semantico", sentencia.linea, sentencia.columna, "La sentencia terminar solo puede estar detro de ciclos");
                    }
                    break;    
                case "pintarpunto":
                    try {
                        lista_expr = sentencia.hijos.get(0);
                        Elemento elemento=new Elemento();
                        int cont_parametro=0;
                        for (Nodo expr : lista_expr.hijos) {
                            opL = new Operacion_ALR(global, tabla);
                            result = opL.operar(expr);
                            if(result.valor!=null){
                                try{
                                    if(cont_parametro==0){
                                        elemento.setX(Integer.parseInt(result.valor.toString()));
                                        cont_parametro++;
                                    }else if(cont_parametro==1){
                                        elemento.setY(Integer.parseInt(result.valor.toString()));
                                        cont_parametro++;
                                    }else if(cont_parametro==2){
                                        elemento.setColor(result.valor.toString());
                                        cont_parametro++;
                                    }else if(cont_parametro==3){
                                        elemento.setDiametro(Integer.parseInt(result.valor.toString()));
                                        Principal.pintar.agregarPoint(elemento.getX(),elemento.getY(),elemento.getColor(),elemento.getDiametro());
                                        cont_parametro++;
                                    }else{
                                        System.out.println("Ocurrio un error");
                                    }
                                }catch(Exception e){
                                    System.out.println("error semantico");
                                }
                            }
                        }
                    } catch (Exception e) {
                        Principal.reporteError.agregar("Ejecucion", sentencia.linea, sentencia.columna, "Ocurrio un error pintando punto:"+e.getMessage());
                    }
                    
                    break;
                case "asignacion":
                    try {
                        new Asignacion(sentencia, global, tabla);
                    } catch (Exception e) {
                        Principal.reporteError.agregar("Ejecucion", sentencia.linea, sentencia.columna, "Sentencia Mientras:"+e.getMessage());
                    }
                    break;
                case "imprimo_":
                    try {
                        lista_expr = sentencia.hijos.get(0);
                        for (Nodo expr : lista_expr.hijos) {
                            opL = new Operacion_ALR(global, tabla);
                            result = opL.operar(expr);
                            if(result.valor!=null && result.tipo.equals("cadena")){
                                System.out.println("Voy a buscar el archivo DASM para y lo ejecuto");
                            }
                        }
                    } catch (Exception e) {
                        Principal.reporteError.agregar("Ejecucion", sentencia.linea, sentencia.columna, "Error Importar DASM"+e.getMessage());
                    }
                    break;
                case "verificar_elementos":
                    try {
                        lista_expr = sentencia.hijos.get(0);
                        for (Nodo expr : lista_expr.hijos) {
                            opL = new Operacion_ALR(global, tabla);
                            result = opL.operar(expr);
                            if(result.valor!=null && result.tipo.equals("cadena")){
                                System.out.println("Voy a buscar el archivo DASM para y lo ejecuto");
                            }
                        }
                    } catch (Exception e) {
                        Principal.reporteError.agregar("Ejecucion", sentencia.linea, sentencia.columna, "Error Importar DASM"+e.getMessage());
                    }
                    break;
                
                case "importar_dasm":
                    try {
                        lista_expr = sentencia.hijos.get(0);
                        for (Nodo expr : lista_expr.hijos) {
                            opL = new Operacion_ALR(global, tabla);
                            result = opL.operar(expr);
                            if(result.valor!=null && result.tipo.equals("cadena")){
                                //aca tengo que leer el archivo DASM y ejecutarlo
                                System.out.println("El archivo que voy a leer es "+result.valor);
                                System.out.println("aqui");
                                //mando a llamar al ejecutor de DASM
                                Ejecutor ejecutar=new Ejecutor();
                                ejecutar.ejecutar_dasm(new File ("").getAbsolutePath ()+"\\ruta\\"+result.valor.toString());
                            }
                        }
                    } catch (Exception e) {
                        Principal.reporteError.agregar("Ejecucion", sentencia.linea, sentencia.columna, "Error Importar DASM"+e.getMessage());
                    }
                    
                    break;        
                
                case "pintarlinea":
                    try {
                        lista_expr = sentencia.hijos.get(0);
                        Elemento elemento5=new Elemento();
                        int cont_parametros5=0;
                        for (Nodo expr : lista_expr.hijos) {
                            opL = new Operacion_ALR(global, tabla);
                            result = opL.operar(expr);
                            if(result.valor!=null){
                                try{
                                    if(cont_parametros5==0){
                                        elemento5.setX(Integer.parseInt(result.valor.toString()));
                                        cont_parametros5++;
                                    }else if(cont_parametros5==1){
                                        elemento5.setY(Integer.parseInt(result.valor.toString()));
                                        cont_parametros5++;
                                    }else if(cont_parametros5==2){
                                        elemento5.setXf(Integer.parseInt(result.valor.toString()));
                                        cont_parametros5++;
                                    }else if(cont_parametros5==3){
                                        elemento5.setYf(Integer.parseInt(result.valor.toString()));
                                        cont_parametros5++;
                                    }else if(cont_parametros5==4){
                                        elemento5.setColor(result.valor.toString());
                                        cont_parametros5++;
                                    }else if(cont_parametros5==5){
                                        elemento5.setGrosor(Integer.parseInt(result.valor.toString()));
                                        Principal.pintar.agregarLine(elemento5.getX(),elemento5.getY(),elemento5.getXf(),elemento5.getYf(),elemento5.getColor(),elemento5.getGrosor());
                                        cont_parametros5++;
                                    }else{
                                        //termino la ejecucion
                                    }
                                }catch(Exception e){
                                    System.out.println("error semantico");
                                }
                            }
                        }
                    } catch (Exception e) {
                        Principal.reporteError.agregar("Ejecucion", sentencia.linea, sentencia.columna, "Ocurrio un error en pintar linea:"+e.getMessage());
                    }
                    
                    break; 
                case "pintarcuadrado":
                    try {
                        lista_expr = sentencia.hijos.get(0);
                        Elemento elemento2=new Elemento();
                        int cont_parametros2=0;
                        for (Nodo expr : lista_expr.hijos) {
                            opL = new Operacion_ALR(global, tabla);
                            result = opL.operar(expr);
                            if(result.valor!=null){
                                try{
                                    if(cont_parametros2==0){
                                        elemento2.setX(Integer.parseInt(result.valor.toString()));
                                        cont_parametros2++;
                                    }else if(cont_parametros2==1){
                                        elemento2.setY(Integer.parseInt(result.valor.toString()));
                                        cont_parametros2++;
                                    }else if(cont_parametros2==2){
                                        elemento2.setColor(result.valor.toString());
                                        cont_parametros2++;
                                    }else if(cont_parametros2==3){
                                        elemento2.setAncho(Integer.parseInt(result.valor.toString()));
                                        cont_parametros2++;
                                    }else if(cont_parametros2==4){
                                        elemento2.setAlto(Integer.parseInt(result.valor.toString()));
                                        Principal.pintar.agregarQuadrate(elemento2.getX(),elemento2.getY(),elemento2.getColor(),elemento2.getAncho(),elemento2.getAlto());
                                        cont_parametros2++;
                                    }else{
                                        System.out.println("Error inesperado");
                                    }
                                }catch(Exception e){
                                    System.out.println("ocurrio un error en la parte semantica del analizador");
                                }
                            }
                        }
                    } catch (Exception e) {
                        Principal.reporteError.agregar("Ejecucion", sentencia.linea, sentencia.columna, "Ocurrio un error pintando el cuadrado:"+e.getMessage());
                    }
                    
                    break;
                case "pintarovalo":
                    try {
                        lista_expr = sentencia.hijos.get(0);
                        Elemento elemento3=new Elemento();
                        int cont_parametros3=0;
                        for (Nodo expr : lista_expr.hijos) {
                            opL = new Operacion_ALR(global, tabla);
                            result = opL.operar(expr);
                            if(result.valor!=null){
                                try{
                                    if(cont_parametros3==0){
                                        elemento3.setX(Integer.parseInt(result.valor.toString()));
                                        cont_parametros3++;
                                    }else if(cont_parametros3==1){
                                        elemento3.setY(Integer.parseInt(result.valor.toString()));
                                        cont_parametros3++;
                                    }else if(cont_parametros3==2){
                                        elemento3.setColor(result.valor.toString());
                                        cont_parametros3++;
                                    }else if(cont_parametros3==3){
                                        elemento3.setAncho(Integer.parseInt(result.valor.toString()));
                                        cont_parametros3++;
                                    }else if(cont_parametros3==4){
                                        elemento3.setAlto(Integer.parseInt(result.valor.toString()));
                                        Principal.pintar.agregarOval(elemento3.getX(),elemento3.getY(),elemento3.getColor(),elemento3.getAncho(),elemento3.getAlto());
                                        cont_parametros3++;
                                    }else{
                                        System.out.println("Ocurrio un error");
                                    }
                                }catch(Exception e){
                                    System.out.println("error semantico");
                                }
                            }
                        }
                    } catch (Exception e) {
                        Principal.reporteError.agregar("Ejecucion", sentencia.linea, sentencia.columna, "Ocurrio un error pintando ovalo:"+e.getMessage());
                    }
                    
                    break;
                case "pintarcadena":
                    try {
                        lista_expr = sentencia.hijos.get(0);
                        Elemento elemento4=new Elemento();
                        int cont_parametros4=0;
                        for (Nodo expr : lista_expr.hijos) {
                            opL = new Operacion_ALR(global, tabla);
                            result = opL.operar(expr);
                            if(result.valor!=null){
                                try{
                                    if(cont_parametros4==0){
                                        elemento4.setX(Integer.parseInt(result.valor.toString()));
                                        cont_parametros4++;
                                    }else if(cont_parametros4==1){
                                        elemento4.setY(Integer.parseInt(result.valor.toString()));
                                        cont_parametros4++;
                                    }else if(cont_parametros4==2){
                                        elemento4.setColor(result.valor.toString());
                                        cont_parametros4++;
                                    }else if(cont_parametros4==3){
                                        elemento4.setCadena(result.valor.toString());
                                        Principal.pintar.agregarString(elemento4.getX(),elemento4.getY(),elemento4.getColor(),elemento4.getCadena());
                                        cont_parametros4++;
                                    }else{
                                        //termino la ejecucion
                                    }
                                }catch(Exception e){
                                    System.out.println("error semantico");
                                }
                            }
                        }
                    } catch (Exception e) {
                        Principal.reporteError.agregar("Ejecucion", sentencia.linea, sentencia.columna, "Ocurrio un error en pintar Cadena:"+e.getMessage());
                    }
                    
                    break;  
                
                case "si":
                    try {
                        Si si = new Si();
                        metodoActual = si.ejecutar(sentencia);
                        if (metodoActual.estadoTerminar) {
                            //metodoActual.estadoTerminar=false;
                            return metodoActual;
                        }
                        if (metodoActual.estadoContinuar) {
                            return metodoActual;
                        }
                    } catch (Exception e) {
                        Principal.reporteError.agregar("Ejecucion", sentencia.linea, sentencia.columna, "Error en SI:"+e.getMessage());
                    }
                    
                    break;
                        
            }
        }
        return metodoActual;
    }
    public void OrdenarNodos(){
        int[] array=new int[10];
         for (int i = 0; i < array.length / 2; i++) {
            int tmp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = tmp;
        }

        /**
         * first k element reverse
         */
        int k=0;
        for (int i = 0; i < k / 2; i++) {
            int tmp = array[i];
            array[i] = array[k - 1 - i];
            array[k - 1 - i] = tmp;
        }

        /**
         * last length - k element reverse
         */

        for (int i = k; i < k + (array.length - k ) / 2; i ++) {
            int tmp = array[i];
            array[i] = array[array.length - 1 - i + k];
            array[array.length - 1 - i + k] = tmp;
}
        
    
    } 
    
     public Object ValidarArreglo(ArrayList dim, Nodo posiciones, String nombreArreglo, String tipo, String nombreFuncion,
            ArrayList<ArrayList<ControlValor>> variables) {

        ControlValor v = new ControlValor();
        if (!"error".equals(v.tipo)) {
            ArrayList pos = (ArrayList) v.valor;
            if (pos.size() == Integer.parseInt(dim.get(0).toString())) {
                if (dim.size() == 1) {
                    ControlValor v2 = new ControlValor(pos, "");
                    return v2;
                } else {
                    Boolean que = ContarPosiciones(dim, pos, nombreArreglo);
                    if (que) {
                        ControlValor vv =new ControlValor();
                        ControlValor v2 = new ControlValor(vv.valor, "");
                        return v2;
                    } else {
                        ControlValor v2 = new ControlValor("", "error");
                        return v2;
                    }
                }
            } else {
                ControlValor v2 = new ControlValor("", "error");
                return v2;
            }
        } else {
            ControlValor v2 = new ControlValor("", "error");
            return v2;
        }

    }

    public Object ExtraerPosiciones(Nodo posiciones, ArrayList dim, String nombreFuncion, String nombreArreglo, String tipo) {
        ArrayList nuevo = new ArrayList();
        for (Nodo c : posiciones.hijos) {
            if (c.valor.toString().equals("Posiciones")) {
                ControlValor v =new ControlValor();
                if (!"error".equals(v.tipo)) {
                    ArrayList a = (ArrayList) v.valor;
                    nuevo.add(a);
                } else {
                    ControlValor v2 = new ControlValor("", "error");
                    return v2;
                }
            } else {
                ControlValor v = new ControlValor();
                if (v != null) {
                    if (v.valor != null) {
                        if (!"error".equals(v.tipo)) {
                            if (v.tipo.equals(tipo)) {
                                nuevo.add(v.valor);
                            } else {
                                ControlValor v2 = new ControlValor("", "error");
                                return v2;
                            }
                        } else {
                            ControlValor v2 = new ControlValor("", "error");
                            return v2;
                        }
                    } else {
                        ControlValor v2 = new ControlValor("", "error");
                        return v2;
                    }
                } else {
                    ControlValor v2 = new ControlValor("", "error");
                    return v2;
                }
            }
        }
        ControlValor v = new ControlValor(nuevo, "");
        return v;
    }
    ArrayList nuevoP = new ArrayList();

    public Object Posiciones(Nodo posiciones, ArrayList dim, String nombreFuncion, String nombreArreglo, String tipo) {

        for (Nodo c : posiciones.hijos) {
            if (c.valor.toString().equals("Posiciones")) {
                ControlValor v=new ControlValor("","Posiciones");
            } else {
                ControlValor v = new ControlValor();
                if (v != null) {
                    if (v.valor != null) {
                        if (!"error".equals(v.tipo)) {
                            if (v.tipo.equals(tipo)) {
                                nuevoP.add(v.valor);
                            }
                        }
                    }
                }
            }
        }
        ControlValor v = new ControlValor(nuevoP, "");
        return v;
    }

    public Object InicializarArreglo(ArrayList dim, int ii) {
        int mult = 1;
        for (int i = 0; i < dim.size(); i++) {
            mult = mult * Integer.parseInt(dim.get(i).toString());
        }
        ArrayList nuevo = new ArrayList();
        for (int i = 0; i < mult; i++) {
            nuevo.add("nulo");
        }
        ControlValor v = new ControlValor(nuevo, "");
        return v;
    }

    int cont = 1;
    Boolean vf = false;

    public Boolean ContarPosiciones(ArrayList dim, ArrayList pos, String nombre) {
        if (cont == dim.size()) {
            return null;
        }
        for (Object pp : pos) {
            try {
                ArrayList p = (ArrayList) pp;
                if (p.size() == Integer.parseInt(dim.get(cont).toString())) {
                    vf = true;
                    for (int i = 0; i < p.size(); i++) {
                        cont++;
                        try {
                            ArrayList a = (ArrayList) p.get(i);
                            ContarPosiciones(dim, a, nombre);
                            cont--;
                        } catch (Exception e) {
                            if (Integer.parseInt(dim.get(cont - 1).toString()) == p.size()) {
                                vf = true;
                            } else {
                                vf = false;
                            }
                        }
                    }
                } else {
                    vf = false;
                    break;
                }
            } catch (Exception e) {
                if (Integer.parseInt(dim.get(cont).toString()) == pos.size()) {
                    vf = true;
                } else {
                    vf = false;
                }

            }
        }

        return vf;
    }

    //devolvemos un valor en la posicion
    public Object BuscarPosicionYdevolverValor(ArrayList dim, ArrayList pos, String nombreA) {

        ControlValor v2 =new ControlValor();
        if (!"error".equals(v2.tipo)) {
            ControlValor existe = new ControlValor();
            ControlValor var = new ControlValor();
            if (dim.size() == 1) {
                if (pos.size() == 1) {
                    ArrayList a = (ArrayList) var.valor;
                    int num = Integer.parseInt(a.get(Integer.parseInt(pos.get(0).toString())).toString());
                    ControlValor v = new ControlValor(num, var.tipo);
                    return v;
                } else {
                    ControlValor v = new ControlValor("", "error");
                    return v;
                }
            } else if (dim.size() == pos.size()) {
                int in1 = Integer.parseInt(pos.get(0).toString());
                for (int i = 0; i < dim.size() - 1; i++) {
                    int mult = in1 * Integer.parseInt(dim.get(i + 1).toString());
                    int in2 = Integer.parseInt(pos.get(i + 1).toString());
                    int suma = mult + in2;
                    in1 = suma;
                }
                ArrayList bb = (ArrayList) var.valor;
                Object ob = bb.get(in1);
                if (ob.toString().equals("nulo")) {
                    ControlValor v = new ControlValor("", "error");
                    return v;
                } else {
                    ControlValor v = new ControlValor(ob, "");
                    return v;
                }
            } else {
                ControlValor v = new ControlValor("", "error");
                return v;
            }
        } else {
            ControlValor v = new ControlValor("", "error");
            return v;
        }
    }

    public Object ValidarAcceso(ArrayList dim, ArrayList pos, String nombre) {
        for (int i = 0; i < dim.size(); i++) {
            if (Integer.parseInt(pos.get(i).toString()) < Integer.parseInt(dim.get(i).toString())
                    && Integer.parseInt(pos.get(i).toString()) >= 0) {

            } else {
                ControlValor v = new ControlValor("", "error");
                return v;
            }
        }
        ControlValor v = new ControlValor("", "");
        return v;
    }

    public Object BuscarPosicion(ArrayList dim, ArrayList pos,String nombreA) {

        ControlValor v2 = new ControlValor();
        if (!"error".equals(v2.tipo)) {

            ControlValor existe = new ControlValor();
            ControlValor var = new ControlValor();
            if (dim.size() == 1) {
                if (pos.size() == 1) {
                    ArrayList a = (ArrayList) var.valor;
                    a.get(Integer.parseInt(pos.get(0).toString()));
                    ControlValor v = new ControlValor(pos.get(0), var.tipo);
                    return v;
                } else {
                    ControlValor v = new ControlValor("", "error");
                    return v;
                }
            } else if (dim.size() == pos.size()) {
                int in1 = Integer.parseInt(pos.get(0).toString());
                for (int i = 0; i < dim.size() - 1; i++) {
                    int mult = in1 * Integer.parseInt(dim.get(i + 1).toString());
                    int in2 = Integer.parseInt(pos.get(i + 1).toString());
                    int suma = mult + in2;
                    in1 = suma;
                }
                ControlValor v = new ControlValor(in1, "");
                return v;
            } else {
                ControlValor v = new ControlValor("", "error");
                return v;
            }
        } else {
            ControlValor v = new ControlValor("", "error");
            return v;
        }
    }
    
    public  String getTipo(Object objeto) {
        String tipo = objeto.getClass().getSimpleName();
        switch (tipo) {
            case "Boolean":
                return "booleano";
            case "String":
                return "cadena";
            case "Character":
                return "caracter";
            case "Integer":
                return "entero";
            case "Double":
                return "decimal";
            default:
                return tipo;
        }
    }
    
    public int getCharValor(Object objeto, Nodo raiz) {
        String valor = (char) objeto + "";
        try {
            return Integer.parseInt(valor);
        } catch (Exception e) {
            Principal.reporteError.agregar("Semantico", raiz.linea, raiz.columna, "No se pudo castear el caracter a tipo Bool");
            return -1;
        }
    }

    public int getStringValor(Object objeto, Nodo raiz) {
        String valor = (String) objeto;
        try {
            return Integer.parseInt(valor);
        } catch (Exception e) {
            Principal.reporteError.agregar("Semantico", raiz.linea, raiz.columna, "No se pudo castear la cadena a tipo Bool");
            return -1;
        }
    }
    
    public int getBoolValor(Object objeto) {
        Boolean valor = (Boolean) objeto;
        if (valor) {
            return 1;
        } else {
            return 0;
        }
    }
}
