package nix.models;

import java.util.Objects;

public final class Location {
    private final int id;
    private final String name;

    public Location(int id, String name){
        this.id = Objects.requireNonNull(id);
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

}
