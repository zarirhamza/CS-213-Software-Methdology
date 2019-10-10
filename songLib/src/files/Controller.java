/* Zarir Hamza and Fares Easa
   Software Methodology
 */

package files;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class Controller implements ChangeListener<Song> {
    private SongLibrary sL = new SongLibrary();
    private int indexNum;

    @FXML AnchorPane maxPane;

    @FXML ListView<Song> list; //CHANGE TO SONG

    @FXML Button deleteButton;
    @FXML Button updateButton;
    @FXML Button addButton;

    @FXML TextField nameField;
    @FXML TextField albumField;
    @FXML TextField artistField;
    @FXML TextField yearField;

    @FXML
    public void initialize(){
        //set items of list view from model
        list.setItems(sL.getItems());

       // list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE); //idk if this is needed
        list.getSelectionModel().selectedItemProperty().addListener(this);

        if (sL.getNumberOfSongs() > 0) {
			list.getSelectionModel().select(0);
            list.getFocusModel().focus(0);
		}
    }

    public void clickAdd(){
        if(addButton.getText().equals("Add")){
            updateButton.setText("Clear");
            deleteButton.setText("Cancel");
            addButton.setText("Confirm");

            nameField.setText("");
            albumField.setText("");
            artistField.setText("");
            yearField.setText("");

            editReady();
            indexNum = list.getSelectionModel().getSelectedIndex();

        }
        else if(addButton.getText().equals("Confirm")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to perform this operation?", ButtonType.YES,
                    ButtonType.NO);
            alert.showAndWait();
            Song temp = new Song(nameField.getText(), artistField.getText(), albumField.getText(), yearField.getText());
            if (alert.getResult() == ButtonType.YES) {
                //MAKE SURE NO REPEATS ARE IN THE THING and add it to the list
                if(nameField.getText().isEmpty() || artistField.getText().isEmpty()){
                    Alert error = new Alert(Alert.AlertType.ERROR, "Artist and Name info must be entered at least", ButtonType.OK);
                    error.showAndWait();
                    return;
                }
                else if (sL.addSong(temp) == -1){
                    Alert error = new Alert(Alert.AlertType.ERROR, "Song already exists!", ButtonType.OK);
                    error.showAndWait();
                    return;
                } else {
                    indexNum = sL.getIndex(temp);
                    returnToNormal();
                }
            }
        }
        else if(addButton.getText().equals("Cancel")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel this operation?", ButtonType.YES,
                    ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                returnToNormal();
            }
        }
    }

    public void clickUpdate(){
        if(updateButton.getText().equals("Update")){
            indexNum = list.getSelectionModel().getSelectedIndex();
            if(indexNum < 0){
                Alert error = new Alert(Alert.AlertType.ERROR, "No songs chosen to be updated", ButtonType.OK);
                error.showAndWait();
                return;
            }
            updateButton.setText("Confirm");
            deleteButton.setText("Clear");
            addButton.setText("Cancel");

            Song temp = list.getSelectionModel().getSelectedItem();
            nameField.setText(temp.getName());
            artistField.setText(temp.getArtist());
            albumField.setText(temp.getAlbum());
            yearField.setText(temp.getYear());

            editReady();


        }
        else if(updateButton.getText().equals("Confirm")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to perform this operation?", ButtonType.YES,
                    ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                //MAKE SURE NO REPEATS ARE IN THE THING and update it to the list
                if(nameField.getText().isEmpty() || artistField.getText().isEmpty()){
                    Alert error = new Alert(Alert.AlertType.ERROR, "Artist and Name info must be entered at least", ButtonType.OK);
                    error.showAndWait();
                    return;
                }
                else {
                    Song temp = new Song(nameField.getText(), artistField.getText(), albumField.getText(), yearField.getText());
                    System.out.println(temp.getKey() + sL.getIndex(temp));
                    if(sL.getIndex(temp) > 0){
                        Alert error = new Alert(Alert.AlertType.ERROR, "Song already exists!", ButtonType.OK);
                        error.showAndWait();
                        return;
                    }
                    else {

                        sL.edit(indexNum, temp);
                        indexNum = sL.getIndex(temp);
                        returnToNormal();
                    }
                }
            }
        }
        else if (updateButton.getText().equals("Clear")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to clear all inputted data?", ButtonType.YES,
                    ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                nameField.setText("");
                albumField.setText("");
                artistField.setText("");
                yearField.setText("");

                editReady();
            }
        }
    }

    public void clickDelete(){
        if(deleteButton.getText().equals("Delete")){
            indexNum = list.getSelectionModel().getSelectedIndex();
            if(indexNum < 0){
                Alert error = new Alert(Alert.AlertType.ERROR, "No songs chosen to be deleted", ButtonType.OK);
                error.showAndWait();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete this song?", ButtonType.YES,
                        ButtonType.NO);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                        sL.delete(indexNum);
                        if (indexNum <= sL.getNumberOfSongs() - 1) {
                            returnToNormal();
                        }
                }
            }
        }
        else if(deleteButton.getText().equals("Cancel")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel this operation?", ButtonType.YES,
                    ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                returnToNormal();
            }
        }

        else if(deleteButton.getText().equals("Clear")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to clear all inputted data?", ButtonType.YES,
                    ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                nameField.setText("");
                albumField.setText("");
                artistField.setText("");
                yearField.setText("");

                editReady();
            }
        }
    }

    public void returnToNormal(){

        try {
            sL.writeFile();
        }catch(IOException e){
            e.printStackTrace();
        }

        list.setItems(null);
        list.setItems(sL.getItems());

        deleteButton.setText("Delete");
        updateButton.setText("Update");
        addButton.setText("Add");

        nameField.setEditable(false);
        albumField.setEditable(false);
        artistField.setEditable(false);
        yearField.setEditable(false);

        list.requestFocus();
        list.getSelectionModel().select(indexNum);
        list.getFocusModel().focus(indexNum);

        System.out.println("this is indexNUm: " + indexNum);
        System.out.println("is this focused: " + list.getFocusModel().isFocused(indexNum) + "\n");
    }

    public void editReady(){
        /*nameField.setText("");
        albumField.setText("");
        artistField.setText("");
        yearField.setText("");*/

        //give instructions maybe on first instance?

        nameField.setEditable(true);
        albumField.setEditable(true);
        artistField.setEditable(true);
        yearField.setEditable(true);

        nameField.requestFocus();
        nameField.positionCaret(0);


    }

    public void changed(ObservableValue<? extends Song> observable, Song oldSong, Song newSong){ //extends SOng
        if(newSong == null){ //change to equals null
            nameField.setText("");
            albumField.setText("");
            artistField.setText("");
            yearField.setText("");
        }
        else{ //change to song values
            nameField.setText(newSong.getName());
            albumField.setText(newSong.getAlbum());
            artistField.setText(newSong.getArtist());
            yearField.setText(newSong.getYear());
        }

    }
}
