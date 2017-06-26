package weightedIntervalScheduling.test;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import weightedIntervalScheduling.src.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import static org.junit.Assert.assertTrue;

/**
 * Created by rob on 6/23/17.
 */
public class WeightedIntervalScheduleTest {

    ArrayList<Task> reqs;
    WeightedIntervalSchedule wis;

    @Before
    public void pre(){
        InputCreator Ic = InputCreator.getInstance();
        reqs = Ic.inputCreation(Constant.NUMBER_OF_TASKS);
        wis = WeightedIntervalSchedule.getWeightedIntervalSchedule(reqs);
        wis.initP();
    }


    @Ignore
    public void taskSortingTest() throws TaskContructordBadFieldsException {
        int[] startTime = { 3,2,1,6};
        int[] finishTimes = {7,9,2,10};
        int[] correctOutput = {2,7,9,10};
        int[] checks = new int[4];
        boolean checkTest = true;

        reqs = new ArrayList<>();

        Task task1 = new Task(startTime[0],finishTimes[0],1);
        Task task2 = new Task(startTime[1],finishTimes[1],2);
        Task task3 = new Task(startTime[2],finishTimes[2],3);
        Task task4 = new Task(startTime[3],finishTimes[3],3);

        reqs.add(task1);
        reqs.add(task2);
        reqs.add(task3);
        reqs.add(task4);

        Iterator<Task> it = reqs.iterator();
        while (it.hasNext()){
            System.out.print( it.next().getFinishTime()+" ");
        }

        System.out.println();
        Collections.sort(reqs);

        it = reqs.iterator();
        int i = 0;
        while (it.hasNext()){
            checks[i] = it.next().getFinishTime();
            System.out.print( checks[i]+" ");
            if(correctOutput[i]!=checks[i]) checkTest = false;
            i++;
        }
        assertTrue("ArrayList not well sorted",checkTest);
    }

    @Test
    @Ignore
    /**
     * Just a visual weightedIntervalScheduling.test to check
     * the correct contruction of a WeightedIntervalSchedule object.
     */
    public void lastNonOverlappingJobIndexPTest() throws TaskContructordBadFieldsException {

        WeightedIntervalSchedule WIS = WeightedIntervalSchedule.getWeightedIntervalSchedule(reqs);
        System.out.println(Arrays.toString(WIS.initP()));
        Iterator<Task> it = reqs.iterator();
        while(it.hasNext()){
            Task t = it.next();
            System.out.print("<("+t.getStartTime()+","+t.getFinishTime()+"), "+t.getWeight()+"> ,");
        }

    }
    @Test(timeout = 5000)
    /**
     * Time complexity test for the recursive
     * implementation: a timeout is set to
     * five seconds.
     */
    public void optSolRec(){
        System.out.println("Recursive weightedIntervalScheduling.test");
        int a = wis.getOptValueRec(reqs.size()-1);
        System.out.println("opt:"+a+"; number of recursion: "+WeightedIntervalSchedule.nOfRecs);
    }
    @Test
    /**
     * Time complexity test for the Dynamic
     * programming implementation: a timeout
     * is set to five seconds.
     */
    public void optSolMemz(){
        System.out.println("Memoization weightedIntervalScheduling.test");
        int a = wis.getOptValueMemz(reqs.size()-1);
        System.out.println("opt:"+a+"; number of recursion: "+WeightedIntervalSchedule.nOfRecs);
        wis.printTasks();
    }
}