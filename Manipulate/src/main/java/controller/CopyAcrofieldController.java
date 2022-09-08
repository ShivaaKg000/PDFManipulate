package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalTime;  
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PushbuttonField;

import manipulate.Constants;

public class CopyAcrofieldController {

	private static CopyAcrofieldController copyAcrofieldController;

	public static CopyAcrofieldController getInstance() {
		if(copyAcrofieldController==null)
			copyAcrofieldController= new CopyAcrofieldController();
		return copyAcrofieldController;
	}

	private CopyAcrofieldController() {}


	public String MapMultipleDoc(String basePath, boolean enableLogoDisclaimer) {

		String log= new String();
		String codiceProdotto;
		File folder = new File(basePath);
		if(folder.isDirectory()) {    												// COntrollo se root è directory
			for (File directory : folder.listFiles()) { 							// ciclo su tutte le reti
				if (directory.isDirectory()) {										// per ogni folder interna tipo W
					String nomeRete= directory.getName();
					File reteFolder = new File(basePath +"\\" +nomeRete);
					for (File directoryProdotto : reteFolder.listFiles()) {		 // ciclo su tutti i files
						if (directoryProdotto.isDirectory()) {						// per ogni folder interna 
							codiceProdotto=directoryProdotto.getName()+"\\";
							File DIRECTORY_ORIGINI = new File(basePath +"\\"+ nomeRete+"\\"+ codiceProdotto +"\\"+Constants.MAPPED);
							if(DIRECTORY_ORIGINI.listFiles()!=null)
							{
								for (File file : DIRECTORY_ORIGINI.listFiles()) { // ciclo su tutti i files
									try {
										int ris=MapDoc(file,codiceProdotto,nomeRete+"\\",basePath, enableLogoDisclaimer);
										if(ris==-1) {
											log+=LogController.getInstance(basePath).printError(codiceProdotto+file.getName(),Constants.ERRORE_TEMPLATE_NON_TROVATO,basePath);
										}
										if(ris==1) {
											log+=LogController.getInstance(basePath).printOK(codiceProdotto+file.getName(),basePath);
										}
									} catch (Exception e) {
										log+=LogController.getInstance(basePath).printError(file.getName(), e.toString(),basePath);
									}
								}
							}

						} 
					}
				}
			}

		}else {
			log+=Constants.ERRORE_ROOT_ORIGINE_NON_TROVATA;
		}		

		return log;

	}


	
	
	public int MapDoc(File file,String codiceProdotto,String rete, String basePath,boolean enableLogoDisclaimer) throws Exception  {
		if(!file.isDirectory()) {
			String DOC_NAME=file.getName();
			String TEMPLATE_FILLATO =  basePath+"\\" +rete+codiceProdotto+ Constants.MAPPED  + DOC_NAME ;/*path Documento fillato da usare come guida*/							
			String TEMPLATE_DA_DIGITALIZZARE = basePath+"\\" +rete+ codiceProdotto+Constants.TO_MAP + DOC_NAME ;/*path documento vuoto*/
			String DIRECTORY_FILE_GENERATO =basePath+"\\" +rete+ codiceProdotto;
			String DOC_GENERATO = basePath+"\\" + rete +codiceProdotto +DOC_NAME ;/*path Documento generato*/	
			File template = new File(TEMPLATE_DA_DIGITALIZZARE);
			if(template.exists()) {
				PdfReader pieno;

				pieno = new PdfReader( TEMPLATE_FILLATO );
				PdfReader vuoto = new PdfReader( TEMPLATE_DA_DIGITALIZZARE ); // throws
				//BaseFont font = BaseFont.createFont(FONT, BaseFont.CP1252, BaseFont.EMBEDDED);
				File directory_file_generato = new File(String.valueOf(DIRECTORY_FILE_GENERATO));
				directory_file_generato.getAbsolutePath().toString();
				PdfStamper stamper = new PdfStamper( pieno ,  new FileOutputStream(DOC_GENERATO) ); //throws
				for( int i = 1; i <= pieno.getNumberOfPages(); ++i) {
					stamper.replacePage( vuoto, i, i );
				}
				// inserimento acrofield logo e disclaimer SPI in alto a destra della prima pagina se flag enableLogoDisclaimer è true
				System.out.println(enableLogoDisclaimer);
				if(enableLogoDisclaimer) {
                PushbuttonField logo = new PushbuttonField(stamper.getWriter(), new Rectangle(Constants.LogoLLX,Constants.LogoLLY,Constants.LogoURX,Constants.LogoURY), Constants.LOGO_FIELD);
                stamper.addAnnotation(logo.getField(), 1);

                PushbuttonField disclaimer = new PushbuttonField(stamper.getWriter(), new Rectangle(Constants.DisclaimerLLX,Constants.DisclaimerLLY,Constants.DisclaimerURX,Constants.DisclaimerURY), Constants.DISCLAIMER_FIELD);
                stamper.addAnnotation(disclaimer.getField(), 1);
				}
				
				stamper.close();
				return 1; // DOC GENERATO
			}
			else {
				return -1; // ERRORE TEMPLATE NON TROVATO
			}

		}
		else return 0;
	}


	public String MapSingleDoc(String toMap, String mapped, String destinazione, boolean enableLogoDisclaimer)  {

		try {
			String nameFile = toMap.substring(toMap.lastIndexOf("\\"),toMap.length());
			File toMapFile = new File(toMap);
			File mappedFile = new File(mapped);
			if(toMapFile.isFile() && mappedFile.isFile()) {  //controlla se sono file
				PdfReader pieno = new PdfReader( mapped );
				PdfReader vuoto = new PdfReader( toMap ); 
				PdfStamper stamper = new PdfStamper( pieno ,  new FileOutputStream(destinazione+nameFile) ); 

				for( int i = 1; i <= pieno.getNumberOfPages(); ++i) {
					stamper.replacePage( vuoto, i, i );
				}

				// inserimento acrofield logo e disclaimer SPI in alto a destra della prima pagina se flag enableLogoDisclaimer è true
				if(enableLogoDisclaimer) {
                PushbuttonField logo = new PushbuttonField(stamper.getWriter(), new Rectangle(333.93f,802.75f,571.84f,831.38f), Constants.LOGO_FIELD);
                stamper.addAnnotation(logo.getField(), 1);

                PushbuttonField disclaimer = new PushbuttonField(stamper.getWriter(), new Rectangle(19.57f,2.13f,546.35f,15.31f), Constants.DISCLAIMER_FIELD);
                stamper.addAnnotation(disclaimer.getField(), 1);
				}
				
				stamper.close();

				return LocalTime.now()+": Documento "+  nameFile.substring(1,nameFile.length()) +" copiato, controllare il posizionamento degli Acrofields copiati";
			}
			else  return LocalTime.now()+": "+Constants.ERRORE_TIPO_FILE;

		} catch (Exception e) {
			e.printStackTrace();
			return LocalTime.now()+": "+Constants.ERRORE_FILE_APERTO;
		} 
	}
	
	
	
}
