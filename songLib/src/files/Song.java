/* Zarir Hamza and Fares Easa
   Software Methodology
 */
package files;

public class Song {
    private String name;
    private String artist;
    private String album;
    private String year;

    Song(String name, String artist, String album, String year){
        this.setName(name);
        this.setArtist(artist);
        this.setAlbum(album);
        this.setYear(year);
    }
    Song(Song song){
        this.setName(song.getName());
        this.setArtist(song.getArtist());
        this.setAlbum(song.getAlbum());
        this.setYear(song.getYear());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getKey(){
        return (this.getName() + this.getArtist()).toLowerCase();
    }

    public boolean equals(Object O){
        if(O == null || !(O instanceof  Song)){
            return false;
        }
        Song song = (Song) O;
        return this.getKey().compareTo(song.getKey()) == 0;
    }

    public int compareTo(Song song) {
        return this.getKey().compareTo(song.getKey());
    }

    public String toString() {
        return "\"" + this.getName() + "\" - " + this.getArtist();
    }
}
