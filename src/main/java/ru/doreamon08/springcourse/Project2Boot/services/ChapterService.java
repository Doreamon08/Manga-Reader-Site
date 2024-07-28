package ru.doreamon08.springcourse.Project2Boot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.doreamon08.springcourse.Project2Boot.models.Chapter;
import ru.doreamon08.springcourse.Project2Boot.models.ChapterPage;
import ru.doreamon08.springcourse.Project2Boot.repositories.ChapterPageRepository;
import ru.doreamon08.springcourse.Project2Boot.repositories.ChapterRepository;

@Service
public class ChapterService {

    private ChapterRepository chapterRepository;

    private ChapterPageRepository chapterPageRepository;

    public ChapterService(ChapterRepository chapterRepository, ChapterPageRepository chapterPageRepository) {
        this.chapterRepository = chapterRepository;
        this.chapterPageRepository = chapterPageRepository;
    }

    public Chapter getChapter(int chapterId) {
        return chapterRepository.findById(chapterId).orElseThrow(() -> new RuntimeException("Chapter not found"));
    }

    public Page<ChapterPage> getChapterPages(int chapterId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return chapterPageRepository.findByChapterId(chapterId, pageable);
    }

    public void saveChapter(Chapter chapter) {
        chapterRepository.save(chapter);
        chapterPageRepository.saveAll(chapter.getPages());
    }
}
