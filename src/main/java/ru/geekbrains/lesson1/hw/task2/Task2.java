package ru.geekbrains.lesson1.hw.task2;

/**
 * Hw for lesson 1.
 * @Author Litvinenko Yuriy
 * 2. Описать ошибки в коде (см. задание в методичке) и предложить варианты оптимизации.
 */
public class Task2 {
    public static void main(String[] args) {
        Car audi = new Car(new Engine("Petrol", 100.0), "White", "Audi");
        audi.open();
        audi.move();
        audi.stop();

        Lorry volvo = new Lorry(new Engine("Diesel", 500.0), "Yellow", "Volvo",
                10000.0, 20.0);
        volvo.open();
        volvo.load(new Cargo(5000, 15.0));

        //здесь нужно сделать разгрузку, разумеется

        volvo.move();
        volvo.stop();
        volvo.close();

        //Car is open
        //Vehicle started moving
        //Vehicle stopped
        //Lorry is open
        //Lorry is loaded with Cargo{weight=5000.0, volume=15.0}
        //Vehicle started moving
        //Vehicle stopped
        //Lorry is closed

    }
}

//interface Moveable {
//    void move ();
//}
//interface Stopable {
//void stop ();
//}
//abstract class Car {
//public Engine engine ;
//private String color ;
//private String name ;
//protected void start () {
//System . out . println ( "Car starting" );
//}
//abstract void open ();
//public Engine getEngine () {
//return engine ;
//}
//public void setEngine ( Engine engine ) {
//this . engine = engine ;
//}
//public String getColor () {
//return color ;
//}
//public void setColor ( String color ) {
//this . color = color ;
//}
//public String getName () {
//return name ;
//}

//public void setName ( String name ) {
//this . name = name ;
//}
//}

//class LightWeightCar extends Car implements Moveable {
//@Override
//void open () {
//System . out . println ( "Car is open" );
//}
//@Override
//public void move () {
//System . out . println ( "Car is moving" );
//}
//}
//class Lorry extends Car , Moveable , Stopable {
//public void move (){
//System . out . println ( "Car is moving" );
//}
//public void stop (){
//System . out . println ( "Car is stop" );
//}
//}