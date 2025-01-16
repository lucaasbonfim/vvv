package vvv.model.DAOs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import vvv.model.ModalTransporte;

public class ModalDAO {
    private String connectionString = "jdbc:postgresql://ep-snowy-flower-a530o2l7.us-east-2.aws.neon.tech/neondb?sslmode=require";

    private String user = "neondb_owner";

    private String password = "sqy8BA9lNnRz";

    public boolean salvar(ModalTransporte modal) {
        try (Connection conn = DriverManager.getConnection(connectionString, user, password)){
            String sql = "INSERT INTO modal (modelo, capacidade, ano_fabricacao, tipo, ativo) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, modal.getModelo());
            stmt.setInt(2, modal.getCapacidade());
            stmt.setInt(3, modal.getAnoFabricacao());
            stmt.setString(4, modal.getTipo());
            stmt.setBoolean(5, modal.getAtivo());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar(ModalTransporte modal) {
        try (Connection conn = DriverManager.getConnection(connectionString, user, password)){
            String sql = "UPDATE modal SET modelo = ?, capacidade = ?, ano_fabricacao = ?, tipo = ? ativo = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, modal.getModelo());
            stmt.setInt(2, modal.getCapacidade());
            stmt.setInt(3, modal.getAnoFabricacao());
            stmt.setString(4, modal.getTipo());
            stmt.setBoolean(5, modal.getAtivo());
            stmt.setLong(5, modal.getIdModal());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ModalTransporte> listar() {
        List<ModalTransporte> modais = new ArrayList<>();

    try (Connection conn = DriverManager.getConnection(connectionString,user,password)) {
            String sql = "SELECT * FROM modal";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ModalTransporte modal = new ModalTransporte(
                    rs.getString("modelo"),
                    rs.getInt("capacidade"),
                    rs.getInt("ano_fabricacao"),
                    rs.getString("tipo"),
                    rs.getBoolean("ativo")
                );

                modal.setIdModal(rs.getLong(("id")));

                modais.add(modal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modais;
    }

    public ModalTransporte buscarPorId(long id) {
        ModalTransporte modal = null;

        try (Connection conn = DriverManager.getConnection(connectionString, user, password)) {
            String sql = "SELECT * FROM modal WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id); // Configura o parâmetro do ID
    
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { // Verifica se há resultados
                modal = new ModalTransporte(
                    rs.getString("modelo"),
                    rs.getInt("capacidade"),
                    rs.getInt("ano_fabricacao"),
                    rs.getString("tipo"),
                    rs.getBoolean("ativo")
                );
    
                modal.setIdModal(rs.getLong("idModal"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modal; // Retorna o passageiro encontrado ou null se não encontrado
    }

    public List<ModalTransporte> listarModaisPorNome(String nome) {
        String sql = "SELECT * FROM modal WHERE LOWER(modelo) LIKE LOWER(?)";
        List<ModalTransporte> modais = new ArrayList<>();
    
        try (Connection conn = DriverManager.getConnection(connectionString, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            // Adiciona o caractere curinga '%' ao final para buscar pelas iniciais
            stmt.setString(1, nome.toLowerCase() + "%");
    
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                // Inicializa o objeto ModalTransporte com os dados do ResultSet
                ModalTransporte modal = new ModalTransporte(
                    rs.getString("modelo"),
                    rs.getInt("capacidade"),
                    rs.getInt("ano_fabricacao"),
                    rs.getString("tipo"),
                    rs.getBoolean("ativo")
                );
                modais.add(modal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return modais;
    }

}


