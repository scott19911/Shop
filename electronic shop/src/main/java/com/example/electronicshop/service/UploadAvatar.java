package com.example.electronicshop.service;

import com.example.electronicshop.users.SpecificUser;
import com.example.electronicshop.users.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import static com.example.electronicshop.constants.ServletsName.REGISTRATION_SERVLET;
import static com.example.electronicshop.servlets.DrawImage.IMAGE_STORAGE_PATH;
import static com.example.electronicshop.servlets.Registration.DB_TYPE;

public class UploadAvatar implements UploadService {
    public static final String SAVE_DIRECTORY = "uploadDir";
    public static final String SPECIFIC_USER = "specificUser";
    public UserService userService;

    @Override
    public void uploadAvatar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = null;
        try {
            session = request.getSession();
            SpecificUser user = (SpecificUser) session.getAttribute(SPECIFIC_USER);
            if (user == null){
                user = new SpecificUser();
            }
            String appPath =session.getServletContext().getInitParameter(IMAGE_STORAGE_PATH);
            appPath = appPath.replace('\\', '/');
            String fullSavePath;
            if (appPath.endsWith("/")) {
                fullSavePath = appPath + SAVE_DIRECTORY + "/" + System.currentTimeMillis() + "/";
            } else {
                fullSavePath = appPath + "/" + SAVE_DIRECTORY + "/" + System.currentTimeMillis() + "/";
            }
            File fileSaveDir = new File(fullSavePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs();
            }
            for (Part part : request.getParts()) {
                String fileName = extractFileName(part);
                if (fileName != null && fileName.length() > 0) {
                    String filePath = fullSavePath + fileName;
                    part.write(filePath);
                    String fileDir = filePath.substring(filePath.indexOf(SAVE_DIRECTORY));
                    if (user.getUserId() > 0) {
                    UserServiceFactory userServiceFactory = new UserServiceFactory();
                    userService = userServiceFactory.getService
                            (session.getServletContext().getInitParameter(DB_TYPE));
                        deletedOldAvatar(user.getUserId(), userService, appPath);
                        userService.setAvatar(user.getUserId(), fileDir);
                    }
                    user.setAvatarUrl(fileDir);
                    session.setAttribute(SPECIFIC_USER, user);
                }
            }
            response.sendRedirect(REGISTRATION_SERVLET);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher(REGISTRATION_SERVLET);
            dispatcher.forward(request, response);
        }
    }

    @Override
    public String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                clientFileName = clientFileName.replace("\\", "/");
                int i = clientFileName.lastIndexOf('/');
                return clientFileName.substring(i + 1);
            }
        }
        return null;
    }

    @Override
    public void deletedOldAvatar(int userId, UserService userService, String realPath) {
        User user = userService.getUser(userId);
        if (user.getAvatarUrl() != null) {
            try {
                Files.delete(Paths.get(realPath + user.getAvatarUrl()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
