package boringdhcp;

import java.util.ArrayList;

public class DHCPAddressPool {

    public static ArrayList<DHCPAddress> availableAddresses = new ArrayList<>();
    public static ArrayList<DHCPAddress> usedAddresses = new ArrayList<>();

    private static void loadAddress(DHCPAddress address) {
        DHCPAddressPool.availableAddresses.add(address);
    }

    public static void loadRange(String addressInit, String addressEnd) {
        for(int i = Integer.parseInt(addressInit.split("\\.")[3]); i <= Integer.parseInt(addressEnd.split("\\.")[3]); i++) {
            String[] splittedOld = addressInit.split("\\.");
            splittedOld[3] = String.valueOf(i);
            String fullIP = String.join(".", splittedOld);
            DHCPAddress dhcpAddress = new DHCPAddress(fullIP);
            loadAddress(dhcpAddress);
        }
    }

    public static void showAvailableAddresses() {
        int poolSize = DHCPAddressPool.availableAddresses.size();
        System.out.println("Searching for available addresses...");
        if (poolSize == 0) {
            System.out.println("There are no available addresses that could be assigned");
            return;
        }
        System.out.println("Found " + poolSize + " addrress");
        for (DHCPAddress address : DHCPAddressPool.availableAddresses) {
            System.out.println(address.getIp());
        }
    }

}
