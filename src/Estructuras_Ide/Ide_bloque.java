/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras_Ide;

import java.util.ArrayList;

/**
 *
 * @author antonio
 */
public class Ide_bloque{
    public int linea = 0;
    public int columna = 0;
    public String codigo_asm = "";
    public ArrayList<String> lineas = new ArrayList<>();

    public Ide_bloque() {
    }
    
    public Ide_bloque(int linea, int columna, String codigo_asm)
    {
        this.linea = linea;
        this.columna = columna;
        add(codigo_asm);
    }
    
    public void add(String codigo)
    {
        String []lt = codigo.split("\n");
        for (int i = 0; i < lt.length; i++) {
            lineas.add(lt[i]);
        }
    }
    
    
    public void intercalar(int senia, String codigo)
    {
        try {
            lineas.set(senia,codigo+lineas.get(senia));
        } catch (Exception e) {
            System.out.println("no se puedo intercalar:"+e.getMessage());
        }
        
    }
    
    public void intercalarF(int senia, String codigo)
    {
        try {
            lineas.set(senia,lineas.get(senia)+codigo);
        } catch (Exception e) {
        }
        
    }
    public int Hacer_Senia()
    {
        lineas.add("");
        return this.lineas.size()-1;
    }
    
    
    
    public void agregarAl_Inicio(String cod)
    {
        if(lineas.size()>0)
        {
            String dat = lineas.get(0);
            dat = cod + dat;
            lineas.set(0, dat);
        }
    }
    
}
