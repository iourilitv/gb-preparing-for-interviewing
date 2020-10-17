package bpc.test;

import java.util.List;

public class ListForKing {
    private final List<Creature> creatures;

    public ListForKing(List<Creature> creatures) {
        this.creatures = creatures;
    }

    //TODO
    public String getStringForPrint(Creature creature) {
        StringBuilder builder = new StringBuilder(creature.getName() + "\n");
        List<Creature> servants = creature.getServants();
        if(servants.isEmpty()) {
            return builder.toString();
        } else {
            servants.forEach(c -> builder.append(getStringForPrint(c)));
        }
        return builder.toString();
    }
}
