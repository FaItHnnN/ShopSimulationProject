import java.util.Optional;

class ArriveEvent extends Event {

    ArriveEvent(Customer c, double etime) {
        super(c, etime);
    }

    @Override
    public String toString() {
        return super.toString() + super.getCustomer().map(x -> x.getId() + " arrives");
    }

    @Override
    public Pair<Optional<Event>,Shop> next(Shop shop) {
        return shop.findServer(super.getCustomer().orElse(new Customer(0,0.0)))
            .map(s -> {
                Server updatedServer = s.serve(super.getCustomer().orElse(new Customer(0,0.0)), 
                                            super.getTime());
                Shop updatedShop = shop.update(updatedServer);
                return new Pair<Optional<Event>,Shop>(Optional.of(new ServeEvent(super.getCustomer()
                                            .orElse(new Customer(0,0.0)), 
                                            updatedServer, 
                                            super.getTime())), 
                                            updatedShop);
            })
            .orElseGet(() -> {
                return new Pair<Optional<Event>,Shop>(Optional.of(
                    new LeaveEvent(super.getCustomer().orElse(new Customer(0,0.0)), 
                                    super.getTime())), 
                                    shop);
            });
    }
}
