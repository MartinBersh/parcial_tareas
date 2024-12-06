package com.ejemplo.tareas;

import com.ejemplo.tareas.model.Tarea;
import com.ejemplo.tareas.services.TareaServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PruebasUnitariasTareas {

    private TareaServices tareaServices;
    private List<Tarea> mockTareas;

    @BeforeEach
    void setUp() {
        mockTareas = new ArrayList<>();
        tareaServices = new TareaServices(mockTareas);
    }
    @Test
    void testCreateTarea() {
        Tarea nuevaTarea = new Tarea();
        nuevaTarea.setDescripcion("Tarea de prueba");

        Tarea result = tareaServices.createTarea(nuevaTarea);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Tarea de prueba", result.getDescripcion());
        assertEquals(1, mockTareas.size());
        assertTrue(mockTareas.contains(result));
    }

    @Test
    void testGetTareaById() {
        Tarea tareaExistente = new Tarea();
        tareaExistente.setId(1L);
        tareaExistente.setDescripcion("Tarea existente");
        mockTareas.add(tareaExistente);

        Tarea result = tareaServices.getTareaById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Tarea existente", result.getDescripcion());
    }


    @Test
    void testUpdateTarea() {
        Tarea tareaExistente = new Tarea();
        tareaExistente.setId(1L);
        tareaExistente.setDescripcion("Tarea antigua");
        mockTareas.add(tareaExistente);

        Tarea tareaActualizada = new Tarea();
        tareaActualizada.setDescripcion("Tarea actualizada");

        Tarea result = tareaServices.updateTarea(1L, tareaActualizada);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Tarea actualizada", result.getDescripcion());
    }


    @Test
    void testDeleteTarea() {
        Tarea tareaExistente = new Tarea();
        tareaExistente.setId(1L);
        mockTareas.add(tareaExistente);

        tareaServices.deleteTarea(1L);

        assertEquals(0, mockTareas.size());
        assertFalse(mockTareas.contains(tareaExistente));
    }
}
