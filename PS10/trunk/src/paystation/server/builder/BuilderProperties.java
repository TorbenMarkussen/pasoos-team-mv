package paystation.server.builder;

/**
 * Created with IntelliJ IDEA.
 * User: pasma00t
 * Date: 17-05-12
 * Time: 14:04
 * To change this template use File | Settings | File Templates.
 */
public class BuilderProperties {
    private String serverName;

    public BuilderProperties(String[] args) {
        serverName = args[0];
    }

    public int getX() {
        return 100;
    }

    public int getY() {
        if(serverName.endsWith("1"))
            return 20;
        else if(serverName.endsWith("2"))
            return 170;
        else if(serverName.endsWith("3"))
            return 320;
        else if(serverName.endsWith("4"))
            return 470;

        return 620;
    }

    public String getServerName() {
        return serverName;
    }
}
