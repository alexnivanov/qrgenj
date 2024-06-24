package com.github.alexnivanov.qrgenj;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static final String QR_FILE = "qr.png";
    private static final int DEFAULT_SIZE = 512;
    private static final int DEFAULT_MARGIN = 2;

    public static void main(String[] args) throws WriterException, IOException {
        if (args.length < 1) {
            System.out.println("Usage: java -jar build/libs/qrgenj-1.0-SNAPSHOT-all.jar INPUT [SIZE] [MARGIN]");
            return;
        }

        final String input = args[0];
        final int size = args.length > 1 ? Integer.parseInt(args[1]) : DEFAULT_SIZE;
        final int margin = args.length > 2 ? Integer.parseInt(args[2]) : DEFAULT_MARGIN;

        System.out.println("Generating QR for '" + input + "', size " + size);

        Map<EncodeHintType, Integer> hints = new HashMap<>();
        hints.put(EncodeHintType.MARGIN, margin);
        final QRCodeWriter qrCodeWriter = new QRCodeWriter();
        final BitMatrix bitMatrix = qrCodeWriter.encode(input, BarcodeFormat.QR_CODE, size, size, hints);

        final FileOutputStream fos = new FileOutputStream(QR_FILE);
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", fos);

        System.out.println("QR written to " + QR_FILE);
    }
}
