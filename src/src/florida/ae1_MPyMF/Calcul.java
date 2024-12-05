	package src.florida.ae1_MPyMF;
	
	public class Calcul {
		/**
	     * Realiza una simulación matemática que dura un periodo de tiempo calculado 
	     * a partir del parámetro de entrada `type`.
	     *
	     * @param type un entero que define el tiempo de simulación (en milisegundos) 
	     *             mediante la fórmula `Math.pow(5, type)`.
	     * @return el último valor calculado de la función matemática durante la simulación.
	     */
	    public static double simulation(int type) {
	        double calc = 0.0;
	        double simulationTime = Math.pow(5, type);
	        double startTime = System.currentTimeMillis();
	        double endTime = startTime + simulationTime;
	
	        while (System.currentTimeMillis() < endTime) {
	            calc = Math.sin(Math.pow(Math.random(), 2));
	        }
	
	        return calc;
	    }
	}
