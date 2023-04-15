/**
 * Esta clase modela un personaje de un juego de rol.
 */
public class Personaje {
    private final Integer MAX_VIDA;
    private final Integer PESO_MAXIMO_BOLSA;
    // nombre -> el nombre del personaje
    private String nombre;
    // vida -> valor actual de vida
    private Integer vida;
    // caldero -> el caldero para preparar pocimas
    private Caldero caldero;
    // objeto -> el elemento que tiene en sus manos
    //           Puede tener las manos vacias (null)
    private Elemento objeto;
    // bolsa -> la Bolsa de elementos
    private Bolsa bolsa;

    /**
     * Crea un personaje con el nombre dado y configura los
     * campos de la superclase: vida, MAX_VIDA y PESO_MAXIMO_BOLSA.
     * 
     * El personaje se inicializa sin bolsa, sin caldero y
     * con las manos vacias.
     * 
     * @param nombre El nombre del personje.
     * @param vida El valor inicial y maximo de vida del personaje.
     * @param peso El peso maximo que puede transportar el personaje.
     */
    public Personaje (String nombre, Integer vida, Integer peso) {
        this.nombre = nombre;
        MAX_VIDA = vida;
        this.vida = vida;
        PESO_MAXIMO_BOLSA = peso;
    }

    /**
     * Establece la nueva bolsa para el personaje.
     * 
     * Solo se puede asignar bolsas cuyo peso maximo de carga
     * no supere el maximo soportado por el personaje.  Si el
     * personaje ya tiene una bolsa asociada, la nueva bolsa
     * debe tener mayor capacidad que la actual.  Si no se
     * cumple algunos de los requisito se debe imprimir:
     *         "Bolsa inapropiada"
     * 
     * En caso de reemplazar la bolsa actual, deben pasarse
     * todos los objetos a la nueva bolsa.
     * 
     * @param bolsa La nueva bolsa del personaje.
     */
    public void setBolsa(Bolsa bolsa) {
        if(bolsa.getPesoMaximo() <= PESO_MAXIMO_BOLSA)
        {
            if(this.bolsa == null)
            {
                this.bolsa = bolsa;
            }
            else if(this.bolsa.getPesoMaximo() < bolsa.getPesoMaximo())
            {
                for(Elemento cambio : this.bolsa.getElementosEnLaBolsa())
                {
                    bolsa.addElemento(this.bolsa.delElemento(cambio.getNombre()));
                }
                this.bolsa = bolsa;
            }
            else
            {
                System.out.println("Bolsa inapropiada");
            }
        }
        else
        {
            System.out.println("Bolsa inapropiada");
        }
    }

    /**
     * Devuelve la bolsa del personaje.
     * 
     * @return La bolsa del personaje.
     */
    public Bolsa getBolsa() {
        return bolsa;
    }

    /**
     * Guarda el elemento que tiene en sus manos en la bolsa, siempre
     * y cuando haya lugar suficiente.  Las manos quedan vacias (null).
     * 
     * Si las manos estan vacias (null), mostrar "No hay elemento
     * para agregar a la bolsa"
     */
    public void guardarElemento() {
        if(objeto != null && (objeto.getPeso() + bolsa.getPesoActual()
                <= bolsa.getPesoMaximo()))
        {
            bolsa.addElemento(objeto);
            objeto = null;
        }
        else if(objeto == null)
        {
            System.out.println("No hay elemento para agregar a la bolsa");
        }
    }

    /**
     * Toma un elemento de la bolsa (delElemento) y lo pone
     * en las manos del personaje.
     * 
     * Si no existe el elemento se debe imprimir
     *       "No se cuenta con el <nombre>"
     * donde <nombre> es el nombre del elemento buscado.
     * 
     * @param nombre El elemento a tomar de la bolsa.
     */
    public void tomarElemento (String nombre) {
        Elemento existe = null;
        for(Elemento objeto : bolsa.getElementosEnLaBolsa())
        {
            if(objeto.getNombre().equals(nombre))
            {
                existe = objeto;
                bolsa.delElemento(objeto.getNombre());
                break;
            }
        }
        if(existe == null)
        {
            System.out.println("No se cuenta con el " + nombre);
        }
        else
        {
            this.objeto = existe;
        }
    }
    
    /**
     * Asigna el caldero al personaje.
     * 
     * @param caldero El caldero para el personaje.
     */
    public void setCaldero(Caldero caldero) {
        this.caldero = caldero;
    }
    
    /**
     * Devuelve el caldero que posee el personaje.
     * 
     * @return El caldero que posee el personaje.
     */
    public Caldero getCaldero() {
        return caldero;
    }

    /**
     * Prepara la pocima de la receta en el caldero.
     * 
     * Se debe agregar la receta al caldero (setReceta)
     * y luego, tomar de la bolsa los ingredientes.
     * 
     * En caso de no contar con todos los ingredientes se
     * debe imprimir:
     *       "Faltan <n> ingredientes para <receta>"
     * donde <n> es la cantidad de ingredientes faltantes y
     * <receta> es el nombre de la receta.
     * 
     * @param receta
     */
    public void prepararReceta (Receta receta) {
        caldero.setReceta(receta);
        for(String ing : receta.getIngredientes()){
            caldero.addIngrediente(bolsa.delElemento(ing));
        }
        if(caldero.getIngredientesFaltantes().size() != 0){
            System.out.println("Faltan " + caldero.getIngredientesFaltantes().size()
                + " ingredientes para " + receta.getNombre());
        } else {
            caldero.prepararPocima();
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getVida() {
        return vida;
    }

    public void setVida(Integer vida) {
        this.vida = vida;
    }

    public void resetVida(Integer vida) {
        this.vida = MAX_VIDA;
    }

    public Elemento getElemento () {
        return objeto;
    }

    public void setElemento (Elemento objeto) {
        this.objeto = objeto;
    }
}