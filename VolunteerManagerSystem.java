import java.util.List;
import java.util.ArrayList;

class Volunteer {

    private int id;
    private String name;
    private String email;

    Volunteer(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("-----------------");
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    String getEmail() {
        return email;
    }

}

class VolunteerManager {

    private List<Volunteer> volunteers = new ArrayList<>();

    String addVolunteer(Volunteer v) {

        if(getVolunteerById(v.getId()) != null) {
            return "ID already exists";
        } 

        volunteers.add(v);
        return "Volunteer added";

    }

    void showAll() {

        if(volunteers.isEmpty()) {
            System.out.println("No volunteers found");
            return;
        }

        for(Volunteer v : volunteers) {
            v.display();
        }


    }

    Volunteer getVolunteerById(int id) {
        for(Volunteer v : volunteers) {
            if(v.getId() == id) return v;
        }

        return null;
    }

    String deleteVolunteerById(int id) {

        for(int i = 0; i < volunteers.size(); i++) {
            if (volunteers.get(i).getId() == id) {
                volunteers.remove(i);
                return "Volunteer deleted";
            }
        }

        return "Volunteer not found";
    }

}

public class VolunteerManagerSystem {
    public static void main(String[] args) {
        VolunteerManager manager = new VolunteerManager();
        System.out.println(manager.addVolunteer(new Volunteer(1, "Rahul", "rahul@gmail.com")));
        System.out.println(manager.addVolunteer(new Volunteer(2, "Sneha", "sneha@gmail.com")));
        System.out.println("All Volunteers:");
        manager.showAll();
        System.out.println("Volunteer data with ID 1:");
        Volunteer v = manager.getVolunteerById(1);
        if (v != null) v.display();
        System.out.println(manager.deleteVolunteerById(1));
        System.out.println("All Volunteers after removing volunteer with ID 1:");
        manager.showAll();
        System.out.println(manager.addVolunteer(new Volunteer(2, "Ravi", "ravi@gmail.com")));
        manager.showAll();
    }

}
