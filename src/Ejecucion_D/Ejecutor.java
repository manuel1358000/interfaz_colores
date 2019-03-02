/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejecucion_D;

import Estructuras_Ide.Nodo;
import Gramatica_Pila.LexicoDasm;
import Gramatica_Pila.PilaGenerica;
import Gramatica_Pila.SintacticaDasm;
import Administrador_Errores.Errores;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import proyecto2_compi2.Mostrar_Stack;

/**
 *
 * @author antonio
 */
public class Ejecutor {
    boolean ya=false;
    String MI_SALIDA = "";
    Nodo raizX = null;
    String archivo ="";
    public static  Errores Lista_Errores = new Errores();
    Nodo metodo_actual =null;
    public static PilaGenerica pila=new PilaGenerica();
    
    public void ejecutar_dasm(String nombre) throws FileNotFoundException
    {
        LexicoDasm lex = new LexicoDasm(new FileReader(nombre));//se le pasa al analizador lexico lo que se escribio
        SintacticaDasm parser = new SintacticaDasm(lex);
        Nodo RAIZ=null;
        
        try {
            parser.parse();
            Nodo raiz = parser.dema_Raiz();
            if(raiz!=null){
                RAIZ  = raiz;
                raizX = raiz;    
            }
        } catch (Exception e) {
            System.out.println("error DASM: "+e.getMessage());
        }
        if(RAIZ!=null)
        {
            EJECUCION_PRINCIPAL(RAIZ);
            
            pila.print(1);
            pila.print(2);
            pila.print(3);
            System.out.println("=====================================");
            System.out.println(MI_SALIDA);
        }
    }
    
    public void EJECUCION_PRINCIPAL(Nodo raiz)
    {
        Nodo metodo_principal = getPrincipal(raiz);
        if(metodo_principal!=null)
        {
            metodo_actual = metodo_principal;
            ejecutar(metodo_actual.hijos.get(0));
            mostrarGrafico("");
        }else 
        {
            
            System.out.println("No existe el metodo principal");
        }
    }
    
    
    public void ejecutar(Nodo sentencias)
    {
        
        for (int x =0; x< sentencias.hijos.size();x++) {
            Nodo sentencia = sentencias.hijos.get(x);
            //if(metodo_actual.valor.equalsIgnoreCase("$$_outStr"))
            //{
                //JOptionPane.showMessageDialog(null, sentencia.nombre);
            //}
            
            if(ya)
            {
                mostrarGrafico("Antes:  "+sentencia.nombre);
                //System.out.println("|"+sentencia.nombre+"|");
            
            }
            switch(sentencia.nombre)
            {   
                case "nada":
                    x = sentencias.hijos.size()-1;
                    break;
                case "f_char":
                    pila.push(1.0,3);
                    break;
                case "set_global":
                    try {
                        set_global(sentencia);
                    } catch (Exception e) {
                        
                    }
                    
                    break;
                case "get_global":
                    try {
                        get_global(sentencia);
                    } catch (Exception e) {
                        
                    }
                    break;    
                case "set_local":
                    try {
                        //mostrarGrafico();
                        set_local(sentencia);
                        //mostrarGrafico();
                    } catch (Exception e) {
                    }
                    
                    break;
                case "get_local":
                    try {
                        get_local(sentencia);
                    } catch (Exception e) {
                        
                    }
                    break;        
                case "br":
                    x = saltar_a_etiqueta(sentencia.valor);
                    //JOptionPane.showMessageDialog(null, "Salto br");
                    System.out.println("\n");
                    break;
                case "br_if":
                    if(pila.pop(3)==0.0)
                    {
                       x = saltar_a_etiqueta(sentencia.valor);
                    }
                    break;    
                case "print":
                    try {
                        Print();
                    } catch (Exception e) {
                    }
                    break;
                
                case "unario":
                    pila.push(Double.valueOf(sentencia.valor)*-1,3);
                    break;
                
                case "numero":
                    pila.push(Double.valueOf(sentencia.valor),3);
                    break;
                case "decimal":
                    pila.push(Double.valueOf(sentencia.valor),3);
                    break;    
                case "add":
                case "diff":
                case "mult":
                case "div":
                case "pot":
                case "mod":    
                    operaciones_aritmeticas(sentencia.nombre,sentencia);
                    break;
                case "lt":
                case "lte":    
                case "gt":
                case "gte":    
                case "eqz":
                    operaciones_relacionales(sentencia.nombre,sentencia);
                    break;    
                case "and":
                case "not":
                case "or":
                    operaciones_logicas(sentencia.nombre,sentencia);
                    break;
                case "llamada":
                    Nodo metodoAux =  metodo_actual;
                    Nodo metodo = getMetodo(sentencia.valor);
                    metodo_actual = metodo;
                    
                    switch(metodo.valor)
                    {
                        case "$$_outStr":
                            //heap.imprimir();
                            //stack.imprimir();
                            JOptionPane.showMessageDialog(null, "LLAMANDO A:"+metodo.valor);
                            mostrarGrafico("");
                            ejecutar(metodo.hijos.get(0));
                            JOptionPane.showMessageDialog(null, "FINALIZA LLAMADA A:"+metodo.valor);
                            mostrarGrafico("");

                            //mostrarGrafico();
                            //JOptionPane.showMessageDialog(null, "HE SALIDO");
                            break;
                        default:
                            //heap.imprimir();
                            //stack.imprimir();
                            
                            
                            
                            
                            
                            
                            JOptionPane.showMessageDialog(null, "LLAMANDO A:"+metodo.valor);
                            mostrarGrafico("");
                            ejecutar(metodo.hijos.get(0));
                            JOptionPane.showMessageDialog(null, "FINALIZA LLAMADA A:"+metodo.valor);
                            mostrarGrafico("");
                            if(metodo_actual.valor.equalsIgnoreCase("struct_casa"))
                            {
                                ya=false;
                            }
                            
                            break;
                    }
                    metodo_actual =metodoAux;
                    break;
            }
            if(ya)
            {
                mostrarGrafico("Despues:  "+sentencia.nombre);
            }
            
        }
    }
    
    public void mostrarGrafico(String title)
    {
        
        JPanel pan = new JPanel();
        for (int i = 0; i < 3; i++) {
            JTextArea area = new JTextArea(30,10);
            if(i==0)
            {
                
                area.setText("STACK\n"+pila.print(1));
                area.setBackground(Color.WHITE);
            }else if(i==1)
            {
                area.setText("HEAP\n"+pila.print(2));
                area.setBackground(Color.white);
            }else
            {
                area.setText("PILA_AUXILIAR\n"+pila.print(3));
            }
            
            JScrollPane scroll = new JScrollPane(area);
            pan.add(scroll);
        }
        
        
        JOptionPane.showMessageDialog(null,pan, title, JOptionPane.INFORMATION_MESSAGE);
        
    }
    public void Print()
    {
        Double v1,v2;
        v1=pila.pop(3);
        v2=pila.pop(3);
        
        //mostrarGrafico();
        
        MI_SALIDA+=Character.toString((char)v1.intValue());
        JOptionPane.showMessageDialog(null, MI_SALIDA);
        //System.out.println("\t\t\t\t<"+Character.toString((char)v1.intValue())+">");
    }
    
    
    public void set_local(Nodo raiz)
    {
        Double direccion,valor;
        Nodo hijo = raiz.hijos.get(0);
        if(hijo.nombre.equals("calc"))
        {
            valor     = pila.pop(3);
            direccion = pila.pop(3);
            pila.set(direccion, valor,1);
        }else if(hijo.nombre.equals("numero"))
        {   
            valor     = pila.pop(3);
            direccion = Double.valueOf(hijo.valor);
            pila.set(direccion, valor,1);
        }else if(hijo.nombre.equals("ret"))
        {
            valor     = pila.pop(3);
            direccion = pila.get(0.0,1) + 0;
            pila.set(direccion, valor,1);
        }
    }
    
    public void set_global(Nodo raiz)
    {
        Double direccion,valor;
        Nodo hijo = raiz.hijos.get(0);
        if(hijo.nombre.equals("calc"))
        {
            valor     = pila.pop(3);
            direccion = pila.pop(3);
            pila.set(direccion, valor,1);
        }else if(hijo.nombre.equals("numero"))
        {
            valor     = pila.pop(3);
            direccion = Double.valueOf(hijo.valor);
            pila.set(direccion, valor,1);
        }
    }
    
    public void get_local(Nodo raiz)
    {
        Double direccion,valor;
        Nodo hijo = raiz.hijos.get(0);
        if(hijo.nombre.equals("calc"))
        {
            direccion = pila.pop(3);
            valor     = pila.get(direccion,1);
            pila.push(valor,3);
        }else if(hijo.nombre.equals("numero"))
        {
            direccion = Double.valueOf(hijo.valor);
            valor     = pila.get(direccion,1);
            pila.push(valor,3);
        }else if(hijo.nombre.equals("ret"))
        {
            direccion = pila.get(0.0,1) + 0;
            valor     = pila.get(direccion,1);
            pila.push(valor,3);
        }
    }
    
    public void get_global(Nodo raiz)
    {
        Double direccion,valor;
        Nodo hijo = raiz.hijos.get(0);
        if(hijo.nombre.equals("calc"))
        {
            direccion = pila.pop(3);
            valor     = pila.get(direccion,2);
            pila.push(valor,3);
        }else if(hijo.nombre.equals("numero"))
        {
            direccion = Double.valueOf(hijo.valor);
            valor     = pila.get(direccion,2);
            pila.push(valor,3);
        }else 
        {
            
        }
    }
    
    public int saltar_a_etiqueta(String etiqueta)
    {
        for (int i = 0; i < metodo_actual.hijos.get(0).hijos.size(); i++) {
            Nodo sentencia = metodo_actual.hijos.get(0).hijos.get(i);
            switch(sentencia.nombre)
            {
                case "etiqueta":
                    if(sentencia.valor.equals(etiqueta))
                    {
                        return i;
                    }
                    break;
                default:
                    break;
            }
        }
        return metodo_actual.hijos.get(0).hijos.size()-1;
    }
    public void operaciones_aritmeticas(String tipo,Nodo raiz)
    {
        Double v1,v2;
        switch(tipo)
        {
            
            case "add":
                v1 = pila.pop(3);
                v2 = pila.pop(3);
                pila.push(v2+v1,3);
                break;
            case "diff":
                v1 = pila.pop(3);
                v2 = pila.pop(3);
                pila.push(v2-v1,3);
                break;
            case "mult":
                v1 = pila.pop(3);
                v2 = pila.pop(3);
                pila.push(v2*v1,3);
                break;    
            case "div":
                v1 = pila.pop(3);
                v2 = pila.pop(3);
                if(v1!=0.0)
                {
                    pila.push(v2/v1,3);
                }else
                {
                    Lista_Errores.add(raiz.linea, raiz.columna,"Ejecucion","Error en ejecutor DASM division entre 0 invalida",archivo);
                }
                
                break;
            case "pot":
                v1 = pila.pop(3);
                v2 = pila.pop(3);
                pila.push(Math.pow(v2, v1),3);
                break;
            case "mod":
                v1 = pila.pop(3);
                v2 = pila.pop(3);
                pila.push(v2%v1,3);
                break;    
        }
    }
    
    public void operaciones_relacionales(String tipo,Nodo raiz)
    {
        Double v1,v2;
        switch(tipo)
        {
            case "lt":
                v1 = pila.pop(3);
                v2 = pila.pop(3);
                if(v2 < v1)
                {
                    pila.push(1.0,3);
                }else
                {
                    pila.push(0.0,3);
                }
                break;
            case "lte":
                v1 = pila.pop(3);
                v2 = pila.pop(3);
                if(v2 <= v1)
                {
                    pila.push(1.0,3);
                }else
                {
                    pila.push(0.0,3);
                }
                break;  
            case "gt":
                v1 = pila.pop(3);
                v2 = pila.pop(3);
                if(v2 > v1)
                {
                    pila.push(1.0,3);
                }else
                {
                    pila.push(0.0,3);
                }
                break;
            case "gte":
                v1 = pila.pop(3);
                v2 = pila.pop(3);
                if(v2 >= v1)
                {
                    pila.push(1.0,3);
                }else
                {
                    pila.push(0.0,3);
                }
                break;
            case "eqz":
                v1 = pila.pop(3);
                
                if(v1 == 0.0)
                {
                    pila.push(1.0,3);
                }else
                {
                    pila.push(0.0,3);
                }
                break;    
        }
    }
    
    
    public void operaciones_logicas(String tipo,Nodo raiz)
    {
        Double v1,v2;
        switch(tipo)
        {
            case "and":
                v1 = pila.pop(3);
                v2 = pila.pop(3);
                if(v1.intValue() >= 1 && v2.intValue() >= 1)
                {
                    pila.push(1.0,3);
                }else
                {
                    pila.push(0.0,3);
                }
                break;
            case "or":
                v1 = pila.pop(3);
                v2 = pila.pop(3);
                if(v1.intValue() >= 1 || v2.intValue() >= 1)
                {
                    pila.push(1.0,3);
                }else
                {
                    pila.push(0.0,3);
                }
            case "not":
                v1 = pila.pop(3);
                if(v1.intValue() >= 1)
                {
                    pila.push(0.0,3);
                }else
                {
                    pila.push(1.0,3);
                }
                break;    
            
        }
    }
    
    
    
    public Nodo getPrincipal(Nodo raiz)
    {
        for (Nodo hijo : raiz.hijos) {
            if(hijo.valor.equalsIgnoreCase("principal"))
            {
                return hijo;
            }
        }
        return null;
    }
    
    public Nodo getMetodo(String nombre)
    {
        for (Nodo hijo : raizX.hijos) {
            if(hijo.valor.equalsIgnoreCase(nombre))
            {
                return hijo;
            }
        }
        return null;
    }
    
    public void escribir(String direccion,String texto) {
        //metodo que guarda lo que esta escrito en un archivo de texto
        try {
            FileWriter writer = new FileWriter(direccion);
            PrintWriter print = new PrintWriter(writer);
            print.print(texto);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
