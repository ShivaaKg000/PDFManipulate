package manipulate;



public final class Constants  {

	/*folder documenti già digitalizzati  */			
	public static final String MAPPED="MAPPED\\";
	
	/*folder documenti da digitalizzare */		
	public static final String TO_MAP="TO_MAP\\";
	
	
	
    /*View fxml resource path from navigation controller*/

	public static final String  GUIDA_VIEW = "/fxml/Guida.fxml"; 
    public static final String  MAIN_VIEW= "/fxml/MainView.fxml";
	public static final String  FILE_ERROR_LOG_NAME = "errorLog.txt";
	
	public static final String  ERRORE_TEMPLATE_NON_TROVATO = "Template non trovato!";
	public static final String  ERRORE_ROOT_ORIGINE_NON_TROVATA = "Cartella d'origine non trovata, selezionare una folder corretta!\n";
	public static final String  ERRORE_ROOT_ORIGINE_VUOTA = "Cartella d'origine vuota";
	public static final String  ERRORE_ROOT_DESTINAZIONE_NON_TROVATA = "Cartella di destinazione non trovata, selezionare una folder corretta!\n";
	public static final String  ERRORE_FILE_APERTO= " ERRORE - Assicurarsi che i file selezionati non sono aperti da altri programmi!";
	public static final String  ERRORE_TIPO_FILE= " ERRORE - I file selezionati non sono dei documenti PDF";
	public static final String  ERRORE_SAME_FOLDER= "La folder di origine e quella di destinazione non possono coincidere!";
	public static final String  ERRORE_DECRYPT=" KO! Per ulteriori dettagli consultare il file errorLog.txt presente nella cartella di unlock \n"; 
	public static final String  END_DECRYPT= "\n\nDecrypting ended!";
	
	public static final String  LOGO_FIELD="Logo";
	public static final String  DISCLAIMER_FIELD="DisclaimerISPB";

	public static final float LogoLLX=333.93f;
	public static final float LogoLLY=802.75f;
	public static final float LogoURX=571.84f;
	public static final float LogoURY=831.38f;
	
	public static final float DisclaimerLLX=19.57f;
	public static final float DisclaimerLLY=2.13f;
	public static final float DisclaimerURX=546.35f;
	public static final float DisclaimerURY=15.31f;
	
	
	
    private Constants(){
        //this prevents even the native class from
        //calling this ctor as well :
        throw new AssertionError();
    }
}
