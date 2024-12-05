package src.florida.ae1_MPyMF;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimulacionMP {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Error: tipo de proteína no especificado.");
            return;
        }

        File carpeta = new File("Prots");
        if (!carpeta.exists()) {
          carpeta.mkdir();
        }

        int tipo = Integer.parseInt(args[0]);
        try {
            double resultado = Calcul.simulation(tipo);
            guardarResultado("MP", tipo, resultado);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Guarda un resultado en un archivo con un formato y nombre específicos.
     * 
     * El método genera un archivo en el directorio "Prots" con un nombre que incluye 
     * el modo, tipo y un sello de tiempo (`timestamp`). Dentro del archivo, se escribe
     * el sello de tiempo, un valor fijo, y el resultado proporcionado.
     * 
     * @param modo   Un identificador textual que representa el modo de operación
     * @param tipo   Un valor entero que identifica el tipo de operación.
     * @param resultado El valor numérico (de tipo `double`) que se desea guardar en el archivo.
     * 
     */
    private static void guardarResultado(String modo, int tipo, double resultado) throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
        String filename = String.format("PROT_%s_%d_%s.sim", modo, tipo, timestamp);
        File file = new File("Prots", filename);

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(timestamp + "\n");
            writer.write(new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date()) + "\n");
            writer.write(String.format("0_%.2f\n", 49.0));
            writer.write(String.valueOf(resultado) + "\n");
        }

        System.out.println("Resultado guardado en: " + file.getAbsolutePath());
    }
}
