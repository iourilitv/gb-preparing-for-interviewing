package ru.geekbrains.lesson1.hw.task2;

import ru.geekbrains.lesson1.hw.task1.lombok.Genders;
import ru.geekbrains.lesson1.hw.task1.lombok.Person;
import ru.geekbrains.lesson1.hw.task1.lombok.PersonBuilder;

/**
 * Hw for lesson 1.
 * @Author Litvinenko Yuriy
 * 2. Описать ошибки в коде (см. задание в методичке) и предложить варианты оптимизации.
 */
public class Task2 {
    public static void main(String[] args) {
        PersonBuilder personBuilder = new PersonBuilder();
        Person person = personBuilder.createNewPerson("Yuriy", "Litvinenko", "Semenovich", 55,
                Genders.Male.name(), "Russia", "Senina, 44", "+7 999 999-99-99");
    }
}

//interface Moveable {
//void move ();
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