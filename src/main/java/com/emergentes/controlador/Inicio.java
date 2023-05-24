package com.emergentes.controlador;

import com.emergentes.dao.AvisoDAO;
import com.emergentes.dao.AvisoDAOimpl;
import com.emergentes.modelo.Aviso;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Inicio", urlPatterns = {"/Inicio"})
public class Inicio extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //    System.out.println("INICIANDO PROYECTO ..." + request.getParameter("action"));
        try {
            int id;
            Aviso avi = new Aviso();
            AvisoDAO dao = new AvisoDAOimpl();

            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
//
            switch (action) {
                case "add":
                    request.setAttribute("aviso", avi);
                    request.getRequestDispatcher("frmaviso.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    avi = dao.getbyId(id);

                    request.setAttribute("aviso", avi);
                    request.getRequestDispatcher("frmaviso.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    System.out.println("Listo para eliminar" + id);
                    dao.delete(id);
                    System.out.println("eliminado " + id);
                    response.sendRedirect("Inicio");
                    break;
                case "view":
                    List<Aviso> lista = dao.getAll();
                    request.setAttribute("avisos", lista);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    break;

                default:

                    break;
            }
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AvisoDAO dao = new AvisoDAOimpl();
        int id = Integer.parseInt(request.getParameter("id"));
        String descripcion = request.getParameter("descripcion");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        float precio = Integer.parseInt(request.getParameter("precio"));
        String categoria = request.getParameter("categoria");

        Aviso avi = new Aviso();
        avi.setId(id);
        avi.setDescripcion(descripcion);
        avi.setCantidad(cantidad);
        avi.setPrecio(precio);
        avi.setCategoria(categoria);

        try {
            if (id == 0) {
                // insertar un registro
                dao.insert(avi);

            } else {
                //actualizar un registro
                dao.update(avi);
            }
        } catch (Exception ex) {
            System.out.println("ERROR al guardar datos..");
        }

        response.sendRedirect("Inicio");

    }

}
