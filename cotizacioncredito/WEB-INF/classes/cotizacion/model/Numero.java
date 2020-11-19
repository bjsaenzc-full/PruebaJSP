package cotizacion.model;
import java.util.regex.Pattern;

public class Numero {
    
    // Patrón a buscar dentro del String
    private Pattern regex = Pattern.compile("-?\\d+(\\.\\d+)?");
	
    // --------------------------------------------------- //
    // -- Verifica si el String corresponde a un número -- //
    // --------------------------------------------------- //
    public boolean esUnNumero(String numero) {
        if (numero == null) {
	        return false; 
	    }
	    return regex.matcher(numero).matches();
	}
}
