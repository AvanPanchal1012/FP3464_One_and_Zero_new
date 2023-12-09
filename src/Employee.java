abstract class Employee {
    public static final double GAIN_FACTOR_CLIENT = 500;
    public static final double GAIN_FACTOR_TRAVEL = 100;

    public static final double GAIN_FACTOR_PROJECTS = 200;
    public static final double GAIN_FACTOR_ERROR = 10;
    public String name;
    private int birthYear;
    private double monthlyIncome;
    private double occupationRate;
    private Vehicle vehicle;
    private String type;

    private Contract contract;

    public Employee(String name, int birthYear, double monthlyIncome, double occupationRate, Vehicle vehicle, String type) {
        this.name = name;
        this.birthYear = birthYear;
        setMonthlyIncome(monthlyIncome);
        setOccupationRate(occupationRate);
        this.vehicle = vehicle;
        this.type = type;
        printNewEmployee(type);
    }

    public Employee(String name, String type) {
        this.name = name;
        printNewEmployee(type);
    }

    private void printNewEmployee(String type) {
        System.out.println("We have a new employee: " + this.name + ", " + type);
    }

    public int getAge(int currentYear) {
        return currentYear - birthYear;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public double getOccupationRate() {
        return occupationRate;
    }

    public void signContract(Contract contract) {
        this.contract = contract;
    }

    public void setOccupationRate(double occupationRate) {
        if (occupationRate < 10) {
            this.occupationRate = 10;
        } else if (occupationRate > 100) {
            this.occupationRate = 100;
        } else {
            this.occupationRate = occupationRate;
        }
    }

    public double calculateAnnualIncome() {
        double baseIncome = 12 * monthlyIncome * (occupationRate / 100);

        // Bonus calculations based on employee type
        if (this instanceof Manager) {
            Manager manager = (Manager) this;
            return baseIncome + (manager.getNewClients() * 500) + (manager.getTravelledDays() * 100);
        } else if (this instanceof Tester) {
            Tester tester = (Tester) this;
            return baseIncome + (tester.getBugsSolved() * 10);
        } else if (this instanceof Programmer) {
            Programmer programmer = (Programmer) this;
            return baseIncome + (programmer.getProjectsCompleted() * 200);
        } else {
            return baseIncome;
        }

    }

    public Contract getContract() {
        return contract;
    }


    @Override
    public String toString() {
        return "Name: " + name + ", " + type + "\n" + "Age: " + getAge(2023) + "\n" + vehicle.toString() + name + " has an Occupation rate: " + occupationRate;
    }

    public String printSalary() {
        return "His/Her estimated annual income is " + calculateAnnualIncome();
    }

    public String contractInfo() {
        Contract contract = getContract();
        if (contract instanceof Permanent) {
            Permanent permanentContract = (Permanent) contract;
            return String.format("%s and he/she has %d children.\n" +
                            "He/She has worked for %d days and upon contract his/her monthly\n" +
                            "salary is %.1f.\n", permanentContract.getMaritalStatusInfo(), permanentContract.getNumberOfChildren(),
                    permanentContract.accumulatedDays, permanentContract.calculateSalary(this));
        } else if (contract instanceof Temporary) {
            Temporary temporaryContract = (Temporary) contract;
            return String.format("he is temporary employee with %.1f.\nhourly salary and " +
                    "he has worked for %d hours \n", temporaryContract.hourlySalary, temporaryContract.accumulatedHours);
        } else {
            return "";
        }
    }

}

class Manager extends Employee {
    private int nbClients;
    private int nbTravelDays;

    public Manager(String name, int birthYear, double monthlyIncome, double occupationRate,
                   int nbClients, int nbTravelDays, Vehicle vehicle) {
        super(name, birthYear, monthlyIncome, occupationRate, vehicle, "a manager");
        this.nbClients = nbClients;
        this.nbTravelDays = nbTravelDays;
    }

    public Manager(String name) {
        super(name, "a manager.");
    }

    public int getNewClients(){
        return nbClients;
    }

    public int getTravelledDays(){
        return nbTravelDays;
    }
    @Override
    public double calculateAnnualIncome() {
        double baseIncome = super.calculateAnnualIncome();
        return baseIncome + nbClients * GAIN_FACTOR_CLIENT + nbTravelDays * GAIN_FACTOR_TRAVEL * 100; // Corrected formula
    }

    @Override
    public String contractInfo() {
        return name + " is a " + getClass().getSimpleName().toLowerCase() + ". " + super.contractInfo();
    }

    @Override
    public String toString() {
        return super.toString() + " He/she travelled " + nbTravelDays + " days and has brought " + nbClients + " new clients.\n" + super.printSalary();
    }
}

class Tester extends Employee {
    private int nbBugs;

    public Tester(String name, int birthYear, double monthlyIncome, double occupationRate,
                  int nbBugs, Vehicle vehicle) {
        super(name, birthYear, monthlyIncome, occupationRate, vehicle, "a tester");
        this.nbBugs = nbBugs;
    }

    public Tester(String name) {
        super(name, "a tester");
    }

    public int getBugsSolved(){
        return nbBugs;
    }

    @Override
    public double calculateAnnualIncome() {
        return super.calculateAnnualIncome() + nbBugs * GAIN_FACTOR_ERROR;
    }

    @Override
    public String contractInfo() {
        return name + " is a " + getClass().getSimpleName().toLowerCase() + ". " + super.contractInfo();
    }

    @Override
    public String toString() {
        return super.toString() + " and corrected " + nbBugs + " bugs.\n" + super.printSalary();
    }
}

class Programmer extends Employee {
    private int nbProjects;

    public Programmer(String name, int birthYear, double monthlyIncome, double occupationRate,
                      int nbProjects, Vehicle vehicle) {
        super(name, birthYear, monthlyIncome, occupationRate, vehicle, "a programmer");
        this.nbProjects = nbProjects;
    }

    public Programmer(String name) {
        super(name, "a programmer");
    }

    public int getProjectsCompleted(){
        return nbProjects;
    }

    @Override
    public double calculateAnnualIncome() {
        double baseIncome = super.calculateAnnualIncome();
        return baseIncome + nbProjects * GAIN_FACTOR_PROJECTS;
    }

    @Override
    public String contractInfo() {
        return name + " is a " + getClass().getSimpleName().toLowerCase() + ". " + super.contractInfo();
    }

    @Override
    public String toString() {
        return super.toString() + " and completed " + nbProjects + " projects.\n" + super.printSalary();
    }
}