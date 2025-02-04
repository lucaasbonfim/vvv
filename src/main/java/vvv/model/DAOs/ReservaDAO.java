package vvv.model.DAOs;

import vvv.model.Reserva;
import vvv.model.Passageiro;
import vvv.model.ModalTransporte;
import vvv.model.PontoVenda;
import vvv.model.Funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    private final String connectionString = "jdbc:postgresql://ep-snowy-flower-a530o2l7.us-east-2.aws.neon.tech/neondb?sslmode=require";
    private final String user = "neondb_owner";
    private final String password = "sqy8BA9lNnRz";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionString, user, password);
    }

    public boolean salvar(Reserva reserva) {
        String sql = "INSERT INTO reserva (data_reserva, status_reserva, data_viagem, partida, chegada, valor, id_passageiro, id_modal, id_ponto_venda, id_funcionario) " +
                     "VALUES (CURRENT_TIMESTAMP, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            setCommonParameters(stmt, reserva);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar(Reserva reserva) {
        String sql = "UPDATE reserva SET data_reserva = CURRENT_TIMESTAMP, status_reserva = ?, data_viagem = ?, partida = ?, chegada = ?, valor = ?, passageiro_id = ?, modal_transporte_id = ?, ponto_venda_id = ?, funcionario_id = ? " +
                     "WHERE id_reserva = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            setCommonParameters(stmt, reserva);
            stmt.setLong(10, reserva.getIdReserva());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void setCommonParameters(PreparedStatement stmt, Reserva reserva) throws SQLException {
        stmt.setBoolean(1, reserva.isStatusReserva());
        stmt.setDate(2, Date.valueOf(reserva.getDataViagem()));
        stmt.setString(3, reserva.getPartida());
        stmt.setString(4, reserva.getChegada());
        stmt.setBigDecimal(5, reserva.getValor());
        stmt.setLong(6, reserva.getPassageiro().getId());
        stmt.setLong(7, reserva.getModalTransporte().getIdModal());
        stmt.setLong(8, reserva.getPontoDeVenda().getIdPontoVenda());
        stmt.setLong(9, reserva.getFuncionario().getIdFuncionario());
    }

    public Reserva buscarPorId(Long idReserva) {
        String sql = "SELECT * FROM reserva WHERE id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, idReserva);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return createReservaFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Reserva> listar() {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reserva";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                reservas.add(createReservaFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservas;
    }

    private Reserva createReservaFromResultSet(ResultSet rs) throws SQLException {
        Reserva reserva = new Reserva();
        reserva.setIdReserva(rs.getLong("id"));
        reserva.setDataReserva(rs.getTimestamp("data_reserva").toLocalDateTime());
        reserva.setStatusReserva(rs.getBoolean("status_reserva"));
        reserva.setDataViagem(rs.getDate("data_viagem").toLocalDate());
        reserva.setPartida(rs.getString("partida"));
        reserva.setChegada(rs.getString("chegada"));
        reserva.setValor(rs.getBigDecimal("valor"));

        reserva.setPassageiro(getPassageiroFromResultSet(rs));
        reserva.setModalTransporte(getModalTransporteFromResultSet(rs));
        reserva.setPontoDeVenda(getPontoVendaFromResultSet(rs));
        reserva.setFuncionario(getFuncionarioFromResultSet(rs));

        return reserva;
    }

    private Passageiro getPassageiroFromResultSet(ResultSet rs) throws SQLException {
        Passageiro passageiro = new Passageiro();
        passageiro.setId(rs.getLong("id_passageiro"));
        return passageiro;
    }

    private ModalTransporte getModalTransporteFromResultSet(ResultSet rs) throws SQLException {
        ModalTransporte modalTransporte = new ModalTransporte();
        modalTransporte.setIdModal(rs.getLong("id_modal"));
        return modalTransporte;
    }

    private PontoVenda getPontoVendaFromResultSet(ResultSet rs) throws SQLException {
        PontoVenda pontoVenda = new PontoVenda();
        pontoVenda.setIdPontoVenda(rs.getLong("id_ponto_venda"));
        return pontoVenda;
    }

    private Funcionario getFuncionarioFromResultSet(ResultSet rs) throws SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.setIdFuncionario(rs.getLong("id_funcionario"));
        return funcionario;
    }

    public boolean deletar(Long idReserva) {
        String sql = "DELETE FROM reserva WHERE id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, idReserva);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
