package nix.models;

public final class Problem {
    private final int id;
    private final int from_id;
    private final int to_id;

    public Problem(int id, int from_id, int to_id){
        this.id = id;
        this.from_id = from_id;
        this.to_id = to_id;
    }

    public int getId(){
        return id;
    }

    public int getFrom_id(){
        return from_id;
    }

    public int getTo_id(){
        return to_id;
    }

}
