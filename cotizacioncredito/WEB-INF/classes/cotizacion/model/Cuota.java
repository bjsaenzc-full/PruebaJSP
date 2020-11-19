package cotizacion.model;

import java.text.DecimalFormat;
import java.math.RoundingMode;

public class Cuota {
    private static float valorCuota;
    private static float valorTotal;

    private static final float plazoMeses = 36; 
    private static void calcularValorTotal(float interes, float montoInicial) {
        valorTotal = montoInicial * (1 + plazoMeses * (interes/100));
    }
    private static void calcularValorCuota(float interes, float montoInicial) {
        calcularValorTotal(interes, montoInicial);
        valorCuota = valorTotal / plazoMeses;
    }
    
    public static String[] getValores(float interes, float montoInicial) {
        String[] valores = new String[2];
        calcularValorCuota(interes, montoInicial);
    
        DecimalFormat redondeo = new DecimalFormat("##.##");
        redondeo.setRoundingMode(RoundingMode.UP);

        valores[0] = redondeo.format(valorCuota);
        valores[1] = redondeo.format(valorTotal);
        return valores;
    }
}
