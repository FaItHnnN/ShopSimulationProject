class Server {
    
    private final int serverid;
    private final int avail;
    private final double time; 
    private final boolean isAvailable;

    public Server(int serverid) {
        this(serverid, 0, 0.0, true); 
    }

    public Server(int serverid, boolean isAvailable) {
        this.serverid = serverid;
        this.avail = 0;
        this.time = 0.0;
        this.isAvailable = isAvailable;
    }

    Server(int serverid, int avail, double time) {
        this.serverid = serverid;
        this.avail = avail;
        this.time = time;
        this.isAvailable = true;
    }

    Server(int serverid, int avail, double time, boolean isAvailable) {
        this.serverid = serverid;
        this.avail = avail;
        this.time = time;
        this.isAvailable = false;
    }
    
    protected boolean isAvailable() {
        return this.isAvailable;
    }
    
    protected Server makeAvailable() {
        return new Server(this.serverid, true);
    }

    protected Server makeUnavailable() {
        return new Server(this.serverid, false);
    }

    protected int getServerid() {
        return this.serverid;
    }

    protected int getAvail() {
        return this.avail;
    }

    protected double getTime() {
        return this.time;
    }

    public Server serve(Customer cust, double t) {
        return new Server(this.getServerid(), cust.getId(), t + cust.getArr());    
    }

    public Server serve(Customer cust) {
        return new Server(this.getServerid(), cust.getId(), cust.getArr() + cust.getService());
    }

    public boolean canServe(Customer cust) {
        return cust.getArr() >= this.getTime();
    }
    
    public boolean isSame(Server s) {
        return this.getServerid() == s.getServerid();
    }
    
    @Override
    public String toString() {
        return "server " + this.getServerid();
    }

}












