/* Zarir Hamza and Fares Easa
        Software Methodology
*/
package files;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.TreeMap;

public class SongLibrary {
    private final String FILE = "src/files/songData.txt";

    private ObservableList<Song> songList;
    private TreeMap<String, Song> songMap;

    public SongLibrary(){
        songList = FXCollections.observableArrayList();
        songMap = new TreeMap<String, Song>();
        try {
            readFile();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public ObservableList<Song> getItems() {
        return songList;
    }

    public int getNumberOfSongs() {
        return songList.size();
    }

    public int getIndex(Song song) {
        return songList.indexOf(song);
    }

    public int addSong(Song song){
        Song check = songMap.get(song.getKey());
        if(check == null){
            songMap.put(song.getKey(),song);
            return getInsertedIndex(song); //return number of location
        }
        else{
            return -1;
        }
    }

    private int getInsertedIndex(Song song){
        if(songList.isEmpty()){
            songList.add(song);
            return 0;
        }
        else{
            for (int i = 0; i < songList.size(); i++) {
                if (song.compareTo(songList.get(i)) < 0) {
                    songList.add(i, song);
                    return i;
                }
            }
        }
        songList.add(song);
        return songList.size() - 1;
    }

    public int edit(int index, Song song){
        Song oldSong = songMap.get(songList.get(index).getKey());
        if(oldSong == null){
            return  -1;
        }
        else{
            delete(getIndex(oldSong));
            Song newSong = new Song(song);
            addSong(newSong);
        }
        return 0;
    }

    public boolean delete(int index) {
        String key = songList.get(index).getKey();
        Song temp = songMap.get(key);
        if (temp != null) {
            songMap.remove(key);
            songList.remove(index);
            return true;
        } else {
            return false;
        }
    }

    public void writeFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE));

        for (Song song : songMap.values()) {
            writer.write(song.getName() + "\t");
            writer.write(song.getArtist() + "\t");
            if (song.getAlbum().isEmpty())
                writer.write("EMPTYSTRINGNOTHINGHERE\t");
            else
                writer.write(song.getAlbum() + "\t");
            if (song.getYear().isEmpty())
                writer.write("EMPTYSTRINGNOTHINGHERE\t");
            else
                writer.write(song.getYear());
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }

    public void readFile () throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE));
        String line = reader.readLine();
        String[] songBreakdown;
        while(line != null){
            songBreakdown = line.split("\t");
            Song temp = new Song(songBreakdown[0], songBreakdown[1], songBreakdown[2], songBreakdown[3]);
            if(songBreakdown[2].equals("EMPTYSTRINGNOTHINGHERE"))
                temp.setAlbum("");

            if(songBreakdown[3].equals("EMPTYSTRINGNOTHINGHERE"))
                temp.setYear("");

            addSong(temp);
            line = reader.readLine();
        }
        reader.close();
    }
}
