/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejecucion_D;

import Gramatica_D.Analizador_LD;
import Gramatica_D.Analizador_SD;
import Estructuras_Ide.Tree;
import Estructuras_Ide.Ide_Bloques;
import Estructuras_Ide.Nodo;
import Estructuras_Ide.Resultado;
import Estructuras_Ide.Simbolo;
import Estructuras_Ide.TablaSimbolo;
import Interprete_D_Elementos.Asignaciones_Variables;
import Interprete_D_Elementos.Declaraciones_Variables;
import Interprete_D_Elementos.Expresiones_Aritmeticas;
import Interprete_D_Elementos.Expresiones_Logicas;
import Interprete_D_Elementos.Expresiones_Relacionales;
import Interprete_D_Elementos.Expresiones_Genericas;
import Interprete_D_Elementos.Expresiones_PR;
import Interprete_D_Elementos.Casteos_Datos;
import Interprete_D_Elementos.Crear;
import Interprete_D_Elementos.Expresiones_PW;
import Administrador_Errores.Errores;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import proyecto2_compi2.Principal;
import static proyecto2_compi2.Principal.jTreeFiles;

/**
 *
 * @author fernando
 */
public class Interpretacion {
    public Nodo RAIX = null;
    public ArrayList<Nodo> metodos;
    public static ArrayList<Nodo> estructuras;
    public ArrayList<Nodo> sentencias_globales;
    public static String Archivo_Actual ="";
    public static boolean constructor = false;
    public static Simbolo Metodo_Actual = null;
    public static Control_Intermedio control = new Control_Intermedio();
    
    public static Expresiones_Logicas XopL;
    public static Stack<String> pilaEntradas = null;
    public static Stack<String> pilaSalidas = null;
    public static Declaraciones_Variables Xdeclaracion = new Declaraciones_Variables();
    public static Expresiones_Aritmeticas XopA;
    public static Expresiones_Relacionales XopR;
    public  static  Asignaciones_Variables  Xasignacion= new Asignaciones_Variables();
     public static Object      nulo  = 201213562;
    public static int Xnivel = 0 ;
    public  static  Expresiones_Genericas genericas = new Expresiones_Genericas();
    public  static  Expresiones_PR  Xprimitivas= new Expresiones_PR();
    
    
    public static  Errores Lista_Errores = new Errores();
    public static  TablaSimbolo tabla ;
    public static  ArrayList<String> ambito =  new ArrayList<>();
    public static  Expresiones_PW manejo_sentencias_control = null;
    public static Ide_Bloques Xblockes = new Ide_Bloques();
    public static Casteos_Datos Xcastear = new Casteos_Datos();
    public static Crear Xcrear = new Crear();
    
   
    
    public void iniciar_interpretacion(String path,String contenidos) throws FileNotFoundException, IOException
    {
        pilaEntradas = new Stack<>();
        pilaSalidas  = new Stack<>();
        manejo_sentencias_control = new Expresiones_PW();
        metodos = new ArrayList<>();
        estructuras = new ArrayList<>();
        sentencias_globales = new ArrayList<>();
        tabla = new TablaSimbolo();
        
        Analizador_LD lex = new Analizador_LD(new FileReader(path));//se le pasa al analizador lexico lo que se escribio
        Analizador_SD parser = new Analizador_SD(lex);
        Nodo RAIZ=null;
        Archivo_Actual = path;
        try {
            parser.parse();
            Nodo raiz = parser.getRoot();
            
            //Arbol_CCSS gen_arbol = new Arbol_CCSS();
            if(raiz!=null){
                //Arbol xx = new Arbol();
                //xx.generacion_arbol(raiz);
                System.out.println("RAIZ NO NULA");
                //gen_arbol.generacion_arbolCCSS(raiz);
            }
            RAIZ=raiz;
        } catch (Exception e) {
            System.out.println("error:"+e.getMessage());
        }
        
        
        if(RAIZ!=null)
        {
            RAIX =  RAIZ;
            tabla = new TablaSimbolo();
            tabla.generar_codigo(RAIZ);
            tabla.generar_reporte();
            
            for (Nodo nodo : RAIZ.hijos) {
                if(nodo.nombre.equals("metodo")  || nodo.nombre.equals("metodoArray"))
                {
                    metodos.add(nodo);
                }else if(nodo.nombre.equals("declaracion_estructura")  )
                {
                    estructuras.add(nodo);
                }else
                {
                    sentencias_globales.add(nodo);
                }
            }
            
        }
        
        constructor=true;
        Xcrear.crear_estructuras();
        constructor=false;
        
        this.codigo_Intemerdio_globales();//se genera el codigo intermedio para las globales
        
        ejecutar_metodos();
        
        Xblockes.agregar_AlUltimoBloque(genericas.OUT_STR());
        Xblockes.agregar_AlUltimoBloque(genericas.mayor());
        Xblockes.agregar_AlUltimoBloque(genericas.mayorIgual());
        Xblockes.agregar_AlUltimoBloque(genericas.menor());
        Xblockes.agregar_AlUltimoBloque(genericas.menorIgual());
        Xblockes.agregar_AlUltimoBloque(genericas.CasteoNumero());
        Xblockes.agregar_AlUltimoBloque(genericas.concat());
        
        
        //System.out.println("*****************************************");
        //System.out.println(control.codigoDASM);
        String codDASM=Xblockes.imprimirBlockes();
        //System.out.println("*****************************************");
        
        try {
            try{
                JFrame parentFrame = new JFrame();
                JFileChooser fileChooser = new JFileChooser("ruta");
                fileChooser.setDialogTitle("Specify a file to save");   
                int userSelection = fileChooser.showSaveDialog(parentFrame); 
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    escribir(codDASM,fileToSave.getAbsolutePath());
                    Principal.jTreeFiles.init();
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Ocurrio un error " +e.toString());
            }
            //JOptionPane.showMessageDialog(null, "A ejecutar cabron");
            
            //new Ejecutor().ejecutar_dasm();
        } catch (Exception e) {
            System.out.println("error al ejecutar");
        }
        
    }
    
    public void escribir(String texto, String ruta)
    {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(ruta);
            pw = new PrintWriter(fichero);
            
            pw.println(texto);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
    
    
    public void codigo_Intemerdio_globales()
    {
        Nodo raix = new Nodo(-1, "Lista_Globales", "", 0, 0);
        for (Nodo sent_globales : sentencias_globales) {
            raix.linea = sent_globales.linea;
            raix.columna = sent_globales.columna;
            raix.add(sent_globales);
        }
        
        if(!sentencias_globales.isEmpty())
        {
           

            this.ejecutarSentencias(raix);
            
            String codigo_tmp = "function $$_globales\n";
            Xblockes.agregar_A_Bloque_AlInicio(codigo_tmp, sentencias_globales.get(0));
            
            codigo_tmp   = "\n0\n";
            
            codigo_tmp   += "get_global 0\n";
            codigo_tmp   += De$pl4z4r()+"\n";
            codigo_tmp   += "add\n";
            
            codigo_tmp   += "set_global $calc\n";
            
            codigo_tmp   += "End\n\n\n";
            Xblockes.agregar_AlUltimoBloque(codigo_tmp);
        }else
        {
        }
        
    }
    
    public void ejecutar_metodos()
    {
        boolean existe_metodo_principal = false;
        for (Nodo metodo : metodos) {
            ambito =  new ArrayList<>();
            String id_metodo = tabla.generar_Id(metodo);
            Metodo_Actual = tabla.obtener_Simbolo(id_metodo, ambito);
            
            String etqRetorno = control.generar_etiqueta();
            
            if(id_metodo.toLowerCase().equals("principal"))
            {
                String codigo_tmp= "";
                codigo_tmp += "\n\nfunction principal\n";
                //codigo_tmp += "$$_globales();\n";
                
                Xblockes.agregar(codigo_tmp, metodo);
                
                ambito.add("principal");
                //ejecuto las sentencias del metodo
                ejecutarSentencias(metodo.hijos.get(3));
                
                codigo_tmp = etqRetorno+"//etiqueta retorno fncion\n";
                codigo_tmp += "End\n";
                Xblockes.agregar_AlUltimoBloque(codigo_tmp);
                
                //control.codigoDASM += "End\n";
                existe_metodo_principal = true;
                /*
                codigo3D+="void Principal(){\n";
					codigo3D+="$$_globales();\n";
					ambito.push("Principal");
					this.ejecutar(metodo.hijos[3]);
					codigo3D+=etiquetaReturn+":  ; \n";
					codigo3D+="}\n\n";
					existePrincipal=true;
                */
                ambito.remove(ambito.size()-1);
            }else
            {
                ambito.add(id_metodo);
                //declararParametros(metodo.hijos[2]);
                
                String codigo_tmp = "function "+id_metodo+"\n";
                Xblockes.agregar(codigo_tmp, metodo);

                if(id_metodo.equals("prueba"))
                {
                    int nada =0;
                    javax.swing.JOptionPane.showMessageDialog(null, "hola");
                }
                ejecutarSentencias(metodo.hijos.get(3));

                
                codigo_tmp = etqRetorno+"//etRetorno funcion\n";
                codigo_tmp += "End\n";
                Xblockes.agregar_AlUltimoBloque(codigo_tmp);
                
                
            }
            
        }  
        if(!existe_metodo_principal)
        {
            Ejecucion_D.Interpretacion.Lista_Errores.add(0, 0, "Semantico", "No se ha encontrado un metodo principal","");
        }
    }
    
    
    public boolean R_IS_NULL(Resultado t)
    {
        if(t==null)
        {
            return true;
        }else
        {
            if(t.valor==null)
            {
                return true;
            }else
            {
                return false;
            }
        }
    }
    public void ejecutarSentencias(Nodo RAIZ)
    {   
        for (Nodo sentencia : RAIZ.hijos) {
            switch(sentencia.nombre)
            {
                case "primitivaDA":
                    Xdeclaracion.declaracionPrimitivaDA(sentencia);
                    break;
                case "primitiva":
                    Xdeclaracion.declaracionPrimitivaD(sentencia);
                    break;
                case "struct":
                    Xdeclaracion.declaracionEstructuraD(sentencia);
                    break;    
                case "Acceso"://llamada a metodo 
                    XopA = new Expresiones_Aritmeticas();
                    XopA.OPERAR(sentencia);
                    break;  
                case "Mientras" :
                    manejo_sentencias_control.WHILE(sentencia);
                    break;
                case "Si" :
                    manejo_sentencias_control.SI(sentencia);
                    break; 
                case "primitivaDAGlobal":
                    Xdeclaracion.declaracionPrimitivaDA_GLOBAL(sentencia);
                    break;
                case "imprimir":
                    Xprimitivas.Imprimir(sentencia);
                    break;
                case "asignacion":
                    Xasignacion.asignacion(sentencia);
                    break;
                case "arreglo":
                    Xdeclaracion.declaracionArreglo(sentencia);
                    break;
            }
        }
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
    
    public Resultado Val_Rel_Logic(Resultado r1)
    {        
        return r1;
    }
    
    
    public String De$pl4z4r()
    {
        if(Metodo_Actual!=null)
        {
            return Metodo_Actual.tamanio + "";
        }else
        {
            return tabla.globales.size() + "";
        }
    }
}
