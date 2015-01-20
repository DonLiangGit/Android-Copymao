package pojo;

/**
 * Created by new on 1/19/15.
 */
public class PhotoResponse extends FlickrRestResponse {

    private Photos photos;

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return "PhotoResponse{" +
                "photos=" + photos +
                "stats=" + stat +
                '}';
    }
}