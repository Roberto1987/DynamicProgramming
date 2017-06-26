package weightedIntervalScheduling.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created by rob on 6/23/17.
 * Weight Interval Schedule problem: given a set of request with given finish time,
 * start time and weight value, we want to obtain the optimal subset of request that
 * which not overlap to each other and maximize the sum of the weights.
 */
public class WeightedIntervalSchedule {
    public static int nOfRecs=0;
    public int[] memArray;

    private ArrayList<Task> requests;
    private int[] p;

    private static WeightedIntervalSchedule weightedIntervalSchedule;

    public static WeightedIntervalSchedule getWeightedIntervalSchedule(ArrayList<Task> requests){
        if (weightedIntervalSchedule==null){
            weightedIntervalSchedule = new WeightedIntervalSchedule(requests);
        }
        return weightedIntervalSchedule;
    }

    private WeightedIntervalSchedule(ArrayList<Task> requests) {
        this.requests = requests;
        Collections.sort(this.requests);
        p = initP();
    }

    /**
     * Creation of a support array: each index i contains the index j
     * of the nearest previous job i which doesn't overlap with the task j
     * @return
     */
    public int[] initP() {
        int[] temp = new int[this.requests.size()];
        Arrays.fill(temp,-1);
        for(int i = temp.length-1;i>=0;i--){
            for(int j = i-1 ; j>=0;j--){
                if(requests.get(j).getFinishTime()<=requests.get(i).getStartTime()){
                    temp[i] = j;
                  //  System.out.println("p("+i+") = "+j);
                  //  System.out.println(requests.get(j).getFinishTime()+","+requests.get(i).getStartTime());
                    break;
                }
            }
        }

        return temp;
    }

    /**
     * Using the memoized Solution
     * @param j
     * @return
     */
    public int getOptValueMemz(int j){
        memArray = new int[requests.size()];
        Arrays.fill(memArray,-1);
        return computeOptSolutionWithMemoization(j);
    }

    /**
     * Using the recursive solution
     * @param j
     * @return
     */
    public int getOptValueRec(int j){
        memArray = new int[requests.size()];
        Arrays.fill(memArray,-1);
        return computeOptSolutionRec(j);
    }

    /**
     * Recursive implementation of the Weighted Interval Problem
     * @param j
     * @return
     */
    private int computeOptSolutionRec(int j) {
        nOfRecs++;
        if (j <= 0) {
            return 0;
        } else {
            // System.out.println("Recursion "+j);
            return (int) Math.max(requests.get(j).getWeight() + computeOptSolutionRec(p[j]), computeOptSolutionRec(j - 1));
        }
    }

    /**
     * Dynamic programming implementation of the Weighted Interval Problem
     *
     * @param j
     * @return
     */
    private int computeOptSolutionWithMemoization(int j){
        nOfRecs++;
        if (j <= 0){
            return 0;
        }
        else{
            if(memArray[j]==-1){
                memArray[j] = (int) Math.max(requests.get(j).getWeight()
                        + computeOptSolutionWithMemoization(p[j]), computeOptSolutionWithMemoization(j-1));
            }
            return memArray[j] ;
        }
    }

    public void printTasks(){
        Iterator<Task> it = requests.iterator();
        while(it.hasNext()){
            Task t = it.next();
            System.out.println("("+t.getStartTime()+","+t.getFinishTime()+"),"+t.getWeight());
        }
    }




}
