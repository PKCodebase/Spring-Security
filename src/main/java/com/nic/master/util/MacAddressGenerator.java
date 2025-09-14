package com.nic.master.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MacAddressGenerator {

    private static final Random random = new Random();

    public  String generateMacAddress() {
        byte[] macAddr = new byte[6];
        random.nextBytes(macAddr);

        // Make sure it's unicast and locally administered
        macAddr[0] = (byte) (macAddr[0] & (byte) 0xFE); // unicast
        macAddr[0] = (byte) (macAddr[0] | (byte) 0x02); // locally administered

        StringBuilder sb = new StringBuilder(18);
        for (byte b : macAddr) {
            if (sb.length() > 0) {
                sb.append(":");
            }
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }


}
