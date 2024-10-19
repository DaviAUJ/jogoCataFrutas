package excecoes;

public class JogadorSemPontosDeMovimentacaoException extends RuntimeException {
    public JogadorSemPontosDeMovimentacaoException(String message) {
        super(message);
    }
}
