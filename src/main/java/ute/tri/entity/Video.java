package ute.tri.entity;

import java.io.Serializable;

import java.util.List;

import jakarta.persistence.*;

@Entity

@Table(name = "Videos")

@NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v")

public class Video implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id

	@Column(name = "VideoId")

	private String videoId;

	@Column(name = "Active")

	private int active;

	@Column(name = "Description", columnDefinition = "NVARCHAR(MAX)")

	private String description;

	@Column(name = "Poster")

	private String poster;

	@Column(name = "Title",columnDefinition = "NVARCHAR(MAX)")

	private String title;

	@Column(name = "Views")

	private int views;

	// bi-directional many-to-one association to Favorite

//	@OneToMany(mappedBy = "video")

//	private List<Favorite> favorites;
//
//	// bi-directional many-to-one association to Share
//
//	@OneToMany(mappedBy = "video")
//
//	private List<Share> shares;

	// bi-directional many-to-one association to Category

	@ManyToOne

	@JoinColumn(name = "CategoryId")

	private Category category;

	public Video() {
	}

	public String getVideoId() {
		return this.videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public int getActive() {
		return this.active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getViews() {
		return this.views;
	}

	public void setViews(int views) {
		this.views = views;
	}

//	public List<Favorite> getFavorites() {
//		return this.favorites;
//	}
//
//	public void setFavorites(List<Favorite> favorites) {
//		this.favorites = favorites;
//	}
//
//	public Favorite addFavorite(Favorite favorite) {
//		getFavorites().add(favorite);
//		favorite.setVideo(this);
//		return favorite;
//	}
//
//	public Favorite removeFavorite(Favorite favorite) {
//		getFavorites().remove(favorite);
//		favorite.setVideo(null);
//		return favorite;
//	}
//
//	public List<Share> getShares() {
//		return this.shares;
//	}
//
//	public void setShares(List<Share> shares) {
//		this.shares = shares;
//	}
//
//	public Share addShare(Share share) {
//		getShares().add(share);
//		share.setVideo(this);
//		return share;
//	}
//
//	public Share removeShare(Share share) {
//		getShares().remove(share);
//		share.setVideo(null);
//		return share;
//	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}