import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * La clase caldero modela un recipiente para preparar pocimas.
 * 
 * Se la inicializa con un nombre a eleccion, la cantidad maxima
 * de ingredientes que admite y sin receta y pocima asociada.
 * 
 * Cuando se tienen todos los ingredientes de la receta, se puede
 * proceder a preparar la pocima.  Solo se admiten recetas que no
 * superen la cantidad maxima de ingredientes del caldero (capacidad).
 * 
 */
public class Caldero {
    private final Integer MAX_INGREDIENTES;
    private String nombre;
    private Receta receta;
    private Elemento pocima;
    private Map<String, Elemento> elementos;

    /**
     * El constructor debe inicializar el objeto con un nombre
     * a eleccion, la capacidad maxima de ingredientes y sin
     * receta y pocima asociadas (null).
     * 
     * No olvidar construir el mapa de elementos (TreeMap).
     * 
     * @param nombre El nombre del caldero.
     * @param capacidad Capacidad maxima de ingredientes.
     */
    public Caldero (String nombre, Integer capacidad) {
        this.nombre = nombre;
        MAX_INGREDIENTES = capacidad; 
        elementos=new TreeMap<String, Elemento>();

        }

    /**
     * Asocia una receta al caldero para poder preparar una pocima.
     * 
     * La receta se incorpora solo si:
     *   1) la cantidad de ingredientes no supera la capacidad
     *      maxima del caldero,
     *   2) no hay una receta asociada,
     *   3) no hay pocima preparada.
     * Si la receta no puede agregarse, se debe imprimir el
     * mensaje;
     * 
     *  "<nombre>: No se puede agregar la receta"
     * 
     * donde <nombre> es el nombre del caldero.
     * 
     * Al agregar la receta, se deben generar las claves en el mapa
     * de elementos asociadas a ningun objeto (null).
     * 
     * @param receta La receta a incorporar.
     */
    public void setReceta (Receta receta) {
      if(receta!=null && receta.getCantidadIngredientes()<=MAX_INGREDIENTES && this.receta==null && pocima==null) {
      this.receta=receta;
      
      for(String ing: this.receta.getIngredientes())
      {
        elementos.put(ing,null);
      }
      }
      else{
        System.out.println(getNombre()+"no se puede agregar receta");}
      }

      

    

    /**
     * Incorpora al caldero un ingrediente siempre y cuando pertenezca
     * a la receta.
     *
     * Se incorpora el ingrediente al mapa de elementos, siendo
     * su nombre (getNombre()) la clave (key) y el valor (value) el
     * objeto elemento.
     * 
     * @param ingrediente El ingrediente a incorporar al caldero.
     */
    public void addIngrediente (Elemento ingrediente) {
        if(receta.getIngredientes().contains(ingrediente.getNombre())){
            elementos.put(ingrediente.getNombre(),ingrediente);
        }

    }

    /**
     * Genera una lista con los nombres de los ingredientes (key) faltantes.
     * 
     * Recordar que cada entrada del mapa (key) debe tener asociado
     * un elemento (value). En caso contrario, no hay elemento asociado (null).
     * 
     * Ayuda: La lista (ArrayList) debe crearse localmente. Lista vacia
     *  significa que se cuenta con todos los ingredientes.
     * 
     * @return La lista con los nombres de los ingredientes faltantes.
     */
    public List<String> getIngredientesFaltantes () {
      ArrayList<String> faltantes=new ArrayList<>();
     for(String s: receta.getIngredientes()){
     if( elementos.get(s)==null)
     {
       faltantes.add(s);
     }
    }
     
     return faltantes;
    }

    /**
     * Verifica que se hayan incorporado todos los ingredientes.
     * 
     * @return Devuelve true si se tienen todos los ingredientes y
     *         false si falta al menos uno.
     */
    public Boolean verificarIngredientes () {
        if(getIngredientesFaltantes().isEmpty())
        {
          return true;
        } 
        else
        {
        return false;
    }
    }
    /**
     * Prepara la pocima previa verificacion que exista una receta
     * y que se hayan incorporado todos los ingredientes.
     * 
     * En caso de realizar la pocima se debe:
     *  1) crear el objeto de tipo Elemento cuyo nombre es la
     *     concatenacion de "Pocima de" con el nombre de la receta
     *     y su peso es la suma de los pesos de cada ingrediente.
     *  2) desvincular la receta del caldero
     *  3) limpiar el mapa de ingredientes
     * 
     * Si no se puede prepara la pocima, se debe imprimir el
     * mensaje;
     * 
     *  "<nombre>: No se puede preparar la pocima"
     * 
     * donde <nombre> es el nombre del caldero 
     */
    public void prepararPocima () {
       Integer pesoPocima = 0;
       if(receta==null || !verificarIngredientes()) {
            System.out.println(getNombre()+"No se puede preparar pocima");
       } else {
         for(String i: receta.getIngredientes()){
           pesoPocima+=elementos.get(i).getPeso();
         }
       pocima=new Elemento("Pocima de "+receta.getNombre(),pesoPocima);
       receta=null;
       elementos.clear();
       }

    }

    /**
     * Retorna la pocima preparada y restablece a null el campo.
     -
     * 
     * @return La pocima preparada.
     */
    public Elemento getPocima() {
        Elemento pocimaLista=pocima;
        pocima=null;
        return pocimaLista;
    }

    /**
     * Genera una cadena con la informacion sobre el caldero.
     * 
     * Debe generarse usando el nombre y la receta asociada o la pocima segun:
     *    "<nombre>: ["vacio" | <receta> | <pocima>]"
     * 
     * Ejemplo sin receta ni pocima:
     *    "Caldero mediano: vacio"
     * 
     * Ejemplo con receta:
     *    "Caldero mediano: Receta voladora"
     * 
     * Ejemplo con pocima:
*     *    "Caldero mediano: Pocima de Receta voladora"
     * 
     * @return El texto indicado en el ejemplo.
     */
    @Override
    public String toString() {
       String calderoEstado;
        if(receta == null && pocima == null)
        {
            calderoEstado = getNombre() + ": vacio";
        }
        else if(pocima == null)
        {
            calderoEstado = getNombre() + ": " + receta.getNombre();
        }
        else
        {
            calderoEstado = getNombre() + ": " + pocima.getNombre();
        }
        return calderoEstado;
    }
    

    /**
     * Devuelve el mapa de ingredientes.
     * 
     * @return El mapa de ingredientes.
     */
    public Map<String, Elemento> getIngredientes () {
       
       
        return elementos;
    }

    /**
     * Devuelve la receta asociada.
     * 
     * @return La receta asociada.
     */
    public Receta getReceta() {
        return receta;
   }

    /**
     * Devuelve el nombre del caldero
     *
     * @return El nombre de caldero
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Devuelve la capacidad max. del caldero
     *
     * @return capacidad max.
     */
    public int getCapacidad() {
        return MAX_INGREDIENTES;
    }
}