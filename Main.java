import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        try {
            Scanner in;
            PrintWriter out;

            if (args.length >= 1)
                in = new Scanner(new FileReader(args[0]));
            else
                in = new Scanner(System.in);

            if (args.length >= 2)
                out = new PrintWriter(new FileWriter(args[1]));
            else
                out = new PrintWriter(System.out);

            int N = in.nextInt();
            int[] k = new int[N];
            for (int i = 0; i < N; i++)
                k[i] = in.nextInt();

            Implementacion solucion = new Implementacion(k);

            int C = solucion.costoOptimal();
            out.println(C);

            ArbolBinario tree = solucion.reconstruirSolucion();
            out.println(tree);

            in.close();
            out.close();
        }
        catch (IOException e) {
            System.err.println("Error en la especificación de archivos de entrada y/o salida.");
            e.printStackTrace();
            System.exit(1);
        }

    }
}
