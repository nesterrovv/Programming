package commands;

import java.util.Objects;

public abstract class AbstractCommand {

    private String description; //Содержит краткое руководство к команде.

    public synchronized String execute() {
        return "Отсутствует аргумент.";
    }

    public synchronized String execute(String arg) {
        return execute(arg);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractCommand)) return false;
        AbstractCommand that = (AbstractCommand) o;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    @Override
    public String toString() {
        return "AbstractCommand{" +
                "description='" + description + '\'' +
                '}';
    }
}
