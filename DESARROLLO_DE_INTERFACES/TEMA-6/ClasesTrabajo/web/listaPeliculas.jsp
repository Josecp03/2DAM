<%-- 
    Document   : listaPeliculas
    Created on : 28-ene-2025, 0:01:16
    Author     : PROGRAMACION
--%>
<%@page import="dao.CineDao"%>
<%@page import="dao.PeliculaDao"%>
<%@page import="model.Pelicula"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>Gestión de Cine</title>
    <%@include file="css.jsp" %>
</head>
<body>
    <header>
        <h2>Gestión de Cine</h2>
    </header>

    <main>
        <nav>
            <div>
                <a href="listaPeliculas.jsp">Lista películas</a>
            </div>
            <div>
                <a href="registrarPelicula.jsp">Registrar película</a>
            </div>
            <div>
                <a href="registrarCine.jsp">Registrar cine</a>
            </div>
        </nav>

        <div>
            <div class="cabecera-web">
                <i class="fas fa-home"></i> Lista películas

                <div class="boton-imprimir">
                    <i class="fa-solid fa-print"></i>
                </div>
            </div>
            <div class="contenido-tabla">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Género</th>
                            <th>Estado de visualización</th>
                            <th>Cine</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Pelicula p : PeliculaDao.listar()) {%>
                        <tr>
                            <td><%= p.getId()%></td>
                            <td><%= p.getNombre()%></td>
                            <td><%= p.getGenero()%></td>
                            <td><%= p.getEstadoVisualizacion()%></td>
                            <td><%= p.getNombreCine()%></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
    </main>

    <footer>
        Alfonso Rincón - José Corrochano - Guillermo Ceca
    </footer>
</body>
</html>