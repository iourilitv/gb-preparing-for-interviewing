package ru.geekbrains.lesson4.hw;

public class PrintService {
    enum printSchemas {
        schema1, schema2, schema3, schema4
    }

    private final RequestManager requestManager;

    public PrintService() {
        this.requestManager =  new RequestManager();
    }

    public void print(printSchemas printSchema) {

        if(printSchema.equals(printSchemas.schema3)) {
            // 3) список фильмов, для каждого — с указанием
            // - DONE название,
            // - DONE общего числа посетителей за все время,
            // - DONE среднего числа зрителей за сеанс и
            // - DONE общей суммы сборов по каждому фильму
            // - отсортировать по убыванию прибыли.
            // - Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу;
            System.out.println("******** " + printSchemas.schema3.name() + " **********");
            System.out.println(requestManager.getSchema3());
        }
        if(printSchema.equals(printSchemas.schema4)) {
            //4) число посетителей и кассовые сборы, сгруппированные по времени начала фильма:
            //с 9 до 15,
            //с 15 до 18,
            //с 18 до 21,
            //с 21 до 00:00
            //(сколько посетителей пришло с 9 до 15 часов и т.д.).
            System.out.println("******** " + printSchemas.schema4.name() + " **********");
            System.out.println(requestManager.getSchema4());
        }
    }

}
