package vvv.model.DAOs;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import vvv.model.Passageiro;

class PassageiroDAOTest {

    private PassageiroDAO passageiroDAO;
    private Connection mockConnection;
    private PreparedStatement mockStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    void setUp() throws Exception {
        mockConnection = mock(Connection.class);
        mockStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        // Passa a conexão mockada para o DAO
        passageiroDAO = new PassageiroDAO(mockConnection);
    }

    @Test
    void testSalvarPassageiro() throws Exception {
        Passageiro passageiro = new Passageiro("Carlos Silva", "carlos@email.com", "11122233344", "21977777777", LocalDate.of(1992, 6, 12));

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);

        boolean result = passageiroDAO.salvar(passageiro);
        assertTrue(result);
    }

    @Test
    void testBuscarPassageiroPorId() throws Exception {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getString("nome")).thenReturn("Carlos Silva");
        when(mockResultSet.getString("email")).thenReturn("carlos@email.com");
        when(mockResultSet.getDate("data_nascimento")).thenReturn(Date.valueOf("1992-06-12"));
    
        Passageiro passageiro = passageiroDAO.buscarPorId(1);
        assertNotNull(passageiro);
        assertEquals("Carlos Silva", passageiro.getNome());
        assertEquals("1992-06-12", passageiro.getDataNascimento().toString()); 
    }
    
}
