package deque;

import java.util.ArrayDeque;
import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque61B(Comparator<T> c){
        comparator = c;
    }

    public T Max(){
        if(this.isEmpty()){
            return null;
        }

        T max = get(Math.floorMod(retnextfront() + 1, retcapacity()));
        for(T item : this){
            if(comparator.compare(item, max) > 0){
                max = item;
            }
        }
        return max;
    }

    public T max(Comparator<T> c){
        T max = null;
        for(T item : this){
            if(c.compare(item, max) > 0){
                max = item;
            }
        }
        return max;
    }
}
