package view;

import manipulate.Constants;

import java.io.File;

import controller.CopyAcrofieldController;
import controller.DecryptController;
import controller.NavigationController;
import javafx.application.Platform;
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

	@FXML private Text welcomeText;
	@FXML private BorderPane mainPane;
	@FXML private AnchorPane decryptAnchorPane;	
	@FXML private TextField lockedPath;
	@FXML private TextField unLockedPath;
	@FXML private TextArea logArea;
	
	@FXML AnchorPane copyAcrofieldAnchorPane;
	
	@FXML TextField mappedPath;
	@FXML TextField folderPath;
	@FXML TextField toMapPath;
	@FXML TextField destPath;
	
	@FXML Text responseSingleCopy;
	@FXML TextArea logAreaMultipleCopy;
	
	 public void initialize() {
		
	 }
	 

	public void chooseSourceFolder() {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		Stage stage = (Stage) mainPane.getScene().getWindow();
		File selectedDirectory = directoryChooser.showDialog(stage);
		lockedPath.setText(selectedDirectory.getAbsolutePath());

	}

	public void chooseDestFolder() {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		Stage stage = (Stage) mainPane.getScene().getWindow();
		File selectedDirectory = directoryChooser.showDialog(stage);
		unLockedPath.setText(selectedDirectory.getAbsolutePath());
	}
	
	public void startDecrypt() {
		String lockedPathString= lockedPath.getText()+"\\";
		String unLockedPathString = unLockedPath.getText()+"\\";
		logArea.appendText(DecryptController.getInstance().decrypt(lockedPathString, unLockedPathString));
	}
	
	public void chooseFolder() {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		Stage stage = (Stage) mainPane.getScene().getWindow();
		File selectedDirectory = directoryChooser.showDialog(stage);
		folderPath.setText(selectedDirectory.getAbsolutePath());
	}
	
	public void chooseMapped() {
		FileChooser fileChooser = new FileChooser();
		Stage stage = (Stage) mainPane.getScene().getWindow();
		File selectedFile = fileChooser.showOpenDialog(stage);
		mappedPath.setText(selectedFile.getAbsolutePath());
	}
	public void chooseToMap() {
		FileChooser fileChooser = new FileChooser();
		Stage stage = (Stage) mainPane.getScene().getWindow();
		File selectedFile = fileChooser.showOpenDialog(stage);
		toMapPath.setText(selectedFile.getAbsolutePath());
	}
	public void chooseDest() {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		Stage stage = (Stage) mainPane.getScene().getWindow();
		File selectedDirectory = directoryChooser.showDialog(stage);
		destPath.setText(selectedDirectory.getAbsolutePath());
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
