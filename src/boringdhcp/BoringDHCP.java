package boringdhcp;

public class BoringDHCP {

    public static void main(String[] args) {
        DHCPAddressPool.loadRange("192.168.1.1", "192.168.1.10");
        DHCPAddressPool.loadRange("10.0.0.1", "10.0.0.20");
        System.out.println("Initializing BoringDHCP v1.0 by Lorenzo Durastante\n");
        DHCPAddressPool.showAvailableAddresses();
        DHCPManager.registerClient("admin");
        DHCPManager.assignIP("admin", DHCPManager.selectIP());
        DHCPManager.registerClient("techoffice");
        DHCPManager.assignIP("techoffice", DHCPManager.selectIP());
        DHCPManager.showClients();
        DHCPManager.releaseIP("admin");
    }

}
