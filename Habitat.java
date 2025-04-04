import java.util.ArrayList;
import java.util.List;

public class Habitat {
    private String name;
    private List<Animal> animals;

    public Habitat(String name) {
        this.name = name;
        this.animals = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void addAnimal(Animal animal) {
        this.animals.add(animal);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name + " Habitat:\n");
        for (Animal animal : animals) {
            sb.append(animal.toString()).append("\n");
        }
        return sb.toString();
    }
}