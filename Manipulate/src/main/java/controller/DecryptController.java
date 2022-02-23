package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;

import manipulate.Constants;

public class DecryptController {

	private static DecryptController decryptController;

	public static DecryptController getInstance() {
		if(decryptController==null)
			decryptController= new DecryptController();
		return decryptController;
	}

	public String decrypt(String lockedPathString, String unLockedPathString) {
		String logArea = "";
		File desFolder = new File(unLockedPathString);
		File folder = new File(lockedPathString);

		if(!folder.isDirectory()) {
			return "Source path is not a folder!";
		}
		if(!desFolder.isDirectory()) {
			return "Destination path is not a folder!";
		}
		
		if(folder.getAbsolutePath().equalsIgnoreCase(desFolder.getAbsolutePath().trim())) {
			return "Source and destination paths folders are the same!!! Select a different folder";
		}
		
		logArea= ("Starting unlock process on "+ lockedPathString+" ...\n\n"); 
		File[] folderList = folder.listFiles();
		if(folderList.length > 0) { 
			for (File lockedFile : folderList) {
				File file = new File(unLockedPathString);
				file.getParentFile().mkdirs();
				try {
					DecryptController.getInstance().manipulatePdf(unLockedPathString+lockedFile.getName(),lockedPathString+lockedFile.getName());
					logArea+=(lockedFile.getName()+" unlocked! \n");
				} catch (Exception e) {
					LogController.getInstance(unLockedPathString+Constants.FILE_ERROR_LOG_NAME).printError("Unlock "+lockedFile.getName()+" KO!\n"+ e.toString() );
					logArea+=("Unlock "+lockedFile.getName()+" KO! For more details read errorLog.txt on unlocked folder.\n");
				}
			}
			logArea+=("\n\nDecrypting ended!");
		}else {
			logArea+=("The source folder is empty");
		}
		return logArea;
	}


	private void manipulatePdf(String dest,String src) throws FileNotFoundException, IOException {
		PdfDocument pdfDoc;

		pdfDoc = new PdfDocument(new PdfReader(src).setUnethicalReading(true), new PdfWriter(dest));
		pdfDoc.close();


	}
}
