package serverCode.commands;

import java.util.Objects;

/**
 * Класс {@code AbstractCommand} является суперклассом для всех классов команд.
 * @author Артемий Кульбако
 * @version 2.0
 * @since 15.05.19
 */
public abstract class AbstractCommand {

    private String description; //Содержит краткое руководство к команде.

    /**
     * Метод служит для выполнения кода команды без агрументов.
     * @return строка, которая содержит результат операции.
     */
    public synchronized String execute() {
        return "Отсутствует аргумент.";
    }

    /**
     * Метод служит для выполнения кода команды с агрументами. Если аргументов больше одного, необходимо проверять
     * явно проверять длину массива args и вызвать метод execute(), при несоответствии количества принятых методом
     * аргументов.
     * @param args аргументы команды.
     * @return строка, которая содержит результат операции.
     */
    public synchronized String execute(String[] args) {
        return execute();
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

