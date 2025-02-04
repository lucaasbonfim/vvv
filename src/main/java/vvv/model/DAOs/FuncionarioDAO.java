package vvv.model.DAOs;

import vvv.model.Funcionario;
import vvv.model.PontoVenda;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    private String connectionString = "jdbc:postgresql://ep-snowy-flower-a530o2l7.us-east-2.aws.neon.tech/neondb?sslmode=require";
    private String user = "neondb_owner";
    private String password = "sqy8BA9lNnRz";

    public boolean salvar(Funcionario funcionario) {
        try (Connection conn = DriverManager.getConnection(connectionString, user, password)) {
            String sql = "INSERT INTO funcionario (nome, cpf, email, senha, tipo, id_ponto_venda) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getEmail());
            stmt.setString(4, funcionario.getSenha());
            stmt.setBoolean(5, funcionario.getCargo());
            stmt.setLong(6, funcionario.getPontoDeVenda().getIdPontoVenda());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar(Funcionario funcionario) {
        try (Connection conn = DriverManager.getConnection(connectionString, user, password)) {
            String sql = "UPDATE funcionario SET nome = ?, cpf = ?, email = ?, senha = ?, tipo = ?, id_ponto_venda = ? WHERE id_funcionario = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getEmail());
            stmt.setString(4, funcionario.getSenha());
            stmt.setBoolean(5, funcionario.getCargo());
            stmt.setLong(6, funcionario.getPontoDeVenda().getIdPontoVenda());
            stmt.setLong(7, funcionario.getIdFuncionario());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Funcionario> listar() {
        List<Funcionario> funcionarios = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(connectionString, user, password)) {
            String sql = "SELECT f.*, pv.nome AS ponto_venda_nome FROM funcionario f " +
                         "LEFT JOIN ponto_venda pv ON f.id_ponto_venda = pv.id";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setIdFuncionario(rs.getLong("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setCargo(rs.getBoolean("tipo"));

                PontoVenda pontoVenda = new PontoVenda();
                pontoVenda.setIdPontoVenda(rs.getLong("id_ponto_venda"));
                pontoVenda.setNome(rs.getString("ponto_venda_nome"));
                funcionario.setPontoDeVenda(pontoVenda);

                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionarios;
    }

    public Funcionario buscarPorId(long id) {
        Funcionario funcionario = null;
        try (Connection conn = DriverManager.getConnection(connectionString, user, password)) {
            String sql = "SELECT * FROM funcionario f WHERE f.id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setIdFuncionario(rs.getLong("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setCargo(rs.getBoolean("tipo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionario;
    }

    public List<Funcionario> buscarPorNome(String nome) {
        List<Funcionario> funcionarios = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(connectionString, user, password)) {
            String sql = "SELECT f.*, pv.nome AS ponto_venda_nome FROM funcionario f " +
                         "LEFT JOIN ponto_venda pv ON f.ponto_venda_id = pv.id WHERE LOWER(f.nome) LIKE LOWER(?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome.toLowerCase() + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setIdFuncionario(rs.getLong("id_funcionario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setCargo(rs.getBoolean("tipo"));

                PontoVenda pontoVenda = new PontoVenda();
                pontoVenda.setIdPontoVenda(rs.getLong("id_ponto_venda"));
                pontoVenda.setNome(rs.getString("ponto_venda_nome"));
                funcionario.setPontoDeVenda(pontoVenda);

                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionarios;
    }

    public boolean deletar(long id) {
        try (Connection conn = DriverManager.getConnection(connectionString, user, password)) {
            String sql = "DELETE FROM funcionario WHERE id_funcionario = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Funcionario autenticar(String email, String senha) {
        Funcionario funcionario = null;
        try (Connection conn = DriverManager.getConnection(connectionString, user, password)) {
            String sql = "SELECT * FROM funcionario WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                String senhaArmazenada = rs.getString("senha");
                if (senhaArmazenada.equals(senha)) {
                    funcionario = new Funcionario();
                    funcionario.setIdFuncionario(rs.getLong("id"));
                    funcionario.setNome(rs.getString("nome"));
                    funcionario.setCpf(rs.getString("cpf"));
                    funcionario.setEmail(rs.getString("email"));
                    funcionario.setSenha(rs.getString("senha"));
                    funcionario.setCargo(rs.getBoolean("tipo"));
    
                    PontoVenda pontoVenda = new PontoVenda();
                    pontoVenda.setIdPontoVenda(rs.getLong("id_ponto_venda"));
                    funcionario.setPontoDeVenda(pontoVenda);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionario; // Retorna null caso a autenticação falhe
    }
    
}
