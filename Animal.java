import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;


public record Animal(
        String name,
        Type type,
        Sex sex,
        int age,
        int height,
        int weight,
        boolean bites
) {
    enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    enum Sex {
        M, F
    }

    public int paws() {
        return switch (type) {
            case CAT, DOG -> 4;
            case BIRD -> 2;
            case FISH -> 0;
            case SPIDER -> 8;
        };
    }

    //1
    public List<Animal> sortByHeight(List<Animal> animals) {
        return animals.stream()
                .sorted(Comparator.comparingInt(Animal::height))
                .toList();
    }

    //2
    public List<Animal> sortByWeightAndSelectK(List<Animal> animals, int k) {
        return animals.stream()
                .sorted((a1, a2) -> Integer.compare(a2.weight(), a1.weight()))
                .limit(k)
                .toList();
    }

    //3
    public Map<Type, Long> countAnimalsByType(List<Animal> animals) {
        return animals.stream()
                .collect(Collectors.groupingBy(Animal::type, Collectors.counting()));
    }

    //4
    public Optional<Animal> findAnimalWithLongestName(List<Animal> animals) {
        return animals.stream()
                .max(Comparator.comparingInt(a -> a.name().length()));
    }

    //5
    public String moreMalesOrFemales(List<Animal> animals) {
        long males = animals.stream().filter(a -> a.sex() == Sex.M).count();
        long females = animals.size() - males;
        return males > females ? "Males" : females > males ? "Females" : "Equal";
    }

    //6
    public Map<Type, Animal> heaviestAnimalPerType(List<Animal> animals) {
        return animals.stream()
                .collect(Collectors.groupingBy(Animal::type,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(Animal::weight)),
                                opt -> opt.orElse(null)
                        )));
    }

    //7
    public Optional<Animal> findLightestAnimal(List<Animal> animals) {
        return animals.stream()
                .min(Comparator.comparingInt(Animal::weight));
    }

    //8
    public Optional<Animal> heaviestAnimalBelowKcm(List<Animal> animals, int k) {
        return animals.stream()
                .filter(a -> a.height() < k)
                .max(Comparator.comparingInt(Animal::weight));
    }

    //9
    public int totalNumberOfLegs(List<Animal> animals) {
        return animals.stream()
                .mapToInt(Animal::paws)
                .sum();
    }

    //10
    public List<Animal> animalsWithAgeMismatch(List<Animal> animals) {
        return animals.stream()
                .filter(a -> a.age() != a.height())
                .toList();
    }

    //11
    public List<Animal> bitingAnimalsAbove100cm(List<Animal> animals) {
        return animals.stream()
                .filter(a -> a.bites() && a.height() > 100)
                .toList();
    }

    //12
    public long countAnimalsWithWeightGreaterThanHeight(List<Animal> animals) {
        return animals.stream()
                .filter(a -> a.weight() > a.height())
                .count();
    }

    //13
    public List<Animal> animalsWithNamesMoreThanTwoWords(List<Animal> animals) {
        return animals.stream()
                .filter(a -> a.name().split(" ").length > 2)
                .toList();
    }

    //14
    public boolean hasDogTallerThanK(List<Animal> animals, int k) {
        return animals.stream()
                .anyMatch(a -> a.type() == Type.DOG && a.height() > k);
    }

    //15
    public Map<Type, Integer> totalWeightPerTypeAge1to10(List<Animal> animals) {
        return animals.stream()
                .filter(a -> a.age() >= 1 && a.age() <= 10)
                .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    //16
    public List<Animal> sortByTypeThenSexThenName(List<Animal> animals) {
        return animals.stream()
                .sorted(Comparator.comparing(Animal::type)
                        .thenComparing(Animal::sex)
                        .thenComparing(Animal::name))
                .toList();
    }

    //17
    public boolean spidersBiteMoreThanDogs(List<Animal> animals) {
        long spiderBites = animals.stream()
                .filter(a -> a.type() == Type.SPIDER && a.bites())
                .count();
        long dogBites = animals.stream()
                .filter(a -> a.type() == Type.DOG && a.bites())
                .count();
        return spiderBites > dogBites;
    }

    //18
    public Optional<Animal> findHeaviestFishInMultipleLists(List<List<Animal>> animalLists) {
        return animalLists.stream()
                .flatMap(List::stream)
                .filter(a -> a.type() == Type.FISH)
                .max(Comparator.comparingInt(Animal::weight));
    }

    //19
    public Map<String, Set<String>> findAnimalsWithErrors(List<Animal> animals) {
        return animals.stream()
                .filter(a -> a.name() == null || a.name().isEmpty() || a.age() < 0 || a.weight() < 0)
                .collect(Collectors.toMap(
                        Animal::name,
                        a -> {
                            Set<String> errors = new HashSet<>();
                            if (a.name() == null || a.name().isEmpty()) errors.add("Invalid name");
                            if (a.age() < 0) errors.add("Invalid age");
                            if (a.weight() < 0) errors.add("Invalid weight");
                            return errors;
                        }
                ));
    }

    //20
    public Map<String, String> formatErrors(Map<String, Set<String>> errors) {
        return errors.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> String.join(", ", entry.getValue())
                ));
    }
}