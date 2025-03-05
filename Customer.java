

class Customer {

    private final int id;
    private final double arr;
    private final double service;

    Customer(int id, double arr, double service) {
        this.id = id;
        this.arr = arr;
        this.service = service;
    }

    Customer(int id, double arr) {
        this.id = id; 
        this.arr = arr;
        this.service = 0.0;
    }
    
    protected int getId() {
        return this.id;
    }

    protected double getArr() {
        return this.arr;
    }

    public double getService() {
        return this.service;
    }

    public boolean canBeServed(double time) {
        return this.getArr() >= time;
    }

    public double serveTill(double time) {
        return this.getArr() + time;
    }

    protected boolean isSameCustomer(Customer c) {
        return this.getId() == c.getId();
    }
    
    @Override
    public String toString() {
        return "customer " + this.id;
    }

}




