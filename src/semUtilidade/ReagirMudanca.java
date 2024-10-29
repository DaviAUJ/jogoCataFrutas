package semUtilidade;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;


import java.beans.PropertyChangeListener;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * Classe genérica que permite observar mudanças em propriedades de um objeto.
 *
 * @param <T> O tipo do objeto que será escutado para mudanças.
 */
public class ReagirMudanca<T> {
    private T objetoEscutado;
    private Map<String, Object> propriedades = new HashMap<>();
    private PropertyChangeSupport suporteMudanca = new PropertyChangeSupport(this);

    /**
     * Construtor da classe ReagirMudanca.
     *
     * @param objetoEscutado O objeto cujas propriedades serão monitoradas.
     */
    public ReagirMudanca(T objetoEscutado) {
        this.objetoEscutado = objetoEscutado;


        for (Field field : objetoEscutado.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                propriedades.put(field.getName(), field.get(objetoEscutado));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Adiciona um listener para observar mudanças nas propriedades do objeto.
     *
     * @param listener O listener que será notificado sobre mudanças nas propriedades.
     */
    // Permite adicionar um listener para observar mudanças nas propriedades
    public void adicionarListener(PropertyChangeListener listener) {
        suporteMudanca.addPropertyChangeListener(listener);
    }

    /**
     * Modifica uma propriedade do objeto e dispara os listeners registrados.
     *
     * @param propriedade O nome da propriedade a ser modificada.
     * @param valor O novo valor da propriedade.
     */
    // Método para modificar propriedades e disparar os listeners
    public void setPropriedade(String propriedade, Object valor) {
        try {
            Field campo = objetoEscutado.getClass().getDeclaredField(propriedade);
            campo.setAccessible(true);

            Object valorAntigo = propriedades.get(propriedade);
            campo.set(objetoEscutado, valor);  // Modifica a propriedade no objeto

            propriedades.put(propriedade, valor);  // Atualiza o valor na lista de propriedades
            suporteMudanca.firePropertyChange(propriedade, valorAntigo, valor);  // Notifica os listeners

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtém o valor de uma propriedade específica.
     *
     * @param propriedade O nome da propriedade cujo valor será obtido.
     * @return O valor da propriedade, ou null se a propriedade não existir.
     */
    // Getter para propriedades (apenas para fins de demonstração)
    public Object getPropriedade(String propriedade) {
        return propriedades.get(propriedade);
    }

    /**
     * Obtém o objeto que está sendo escutado para mudanças.
     *
     * @return O objeto escutado.
     */
    public T getObjetoEscutado() {
        return objetoEscutado;
    }
}

