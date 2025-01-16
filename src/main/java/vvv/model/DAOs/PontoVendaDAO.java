package vvv.model.DAOs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import vvv.model.PontoVenda;


public class PontoVendaDAO {
    
    private String connectionString = "jdbc:postgresql://ep-snowy-flower-a530o2l7.us-east-2.aws.neon.tech/neondb?sslmode=require";

    private String user = "neondb_owner";

    private String password = "sqy8BA9lNnRz";


    public boolean salvar(PontoVenda pontoVenda) {
        try (Connection conn = DriverManager.getConnection(connectionString, user, password)) {
            String sql = "INSERT INTO ponto_venda (nome, localizacao) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, pontoVenda.getNome());
            stmt.setString(2, pontoVenda.getLocalizacao());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar(PontoVenda pontoVenda) {
        try (Connection conn = DriverManager.getConnection(connectionString, user, password)) {
            String sql = "UPDATE ponto_venda SET nome = ?, localizacao = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, pontoVenda.getNome());
            stmt.setString(2, pontoVenda.getLocalizacao());
            stmt.setLong(3, pontoVenda.getIdPontoVenda());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<PontoVenda> listar() {
        List<PontoVenda> pontoVendas = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(connectionString,user,password)) {
            String sql = "SELECT * FROM ponto_venda";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PontoVenda pontoVenda = new PontoVenda(
                    rs.getString("nome"),
                    rs.getString("localizacao")
                );

                pontoVenda.setIdPontoVenda(rs.getLong(("id")));

                pontoVendas.add(pontoVenda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pontoVendas;
    }

    public PontoVenda buscarPorId(long id) {
        PontoVenda pontoVenda = null;
        try (Connection conn = DriverManager.getConnection(connectionString, user, password)) {
            String sql = "SELECT * FROM ponto_venda WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
    
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                pontoVenda = new PontoVenda(
                    rs.getString("nome"),
                    rs.getString("localizacao")
                );
    
                pontoVenda.setIdPontoVenda(rs.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pontoVenda; 
    }

    public List<PontoVenda> listarPontoVendasPorNome(String nome) {
        List<PontoVenda> pontoVendas = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(connectionString, user, password)) {
            String sql = "SELECT * FROM ponto_venda WHERE nome LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%"); // Utiliza LIKE para buscar por nome
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                PontoVenda pontoVenda = new PontoVenda(
                    rs.getString("nome"),
                    rs.getString("localizacao")
                );
    
                pontoVenda.setIdPontoVenda(rs.getLong("id"));
                pontoVendas.add(pontoVenda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pontoVendas;
    }
}
