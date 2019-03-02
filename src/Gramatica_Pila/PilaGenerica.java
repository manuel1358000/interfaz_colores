/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gramatica_Pila;

import java.util.ArrayList;

/**
 *
 * @author anton
 */
public class PilaGenerica {
    public int id_stack= 0;
    public int id_heap= 0;
    public int id_auxiliar= 0;
    public static  ArrayList<Double> heap = new ArrayList<>();
    public static ArrayList<Double> stack = new ArrayList<>();
    public static  ArrayList<Double> pilaaux = new ArrayList<>();

    public static ArrayList<Double> getHeap() {
        return heap;
    }

    public static void setHeap(ArrayList<Double> heap) {
        PilaGenerica.heap = heap;
    }

    public static ArrayList<Double> getStack() {
        return stack;
    }

    public static void setStack(ArrayList<Double> stack) {
        PilaGenerica.stack = stack;
    }

    public static ArrayList<Double> getPilaaux() {
        return pilaaux;
    }

    public static void setPilaaux(ArrayList<Double> pilaaux) {
        PilaGenerica.pilaaux = pilaaux;
    }

    
        
    public PilaGenerica() {
        for (int i = 0; i < 10000; i++) {
            heap.add(null);
        }
        set(0.0,1.0,2);
        
        for (int i = 0; i < 10000; i++) {
            stack.add(null);
        }
        set(0.0,1.0,1);
    }
    
    public Double pop(int numero){
        if(numero==1){
            if(!stack.isEmpty()){   
                Double ret = stack.remove(stack.size()-1);
                System.out.println("Stack hizo pop->"+ret);
                return ret;
            }else{
                System.out.println("Ocurrio un error intentando hacer pop a stack");     
                return null;
            }
        }else if(numero==2){
            if(!heap.isEmpty()){   
                Double ret = heap.remove(heap.size()-1);
                System.out.println("heap hizo pop->"+ret);
                return ret;
            }else{
                System.out.println("Ocurrio un error intentando hacer pop a heap");     
                return null;
            }
        }else{
            if(!pilaaux.isEmpty()){   
                Double ret = pilaaux.remove(pilaaux.size()-1);
                System.out.println("Auxiliar hizo pop->"+ret);
                return ret;
            }else{
                System.out.println("Ocurrio un error intentando hacer pop a auxiliar");     
                return null;
            }
        }
    }
    public void push(Double v,int numero)
    {
        if(numero==1){
            System.out.println("Stack se hizo un push->"+v);
            stack.add(v);
        }else if(numero==2){
            System.out.println("Heap se hizo un push->"+v);
            heap.add(v);
        }else if(numero==3){
            System.out.println("Auxiliar se hizo un push->"+v);
            pilaaux.add(v);
            System.out.println("");
        }
    }
    public Double get(Double i, int numero)
    {
        if(numero ==1)
        {
            if(!stack.isEmpty() && i<stack.size())
            {   
                Double ret = stack.get(i.intValue());
                System.out.println("Get de Stack->"+ret);
                return ret;
            }else
            {
                System.out.println("Error al obtener en Stack get("+i+")");
                return null;
            }
        }else if(numero==2)
        {
            if(!heap.isEmpty() && i<heap.size())
            {   
                Double ret = heap.get(i.intValue());
                System.out.println("Get de Heap->"+ret);
                return ret;
            }else
            {
                System.out.println("Error al obtener en Heap get("+i+")");
                return null;
            }
        }else if(numero==3)
        {
            if(!pilaaux.isEmpty() && i<pilaaux.size())
            {   
                Double ret = pilaaux.get(i.intValue());
                System.out.println("Get de pilaaux ->"+ret);
                return ret;
            }else
            {
                System.out.println("Error al obtener en Spilaauxtack get("+i+")");
                return null;
            }
        }else{
            return null;
        }
        
    }
    
    public void set(Double i, Double v,int numero)
    {
        ArrayList<Double> aux  = null;
        
        if(numero==1)//stack
        {
            try {
                System.out.println("En stack set ->"+i+","+v);
                stack.set(i.intValue(), v);
            if(i.intValue()> id_stack)
            {
                id_stack = i.intValue();
            }
            } catch (Exception e) {
                System.out.println("Error en Stack al setear("+i+","+v+")");
            }
        }else if(numero==2)//heap
        {
            try {
                System.out.println("En heap set ->"+i+","+v);
                heap.set(i.intValue(), v);
            if(i.intValue()> id_heap)
            {
                id_heap = i.intValue();
            }
            } catch (Exception e) {
                System.out.println("Error en heap al setear("+i+","+v+")");
            }
        }else if(numero==3)
        {
            try {
                System.out.println("Set en pila aux->"+i+","+v);
                pilaaux.set(i.intValue(), v);
                if(i.intValue()> id_auxiliar)
                {
                    id_auxiliar = i.intValue();
                }
            } catch (Exception e) {
                System.out.println("Error al Set en pila aux ("+i+","+v+")");
            }
        }
        
    }
    
    public String print(int numero)
    {
        String cad="";

        if(numero==1)
        {
            System.out.println(">-------------------Imprimiendo Stack--------------------<");
            for (int i = id_stack; i >= 0 ; i--) {
                if(this.get((double)i,1)!=null)
                {
                    cad += "["+this.get((double)i,1)+"]{POS->"+i+"}\n"; 
                    System.out.println("["+this.get((double)i,1)+"]");
                }else
                {
                    cad += "[]{POS->"+i+"}\n";
                    System.out.println("[]");
                }
            }
        }else if(numero==2)
        {   
            System.out.println(">=====================Imprimiendo Heap=====================<");
            for (int i = id_heap; i >= 0 ; i--) {
                if(this.get((double)i,2)!=null)
                {
                    cad += "["+this.get((double)i,2)+"]{POS->"+i+"}\n"; 
                    System.out.println("["+this.get((double)i,2)+"]");
                }else
                {
                    cad += "[]{POS->"+i+"}\n";
                    System.out.println("[]");
                }
            }
        }else if(numero==3)
        {
            System.out.println(">=====================Imprimiendo Pila Aux =====================<");
            for (int i = pilaaux.size()-1; i >= 0 ; i--) {
                if(this.get((double)i,3)!=null)
                {
                    cad += "["+this.get((double)i,3)+"]{POS->"+i+"}\n"; 
                    System.out.println("["+this.get((double)i,3)+"]");
                }else
                {
                    cad += "[]{POS->"+i+"}\n";
                    System.out.println("[]");
                }
            }
        }
        
        return cad;
    }
}
