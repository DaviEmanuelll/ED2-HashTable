
public class WebAddress {
    String address;
    String dns;
    int countFrequency = 0;
    WebAddress previous;
    WebAddress next;

    public WebAddress() {
    }
    public WebAddress(String address, String dns) {
        this.address = address;
        this.dns = dns;
    }
    public WebAddress(String address, String dns, int countFrequency) {
        this.address = address;
        this.dns = dns;
        this.countFrequency = countFrequency;
    }
    
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getdns() {
        return dns;
    }
    public void setdns(String dns) {
        this.dns = dns;
    } 
    
    public int getCountFrequency() {
        return countFrequency;
    }
    public void setCountFrequency(int countFrequency) {

        this.countFrequency = countFrequency;
    }
}