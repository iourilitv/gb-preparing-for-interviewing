package ru.geekbrains.lesson1.hw.task2;

//replaced
//abstract class Car {
abstract class Vehicle implements Moveable, Stopable {
    //replaced
//    public Engine engine ;
    private Engine engine ;

    private String color ;
    private String name ;

    public Vehicle(Engine engine, String color, String name) {
        this.engine = engine;
        this.color = color;
        this.name = name;
    }

    abstract void open ();

    abstract void close ();

    //deleted
//    protected void start () {
//        System.out.println("Car starting");
//    }

    //added
    @Override
    public void move() {
        System.out.println ( "Vehicle started moving" );
    }

    //added
    @Override
    public void stop() {
        System.out.println ( "Vehicle stopped" );
    }

    public Engine getEngine () {
        return engine ;
    }

    public void setEngine ( Engine engine ) {
        this . engine = engine ;
    }

    public String getColor () {
        return color ;
    }

    public void setColor ( String color ) {
        this . color = color ;
    }

    public String getName () {
        return name ;
    }

    public void setName ( String name ) {
        this . name = name ;
    }

}
