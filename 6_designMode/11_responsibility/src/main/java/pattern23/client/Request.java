package pattern23.client;

/**
 * User: hanwei
 * Date: 15-5-19
 * Time: обнГ3:04
 */
public class Request {
    private String serviceName;

    public Request(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
