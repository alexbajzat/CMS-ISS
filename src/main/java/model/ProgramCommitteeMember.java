package model;

/**
 * Created by bjz on 5/9/2017.
 */
public class ProgramCommitteeMember {
    private final Integer id;
    private final String affiliation;
    private final String email;
    private final String webpage;

    public static ProgramCommitteeMemberBuilder builder() {
        return new ProgramCommitteeMemberBuilder();
    }

    ProgramCommitteeMember(Integer id, String affiliation, String email, String webpage) {
        this.id = id;
        this.affiliation = affiliation;
        this.email = email;
        this.webpage = webpage;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public String getEmail() {
        return email;
    }

    public String getWebpage() {
        return webpage;
    }
}
