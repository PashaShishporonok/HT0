public class Mp3 {

    private String artistName;
    private String albumName;
    private String trackName;
    private int trackLength;
    private String trackPath;
    private String sum;

    public Mp3(String artistName, String albumName, String trackName, int trackLength, String trackPath, String sum) {
        this.artistName = artistName;
        this.albumName = albumName;
        this.trackName = trackName;
        this.trackLength = trackLength;
        this.trackPath = trackPath;
        this.sum = sum;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getTrackName() {
        return trackName;
    }

    public int getTrackLength() {
        return trackLength;
    }

    public String getTrackPath() {
        return trackPath;
    }

    public String getSum() {
        return sum;
    }
}
