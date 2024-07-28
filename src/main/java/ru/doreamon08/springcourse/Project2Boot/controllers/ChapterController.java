package ru.doreamon08.springcourse.Project2Boot.controllers;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.doreamon08.springcourse.Project2Boot.models.Chapter;
import ru.doreamon08.springcourse.Project2Boot.models.ChapterPage;
import ru.doreamon08.springcourse.Project2Boot.services.ChapterService;

@Controller
public class ChapterController {

    private ChapterService chapterService;

    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @GetMapping("/chapter/{id}")
    public String viewChapter(@PathVariable("id") int chapterId,
                              @RequestParam(value = "page", defaultValue = "0") int page,
                              @RequestParam(value = "size", defaultValue = "1") int size,
                              Model model) {
        Chapter chapter = chapterService.getChapter(chapterId);
        Page<ChapterPage> pages = chapterService.getChapterPages(chapterId, page, size);

        model.addAttribute("chapter", chapter);
        model.addAttribute("pages", pages);

        return "manga/chapters/show";
    }
}

