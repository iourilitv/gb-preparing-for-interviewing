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
    }

}
