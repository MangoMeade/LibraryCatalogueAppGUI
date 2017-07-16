package App;

/**
 * Created by jenny on 7/12/2017.
 */
public enum Status {

    ON_SHELF("On Shelf"), CHECKED_OUT("Checked Out"), RESERVED("Reserved");

    private String stringVersion;

    Status(String stringVersion) {
        this.stringVersion = stringVersion;
    }

    public static Status getEnumVersion(String stat){
        Status result = null;
        switch (stat){
            case "Reserved":
                result = Status.RESERVED;
                break;
            case "Checked Out":
                result = Status.CHECKED_OUT;
                break;
            case "On Shelf":
                result = Status.ON_SHELF;
                break;
            default:
                break;
        }
        return result;
    }
    //Override to output Status as string (as declared for stringVersion) instead of reference location:
    @Override
    public String toString() {
        return stringVersion;
    }

}