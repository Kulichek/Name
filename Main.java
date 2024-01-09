import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите данные в формате: Фамилия Имя Отчество датарождения номертелефона пол");

            String input = scanner.nextLine();

            String[] data = input.split(" ");

            if (data.length != 6) {
                System.out.println("Ошибка! Введено неверное количество данных.");
                return;
            }

            String lastName = data[0];
            String firstName = data[1];
            String middleName = data[2];
            String birthDate = data[3];
            String phoneNumber = data[4];
            String gender = data[5];

            try {
                parseData(lastName, firstName, middleName, birthDate, phoneNumber, gender);
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
                return;
            }

            try {
                writeToFile(lastName, firstName, middleName, birthDate, phoneNumber, gender);
            } catch (IOException e) {
                System.out.println("Ошибка записи в файл.");
                return;
            }
        }

        System.out.println("Данные успешно записаны в файл.");
    }

    private static void parseData(String lastName, String firstName, String middleName, String birthDate, String phoneNumber, String gender) throws InvalidDataException {
        if (!isValidName(lastName)) {
            throw new InvalidDataException("Ошибка! Неверный формат фамилии.");
        }

        if (!isValidName(firstName)) {
            throw new InvalidDataException("Ошибка! Неверный формат имени.");
        }

        if (!isValidName(middleName)) {
            throw new InvalidDataException("Ошибка! Неверный формат отчества.");
        }

        if (!isValidDate(birthDate)) {
            throw new InvalidDataException("Ошибка! Неверный формат даты рождения.");
        }

        if (!isValidPhoneNumber(phoneNumber)) {
            throw new InvalidDataException("Ошибка! Неверный формат номера телефона.");
        }

        if (!isValidGender(gender)) {
            throw new InvalidDataException("Ошибка! Неверный формат пола.");
        }
    }

    private static boolean isValidName(String name) {
        return name.matches("[А-Яа-я]+");
    }

    private static boolean isValidDate(String date) {
        return date.matches("\\d{2}.\\d{2}.\\d{4}");
    }

    private static boolean isValidPhoneNumber(String number) {
        return number.matches("\\d+");
    }

    private static boolean isValidGender(String gender) {
        return gender.equals("f") || gender.equals("m");
    }

    private static void writeToFile(String lastName, String firstName, String middleName, String birthDate, String phoneNumber, String gender) throws IOException {
        FileWriter fileWriter = new FileWriter(lastName + ".txt", true);

        String data = lastName + " " + firstName + " " + middleName + " " + birthDate + " " + phoneNumber + " " + gender + "\n";

        fileWriter.write(data);

        fileWriter.close();
    }
}

class InvalidDataException extends Exception {
    public InvalidDataException(String message) {
        super(message);
    }
}
