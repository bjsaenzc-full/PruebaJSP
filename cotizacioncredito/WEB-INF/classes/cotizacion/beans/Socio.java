package cotizacion.beans;

public class Socio {
    private String nombre;
    private int id;
    private float monto;
    private String tasa;
    
    public String getNombre() {
        return this.nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getID() {
        return this.id;
    }   
    public void setID(int id) {
        this.id = id;
    }
    public float getMontoDisponible() {
        return this.monto;
    }   
    public void setMontoDisponible(float monto) {
        this.monto = monto;
    }   
    public String getTasaInt() {
        return this.tasa;
    }   
    public void setTasaInt(String tasa) {
        this.tasa = tasa;
    }
}
