package ru.doreamon08.springcourse.Project2Boot.services;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.doreamon08.springcourse.Project2Boot.models.Book;
import ru.doreamon08.springcourse.Project2Boot.models.Manga;
import ru.doreamon08.springcourse.Project2Boot.repositories.MangaRepository;
import ru.doreamon08.springcourse.Project2Boot.util.FileSaver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


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

    public List<Manga> findAll(int page, int mangaPerPage, boolean sortByYear) {
        List<Manga> all = mangaRepository.findAll();
//        if (sortByYear)
//            all = bookRepository.findAll(Sort.by("year"));
        if (mangaPerPage != 0)
            all = mangaRepository.findAll(PageRequest.of(page, mangaPerPage)).getContent();
//        if (booksPerPage != 0 && sortByYear)
//            all = bookRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
        return all;
    }

    public void save(MultipartFile file, String title,
                     String author, String description,
                     Model model) {

        Manga manga = new Manga();

        String linkToPreview = FileSaver.handleFileUpload(file, model, "src/main/resources/static/images/previews/", "/images/previews/");

        manga.setTitle(title);
        manga.setAuthor(author);
        manga.setDescription(description);
        manga.setLinkToPreviewImage(linkToPreview);

        mangaRepository.save(manga);

    }

}
