import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

Main3ic final double SERVICE_TIME = 1.0;

void main() {
    Scanner sc = new Scanner(System.in);
    int numOfServers = sc.nextInt();
    int numOfCustomers = sc.nextInt();

    sc.nextLine(); // removes trailing newline
    List<Pair<Integer,Double>> arrivals = sc.useDelimiter("\n")
        .tokens()
        .map(line -> {
            List<String> token = Stream.of(line.split(" ")).toList();
            return new Pair<Integer,Double>(Integer.parseInt(token.get(0)),
                    Double.parseDouble(token.get(1)));
        }).toList();
    String state = new Simulator3(numOfServers,
        numOfCustomers, arrivals, SERVICE_TIME).run();
    System.out.println(state);
}
