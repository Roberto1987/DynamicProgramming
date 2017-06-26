package weightedIntervalScheduling.src;

/**
 * Created by rob on 6/23/17.
 *
 * Implementation of the task/request.
 * Each one has its start time,
 * finish time and weight value.
 */
public class Task implements Comparable<Task> {

    private int startTime;
    private int finishTime;
    private int weight;

    public Task(int startTime, int finishTime, int weight) throws TaskContructordBadFieldsException {
        if(startTime>finishTime || startTime<0) throw new TaskContructordBadFieldsException();
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.weight = weight;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Task o) {
        int cmp = 0;

         if(this.finishTime<o.getFinishTime()) cmp = -1;
         if(this.finishTime==o.getFinishTime()) cmp = 0;
         if(this.finishTime>o.getFinishTime()) cmp = 1;

        return cmp;
    }
}
