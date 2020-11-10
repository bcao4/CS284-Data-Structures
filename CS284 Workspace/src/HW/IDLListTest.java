package HW;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IDLListTest {

    @Test
    public void test3() {
        IDLList<Integer> s = new IDLList<Integer>();

        s.append(1);
        s.append(2);

        try {
            s.add(3,2);
            assert(false);
        } catch (IndexOutOfBoundsException e) {
            assert(true);
        }


    }
}