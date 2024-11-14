package com.ejemplo.tareas.services;

import com.ejemplo.tareas.model.Tarea;
import com.ejemplo.tareas.repository.TareaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TareaServices {

    private final List<Tarea> tareas;

    private final TareaRepository repository = null;
    private final AtomicLong counter = new AtomicLong();

    public TareaServices(List<Tarea> tareas) {
        this.tareas = tareas;
    }


    public Tarea createTarea(Tarea tarea) {
        tarea.setId(counter.incrementAndGet());
        tareas.add(tarea);
        return tarea;
    }

    public List<Tarea> getAllTareas() {
        return repository.findAll();
    }
    public Tarea getTareaById(Long id) {
        return tareas.stream()
                .filter(tarea -> tarea.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Tarea updateTarea(Long id, Tarea tarea) {
        Optional<Tarea> tareaExistente = tareas.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();

        if (tareaExistente.isPresent()) {
            Tarea t = tareaExistente.get();
            t.setDescripcion(tarea.getDescripcion());
            return t;
        }
        return null;
    }


    public void deleteTarea(Long id) {
        tareas.removeIf(tarea -> tarea.getId().equals(id));
    }



}