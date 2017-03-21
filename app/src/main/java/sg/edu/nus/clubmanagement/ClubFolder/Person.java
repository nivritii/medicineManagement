package sg.edu.nus.clubmanagement.ClubFolder;

/**
 * Created by swarna on 4/8/2016.
 */
public class Person {

    private String surname;
    private String firstName;
    private String secondName;

    public Person (String surname, String firstName, String secondName) {
        this.surname = surname;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSurname() {
        return surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public boolean equals(Object person){
        if (person instanceof Person){
            Person p = (Person)person;
            if (this.getSurname().equals(p.getSurname())
                    && this.getFirstName().equals(p.getFirstName()))
                if (this.getSecondName() == null){
                    if (p.getSecondName() == null)
                        return true;
                    else return false;
                }else if (p.getSecondName() !=null)
                    if (this.getSecondName().equals(p.getSecondName()))
                        return true;
        }
        return false;
    }

    public String toString () {
        String fullName = firstName;
        if (secondName != null) {
            fullName += " " + secondName;
        }
        fullName += " " + surname;
        return (fullName);
    }

    public void show () {
        System.out.println (this);
    }
}
