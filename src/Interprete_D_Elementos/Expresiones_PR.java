/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete_D_Elementos;

import Estructuras_Ide.Ide_bloque;
import Estructuras_Ide.Nodo;
import Estructuras_Ide.Resultado;
import jdk.nashorn.internal.ir.Block;

/**
 *
 * @author antonio
 */
public class Expresiones_PR extends Ejecucion_D.Interpretacion{
    public void Imprimir(Nodo RAIZ)
    {
        String codigo_tmp = "";
        String codigo_2 = "";
        XopL = new Expresiones_Logicas();
        
        Ide_bloque last = Xblockes.obtener_UltimoBlock();
        
        Xblockes.agregar("\n", RAIZ);
        Resultado r1 = XopL.OPERAR(RAIZ.hijos.get(0));
        
        Ide_bloque last2 = Xblockes.obtener_UltimoBlock();
        
        if(r1.tipo.equals("cadena"))
        {
            if(r1.ref)
            {
                codigo_tmp = "";
                codigo_tmp +=  "get_global $calc//****\n\n\n";
                last2.add(codigo_tmp);
                
                
                
                codigo_tmp = "get_local 0//paso parametro\n";
                codigo_tmp += De$pl4z4r()+"\n";
                codigo_tmp += "add\n";
                codigo_tmp += "1\n";
                codigo_tmp += "add\n";
                
                
                
                last2.agregarAl_Inicio(codigo_tmp);
                
                codigo_tmp = "set_local $calc\n";
                
                codigo_tmp += "//LLAMANDO A IMPRIMIR\n";
                codigo_tmp += "\n\n\n0//Cambio de ambito\n";
                codigo_tmp += "get_local 0\n";
                codigo_tmp += De$pl4z4r()+"\n";
                codigo_tmp += "add\n";
                codigo_tmp += "set_local $calc\n";
                
                
                
                
                codigo_tmp += "call $$_outStr\n";
                
                
                codigo_tmp += "0//regreso de ambtio\n";
                codigo_tmp += "get_local 0\n";
                codigo_tmp += De$pl4z4r()+"\n";
                codigo_tmp += "diff\n";
                codigo_tmp += "set_local $calc\n";
                
                last2.add(codigo_tmp);
            }else
            {
                codigo_tmp = "//LLAMANDO A IMPRIMIR\n";
                codigo_tmp += "get_local 0\n";
                codigo_tmp += De$pl4z4r()+"\n";
                codigo_tmp += "add\n";
                codigo_tmp += "1\n";
                codigo_tmp += "add\n";
                
                
                last.add(codigo_tmp);
                
                
                codigo_tmp = "set_local $calc\n";
                
                codigo_tmp += "0\n";
                codigo_tmp += "get_local 0\n";
                codigo_tmp += De$pl4z4r()+"\n";
                codigo_tmp += "add\n";
                codigo_tmp += "set_local $calc\n\n";
                
                
                codigo_tmp += "call $$_outStr\n";

                codigo_tmp += "0\n";
                codigo_tmp += "get_local 0\n";
                codigo_tmp += De$pl4z4r()+"\n";
                codigo_tmp += "diff\n";
                codigo_tmp += "set_local $calc\n\n";
                
                Xblockes.agregar_AlUltimoBloque(codigo_tmp);
            }
        }
    }
}