package bpc.test;

public class CreaturesComparator implements ICreaturesComparator {
    private static final CreaturesComparator instance = new CreaturesComparator();

    private CreaturesComparator() {

    }

    public static CreaturesComparator getInstance() {
        return instance;
    }

    @Override
    public int compare(Creature o1, Creature o2) {
        return o1.compareTo(o2);
    }
}
