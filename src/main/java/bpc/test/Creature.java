package bpc.test;

import java.util.ArrayList;
import java.util.List;

public class Creature implements Comparable<Creature> {
    public static final Creature emptyObject = createEmptyObject();

    private static Creature createEmptyObject() {
        Creature creature = new Creature("Empty");
        creature.setMaster(emptyObject);
        return creature;
    }

    private final String name;
    private Creature master;
    private final List<Creature> servants = new ArrayList<>();

    public Creature(String name) {
        this.name = name;
        master = emptyObject;
    }

    @Override
    public int compareTo(Creature o) {
        return this.getName().compareToIgnoreCase(o.getName());
    }

    public Creature getMaster() {
        return master != null ? master : emptyObject;
    }

    public Creature getEmptyObject() {
        return emptyObject;
    }

    public String getName() {
        return name;
    }

    public void setMaster(Creature master) {
        this.master = master;
    }

    public List<Creature> getServants() {
        return servants;
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

//    public String getStringForPrint(Creature creature) {
//        StringBuilder builder = new StringBuilder("\n" + creature.name + "\n");
//        if(creature.getServants().isEmpty()) {
//            return builder.toString();
//        } else {
//            servants.forEach(c -> {
////                builder.append("\n\t").append(c.name);
//                getStringForPrint(c);
//            });
//        }
//    }

}