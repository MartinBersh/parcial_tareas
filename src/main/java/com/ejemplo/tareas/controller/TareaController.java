package com.ejemplo.tareas.controller;

import com.ejemplo.tareas.model.Tarea;
import com.ejemplo.tareas.repository.TareaRepository;
import com.ejemplo.tareas.services.TareaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    @Autowired
    private TareaServices tareaServices;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tarea createTarea(@RequestBody Tarea tarea) {
        return tareaServices.createTarea(tarea);
    }

    @GetMapping
    public List<Tarea> obtenerTodasLasTareas() {
        return tareaServices.getAllTareas();
    }
    @GetMapping("/{id}")
    public Tarea getTarea(@PathVariable Long id) {
        return tareaServices.getTareaById(id);
    }

    @PutMapping("/{id}")
    public Tarea updateTarea(@PathVariable Long id, @RequestBody Tarea tarea) {
        return tareaServices.updateTarea(id, tarea);
    }

    @DeleteMapping("/{id}")
    public void deleteTarea(@PathVariable Long id) {
        tareaServices.deleteTarea(id);
    }

}