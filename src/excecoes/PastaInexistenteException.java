package excecoes;

public class PastaInexistenteException extends RuntimeException {
    public PastaInexistenteException(String message) {
        super(message);
    }
}
