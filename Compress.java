import java.util.ArrayList;

import java.util.Scanner;

public class Compress {
    public Compress() {
        this.run();
    }

    private void run() {
        Scanner reference = null;
        String line = null;
        FileOperations operations = new FileOperations();

        reference = operations.openFile("encoded.txt");

        while ((line = operations.readFile(reference)) != null) {
            for (byte i: compress(line)) {
                operations.writeByte(i, "compressed.txt");
            }
        }
    }

    private ArrayList<Byte> compress(String line) {
        ArrayList<Byte> bytes = new ArrayList<Byte>();
        String codeByte = "";
        boolean flag = true;
        byte[] sum = new byte[2];

        for (int i = 1; i <= line.length(); i++) {
            if (i % 9 != 0) {
                codeByte += Character.toString(line.charAt(i - 1));
                flag = true;
            } else {
                sum = compressHelper(codeByte);
                bytes.add(sum[0]);
                codeByte = Character.toString(line.charAt(i - 1));
                flag = false;
            }
        }

        if (flag) {
            sum = compressHelper(codeByte);

            bytes.add(sum[0]);

            if (sum[1] != 0) {
                bytes.add(sum[1]);
            }
        }

        return bytes;
    }

    private byte[] compressHelper(String codeByte) {
        int length = codeByte.length();
        byte[] sum = {0, 0};

        for (int i = 0, j = length - 1; i < length; i++, j--) {
            if (codeByte.charAt(i) == '1') {
                sum[0] += (byte) (Math.pow(2, j));
            }
        }

        if (length != 8) {
            sum[1] = (byte) (8 - length);
        }

        return sum;
    }
}
