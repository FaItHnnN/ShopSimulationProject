import java.util.Optional;

class TerminalEvent extends Event {
  


    TerminalEvent(double etime) {
        // You can assign a specific value or identifier for this event
        super(etime);
    }

    @Override
    protected boolean isTerminalEvent() {
        return true;
    }
    

    @Override
    public String toString() {
        return "";
    }

    @Override
    public Pair<Optional<Event>, Shop> next(Shop shop) {
        return new Pair<>(Optional.empty(), shop); // No further events after TerminalEvent
    }
}
