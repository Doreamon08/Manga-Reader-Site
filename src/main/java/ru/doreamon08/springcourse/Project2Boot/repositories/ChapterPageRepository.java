package ru.doreamon08.springcourse.Project2Boot.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.doreamon08.springcourse.Project2Boot.models.ChapterPage;

import java.util.List;

//@Repository
//public interface ChapterPageRepository extends JpaRepository<ChapterPage, Integer> {
//
//    List<ChapterPage> findAllByChapterOwnerId(int chapter_id, PageRequest pageRequest);
//
//}

@Repository
public interface ChapterPageRepository extends JpaRepository<ChapterPage, Integer> {
    @Query("SELECT p FROM ChapterPage p WHERE p.chapterOwner.id = :chapterId")
    Page<ChapterPage> findByChapterId(@Param("chapterId") int chapterId, Pageable pageable);
}