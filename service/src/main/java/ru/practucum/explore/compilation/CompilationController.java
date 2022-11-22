package ru.practucum.explore.compilation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practucum.explore.compilation.model.Compilation;
import ru.practucum.explore.compilation.service.CompilationService;

import java.util.List;

@RestController
@RequestMapping("/compilations")
@RequiredArgsConstructor
public class CompilationController {
    private final CompilationService compilationService;

    @GetMapping("/{compId}")
    public Compilation getById(@PathVariable Integer compId) {
        return compilationService.getById(compId);
    }

    @GetMapping
    public List<Compilation> getAllCompilations(@RequestParam(required = false) Boolean pinned,
                                                @RequestParam(required = false, defaultValue = "0") int from,
                                                @RequestParam(required = false, defaultValue = "20") int size) {
        return compilationService.getAllCompilations(pinned, from, size);

    }
}
