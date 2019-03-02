/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete_D_Elementos;
import Estructuras_Ide.Nodo;
import Administrador_Errores.Errores;
import java.util.ArrayList;
import java.util.List;

public class ControlArreglos {


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

}