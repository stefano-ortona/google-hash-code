package google.com.ortona.hashcode.qualification_2017.model;

import java.util.Set;

public class Cache {
	int id;
	int size;
	Set<Video> videos;
	private final int availableCapacity = 0;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Set<Video> getVideos() {
		return videos;
	}

	public void setVideos(Set<Video> videos) {
		this.videos = videos;
	}

	public void addVideo(Video v) {
		if (videos.contains(v) || (availableCapacity < v.getSize())) {
			throw new RuntimeException("Video is too big or already present!");
		}
		videos.add(v);
	}

}
