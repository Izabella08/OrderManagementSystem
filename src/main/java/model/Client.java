package model;

/**
 * CLASS THAT CONTAINS THE DETAILS REGARDING A CLIENT
 */
public class Client {

    /**
     * Client's attributes
     */
    private Integer clientId;
    private String clientName;
    private String clientAddress;
    private String emailAddress;

    public Client() {

    }

    public Client(Integer clientId, String clientName, String clientAddress, String emailAddress) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.emailAddress = emailAddress;
    }

    public Client(String clientName) {
        this.clientName = clientName;
    }

    public Client(String clientName, String clientAddress) {
        this.clientName = clientName;
        this.clientAddress = clientAddress;
    }

    public Client(String clientName, String clientAddress, String emailAddress) {
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.emailAddress = emailAddress;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}
