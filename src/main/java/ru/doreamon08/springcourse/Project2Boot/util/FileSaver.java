package ru.doreamon08.springcourse.Project2Boot.util;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSaver {

    public static String handleFileUpload(MultipartFile file, Model model, String uploadedFolder, String folder) {

        try {
            Path uploadPath = Paths.get(uploadedFolder);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadedFolder + file.getOriginalFilename());
            Files.write(path, bytes);

            model.addAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");

            return (folder + file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Failed to upload '" + file.getOriginalFilename() + "'");
        }

        return "/images/defaultPreview";
    }

}
