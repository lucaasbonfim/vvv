package vvv.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassageiroDAO {

    public boolean salvar(Passageiro passageiro) {
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://ep-snowy-flower-a530o2l7.us-east-2.aws.neon.tech/neondb?sslmode=require", "neondb_owner", "sqy8BA9lNnRz")) {
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

    public List<Passageiro> listar() {
        List<Passageiro> passageiros = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:seubanco", "usuario", "senha")) {
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
                passageiros.add(passageiro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passageiros;
    }
}
