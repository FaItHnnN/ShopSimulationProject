import java.util.Optional;

class ServeEvent extends Event {

    private final Customer c;
    private final Server server;

    ServeEvent(Customer c, Server server, double etime) {
        super(etime);
        this.server = server;
        this.c = c;
    }

    protected Server getServer() {
        return this.server;
    }

    @Override
    public String toString() {
        return super.toString() + c.getId() + " serve by " + this.getServer();
    }

    @Override
    public Pair<Optional<Event>,Shop> next(Shop shop) {
        return new Pair<>(Optional.of(new DoneEvent(c, 
                                c.serveTill(c.getService()), 
                                this.getServer().serve(c))), 
                            shop.update(this.getServer().serve(c)));
    }




}
