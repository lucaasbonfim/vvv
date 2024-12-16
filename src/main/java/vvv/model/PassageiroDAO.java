package vvv.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassageiroDAO {


    public List<Passageiro> buscarTodos() {
        List<Passageiro> passageiros = new ArrayList<>();
        String sql = "SELECT * FROM passageiro";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                    Passageiro passageiro = new Passageiro(
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("cpf"),
                    rs.getString("telefone"),
                    rs.getDate("data_nascimento").toLocalDate()
                );
                passageiros.add(passageiro);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar passageiros: " + e.getMessage());
        }
        return passageiros;
    }


    public Passageiro buscarPorId(long id) {
        String sql = "SELECT * FROM passageiro WHERE id = ?";
        Passageiro passageiro = null;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                    passageiro = new Passageiro(
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("cpf"),
                    rs.getString("telefone"),
                    rs.getDate("data_nascimento").toLocalDate()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar passageiro por ID: " + e.getMessage());
        }
        return passageiro;
    }


    public boolean salvar(Passageiro passageiro) {
        String sql = "INSERT INTO passageiro (nome, email, cpf, telefone, data_nascimento) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, passageiro.getNome());
            stmt.setString(2, passageiro.getEmail());
            stmt.setString(3, passageiro.getCpf());
            stmt.setString(4, passageiro.getTelefone());
            stmt.setDate(5, Date.valueOf(passageiro.getDataNascimento()));

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar passageiro: " + e.getMessage());
        }
    }


    public void atualizar(Passageiro passageiro) {
        String sql = "UPDATE passageiro SET nome = ?, email = ?, cpf = ?, telefone = ?, data_nascimento = ? WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, passageiro.getNome());
            stmt.setString(2, passageiro.getEmail());
            stmt.setString(3, passageiro.getCpf());
            stmt.setString(4, passageiro.getTelefone());
            stmt.setDate(5, Date.valueOf(passageiro.getDataNascimento()));
            stmt.setLong(6, passageiro.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar passageiro: " + e.getMessage());
        }
    }


    public void excluir(long id) {
        String sql = "DELETE FROM passageiro WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir passageiro: " + e.getMessage());
        }
    }
}
