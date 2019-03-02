/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos_Draco;

import org.fife.ui.rtextarea.GutterIconInfo;

/**
 *
 * @author anton
 */
public class Debugger {
    
    
    int linea;
    GutterIconInfo info;

    public Debugger(int linea, GutterIconInfo info) {
        this.linea = linea;
        this.info = info;
    }

    public Debugger() {
        this.linea=-1;
        this.info=null;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public GutterIconInfo getInfo() {
        return info;
    }

    public void setInfo(GutterIconInfo info) {
        this.info = info;
    }
    
    
    
}
