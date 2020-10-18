package bpc.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class ListForKingService {
    private final Logger log = Logger.getLogger(ListForKingService.class.getName());

    private final String PRIMARY_REGEX = ":";
    private final String SECONDARY_REGEX = ",";

    private final List<Creature> creaturesTemp = new ArrayList<>();
    private final List<Creature> creatures = new ArrayList<>();
    private final Creature king = new Creature("King", creatures);

    public ListForKingService(List<String> pollResults) {
        fillCreaturesTemp(pollResults);
        fillAndSortCreatures();
    }

    private void fillCreaturesTemp(List<String> pollResults) {
        pollResults.forEach(s -> {
            if(s.contains(PRIMARY_REGEX)) {
                extractCreaturesAndAddToCreaturesTemp(s);
            } else {
                getCreatureInCreaturesTempByNameOrCreateNewAndAdd(s.trim());
            }
        });
    }

    private void fillAndSortCreatures() {
        creaturesTemp.forEach(creature -> {
            if(creature.getMaster() == null) {
                creature.setMaster(king);
                king.getServants().add(creature);
            }
        });
        sortList(creatures);
        log.info("*** creatures= " + creatures);
    }

    private void sortList(List<Creature> list) {
        if(!list.isEmpty()) {
            Collections.sort(list);
            list.forEach(c -> sortList(c.getServants()));
        }
    }

    private void extractCreaturesAndAddToCreaturesTemp(String subList) {
        String[] ar = subList.split(PRIMARY_REGEX);
        Creature primary = getCreatureInCreaturesTempByNameOrCreateNewAndAdd(ar[0].trim());

                subList = ar[1].trim();
        ar = subList.split(SECONDARY_REGEX);
        for (String name : ar) {
            createServantAndAddToMasterServants(primary, name.trim());
        }
    }

    private void createServantAndAddToMasterServants(Creature master, String name) {
        Creature creature = getCreatureInCreaturesTempByNameAndDeleteOrCreateNew(name);
        creature.setMaster(master);
        master.addServant(creature);
    }

    private Creature getCreatureInCreaturesTempByNameOrCreateNewAndAdd(String name) {
        Creature creature = getCreatureByName(creaturesTemp, name);
        if(creature == null) {
            creature = new Creature(name);
            creaturesTemp.add(creature);
        }
        return creature;
    }

    private Creature getCreatureInCreaturesTempByNameAndDeleteOrCreateNew(String name) {
        Creature creature = getCreatureByName(creaturesTemp, name);
        if(creature == null) {
            creature = new Creature(name);
        } else {
            creaturesTemp.remove(creature);
        }
        return creature;
    }

    private Creature getCreatureByName(List<Creature> list, String name) {
        for (Creature c: list) {
            boolean flag = c.getName().equals(name);
            if(flag) {
                return c;
            }
            if(!c.getServants().isEmpty()) {
                Creature out = getCreatureByName(c.getServants(), name);
                if(out != null) {
                    return out;
                }
            }
        }
        return null;
    }

    public String getCreatureStringForPrint(Creature creature) {
        StringBuilder builder = new StringBuilder("\n" + creature.getName() + "\n");
        return getStringBuilderForPrint(creature.getServants(), builder, 1).toString();
    }

    private StringBuilder getStringBuilderForPrint(List<Creature> list, StringBuilder builder, int tabCounter) {
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

    public Creature getKing() {
        return king;
    }
}
