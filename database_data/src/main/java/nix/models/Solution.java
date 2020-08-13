package nix.models;

public final class Solution {
    private final int problem_id;
    private final int cost;

    public Solution(int problem_id, int cost){
        this.problem_id = problem_id;
        this.cost = cost;
    }

    public int problem_id(){
        return problem_id;
    }

    public int cost(){
        return cost;
    }
}
