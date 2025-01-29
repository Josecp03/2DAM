<%-- 
    Document   : registrarCine
    Created on : 28-ene-2025, 0:01:42
    Author     : PROGRAMACION
--%>
<%@page import="dao.CineDao"%>
<%@page import="model.Cine"%>

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
                    <i class="fas fa-home"></i> Registrar cines
                </div>
                <div class="formularios formulario-cine">
                    <div>
                        <div class="titulo-formulario">
                            <h4>Inserta un nuevo cine</h4>
                        </div>

                        <!--Formulario-->
                        <form action="CineControl" method="post">
                            <div>
                                <label for="nombre-cine">Nombre: </label>
                                <input type="text" name="Nombre" placeholder="Inserta un nombre...">
                            </div>
                            <div>
                                <label for="direccion-cine">Dirección: </label>
                                <input type="text" name="Direccion" placeholder="Inserta una Dirección...">
                            </div>                              

                            <button type="submit">
                                <i class="fa-solid fa-pen-to-square" type="submit" id="boton-registrar-cine"></i>
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