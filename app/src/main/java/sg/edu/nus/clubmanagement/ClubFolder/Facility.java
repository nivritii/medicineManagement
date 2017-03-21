package sg.edu.nus.clubmanagement.ClubFolder;

/**
 * Created by swarna on 4/8/2016.
 */
public class Facility implements Comparable<Facility> {

    private int catId;
    private String name;
    private String description;
    private String code;
    private String reminder;

    public Facility (String name, String description, String code, String reminder) {
        this.name = name;
        this.description = description;
    }

    public Facility (int catId, String name, String description, String code, String reminder) {
        this.name = name;
        this.description = description;
        this.code = code;
        this.reminder = reminder;
        this.catId = catId;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public void setFacilityNumber(int catId) {
        this.catId = catId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFacilityNumber () {
        return catId;
    }

    public String getName () {
        return name;
    }

    public String getDescription () {
        return description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + catId;
        return result;
    }

    public boolean equals(Object facility) {
        if (facility instanceof Facility) {
            Facility fac = (Facility) facility;
            if (this.getName().equals(fac.getName()))
                if (this.getDescription() == null)
                    if (fac.getDescription() == null)
                        return true;
                    else return false;
                else if (fac.getDescription() != null)
                    if (this.getDescription().equals(fac.getDescription()))
                        return true;

        }
        return false;
    }

    public String toString () {
        String fullName = catId + " - " + name;
        if (description != null) {
            fullName += " (" + description + ")";
        }
        return (fullName);
    }

    public void show () {
        System.out.println (this);
    }

    // Added so that Facilities can be sorted alphabetically
    public int compareTo (Facility other) {
        return (getName().compareTo(other.getName()));
    }
}
