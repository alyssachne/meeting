package Entity;

public class Room {
    public int MaxCapacity;
    public int currentCapacity;
    public int id;
    public boolean full;
    public boolean booked;

    public Room(int id, int MaxCapacity) {
        this.MaxCapacity = MaxCapacity;
        this.id = id;
    }

    public double getMaxCapacity() {
        return MaxCapacity;
    }

    public double getCurrentCapacity() {
        return currentCapacity;
    }

    public int getId() {
        return id;
    }

    public boolean isFull() {
        return full;
    }

    public boolean isBooked() {return booked;}

    public boolean Book() {
        if(!booked) {
            booked = true;
            return true;
        }
        return false;
    }

}
