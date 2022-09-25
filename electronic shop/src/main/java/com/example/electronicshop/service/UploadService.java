package com.example.electronicshop.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

/**
 *
 * Responsible for storing the user's avatar
 */
public interface UploadService {
    /**
     * Save avatar
     * @param request - with image file
     * @param response - sending a response with save image path
     * @throws ServletException - when can't forward
     * @throws IOException - when can't save image
     */
    void uploadAvatar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    /**
     * Extract file name
     * @param part - represents a part or form item that was received within a multipart/form-data POST request.
     * @return - name of file
     */
    String extractFileName(Part part);

    /**
     *
     * Allows you to delete previously saved avatars
     * @param userId - user id by which you need to remove the avatar
     * @param userService - service for get avatar links
     * @param realPath - to storage file
     */
    void deletedOldAvatar(int userId, UserService userService, String realPath);
}
