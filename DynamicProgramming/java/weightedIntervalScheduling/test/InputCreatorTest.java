package weightedIntervalScheduling.test;

import org.junit.Test;
import weightedIntervalScheduling.src.InputCreator;
import weightedIntervalScheduling.src.Task;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by rob on 6/23/17.
 *
 * A really simple check in which I weightedIntervalScheduling.test that the input created is random
 */
public class InputCreatorTest {
    @Test
    public void inputCreation() throws Exception {
        InputCreator ic = InputCreator.getInstance();
        ArrayList<Task> randInp1 = ic.inputCreation(9);
        ArrayList<Task> randInp2 = ic.inputCreation(9);

        Iterator<Task> it1 = randInp1.iterator();
        Iterator<Task> it2 = randInp2.iterator();

        while(it1.hasNext() && it2.hasNext()){
            System.out.println(it2.next().getFinishTime() + ";" +it1.next().getFinishTime());
        }
    }

}