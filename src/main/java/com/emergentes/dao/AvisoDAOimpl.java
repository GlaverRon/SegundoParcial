package com.emergentes.dao;

import com.emergentes.modelo.Aviso;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AvisoDAOimpl extends ConexionDB implements AvisoDAO {

    @Override
    public void insert(Aviso aviso) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO productos (descripcion,cantidad,precio,categoria) values (?,?,?,?) ";
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setString(1, aviso.getDescripcion());
            ps.setInt(2, aviso.getCantidad());
            ps.setFloat(3, aviso.getPrecio());
            ps.setString(4, aviso.getCategoria());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Aviso aviso) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE productos SET descripcion=?, cantidad=?, precio=?, categoria=? WHERE id = ?";
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setString(1, aviso.getDescripcion());
            ps.setInt(2, aviso.getCantidad());
            ps.setFloat(3, aviso.getPrecio());
            ps.setString(4, aviso.getCategoria());
            ps.setInt(3, aviso.getId());

            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();

        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar();
            String sql = "DELETE FROM productos WHERE id = ?";
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }

    }

    @Override
    public Aviso getbyId(int id) throws Exception {
        Aviso avi = new Aviso();
        try {
            this.conectar();
            String sql = "SELECT * FROM productos WHERE id = ?";
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                avi.setId(rs.getInt("id"));
                avi.setDescripcion(rs.getString("descripcion"));
                avi.setCantidad(rs.getInt("cantidad"));
                avi.setPrecio(rs.getFloat("precio"));
                avi.setCategoria(rs.getString("categoria"));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }

        return avi;
    }

    @Override
    public List<Aviso> getAll() throws Exception {
        ArrayList<Aviso> lista = new ArrayList<Aviso>();
        try {

            this.conectar();
            String sql = "SELECT * FROM productos";
            PreparedStatement ps = this.con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Aviso avi = new Aviso();

                avi.setId(rs.getInt("id"));
                avi.setDescripcion(rs.getString("descripcion"));
                avi.setCantidad(rs.getInt("cantidad"));
                avi.setPrecio(rs.getFloat("precio"));
                avi.setCategoria(rs.getString("categoria"));

                lista.add(avi);
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }

        return lista;

    }

}
