package bpc.test;

import javassist.NotFoundException;

import java.util.*;
import java.util.logging.Logger;

public class ListForKingService {
    private final Logger log = Logger.getLogger(ListForKingService.class.getName());

    private final String PRIMARY_REGEX = ":";
    private final String SECONDARY_REGEX = ",";

    private final Set<Creature> creatures;
    private final Creature king;

    public ListForKingService(List<String> pollResults) throws NotFoundException {
        creatures = new TreeSet<>();
        king = new Creature("King");
        fillCreatures(pollResults);
        setMasterServantsInCreaturesElements(pollResults);
        setKingAsMasterInCreatureElements();
    }

    private void fillCreatures(List<String> pollResults) {
        pollResults.forEach(this::extractCreaturesAndAddToCreatures);
    }

    private void setMasterServantsInCreaturesElements(List<String> pollResults) throws NotFoundException {
        for (String subList : pollResults) {
            extractCreaturesAndSetMasterAndServants(subList);
        }
    }

    private void setKingAsMasterInCreatureElements() {
        creatures.forEach(c -> {
            if(c.getMaster() == null) {
                c.setMaster(king);
                king.getServants().add(c);
            }
        });
    }

    private void extractCreaturesAndAddToCreatures(String subList) {
        String[] ar = subList.split("[" + PRIMARY_REGEX + SECONDARY_REGEX + "]");
        for (String s : ar) {
            if (getCreatureByName(creatures, s.trim()) == null) {
                creatures.add(new Creature(s.trim()));
            }
        }
    }

    private void extractCreaturesAndSetMasterAndServants(String subList) throws NotFoundException {
        String[] ar = subList.split("[" + PRIMARY_REGEX + SECONDARY_REGEX + "]");
        if(ar.length < 1) {
            return;
        }
        for (int i = 1; i < ar.length; i++) {
            Creature master = getCreatureByName(creatures, ar[0].trim());
            Creature servant = getCreatureByName(creatures, ar[i].trim());
            if(master != null && servant != null) {
                servant.setMaster(master);
                master.getServants().add(servant);
            } else {
                throw new NotFoundException("Creature not found!");
            }
        }
    }

    private Creature getCreatureByName(Set<Creature> list, String name) {
        for (Creature c: list) {
            if(c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    public Creature getKing() {
        return king;
    }

    public String getCreatureStringForPrint(Creature creature) {
        StringBuilder builder = new StringBuilder("\n" + creature.getName() + "\n");
        return getStringBuilderForPrint(creature.getServants(), builder, 1).toString();
    }

    private StringBuilder getStringBuilderForPrint(Set<Creature> list, StringBuilder builder, int tabCounter) {
        for (Creature c: list) {
            for (int i = 0; i < tabCounter; i++) {
                builder.append("\t");
            }
            builder.append(c.getName()).append("\n");
            if(!c.getServants().isEmpty()) {
                getStringBuilderForPrint(c.getServants(), builder, ++tabCounter);
                tabCounter--;
            }
        }
        return builder;
    }
}
