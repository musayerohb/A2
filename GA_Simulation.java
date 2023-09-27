import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Stores the parameters initialized in the Individual class in fields after taking them in as arguments.
 */
public class GA_Simulation {

    public int n = 100;
    public int k = 15;
    public int r = 100;
    public int c_0 = 8;
    public int c_max = 20;
    public double m = 0.01;
    public int g = 4;
    
    /** 
     * Constructor for the class that takes all parameters.
     */
    public void GA_Simulation() {
        this.n = 100;
        this.k = 15;
        this.r = 100;
        this.c_0 = 8;
        this.c_max = 20;
        this.m = 0.01;
        this.g = 4;

    }

    /**
     * Initializes a population of the desired size, calling the Individual setup constructor for each one.
     * @return The population in an ArrayList
     */
    public ArrayList<Individual> init() {
        ArrayList<Individual> pop = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Individual indiv = new Individual(g, c_0);
            pop.add(indiv);
        }
        return pop;
    }

    /**
     * Sorts the population members according to their fitness level, best first.
     * @param pop The population with the individuals being ranked
     * @return A sorted version of the population, with those with the best fitness score being listed first
     */
    public void rankPopulation(ArrayList<Individual> pop) {
        // sort population by fitness
        Comparator<Individual> ranker = new Comparator<>() {
            // this order will sort higher scores at the front
            public int compare(Individual c1, Individual c2) {
                return (int)Math.signum(c2.getFitness()-c1.getFitness());
            }
        };
        pop.sort(ranker); 
    }
        
    /**
     * Selects the winners (the first k elements of the population after ranking), and from them randomly selects two parents for each member of the next generation. Then creates the new population using the second constructor for Individual.
     * @param k The number of winners picked amongst the population
     * @param pop The population being evolved
     * @param n The number of individuals in the population
     * @return The population of the next generation
     */
    public ArrayList<Individual> evolve(ArrayList<Individual> pop) {
        ArrayList<Individual> winners = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            winners.add(pop.get(i));
        }
        for (int i = 0; i < n; i++) {
            pop.remove(i);
            Individual parent1 = winners.get(ThreadLocalRandom.current().nextInt(0, winners.size()));
            Individual parent2 = winners.get(ThreadLocalRandom.current().nextInt(0, winners.size()));
            while(parent1.equals(parent2)) {
                parent1 = winners.get(ThreadLocalRandom.current().nextInt(0, winners.size()));
                parent2 = winners.get(ThreadLocalRandom.current().nextInt(0, winners.size()));
            }
            Individual child = new Individual(parent1, parent2, m, g, c_max);
            pop.add(child);
        }
            
        return pop;
    }

    /** 
     * Prints statistics about the current generation, including: the fitness of the top individual in the generation, the top individual's chromosome, the fitness of the kth individual, and the least fit (last ranking) individual.
     * @param sortedpop The population of the current generation after being ranked in order of fitness
     */
    public void describeGeneration(ArrayList<Individual> sortedpop) {
        System.out.println("Fitness of generation's top individual: " + sortedpop.get(0).getFitness());
        System.out.println("Chromosome of the generation's top (fittest) individual: " + sortedpop.get(0));
        System.out.println("Fitness of " + k + "th individual: " + sortedpop.get(k-1).getFitness());
        System.out.println("Fitness of generation's least fit individual: " + sortedpop.get(sortedpop.size()-1).getFitness());
    }

    /** 
     * Runs the entire experiment. First initializes the population, rank it, and describes it. Then, for each round, evolves the population, ranks it, and describes it.
     */
    public void run() {
        System.out.println("Round 1");
        ArrayList<Individual> pop = init();
        rankPopulation(pop);
        describeGeneration(pop);
        for (int i = 2; i < r+1; i++) {
            System.out.println("Round " + i);
            evolve(pop);
            rankPopulation(pop);
            describeGeneration(pop);
        }

    }

// main will create a new GA_Simulation object and run it.
    public static void main(String[] args) {
        GA_Simulation one = new GA_Simulation();
        one.run();

    }

}
