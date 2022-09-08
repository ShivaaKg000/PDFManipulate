package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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
			return Constants.ERRORE_ROOT_ORIGINE_NON_TROVATA;
		}
		if(!desFolder.isDirectory()) {
			return Constants.ERRORE_ROOT_DESTINAZIONE_NON_TROVATA;
		}
		
		if(folder.getAbsolutePath().equalsIgnoreCase(desFolder.getAbsolutePath().trim())) {
			return Constants.ERRORE_SAME_FOLDER;
		}
		
		logArea= ("Starting unlock process su "+ lockedPathString+" ...\n\n"); 
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
					logArea+=("Unlock "+lockedFile.getName()+Constants.ERRORE_DECRYPT);
				}
			}
			logArea+=Constants.END_DECRYPT;
		}else {
			logArea+=Constants.ERRORE_ROOT_ORIGINE_VUOTA;
		}
		return logArea;
	}


	private void manipulatePdf(String dest,String src) throws FileNotFoundException, IOException {
		PdfDocument pdfDoc;

		pdfDoc = new PdfDocument(new PdfReader(src).setUnethicalReading(true), new PdfWriter(dest));
		pdfDoc.close();


	}
}
