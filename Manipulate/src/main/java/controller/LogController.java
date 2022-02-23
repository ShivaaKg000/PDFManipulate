package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogController {

	private static File log;
	private static LogController logController;


	public static LogController getInstance(String pathLogFile) {
		if(logController==null)
			logController= new LogController(pathLogFile);
		return logController;
	}



	private LogController(String logFile) {
		log= new File(logFile);
	}

	public void printError(String error){

		try {
			log.createNewFile();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			FileWriter myWriter = new FileWriter(log.getAbsolutePath(),true);
			myWriter.append(dtf.format(now)+" : "+error+"\n");
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public  String printError(String fileNameError,String error, String basePath){
		String log = new String();
		File errorFile = new File(basePath+"\\"+"log.txt");
		try {
			errorFile.createNewFile();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			FileWriter myWriter = new FileWriter(basePath+"\\"+"log.txt",true);
			log=dtf.format(now)+" : "+fileNameError+": "+error+"\n";
			myWriter.append(log);
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return log;
	}
	public String printOK(String fileName, String basePath){
		String log = new String();
		File file = new File(basePath+"\\"+"log.txt");
		try {
			file.createNewFile();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  

			FileWriter myWriter = new FileWriter(basePath+"\\"+"log.txt",true);
			log=dtf.format(now)+" : "+fileName+" Copiato \n";
			myWriter.append(log);
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return log;
	}


}
