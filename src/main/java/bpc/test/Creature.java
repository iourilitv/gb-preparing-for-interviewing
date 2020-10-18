package bpc.test;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Creature implements Comparable<Creature> {

    private final String name;
    private Creature master;
    private final List<Creature> servants;

    public Creature(String name) {
        this.name = name;
        servants = new ArrayList<>();
    }

    public Creature(String name, List<Creature> servants) {
        this.name = name;
        this.servants = servants;
    }

    @Override
    public int compareTo(Creature o) {
        return this.getName().compareToIgnoreCase(o.getName());
    }

    public void addServant(Creature creature) {
        servants.add(creature);
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