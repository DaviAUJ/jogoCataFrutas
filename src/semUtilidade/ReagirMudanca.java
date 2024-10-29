package semUtilidade;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;


import java.beans.PropertyChangeListener;
import java.lang.reflect.Field;
import java.util.Map;

public class ReagirMudanca<T> {
    private T objetoEscutado;
    private Map<String, Object> propriedades = new HashMap<>();
    private PropertyChangeSupport suporteMudanca = new PropertyChangeSupport(this);

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

    // Permite adicionar um listener para observar mudanças nas propriedades
    public void adicionarListener(PropertyChangeListener listener) {
        suporteMudanca.addPropertyChangeListener(listener);
    }

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

    // Getter para propriedades (apenas para fins de demonstração)
    public Object getPropriedade(String propriedade) {
        return propriedades.get(propriedade);
    }

    public T getObjetoEscutado() {
        return objetoEscutado;
    }
}

