package cotizacion.beans;

public class ResumenCredito {
    private String nombreSocio;
    private String totalCredito;
    private String tasaInteres;
    private String valorCuota;
    
    public String getNombreSocio() {
        return this.nombreSocio;
    }
    public void setNombre(String nombre) {
        this.nombreSocio = nombre;
    }
    public String getTotalCredito() {
        return this.totalCredito;
    }   
    public void setTotalCredito(String total) {
        this.totalCredito = total;
    }
    public String getTasaInteres() {
        return this.tasaInteres;
    }   
    public void setTasaInteres(String interes) {
        this.tasaInteres = interes;
    }   
    public String getValorCuota() {
        return this.valorCuota;
    }   
    public void setValorCuota(String cuota) {
        this.valorCuota = cuota;
    }
}
