/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2017.web.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.acceso.TipoPostFacadeLocal;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.definiciones.TipoPost;

/**
 *
 * @author carlos
 */
@Named(value = "tipoPostBean")
@ViewScoped
public class TipoPostBean implements Serializable{

    /**
     * Creates a new instance of TipoPostBean
     */
    public TipoPostBean() {
    } 
        
    @EJB
    private TipoPostFacadeLocal rfl;
    private LazyDataModel<TipoPost> modelo;
    private TipoPost registro;
    private boolean btnadd = true;
    private boolean botones = false;
    private boolean seleccions =false;

    
    @PostConstruct
    private void inicio() {

        registro = new TipoPost();

        try {
            this.modelo = new LazyDataModel<TipoPost>() {
                @Override
                public Object getRowKey(TipoPost object) {
                    if (object != null) {
                        return object.getIdTipoPost();
                    }
                    return null;
                }

                @Override
                public TipoPost getRowData(String rowKey) {
                    if (rowKey != null && !rowKey.isEmpty() && this.getWrappedData() != null) {
                        try {
                            Integer buscado = new Integer(rowKey);
                            for (TipoPost reg : (List<TipoPost>) getWrappedData()) {
                                if (reg.getIdTipoPost().compareTo(buscado) == 0) {
                                    return reg;
                                }
                            }
                        } catch (Exception e) {
                            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                        }
                    }
                    return null;
                }

                @Override
                public List<TipoPost> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                    List<TipoPost> salida = new ArrayList();
                    try {
                        if (rfl != null) {
                            this.setRowCount(rfl.count());
                            salida = rfl.findRange(first, pageSize);
                            
                        }
                    } catch (Exception e) {
                        Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                    }
                    return salida;
                }

            };
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
                 
    }
    
    
    /**
     * METODO PARA CANCELAR TODO 
     */
    
    public void cancelar() {
        this.registro = new TipoPost();
        this.botones=false;
        this.btnadd=true;
        
    }

    /**
     * METODO PARA GUARDAR EL REGISTRO 
     */
    public void guardarRegistro() {
        try {
            if (this.registro != null && this.rfl != null) {
               if (this.rfl.create(registro)) {
                    System.out.println("Agregado exitosamente");
                    inicio();
                }
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    /**
     * METODO PARA ELIMINAR EL REGISTRO 
     */
    public void Eliminar() {
        try {

            if (this.registro != null && this.rfl != null) {
                if (this.rfl.remove(registro)) {
                    this.registro = new TipoPost();
                    inicio();
                }
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    
    /**
     * METODO QUE SE UTILIZA PARA GUARDAR EL REGISTRO QUE EDITAMOS 
     */
    public void Modificar() {
        try {
            if (this.registro != null && this.rfl != null) {
                if (this.rfl.edit(registro)) {
                    inicio();
                    
                }
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    /**
     * ESTE METODO SE USA PARA CAMBIAR LA SELECCION DE LOS BOTONES
     * (ACTIVA O DESACTIVA LOS BOTONES -CREAR- -ELIMINAR- -EDITAR-)
     */
    public void cambiarSeleccion() {
                this.botones = true;
                this.btnadd = false;
                  
    }
    
    /**
     * TODOS LOS GETTER & SETTER
     * @return 
     */
    public LazyDataModel<TipoPost> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<TipoPost> modelo) {
        this.modelo = modelo;
    }

    public TipoPost getRegistro() {
        return registro;
    }

    public void setRegistro(TipoPost registro) {
        this.registro = registro;
    }

    public boolean isBtnadd() {
        return btnadd;
    }

    public void setBtnadd(boolean btnadd) {
        this.btnadd = btnadd;
    }

    public boolean isSeleccions() {
        return seleccions;
    }

    public void setSeleccions(boolean seleccions) {
        this.seleccions = seleccions;
    }

    
    public boolean isBotones() {
        return botones;
    }

    public void setBotones(boolean botones) {
        this.botones = botones;
    }

   
   
}


        
        
        
        
    
    

