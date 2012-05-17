package paystation.client.builder;

/**
 * Created with IntelliJ IDEA.
 * User: pasma00t
 * Date: 17-05-12
 * Time: 14:45
 * To change this template use File | Settings | File Templates.
 */
public class MonitorProperties {
    private String id;

    public MonitorProperties(String[] args) {
        if(args.length > 0)
            id = args[0];
        else
            id="1";
    }

    public int getX() {
        return 580;
    }

    public int getY() {
        if(id.endsWith("1"))
            return 20;
        else if(id.endsWith("2"))
            return 170;
        else if(id.endsWith("3"))
            return 320;
        else if(id.endsWith("4"))
            return 470;

        return 620;

    }

    public String getRootUrl() {
        return "rmi://localhost/";
    }
}
