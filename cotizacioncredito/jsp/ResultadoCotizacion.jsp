<%@page language="java" contentType="text/html"%>
<%@page import="cotizacion.beans.ResumenCredito"%>
<jsp:useBean id="dataManager" scope="application" class="cotizacion.model.DataManager"/>
<jsp:useBean id="esNumero" scope="application" class="cotizacion.model.Numero"/>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <title>Resultado de la Cotizacion</title>
  </head>
<body>
<jsp:include page="Titulo.jsp" flush="true"/>
<%
  String montoInicial = request.getParameter("montoInicial");
  boolean numero = esNumero.esUnNumero(montoInicial);
  ResumenCredito datosCredito = new ResumenCredito();
  datosCredito = (ResumenCredito)dataManager.getDatosCredito(montoInicial);

  if(numero && !montoInicial.trim().equals("")) {
    if (datosCredito != null) {
%>
    <div>
      <h2>Resultado de la Cotizacion - Financiamiento S.A.S</h2>
      <table>
        <tr>
          <th>Socio que realiza el prestamo</th>
          <th>Cuota Mensual</th>
          <th>Pago total del credito</th>
          <th>Tasa de interes mensual</th>
          </tr>
        <tr>
          <td><%=datosCredito.getNombreSocio()%></td>
          <td><%=datosCredito.getValorCuota()%></td>
          <td><%=datosCredito.getTotalCredito()%></td>
          <td><%=datosCredito.getTasaInteres()%>% E.M.</td>
	      </tr>
      </table>
    </div>
<%
    }
    else {
%>    
    <h2><b>No hay socio disponible!</b></h2>
<%
    }
  }
  else {
%>
    <h2><b>Dato invalido!</b></h2>
<%
  }
%>
</body>
</html>
