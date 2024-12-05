package src.florida.ae1_MPyMF;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimulacioMT implements Runnable {
    private final int tipo;
    private final int orden;
    

    /**
     * Constructor de la clase {@code SimulacioMT}.
     * 
     * @param tipo  El tipo de simulación a realizar.
     * @param orden El orden en el que se ejecuta esta simulación.
     */
    public SimulacioMT(int tipo, int orden) {
        this.tipo = tipo;
        this.orden = orden;
    }

    /**
     * Método principal que se ejecuta al iniciar el hilo. Este método crea la
     * carpeta "Pronts" si no existe, realiza la simulación y guarda el resultado en
     * un archivo.
     */
    @Override
    public void run() {
    	File carpeta = new File("Pronts");
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }
        try {
            double resultado = Calcul.simulation(tipo);
            guardarResultado("MT", tipo, orden, resultado);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Guarda el resultado de la simulación en un archivo dentro de la carpeta
     * "Pronts". El archivo sigue un formato específico en su nombre y contenido.
     * 
     * @param modo      Identificador del modo de la simulación.
     * @param tipo      Tipo de simulación ejecutada.
     * @param orden     Orden de la simulación.
     * @param resultado Resultado de la simulación.
     * @throws IOException Si ocurre un error al crear o escribir en el archivo.
     */
    private void guardarResultado(String modo, int tipo, int orden, double resultado) throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
        String filename = String.format("Pronts/PROT_%s_%d_n%d_%s.sim", modo, tipo, orden, timestamp);
        
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(timestamp + "\n");
            writer.write(new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date()) + "\n");
            writer.write(String.format("0_%.2f\n", 49.0));
            writer.write(String.valueOf(resultado) + "\n");
        }
    }

}
