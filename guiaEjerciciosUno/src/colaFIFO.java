import java.util.Arrays;

public class colaFIFO {
    private final String[] data;
    private int head = 0;   // frente
    private int tail = 0;   // lugar donde se inserta el proximo
    private int size = 0;   // cantidad de elementos
    public colaFIFO(int capacidad) {
        if (capacidad <= 0) throw new IllegalArgumentException("La capacidad debe ser mayor a cero");
        this.data = new String[capacidad];
    }
    //getters
    public int capacidad() { return this.data.length; }
    public int size() { return this.size; }
    public boolean isEmpty() { return this.size == 0; }
    public boolean isFull() { return this.size == this.data.length; }
    public static final class resultados{
        public final boolean ok;
        public final String message; // mostrar en UI si ok == false y logs
        public final String valor;   // dequeue/peek el valor resultante

        private resultados(boolean ok, String message, String valor){
            this.ok = ok;
            this.message = message;
            this.valor = valor;
        }
        public static resultados exito(String cadena){ return new resultados(true, null, cadena); }
        public static resultados falla(String mensaje){ return new resultados(false, mensaje, null); }
        @Override public String toString(){
            return ok ? "OK" + (valor != null ? "[" + valor + "]" : "") : "ERROR [" + message + "]";
        }
    }
    /* FUNCIONES:
     * Agregar elemento
     * validar input no vacio
     * validar que Count < capacidad
     * poner elemento en la cola del rear
     */
    public resultados enqueue(String input){
        if (input == null || input.trim().isEmpty()) {
            return resultados.falla("Entrada vacia");
        }
        if (isFull()) {
            return resultados.falla("Cola llena");
        }
        data[tail] = input;
        tail = (tail + 1) % data.length;
        size++;
        return resultados.exito(input);
    }
    // Dequeue
    public resultados dequeue(){
        if (isEmpty()) {
            return resultados.falla("Cola vacia");
        }
        String valor = data[head];
        data[head] = null;
        head = (head + 1) % data.length;
        size--;
        return resultados.exito(valor);
    }
    // VER
    public resultados peek(){
        if (isEmpty()) {
            return resultados.falla("Cola vacia");
        }
        return resultados.exito(data[head]);
    }
    // Snapshot
    public String[] toSnapshot(){
        String[] out = new String[size];
        for (int i = 0; i < size; i++){
            out[i] = data[(head + i) % data.length];
        }
        return out;
    }
    @Override public String toString(){
        return Arrays.toString(toSnapshot());
    }
}