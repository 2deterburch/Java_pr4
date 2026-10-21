
interface Movable {
    void move();

    default void info() {
        System.out.println("Movable: тварина може рухатися");
    }
}

interface Feedable {
    void feed();

    default void info() {
        System.out.println("Feedable: тварину можна годувати");
    }
}

// Абстрактний клас
abstract class Animal {
    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public abstract void makeSound();

    public void showName() {
        System.out.println("Тварина: " + name);
    }
}

// Похідний клас Птах
class Bird extends Animal implements Movable, Feedable {
    public Bird(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(name + ": цвірінь!");
    }

    @Override
    public void move() {
        System.out.println(name + " літає");
    }

    @Override
    public void feed() {
        System.out.println(name + " їсть зерно");
    }

    @Override
    public void info() {
        Movable.super.info();
        Feedable.super.info();
    }
}

// Похідний клас Риба
class Fish extends Animal implements Movable, Feedable {
    public Fish(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(name + ": буль-буль!");
    }

    @Override
    public void move() {
        System.out.println(name + " плаває");
    }

    @Override
    public void feed() {
        System.out.println(name + " їсть корм");
    }

    @Override
    public void info() {
        Movable.super.info();
        Feedable.super.info();
    }
}

// Інтерфейс генератора номерів
interface NumberGenerator {
    String generate();
}

// Послідовна генерація
class SequentialGenerator implements NumberGenerator {
    private int number = 0;

    @Override
    public String generate() {
        number++;
        return String.valueOf(number);
    }
}

// Генерація з префіксом
class PrefixGenerator implements NumberGenerator {
    private int number = 0;

    @Override
    public String generate() {
        number++;
        return "ANIMAL-" + number;
    }
}

// Сервіс з передачею залежності через конструктор
class AnimalService {
    private final NumberGenerator generator;

    public AnimalService(NumberGenerator generator) {
        this.generator = generator;
    }

    public void register(Animal animal) {
        System.out.println("Номер: " + generator.generate());
        animal.showName();
    }
}

public class Main {
    public static void main(String[] args) {

        // Абстрактний клас
        // Animal animal = new Animal("Тварина");
        // Помилка: абстрактний клас не можна створити

        Animal bird = new Bird("Папуга");
        Animal fish = new Fish("Короп");

        System.out.println("=== АБСТРАКТНІ КЛАСИ ===");
        bird.showName();
        bird.makeSound();
        fish.showName();
        fish.makeSound();

        System.out.println("\n=== ІНТЕРФЕЙСИ ===");
        Movable movable = (Movable) bird;
        Feedable feedable = (Feedable) bird;

        movable.move();
        feedable.feed();

        // Вирішення конфлікту default-методів
        ((Bird) bird).info();

        System.out.println("\n=== СЕРВІС: ПОСЛІДОВНІ НОМЕРИ ===");
        AnimalService service1 =
                new AnimalService(new SequentialGenerator());

        service1.register(bird);
        service1.register(fish);

        System.out.println("\n=== СЕРВІС: НОМЕРИ З ПРЕФІКСОМ ===");
        AnimalService service2 =
                new AnimalService(new PrefixGenerator());

        service2.register(bird);
        service2.register(fish);
    }
}
