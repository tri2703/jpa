package ute.tri.dao;
import java.util.List;

import ute.tri.entity.Video;

public interface IVideoDao {
    void insert(Video video);
    void update(Video video);
    void delete(String videoId) throws Exception;
    Video findById(String videoId);
    List<Video> findAll();
}
