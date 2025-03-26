import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

     @Test
     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }

     @Test
    void addFirstTest(){
         Deque61B<Integer> deque = new ArrayDeque61B<>();
         deque.addFirst(4);
         deque.addFirst(5);
         deque.addFirst(6);
         deque.addFirst(7);
         deque.addFirst(8);
         deque.addFirst(9);
         deque.addFirst(10);
         deque.addFirst(11);
         assertThat(deque.toList()).containsExactly(7, 6, 5, 4, 11, 10, 9, 8).inOrder();
     }

     @Test
    void addLastTest(){
         Deque61B<Integer> deque = new ArrayDeque61B<>();
         deque.addLast(4);
         deque.addLast(5);
         deque.addLast(6);
         deque.addLast(7);
         deque.addLast(8);
         deque.addLast(9);
         deque.addLast(10);
         deque.addLast(11);
         assertThat(deque.toList()).containsExactly(8, 9, 10, 11, 4, 5, 6, 7).inOrder();
     }

     @Test
    void addFirstAndLastTest(){
         Deque61B<Integer> deque = new ArrayDeque61B<>();
         deque.addFirst(4);
         deque.addFirst(5);
         deque.addFirst(6);
         deque.addFirst(7);
         deque.addLast(8);
         deque.addLast(9);
         deque.addLast(10);
         deque.addFirst(11);
         assertThat(deque.toList()).containsExactly(7, 6, 5, 4, 8, 9, 10, 11).inOrder();
     }

     @Test
    void getElementTest(){
         Deque61B<Integer> deque = new ArrayDeque61B<>();
         deque.addFirst(4);
         deque.addFirst(5);
         assertThat(deque.get(3)).isEqualTo(4);
     }

     @Test
    void getInvaildIndexTest(){
         Deque61B<Integer> deque = new ArrayDeque61B<>();
         deque.addFirst(4);
         assertThat(deque.get(-3)).isEqualTo(null);
     }
     @Test
     void getZeroTest(){
         Deque61B<Integer> deque = new ArrayDeque61B<>();
         deque.addFirst(4);
         deque.addLast(5);
         assertThat(deque.get(0)).isEqualTo(null);
     }

     @Test
    void sizeTest(){
         Deque61B<Integer> deque = new ArrayDeque61B<>();
         deque.addFirst(4);
         deque.addLast(5);
         assertThat(deque.size()).isEqualTo(2);
     }
     @Test
     void EmptysizeTest(){
         Deque61B<Integer> deque = new ArrayDeque61B<>();
         assertThat(deque.size()).isEqualTo(0);
     }
     @Test
     void sizeTest2(){
         Deque61B<Integer> deque = new ArrayDeque61B<>();
         deque.addFirst(4);
         deque.addLast(5);
         deque.addFirst(6);
         deque.addLast(7);
         deque.addLast(8);
         deque.addLast(9);
         deque.addLast(10);
         assertThat(deque.size()).isEqualTo(7);
     }
    @Test
    void isEmptyTest(){
         Deque61B<Integer> deque = new ArrayDeque61B<>();
         deque.addFirst(4);
         assertThat(deque.isEmpty()).isFalse();
    }
    @Test
    void toListTest(){
         Deque61B<Integer> deque = new ArrayDeque61B<>();
         deque.addFirst(4);
         assertThat(deque.toList()).containsExactly(null, null, null, 4, null, null, null, null).inOrder();
    }
    @Test
    void removeFirstTest(){
         Deque61B<Integer> deque = new ArrayDeque61B<>();
         deque.addFirst(4);
         deque.addFirst(5);
         deque.addFirst(6);
         deque.addFirst(7);
         deque.addFirst(8);
         deque.removeFirst();
         assertThat(deque.toList()).containsExactly(7, 6, 5, 4, null, null, null, null).inOrder();
    }

    @Test
    void resizeTest(){
         ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
         deque.addFirst(4);
         deque.addFirst(5);
         deque.addFirst(6);
         deque.resize(4);
         assertThat(deque.toList()).containsExactly(6, 5, 4, null).inOrder();
    }
}
