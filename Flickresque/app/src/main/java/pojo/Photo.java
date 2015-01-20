package pojo;

import flickr.FlickrPhotoUrl;

/**
 * Created by new on 1/19/15.
 */
public class Photo {
    private String id;
    private String owner;
    private String secret;
    private String server;
    private long farm;
    private String title;
    private long ispublic;
    private long isfriend;
    private long isfamily;
    private String originalsecret;
    private String originalformat;
    private String username;


    private FlickrPhotoUrl mFlickrPhotoUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public long getFarm() {
        return farm;
    }

    public void setFarm(long farm) {
        this.farm = farm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getIsPublic() {
        return ispublic;
    }

    public void setIspublic(long isPublic) {
        this.ispublic = isPublic;
    }

    public long getIsfriend() {
        return isfriend;
    }

    public void setIsFriend(long isFriend) {
        this.isfriend = isFriend;
    }

    public long getIsFamily() {
        return isfamily;
    }

    public void setIsFamily(long isFamily) {
        this.isfamily = isFamily;
    }

    public String getOriginalSecret() {
        return originalsecret;
    }

    public void setOriginalSecret(String originalSecret) {
        this.originalsecret = originalSecret;
    }

    public String getOriginalFormat() {
        return originalformat;
    }

    public void setOriginalFormat(String originalFormat) {
        this.originalformat = originalFormat;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public FlickrPhotoUrl getFlickrPhotoUrl() {
        if(mFlickrPhotoUrl == null){
            mFlickrPhotoUrl = new FlickrPhotoUrl(this);
        }
        return mFlickrPhotoUrl;
    }

    @Override
    public String toString() {
        return null;
//        return "Photo{" +
//                "id='" + id + '\'' +
//                ", owner='" + owner + '\'' +
//                ", secret='" + secret + '\'' +
//                ", server='" + server + '\'' +
//                ", farm=" + farm +
//                ", title='" + title + '\'' +
//                ", ispublic=" + ispublic +
//                ", isfriend=" + isfriend +
//                ", isfamily=" + isfamily +
//                ", originalsecret='" + originalsecret + '\'' +
//                ", originalformat='" + originalformat + '\'' +
//                ", views='" + views + '\'' +
//                '}';
    }
}
