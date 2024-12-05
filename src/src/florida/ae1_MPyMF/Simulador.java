package src.florida.ae1_MPyMF;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.*;

public class Simulador extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JSpinner spnEstrucPortecPrimaria;
    private JSpinner spnEstrucProtecSecundaria;
    private JSpinner spnEstrucProtecTerciaria;
    private JSpinner spnEstrucProtecCuaternaria;
    private JButton btnSimular;
    private JLabel lblMultiprocesos;
    private JLabel lblMultihilo;

    /**
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Simulador frame = new Simulador();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * 
     */
    public Simulador() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 712, 525);
        contentPane = new JPanel();
        contentPane.setBackground(new java.awt.Color(175, 252, 223));
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Java Protein Simulation");
        lblNewLabel.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 40));
        lblNewLabel.setBounds(0, -21, 477, 114);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Estructura Proteica Primaria");
        lblNewLabel_1.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 20));
        lblNewLabel_1.setBounds(10, 122, 255, 49);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Estructura Proteica Secundaria");
        lblNewLabel_1_1.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 20));
        lblNewLabel_1_1.setBounds(10, 181, 276, 49);
        contentPane.add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_2 = new JLabel("Estructura Proteica Terciaria");
        lblNewLabel_1_2.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 20));
        lblNewLabel_1_2.setBounds(10, 243, 255, 49);
        contentPane.add(lblNewLabel_1_2);

        JLabel lblNewLabel_1_3 = new JLabel("Estructura Proteica Cuaternaria");
        lblNewLabel_1_3.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 20));
        lblNewLabel_1_3.setBounds(10, 306, 302, 49);
        contentPane.add(lblNewLabel_1_3);

        spnEstrucPortecPrimaria = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
        spnEstrucPortecPrimaria.setBounds(341, 137, 59, 26);
        contentPane.add(spnEstrucPortecPrimaria);

        spnEstrucProtecSecundaria = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
        spnEstrucProtecSecundaria.setBounds(341, 196, 59, 26);
        contentPane.add(spnEstrucProtecSecundaria);

        spnEstrucProtecTerciaria = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
        spnEstrucProtecTerciaria.setBounds(341, 258, 59, 26);
        contentPane.add(spnEstrucProtecTerciaria);

        spnEstrucProtecCuaternaria = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
        spnEstrucProtecCuaternaria.setBounds(341, 321, 59, 26);
        contentPane.add(spnEstrucProtecCuaternaria);

        lblMultiprocesos = new JLabel("...");
        lblMultiprocesos.setHorizontalAlignment(SwingConstants.CENTER);
        lblMultiprocesos.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 20));
        lblMultiprocesos.setBounds(144, 389, 373, 13);
        contentPane.add(lblMultiprocesos);

        lblMultihilo = new JLabel("...");
        lblMultihilo.setHorizontalAlignment(SwingConstants.CENTER);
        lblMultihilo.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 20));
        lblMultihilo.setBounds(98, 429, 466, 13);
        contentPane.add(lblMultihilo);

        btnSimular = new JButton("Simular");
        btnSimular.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 20));
        btnSimular.setBounds(480, 195, 122, 54);
        contentPane.add(btnSimular);

        btnSimular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int primaria = (int) spnEstrucPortecPrimaria.getValue();
                int secundaria = (int) spnEstrucProtecSecundaria.getValue();
                int terciaria = (int) spnEstrucProtecTerciaria.getValue();
                int cuaternaria = (int) spnEstrucProtecCuaternaria.getValue();

                int[] tipos = {primaria, secundaria, terciaria, cuaternaria};
                
                
                
                try {
                    double tiempoMP = ejecutarMultiproceso(tipos);
                    lblMultiprocesos.setText(String.format("Multiprocesos: %.2f s", tiempoMP));

                    double tiempoMT = ejecutarMultihilo(tipos);
                    lblMultihilo.setText(String.format("Multifill:%.2f s", tiempoMT));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error en la simulación");
                }
            }
        });
    }

    /**
     * @param tipos
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    private double ejecutarMultiproceso(int[] tipos) throws IOException, InterruptedException {
        long inicio = System.currentTimeMillis();
        List<Process> procesos = new ArrayList<>();

        for (int i = 0; i < tipos.length; i++) {
            for (int j = 0; j < tipos[i]; j++) {
                ProcessBuilder pb = new ProcessBuilder(
                        "java", "-cp", ".", "src.florida.ae1_MPyMF.SimulacioMP", String.valueOf(i + 1));
                
                procesos.add(pb.start());
            }
        }

        for (Process proceso : procesos) {
            proceso.waitFor();
        }

        long fin = System.currentTimeMillis();
        return (fin - inicio) / 1000.0;
    }

    /**
     * @param tipos
     * @return
     * @throws InterruptedException
     */
    private double ejecutarMultihilo(int[] tipos) throws InterruptedException {
        long inicio = System.currentTimeMillis();
        List<Thread> hilos = new ArrayList<>();

        for (int i = 0; i < tipos.length; i++) {
            for (int j = 0; j < tipos[i]; j++) {
                Thread hilo = new Thread(new SimulacioMT(i + 1, j + 1));
                hilos.add(hilo);
                hilo.start();
            }
        }

        for (Thread hilo : hilos) {
            hilo.join();
        }

        long fin = System.currentTimeMillis();
        return (fin - inicio) / 1000.0;
    }
}
