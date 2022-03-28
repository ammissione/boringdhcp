package boringdhcp;

import java.util.HashMap;
import java.util.Map;

public class DHCPManager {

    public static HashMap<String, DHCPAddress> clients = new HashMap<>();

    public static void registerClient(String hostName) {
        DHCPManager.clients.put(hostName, new DHCPAddress(""));
        System.out.println("Registering client " + hostName);
    }

    public static void showClients() {
        for(Map.Entry<String, DHCPAddress> entrySet : DHCPManager.clients.entrySet()) {
            if(entrySet.getValue() == null) continue;
            if(entrySet.getValue().getIp().isEmpty()) {
                System.out.println("Client " + entrySet.getKey() + " has no IP ");
            }else {
                System.out.println("Client " + entrySet.getKey() + " has IP " + entrySet.getValue().getIp());
            }
        }
    }

    public static DHCPAddress selectIP() {
       if(DHCPAddressPool.availableAddresses.size() == 0) {
           return null;
       }
       DHCPAddress address = DHCPAddressPool.availableAddresses.get(0);
       DHCPAddressPool.availableAddresses.remove(address);
       DHCPAddressPool.usedAddresses.add(address);
       return address;
    }

    public static void assignIP(String hostName, DHCPAddress address) {
        if(address == null) return;
        System.out.println("Assigning IP " + address.getIp() + " to client " + hostName);
        DHCPManager.clients.put(hostName, address);
    }

    public static void releaseIP(String hostName) {
        DHCPAddress address = DHCPManager.clients.get(hostName);
        DHCPManager.clients.put(hostName, new DHCPAddress(""));
        DHCPAddressPool.usedAddresses.remove(address);
        DHCPAddressPool.availableAddresses.add(address);
        System.out.println("Released IP " + address.getIp() + " previously assigned to " + hostName);
    }

}
