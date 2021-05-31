import org.junit.Test;
import utilList.*;

import static junit.framework.TestCase.*;

public class TestGenericClone {
    @Test
    public void unequalRefs(){
        GenericList<Integer> li = new GenericList<>();
        li.add(10);
        GenericList<Integer> clone = li.clone();

        assertNotSame(li, clone);
    }

    @Test
    public void sameClass(){
        GenericList<Integer> li = new GenericList<>();
        li.add(10);
        GenericList<Integer> clone = li.clone();

        assertSame(clone.getClass(), li.getClass());
    }

    /* we did not tested x.clone().equals(x) because we it always returns false as we haven't
     * overwritten the equals and hashcode methods. Thus the default comparison will fail as it
     * compares both referenced and we already showed that this will evaluate to false.
     * We decided to not implement this functionality as we did not saw any practical reason why
     * this could be really use full.
     */
}

