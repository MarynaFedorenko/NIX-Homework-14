package nix.models;

import java.util.Objects;

public final class Route {
    private final int id;
    private final int from_id;
    private final int to_id;
    private final int cost;

    public Route(int id, int from_id, int to_id, int cost){
        this.id = id;
        this.from_id = from_id;
        this.to_id = to_id;
        this.cost = cost;

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

    public int getCost(){
        return cost;
    }


}
