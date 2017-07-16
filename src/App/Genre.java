package App;

/**
 * Created by jenny on 7/12/2017.
 */
public enum Genre {
    FICTION("Fiction"), NONFICTION("Nonfiction"), HISTORICAL("Historical"), DRAMA("Drama"), BIOGRAPHICAL("Biographical"), MYSTERY("Mystery");

    private String stringVersion;

    Genre(String stringVersion) {
        this.stringVersion = stringVersion;
    }

    public static Genre getEnumVersion(String stat){
        Genre result = null;
        switch (stat){
            case "nonfiction":
                result = Genre.NONFICTION;
                break;
            case "historical":
                result = Genre.HISTORICAL;
                break;
            case "fiction":
                result = Genre.FICTION;
                break;
            case "drama":
                result = Genre.DRAMA;
                break;
            case "biographical":
                result = Genre.BIOGRAPHICAL;
                break;
            case "mystery":
                result = Genre.MYSTERY;
                break;
            default:
                break;
        }
        return result;
    }

    //Override to output Genre as string (as declared for stringVersion) instead of reference location:
    @Override
    public String toString() {
        return stringVersion;
    }

}