public class ArbolBinario {
    private int raiz;
    private ArbolBinario izquierdo, derecho;

    // Construye un árbol a partir del valor de la raíz y sus subárboles izquierdo y derecho
    // Para especificar un árbol vacío debe utilizarse el valor null
    public ArbolBinario(int raiz, ArbolBinario izquierdo, ArbolBinario derecho) {
        this.raiz = raiz;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append('(');
        if (izquierdo != null)
            builder.append(izquierdo.toString());
        builder.append(')');

        builder.append(raiz);

        builder.append('(');
        if (derecho != null)
            builder.append(derecho.toString());
        builder.append(')');

        return builder.toString();
    }
}
