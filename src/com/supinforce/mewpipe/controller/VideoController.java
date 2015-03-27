package com.supinforce.mewpipe.controller;
import com.supinforce.mewpipe.entity.Video;
import com.supinforce.mewpipe.service.VideoService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;


@ManagedBean
@RequestScoped
public class VideoController {

    @EJB
    private VideoService videoService;

    public List<Video> getUserVideos(int id)
    {
        return videoService.getUserVideos(id);
    }
}