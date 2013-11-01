package videos;

public class Playlist {

	String id;
	String title;
	String thumbnail;
	int videoCount;
	
	
	public Playlist(String playlistID, String playlistTitle,String thumbnailUrl, int nVideos) {
		this.id = playlistID;
		this.title = playlistTitle;
		this.thumbnail = thumbnailUrl;
		this.videoCount = nVideos;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getThumbnailURL() {
		return thumbnail;
	}
	public void setThumbnailURL(String thumbnailURL) {
		this.thumbnail = thumbnailURL;
	}
	public int getVideoCount() {
		return videoCount;
	}
	public void setVideoCount(int videoCount) {
		this.videoCount = videoCount;
	}
		
}