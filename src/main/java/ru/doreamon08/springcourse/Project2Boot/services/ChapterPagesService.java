//package ru.doreamon08.springcourse.Project2Boot.services;
//
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Service;
//import ru.doreamon08.springcourse.Project2Boot.models.ChapterPage;
//import ru.doreamon08.springcourse.Project2Boot.repositories.ChapterPageRepository;
//
//import java.util.List;
//
//@Service
//public class ChapterPagesService {
//
//    private final ChapterPageRepository chapterPageRepository;
//
//    public ChapterPagesService(ChapterPageRepository chapterPageRepository) {
//        this.chapterPageRepository = chapterPageRepository;
//    }
//
//    public List<ChapterPage> findAllPages(int chapter_id, int page, int imagesPerPage) {
//
//       return chapterPageRepository.findAllByChapterOwnerId(chapter_id, PageRequest.of(page, imagesPerPage));
//
//    }
//
//}
