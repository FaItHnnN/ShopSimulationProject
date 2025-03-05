import java.util.List;
import java.util.stream.Stream;

class Simulator {
    
    private final int numOfServers;
    private final int numOfCustomers;
    private final List<Pair<Integer,Pair<Double,Double>>> arrivals;

    Simulator(int numOfServers, int numOfCustomers, 
        List<Pair<Integer,Pair<Double,Double>>> arrivals) {
        this.numOfServers = numOfServers;
        this.numOfCustomers = numOfCustomers;
        this.arrivals = arrivals;
    }

    String run() {

        PQ<Event> pq = arrivals.stream()
                .map(entry -> new ArriveEvent(new Customer(entry.t(), 
                                entry.u().t(), 
                                entry.u().u()), 
                            entry.u().t()))
                    .reduce(new PQ<Event>(), (PQ<Event> x, Event y) -> x.add(y), (pq1, pq2) -> pq1);

        
        State init = new State(pq, new Shop(numOfServers));
        
        
        return Stream.<State>iterate(init, st -> st.hasNext(), st -> st.next())
        .map(st -> st.toString())
        .filter(st -> !st.isEmpty()) 
        .reduce("", (x,y) -> x + y + "\n");

    }
}
