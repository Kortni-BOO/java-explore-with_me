package ru.practucum.explore.compilation.service;

import ru.practucum.explore.compilation.dto.NewCompilationDto;
import ru.practucum.explore.compilation.model.Compilation;

import java.util.List;

public interface CompilationService {
    //Получение подборки событий по его id
    Compilation getById(Integer compId);

    //Получение подборок событий
    List<Compilation> getAllCompilations(Boolean pinned, int from, int size);

    //Добавление новой подборки.
    Compilation createCompilation(NewCompilationDto compilationDto);

    //Удаление подборки.
    void deleteCompilation(Integer compId);

    //Удалить событие из подборки.
    void deleteEventCompilation(Integer compId, long eventId);

    //Добавить событие в подборку.
    void addEventCompilation(Integer compId, long eventId);

    //Открепить подборку на главной странице.
    void unpinCompilation(Integer compId);

    //Закрепить подборку на главной странице.
    void pinCompilation(Integer compId);
}
