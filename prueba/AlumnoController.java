/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.prueba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
/**
 *
 * @author alvarogasca
 */
@Controller

public class AlumnoController {
    @Autowired
    private AlumnoRepository alumnoRepository;

   
    
    @GetMapping("/")
    public String listaPersonas(Model model, @PageableDefault(size = 5) Pageable pageable) {
        Page<Alumno> alumnos = alumnoRepository.findAll(pageable);
        model.addAttribute("alumnos", alumnos);
        return "home";
    }

    @GetMapping("/agregar")
    public String agregarAlumnoForm(Model model) {
        model.addAttribute("alumno", new Alumno());
        return "agregar";
    }

    @PostMapping("/agregar")
    public String agregarAlumnoSubmit(Alumno alumno) {
        alumnoRepository.save(alumno);
        return "redirect:/";
    }

    @GetMapping("/modificar/{id}")
    public String modificarAlumnoForm(@PathVariable Integer id, Model model) {
        Alumno alumno = alumnoRepository.findById(id).orElse(null);
        if (alumno != null) {
            model.addAttribute("alumno", alumno);
            return "modificar";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/modificar/{id}")
    public String modificarAlumnoSubmit(@PathVariable Integer id, Alumno alumno) {
        Alumno alumnoExistente = alumnoRepository.findById(id).orElse(null);
        if (alumnoExistente != null) {
            alumnoExistente.setNombre(alumno.getNombre());
            alumnoExistente.setApellido(alumno.getApellido());
            alumnoExistente.setEdad(alumno.getEdad());
            alumnoExistente.setDni(alumno.getDni());

            alumnoRepository.save(alumnoExistente);
        }
        return "redirect:/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAlumno(@PathVariable Integer id) {
        alumnoRepository.deleteById(id);
        return "redirect:/";
    }

}
