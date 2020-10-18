package bpc.test;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.TreeSet;

@Getter
@Setter
public class Creature implements Comparable<Creature> {

    private final String name;
    private Creature master;
    private Set<Creature> servants;

    public Creature(String name) {
        this.name = name;
        servants = new TreeSet<>();
    }

    @Override
    public int compareTo(Creature o) {
        return this.getName().compareToIgnoreCase(o.getName());
    }

    @Override
    public String toString() {
        String masterName = "";
        if(master != null) {
            masterName = master.getName();
        }
        return "Creature{" +
                "name='" + name + '\'' +
                ", master_name=" + masterName +
                ", servants.size()=" + servants.size() +
                '}';
    }

}