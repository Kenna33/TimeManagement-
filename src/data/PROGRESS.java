package data;

public enum PROGRESS {
	Not_Started(0), Started(1), Finished(2);
	
	private int numVal;

   PROGRESS(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
}
