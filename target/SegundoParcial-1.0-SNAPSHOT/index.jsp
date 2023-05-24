<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Aviso"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    List<Aviso> avisos = (List<Aviso>) request.getAttribute("productos");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center><div> 
            <table border="2">
                
                <tr>
                    <th>SEGUNDO PARCIAL TEM-742 <br>
                        NOMBRE: Ronald Glaver Tola Zapata <br>
                        CARNET: 9905248 L.P. <br>
                    </th>
                </tr>

            </table>
        </div></center>
    <center><div> 
            <h1>Gestion de Productos</h1>
            <a href="Inicio?action=add">Nuevo producto</a><br><br>
            <table border="1">
                <tr>
                    <th>Id</th>
                    <th>Descripcion</th>
                    <th>Cantidad</th>
                    <th>Precio</th>
                    <th>Categoria</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach var="item" items="${avisos}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.descripcion}</td>
                        <td>${item.cantidad}</td>
                        <td>${item.precio}</td>
                        <td>${item.categoria}</td>
                        <td><a href="Inicio?action=edit&id=${item.id}">Editar</a></td>
                        <td><a href="Inicio?action=delete&id=${item.id}">Eliminar</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div></center>
</body>
</html>
