package ru.doreamon08.springcourse.Project2Boot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.doreamon08.springcourse.Project2Boot.models.Manga;
import ru.doreamon08.springcourse.Project2Boot.services.MangaService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
@RequestMapping("/manga")
public class MangaController {
    private final MangaService mangaService;

    public MangaController(MangaService mangaService) {
        this.mangaService = mangaService;
    }

    @GetMapping("{manga_name}")
    public String show(
            @PathVariable("manga_name") int manga_id,
            @ModelAttribute("manga") Manga manga,//todo Что это?
            Model model) {
        model.addAttribute("manga", mangaService.findOne(manga_id));
        return "manga/show"; //todo Что это возвращается?
    }

    @GetMapping("/upload")
    public String showUploadForm() {
        return "manga/upload";
    }

    @PostMapping("/upload")
    //todo Также нужно что то сделать с тем, что изображения работают только, если находятся в папке таргет
    public String upload (
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("author") String author,
            @RequestParam("description") String description,
            Model model) {

        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload");
            return "manga/upload";
        }

        mangaService.save(file, title, author, description, model);

        return "manga/uploadStatus";
    }
}
