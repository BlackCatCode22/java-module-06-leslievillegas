import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ZookeeperChallenge {

    private static Map<String, Habitat> habitats = new HashMap<>();
    private static Map<String, List<String>> animalNameMap = new HashMap<>();
    private static Map<String, Integer> speciesCount = new HashMap<>();
    private static List<Animal> allAnimals = new ArrayList<>();

    public static void main(String[] args) {
        loadAnimalNames("animalNames.txt");
        processArrivingAnimals("arrivingAnimals.txt");
        assignNames();
        generateUniqueIDs();
        organizeIntoHabitats();
        generateZooReport("zooPopulation.txt");

        System.out.println("Zoo population report generated successfully in zooPopulation.txt");
    }

    private static void loadAnimalNames(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            String currentSpecies = null;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.endsWith(" Names:")) {
                    currentSpecies = line.substring(0, line.length() - " Names:".length()).trim();
                    animalNameMap.put(currentSpecies, new ArrayList<>());
                } else if (currentSpecies != null && !line.isEmpty()) {
                    String[] names = line.split(",");
                    List<String> nameList = animalNameMap.get(currentSpecies);
                    if (nameList != null) {
                        for (String name : names) {
                            nameList.add(name.trim());
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading animal names: " + e.getMessage());
        }
    }

    private static void processArrivingAnimals(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 8) { // Check if we have at least 8 parts
                    try {
                        String species = parts[0].trim();
                        int age = Integer.parseInt(parts[1].trim());
                        String sex = parts[2].trim();
                        String color = parts[3].trim();
                        double weight = Double.parseDouble(parts[4].trim());

                        // Reconstruct origin, handling potential commas within quotes
                        StringBuilder originBuilder = new StringBuilder(parts[5].trim());
                        for (int i = 6; i < parts.length - 2; i++) {
                            originBuilder.append(",").append(parts[i].trim());
                        }
                        String origin = originBuilder.toString().trim();
                        if (origin.startsWith("\"") && origin.endsWith("\"")) {
                            origin = origin.substring(1, origin.length() - 1);
                        }

                        LocalDate arrivalDate = LocalDate.parse(parts[parts.length - 2].trim(), dateFormatter);
                        String birthSeason = parts[parts.length - 1].trim();

                        Animal animal = new Animal(species, age, sex, color, weight, origin, arrivalDate, birthSeason);
                        allAnimals.add(animal);
                        speciesCount.put(species, speciesCount.getOrDefault(species, 0) + 1);

                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing number in line: " + line);
                    } catch (DateTimeParseException e) {
                        System.err.println("Error parsing date in line: " + line);
                    } catch (Exception e) {
                        System.err.println("Error processing line: " + line + " - " + e.getMessage());
                    }
                } else {
                    System.err.println("Skipping invalid animal data line (less than 8 parts): " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error processing arriving animals: " + e.getMessage());
        }
    }

    private static void assignNames() {
        Map<String, Integer> nameIndex = new HashMap<>();
        for (Animal animal : allAnimals) {
            String species = animal.getSpecies();
            if (animalNameMap.containsKey(species)) {
                List<String> names = animalNameMap.get(species);
                int currentIndex = nameIndex.getOrDefault(species, 0);
                if (currentIndex < names.size()) {
                    animal.setName(names.get(currentIndex));
                    nameIndex.put(species, currentIndex + 1);
                } else {
                    // If we run out of names, we can assign a default or handle it differently
                    animal.setName(species + " #" + (currentIndex + 1 - names.size()));
                    nameIndex.put(species, currentIndex + 1);
                }
            } else {
                animal.setName("Unnamed " + species);
            }
        }
    }

    private static void generateUniqueIDs() {
        Map<String, Integer> currentCount = new HashMap<>();
        for (Animal animal : allAnimals) {
            String speciesCode = animal.getSpecies().substring(0, Math.min(2, animal.getSpecies().length())).toUpperCase();
            int count = currentCount.getOrDefault(animal.getSpecies(), 0) + 1;
            String uniqueID = String.format("%s%02d", speciesCode, count);
            animal.setUniqueID(uniqueID);
            currentCount.put(animal.getSpecies(), count);
        }
    }

    private static void organizeIntoHabitats() {
        for (Animal animal : allAnimals) {
            String species = animal.getSpecies();
            habitats.putIfAbsent(species, new Habitat(species));
            habitats.get(species).addAnimal(animal);
        }
    }

    private static void generateZooReport(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Habitat habitat : habitats.values()) {
                writer.write(habitat.toString());
                writer.write("\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing zoo report: " + e.getMessage());
        }
    }
}