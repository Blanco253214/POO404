import java.util.Arrays;

public class pilaLIFO {
    private final String[] data;
    private int top = -1; 

    public pilaLIFO(int capacidad){
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor a cero");
        }
        this.data = new String[capacidad];
    }
    //getters
    public int capacidad() { return data.length; }
    public int size() { return top + 1; }
    public boolean isEmpty() { return top == -1; }
    public boolean isFull() { return size() == data.length; }

    //Contenedor
    public static final class resultados{
        public final boolean ok;
        public final String message;
        public final String valor;

        private resultados(boolean ok, String message, String valor){
            this.ok = ok;
            this.message = message;
            this.valor = valor;
        }
        public static resultados exito(String valor) { return new resultados(true, null, valor); }
        public static resultados falla(String mensaje) { return new resultados(false, mensaje, null); }
        @Override public String toString(){
            return ok ? "OK" + (valor != null ? "[" + valor + "]" : "") : "ERROR [" + message + "]";
        }
    }
    //PUSH
    public resultados push(String input){
        if (input == null || input.trim().isEmpty()) {
            return resultados.falla("Entrada vacia");
        }
        if (isFull()) {
            return resultados.falla("pila llena");
        }
        data[++top] = input;    // primero avanza top y luego se escribe
        return resultados.exito(input);
    }
    //POP
    public resultados pop(){
        if (isEmpty()) {
            return resultados.falla("Pila vacia");
        }
        String value = data[top];
        data[top] = null;
        top--;
        return resultados.exito(value);
    }
    //MOSTRAR
    public resultados peek(){
        if (isEmpty()) {
            return resultados.falla("Pila vacia");
        }
        return resultados.exito(data[top]);
    }
    public String[] toSnapshot(){
        String[] out = new String[size()];
        for (int i = 0; i < out.length; i++){
            out[i] = data[i];
        }
        return out;
    }
    @Override public String toString(){
        return Arrays.toString(toSnapshot());
    }
}