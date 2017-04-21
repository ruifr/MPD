package api;

import api.dto.ArtistDto;
import api.dto.TrackDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import util.HttpRequest;
import util.IRequest;

public class LastfmApi {

    private static final String LASTFM_HOST = "http://ws.audioscrobbler.com/2.0/";
    private static final String LASTFM_ARTIST_ARGS = "?method=artist.getinfo&format=json&mbid=%s&api_key=%s";
    private static final String LASTFM_TRACK_ARGS = "?method=track.getinfo&format=json&track=%s&artist=%s&api_key=%s";
    private static final String LASTFM_key = "1e36f8968af975f197cf0502a2cad082";

    private final IRequest req;
    private Gson gson;

    public LastfmApi(IRequest req) {
        this.req = req;
        gson = new GsonBuilder().create();
    }

    public LastfmApi() {
        this(new HttpRequest());
    }

    public ArtistDto getArtistInfo(String mbid) {
        String path = LASTFM_HOST + LASTFM_ARTIST_ARGS;
        String url = String.format(path, mbid, LASTFM_key);
        Iterable<String> content = () -> req.getContent(url).iterator();
        return gson.fromJson(content.iterator().next(), ArtistDto.class);
    }

    public TrackDto getTrackInfo(String artist, String track) {
        String path = LASTFM_HOST + LASTFM_TRACK_ARGS;
        String url = String.format(path, track, artist, LASTFM_key);
        Iterable<String> content = () -> req.getContent(url).iterator();
        return gson.fromJson(content.iterator().next(), TrackDto.class);
    }
}