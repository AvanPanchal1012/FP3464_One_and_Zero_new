abstract class Contract {
    // Common properties and methods for all contract types
    public abstract double calculateSalary(Employee employee);

    // public abstract String contractType();
}

class Permanent extends Contract {
    private int nbChildren;
    private boolean married;
    private double monthlySalary;
    private double bonusPerChildPerMonth;
    public int accumulatedDays;

    public Permanent(int nbChildren, boolean married, double monthlySalary,
                     double bonusPerChildPerMonth, int accumulatedDays) {
        this.nbChildren = nbChildren;
        this.married = married;
        this.monthlySalary = monthlySalary;
        this.bonusPerChildPerMonth = bonusPerChildPerMonth;
        this.accumulatedDays = accumulatedDays;
    }

    @Override
    public double calculateSalary(Employee employee) {
        double childrenBonus = married ? nbChildren * bonusPerChildPerMonth : 0;
        return (accumulatedDays * (monthlySalary + childrenBonus)) / Management.workingDaysPerMonth;
    }

    // @Override
    // public String contractType() {
    // return "Permanent";
    // }

    // public boolean isMarried() {
    // return married;
    // }

    public int getNumberOfChildren() {
        return nbChildren;
    }

    public String getMaritalStatusInfo() {
        return married ? "he is married" : "he/she is not married";
    }

    // public int getAccumulatedDays() {
    // return accumulatedDays;
    // }

    // public double calculateMonthlySalary() {
    // double baseSalary = monthlySalary + (450 * nbChildren);
    // return (baseSalary / 20) * accumulatedDays;
    // }
}

class Temporary extends Contract {
    public double hourlySalary;
    public int accumulatedHours;

    public Temporary(double hourlySalary, int accumulatedHours) {
        this.hourlySalary = hourlySalary;
        this.accumulatedHours = accumulatedHours;
    }

    @Override
    public double calculateSalary(Employee employee) {
        return hourlySalary * accumulatedHours;
    }

    // @Override
    // public String contractType() {
    // return "Temporary";
    // }
}