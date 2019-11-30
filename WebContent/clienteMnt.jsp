<%@page import="com.clientes.bean.ClienteBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
    ClienteBean cliente = (ClienteBean) session.getAttribute("clienteActualizar");
    cliente = cliente == null ? new ClienteBean() : cliente;
    String ruc = cliente.getRuc() == null ? "" : cliente.getRuc();
    String razSocial = cliente.getRazSocial() == null ? "" : cliente.getRazSocial();
    String direccion = cliente.getDireccion() == null ? "" : cliente.getDireccion();
    String telefono = cliente.getTelefono() == null ? "" : cliente.getTelefono();
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mantenimiento de clientes</title>
        <script type="text/javascript">
        
            function insertar(){
                var ruc = document.getElementById("txtRuc").value;
                var razSocial = document.getElementById("txtRazSocial").value;
                if(ruc == "" || razSocial == ""){
                    alert("Campos RUC y Razón Social son obligatorios.")
                }else{
                    var direccion = document.getElementById("txtDireccion").value;
                    var telefono = document.getElementById("txtTelefono").value;
                    document.frmMnt.action = "ClienteServlet?accion=insertar&ruc=" + ruc + 
                        "&razSocial=" + razSocial + "&direccion=" + direccion + "&telefono=" + telefono;
                    document.frmMnt.submit();
                }
            }
            
            function actualizar(id){
                var ruc = document.getElementById("txtRuc").value;
                var razSocial = document.getElementById("txtRazSocial").value;
                if(ruc == "" || razSocial == ""){
                    alert("Campos RUC y Razón Social obligatorios.")
                }else{
                    var direccion = document.getElementById("txtDireccion").value;
                    var telefono = document.getElementById("txtTelefono").value;
                    document.frmMnt.action = "ClienteServlet?accion=actualizar&id=" + id +
                        "&ruc=" + ruc + "&razSocial=" + razSocial + 
                        "&direccion=" + direccion + "&telefono=" + telefono;
                    document.frmMnt.submit();
                }
            }
            
            function cancelar(){
                document.location = "clienteLst.jsp";
            }
            
        </script>
    </head>
    <body>
        <form name="frmMnt" method="post">
            <table>
                <tr>
                    <td>RUC:</td>
                    <td><input type="text" id="txtRuc" value="<%=ruc%>" /></td>
                </tr>
                <tr>
                    <td>Razón social:</td>
                    <td><input type="text" id="txtRazSocial" value="<%=razSocial%>" /></td>
                </tr>
                <tr>
                    <td>Dirección:</td>
                    <td><input type="text" id="txtDireccion" value="<%=direccion%>" /></td>
                </tr>
                <tr>
                    <td>Teléfono:</td>
                    <td><input type="text" id="txtTelefono" value="<%=telefono%>" /></td>
                </tr>
                <tr>
                    <td>
                        <input type="button" value="Cancelar" onclick="cancelar()" id="btnCancelar" />
                    </td>
                        <%if(session.getAttribute("clienteActualizar") == null){%>
                        ${clienteActualizar}
                    <td>
                        <input type="button" value="Insertar" onclick="insertar()" id="btnInsertar" />
                    </td>
                        <%}else{%>
                    <td>
                        <input type="button" value="Actualizar" onclick="actualizar(<%=cliente.getId()%>)" 
                               id="btnActualizar" />
                    </td>
                        <%}%>
                </tr>
            </table>
        </form>
    </body>
</html>
