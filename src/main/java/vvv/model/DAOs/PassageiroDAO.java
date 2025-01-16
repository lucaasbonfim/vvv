package vvv.model.DAOs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import vvv.model.Passageiro;

public class PassageiroDAO {

    private String connectionString = "jdbc:postgresql://ep-snowy-flower-a530o2l7.us-east-2.aws.neon.tech/neondb?sslmode=require";

    private String user = "neondb_owner";

    private String password = "sqy8BA9lNnRz";

    public boolean salvar(Passageiro passageiro) {
        try (Connection conn = DriverManager.getConnection(connectionString, user, password)) {
            String sql = "INSERT INTO passageiro (nome, email, cpf, telefone, data_nascimento) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, passageiro.getNome());
            stmt.setString(2, passageiro.getEmail());
            stmt.setString(3, passageiro.getCpf());
            stmt.setString(4, passageiro.getTelefone());
            stmt.setDate(5, Date.valueOf(passageiro.getDataNascimento()));
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar(Passageiro passageiro) {
        try (Connection conn = DriverManager.getConnection(connectionString, user, password)) {
            String sql = "UPDATE passageiro SET nome = ?, email = ?, cpf = ?, telefone = ?, data_nascimento = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, passageiro.getNome());
            stmt.setString(2, passageiro.getEmail());
            stmt.setString(3, passageiro.getCpf());
            stmt.setString(4, passageiro.getTelefone());
            stmt.setDate(5, Date.valueOf(passageiro.getDataNascimento()));
            stmt.setLong(6, passageiro.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Passageiro> listar() {
        List<Passageiro> passageiros = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(connectionString,user,password)) {
            String sql = "SELECT * FROM passageiro";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Passageiro passageiro = new Passageiro(
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("cpf"),
                    rs.getString("telefone"),
                    rs.getDate("data_nascimento").toLocalDate()
                );

                passageiro.setId(rs.getLong(("id")));

                passageiros.add(passageiro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passageiros;
    }

    public Passageiro buscarPorId(long id) {
        Passageiro passageiro = null; // Inicializamos como null caso o ID não exista
        try (Connection conn = DriverManager.getConnection(connectionString, user, password)) {
            String sql = "SELECT * FROM passageiro WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id); // Configura o parâmetro do ID
    
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { // Verifica se há resultados
                passageiro = new Passageiro(
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("cpf"),
                    rs.getString("telefone"),
                    rs.getDate("data_nascimento").toLocalDate()
                );
    
                passageiro.setId(rs.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passageiro; // Retorna o passageiro encontrado ou null se não encontrado
    }

    public List<Passageiro> listarPassageirosPorNome(String nome) {
        List<Passageiro> passageiros = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(connectionString, user, password)) {
            String sql = "SELECT * FROM passageiro WHERE nome LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%"); // Utiliza LIKE para buscar por nome
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                Passageiro passageiro = new Passageiro(
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("cpf"),
                    rs.getString("telefone"),
                    rs.getDate("data_nascimento").toLocalDate()
                );
    
                passageiro.setId(rs.getLong("id"));
                passageiros.add(passageiro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passageiros;
    }
    
}
