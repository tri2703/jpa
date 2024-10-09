package ute.tri.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import ute.tri.entity.Category;
import ute.tri.entity.Video;
import ute.tri.services.impl.CategoryService;
import ute.tri.services.impl.VideoService;
import ute.tri.utils.Constant;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/videos", "/admin/video/edit", "/admin/video/update", "/admin/video/insert",
		"/admin/video/add", "/admin/video/delete" })
public class VideoController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	VideoService videoService = new VideoService();
	CategoryService categoryService = new CategoryService(); // Initialize your category service

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if (url.contains("videos")) {
			List<Video> list = videoService.findAll();
			req.setAttribute("listVideo", list);
			req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
		} else if (url.contains("/admin/video/edit")) {
			List<Category> categories = categoryService.findAll(); // Fetch all categories
			req.setAttribute("listCategories", categories);
			String id = req.getParameter("id");
			Video video = videoService.findById(id);
			req.setAttribute("video", video);
			req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
		} else if (url.contains("/admin/video/add")) {
			// Fetch categories and set as request attribute
			List<Category> categories = categoryService.findAll(); // Fetch all categories
			req.setAttribute("listCategories", categories); // Set categories for JSP
			req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);
		} else if (url.contains("/admin/video/delete")) {
			String id = req.getParameter("id");
			try {
				videoService.delete(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    String url = req.getRequestURI();
	    if (url.contains("/admin/video/update")) {
	        String videoId = req.getParameter("videoId");
	        String title = req.getParameter("title");
	        String description = req.getParameter("description");
	        int active = Integer.parseInt(req.getParameter("active"));
	        int views = Integer.parseInt(req.getParameter("views"));
	        int categoryId = Integer.parseInt(req.getParameter("categoryId"));// Get views from the request
	         // Get categoryId from the request
	        
	        Video video = videoService.findById(videoId);
	        video.setTitle(title);
	        video.setDescription(description);
	        video.setActive(active);
	        video.setViews(views); // Set views
	        video.setCategory(categoryService.findById(categoryId)); // Set category based on categoryId

	        // Handle video poster upload
	        String fname = handleFileUpload(req, video.getPoster());
	        video.setPoster(fname);

	        videoService.update(video);
	        resp.sendRedirect(req.getContextPath() + "/admin/videos");
	    } else if (url.contains("/admin/video/insert")) {
	        // Get videoId from user input
	        String videoId = req.getParameter("videoId"); // Allow manual entry of video ID
	        String title = req.getParameter("title");
	        String description = req.getParameter("description");
	        int active = Integer.parseInt(req.getParameter("active"));
	        int views = Integer.parseInt(req.getParameter("views")); // Get views from the request
	        int categoryId = Integer.parseInt(req.getParameter("categoryId")); // Get categoryId from the request

	        // Ensure the videoId is not null or empty
	        if (videoId == null || videoId.trim().isEmpty()) {
	            req.setAttribute("errorMessage", "Video ID cannot be empty.");
	            req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);
	            return;
	        }

	        Video video = new Video();
	        video.setVideoId(videoId); // Assign the manually entered videoId
	        video.setTitle(title);
	        video.setDescription(description);
	        video.setActive(active);
	        video.setViews(views); // Set views
	        video.setCategory(categoryService.findById(categoryId)); // Set category based on categoryId

	        // Handle poster upload
	        String fname = handleFileUpload(req, "default.jpg");
	        video.setPoster(fname);

	        // Insert the video into the database
	        videoService.insert(video);
	        resp.sendRedirect(req.getContextPath() + "/admin/videos");
	    }
	}

	private String handleFileUpload(HttpServletRequest req, String defaultFile) throws IOException, ServletException {
		String fname = defaultFile;
		String uploadPath = Constant.UPLOAD_DIRECTORY;
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		Part part = req.getPart("poster");
		if (part != null && part.getSize() > 0) {
			String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
			int index = filename.lastIndexOf(".");
			String ext = filename.substring(index + 1);
			fname = System.currentTimeMillis() + "." + ext;
			part.write(uploadPath + "/" + fname);
		}
		return fname;
	}
}