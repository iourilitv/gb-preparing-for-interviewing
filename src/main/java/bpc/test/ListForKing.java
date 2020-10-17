package bpc.test;

import java.util.List;

public class ListForKing {

    public StringBuilder getStringBuilderForPrint(List<Creature> list, StringBuilder builder, int tabCounter) {
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
