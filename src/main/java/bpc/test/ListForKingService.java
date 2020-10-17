package bpc.test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ListForKingService {
    private final Logger log = Logger.getLogger("Test");

    private final String PRIMARY_REGEX = ":";
    private final String SECONDARY_REGEX = ",";

    private final List<Creature> creaturesTemp = new ArrayList<>();
    private final List<Creature> creatures = new ArrayList<>();
    private final ListForKing listForKing = new ListForKing(creatures);
    private final Creature king = new Creature("King");

    public ListForKingService(List<String> pollResults) {
        king.setMaster(Creature.emptyObject);
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

        log.info("*** creaturesTemp= " + creaturesTemp);
    }

    private void fillAndSortCreatures() {
        creaturesTemp.forEach(creature -> {
            if(creature.getMaster().equals(Creature.emptyObject)) {
                bindToMasterOrKing(creature);
            }
            if(creature.getMaster().equals(king)) {
//                log.info("*** king's creature= " + creature);
//                creature.getServants().sort(CreaturesComparator.getInstance());
                creatures.add(creature);
            }
        });
//        creatures.sort(CreaturesComparator.getInstance());
        sortList(creatures);
        log.info("*** creatures= " + creatures);
    }

    private void sortList(List<Creature> list) {
        if(!list.isEmpty()) {
            list.sort(CreaturesComparator.getInstance());
            list.forEach(c -> sortList(c.getServants()));
        }
    }

//    private void sortCreatures() {
//        Comparator<Creature> comparator = new Comparator<Creature>() {
//            @Override
//            public int compare(Creature o1, Creature o2) {
//                return o1.compareTo(o2);
//            }
//        };
//        creatures.sort(comparator);
//    }
//    private void sortCreatures() {
//        creatures.sort(CreaturesComparator.getInstance());
//    }

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
        if(creature.equals(Creature.emptyObject)) {
            creature = new Creature(name);
            creaturesTemp.add(creature);
        }
        return creature;
    }

    private Creature getCreatureInCreaturesTempByNameAndDeleteOrCreateNew(String name) {
        Creature creature = getCreatureByName(creaturesTemp, name);
        if(creature.equals(Creature.emptyObject)) {
            creature = new Creature(name);
        } else {
            creaturesTemp.remove(creature);
        }
        return creature;
    }

    private Creature getCreatureByName(List<Creature> list, String name) {
        Creature out = Creature.emptyObject;
        for (Creature c: list) {
            boolean flag = c.getName().equals(name);
            if(flag) {
                return c;
            }
            if(!c.getServants().isEmpty()) {
                out = getCreatureByName(c.getServants(), name);
                if(!out.equals(Creature.emptyObject)) {
                    return out;
                }
            }
        }
        return out;
    }

    private void bindToMasterOrKing(Creature creature) {
        for (Creature c : creaturesTemp) {
            if(isListContainsName(c.getServants(), creature.getName())) {
                creature.setMaster(c);
                return;
            }
        }
        creature.setMaster(king);
        king.getServants().add(creature);
    }

    private boolean isListContainsName(List<Creature> list, String name) {
        boolean flag = false;
        for (Creature c : list) {
            if(c.getName().equals(name)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
    
    private Creature getKing() {
        return king;
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public ListForKing getListForKing() {
        return listForKing;
    }

//    public String getStringForPrint() {
//        StringBuilder builder = new StringBuilder("\n" + king.getName() + "\n");
//        king.getServants().forEach(creature -> {
//            builder.append("\t").append(creature.getStringForPrint());
//        });
//        return builder.toString();
//    }
    public String getStringForPrint() {
        return listForKing.getStringForPrint(king);
    }


}
