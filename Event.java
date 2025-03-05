import java.util.Optional;

class Event implements Comparable<Event> {

    private final double etime; 
    private final Optional<Customer> c;


    Event(double etime) {
        this.c = Optional.empty();
        this.etime = etime;

    }

    Event(Customer c, double etime) {
        this.c = Optional.of(c);
        this.etime = etime;

    }

    protected boolean isLeaveEvent() {
        return false; 
    }

    protected boolean isDoneEvent() {
        return false;
    }



    protected boolean isTerminalEvent() {
        return false;
    }
    



    

    protected Optional<Customer> getCustomer() {
        return this.c;
    }

    protected double getTime() {
        return this.etime;
    }


    @Override
    public int compareTo(Event otherEvent) {
        int compareTime = Double.compare(this.getTime(), otherEvent.getTime());
        if (compareTime != 0) {
            return compareTime;
        }
        return Double.compare(this.getCustomer().map(x -> x.getArr()).orElse(Double.MAX_VALUE), 
                otherEvent.getCustomer().map(x -> x.getArr()).orElse(Double.MAX_VALUE));
    }

    @Override
    public String toString() {
        return this.getTime() + " customer ";
    }

    public Pair<Optional<Event>,Shop> next(Shop shop) {
        return new Pair<>(Optional.empty(), shop);
    }
}
