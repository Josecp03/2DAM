<%-- 
    Document   : registrarPelicula
    Created on : 28-ene-2025, 0:01:33
    Author     : PROGRAMACION
--%>
<%@page import="model.Cine"%>
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
                    <i class="fas fa-home"></i> Registrar películas
                </div>
                <div class="formularios formulario-pelicula">
                    <div>
                        <div class="titulo-formulario">
                            <h4>Inserta una nueva película</h4>
                        </div>

                        <!--Formulario-->
                        <form action="PeliculaControl" method="post">
                            <div>
                                <label for="Nombre">Nombre: </label>
                                <input type="text" name="Nombre" placeholder="Inserta un nombre...">
                            </div>
                            <div>
                                <label for="Genero">Género: </label>
                                <input type="text" name="Genero" placeholder="Inserta un género...">
                            </div>
                            <div>
                                <label for="EstadoVisualizacion">¿Has visto esta película? </label>
                                Si <input type="radio" name="EstadoVisualizacion" value="Vista" id="vista" style="margin-right: 10px; margin-left: 5px; cursor: pointer;">
                                No <input type="radio" name="EstadoVisualizacion" value="No Vista"id="noVista" style="cursor: pointer; margin-left: 5px;">
                            </div>

                            <div>
                                <label for="CineID">Cine asociado: </label>

                                <select name="CineID" style="cursor: pointer;" required>
                                    <option value="0">Seleccione un cine</option>
                                    <% for (Cine c : CineDao.listar()) {%>
                                    <option value="<%=c.getId()%>"><%=c.getNombre()%></option>
                                    <%}%>
                                </select>

                            </div> 

                            <button>
                                <i class="fa-solid fa-pen-to-square" type="submit" id="boton-registrar-pelicula"></i>
                                REGISTRAR
                            </button> 

                            <div>
                                <%=(request.getAttribute("mensaje") != null ? request.getAttribute("mensaje") : "")%>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </main>

        <footer>
            Alfonso Rincón - José Corrochano - Guillermo Ceca
        </footer>
    </body>
</html>