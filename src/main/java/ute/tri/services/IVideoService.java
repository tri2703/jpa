package ute.tri.services;

import java.util.List;

import ute.tri.entity.Video;

public interface IVideoService {
    List<Video> findAll();
    Video findById(String videoId);
    void delete(String videoId) throws Exception;
    void update(Video video);
    void insert(Video video);
}