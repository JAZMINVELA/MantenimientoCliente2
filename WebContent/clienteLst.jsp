
<%@page import="com.clientes.bean.ClienteBean"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; 
         charset=UTF-8">
        <title>Listado de clientes</title>
        
        <script type="text/javascript">
            function nuevo(){
                document.location = "clienteMnt.jsp";
            }
            
            function buscar(){
                var razSocial = document.getElementById
                ("txtRazSocial").value;
                alert('entrooo1');
                document.frmLst.action ="ClienteServlet?accion=
                	buscar&razSocial=" + razSocial;
                		alert('entrooo2');
                document.frmLst.submit();
                alert('entrooo3');
            }
            
            function obtenerPorId(id){
                document.frmLst.action = "ClienteServlet?accion=
                	obtenerPorId&id=" + id;
                document.frmLst.submit();
            }
            
            function eliminar(id){
                document.frmLst.action = "ClienteServlet?accion
                		=eliminar&id=" + id;
                document.frmLst.submit();
            }
        </script>
    </head>
    <body>
        <form name="frmLst" method="post">
            <table>
                <tr>
                    <td colspan="3">&nbsp;</td>
                </tr>
                <tr>
                    <td>Razón social:</td>
                    <td colspan="2"><input type="text" id="txtRazSocial"
                     size="50" /></td>
                </tr>
                <tr>
                    <td colspan="3" align="center">
                        <input type="button" value="Buscar" 
                        onclick="buscar()" id="btnBuscar" />
                        <input type="button" value="Nuevo" 
                        onclick="nuevo()" id="btnNuevo" />
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        ${msgListado}
                        ${msgPostOperacion}
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <table border="1">
                            <tr align="center">
                                <td width="125px"><b>RUC</b></td>
                                <td width="150px"><b>Razon Social</b></td>
                                <td width="150px"><b>Dirección</b></td>
                                <td width="100px"><b>Teléfono</b></td>
                                <td width="75px"><b>Operaciones</b></td>
                            </tr>
                            <%if(session.getAttribute("listaClientes") != null){
                                List<ClienteBean> lstClientes = (List<ClienteBean>)session.
                                        getAttribute("listaClientes");
                                for(int i=0; i < lstClientes.size(); i++){
                                    ClienteBean cliente = lstClientes.get(i);
                            %>
                            <tr>
                                <td><%=cliente.getRuc()%></td>
                                <td><%=cliente.getRazSocial()%></td>
                                <td><%=cliente.getDireccion()%></td>
                                <td><%=cliente.getTelefono()%></td>
                                <td>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <a onclick="obtenerPorId(<%=cliente.getId()%>)" href="#">Actualizar</a>
                                    &nbsp;&nbsp;
                                    <a onclick="eliminar(<%=cliente.getId()%>)" href="#">Eliminar</a>
                                </td>
                            </tr>
                            <%  }
                            }%>
                        </table>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
