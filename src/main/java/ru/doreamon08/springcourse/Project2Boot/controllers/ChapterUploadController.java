package ru.doreamon08.springcourse.Project2Boot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.doreamon08.springcourse.Project2Boot.models.Chapter;
import ru.doreamon08.springcourse.Project2Boot.models.ChapterPage;
import ru.doreamon08.springcourse.Project2Boot.services.ChapterService;
import ru.doreamon08.springcourse.Project2Boot.services.MangaService;
import ru.doreamon08.springcourse.Project2Boot.util.FileSaver;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChapterUploadController {

    private ChapterService chapterService;
    private MangaService mangaService;

    public ChapterUploadController(ChapterService chapterService, MangaService mangaService) {
        this.chapterService = chapterService;
        this.mangaService = mangaService;
    }

    @GetMapping("/chapter/upload")
    public String uploadChapterForm(Model model) {
        return "manga/chapters/chapter_upload";
    }

    @PostMapping("/chapter/upload")
    public String uploadChapter(@RequestParam("title") String title,
                                @RequestParam("number") int number,
                                @RequestParam("pages") MultipartFile[] pages,
                                @RequestParam("manga_id") int manga_id,
                                Model model) {
        // Создание и сохранение новой главы
        Chapter chapter = new Chapter();
        chapter.setTitle(title);
        chapter.setNumber(number);
        chapter.setMangaOwner(mangaService.findOne(manga_id));

        List<ChapterPage> chapterPages = new ArrayList<>();
        for (int i = 0; i < pages.length; i++) {
            MultipartFile page = pages[i];
            ChapterPage chapterPage = new ChapterPage();
            chapterPage.setPageNumber(i + 1);
            // Предположим, что у вас есть метод для сохранения файла и получения URL
            String pageUrl = FileSaver.handleFileUpload(page, model, "src/main/resources/static/images/chapters/");
            chapterPage.setPageUrl(pageUrl);
            chapterPage.setChapterOwner(chapter);
            chapterPages.add(chapterPage);
        }

        chapter.setPages(chapterPages);
        chapterService.saveChapter(chapter);

        return "redirect:/chapter/" + chapter.getId();
    }
}

