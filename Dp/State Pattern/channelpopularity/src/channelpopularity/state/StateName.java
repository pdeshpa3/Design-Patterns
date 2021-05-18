package channelpopularity.state;

public enum StateName {
    /**
     * Takes in (low, high] and acceptable add request length for each state
     */
    UNPOPULAR(0, 999, 10), //greater than 0 and lessthan or eqto 999; greater than 1, lessthaneqto 10
    MILDLY_POPULAR(1000, 10000, 20),
    HIGHLY_POPULAR(10000, 100000, 30),
    ULTRA_POPULAR(100000, Integer.MAX_VALUE, 40);

    private final int low;
    private final int hi;
    private final int addUpperLimit;

    StateName(int low, int high, int add){
        this.low = low;
        this.hi = high;
        this.addUpperLimit = add;
    }

    public int getLow(){return low;}
    public int getHi(){return hi;}
    public int getAddUpperLimit(){return addUpperLimit;}


}
