import java.util.Set;
import java.util.TreeSet;


public class Receta {
    private Boolean cerrada;
    private Set<String> ingredientes;
    private String nombre;

    /**
     * El constructor debe inicializar el objeto con un nombre
     * a eleccion y estado "ok" en falso.
     * 
     * No olvidar construir el conjunto (TreeSet) de ingredientes 
     * 
     * @param nombre El nombre de la receta.
     */
    public Receta (String nombre) {
        cerrada=false;
        this.nombre = nombre;
        ingredientes = new TreeSet<>();
    }

    /**
     * Agrega un nuevo ingrediente a la receta.
     * 
     * Solo se aceptaran nuevos ingredientes cuando la
     * receta se encuentre sin terminar ("cerrada" en falso).
     * 
     * @param ingrediente El ingrediente a agregar.
     */
    public void addIngrediente(String ingrediente) {
        if(!cerrada){
            ingredientes.add(ingrediente);
        }

    }

    /**
     * Devuelve la cantidad de ingredientes de la receta.
     * 
     * @return La cantidad de ingredientes de la receta.
     */
    public int getCantidadIngredientes () {
        int cantidad = ingredientes.size();
        return cantidad;
    }

    /**
     * Devuelve los ingredientes de la receta.
     * 
     * @return Los ingredientes de la receta como conjunto.
     */
    public Set<String> getIngredientes () {
        return ingredientes;
    }
    
    /**
     * Cambia el estado de la receta cuando tenga al menos dos (2) ingredientes.
     * En caso contrario, no hace nada.
     * 
     * No puede revertirse.
     * 
     * @return true si se pudo cerrar la receta, false en caso contrario
     */
    public Boolean cerrarReceta () {        
        if (!cerrada){
            if(ingredientes.size()>=2){
              cerrada=true;  
            }        
        }
        return cerrada;
    }


    /**
     * Genera una cadena con la informacion sobre la receta.
     * 
     * Debe generarse usando el nombre, "incompleta" o "completa" segun
     * el estado ("ok") y listar los ingredientes que la conforman con el
     * formato
     *     "Receta <nombre> (<estado>)\nIngredientes:\n<ingr1>\n<ingr2>\n..."
     * 
     * Ejemplo receta incompleta:
     *    "Receta voladora (incompleta)
     *     Ingredientes:
     *     Pluma"
     * 
     * Ejemplo receta completa:
     *    "Receta voladora (completa)
     *     Ingredientes:
     *     Hueso
     *     Pluma
     *     Sangre"
     * 
     * @return El texto indicado en el ejemplo.
     */
    @Override
    public String toString() {
      String texto; //Texto de salida
      String estado; //Estado de la receta completa o no
      String listadoIngredientes=""; //String con listado de ingredientes
      if (cerrada){
          estado="completa"; 
        }else{
          estado="incompleta";
        }      
      for(String ingrediente : ingredientes){
         listadoIngredientes += ingrediente+" \n";
        }   
      texto="Receta "+nombre+" ("+estado+")\nIngredientes:\n"+listadoIngredientes;
      return texto;
    }

    /**
     * Verifica si la receta se puede usar.
     * 
     * @return true si puede usarse y false si no esta cargada completamente.
     */
    public Boolean isCerrada () {
        return cerrada;
    }

    public String getNombre () {
        return nombre;
    }    

}