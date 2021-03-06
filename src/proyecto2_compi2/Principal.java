/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_compi2;

import Estructuras_Ide.Nodo_Ejecutor;
import Gramatica_DASM.lexico_DASM;
import Gramatica_DASM.sintactico_DASM;
import EjecutorPrincipal_Draco.EjecutorDraco;
import Elementos_Draco.Debugger;
import Elementos_Draco.Reporte_ErroresDRACO;
import Elementos_Draco.ReporteTablaSimbolo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import static java.lang.System.in;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.BadLocationException;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.fife.ui.rsyntaxtextarea.AbstractTokenMakerFactory;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenMakerFactory;
import org.fife.ui.rtextarea.Gutter;
import org.fife.ui.rtextarea.GutterIconInfo;
import org.fife.ui.rtextarea.RTextScrollPane;
import static proyecto2_compi2.Pesta.textArea;
/**
 *
 * @author anton
 */
public class Principal extends javax.swing.JFrame {
    public static ArrayList<JTextArea> listAreas = new ArrayList<JTextArea>();
    public static Pesta nueva;
    public static boolean control_ambientes=false;
    public int contador_pesta=0;
    public static JTree arbol;
    public static jtree jTreeFiles = new jtree();
    RSyntaxTextArea textArea;
    public static Gutter gutter;
    public static boolean variableDebugger=false;
    public static ArrayList<Debugger>lista_Debugger=new ArrayList<Debugger>();
    public int lineaActual=-1;
    public static Pintar pintar=new Pintar();
    public static Reporte_ErroresDRACO reporteError=new Reporte_ErroresDRACO();
    /**
     * Creates new form Principal
     */
    public Principal() throws IOException {
        initComponents();
        arbol=jTree1;
        iniciarTree();
        agregarPesta_Consola();
        agregar_Tabla();
        agregar_Errores();
        agregarPesta();
        
    }
    public void agregar_Tabla(){
        JPanel panel_tabla = new JPanel();
        panel_tabla.setName("tabla");
        panel_tabla.setLayout(new BorderLayout());  //give your JPanel a BorderLayout
        JTextArea text = new JTextArea();
        text.setName("texto_tabla");
        listAreas.add(text);
        JScrollPane scroll = new JScrollPane(text); //place the JTextArea in a scroll pane
        scroll.setName("scroll_tabla");
        panel_tabla.add(scroll, BorderLayout.CENTER);
        this.jTabbedPane2.add(panel_tabla,"TABLA SIMBOLOS",0);
        this.jTabbedPane2.setSelectedIndex(0);
    
    }
    public void agregar_Errores(){
        JPanel panel_errores = new JPanel();
        panel_errores.setName("errores");
        panel_errores.setLayout(new BorderLayout());  //give your JPanel a BorderLayout
        JTextArea text = new JTextArea();
        text.setName("texto_errores");
        listAreas.add(text);
        JScrollPane scroll = new JScrollPane(text); //place the JTextArea in a scroll pane
        scroll.setName("scroll_errores");
        panel_errores.add(scroll, BorderLayout.CENTER);
        this.jTabbedPane2.add(panel_errores,"ERRORES",0);
        this.jTabbedPane2.setSelectedIndex(0);
    
    }
    public static void iniciarTree() throws IOException, IOException{
        jTreeFiles.setJTree(arbol);
        jTreeFiles.init(); 
    }
    public void agregarPesta_Consola(){
        JPanel panel = new JPanel();
        panel.setName("consola");
        panel.setLayout(new BorderLayout());  //give your JPanel a BorderLayout
        JTextArea text = new JTextArea();
        text.setName("texto_consola");
        listAreas.add(text);
        JScrollPane scroll = new JScrollPane(text); //place the JTextArea in a scroll pane
        scroll.setName("scroll_consola");
        panel.add(scroll, BorderLayout.CENTER);
        this.jTabbedPane2.add(panel,"CONSOLA",0);
        this.jTabbedPane2.setSelectedIndex(0);
    
    } 
    private MouseMotionListener getListener() {
        return new MouseMotionListener() {

            @Override
            public void mouseMoved(MouseEvent e) {

                int viewToModel = textArea.viewToModel(e.getPoint());
                if(viewToModel != -1){
                    try {
                        lineaActual=(1+textArea.getLineOfOffset(viewToModel));
                        //System.out.println("line: "+(1+textArea.getLineOfOffset(viewToModel)));
                    } catch (BadLocationException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
            }
        };
    }
    public static void agregar_puntos(int linea) throws IOException, BadLocationException{
        Icon icon = new ImageIcon(ImageIO.read(Principal.class.getResource("/breakpoint.png")));
        GutterIconInfo info = gutter.addLineTrackingIcon(linea, icon);
        Debugger debug=new Debugger(linea,info);
        lista_Debugger.add(debug);
    }
    public static void quitar_puntos(int linea){
        gutter.removeTrackingIcon(buscar_Lista(linea).getInfo());
        eliminar_Lista(linea);
    }
    public void agregarPesta(){
        RTextScrollPane sp;
        System.out.println(this.jTabbedPane2.getSelectedIndex());
        JPanel panel= new JPanel();
        textArea= new RSyntaxTextArea(16,157);
        textArea.setLocation(0,0);
        textArea.addMouseMotionListener(getListener());
        textArea.addMouseListener(new MouseAdapter(){
         @Override
            public void mouseClicked(MouseEvent e){
                
            if(e.getClickCount()==2){
               try {
                    if(buscar_Lista(lineaActual)!=null){
                        gutter.removeTrackingIcon(buscar_Lista(lineaActual).getInfo());
                        eliminar_Lista(lineaActual);
                    }else{
                        Icon icon = new ImageIcon(ImageIO.read(getClass().getResource("/breakpoint.png")));
                        GutterIconInfo info = gutter.addLineTrackingIcon(lineaActual-1, icon);
                        Debugger debug=new Debugger(lineaActual,info);
                        lista_Debugger.add(debug);
                    }
                }catch(IOException | BadLocationException o){
                    System.out.println(o.toString());
                }
            }
            }
        });
        AbstractTokenMakerFactory atmf = (AbstractTokenMakerFactory)TokenMakerFactory.getDefaultInstance();
        atmf.putMapping("text/mi_lenguaje", "colores.colores_Draco");
        textArea.setSyntaxEditingStyle("text/mi_lenguaje");
        textArea.setName("textArea");
        changeStyleProgrammatically();
        RTextScrollPane sp1 = new RTextScrollPane(textArea);
        sp1.setFoldIndicatorEnabled(true);
        sp1.setIconRowHeaderEnabled(true);
        
        gutter = sp1.getGutter();
        gutter.setLineNumberColor(Color.RED);
        
        sp1.setName("scroll");
        sp1.setSize(10, 20);
        panel.add(BorderLayout.CENTER, sp1);
        panel.add(sp1);
        this.jTabbedPane2.add(panel,"DASM",0);
        this.jTabbedPane2.setSelectedIndex(0);
    }
    public static Debugger buscar_Lista(int valor){
        Debugger respuesta=null;
        for(int i=0;i<lista_Debugger.size();i++){
            if(lista_Debugger.get(i).getLinea()==valor){
                respuesta=lista_Debugger.get(i);
                break;
            }
        }
        return respuesta;
    }
    public static void eliminar_Lista(int valor){
        for(int i=0;i<lista_Debugger.size();i++){
            if(lista_Debugger.get(i).getLinea()==valor){
                lista_Debugger.remove(i);
                break;
            }
        }
    }
     private void changeStyleProgrammatically() {
      // Change a few things here and there.
        try{
            SyntaxScheme scheme = textArea.getSyntaxScheme();
            scheme.getStyle(Token.RESERVED_WORD).foreground = Color.BLUE;
            scheme.getStyle(Token.LITERAL_CHAR).foreground = Color.ORANGE;
            scheme.getStyle(Token.LITERAL_STRING_DOUBLE_QUOTE).foreground = Color.ORANGE;
            scheme.getStyle(Token.LITERAL_NUMBER_DECIMAL_INT).foreground = Color.MAGENTA;
            scheme.getStyle(Token.COMMENT_MULTILINE).foreground = Color.GRAY;
            scheme.getStyle(Token.COMMENT_EOL).foreground = Color.GRAY;
            textArea.revalidate();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ocurrio un error " +e.toString());
        }
   }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu3 = new javax.swing.JMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();

        jMenu3.setText("jMenu3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTree1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jTree1);

        jButton1.setText("GENERAR IMAGENES");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("COMPILAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jMenu1.setText("ARCHIVO");

        jMenuItem5.setText("Nuevo Archivo(nueva tab)");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem4.setText("Nueva Carpeta");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem2.setText("Guardar (tab Actual)");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Cerrar (tab Actual)");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("DEBUGGER");

        jMenuItem6.setText("Iniciar");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem7.setText("Siguiente Paso");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuItem8.setText("Terminar Todo");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuItem10.setText("Continuar");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);

        jMenuItem9.setText("Mostrar Pilas");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(335, 335, 335)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 938, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1203, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void GenerarPesta(String entrada,String nombre,String path){
        try{
            if((this.jTabbedPane1.getTabCount()-1)==this.jTabbedPane1.getSelectedIndex()){
                nueva=new Pesta(path);
                nueva.setContenido(entrada);
                //x,y,ancho,alto
                nueva.setName(nombre);
                this.jTabbedPane1.add(nueva,nombre,this.jTabbedPane1.getTabCount());
                this.jTabbedPane1.setSelectedIndex(this.jTabbedPane1.getTabCount()-1);
                contador_pesta++;
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ocurrio un error " +e.toString());
        }
    }
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        try{
            JFrame parentFrame = new JFrame();
            JFileChooser fileChooser = new JFileChooser("ruta");
            fileChooser.setDialogTitle("Specify a file to save");   
            int userSelection = fileChooser.showSaveDialog(parentFrame); 
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                if(Crear_Archivo(fileToSave.getAbsolutePath())){
                    GenerarPesta("",fileToSave.getName(),fileToSave.getAbsolutePath());
                    jTreeFiles.init();
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ocurrio un error " +e.toString());
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed
    public boolean Crear_Archivo(String ruta){
        boolean respuesta=false;
        try{
            File archivo = new File(ruta);
            BufferedWriter bw=null;
            if(archivo.exists()) {
                JOptionPane.showMessageDialog(null, "Ya existe un archivo con ese nombre");
            }else {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write("");
                respuesta=true;
            }
            bw.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ocurrio un error " +e.toString());
        }
        return respuesta;
    }
    private void jTree1ValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jTree1ValueChanged
        // TODO add your handling code here:
        try{
            DefaultMutableTreeNode nodo=(DefaultMutableTreeNode)arbol.getLastSelectedPathComponent();
            if(nodo==null){
                return;
            }
            if(nodo.getLevel()==0){
                System.out.println("Es el nodo raiz");
            }else{
                System.out.println(nodo.getUserObject().toString());
                String valor=nodo.getUserObject().toString();
                String [] separado=valor.split("\\.");
                if(separado.length==2){
                     //aca tengo que agregar la nueva tab a la interfaz
                    String rutanueva=new File ("").getAbsolutePath ()+"\\"+nodo.getUserObject().toString();
                    File archivo=new File(rutanueva);
                    FileReader fr = new FileReader (archivo);
                    BufferedReader br = new BufferedReader(fr);
                    String completo="";
                    String linea ="";
                    while((linea=br.readLine())!=null){
                        completo+="\n"+linea;
                    }
                    GenerarPesta(completo,valor,rutanueva);
                }else{
                    System.out.println("Es una carpeta");
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ocurrio un error " +e.toString());
        }
    }//GEN-LAST:event_jTree1ValueChanged

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        try{
            if(contador_pesta>0){
                this.jTabbedPane1.remove(this.jTabbedPane1.getSelectedIndex());
                contador_pesta--;
            }else{
                JOptionPane.showMessageDialog(null, "No hay pestañas para cerrar");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ocurrio un error " +e.toString());
        }
        
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        try{
            String choosertitle="";
            JFileChooser chooser = new JFileChooser(); 
            chooser.setCurrentDirectory(new java.io.File("ruta"));
            chooser.setDialogTitle(choosertitle);
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);    
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
                System.out.println("getCurrentDirectory(): " 
                +  chooser.getCurrentDirectory());
                System.out.println("getSelectedFile() : " 
                +  chooser.getSelectedFile());
            }else{
                System.out.println("No Selection ");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ocurrio un error " +e.toString());
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed
    public void EscribirArchivo(String nombre, String contenido){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try{
            fichero = new FileWriter(nombre);
            pw = new PrintWriter(fichero);
            pw.println(contenido);
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
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if(contador_pesta>0){
            System.out.println(this.jTabbedPane1.getSelectedIndex());
            Pesta pesta=(Pesta)this.jTabbedPane1.getComponentAt(this.jTabbedPane1.getSelectedIndex());
            for(Component cmp2:pesta.getComponents()){
                if(cmp2.getName()=="scroll"){
                    RTextScrollPane scroll=(RTextScrollPane)cmp2;
                    System.out.println(scroll.getTextArea().getText());
                    EscribirArchivo(pesta.path,scroll.getTextArea().getText());
                    JOptionPane.showMessageDialog(null, "Se guardo con exito la pestaña");
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "No hay pestañas para guardar");
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        System.out.println("BOTON SIGUIENTE PASO");
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        System.out.println("BOTON TERMINAR TODO");
    }//GEN-LAST:event_jMenuItem8ActionPerformed
public static boolean existe_maestro(String path){
        boolean respuesta=false;
        File fichero = new File(path);
        if (fichero.exists()){
            respuesta=true;
        }else{
            respuesta=false;
        }
        return respuesta;
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        System.out.println("BOTON COMPILAR");
        
        if(control_ambientes==true){
            if((this.jTabbedPane1.getTabCount()-1)==this.jTabbedPane1.getSelectedIndex()){
                nueva=new Pesta(listAreas.get(0).getText());
                nueva.setContenido(listAreas.get(1).getText());
                //x,y,ancho,alto
                this.jTabbedPane1.add(nueva,"",this.jTabbedPane1.getTabCount());
                this.jTabbedPane1.setSelectedIndex(this.jTabbedPane1.getTabCount()-1);
                contador_pesta++;
            }
        }
        if(contador_pesta>0){
            System.out.println(this.jTabbedPane1.getSelectedIndex());
            Pesta pesta=(Pesta)this.jTabbedPane1.getComponentAt(this.jTabbedPane1.getSelectedIndex());
            for(Component cmp2:pesta.getComponents()){
                if(cmp2.getName()=="scroll"){
                    RTextScrollPane scroll=(RTextScrollPane)cmp2;
                    System.out.println(scroll.getTextArea().getText());
                    EscribirArchivo(pesta.path,scroll.getTextArea().getText());
                    Ejecucion_D.Interpretacion ttt = new Ejecucion_D.Interpretacion();
                    try {
                        
                        ttt.iniciar_interpretacion(pesta.path,scroll.getTextArea().getText().toString());
                    } catch (IOException ex) {
                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "No hay pestañas para guardar");
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    public void Lectura(String direccion){
    
    
    }
    public void GenerarNueva(String entrada){
        if((this.jTabbedPane1.getTabCount()-1)==this.jTabbedPane1.getSelectedIndex()){
            nueva=new Pesta(entrada);
            nueva.setContenido(entrada);
            //x,y,ancho,alto
            this.jTabbedPane1.add(nueva,"",this.jTabbedPane1.getTabCount());
            this.jTabbedPane1.setSelectedIndex(this.jTabbedPane1.getTabCount()-1);
            contador_pesta++;
        }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        pintar.listapintar.clear();
        JPanel panel=(JPanel)this.jTabbedPane2.getComponentAt(0);
        for(Component cmp2:panel.getComponents()){
            if(cmp2.getName()=="scroll"){
                try {
                    if(true){
                    
                    }else{
                        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                        FileNameExtensionFilter filter = new FileNameExtensionFilter("XLS files", "xls");
                        jfc.setFileFilter(filter);
                        int returnValue = jfc.showOpenDialog(null);
                        // int returnValue = jfc.showSaveDialog(null);
                        if (returnValue == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = jfc.getSelectedFile();
                            String path=selectedFile.getAbsolutePath().toString();
                            System.out.println(selectedFile.getAbsolutePath());
                            Lectura(path);
                            JOptionPane.showMessageDialog(null,"Archivo Cargado a Memoria","Mensaje",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    RTextScrollPane scroll=(RTextScrollPane)cmp2;
                    reporteError = new Reporte_ErroresDRACO();
                    try{
                        JFrame parentFrame = new JFrame();
                        JFileChooser fileChooser = new JFileChooser("ruta");
                        fileChooser.setDialogTitle("Specify a file to save");   
                        int userSelection = fileChooser.showSaveDialog(parentFrame); 
                        if (userSelection == JFileChooser.APPROVE_OPTION) {
                            File fileToSave = fileChooser.getSelectedFile();
                            EscribirArchivo(fileToSave.getAbsolutePath(),scroll.getTextArea().getText());
                            File file = new File(fileToSave.getAbsolutePath());
                            EjecutorDraco draco = new EjecutorDraco(file,fileToSave.getAbsolutePath());   
                            ReporteTablaSimbolo reporte=new ReporteTablaSimbolo();
                            reporte.generarHtml();
                            reporteError.generarHtml("reporte","DRACO");
                        }
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null, "Ocurrio un error " +e.toString());
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Throwable ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    
    public static boolean existe(String path){
        boolean respuesta=false;
        File fichero = new File(path);
        if (fichero.exists()){
            respuesta=true;
        }else{
            respuesta=false;
        }
        return respuesta;
    }
    
    public static void cargar_archivos_BD(){
        //aqui vamos a hacer la ejecucion
        try {
            
            for(int i=0;i<listAreas.size();i++){
                //System.out.println(inicio_maestro.getHijos().get(i).getPath());
                FileReader lectura=new FileReader(listAreas.get(0).getText());
                EjecutorDraco nuevo;
                System.out.println("Archivos");
            }
            //System.out.println(inicio_maestro.getNombre());
            
        } catch (Exception e) {
            System.out.println("error metodo cargar_archivos_BD"+ e.getMessage());
        }
    }
    
    
    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jMenuItem9ActionPerformed
    
    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        variableDebugger=true;
        pintar.listapintar.clear();
        JPanel panel=(JPanel)this.jTabbedPane2.getComponentAt(0);
        for(Component cmp2:panel.getComponents()){
            if(cmp2.getName()=="scroll"){
                try {
                    if(true){
                    }else{
                        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                        FileNameExtensionFilter filter = new FileNameExtensionFilter("", "");
                        jfc.setFileFilter(filter);
                        int returnValue = jfc.showOpenDialog(null);
                        // int returnValue = jfc.showSaveDialog(null);
                        if (returnValue == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = jfc.getSelectedFile();
                            String path=selectedFile.getAbsolutePath().toString();
                            System.out.println(selectedFile.getAbsolutePath());
                            Lectura(path);
                            JOptionPane.showMessageDialog(null,"Archivo Cargado a Memoria","Mensaje",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    RTextScrollPane scroll=(RTextScrollPane)cmp2;
                    reporteError = new Reporte_ErroresDRACO();
                    try{
                        JFrame parentFrame = new JFrame();
                        JFileChooser fileChooser = new JFileChooser("ruta");
                        fileChooser.setDialogTitle("Specify a file to save");   
                        int userSelection = fileChooser.showSaveDialog(parentFrame); 
                        if (userSelection == JFileChooser.APPROVE_OPTION) {
                            File fileToSave = fileChooser.getSelectedFile();
                            EscribirArchivo(fileToSave.getAbsolutePath(),scroll.getTextArea().getText());
                            File file = new File(fileToSave.getAbsolutePath());
                            EjecutorDraco draco = new EjecutorDraco(file,fileToSave.getAbsolutePath());   
                            ReporteTablaSimbolo reporte=new ReporteTablaSimbolo();
                            reporte.generarHtml();
                            reporteError.generarHtml("reporte","DRACO");
                        }
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null, "Ocurrio un error " +e.toString());
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Throwable ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        
        pintar.agregarPoint(100,100,"#000000",50);
        pintar.agregarQuadrate(200, 200,"#FA0000",100,100);
        pintar.agregarOval(300, 200,"#FF0AA0",100,50);
        pintar.agregarString(400, 400,"#AAAFFF","HOLA");
        pintar.agregarLine(100,500,150,200,"#F44000",15);
    }//GEN-LAST:event_jMenuItem10ActionPerformed
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Principal().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    public static javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
}
