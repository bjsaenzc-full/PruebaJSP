package cotizacion.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import cotizacion.beans.ResumenCredito;
import cotizacion.beans.Socio;

/* Accede a la base de datos de socios y retorna los
socios que cumplan el criterio de seleccion
*/
public class LeerSocio {
    public static boolean listaVacia = true;
    // Lee la base de datos, y retorna un arreglo con 
    // los socios que cumplan el criterio
    private static ArrayList<Socio> leerSocios(DataManager dataManager, String monto) {
        ArrayList<Socio> socios = new ArrayList<Socio>();
        Connection conexion = dataManager.estableceConexion();
        if(conexion != null) {
            try {
                Statement query = conexion.createStatement();
                String sql_query = "SELECT socioID, nombre, tasaDeInteres, montoMaxDisp FROM datos "
                    + "WHERE montoMaxDisp < " + monto.trim() + " ORDER BY tasaDeInteres;";
                try {
                    ResultSet rs = query.executeQuery(sql_query);
                    try {
                        while (rs.next()) {
                            listaVacia = false;
                            Socio unSocio = new Socio();
                            unSocio.setID(rs.getInt(1));
                            unSocio.setNombre(rs.getString(2));
                            unSocio.setTasaInt(rs.getString(3));
                            unSocio.setMontoDisponible(rs.getFloat(4));
                            socios.add(unSocio);
                        }
                    }
                    finally {
                        rs.close();
                    }
                }
                finally {
                    query.close();
                }
            }
            catch (SQLException exc) {
                System.out.println("No hay socios disponibles");
            }
            finally {
                dataManager.cerrarConexion(conexion);
            }
        }
        return socios;
    }

    // Retorna el primer elemento de la lista de socios, 
    // puesto que es el que cumple el criterio de ser el de 
    // menor tasa de interés
    public static ResumenCredito socioAdecuado(DataManager dataManager, String montoInicial) {
        ArrayList<Socio> socios = new ArrayList<Socio>();
        Socio socioLeido = null;
        ResumenCredito datosCredito = new ResumenCredito();
        socios = leerSocios(dataManager, montoInicial);
        if(!listaVacia) {
            socioLeido = socios.get(0);
            String[] valoresCuota = Cuota.getValores(Float.parseFloat(socioLeido.getTasaInt()), Float.parseFloat(montoInicial));
            datosCredito.setNombre(socioLeido.getNombre());
            datosCredito.setTasaInteres(socioLeido.getTasaInt());
            datosCredito.setValorCuota(valoresCuota[0]);
            datosCredito.setTotalCredito(valoresCuota[1]);
        }
        else {
            datosCredito = null;
        }
        return datosCredito;
    }
}