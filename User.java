import java.util.ArrayList;
import java.util.Random;

public class User
{
    static Random random = new Random();
    static String[] m_names = {"Wade", "Dave", "Seth", "Ivan", "Riley", "Gilbert", "Jorge", "Dan", "Brian","Ethan"};
    static String[] f_names = {"Deborah", "Isabel", "Stella", "Debra", "Beverly", "Vera", "Angela", "Lucy", "Tracey","Christina"};
    static String[] second_names = {"Allen", "Lopez", "Green", "Baker", "Ford", "Foster", "Warren", "Fisher", "Hamilton","Jordan"};
    static String[] emails = {"gmail.com", "mail.ru", "list.ru", "gmail.com", "gmail.com"};
    int id;
    String name;
    String second_name;
    String email;

    public User(int id, String name, String second_name, String email)
    {
        this.id = id;
        this.name = name;
        this.second_name = second_name;
        this.email = email;
    }

    public static ArrayList<User> generate_users(long n)
    {
        ArrayList<User> result = new ArrayList<>();

        if(n > 100)
        {
            n = 1;
        }

            String name = "";
            String second_name = "";
            String email = "";
            for (int i = 0; i < n; i++)
            {
                if (random.nextBoolean())
                {
                    name = m_names[random.nextInt(10)];
                } else
                    {
                        name = f_names[random.nextInt(10)];
                    }

                second_name = second_names[random.nextInt(10)];

                email = name.toLowerCase() + "." + second_name.toLowerCase() + "@" + emails[random.nextInt(5)];
                result.add(new User(0, name, second_name, email));
            }


        return result;
    }
}
