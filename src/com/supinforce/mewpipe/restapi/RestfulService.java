package com.supinforce.mewpipe.restapi;

import com.supinforce.mewpipe.entity.User;
import com.supinforce.mewpipe.entity.Video;
import com.supinforce.mewpipe.service.UserService;
import com.supinforce.mewpipe.service.VideoService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.util.List;

/**
 * Created by Pierre on 26/03/2015.
 */

@Path("/api")
@Produces("application/json")
@Consumes("application/json")
public class RestfulService {

    @EJB
    private UserService userService;

    @EJB
    private VideoService videoService;

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void upload(@Context HttpServletRequest request, @QueryParam("path") String fileName, @QueryParam("id") int userId) // username/video.mp4; User.id
    {
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);

                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        String name = new File(item.getName()).getName();
                        item.write(new File(VideoService.VIDEOPATH + fileName));
                    }

                }
                //File uploaded successfully
                User user = userService.getUserById(userId);
                Video vid = new Video();
                vid.setVisibility("public");
                vid.setShares(0);
                vid.setViews(0);
                vid.setUser(user);
                vid.setLink(fileName);
                videoService.addVideo(vid);
                request.setAttribute("message", "File Uploaded Successfully");
            } catch (Exception ex) {
                request.setAttribute("message", "File Upload Failed due to " + ex);
            }

        }else{
            request.setAttribute("message",
                    "Sorry this LoginServlet only handles file upload request");
        }
    }

    @GET
    @Path("/test")
    public void aVoid()
    {
        int i = 0;
        i++;
    }
}