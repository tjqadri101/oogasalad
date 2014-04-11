package gameFactory;

import java.util.ArrayList;

public class OrderList extends ArrayList<Object> {
    //? how about making it a wildcard? 

    public OrderList() {
        super();
    }

    public OrderList(int initialSize) {
        super(initialSize);
    }

    @Override
    public boolean add(Object obj) {
        if ((obj instanceof String) || (obj instanceof Integer) || (obj instanceof Double)) {
            add(obj);
            return true;
        } else {
            throw new IllegalArgumentException("not a String or int or double");
        }
    }
    public void add(String s) {
        super.add(s);
    }
    // Question Prof.Duvall: if autoboxing and unboxing is performed here using Integer and int
    public void add(Integer s) {
        super.add(s);
    }
    
    public void add(Double s) {
        super.add(s);
    }
}
