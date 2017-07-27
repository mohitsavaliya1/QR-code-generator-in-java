import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCode {

	public static void main(String[] args) throws WriterException, IOException,
			NotFoundException {
		
		String charset = "UTF-8"; // or "ISO-8859-1"
		Map hintMap = new HashMap();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

		Map<Integer,String> custpay = new HashMap<>();
		
		custpay.put(1, "1523");
		custpay.put(2, "00");
		custpay.put(3, "100");
		custpay.put(4, "5896");
		custpay.put(5, "158962");
		custpay.put(6, "1000000");
		custpay.put(7, "2589");
		
	int count = 1;
		for(int i=0;i<custpay.size();i++){
			String qrCodeData = custpay.get(count);
			String filePath = "D:\\";
			String h = Integer.toString(count);
		filePath = filePath + h + ".png";
			System.out.println(filePath);
			createQRCode(qrCodeData, filePath, charset, hintMap, 200, 200);
			System.out.println("QR Code image created successfully! for " + count);
			
	count++;
		}
	
	String filePath = "D:\\";
		Scanner sc = new Scanner(System.in);
		System.out.println("Entre cust no.");
		int y = sc.nextInt();
		String h = Integer.toString(y);
		filePath = filePath + h + ".png"; 
		//System.out.println(filePath);
	System.out.println("Data read from QR Code: "
			+ readQRCode(filePath, charset, hintMap));

	}

	public static void createQRCode(String qrCodeData, String filePath,
			String charset, Map hintMap, int qrCodeheight, int qrCodewidth)
			throws WriterException, IOException {
		BitMatrix matrix = new MultiFormatWriter().encode(
				new String(qrCodeData.getBytes(charset), charset),
				BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
		MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
				.lastIndexOf('.') + 1), new File(filePath));
	}

	public static String readQRCode(String filePath, String charset, Map hintMap)
			throws FileNotFoundException, IOException, NotFoundException {
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
				new BufferedImageLuminanceSource(
						ImageIO.read(new FileInputStream(filePath)))));
		Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap,
				hintMap);
		return qrCodeResult.getText();
	}
}
