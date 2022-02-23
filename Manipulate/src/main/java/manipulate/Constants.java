package manipulate;



public final class Constants  {

	/*folder documenti già digitalizzati  */			
	public static final String MAPPED="MAPPED\\";
	
	/*folder documenti da digitalizzare */		
	public static final String TO_MAP="TO_MAP\\";
	
	
	
    /*View fxml resource path from navigation controller*/

    public static final String  MAIN_VIEW= "/fxml/MainView.fxml";
    public static final String  DECRYPT_VIEW= "/fxml/DecryptView.fxml";
    public static final String  COPY_ACROFIELD_VIEW = "/fxml/CopyAcrofieldView.fxml";
	public static final String  FILE_ERROR_LOG_NAME = "errorLog.txt";
	public static final String  ERRORE_TEMPLATE_NON_TROVATO = "Template non trovato!";
	public static final String  ERRORE_ROOT_NON_TROVATA = "Cartella d'origine non trovata, selezionare una folder corretta"; 


    private Constants(){
        //this prevents even the native class from
        //calling this ctor as well :
        throw new AssertionError();
    }
}
