package sg.edu.nus.clubmanagement.ClubFolder;

/**
 * Created by swarna on 4/8/2016.
 */
public class Member {

    public static final String DATE_FORMAT = "d-MMM-yyyy";

    private int medId;
    private String medName;
    private String medDesc;
    private String category;
    private String reminder;
    private int quantity;
    private int dosage;
    private int threshold;
    private String dateIssued;
    private int expireFactor;

    public Member (String medName, String medDesc, String category, String reminder, int quantity, int dosage, int threshold,
                     String dateIssued, int expireFactor){
        this.medName = medName;
        this.medDesc = medDesc;
        this.category = category;
        this.reminder = reminder;
        this.quantity = quantity;
        this.dosage = dosage;
        this.threshold = threshold;
        this.dateIssued = dateIssued;
        this.expireFactor = expireFactor;
    }

    public Member(int medId, String medName, String medDesc, String category, String reminder, int quantity, int dosage, int threshold, String dateIssued, int expireFactor) {
        this.medId = medId;
        this.medName = medName;
        this.medDesc = medDesc;
        this.category = category;
        this.reminder = reminder;
        this.quantity = quantity;
        this.dosage = dosage;
        this.threshold = threshold;
        this.dateIssued = dateIssued;
        this.expireFactor = expireFactor;
    }

    public int getMedId() {
        return medId;
    }

    public String getMedName() {
        return medName;
    }

    public String getMedDesc() {
        return medDesc;
    }

    public String getCategory() {
        return category;
    }

    public String getReminder() {
        return reminder;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getDosage() {
        return dosage;
    }

    public int getThreshold() {
        return threshold;
    }

    public String getDateIssued() {
        return dateIssued;
    }

    public int getExpireFactor() {
        return expireFactor;
    }

    public String toString () {
        return (medId + " - " + super.toString ());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + medId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Member other = (Member) obj;
        super.equals(other);
        if (medId != other.medId)
            return false;
        return true;
    }

    // Added so that Members can be sorted by membership number
    public int compareTo (Member other) {
        return (getMedId() - other.getMedId());
    }
}
