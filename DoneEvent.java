
import java.util.Optional;

class DoneEvent extends Event {
    
    private final Server server;

    DoneEvent(Customer c, double etime, Server server) {
        super(c, etime);
        this.server = server;
    }
 
    @Override
    protected boolean isDoneEvent() {
        return true;
    }

    protected Server getServer() {
        return this.server;
    }

    protected boolean equals(DoneEvent otherDoneEvent) {
        int thisC = this.getCustomer().map(c -> c.getId()).orElse(0);
        int otherC = otherDoneEvent.getCustomer().map(x -> x.getId()).orElse(0);
        return this.getServer().isSame(otherDoneEvent.getServer()) && thisC == otherC;
    }

    @Override
    public Pair<Optional<Event>,Shop> next(Shop shop) {
        return new Pair<>(Optional.empty(), 
        shop.update(new Server(this.getServer().getServerid()))); 
    }
    
    @Override
    public String toString() {
        return super.toString() + super.getCustomer().map(x -> x.getId() + " done");
    }
    
}





    

