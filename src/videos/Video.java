package videos;

public class Video {
	
	String videoId;
	String title;
	String author;
	String duration;
	String nViews;
	String thumbnail;
	
	public Video(String videoId,String title, String author, String duration, String nViews, String thumbnail) {
			this.videoId = videoId;
			this.title = title;
			this.author = author;
			this.duration = duration;
			this.nViews = nViews;
			this.thumbnail = thumbnail;
	}
	
	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getnViews() {
		return nViews;
	}
	public void setnViews(String nViews) {
		this.nViews = nViews;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	
}
