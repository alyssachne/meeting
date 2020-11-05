package Entity;

public class Room {
    private int MaxCapacity;
    private int currentCapacity;
    private int id;
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

    public boolean addAttendee() {
        if (full) {
            return false;
        }
        currentCapacity += 1;
        if(currentCapacity == MaxCapacity) {
            full = true;
        }
        return true;
    }

    public boolean removeAttendee() {
        if (currentCapacity == 0) {
            return false;
        }
        if (currentCapacity == MaxCapacity) {
            full = false;
        }
        currentCapacity -= 1;
        return true;
    }
}
