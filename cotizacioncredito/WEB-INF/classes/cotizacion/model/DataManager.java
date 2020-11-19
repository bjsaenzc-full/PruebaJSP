package cotizacion.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import cotizacion.beans.ResumenCredito;;

public class DataManager {
    private String dataBase_URL = "";
    private String dataBase_User = "";
    private String dataBase_Pass = "";

    // --------------------------------------------------- //
    // -- Parámetros de la Base de datos, para conexión -- //
    // --------------------------------------------------- //
    public void setDBUrl(String dbURL) {
        this.dataBase_URL = dbURL;
    }
    public String getDBUrl() {
        return this.dataBase_URL;
    }
    public void setDBUser(String dbUsuario) {
        this.dataBase_User = dbUsuario;
    }
    public String getDBUser() {
        return this.dataBase_User;
    }
    public void setDBPass(String dbPass) {
        this.dataBase_Pass = dbPass;
    }
    public String getDBPass() {
        return this.dataBase_Pass;
    }
    // -------------------------------------------------------------- //
    // -- Métodos para establecer la conexión con la base de datos -- //
    // -------------------------------------------------------------- //
    public Connection estableceConexion() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(getDBUrl(), getDBUser(), getDBPass());
        }
        catch (SQLException exc) {
            System.out.println("Error de conexión" + exc.getMessage());
        }
        return conexion;
    }
    public void cerrarConexion(Connection conexion){
        if(conexion != null) {
            try {
                conexion.close();
            }
            catch (SQLException exc) {
            }
        }
    }
    // ---------------------------------- //
    // -- Operaciones sobre los Socios -- //
    // ---------------------------------- //
    // Extrae los datos del socio adecuado y  calcula los
    // montos del crédito
    public ResumenCredito getDatosCredito(String montoInicial) {
        return LeerSocio.socioAdecuado(this, montoInicial);
    }
}
