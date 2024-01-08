package frc.robot.subsystems;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;

import org.opencv.core.Point3;

import edu.wpi.first.math.geometry.Quaternion;

public class VisionNetParse extends Thread {

    private VisionData[] currData = new VisionData[16];

    private final int BUFFER = 256;
    private final int PORT = 5800;

    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[BUFFER];

    public VisionNetParse() throws SocketException {
        socket = new DatagramSocket(PORT);
    }

    public void run() {
        running = true;

        while(running) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);

                byte[] data = packet.getData();
                String stringData = new String(data, "UTF-8");

                Arrays.fill(currData, null);
                currData = parseData(stringData);
            } catch (Exception e) {

            }
        }
    }

    public VisionData getCurrData(int index) {
        return currData[index];
    }

    private VisionData[] parseData(String data) {
        VisionData[] result = new VisionData[16];
        
        //[qx:1231|qy:1321|...,qx:1232|qy:1232,,]
        String[] idData = data.split(",");
        for (int i = 0; i < idData.length; i++) {
            String[] kvs = idData[i].split("|");
            for (String kv : kvs) {
                String[] values = kv.split(":");
                try {
                    double num = Double.parseDouble(values[1]);
                    switch(values[0]) {
                        case "px":
                            result[i].pos.x = num;
                            break;
                        case "py":
                            result[i].pos.y = num;
                            break;
                        case "pz":
                            result[i].pos.z = num;
                            break;
                        case "qx":
                            result[i].qx = num;
                            break;
                        case "qy":
                            result[i].qy = num;
                            break;
                        case "qz":
                            result[i].qz = num;
                            break;
                        case "qw":
                            result[i].qw = num;
                            break;
                    }
                } catch (Exception e) {
                    result[i] = null;
                    break;
                }
            }
            if (result[i] != null && !result[i].verifyAll()) {
                result[i] = null;
            }
        }

        return result;
    }

    public class VisionData {
        public Point3 pos;
        public Quaternion rot;
        protected Double qx;
        protected Double qy;
        protected Double qz;
        protected Double qw;

        protected boolean verifyAll() {
            if (qx != null && qy != null && qz != null && qw != null) {
                rot = new Quaternion(qw, qx, qy, qz);
                return true;
            }
            return false;
        }
    }
}
