package vvv.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class PassageiroTest {
    
    @Test
    void testPassageiroCreation() {
        Passageiro passageiro = new Passageiro("João Silva", "joao@email.com", "12345678900", "21999999999", LocalDate.of(1990, 5, 20));

        assertEquals("João Silva", passageiro.getNome());
        assertEquals("joao@email.com", passageiro.getEmail());
        assertEquals("12345678900", passageiro.getCpf());
        assertEquals("21999999999", passageiro.getTelefone());
        assertEquals(LocalDate.of(1990, 5, 20), passageiro.getDataNascimento());
    }

    @Test
    void testSetters() {
        Passageiro passageiro = new Passageiro();
        passageiro.setNome("Maria Souza");
        passageiro.setEmail("maria@email.com");
        passageiro.setCpf("09876543211");
        passageiro.setTelefone("21988888888");
        passageiro.setDataNascimento(LocalDate.of(1985, 8, 15));

        assertEquals("Maria Souza", passageiro.getNome());
        assertEquals("maria@email.com", passageiro.getEmail());
        assertEquals("09876543211", passageiro.getCpf());
        assertEquals("21988888888", passageiro.getTelefone());
        assertEquals(LocalDate.of(1985, 8, 15), passageiro.getDataNascimento());
    }
}
