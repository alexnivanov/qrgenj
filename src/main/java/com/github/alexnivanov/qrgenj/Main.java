package com.github.alexnivanov.qrgenj;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    private static final String QR_FILE = "qr.png";
    private static final int DEFAULT_SIZE = 512;

    public static void main(String[] args) throws WriterException, IOException {
        if (args.length < 1) {
            System.out.println("Usage: java -jar build/libs/qrgenj-1.0-SNAPSHOT-all.jar INPUT [SIZE]");
            return;
        }

        final String input = args[0];
        final int size = args.length > 1 ? Integer.parseInt(args[1]) : DEFAULT_SIZE;

        System.out.println("Generating QR for '" + input + "', size " + size);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(input, BarcodeFormat.QR_CODE, size, size);

        FileOutputStream fos = new FileOutputStream(QR_FILE);
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", fos);

        System.out.println("QR written to " + QR_FILE);
    }
}
