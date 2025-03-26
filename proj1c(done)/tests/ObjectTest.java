import deque.Deque61B;
import deque.LinkedListDeque61B;
import org.junit.jupiter.api.Test;


import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ObjectTest {
    @Test
    public void testiterator(){
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        Deque61B<String> lld2 = new LinkedListDeque61B<>();
        lld1.addLast("a");
        lld1.addLast("b");
        lld1.addLast("c");
        lld2.addLast("a");
        lld2.addLast("b");
        lld2.addLast("c");
        assertThat(lld1.equals(lld2)).isTrue();
    }
}
