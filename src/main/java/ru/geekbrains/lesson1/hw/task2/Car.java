package ru.geekbrains.lesson1.hw.task2;

//replaced
//class LightWeightCar extends Car implements Moveable {
class Car extends Vehicle {
    //added
    public Car(Engine engine, String color, String name) {
        super(engine, color, name);
    }

    @Override
    void open () {
        System . out . println ( "Car is open" );
    }

    @Override
    void close() {
        System.out.println ( "Car is closed" );
    }

    //deleted
//    @Override
//    public void move () {
//        System . out . println ( "Car is moving" );
//    }

}
