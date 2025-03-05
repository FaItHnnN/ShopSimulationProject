import java.util.Optional;

class State {

    private final Shop shop;
    private final String message;
    private final PQ<Event> queue;
    private final Optional<Event> lastEvent;

    public State(PQ<Event> queue, Shop shop) {
        this.shop = shop;
        this.message = "";
        this.queue = queue;
        this.lastEvent = Optional.empty();
    }
    
    State(Shop shop) {
        this.shop = shop;
        this.message = "";
        this.queue = new PQ<>();
        this.lastEvent = Optional.empty();
    }


    public State(PQ<Event> queue, Shop shop, Optional<Event> lastEvent) {
        this.shop = shop;
        this.message = lastEvent.toString();
        this.queue = queue;
        this.lastEvent = Optional.empty();
    }


    private State(Shop shop, String message) {
        this.shop = shop;
        this.message = message;
        this.queue = new PQ<>();
        this.lastEvent = Optional.empty();
    }

    private State(PQ<Event> queue, Shop shop, String message, Optional<Event> lastEvent) {
        this.shop = shop;
        this.message = message;
        this.queue = queue;
        this.lastEvent = lastEvent;
    }

    protected Shop getShop() {
        return this.shop;
    }

    public PQ<Event> getQueue() {
        return this.queue;
    }

    public Optional<Event> getLast() {
        return this.lastEvent;
    } 

    public State next() {

        Pair<Optional<Event>, PQ<Event>> polled = queue.poll();
        Optional<Event> optEvent = polled.t();
        PQ<Event> newQueue = polled.u(); 

        return optEvent.map(event -> {
            if (event.isDoneEvent() && this.getQueue().poll().u().isEmpty()) {
                Pair<Optional<Event>, Shop> result = event.next(shop);
                Event nextEvent = result.t().map(x -> x).orElse(new TerminalEvent(0.0));
                PQ<Event> addedEvent = newQueue.add(nextEvent);
                return new State(addedEvent, shop, event.toString(), Optional.of(event));
            }
    
            if (event.isDoneEvent()) {
                Shop updatedShop = event.next(shop).u();
                return new State(newQueue, updatedShop, event.toString(), Optional.of(event));
            } 

            if (event.isLeaveEvent()) {
                return new State(newQueue, shop, event.toString(), Optional.of(event));
            } else {
                Pair<Optional<Event>, Shop> result = event.next(shop);
                PQ<Event> addedEvent = result.t()
                    .map(e -> newQueue.add(e))
                    .orElseGet(() -> newQueue);
                Shop updatedShop = result.u();
                return new State(addedEvent, updatedShop, event.toString(), Optional.of(event));
            }
        }).orElse(this); 
    }

    public boolean hasNext() {
        return !this.isEmpty();
    }

    public boolean isEmpty() {
        return this.getLast().map(event -> event.isTerminalEvent()).orElse(false);
    }
    
    @Override
    public String toString() {
        return message;
    }

}



