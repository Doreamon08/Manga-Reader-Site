package ru.doreamon08.springcourse.Project2Boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.doreamon08.springcourse.Project2Boot.models.Chapter;

import java.util.List;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Integer> {

    List<Chapter> findChapterByMangaOwnerId(int mangaId);

}
