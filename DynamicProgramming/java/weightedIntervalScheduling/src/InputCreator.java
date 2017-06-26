package weightedIntervalScheduling.src;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by rob on 6/23/17.
 * Class deputated to the creation of a random input for the problem
 * of the WeightedIntervalSchedule
 *
 */
public class InputCreator {

    private static InputCreator instance = null;

    private InputCreator(){};

    public static InputCreator getInstance(){
        if(instance==null) instance = new InputCreator();
        return instance;
    }

    /**
     * Creation of a random set of weighted jobs to schedule
     * @param size
     * @return
     */
    public ArrayList<Task> inputCreation(int size){
        ArrayList<Task> input = new ArrayList<>();
        Random rand = new Random();
        for(int i = 0; i<size;i++){
            int startTime = rand.nextInt(Constant.MAXIMUM_START_TIME);
            int finishTime = startTime + rand.nextInt(Constant.MAXIMUM_START_TIME);
            int weight = rand.nextInt(Constant.WEIGHT_POSITIVE_RANGE);
            try {
                input.add(new Task(startTime,finishTime, weight));
            } catch (TaskContructordBadFieldsException e) {
                e.printStackTrace();
            }
        }
        return input;
    }
}
