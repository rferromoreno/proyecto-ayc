public class Implementacion {

    private int n;
    private int[] k;
    
    // Matriz para ir guardando el costo optimal de cada subarbol
    private int[][] costo;
    // Matriz para ir guardando las raices de cada subarbol optimal
    private int[][] raiz;
    
    private static final int MAXINT = Integer.MAX_VALUE;

    public Implementacion(int[] k) {
        this.n = k.length;
        this.k = k;
    	costo = new int[n][n];
    	raiz = new int[n][n];
    }

    // Debe devolver un int con el costo para un árbol binario de búsqueda optimal
    public int costoOptimal() {

    	// Completo la matriz diagonal (casos bases).
    	// Cuando el arbol tiene un solo nodo C(i,j) cuando i=j
    	// entonces el costo es Ki*1, es decir el Ki del nodo. C(i,j) = Ki
    	// Además la raiz es el nodo mismo.
        for(int i=0; i<n; i++){
            costo[i][i] = k[i];
            raiz[i][i] = i;
        }
        
        for(int cant=2; cant<=n; cant++){
            for(int i=0; i<=n-cant; i++){
                int j = i+cant-1;             
                costo[i][j] = MAXINT;
                int p = peso(i,j);
                
                for(int r=i; r<=j; r++){                   	
                	// Calculo, cual seria el costo para cada raiz r posible 
                	// Para ello obtengo el costo de los dos subarboles hijos
                	int costoizq,costoder,cos;
                	if (r-1<i)
                		costoizq = 0;
                	else
                		costoizq = costo[i][r-1];
                	if (r+1>j)
                		costoder = 0;
                	else
                		costoder = costo[r+1][j];
                	// peso(i,j) + C[i,r-1] + C[r+1][j]
                	cos = p + costoizq + costoder;

                	// Si el costo es el minimo, lo guardo (y su raiz)
                	if(cos < costo[i][j]){
                		costo[i][j] = cos;
                		raiz[i][j] = r;
                    }
                }
            }
        }
        return costo[0][n-1];
    }
    
    /* 
     * Metodo para obtener la sumatoria de los accesos para 
     * un subconjunto de nodos C(i,j)
     */
    private int peso(int i, int j){
        int suma = 0;
        for(int ind=i; ind<=j; ind++)
            suma += k[ind];         
        return suma;
    }
    
    /* 
     * Metodo para reconstriur el Arbol Optimal.
     * Utiliza la matriz de raices (raiz)
     */
    private ArbolBinario reconstruir(int i, int j){
    	if (j<i || j>n || i<0 || i>=n){
    		return null;
    	} else{
        	int r = raiz[i][j];
        	// Si estoy en raiz[i][i], devuelvo un nodo hoja (con hijos null)
        	// Sino, significa que estoy en un nodo interno y necesito seguir 
        	// reconstruyendo el subarbol izquierdo y derecho
        	if (i==j)
        		return new ArbolBinario(r+1,null,null);
        	else
        		return new ArbolBinario(r+1,reconstruir(i,r-1),reconstruir(r+1,j));
    	}
    }

    // Se llama luego de haber invocado a costoOptimal
    // Debe devolver un árbol binario optimal representado con la clase ArbolBinario
    public ArbolBinario reconstruirSolucion() {
        return reconstruir(0,n-1);
    }
}
