import java.util.List;
import java.util.ArrayList;

// Represents one volunteer in the system and stores volunteer details like id, name, email.
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

    void setEmail(String email) {
        this.email = email;
    }

}

// Handles volunteer operations: add, search, update, delete, list.
class VolunteerManager {

    private List<Volunteer> volunteers = new ArrayList<>();

    String addVolunteer(Volunteer v) {

        // Prevent duplicate volunteer IDs before adding record
        if(getVolunteerById(v.getId()) != null) return "ID already exists";
        if(!isValidEmail(v.getEmail())) return "Invalid Email";
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

    boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    String updateVolunteerEmail(int id, String newEmail) {

        if (!isValidEmail(newEmail)) return "Invalid Email";

        Volunteer v = getVolunteerById(id);

        if(v != null) {
            v.setEmail(newEmail);
            return "Email updated";
        }
        
        return "Volunteer not found";

    }

    List<Volunteer> getAllVolunteers() {
        return new ArrayList<>(volunteers);
    }

    Volunteer getVolunteerByName(String name) {

        for(Volunteer v : volunteers) {
            if(v.getName().equalsIgnoreCase(name)) return v;
        }

        return null;
    }

    List<Volunteer> getVolunteersByName(String name) {
        List<Volunteer> vol = new ArrayList<>(); 
        for(Volunteer v : volunteers) {
            if(v.getName().equals(name))  {
                vol.add(v);
            }
        }

        return vol;

    }

    int getVolunteerCount() {
        return volunteers.size();
    }

    List <Volunteer> findByNamePrefix(String prefix) {

        List<Volunteer> result = new ArrayList<>();

        for(Volunteer v : volunteers) {
            if(v.getName().toLowerCase().startsWith(prefix.toLowerCase())) result.add(v);
        }

        return result;

    }

}

public class VolunteerManagerSystem {

    public static void main(String[] args) {

        VolunteerManager manager = new VolunteerManager();

        // ADD VOLUNTEERS
        System.out.println("=== ADD VOLUNTEERS ===");
        System.out.println(manager.addVolunteer(new Volunteer(1, "Rahul", "rahul@gmail.com")));
        System.out.println(manager.addVolunteer(new Volunteer(2, "Sneha", "sneha@gmail.com")));
        System.out.println(manager.addVolunteer(new Volunteer(3, "Rahul", "rahul2@gmail.com")));

        // SHOW ALL
        System.out.println("\n=== ALL VOLUNTEERS ===");
        manager.showAll();

        // DUPLICATE ID TEST
        System.out.println("\n=== DUPLICATE ID TEST ===");
        System.out.println(manager.addVolunteer(new Volunteer(1, "Ravi", "ravi@gmail.com")));

        // SHOW ALL
        System.out.println("\n=== ALL VOLUNTEERS TO CHECK IF DUPLICATE ID TEST WORKS===");
        manager.showAll();

        // GET BY ID
        System.out.println("\n=== GET VOLUNTEER BY ID: 2 ===");
        Volunteer byId = manager.getVolunteerById(2);
        if (byId != null) {
            byId.display();
        } else {
            System.out.println("Volunteer not found");
        }

        // GET BY INVALID ID
        System.out.println("\n=== GET VOLUNTEER BY ID: 99 ===");
        Volunteer invalidId = manager.getVolunteerById(99);
        if (invalidId != null) {
            invalidId.display();
        } else {
            System.out.println("Volunteer not found");
        }

        // GET BY NAME
        System.out.println("\n=== GET ONE VOLUNTEER BY NAME: Rahul ===");
        Volunteer byName = manager.getVolunteerByName("Rahul");
        if (byName != null) {
            byName.display();
        } else {
            System.out.println("Volunteer not found");
        }

        // GET MULTIPLE BY NAME
        System.out.println("\n=== GET ALL VOLUNTEERS BY NAME: Rahul ===");
        List<Volunteer> sameNames = manager.getVolunteersByName("Rahul");
        if (sameNames.isEmpty()) {
            System.out.println("No volunteers found");
        } else {
            for (Volunteer v : sameNames) {
                v.display();
            }
        }

        // UPDATE EMAIL
        System.out.println("\n=== UPDATE EMAIL OF ID 2 ===");
        System.out.println(manager.updateVolunteerEmail(2, "sneha_new@gmail.com"));
        Volunteer volunteer = manager.getVolunteerById(2);
        if (volunteer != null) volunteer.display();

        // DELETE BY ID
        System.out.println("\n=== DELETE ID 1 ===");
        System.out.println(manager.deleteVolunteerById(1));

        // DELETE INVALID ID
        System.out.println("\n=== DELETE ID 99 ===");
        System.out.println(manager.deleteVolunteerById(99));

        // SHOW ALL AFTER DELETE
        System.out.println("\n=== ALL VOLUNTEERS AFTER DELETE ===");
        manager.showAll();

        // GET ALL VOLUNTEERS METHOD
        System.out.println("\n=== GET ALL USING getAllVolunteers() ===");
        List<Volunteer> all = manager.getAllVolunteers();

        for (Volunteer v : all) {
            v.display();
        }

        // COUNT
        System.out.println("\n=== TOTAL COUNT ===");
        System.out.println(manager.getVolunteerCount());

        //INVALID EMAIL CHECK
        System.out.println("\n=== INVALID EMAIL TEST ===");
        System.out.println(manager.addVolunteer(new Volunteer(4, "Ravi", "ravigmail.com")));

        System.out.println("\n=== PREFIX SEARCH: RA ===");
        List<Volunteer> prefixResults = manager.findByNamePrefix("Ra");
        if (prefixResults.isEmpty()) {
            System.out.println("No volunteers found");
        } else {
            for (Volunteer v : prefixResults) {
                v.display();
            }
        }
    }
}
