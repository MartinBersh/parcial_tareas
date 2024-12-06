package com.ejemplo.tareas;

import com.ejemplo.tareas.model.Tarea;
import com.ejemplo.tareas.repository.TareaRepository;
import com.ejemplo.tareas.services.TareaServices;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@SpringBootTest
public class PruebasEndPointsTareas {

    @LocalServerPort
    private int port;
    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }
    private TareaServices tareaServices;
    private List<Tarea> tareas;

    @Autowired
    private TareaRepository tareaRepository;

    @Test
    void testCrearTarea() {
        Tarea tarea = new Tarea();
        tarea.setDescripcion("Terminar el parcial");

        given()
                .contentType(ContentType.JSON)
                .body(tarea)
                .when()
                .post("/api/tareas")
                .then()
                .statusCode(201)
                .body("descripcion", equalTo("Terminar el parcial"));
    }

    @Test
    void testGetTarea() {
        Tarea tareaCreada = new Tarea();
        tareaCreada.setDescripcion("Terminar el semestre");

        Integer tareaId = given()
                .contentType(ContentType.JSON)
                .body(tareaCreada)
                .when()
                .post("/api/tareas")
                .then()
                .statusCode(201)
                .extract().path("id");

        given()
                .when()
                .get("/api/tareas/{id}", tareaId)
                .then()
                .statusCode(200)
                .body("id", equalTo(tareaId))
                .body("descripcion", equalTo("Terminar el semestre"));
    }

    @Test
    void testUpdateTarea() {
        Tarea createdUser = new Tarea();
        createdUser.setDescripcion("Almorzar weno");

        Integer tareaId = given()
                .contentType(ContentType.JSON)
                .body(createdUser)
                .when()
                .post("/api/tareas")
                .then()
                .statusCode(201)
                .extract().path("id");

        createdUser.setDescripcion("Almorzar mas o menos weno");

        given()
                .contentType(ContentType.JSON)
                .body(createdUser)
                .when()
                .put("/api/tareas/{id}", tareaId)
                .then()
                .statusCode(200)
                .body("descripcion", equalTo("Almorzar mas o menos weno"));
    }

    @Test
    void testDeleteTarea() {
        Tarea createdUser = new Tarea();
        createdUser.setDescripcion("Pasear al perro");

        Integer tareaId = given()
                .contentType(ContentType.JSON)
                .body(createdUser)
                .when()
                .post("/api/tareas")
                .then()
                .statusCode(201)
                .extract().path("id");

        given()
                .when()
                .delete("/api/tareas/{id}", tareaId)
                .then()
                .statusCode(200);
    }

}


