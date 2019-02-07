package google.com.ortona.hashcode.qualification_2017.model;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class Cache {
	int id;
	int size;
	Set<Video> videos = new HashSet<>();
	private int availableCapacity = 0;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAvailableCapacity() {
		return this.availableCapacity;
	}

	public void setSize(int size) {
		this.size = size;
		this.availableCapacity = size;
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
		availableCapacity -= v.getSize();
	}

	public boolean checkSoundess() {
		final AtomicLong allSizes = new AtomicLong(0);
		this.videos.forEach(v -> {
			if (v.getSize() <= 0) {
				throw new IllegalArgumentException("Video " + v + " has size: " + v.getSize());
			}
			allSizes.addAndGet(v.getSize());
		});
		return allSizes.get() <= this.size;
	}

	@Override
	public String toString() {
		String videos = "";
		for (final Video video : this.getVideos()) {
			videos += " " + video.getId();
		}
		return this.id + videos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Cache other = (Cache) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

}
