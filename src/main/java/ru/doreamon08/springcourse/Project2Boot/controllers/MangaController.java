package ru.doreamon08.springcourse.Project2Boot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.doreamon08.springcourse.Project2Boot.models.Chapter;
import ru.doreamon08.springcourse.Project2Boot.models.Manga;
import ru.doreamon08.springcourse.Project2Boot.services.ChapterService;
import ru.doreamon08.springcourse.Project2Boot.services.MangaService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Controller
@RequestMapping("/manga")
public class MangaController {
    private final MangaService mangaService;
    private final ChapterService chapterService;

    public MangaController(MangaService mangaService, ChapterService chapterService) {
        this.mangaService = mangaService;
        this.chapterService = chapterService;
    }

    @GetMapping("/catalog")
    public String index(
            Model model,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "manga_per_page", required = false, defaultValue = "0") int mangaPerPage,
            @RequestParam(name = "sort_by_year", required = false, defaultValue = "false") boolean sortByYear) {

        model.addAttribute("manga", mangaService.findAll(page, mangaPerPage, sortByYear));
        return "manga/index";
    }

    @GetMapping("{manga_name}")
    public String show(
            @PathVariable("manga_name") int manga_id,
            Model model) {

        Manga manga = mangaService.findOne(manga_id);
        List<Chapter> chapters = chapterService.findChapters(manga_id);

        model.addAttribute("manga", manga);
        model.addAttribute("chapters", chapters);

        return "manga/show";
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
