package utilitarios;

import java.util.Stack;

public class FixedStack<T> extends Stack<T> {
    private final int tamMaximo;

    public FixedStack(int tamMaximo) {
        super();
        this.tamMaximo = tamMaximo;
    }

    @Override
    public T push(T object) throws StackOverflowError {
        if(isFull()) {
            throw new StackOverflowError();
        }

        return super.push(object);
    }
    
    public boolean isFull() {
        return size() == tamMaximo;
    }
}
