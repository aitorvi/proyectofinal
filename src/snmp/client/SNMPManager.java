/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SNMP.client;

/**
 *
 * @author Aitor
 */
import java.io.IOException;
import javax.swing.JOptionPane;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class SNMPManager {

    Snmp snmp = null;
    String address = null;

    /**
     * Constructor
     *
     * @param add
     */
    public SNMPManager(String add) {
        address = add;
    }

    public static void main(String[] args) throws IOException {
        /**
         * Port 161 is used for Read and Other operations Port 162 is used for
         * the trap generation
         */
        String ipInicial = "192.168.1.138";
        SNMPManager client = new SNMPManager("udp:" + ipInicial + "/161");
        client.start();
        /**
         * OID - .1.3.6.1.2.1.1.1.0 => SysDec OID - .1.3.6.1.2.1.1.5.0 =>
         * SysName
         *
         */
        String sysDescr = client.getAsString(new OID(".1.3.6.1.2.1.1.1.0"));
        String SysName = client.getAsString(new OID(".1.3.6.1.2.1.1.1.0"));
        System.out.println(ipInicial + " " + sysDescr + " " + SysName);
    }

    /**
     * Start the Snmp session. If you forget the listen() method you will not
     * get any answers because the communication is asynchronous and the
     * listen() method listens for answers.
     *
     * @throws IOException
     */
    public void start() throws IOException {
        TransportMapping transport = new DefaultUdpTransportMapping();
        snmp = new Snmp(transport);
// Do not forget this line!
        transport.listen();
    }

    /**
     * Method which takes a single OID and returns the response from the agent
     * as a String.
     *
     * @param oid
     * @return
     * @throws IOException
     */
    public String getAsString(OID oid) throws IOException {
        try {
            ResponseEvent event = get(new OID[]{oid});
            return event.getResponse().get(0).getVariable().toString();
        } catch (NullPointerException e) {
            

        }
        return null;

    }

    /**
     * This method is capable of handling multiple OIDs
     *
     * @param oids
     * @return
     * @throws IOException
     */
    public ResponseEvent get(OID oids[]) throws IOException {
        try {
            PDU pdu = new PDU();
            for (OID oid : oids) {
                pdu.add(new VariableBinding(oid));
            }
            pdu.setType(PDU.GET);
            ResponseEvent event = snmp.send(pdu, getTarget(), null);
            if (event != null) {
                return event;
            }
            throw new RuntimeException("GET timed out");
        } catch (Exception ex) {
            

        }
        return null;

    }

    /**
     * This method returns a Target, which contains information about where the
     * data should be fetched and how.
     *
     * @return
     */
    private Target getTarget() {
        Address targetAddress = GenericAddress.parse(address);
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString("public"));
        target.setAddress(targetAddress);
        target.setRetries(2);
        target.setTimeout(1500);
        target.setVersion(SnmpConstants.version2c);
        return target;
    }

}