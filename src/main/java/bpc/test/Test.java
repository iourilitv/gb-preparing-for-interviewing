package bpc.test;

import javassist.NotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/*
 * В одной далекой стране правил крайне сумасбродный король, который больше всего на свете любил власть.
 * Ему подчинялось множество людей, но вот незадача, у его подчиненных тоже были свои слуги.
 * Король обезумел от мысли, что какой-нибудь дворянин или даже зажиточный холоп может иметь больше слуг, чем он сам.
 * И приказал всем людям на бумаге через запятую написать свое имя и имена своих прямых подчиненных.
 *
 * По результатам опроса король получил огромный список из имен (see "pollResults")
 *
 * У короля разболелась голова. Что с этими данными делать, король не знал и делегировал задачу невезучему слуге.

 * Помогите слуге правильно составить иерархию и подготовить  отчет для короля следующим образом:
 *
 * король
 *     дворянин Кузькин
 *         управляющий Семен Семеныч
 *             крестьянин Федя
 *             доярка Нюра
 *         жена Кузькина
 *         ...
 *     секретарь короля
 *         зажиточный холоп
 *         ...
 *     ...
 *
 * Помните:
 *  1. Те, кто у кого нет подчиненных, просто написали свое имя.
 *  2. Те, кого никто не указал как слугу, подчиняются напрямую королю (ну, пускай бедный король так думает).
 *  3. Итоговый список должен быть отсортирован в алфавитном порядке на каждом уровне иерархии.
 *
 * */

public class Test {
    private static final List<String> pollResults = Arrays.asList(
            "служанка Аня",
            "управляющий Семен Семеныч: крестьянин Федя, доярка Нюра",
            "дворянин Кузькин: управляющий Семен Семеныч, жена Кузькина, экономка Лидия Федоровна",
            "экономка Лидия Федоровна: дворник Гена, служанка Аня",
            "доярка Нюра",
            "кот Василий: человеческая особь Катя",
            "дворник Гена: посыльный Тошка",
            "киллер Гена",
            "зажиточный холоп: крестьянка Таня",
            "секретарь короля: зажиточный холоп, шпион Т",
            "шпион Т: кучер Д",
            "посыльный Тошка: кот Василий",
            "аристократ Клаус",
            "просветленный Антон"
    );

    public static void main(String... args) throws NotFoundException {
        UnluckyVassal unluckyVassal = new UnluckyVassal();

        unluckyVassal.printReportForKing(pollResults);
    }
}

class UnluckyVassal {
    private final Logger log = Logger.getLogger(UnluckyVassal.class.getName());
    public void printReportForKing(List<String> pollResults) throws NotFoundException {
        ListForKingService service = new ListForKingService(pollResults);
        log.info(service.getCreatureStringForPrint(service.getKing()));
    }
}
//окт 17, 2020 5:59:48 AM bpc.test.ListForKingService fillAndSortCreatures
//INFO: *** creatures= [Creature{name='аристократ Клаус', master_name=King, servants.size()=0}, Creature{name='дворянин Кузькин', master_name=King, servants.size()=3}, Creature{name='киллер Гена', master_name=King, servants.size()=0}, Creature{name='просветленный Антон', master_name=King, servants.size()=0}, Creature{name='секретарь короля', master_name=King, servants.size()=2}]
//окт 17, 2020 5:59:48 AM bpc.test.UnluckyVassal printReportForKing
//INFO:
//King
//	аристократ Клаус
//	дворянин Кузькин
//		жена Кузькина
//		управляющий Семен Семеныч
//			доярка Нюра
//			крестьянин Федя
//		экономка Лидия Федоровна
//			дворник Гена
//				посыльный Тошка
//					кот Василий
//						человеческая особь Катя
//			служанка Аня
//	киллер Гена
//	просветленный Антон
//	секретарь короля
//		зажиточный холоп
//			крестьянка Таня
//		шпион Т
//			кучер Д
//
//
//Process finished with exit code 0