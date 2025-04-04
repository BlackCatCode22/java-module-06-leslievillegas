import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Animal {
    private String uniqueID;
    private String name;
    private String species;
    private int age;
    private String sex;
    private String color;
    private double weight;
    private String origin;
    private LocalDate arrivalDate;
    private LocalDate birthDate;

    public Animal(String species, int age, String sex, String color, double weight, String origin, LocalDate arrivalDate, String birthSeason) {
        this.species = species;
        this.age = age;
        this.sex = sex;
        this.color = color;
        this.weight = weight;
        this.origin = origin;
        this.arrivalDate = arrivalDate;
        this.birthDate = genBirthDay(age, arrivalDate, birthSeason);
    }

    // Getters
    public String getUniqueID() {
        return uniqueID;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public String getColor() {
        return color;
    }

    public double getWeight() {
        return weight;
    }

    public String getOrigin() {
        return origin;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    // Setters
    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate genBirthDay(int age, LocalDate arrivalDate, String birthSeason) {
        int birthYear = arrivalDate.getYear() - age;
        Random random = new Random();

        if (birthSeason != null && !birthSeason.trim().isEmpty()) {
            switch (birthSeason.trim().toLowerCase()) {
                case "spring":
                    return LocalDate.of(birthYear, Month.MARCH, random.nextInt(31) + 1);
                case "summer":
                    return LocalDate.of(birthYear, Month.JUNE, random.nextInt(30) + 1);
                case "autumn":
                case "fall":
                    return LocalDate.of(birthYear, Month.SEPTEMBER, random.nextInt(30) + 1);
                case "winter":
                    return LocalDate.of(birthYear, Month.DECEMBER, random.nextInt(31) + 1);
                default:
                    // If birth season is not provided or invalid, assign a random date within the birth year
                    return LocalDate.of(birthYear, random.nextInt(12) + 1, random.nextInt(28) + 1); // Assuming max 28 for simplicity
            }
        } else {
            // If birth season is not provided, assign a random date within the birth year
            return LocalDate.of(birthYear, random.nextInt(12) + 1, random.nextInt(28) + 1); // Assuming max 28 for simplicity
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;
        return uniqueID + "; " + name + "; birth date: " + birthDate.format(dateFormatter) + "; " +
                color + " color; " + sex.toLowerCase() + "; " + (int) weight + " pounds; from " +
                origin + "; arrived " + arrivalDate.format(dateFormatter);
    }
}
