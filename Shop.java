import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

class Shop {

    private final List<Server> servers;

    Shop(int num) {
        this.servers = IntStream.rangeClosed(1, num)
            .mapToObj(x -> new Server(x))
            .toList();
    }

    Shop(List<Server> servers)  {
        this.servers = servers;
    }

    public List<Server> getServers() {
        return this.servers;
    }

    public int getNum() {
        return servers.size();
    }

    public Optional<Server> findServer(Customer cust) {
        return this.getServers()
            .stream()
            .filter(x -> x.canServe(cust))
            .findFirst();
    }

    public Shop update(Server s) {
        return new Shop(getServers().stream()
            .map(x -> x.isSame(s) ? s : x)
            .toList());
    }
        @Override
    public String toString() {
        return IntStream.rangeClosed(1, this.getNum())
            .mapToObj(x -> new Server(x))
            .map(x -> x.toString())
            .reduce((x, y) -> x + ", " + y)
            .map(x -> "[" + x + "]")
            .orElse("[]");
    }
    
}
