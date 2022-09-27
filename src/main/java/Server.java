import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.TreeSet;

public class Server {

    private static final int PORT = 8989;

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер стартовал");
            String city = null;
            Set<String> cities = new TreeSet<>();
            char lastLetter = ' ';
            char firstLetter;
            boolean firstCity = true;

            while (true) {
                try (Socket clientSocket = serverSocket.accept(); PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    if (firstCity) {
                        out.println("???");
                        city = in.readLine();
                        cities.add(city.toLowerCase());
                        lastLetter = city.charAt(city.length() - 1);
                        firstCity = false;
                    } else {
                        if (lastLetter == 'ь') {
                            lastLetter = city.charAt(city.length() - 2);
                        }
                        out.println("Введите название города на букву " + lastLetter);
                        String city2 = in.readLine();
                        firstLetter = city2.toLowerCase().charAt(0);

                        if (lastLetter == firstLetter) {
                            if (cities.contains(city2.toLowerCase())) {
                                out.println("Not OK. Этот город уже называли. Введите другой.");
                                continue;
                            }
                            city = city2;
                            lastLetter = city2.charAt(city.length() - 1);
                            cities.add(city2.toLowerCase());
                            out.println("OK. Вы ввели: " + city);
                        } else {
                            out.println("NOT OK. Вы ввели название города на букву " + firstLetter);
                        }
                    }

                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
