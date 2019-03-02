/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete_D_Elementos;

/**
 *
 * @author anton
 */

public class ControlC3D {
    
    private static int temp = 0;
    private static String c3d = "";

    public static void reiniciar(){
        temp = 0;
        c3d = "";
    }
    
    public static String generaTemp(){
        return "t$"+temp++;
    }
    
    public static void agregarC3D(String sentencia){
        c3d += sentencia;
    }

    public static String getC3D(){
        return c3d;
    }
    
}