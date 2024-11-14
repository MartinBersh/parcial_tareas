package com.ejemplo.tareas;

import com.ejemplo.tareas.model.Tarea;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PruebasEndPointsTareas {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

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
    void testGetUser() {
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
    void testUpdateUser() {
        Tarea createdUser = new Tarea();
        createdUser.setDescripcion("Almorzar weno");

        Integer userId = given()
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
                .put("/api/tareas/{id}", userId)
                .then()
                .statusCode(200)
                .body("descripcion", equalTo("Almorzar mas o menos weno"));
    }

    @Test
    void testDeleteUser() {
        Tarea createdUser = new Tarea();
        createdUser.setDescripcion("Pasear al perro");

        Integer userId = given()
                .contentType(ContentType.JSON)
                .body(createdUser)
                .when()
                .post("/api/tareas")
                .then()
                .statusCode(201)
                .extract().path("id");

        given()
                .when()
                .delete("/api/tareas/{id}", userId)
                .then()
                .statusCode(200);
    }

}


