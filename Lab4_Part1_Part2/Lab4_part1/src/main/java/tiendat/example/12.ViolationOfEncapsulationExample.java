package tiendat.example;

import java.util.logging.Level;
import java.util.logging.Logger;

class User {
    private static final Logger LOGGER = Logger.getLogger(User.class.getName());

    private String name;
    private int age;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("age must be non-negative");
        }
        this.age = age;
    }

    public void display() {
        LOGGER.log(Level.INFO, "Name: {0}, Age: {1}", new Object[] { name, age });
    }
}
