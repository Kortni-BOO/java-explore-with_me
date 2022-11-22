package ru.practucum.explore.compilation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practucum.explore.compilation.dto.NewCompilationDto;
import ru.practucum.explore.compilation.model.Compilation;
import ru.practucum.explore.compilation.service.CompilationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/compilations")
@RequiredArgsConstructor
public class CompilationControllerAdmin {
    private final CompilationService compilationService;

    @PostMapping
    //Добавление новой подборки.
    public Compilation createCompilation(@RequestBody @Valid NewCompilationDto compilationDto) {
        return compilationService.createCompilation(compilationDto);
    }

    @DeleteMapping("/{compId}")
    //Удаление подборки.
    public void deleteCompilation(@PathVariable Integer compId) {
        compilationService.deleteCompilation(compId);
    }

    @DeleteMapping("/{compId}/events/{eventId}")
    //Удалить событие из подборки.
    public void deleteEventCompilation(@PathVariable Integer compId, @PathVariable long eventId) {
        compilationService.deleteEventCompilation(compId, eventId);
    }

    @PatchMapping("/{compId}/events/{eventId}")
    //Добавить событие в подборку.
    public void addEventCompilation(@PathVariable Integer compId, @PathVariable long eventId) {
        compilationService.addEventCompilation(compId, eventId);
    }

    @DeleteMapping("/{compId}/pin")
    //Открепить подборку на главной странице.
    public void unpinCompilation(@PathVariable Integer compId) {
        compilationService.unpinCompilation(compId);
    }

    @PatchMapping("/{compId}/pin")
    //Закрепить подборку на главной странице.
    public void pinCompilation(@PathVariable Integer compId) {
        compilationService.pinCompilation(compId);
    }
}
