package view;

import java.io.File;

import controller.CopyAcrofieldController;
import controller.DecryptController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainView  {

	@FXML private BorderPane mainPane;
	
	@FXML private AnchorPane copyAcrofieldAnchorPane;
	@FXML private AnchorPane decryptAnchorPane;	
	
	@FXML private Text responseSingleCopy;
	@FXML private Text welcomeText;
	
	@FXML private TextArea logAreaMultipleCopy;
	@FXML private TextArea logArea;
	
	@FXML private TextField lockedPath;
	@FXML private TextField unLockedPath;
	@FXML private TextField mappedPath;
	@FXML private TextField folderPath;
	@FXML private TextField toMapPath;
	@FXML private TextField destPath;

	public void initialize() {
		
	}

	public void chooseSourceFolder() {
		lockedPath.setText( chooseDirectory());

	}

	public void chooseDestFolder() {
		unLockedPath.setText( chooseDirectory());
	}

	public void chooseFolder() {
		folderPath.setText( chooseDirectory());
	}


	public void chooseDest() {
		destPath.setText(chooseDirectory());
	}

	public void chooseToMap() { 
		mappedPath.setText(chooseFile()); 
	}


	public void chooseMapped() {
		mappedPath.setText(chooseFile());
	}

	public String chooseDirectory() {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		Stage stage = (Stage) mainPane.getScene().getWindow();
		File selectedDirectory = directoryChooser.showDialog(stage);
		return selectedDirectory.getAbsolutePath();
	}

	public String chooseFile() {
		FileChooser fileChooser = new FileChooser();
		Stage stage = (Stage) mainPane.getScene().getWindow();
		File selectedFile = fileChooser.showOpenDialog(stage);
		return selectedFile.getAbsolutePath();
	}

	public void startDecrypt() {
		String lockedPathString= lockedPath.getText()+"\\";
		String unLockedPathString = unLockedPath.getText()+"\\";
		logArea.appendText(DecryptController.getInstance().decrypt(lockedPathString, unLockedPathString));
	}
	public void startMultipleCopy() {
		logAreaMultipleCopy.setText(
				CopyAcrofieldController.getInstance().MapMultipleDoc(folderPath.getText())
				);
	}

	public void startSingleCopy() {
		responseSingleCopy.setText(
				CopyAcrofieldController.getInstance().MapSingleDoc(toMapPath.getText(), mappedPath.getText(), destPath.getText())
				);
	}

}
