package flickr;

/**
 * Created by new on 1/19/15.
 */
public class FlickrPhotoUrl {

    private static final String FORMAT_FLICKR_PHOTO_URL = "https://farm%s.staticflickr.com/%s/%s_%s_%s.jpg";
    private static final String FORMAT_FLICKR_ORIGINAL_PHOTO_URL = "https://farm%s.staticflickr.com/%s/%s_%s_o.%s";
    /**
     * These could probably be in an enum that associates the letter with the value. You could then have one function
     * that takes an enum and then creates the correct URL.
     */
    private static final String SIZE_THUMBNAIL = "t";
    private static final String SIZE_LARGE = "b";


    private long farmId;
    private String serverId;
    private String id;
    private String secret;
    private String originalFormat;
    private String originalSecret;

    public FlickrPhotoUrl(pojo.Photo photo) {
        this.farmId = photo.getFarm();
        this.serverId = photo.getServer();
        this.id = photo.getId();
        this.secret = photo.getSecret();
        this.originalSecret = photo.getOriginalSecret();
        this.originalFormat = photo.getOriginalFormat();
    }

    public String getThumbnailUrl() {
        return String.format(FORMAT_FLICKR_PHOTO_URL, String.valueOf(farmId), serverId, id, secret, SIZE_THUMBNAIL);
    }

    public String getLargeUrl() {
        return String.format(FORMAT_FLICKR_PHOTO_URL, String.valueOf(farmId), serverId, id, secret, SIZE_LARGE);
    }

    public String getOriginalUrl() {
        return String.format(FORMAT_FLICKR_ORIGINAL_PHOTO_URL, String.valueOf(farmId), serverId, id, originalSecret,
                originalFormat);
    }
}
