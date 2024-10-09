package ute.tri.services.impl;

import java.util.List;

import ute.tri.dao.IVideoDao;
import ute.tri.dao.impl.VideoDao;
import ute.tri.entity.Video;
import ute.tri.services.IVideoService;

public class VideoService implements IVideoService {
    IVideoDao videoDao = new VideoDao();

    @Override
    public List<Video> findAll() {
        return videoDao.findAll();
    }

    @Override
    public Video findById(String videoId) {
        return videoDao.findById(videoId);
    }

    @Override
    public void delete(String videoId) throws Exception {
        videoDao.delete(videoId);
    }

    @Override
    public void update(Video video) {
        videoDao.update(video);
    }

    @Override
    public void insert(Video video) {
        videoDao.insert(video);
    }
}