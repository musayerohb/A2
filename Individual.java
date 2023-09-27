import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

/**
 * Initializes parameters to be used in the GA_Simulation class. Also initializes two constructors: one to generate initial population members with a random sequence of chromosomesand one to generate offspring from two parents in subsequent generations
 */
public class Individual {
    public ArrayList<Character> chromosome = new ArrayList<>();
    
    /**
     * Initial constructor. Generates initial population members with a random sequence of chromosomes.
     * @param g The number of letters possible per gene
     * @param c_0 The initial chromosome size
     */
    public Individual(int g, int c_0) {
        this.chromosome = chromosome;
        for (int i = 0; i < c_0; i++) {
            this.chromosome.add(randomLetter(g));
        }
    }

    //quick question, should the offspring have at least 8 letters in its chromosome? nope however many
    /** 
     * Offspring constructor. Generates offspring from two parents in subsequent generations.
     * @param parent1 The first parent chosen to generate the offspring
     * @param parent2 The second parent chosen to generate the offspring
     * @param m The chance per round of a mutation in each generation
     * @param g The number of letters possible per gene
     * @param c_max The maximum chromosome size
     */
    public Individual(Individual parent1, Individual parent2, double m, int g, int c_max) {

        //Selects what part of parent1's and parent2's chromosome will be used to create the offspring's sequence 
        // origin is 1 because if 0 is chosen then the for loop won't be able to do anything as i must be < size().
        int prefixOptionSelector = ThreadLocalRandom.current().nextInt(1, parent1.chromosome.size());
        int suffixOptionSelector = ThreadLocalRandom.current().nextInt(1, parent2.chromosome.size());
        
        for (int i = 0; i < prefixOptionSelector; i++) {
            this.chromosome.add(parent1.chromosome.get(i));
        }

        ArrayList<Character> suffix = new ArrayList<Character>(parent2.chromosome.subList(suffixOptionSelector, parent2.chromosome.size()));
        for (int i = 0; i < suffix.size(); i++) {
            this.chromosome.add(suffix.get(i));
        }
        
        // Truncates offspring's chromosome size to 20 (c_max) if it ends up greater than that. 
        if (this.chromosome.size() > c_max) {
            ArrayList<Character> keepThis = new ArrayList<Character>(this.chromosome.subList(0, c_max));
            this.chromosome = keepThis;
        }

        //Mutates chromosome based on chance. 
        for (int i = 0; i<this.chromosome.size(); i++) {
            double randomnum = ThreadLocalRandom.current().nextDouble();
            if (randomnum < m) {
                this.chromosome.set(i, randomLetter(g));
            }
        }

    }
    
    /**
     * Generates random dna letters for the initial chromosomes.
     * @param num_dna_letters The possible letters that can be used in the sequence
     * @return The randomly selected thread of letters that make up the chromosome
     */
    private Character randomLetter(int num_dna_letters) {
        return Character.valueOf((char)(65+ThreadLocalRandom.current().nextInt(num_dna_letters)));
    }

    /**
     * Modified toString method that displays chromosome.
     * @return The modified toString method that allows chromosomes to be displayed
     */
    public String toString() {
        StringBuilder builder = new StringBuilder(chromosome.size());
        for(Character ch: chromosome) {
          builder.append(ch);
        }
        return builder.toString();
        //return chromosome.stream().map(e->e.toString()).collect(Collectors.joining());
    }

    /**
     * Calculates the fitness score of the requested member of the population.
     * @return The fitness score of the requested population member.
     */
    public int getFitness() {
        int fitnessScore = 0;
        // First part of fitness score calculation.
        for (int i = 0; i < chromosome.size(); i++) {
            if (chromosome.get(i) == chromosome.get(chromosome.size() - 1 - i)) {
                    fitnessScore++;
            }
            else {
                fitnessScore--;
            }
        }
        
        // Second part of the fitness score calculation. 
        for (int i = 0; i < chromosome.size(); i++) {
            if (i+1 == chromosome.size()) {
                break;
            }
            else if (chromosome.get(i) == chromosome.get(i + 1)) {
                fitnessScore--;
            }
            else {
                continue;
            }
        }

        return fitnessScore;
    }

    public static void main(String[] args) {
        Individual one = new Individual(4, 8);
        Individual two = new Individual(4, 8);
        System.out.println(one.chromosome.toString());
        System.out.println(two.chromosome.toString());

        Individual child = new Individual(one, two, 0.01, 4, 20);
        System.out.println(child.chromosome.toString());        


    }
}
