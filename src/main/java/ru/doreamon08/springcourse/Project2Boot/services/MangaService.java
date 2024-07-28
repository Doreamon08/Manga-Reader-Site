package ru.doreamon08.springcourse.Project2Boot.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.doreamon08.springcourse.Project2Boot.models.Book;
import ru.doreamon08.springcourse.Project2Boot.models.Manga;
import ru.doreamon08.springcourse.Project2Boot.repositories.MangaRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
@Transactional
public class MangaService {

    private static final String UPLOADED_FOLDER = "src/main/resources/static/images/";
    private final MangaRepository mangaRepository;

    public MangaService(MangaRepository mangaRepository) {
        this.mangaRepository = mangaRepository;
    }

    public Manga findOne(int id) {
        return mangaRepository.findById(id).orElse(null);
    }

    public void save(MultipartFile file, String title,
                     String author, String description,
                     Model model) {

        Manga manga = new Manga();

        String linkToPreview = handleFileUpload(file, model);

        manga.setTitle(title);
        manga.setAuthor(author);
        manga.setDescription(description);
        manga.setLinkToPreviewImage(linkToPreview);

        mangaRepository.save(manga);

    }

    public String handleFileUpload(MultipartFile file, Model model) {

        try {
            Path uploadPath = Paths.get(UPLOADED_FOLDER);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            model.addAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");

            return ("/images/" + file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Failed to upload '" + file.getOriginalFilename() + "'");
        }

        return "/images/defaultPreview";
    }

}
