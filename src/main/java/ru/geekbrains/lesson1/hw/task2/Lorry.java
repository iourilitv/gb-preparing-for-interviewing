package ru.geekbrains.lesson1.hw.task2;

//replaced
//class Lorry extends Car , Moveable , Stopable {
class Lorry extends Vehicle {
    private final double weightCapacity;
    private final double volumeCapacity;

    //added
    public Lorry(Engine engine, String color, String name, double weightCapacity, double volumeCapacity) {
        super(engine, color, name);
        this.weightCapacity = weightCapacity;
        this.volumeCapacity = volumeCapacity;
    }

    //added
    @Override
    void open() {
        System.out.println ( "Lorry is open" );
    }

    @Override
    void close() {
        System.out.println ( "Lorry is closed" );
    }

    void load(Cargo cargo) {
        System.out.println ( "Lorry is loaded with " + cargo );
    }

    //deleted
//    public void move (){
//        System . out . println ( "Car is moving" );
//    }

    //deleted
//    public void stop (){
//        System . out . println ( "Car is stop" );
//    }


    public double getWeightCapacity() {
        return weightCapacity;
    }

    public double getVolumeCapacity() {
        return volumeCapacity;
    }
}
